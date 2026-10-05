package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CosmeticSelectionElement extends AbstractModulesGuiElement {
   public ColorFade recoveredField2290 = new ColorFade(0, 788529152);
   public ClientResourceManager recoveredField2291;

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var4 = var1 > this.x && var1 < this.x + this.width && var2 > this.y && var2 < this.y + this.height;
      Gui.a(this.x, this.y, this.x + this.width, this.y + this.height, this.recoveredField2290.method_25066(var4).getRGB());
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.recoveredField2291.method_20848().method_00485().equals("cape")) {
         Minecraft.getMinecraft().renderEngine.bindTexture(this.recoveredField2291.method_20859());
         GL11.glPushMatrix();
         GL11.glTranslatef(this.x + 20, this.y + 7, 0.0F);
         GL11.glScalef(0.25F, 0.13F, 0.25F);
         RenderUtil.method_22065(0.0F, 0.0F, 2.0F, 7.0F, 44, 120);
         GL11.glPopMatrix();
      } else {
         try {
            RenderUtil.drawIcon(this.recoveredField2291.method_20850(), 8.0F, this.x + 20, this.y + 7);
         } catch (Exception var6) {
         }
      }

      CheatBreaker.getInstance()
         .recoveredField1548
         .drawString(this.recoveredField2291.method_20858().replace("_", " "), this.x + 42, this.y + this.height / 2 - 5, -1342177281);
      if (this.recoveredField2291.method_20849()) {
         GL11.glColor4f(0.0F, 0.8F, 0.0F, 0.45F);
      } else {
         GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.25F);
      }

      RenderUtil.method_22052(this.x + 8, this.y + this.height / 2.0F, 3.0);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > this.x && var1 < this.x + this.width && var2 > this.y && var2 < this.y + this.height;
      if (var4 && var3 == 0) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         CheatBreaker.getInstance().method_19791().getLocalCosmetics().toggle(this.recoveredField2291);
      }
   }

   public CosmeticSelectionElement(ClientResourceManager var1, float var2) {
      super(var2);
      this.height = 30;
      this.recoveredField2291 = var1;
   }
}
