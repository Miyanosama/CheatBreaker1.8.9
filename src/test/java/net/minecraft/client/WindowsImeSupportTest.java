package net.minecraft.client;

import com.sun.jna.Pointer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiEditSign;
import sun.misc.Unsafe;

public class WindowsImeSupportTest extends TestCase {
   private static <T> T withoutGameStartup(Class<T> type) throws Exception {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
   }

   public void testGameInputDetachesOnceAndTextInputRestoresTheSameContext() {
      FakeApi api = new FakeApi();
      Pointer original = new Pointer(100);
      api.contexts.put(1L, original);
      WindowsImeSupport.ContextController controller = new WindowsImeSupport.ContextController();
      controller.update(1L, false, api);
      assertNull(api.contexts.get(1L));
      assertEquals(1, api.associations);
      controller.update(1L, false, api);
      assertEquals(1, api.associations);
      controller.update(1L, true, api);
      assertSame(original, api.contexts.get(1L));
      assertEquals(2, api.associations);
      assertEquals(0, api.defaults);
   }

   public void testWindowRecreationDoesNotReuseAnOldWindowsContext() {
      FakeApi api = new FakeApi();
      Pointer original = new Pointer(100);
      api.contexts.put(1L, original);
      WindowsImeSupport.ContextController controller = new WindowsImeSupport.ContextController();
      controller.update(1L, false, api);
      controller.update(2L, true, api);
      assertEquals(1, api.defaults);
      assertNotSame(original, api.contexts.get(2L));
      assertNull(api.contexts.get(1L));
   }

   public void testShutdownRestoresInputContextAndMissingWindowIsIgnored() {
      FakeApi api = new FakeApi();
      Pointer original = new Pointer(100);
      api.contexts.put(1L, original);
      WindowsImeSupport.ContextController controller = new WindowsImeSupport.ContextController();
      controller.update(0L, false, api);
      assertEquals(0, api.associations);
      controller.update(1L, false, api);
      controller.restore(api);
      assertSame(original, api.contexts.get(1L));
      controller.restore(api);
      assertEquals(2, api.associations);
   }

   public void testMenusOnlyRequireImeWhileATextFieldIsFocused() throws Exception {
      GuiScreen screen = withoutGameStartup(GuiChat.class);
      Object field = new Object();
      boolean[] focused = {false};
      WindowsImeSupport.trackFocus(screen, field, () -> focused[0]);
      assertFalse(WindowsImeSupport.requiresIme(null));
      assertFalse(WindowsImeSupport.requiresIme(screen));
      focused[0] = true;
      assertTrue(WindowsImeSupport.requiresIme(screen));
      focused[0] = false;
      assertFalse(WindowsImeSupport.requiresIme(screen));
      focused[0] = true;
      WindowsImeSupport.beginScreen(screen);
      assertFalse(WindowsImeSupport.requiresIme(screen));
   }

   public void testSignAndWritableBookAllowImeButReadOnlyBookDoesNot() throws Exception {
      assertTrue(WindowsImeSupport.requiresIme(withoutGameStartup(GuiEditSign.class)));
      GuiScreenBook book = withoutGameStartup(GuiScreenBook.class);
      assertFalse(WindowsImeSupport.requiresIme(book));
      book.bookIsUnsigned = true;
      assertTrue(WindowsImeSupport.requiresIme(book));
   }

   private static final class FakeApi implements WindowsImeSupport.ContextApi {
      final Map<Long, Pointer> contexts = new HashMap<>();
      int associations;
      int defaults;
      public Pointer get(long window) { return contexts.get(window); }
      public void release(long window, Pointer context) { assertSame(contexts.get(window), context); }
      public Pointer associate(long window, Pointer context) {
         associations++;
         return contexts.put(window, context);
      }
      public void restoreDefault(long window) {
         defaults++;
         contexts.put(window, new Pointer(1000 + window));
      }
   }
}
