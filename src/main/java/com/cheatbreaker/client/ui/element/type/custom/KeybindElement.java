package com.cheatbreaker.client.ui.element.type.custom;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.FriendListKeybind;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.module.ModulesGuiButtonElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import com.cheatbreaker.client.ui.util.GuiThemeColors;

public class KeybindElement extends AbstractModulesGuiElement {
   public boolean recoveredField471 = false;
   public ModulesGuiButtonElement recoveredField472;

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      CheatBreaker.getInstance()
         .recoveredField1589
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + 4,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
         );
      boolean friendList = this.setting == CheatBreaker.getInstance().getGlobalSettings().friendListKeybind;
      if (friendList && !this.recoveredField471) {
         this.recoveredField472.displayString = FriendListKeybind.getDisplayName(this.setting.method_08912());
      }
      if (this.recoveredField471 && Keyboard.getEventKeyState()
         && (!friendList || !FriendListKeybind.isModifier(Keyboard.getEventKey()))) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         if (Keyboard.getKeyName(Keyboard.getEventKey()).equalsIgnoreCase("Back")
            || !friendList && CheatBreaker.getInstance().getModuleManager().method_21668(this.setting, Keyboard.getEventKey())) {
            this.setting.setValue(0);
            this.recoveredField472.displayString = "NONE";
            this.setting.method_08885(false);
            this.recoveredField471 = false;
            return;
         }

         this.setting.setValue(friendList ? FriendListKeybind.encode(Keyboard.getEventKey(), FriendListKeybind.getModifiers()) : Keyboard.getEventKey());
         this.setting.method_08885(false);
         this.recoveredField472.displayString = friendList ? FriendListKeybind.getDisplayName(this.setting.method_08912()) : Keyboard.getKeyName((Integer)this.setting.getValue());
         this.recoveredField471 = false;
      }

      if (!friendList && this.recoveredField471 && Mouse.getEventButton() != 0 && Mouse.getEventButtonState()) {
         if (CheatBreaker.getInstance().getModuleManager().method_21668(this.setting, Mouse.getEventButton())) {
            this.setting.setValue(0);
            this.recoveredField472.displayString = "NONE";
            this.setting.method_08885(false);
            this.recoveredField471 = false;
            return;
         }

         this.setting.setValue(Mouse.getEventButton());
         this.setting.method_08885(true);
         this.recoveredField472.displayString = "Button " + (this.setting.method_08912() + 1);
         this.recoveredField471 = false;
      }

      this.recoveredField472.yOffset = this.yOffset;
      this.recoveredField472.setDimensions(this.x + this.width - 109, this.y + 2, 93, 15);
      this.recoveredField472.handleDrawElement(var1, var2, var3);
      this.method_23220(this.setting, var1, var2);
   }

   public ModulesGuiButtonElement method_27904() {
      return this.recoveredField472;
   }

   public KeybindElement(Setting var1, float var2) {
      super(var2);
      this.setting = var1;
      this.height = 18;
      this.recoveredField472 = new ModulesGuiButtonElement(
         CheatBreaker.getInstance().recoveredField1575,
         null,
         var1 == CheatBreaker.getInstance().getGlobalSettings().friendListKeybind ? FriendListKeybind.getDisplayName(var1.method_08912()) : var1.method_08879() ? "Button " + (var1.method_08912() + 1) : Keyboard.getKeyName(var1.method_08912()),
         this.x + this.width - 100,
         this.y,
         96,
         18,
         -9442858,
         var2,
         false,
         true
      );
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      if (this.recoveredField472.isMouseInside(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.recoveredField471 = true;
         this.recoveredField472.displayString = "<PRESS ANY KEY>";
      }
   }
}
