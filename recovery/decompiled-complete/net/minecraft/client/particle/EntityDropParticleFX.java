package net.minecraft.client.particle;

import net.minecraft.block.BlockHay;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.server.CommandPardonPlayer;
import net.minecraft.item.Item$6;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDropParticleFX extends EntityFX {
   public int bobTimer;
   public Material materialType;
   public CommandPardonPlayer field_0001;
   public BlockHay field_0003;
   public Item$6 field_0000;

   public EntityDropParticleFX(World var1, double var2, double var4, double var6, Material var8) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v = this.w = this.x = 0.0;
      if (var8 == Material.water) {
         this.ar = 0.0F;
         this.as = 0.0F;
         this.at = 1.0F;
      } else {
         this.ar = 1.0F;
         this.as = 0.0F;
         this.at = 0.0F;
      }

      this.k(113);
      this.setSize(0.01F, 0.01F);
      this.i = 0.06F;
      this.materialType = var8;
      this.bobTimer = 40;
      this.g = (int)(64.0 / (Math.random() * 0.8 + 0.2));
      this.v = this.w = this.x = 0.0;
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.materialType == Material.water) {
         this.ar = 0.2F;
         this.as = 0.3F;
         this.at = 1.0F;
      } else {
         this.ar = 1.0F;
         this.as = 16.0F / (40 - this.bobTimer + 16);
         this.at = 4.0F / (40 - this.bobTimer + 8);
      }

      this.w = this.w - this.i;
      if (this.bobTimer-- > 0) {
         this.v *= 0.02;
         this.w *= 0.02;
         this.x *= 0.02;
         this.k(113);
      } else {
         this.k(112);
      }

      this.d(this.v, this.w, this.x);
      this.v *= 0.98F;
      this.w *= 0.98F;
      this.x *= 0.98F;
      if (this.g-- <= 0) {
         this.setDead();
      }

      if (this.C) {
         if (this.materialType == Material.water) {
            this.setDead();
            this.o.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.s, this.t, this.u, 0.0, 0.0, 0.0);
         } else {
            this.k(114);
         }

         this.v *= 0.7F;
         this.x *= 0.7F;
      }

      BlockPos var1 = new BlockPos(this);
      IBlockState var2 = this.o.getBlockState(var1);
      Material var3 = var2.getBlock().getMaterial();
      if (var3.isLiquid() || var3.isSolid()) {
         double var4 = 0.0;
         if (var2.getBlock() instanceof BlockLiquid) {
            var4 = BlockLiquid.getLiquidHeightPercent(var2.getValue(BlockLiquid.b));
         }

         double var6 = MathHelper.floor_double(this.t) + 1 - var4;
         if (this.t < var6) {
            this.setDead();
         }
      }
   }

   @Override
   public float a_(float var1) {
      return this.materialType == Material.water ? super.a_(var1) : 1.0F;
   }

   @Override
   public int b_(float var1) {
      return this.materialType == Material.water ? super.b_(var1) : 257;
   }
}
