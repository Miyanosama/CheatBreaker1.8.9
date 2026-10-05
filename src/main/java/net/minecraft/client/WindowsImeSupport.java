package net.minecraft.client;

import com.cheatbreaker.client.CheatBreaker;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BooleanSupplier;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.Display;

/** Detach Windows IME during game input, restoring it only for text editing. */
public final class WindowsImeSupport {
   private static final Map<GuiScreen, List<FocusEntry>> textFields = new WeakHashMap<>();
   private static final ContextController context = new ContextController();
   private static boolean unavailable;

   private WindowsImeSupport() { }

   public static void beginScreen(GuiScreen screen) {
      textFields.remove(screen);
   }

   public static void focusChanged(Object field, BooleanSupplier focused) {
      Minecraft minecraft = Minecraft.getMinecraft();
      if (minecraft == null || minecraft.currentScreen == null) return;
      trackFocus(minecraft.currentScreen, field, focused);
      updateGameInput(minecraft.currentScreen);
   }

   static void trackFocus(GuiScreen screen, Object field, BooleanSupplier focused) {
      List<FocusEntry> entries = textFields.computeIfAbsent(screen, ignored -> new ArrayList<>());
      for (FocusEntry entry : entries) if (entry.field == field) return;
      entries.add(new FocusEntry(field, focused));
   }

   static boolean requiresIme(GuiScreen screen) {
      if (screen instanceof GuiEditSign
         || screen instanceof GuiScreenBook && ((GuiScreenBook)screen).bookIsUnsigned) return true;
      List<FocusEntry> entries = textFields.get(screen);
      if (entries != null) {
         for (FocusEntry entry : entries) if (entry.focused.getAsBoolean()) return true;
      }
      return false;
   }

   public static void updateGameInput(GuiScreen screen) {
      CheatBreaker client = CheatBreaker.getInstance();
      boolean enabled = client != null && client.globalSettings != null
         && client.globalSettings.preventImeSticking != null
         && client.globalSettings.preventImeSticking.method_08908();
      updateGameInput(screen, enabled);
   }

   public static void restoreGameInput() {
      updateGameInput(null, false);
   }

   private static void updateGameInput(GuiScreen screen, boolean enabled) {
      if (unavailable || Util.getOSType() != Util.EnumOS.WINDOWS) return;
      try {
         if (!Display.isCreated()) return;
         context.update(WindowHandle.get(), !enabled || requiresIme(screen), NativeApi.INSTANCE);
      } catch (Throwable error) {
         // Attempt to restore a detached context before disabling a failed native bridge.
         try { context.restore(NativeApi.INSTANCE); } catch (Throwable ignored) { }
         unavailable = true;
         LogManager.getLogger().warn("Could not switch the Windows IME context for game input", error);
      }
   }

   interface ContextApi {
      Pointer get(long window);
      void release(long window, Pointer inputContext);
      Pointer associate(long window, Pointer inputContext);
      void restoreDefault(long window);
   }

   static final class ContextController {
      private long window;
      private Pointer saved;

      void update(long handle, boolean editingText, ContextApi api) {
         if (handle == 0L) return;
         if (window != handle) {
            window = handle;
            saved = null; // A recreated HWND must never reuse the old HWND's context.
         }
         if (!editingText) {
            if (saved == null) {
               Pointer current = api.get(window);
               if (current != null && Pointer.nativeValue(current) != 0L) {
                  api.release(window, current);
                  saved = api.associate(window, null);
                  if (saved != null && Pointer.nativeValue(saved) == 0L) saved = null;
               }
            }
         } else if (saved != null) {
            restore(api);
         } else {
            Pointer current = api.get(window);
            if (current == null || Pointer.nativeValue(current) == 0L) api.restoreDefault(window);
            else api.release(window, current);
         }
      }

      void restore(ContextApi api) {
         if (saved != null) {
            api.associate(window, saved);
            saved = null;
         }
      }
   }

   private static final class NativeApi implements ContextApi {
      static final NativeApi INSTANCE = new NativeApi();
      public Pointer get(long window) { return Imm32.INSTANCE.ImmGetContext(new Pointer(window)); }
      public void release(long window, Pointer inputContext) { Imm32.INSTANCE.ImmReleaseContext(new Pointer(window), inputContext); }
      public Pointer associate(long window, Pointer inputContext) { return Imm32.INSTANCE.ImmAssociateContext(new Pointer(window), inputContext); }
      public void restoreDefault(long window) { Imm32.INSTANCE.ImmAssociateContextEx(new Pointer(window), null, 0x0010); }
   }

   private interface Imm32 extends StdCallLibrary {
      Imm32 INSTANCE = (Imm32)Native.loadLibrary("imm32", Imm32.class);
      Pointer ImmGetContext(Pointer window);
      int ImmReleaseContext(Pointer window, Pointer inputContext);
      Pointer ImmAssociateContext(Pointer window, Pointer inputContext);
      boolean ImmAssociateContextEx(Pointer window, Pointer inputContext, int flags);
   }

   private static final class WindowHandle {
      private static final Method GET_IMPLEMENTATION;
      private static final Method GET_HWND;
      static {
         try {
            GET_IMPLEMENTATION = Display.class.getDeclaredMethod("getImplementation");
            GET_HWND = Class.forName("org.lwjgl.opengl.WindowsDisplay", false,
               Display.class.getClassLoader()).getDeclaredMethod("getHwnd");
            GET_IMPLEMENTATION.setAccessible(true);
            GET_HWND.setAccessible(true);
         } catch (Exception error) {
            throw new IllegalStateException("Unsupported LWJGL Windows display bridge", error);
         }
      }
      static long get() throws Exception {
         return ((Long)GET_HWND.invoke(GET_IMPLEMENTATION.invoke(null))).longValue();
      }
   }

   private static final class FocusEntry {
      final Object field;
      final BooleanSupplier focused;
      FocusEntry(Object field, BooleanSupplier focused) {
         this.field = field;
         this.focused = focused;
      }
   }
}
