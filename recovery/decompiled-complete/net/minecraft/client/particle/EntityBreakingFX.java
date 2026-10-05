package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.world.World;

public class EntityBreakingFX extends EntityFX {
   public UserListBansEntry field_0000;

   public EntityBreakingFX(World var1, double var2, double var4, double var6, Item var8, int var9) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.a(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(var8, var9));
      this.ar = this.as = this.at = 1.0F;
      this.i = Blocks.snow.blockParticleGravity;
      this.h /= 2.0F;
   }

   public EntityBreakingFX(World var1, double var2, double var4, double var6, Item var8) {
      this(var1, var2, var4, var6, var8, 0);
   }

   public EntityBreakingFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, Item var14, int var15) {
      this(var1, var2, var4, var6, var14, var15);
      this.v *= 0.1F;
      this.w *= 0.1F;
      this.x *= 0.1F;
      this.v += var8;
      this.w += var10;
      this.x += var12;
   }

   @Override
   public int getFXLayer() {
      return 1;
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (this.b + this.d / 4.0F) / 16.0F;
      float var10 = var9 + 0.015609375F;
      float var11 = (this.c + this.e / 4.0F) / 16.0F;
      float var12 = var11 + 0.015609375F;
      float var13 = 0.1F * this.h;
      if (this.av != null) {
         var9 = this.av.getInterpolatedU(this.d / 4.0F * 16.0F);
         var10 = this.av.getInterpolatedU((this.d + 1.0F) / 4.0F * 16.0F);
         var11 = this.av.getInterpolatedV(this.e / 4.0F * 16.0F);
         var12 = this.av.getInterpolatedV((this.e + 1.0F) / 4.0F * 16.0F);
      }

      float var14 = (float)(this.p + (this.s - this.p) * var3 - aw);
      float var15 = (float)(this.q + (this.t - this.q) * var3 - ax);
      float var16 = (float)(this.r + (this.u - this.r) * var3 - ay);
      int var17 = this.b_(var3);
      int var18 = var17 >> 16 & 65535;
      int var19 = var17 & 65535;
      var1.pos(var14 - var4 * var13 - var7 * var13, var15 - var5 * var13, var16 - var6 * var13 - var8 * var13)
         .tex(var9, var12)
         .color(this.ar, this.as, this.at, 1.0F)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 - var4 * var13 + var7 * var13, var15 + var5 * var13, var16 - var6 * var13 + var8 * var13)
         .tex(var9, var11)
         .color(this.ar, this.as, this.at, 1.0F)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 + var4 * var13 + var7 * var13, var15 + var5 * var13, var16 + var6 * var13 + var8 * var13)
         .tex(var10, var11)
         .color(this.ar, this.as, this.at, 1.0F)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 + var4 * var13 - var7 * var13, var15 - var5 * var13, var16 + var6 * var13 - var8 * var13)
         .tex(var10, var12)
         .color(this.ar, this.as, this.at, 1.0F)
         .lightmap(var18, var19)
         .endVertex();
   }
}
