package net.minecraft.client.gui;

import junit.framework.TestCase;

public class ImeKeyboardEventTest extends TestCase {
   public void testCommittedChineseTextWithoutKeyDownIsDispatched() {
      assertTrue(ImeKeyboardEvent.shouldDispatch(false, 0, '\u4e2d'));
      assertTrue(ImeKeyboardEvent.shouldDispatch(false, 0, 'A'));
      assertTrue(ImeKeyboardEvent.shouldDispatch(true, 28, '\r'));
   }

   public void testKeyReleasesAndControlCharactersAreNotTypedAgain() {
      assertFalse(ImeKeyboardEvent.shouldDispatch(false, 30, 'A'));
      assertFalse(ImeKeyboardEvent.shouldDispatch(false, 0, '\0'));
      assertFalse(ImeKeyboardEvent.shouldDispatch(false, 0, '\r'));
      assertFalse(ImeKeyboardEvent.shouldDispatch(false, 0, '\ud800'));
      assertFalse(ImeKeyboardEvent.shouldDispatch(false, 0, '\uffff'));
   }
}
