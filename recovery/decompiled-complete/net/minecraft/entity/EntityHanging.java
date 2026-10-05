package net.minecraft.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.world.World;
import org.apache.commons.lang3.Validate;

public abstract class EntityHanging extends Entity {
   public int tickCounter1;
   public EnumFacing b;
   public LazyLoadBase field_0000;
   public BlockPos a;

   public abstract int getWidthPixels();

   @Override
   public EnumFacing getHorizontalFacing() {
      return this.b;
   }

   public void updateBoundingBox() {
      if (this.b != null) {
         double var1 = this.a.getX() + 0.5;
         double var3 = this.a.getY() + 0.5;
         double var5 = this.a.getZ() + 0.5;
         double var7 = 0.46875;
         double var9 = this.func_174858_a(this.getWidthPixels());
         double var11 = this.func_174858_a(this.getHeightPixels());
         var1 -= this.b.getFrontOffsetX() * 0.46875;
         var5 -= this.b.getFrontOffsetZ() * 0.46875;
         var3 += var11;
         EnumFacing var13 = this.b.rotateYCCW();
         var1 += var9 * var13.getFrontOffsetX();
         var5 += var9 * var13.getFrontOffsetZ();
         this.s = var1;
         this.t = var3;
         this.u = var5;
         double var14 = this.getWidthPixels();
         double var16 = this.getHeightPixels();
         double var18 = this.getWidthPixels();
         if (this.b.getAxis() == EnumFacing$Axis.Z) {
            var18 = 1.0;
         } else {
            var14 = 1.0;
         }

         var14 /= 32.0;
         var16 /= 32.0;
         var18 /= 32.0;
         this.setEntityBoundingBox(new AxisAlignedBB(var1 - var14, var3 - var16, var5 - var18, var1 + var14, var3 + var16, var5 + var18));
      }
   }

   @Override
   public void addVelocity(double var1, double var3, double var5) {
      if (!this.o.D && !this.I && var1 * var1 + var3 * var3 + var5 * var5 > 0.0) {
         this.setDead();
         this.onBroken((Entity)null);
      }
   }

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   public EntityHanging(World var1) {
      super(var1);
      this.setSize(0.5F, 0.5F);
   }

   public BlockPos n() {
      return this.a;
   }

   public abstract void onBroken(Entity var1);

   @Override
   public boolean shouldSetPosAfterLoading() {
      return false;
   }

   @Override
   public void b(double var1, double var3, double var5) {
      this.s = var1;
      this.t = var3;
      this.u = var5;
      BlockPos var7 = this.a;
      this.a = new BlockPos(var1, var3, var5);
      if (!this.a.equals(var7)) {
         this.updateBoundingBox();
         this.ai = true;
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setByte("Facing", (byte)this.b.getHorizontalIndex());
      var1.setInteger("TileX", this.n().getX());
      var1.setInteger("TileY", this.n().getY());
      var1.setInteger("TileZ", this.n().getZ());
   }

   @Override
   public void d(double var1, double var3, double var5) {
      if (!this.o.D && !this.I && var1 * var1 + var3 * var3 + var5 * var5 > 0.0) {
         this.setDead();
         this.onBroken((Entity)null);
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      this.a = new BlockPos(var1.getInteger("TileX"), var1.getInteger("TileY"), var1.getInteger("TileZ"));
      EnumFacing var2;
      if (var1.hasKey("Direction", 99)) {
         var2 = EnumFacing.getHorizontal(var1.getByte("Direction"));
         this.a = this.a.a(var2);
      } else if (var1.hasKey("Facing", 99)) {
         var2 = EnumFacing.getHorizontal(var1.getByte("Facing"));
      } else {
         var2 = EnumFacing.getHorizontal(var1.getByte("Dir"));
      }

      this.a(var2);
   }

   public boolean j() {
      if (!this.o.a(this, this.getEntityBoundingBox()).isEmpty()) {
         return false;
      } else {
         int var1 = Math.max(1, this.getWidthPixels() / 16);
         int var2 = Math.max(1, this.getHeightPixels() / 16);
         BlockPos var3 = this.a.a(this.b.getOpposite());
         EnumFacing var4 = this.b.rotateYCCW();

         for (int var5 = 0; var5 < var1; var5++) {
            for (int var6 = 0; var6 < var2; var6++) {
               BlockPos var7 = var3.a(var4, var5).up(var6);
               Block var8 = this.o.getBlockState(var7).getBlock();
               if (!var8.getMaterial().isSolid() && !BlockRedstoneDiode.isRedstoneRepeaterBlockID(var8)) {
                  return false;
               }
            }
         }

         for (Entity var10 : this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox())) {
            if (var10 instanceof EntityHanging) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         if (!this.I && !this.o.D) {
            this.setDead();
            this.setBeenAttacked();
            this.onBroken(var1.getEntity());
         }

         return true;
      }
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.tickCounter1++ == 100 && !this.o.D) {
         this.tickCounter1 = 0;
         if (!this.I && !this.j()) {
            this.setDead();
            this.onBroken((Entity)null);
         }
      }
   }

   public void a(EnumFacing var1) {
      Validate.notNull(var1);
      Validate.isTrue(var1.getAxis().isHorizontal());
      this.b = var1;
      this.A = this.y = this.b.getHorizontalIndex() * 90;
      this.updateBoundingBox();
   }

   @Override
   public void k_() {
   }

   @Override
   public boolean hitByEntity(Entity var1) {
      return var1 instanceof EntityPlayer ? this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)var1), 0.0F) : false;
   }

   public double func_174858_a(int var1) {
      return var1 % 32 == 0 ? 0.5 : 0.0;
   }

   public EntityHanging(World var1, BlockPos var2) {
      this(var1);
      this.a = var2;
   }

   public abstract int getHeightPixels();
}
