package com.cheatbreaker.client.ui.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.module.ModulesGuiButtonElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.overlay.element.InputFieldElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import recovered.unidentified.UnidentifiedClass4596;
import recovered.unidentified.UnidentifiedClass5100;

public class KeybindElement extends AbstractModulesGuiElement {
   public ModulesGuiButtonElement field_0001;
   public InputFieldElement field_0002;
   public boolean field_0000 = false;
   public boolean field_0003 = false;

   @Override
   public boolean method_23217(float var1, float var2, int var3, boolean var4) {
      this.method_23215();
      this.field_0002.handleElementMouseClicked(var1, var2, var3, var4);
      return false;
   }

   @Override
   public void method_23216(char var1, int var2) {
      if (var2 == 1) {
         this.field_0002.method_06028(false);
      } else {
         super.method_23216(var1, var2);
         this.field_0002.handleElementKeyTyped(var1, var2);
      }
   }

   public InputFieldElement method_29689() {
      return this.field_0002;
   }

   @Override
   public void setDimensions(int var1, int var2, int var3, int var4) {
      super.setDimensions(var1, var2, var3, var4);
      this.field_0002.setElementSize(this.x + (this.field_0003 ? 65 : 150), var2 + var4 - 14.0F, 166.0F, 13.0F);
   }

   public void method_29687() {
      CheatBreaker.getInstance().getModuleManager().field_0032.method_26048("");
      this.field_0001.displayString = "NONE";
      this.setting.method_08885(false);
      this.setting.method_08887(0);
      this.field_0000 = false;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      if (this.field_0001.isMouseInside(var1, var2) && this.field_0003) {
         if (!CheatBreaker.getInstance().getModuleManager().field_0032.method_26045().equals("")) {
            return;
         }

         CheatBreaker.getInstance().getModuleManager().field_0032.method_26048(this.setting.method_08911());
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.field_0000 = true;
         this.field_0001.displayString = "<PRESS ANY KEY>";
      }

      boolean var4 = var1 > this.x * this.scale
         && var1 < (this.x + this.width - (this.field_0003 ? 120 : 40)) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 18 + this.yOffset) * this.scale;
      if (var4) {
         Minecraft.getMinecraft().displayGuiScreen(new UnidentifiedClass4596(this.setting, CBModulesGui.instance, this.scale));
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
      }
   }

   public KeybindElement(Setting var1, float var2) {
      super(var2);
      this.setting = var1;
      this.field_0002 = new InputFieldElement(CheatBreaker.getInstance().field_0039, this.setting.getValue().toString(), 0, 0);
      this.field_0002.trimToLength(256);
      this.field_0001 = new ModulesGuiButtonElement(
         CheatBreaker.getInstance().field_0039,
         null,
         var1.method_08879() ? "Button " + (var1.method_08877() + 1) : Keyboard.getKeyName(var1.method_08877()),
         this.x + this.width - 100,
         this.y,
         50,
         18,
         -9442858,
         this.scale,
         false,
         true
      );
      this.height = 18;
      if (var1.method_08911().startsWith("Hot key")) {
         this.field_0003 = true;
      }
   }

   @Override
   public void method_23215() {
      this.field_0002.handleElementUpdate();
   }

   @Override
   public void method_23221() {
      this.field_0002.handleElementClose();
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      if (this.field_0000 && this.field_0003 && Keyboard.getEventKeyState()) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         CheatBreaker.getInstance().getModuleManager().field_0032.method_26048(this.setting.method_08911());

         for (Setting var5 : CheatBreaker.getInstance().getModuleManager().field_0032.field_0004) {
            if (Keyboard.getEventKey() == var5.method_08877()
               || CheatBreaker.getInstance().getModuleManager().method_21668(this.setting, Keyboard.getEventKey())) {
               this.method_29687();
               break;
            }
         }

         if (Keyboard.getEventKey() == 14) {
            this.method_29687();
         }

         if (!this.field_0001.displayString.equals("NONE")) {
            if (this.field_0001.field_0011) {
               this.field_0001.method_07927(false);
            }

            this.setting.method_08885(false);
            this.setting.method_08887(Keyboard.getEventKey());
            this.field_0001.displayString = Keyboard.getKeyName(this.setting.method_08877());
            this.field_0000 = false;
            CheatBreaker.getInstance().getModuleManager().field_0032.method_26048("");
         }
      }

      if (this.field_0000 && this.field_0003 && Mouse.getEventButton() != 0 && Mouse.getEventButtonState()) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         CheatBreaker.getInstance().getModuleManager().field_0032.method_26048(this.setting.method_08911());

         for (Setting var7 : CheatBreaker.getInstance().getModuleManager().field_0032.field_0004) {
            if (Mouse.getEventButton() == var7.method_08877()
               || CheatBreaker.getInstance().getModuleManager().method_21668(this.setting, Mouse.getEventButton())) {
               this.field_0001.displayString = "NONE";
               CheatBreaker.getInstance().getModuleManager().field_0032.method_26048("");
               this.setting.method_08885(false);
               this.setting.method_08887(0);
               this.field_0000 = false;
               break;
            }
         }

         if (Keyboard.getEventKey() == 14) {
            this.field_0001.displayString = "NONE";
            CheatBreaker.getInstance().getModuleManager().field_0032.method_26048("");
            this.setting.method_08885(false);
            this.setting.method_08887(0);
            this.field_0000 = false;
         }

         if (!this.field_0001.displayString.equals("NONE")) {
            if (this.field_0001.field_0011) {
               this.field_0001.method_07927(false);
            }

            this.setting.method_08887(Mouse.getEventButton());
            this.setting.method_08885(true);
            this.field_0001.displayString = "Button " + (this.setting.method_08877() + 1);
            this.field_0000 = false;
            CheatBreaker.getInstance().getModuleManager().field_0032.method_26048("");
         }
      }

      if (this.field_0003) {
         this.field_0001.yOffset = this.yOffset;
         this.field_0001.setDimensions(this.x + this.width - 109, this.y + 2, 93, 15);
         this.field_0001.handleDrawElement(var1, var2, var3);
      }

      CheatBreaker.getInstance()
         .field_0068
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + 4,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
         );
      Gui.a(
         this.x + (this.field_0003 ? 65 : 148),
         this.y + 16,
         this.x + this.width - (this.field_0003 ? 120 : 16),
         this.y + 17,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
      );
      this.field_0002.method_06032(GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010);
      this.field_0002.method_06035(false);
      this.field_0002.drawElement(var1, var2, true);
      this.method_23220(this.setting, var1, var2);
      this.method_23215();
   }

   public ModulesGuiButtonElement method_29688() {
      return this.field_0001;
   }
}
