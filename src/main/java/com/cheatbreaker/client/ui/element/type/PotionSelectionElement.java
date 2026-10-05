package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.ui.util.GuiThemeColors;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PotionSelectionElement extends AbstractModulesGuiElement {
   public List<Potion> recoveredField1324 = new ArrayList<>();

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      try {
         int var4 = 0;
         int var5 = 0;

         for (Integer var9 : (Integer[])this.setting.method_08883()) {
            if (var9 != null) {
               if (var4 >= 15) {
                  var4 = 0;
                  var5++;
               }

               int var10 = this.x + 12 + var4 * 20;
               int var11 = this.y + 14 + var5 * 20;
               boolean var12 = var1 > (var10 - 2) * this.scale
                  && var1 < (var10 + 18) * this.scale
                  && var2 > (var11 - 2 + this.yOffset) * this.scale
                  && var2 < (var11 + 18 + this.yOffset) * this.scale;
               if (var12 && var3 == 0) {
                  int var13 = var9;
                  if (((List<Integer>)this.setting.getValue()).contains(var13)) {
                     ((List<Integer>)this.setting.getValue()).removeIf(var1x -> var1x == var13);
                  } else {
                     this.setting.method_08869().add(var13);
                  }

                  this.setting.setValue(this.setting.method_08869());
                  Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               }

               var4++;
            }
         }
      } catch (Exception var14) {
         var14.printStackTrace();
      }
   }

   public void method_08063() {
      this.recoveredField1324.add(Potion.moveSpeed);
      this.recoveredField1324.add(Potion.moveSlowdown);
      this.recoveredField1324.add(Potion.digSpeed);
      this.recoveredField1324.add(Potion.digSlowdown);
      this.recoveredField1324.add(Potion.damageBoost);
      this.recoveredField1324.add(Potion.jump);
      this.recoveredField1324.add(Potion.confusion);
      this.recoveredField1324.add(Potion.regeneration);
      this.recoveredField1324.add(Potion.resistance);
      this.recoveredField1324.add(Potion.fireResistance);
      this.recoveredField1324.add(Potion.waterBreathing);
      this.recoveredField1324.add(Potion.invisibility);
      this.recoveredField1324.add(Potion.blindness);
      this.recoveredField1324.add(Potion.nightVision);
      this.recoveredField1324.add(Potion.hunger);
      this.recoveredField1324.add(Potion.weakness);
      this.recoveredField1324.add(Potion.poison);
      this.recoveredField1324.add(Potion.wither);
      this.recoveredField1324.add(Potion.absorption);
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      CheatBreaker.getInstance()
         .recoveredField1589
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + 2,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
         );
      Minecraft var4 = Minecraft.getMinecraft();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(32826);
      RenderHelper.enableStandardItemLighting();
      RenderHelper.enableGUIStandardItemLighting();
      int var5 = 0;
      int var6 = 0;

      for (Potion var8 : this.recoveredField1324) {
         if (var8 != null) {
            if (var5 >= 15) {
               var5 = 0;
               var6++;
            }

            int var9 = this.x + 12 + var5 * 20;
            int var10 = this.y + 14 + var6 * 20;
            boolean var11 = var1 > (var9 - 2) * this.scale
               && var1 < (var9 + 18) * this.scale
               && var2 > (var10 - 2 + this.yOffset) * this.scale
               && var2 < (var10 + 18 + this.yOffset) * this.scale;
            if (this.setting.method_08869().contains(var8.id)) {
               Gui.a(var9 - 2, var10 - 2, var9 + 18, var10 + 18, 2130771712);
            } else if (var11) {
               Gui.a(var9 - 2, var10 - 2, var9 + 18, var10 + 18, 1325400319);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
            int var12 = var8.getStatusIconIndex();
            RenderUtil.method_22065(var9 - 1, var10 - 1.0F, var12 % 8 * 18, 198 + var12 / 8 * 18, 18, 18);
            var5++;
         }
      }

      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(32826);
      GL11.glDisable(3042);
   }

   public PotionSelectionElement(Setting var1, float var2) {
      super(var2);
      this.height = 50;
      this.setting = var1;
      this.method_08063();
   }
}
