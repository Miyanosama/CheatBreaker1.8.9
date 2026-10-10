package com.cheatbreaker.client.config;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public final class FriendListKeybind {
   public static final int SHIFT = 256;
   public static final int CTRL = 512;
   public static final int ALT = 1024;
   public static final int DEFAULT = SHIFT | Keyboard.KEY_TAB;

   private FriendListKeybind() {
   }

   public static int encode(int key, int modifiers) {
      return (key & 255) | modifiers;
   }

   public static boolean matches(int binding, int key, int modifiers) {
      return (binding & 255) != 0 && binding == encode(key, modifiers);
   }

   public static int getModifiers() {
      return (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) ? SHIFT : 0)
         | (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) ? CTRL : 0)
         | (Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU) ? ALT : 0);
   }

   public static boolean isModifier(int key) {
      return key == Keyboard.KEY_LSHIFT || key == Keyboard.KEY_RSHIFT
         || key == Keyboard.KEY_LCONTROL || key == Keyboard.KEY_RCONTROL
         || key == Keyboard.KEY_LMENU || key == Keyboard.KEY_RMENU;
   }

   public static boolean isPressed(int key) {
      // Allow the settings screen to capture a binding without opening the overlay.
      return !(Minecraft.getMinecraft().currentScreen instanceof CBModulesGui)
         && matches(CheatBreaker.getInstance().getGlobalSettings().friendListKeybind.method_08912(), key, getModifiers());
   }

   public static String getDisplayName(int binding) {
      if ((binding & 255) == 0) {
         return "NONE";
      }
      return ((binding & CTRL) != 0 ? "CTRL + " : "")
         + ((binding & ALT) != 0 ? "ALT + " : "")
         + ((binding & SHIFT) != 0 ? "SHIFT + " : "")
         + Keyboard.getKeyName(binding & 255);
   }
}
