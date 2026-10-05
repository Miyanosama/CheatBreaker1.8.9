package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay$PlayerComparator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMinecart;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenSand;

public class EntityDiggingFX extends EntityFX {
   public WorldGenSand field_0000;
   public BlockPos sourcePos;
   public ItemMinecart field_0004;
   public GuiPlayerTabOverlay$PlayerComparator field_0003;
   public IBlockState sourceState;

   public EntityDiggingFX func_174845_l() {
      this.sourcePos = new BlockPos(this.s, this.t, this.u);
      Block var1 = this.sourceState.getBlock();
      if (var1 == Blocks.grass) {
         return this;
      } else {
         int var2 = var1.getRenderColor(this.sourceState);
         this.ar *= (var2 >> 16 & 0xFF) / 255.0F;
         this.as *= (var2 >> 8 & 0xFF) / 255.0F;
         this.at *= (var2 & 0xFF) / 255.0F;
         return this;
      }
   }

   public EntityDiggingFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, IBlockState var14) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.sourceState = var14;
      this.a(Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(var14));
      this.i = var14.getBlock().blockParticleGravity;
      this.ar = this.as = this.at = 0.6F;
      this.h /= 2.0F;
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

   public EntityDiggingFX setBlockPos(BlockPos var1) {
      this.sourcePos = var1;
      if (this.sourceState.getBlock() == Blocks.grass) {
         return this;
      } else {
         int var2 = this.sourceState.getBlock().colorMultiplier(this.o, var1);
         this.ar *= (var2 >> 16 & 0xFF) / 255.0F;
         this.as *= (var2 >> 8 & 0xFF) / 255.0F;
         this.at *= (var2 & 0xFF) / 255.0F;
         return this;
      }
   }

   @Override
   public int getFXLayer() {
      return 1;
   }

   @Override
   public int b_(float var1) {
      int var2 = super.b_(var1);
      int var3 = 0;
      if (this.o.e(this.sourcePos)) {
         var3 = this.o.getCombinedLight(this.sourcePos, 0);
      }

      return var2 == 0 ? var3 : var2;
   }
}
