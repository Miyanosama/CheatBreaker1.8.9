package net.minecraft.client.gui;

/** LWJGL may deliver committed IME text with KEY_NONE and no key-down flag. */
public final class ImeKeyboardEvent {
   private ImeKeyboardEvent() { }

   public static boolean shouldDispatch(boolean keyDown, int keyCode, char character) {
      return keyDown || keyCode == 0 && character != '\0'
         && !Character.isISOControl(character) && !Character.isSurrogate(character)
         && Character.isDefined(character);
   }
}
