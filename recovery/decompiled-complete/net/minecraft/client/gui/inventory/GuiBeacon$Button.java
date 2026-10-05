package net.minecraft.client.gui.inventory;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import recovered.unidentified.UnidentifiedClass0334;
import recovered.unidentified.UnidentifiedClass4575;

public class GuiBeacon$Button extends GuiButton {
   public boolean field_146142_r;
   public UnidentifiedClass4575 field_0006;
   public ResourceLocation field_146145_o;
   public int field_146143_q;
   public BlockEndPortalFrame field_0000;
   public int field_146144_p;
   public HttpPostRequestDecoder$ErrorDataDecoderException field_0007;
   public UnidentifiedClass0334 field_0004;

   public GuiBeacon$Button(int var1, int var2, int var3, ResourceLocation var4, int var5, int var6) {
      super(var1, var2, var3, 22, 22, "");
      this.field_146145_o = var4;
      this.field_146144_p = var5;
      this.field_146143_q = var6;
   }

   @Override
   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         var1.getTextureManager().bindTexture(GuiBeacon.access$000());
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
         short var4 = 219;
         int var5 = 0;
         if (!this.l) {
            var5 += this.f * 2;
         } else if (this.field_146142_r) {
            var5 += this.f * 1;
         } else if (this.hovered) {
            var5 += this.f * 3;
         }

         this.drawTexturedModalRect(this.h, this.i, var5, var4, this.f, this.height);
         if (!GuiBeacon.access$000().equals(this.field_146145_o)) {
            var1.getTextureManager().bindTexture(this.field_146145_o);
         }

         this.drawTexturedModalRect(this.h + 2, this.i + 2, this.field_146144_p, this.field_146143_q, 18, 18);
      }
   }

   public void func_146140_b(boolean var1) {
      this.field_146142_r = var1;
   }

   public boolean func_146141_c() {
      return this.field_146142_r;
   }
}
