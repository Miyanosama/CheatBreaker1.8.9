package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFirework_OverlayFX extends EntityFX {
   public EntityFirework_OverlayFX(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
      this.g = 4;
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = 0.25F;
      float var10 = 0.5F;
      float var11 = 0.125F;
      float var12 = 0.375F;
      float var13 = 7.1F * MathHelper.sin((this.f + var3 - 1.0F) * 0.25F * (float) Math.PI);
      this.au = 0.6F - (this.f + var3 - 1.0F) * 0.25F * 0.5F;
      float var14 = (float)(this.p + (this.s - this.p) * var3 - aw);
      float var15 = (float)(this.q + (this.t - this.q) * var3 - ax);
      float var16 = (float)(this.r + (this.u - this.r) * var3 - ay);
      int var17 = this.b_(var3);
      int var18 = var17 >> 16 & 65535;
      int var19 = var17 & 65535;
      var1.pos(var14 - var4 * var13 - var7 * var13, var15 - var5 * var13, var16 - var6 * var13 - var8 * var13)
         .tex(0.5, 0.375)
         .color(this.ar, this.as, this.at, this.au)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 - var4 * var13 + var7 * var13, var15 + var5 * var13, var16 - var6 * var13 + var8 * var13)
         .tex(0.5, 0.125)
         .color(this.ar, this.as, this.at, this.au)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 + var4 * var13 + var7 * var13, var15 + var5 * var13, var16 + var6 * var13 + var8 * var13)
         .tex(0.25, 0.125)
         .color(this.ar, this.as, this.at, this.au)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 + var4 * var13 - var7 * var13, var15 - var5 * var13, var16 + var6 * var13 - var8 * var13)
         .tex(0.25, 0.375)
         .color(this.ar, this.as, this.at, this.au)
         .lightmap(var18, var19)
         .endVertex();
   }
}
