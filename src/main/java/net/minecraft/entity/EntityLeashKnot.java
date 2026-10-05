package net.minecraft.entity;

import net.minecraft.block.BlockFence;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityLeashKnot extends EntityHanging {
   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
   }

   @Override
   public boolean isInRangeToRenderDist(double var1) {
      return var1 < 1024.0;
   }

   public static EntityLeashKnot getKnotForPosition(World var0, BlockPos var1) {
      int var2 = var1.getX();
      int var3 = var1.getY();
      int var4 = var1.getZ();

      for (EntityLeashKnot var6 : var0.getEntitiesWithinAABB(
         EntityLeashKnot.class, new AxisAlignedBB(var2 - 1.0, var3 - 1.0, var4 - 1.0, var2 + 1.0, var3 + 1.0, var4 + 1.0)
      )) {
         if (var6.n().equals(var1)) {
            return var6;
         }
      }

      return null;
   }

   @Override
   public boolean a_(EntityPlayer var1) {
      ItemStack var2 = var1.getHeldItem();
      boolean var3 = false;
      if (var2 != null && var2.getItem() == Items.lead && !this.o.D) {
         double var4 = 7.0;

         for (EntityLiving var7 : this.o
            .getEntitiesWithinAABB(
               EntityLiving.class, new AxisAlignedBB(this.s - var4, this.t - var4, this.u - var4, this.s + var4, this.t + var4, this.u + var4)
            )) {
            if (var7.getLeashed() && var7.getLeashedToEntity() == var1) {
               var7.setLeashedToEntity(this, true);
               var3 = true;
            }
         }
      }

      if (!this.o.D && !var3) {
         this.setDead();
         if (var1.bA.isCreativeMode) {
            double var8 = 7.0;

            for (EntityLiving var10 : this.o
               .getEntitiesWithinAABB(
                  EntityLiving.class, new AxisAlignedBB(this.s - var8, this.t - var8, this.u - var8, this.s + var8, this.t + var8, this.u + var8)
               )) {
               if (var10.getLeashed() && var10.getLeashedToEntity() == this) {
                  var10.a(true, false);
               }
            }
         }
      }

      return true;
   }

   public EntityLeashKnot(World var1) {
      super(var1);
   }

   public EntityLeashKnot(World var1, BlockPos var2) {
      super(var1, var2);
      this.b(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5);
      float var3 = 0.125F;
      float var4 = 0.1875F;
      float var5 = 0.25F;
      this.setEntityBoundingBox(
         new AxisAlignedBB(this.s - 0.1875, this.t - 0.25 + 0.125, this.u - 0.1875, this.s + 0.1875, this.t + 0.25 + 0.125, this.u + 0.1875)
      );
   }

   @Override
   public void onBroken(Entity var1) {
   }

   @Override
   public boolean writeToNBTOptional(NBTTagCompound var1) {
      return false;
   }

   @Override
   public int getHeightPixels() {
      return 9;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
   }

   @Override
   public boolean j() {
      return this.o.getBlockState(this.a).getBlock() instanceof BlockFence;
   }

   @Override
   public void a(EnumFacing var1) {
   }

   @Override
   public int getWidthPixels() {
      return 9;
   }

   @Override
   public float getEyeHeight() {
      return -0.0625F;
   }

   public static EntityLeashKnot createKnot(World var0, BlockPos var1) {
      EntityLeashKnot var2 = new EntityLeashKnot(var0, var1);
      var2.n = true;
      var0.spawnEntityInWorld(var2);
      return var2;
   }

   @Override
   public void k_() {
      super.k_();
   }
}
