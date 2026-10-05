package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.ui.util.GuiThemeColors;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CyclingChoiceElement extends AbstractModulesGuiElement {
   public String recoveredField1480;
   public ResourceLocation recoveredField1481 = new ResourceLocation("client/icons/left.png");
   public float recoveredField1482;
   public ResourceLocation recoveredField1483 = new ResourceLocation("client/icons/right.png");
   public int recoveredField1484 = 0;

   public CyclingChoiceElement(Setting var1, float var2) {
      super(var2);
      this.recoveredField1482 = 0.0F;
      this.setting = var1;
      this.height = 12;
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var4 = var2 > (this.y + this.yOffset) * this.scale && var2 < (this.y + 10 + this.yOffset) * this.scale;
      boolean var5 = var1 > (this.x + this.width - 48) * this.scale && var1 < (this.x + this.width - 10) * this.scale && var4;
      boolean var6 = var1 > (this.x + this.width - 92) * this.scale && var1 < (this.x + this.width - 48) * this.scale && var4;
      CheatBreaker.getInstance()
         .recoveredField1589
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + 2,
            !var6 && !var5
               ? (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654)
               : (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1665 : GuiThemeColors.recoveredField1663)
         );
      boolean var7 = this.setting.method_08911().toLowerCase().endsWith("color");
      if (!var7) {
         if (this.recoveredField1484 == 0) {
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawCenteredString(
                  (String)this.setting.getValue(),
                  this.x + this.width - 48,
                  this.y + 2,
                  GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
               );
         } else {
            boolean var8 = this.recoveredField1484 == 1;
            float var10002 = this.x + this.width - 48 - (var8 ? -this.recoveredField1482 : this.recoveredField1482);
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawCenteredString(
                  this.recoveredField1480,
                  var10002,
                  this.y + 2,
                  GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
               );
            if (var8) {
               CheatBreaker.getInstance()
                  .recoveredField1589
                  .drawCenteredString(
                     (String)this.setting.getValue(),
                     this.x + this.width - 98 + this.recoveredField1482,
                     this.y + 2,
                     GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
                  );
            } else {
               CheatBreaker.getInstance()
                  .recoveredField1589
                  .drawCenteredString(
                     (String)this.setting.getValue(),
                     this.x + this.width + 2 - this.recoveredField1482,
                     this.y + 2,
                     GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
                  );
            }

            if (this.recoveredField1482 >= 50.0F) {
               this.recoveredField1484 = 0;
               this.recoveredField1482 = 0.0F;
            } else {
               float var9 = CBModulesGui.getSmoothFloat(50.0F + this.recoveredField1482 * 15.0F);
               this.recoveredField1482 = Math.min(this.recoveredField1482 + var9, 50.0F);
            }

            Gui.a(
               this.x + this.width - 130,
               this.y + 2,
               this.x + this.width - 72,
               this.y + 12,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1664 : GuiThemeColors.recoveredField1678
            );
            Gui.a(
               this.x + this.width - 22,
               this.y + 2,
               this.x + this.width + 4,
               this.y + 12,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1664 : GuiThemeColors.recoveredField1678
            );
         }
      } else if (this.recoveredField1484 == 0) {
         float var11 = CheatBreaker.getInstance().recoveredField1589.getStringWidth((String)this.setting.getValue());
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawString((String)this.setting.getValue(), this.x + this.width - 47.5F - var11 / 2.0F, this.y + 2.5F, -16777216);
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawString("§" + this.setting.getValue() + this.setting.getValue(), this.x + this.width - 48 - var11 / 2.0F, this.y + 2, -16777216);
      } else {
         boolean var12 = this.recoveredField1484 == 1;
         float var15 = this.x + this.width - 48 - (var12 ? -this.recoveredField1482 : this.recoveredField1482);
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawCenteredString(
               this.recoveredField1480,
               var15,
               this.y + 2,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
            );
         float var14 = CheatBreaker.getInstance().recoveredField1589.getStringWidth((String)this.setting.getValue());
         if (var12) {
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawString((String)this.setting.getValue(), this.x + this.width - 97.5F - var14 / 2.0F + this.recoveredField1482, this.y + 2.5F, -16777216);
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawString(
                  "§" + this.setting.getValue() + this.setting.getValue(),
                  this.x + this.width - 98 - var14 / 2.0F + this.recoveredField1482,
                  this.y + 2,
                  -16777216
               );
         } else {
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawString((String)this.setting.getValue(), this.x + this.width - 1.5F - var14 / 2.0F - this.recoveredField1482, this.y + 2.5F, -16777216);
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawString(
                  "§" + this.setting.getValue() + this.setting.getValue(),
                  this.x + this.width - 2 - var14 / 2.0F - this.recoveredField1482,
                  this.y + 2,
                  -16777216
               );
         }

         if (this.recoveredField1482 >= 50.0F) {
            this.recoveredField1484 = 0;
            this.recoveredField1482 = 0.0F;
         } else {
            float var10 = CBModulesGui.getSmoothFloat(50.0F + this.recoveredField1482 * 15.0F);
            this.recoveredField1482 = Math.min(this.recoveredField1482 + var10, 50.0F);
         }

         Gui.a(
            this.x + this.width - 130,
            this.y + 2,
            this.x + this.width - 72,
            this.y + 12,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1664 : GuiThemeColors.recoveredField1678
         );
         Gui.a(
            this.x + this.width - 22,
            this.y + 2,
            this.x + this.width + 4,
            this.y + 12,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1664 : GuiThemeColors.recoveredField1678
         );
      }

      float var13 = GlobalSettings.recoveredField529.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var13, var13, var13, var6 ? 0.8F : 0.45F);
      RenderUtil.drawIcon(this.recoveredField1481, 4.0F, this.x + this.width - 82, this.y + 3);
      GL11.glColor4f(var13, var13, var13, var5 ? 0.8F : 0.45F);
      RenderUtil.drawIcon(this.recoveredField1483, 4.0F, this.x + this.width - 22, this.y + 3);
      this.method_23220(this.setting, var1, var2);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > (this.x + this.width - 48) * this.scale
         && var1 < (this.x + this.width - 10) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10 + this.yOffset) * this.scale;
      boolean var5 = var1 > (this.x + this.width - 92) * this.scale
         && var1 < (this.x + this.width - 48) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10 + this.yOffset) * this.scale;
      if ((var5 || var4) && this.recoveredField1484 == 0) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));

         for (int var6 = 0; var6 < this.setting.getAcceptedValues().length; var6++) {
            if (this.setting.getAcceptedValues()[var6].toLowerCase().equalsIgnoreCase((String)this.setting.getValue())) {
               this.recoveredField1480 = (String)this.setting.getValue();
               if (var4) {
                  if (var6 + 1 >= this.setting.getAcceptedValues().length) {
                     this.recoveredField1484 = 2;
                     this.setting.setValue(this.setting.getAcceptedValues()[0]);
                  } else {
                     this.recoveredField1484 = 2;
                     this.setting.setValue(this.setting.getAcceptedValues()[var6 + 1]);
                  }
                  break;
               }

               if (var5) {
                  if (var6 - 1 < 0) {
                     this.recoveredField1484 = 1;
                     this.setting.setValue(this.setting.getAcceptedValues()[this.setting.getAcceptedValues().length - 1]);
                  } else {
                     this.recoveredField1484 = 1;
                     this.setting.setValue(this.setting.getAcceptedValues()[var6 - 1]);
                  }
                  break;
               }
            }
         }
      }
   }
}
