package com.cheatbreaker.client.ui.element.type.custom;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.module.ModulesGuiButtonElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleXRoom;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import recovered.unidentified.UnidentifiedClass5100;

public class KeybindElement extends AbstractModulesGuiElement {
   public boolean field_0001 = false;
   public StructureOceanMonumentPieces$DoubleXRoom field_0002;
   public CombatTracker field_0000;
   public ModulesGuiButtonElement field_0003;

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      CheatBreaker.getInstance()
         .field_0068
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + 4,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
         );
      if (this.field_0001 && Keyboard.getEventKeyState()) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         if (Keyboard.getKeyName(Keyboard.getEventKey()).equalsIgnoreCase("Back")
            || CheatBreaker.getInstance().getModuleManager().method_21668(this.setting, Keyboard.getEventKey())) {
            this.setting.setValue(0);
            this.field_0003.displayString = "NONE";
            this.setting.method_08885(false);
            this.field_0001 = false;
            return;
         }

         this.setting.setValue(Keyboard.getEventKey());
         this.setting.method_08885(false);
         this.field_0003.displayString = Keyboard.getKeyName((Integer)this.setting.getValue());
         this.field_0001 = false;
      }

      if (this.field_0001 && Mouse.getEventButton() != 0 && Mouse.getEventButtonState()) {
         if (CheatBreaker.getInstance().getModuleManager().method_21668(this.setting, Mouse.getEventButton())) {
            this.setting.setValue(0);
            this.field_0003.displayString = "NONE";
            this.setting.method_08885(false);
            this.field_0001 = false;
            return;
         }

         this.setting.setValue(Mouse.getEventButton());
         this.setting.method_08885(true);
         this.field_0003.displayString = "Button " + (this.setting.method_08912() + 1);
         this.field_0001 = false;
      }

      this.field_0003.yOffset = this.yOffset;
      this.field_0003.setDimensions(this.x + this.width - 109, this.y + 2, 93, 15);
      this.field_0003.handleDrawElement(var1, var2, var3);
      this.method_23220(this.setting, var1, var2);
   }

   public ModulesGuiButtonElement method_27904() {
      return this.field_0003;
   }

   public KeybindElement(Setting var1, float var2) {
      super(var2);
      this.setting = var1;
      this.height = 18;
      this.field_0003 = new ModulesGuiButtonElement(
         CheatBreaker.getInstance().field_0022,
         null,
         var1.method_08879() ? "Button " + (var1.method_08912() + 1) : Keyboard.getKeyName(var1.method_08912()),
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
      if (this.field_0003.isMouseInside(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.field_0001 = true;
         this.field_0003.displayString = "<PRESS ANY KEY>";
      }
   }
}
