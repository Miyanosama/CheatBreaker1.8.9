package com.cheatbreaker.client.config;

import junit.framework.TestCase;
import org.lwjgl.input.Keyboard;

public class FriendListKeybindTest extends TestCase {
   public void testDefaultRequiresShiftTab() {
      assertTrue(FriendListKeybind.matches(FriendListKeybind.DEFAULT, Keyboard.KEY_TAB, FriendListKeybind.SHIFT));
      assertFalse(FriendListKeybind.matches(FriendListKeybind.DEFAULT, Keyboard.KEY_TAB, 0));
      assertFalse(FriendListKeybind.matches(FriendListKeybind.DEFAULT, Keyboard.KEY_F, FriendListKeybind.SHIFT));
      assertEquals("SHIFT + TAB", FriendListKeybind.getDisplayName(FriendListKeybind.DEFAULT));
   }

   public void testRebindingReplacesOldCombination() {
      int binding = FriendListKeybind.encode(Keyboard.KEY_F, FriendListKeybind.CTRL | FriendListKeybind.ALT);
      assertTrue(FriendListKeybind.matches(binding, Keyboard.KEY_F, FriendListKeybind.CTRL | FriendListKeybind.ALT));
      assertFalse(FriendListKeybind.matches(binding, Keyboard.KEY_TAB, FriendListKeybind.SHIFT));
      assertFalse(FriendListKeybind.matches(binding, Keyboard.KEY_F, FriendListKeybind.CTRL));
      assertTrue(FriendListKeybind.matches(Keyboard.KEY_G, Keyboard.KEY_G, 0));
   }

   public void testClearedBindingNeverMatches() {
      assertFalse(FriendListKeybind.matches(0, 0, 0));
      assertFalse(FriendListKeybind.matches(0, Keyboard.KEY_TAB, FriendListKeybind.SHIFT));
      assertEquals("NONE", FriendListKeybind.getDisplayName(0));
   }

   public void testModifiersAreNotCapturedAsPrimaryKeys() {
      int[] keys = {Keyboard.KEY_LSHIFT, Keyboard.KEY_RSHIFT, Keyboard.KEY_LCONTROL,
         Keyboard.KEY_RCONTROL, Keyboard.KEY_LMENU, Keyboard.KEY_RMENU};
      for (int key : keys) {
         assertTrue(FriendListKeybind.isModifier(key));
      }
      assertFalse(FriendListKeybind.isModifier(Keyboard.KEY_TAB));
   }
}
