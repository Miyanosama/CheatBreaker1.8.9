package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.PingResponseHandler;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import org.apache.log4j.pattern.LineSeparatorPatternConverter;

public class GuiMerchant$MerchantButton extends GuiButton {
   public LineSeparatorPatternConverter field_0001;
   public WorldGenDeadBush field_0003;
   public PingResponseHandler field_0000;
   public boolean field_146157_o;

   @Override
   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         var1.getTextureManager().bindTexture(GuiMerchant.access$000());
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         boolean var4 = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
         int var5 = 0;
         int var6 = 176;
         if (!this.l) {
            var6 += this.f * 2;
         } else if (var4) {
            var6 += this.f;
         }

         if (!this.field_146157_o) {
            var5 += this.height;
         }

         this.drawTexturedModalRect(this.h, this.i, var6, var5, this.f, this.height);
      }
   }

   public GuiMerchant$MerchantButton(int var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3, 12, 19, "");
      this.field_146157_o = var4;
   }
}
