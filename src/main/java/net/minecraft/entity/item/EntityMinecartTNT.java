package net.minecraft.entity.item;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityMinecartTNT extends EntityMinecart {
   public int minecartTNTFuse = -1;

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.hasKey("TNTFuse", 99)) {
         this.minecartTNTFuse = var1.getInteger("TNTFuse");
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("TNTFuse", this.minecartTNTFuse);
   }

   @Override
   public boolean verifyExplosion(Explosion var1, World var2, BlockPos var3, IBlockState var4, float var5) {
      return this.isIgnited() && (BlockRailBase.isRailBlock(var4) || BlockRailBase.isRailBlock(var2, var3.up()))
         ? false
         : super.verifyExplosion(var1, var2, var3, var4, var5);
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 10) {
         this.ignite();
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return Blocks.tnt.getDefaultState();
   }

   public void ignite() {
      this.minecartTNTFuse = 80;
      if (!this.o.D) {
         this.o.setEntityState(this, (byte)10);
         if (!this.R()) {
            this.o.a(this, "game.tnt.primed", 1.0F, 1.0F);
         }
      }
   }

   @Override
   public EntityMinecart.EnumMinecartType getMinecartType() {
      return EntityMinecart.EnumMinecartType.TNT;
   }

   @Override
   public float getExplosionResistance(Explosion var1, World var2, BlockPos var3, IBlockState var4) {
      return this.isIgnited() && (BlockRailBase.isRailBlock(var4) || BlockRailBase.isRailBlock(var2, var3.up()))
         ? 0.0F
         : super.getExplosionResistance(var1, var2, var3, var4);
   }

   public void explodeCart(double var1) {
      if (!this.o.D) {
         double var3 = Math.sqrt(var1);
         if (var3 > 5.0) {
            var3 = 5.0;
         }

         this.o.createExplosion(this, this.s, this.t, this.u, (float)(4.0 + this.V.nextDouble() * 1.5 * var3), true);
         this.setDead();
      }
   }

   public EntityMinecartTNT(World var1) {
      super(var1);
   }

   @Override
   public void killMinecart(DamageSource var1) {
      super.killMinecart(var1);
      double var2 = this.v * this.v + this.x * this.x;
      if (!var1.isExplosion() && this.o.Q().getBoolean("doEntityDrops")) {
         this.a(new ItemStack(Blocks.tnt, 1), 0.0F);
      }

      if (var1.isFireDamage() || var1.isExplosion() || var2 >= 0.01F) {
         this.explodeCart(var2);
      }
   }

   @Override
   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {
      if (var4 && this.minecartTNTFuse < 0) {
         this.ignite();
      }
   }

   public boolean isIgnited() {
      return this.minecartTNTFuse > -1;
   }

   public int getFuseTicks() {
      return this.minecartTNTFuse;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.minecartTNTFuse > 0) {
         this.minecartTNTFuse--;
         this.o.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.s, this.t + 0.5, this.u, 0.0, 0.0, 0.0);
      } else if (this.minecartTNTFuse == 0) {
         this.explodeCart(this.v * this.v + this.x * this.x);
      }

      if (this.D) {
         double var1 = this.v * this.v + this.x * this.x;
         if (var1 >= 0.01F) {
            this.explodeCart(var1);
         }
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      Entity var3 = var1.getSourceOfDamage();
      if (var3 instanceof EntityArrow) {
         EntityArrow var4 = (EntityArrow)var3;
         if (var4.isBurning()) {
            this.explodeCart(var4.v * var4.v + var4.w * var4.w + var4.x * var4.x);
         }
      }

      return super.attackEntityFrom(var1, var2);
   }

   public EntityMinecartTNT(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   @Override
   public void fall(float var1, float var2) {
      if (var1 >= 3.0F) {
         float var3 = var1 / 10.0F;
         this.explodeCart(var3 * var3);
      }

      super.fall(var1, var2);
   }
}
