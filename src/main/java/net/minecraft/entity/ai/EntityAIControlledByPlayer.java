package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.pathfinder.WalkNodeProcessor;

public class EntityAIControlledByPlayer extends EntityAIBase {
   public boolean speedBoosted;
   public float currentSpeed;
   public float maxSpeed;
   public int speedBoostTime;
   public int maxSpeedBoostTime;
   public EntityLiving thisEntity;

   @Override
   public boolean shouldExecute() {
      return this.thisEntity.isEntityAlive()
         && this.thisEntity.l != null
         && this.thisEntity.l instanceof EntityPlayer
         && (this.speedBoosted || this.thisEntity.canBeSteered());
   }

   public void boostSpeed() {
      this.speedBoosted = true;
      this.speedBoostTime = 0;
      this.maxSpeedBoostTime = this.thisEntity.getRNG().nextInt(841) + 140;
   }

   @Override
   public void startExecuting() {
      this.currentSpeed = 0.0F;
   }

   @Override
   public void updateTask() {
      EntityPlayer var1 = (EntityPlayer)this.thisEntity.l;
      EntityCreature var2 = (EntityCreature)this.thisEntity;
      float var3 = MathHelper.wrapAngleTo180_float(var1.y - this.thisEntity.y) * 0.5F;
      if (var3 > 5.0F) {
         var3 = 5.0F;
      }

      if (var3 < -5.0F) {
         var3 = -5.0F;
      }

      this.thisEntity.y = MathHelper.wrapAngleTo180_float(this.thisEntity.y + var3);
      if (this.currentSpeed < this.maxSpeed) {
         this.currentSpeed = this.currentSpeed + (this.maxSpeed - this.currentSpeed) * 0.01F;
      }

      if (this.currentSpeed > this.maxSpeed) {
         this.currentSpeed = this.maxSpeed;
      }

      int var4 = MathHelper.floor_double(this.thisEntity.s);
      int var5 = MathHelper.floor_double(this.thisEntity.t);
      int var6 = MathHelper.floor_double(this.thisEntity.u);
      float var7 = this.currentSpeed;
      if (this.speedBoosted) {
         if (this.speedBoostTime++ > this.maxSpeedBoostTime) {
            this.speedBoosted = false;
         }

         var7 += var7 * 1.15F * MathHelper.sin((float)this.speedBoostTime / this.maxSpeedBoostTime * (float) Math.PI);
      }

      float var8 = 0.91F;
      if (this.thisEntity.C) {
         var8 = this.thisEntity
               .o
               .getBlockState(new BlockPos(MathHelper.floor_float(var4), MathHelper.floor_float(var5) - 1, MathHelper.floor_float(var6)))
               .getBlock()
               .L
            * 0.91F;
      }

      float var9 = 0.16277136F / (var8 * var8 * var8);
      float var10 = MathHelper.sin(var2.y * (float) Math.PI / 180.0F);
      float var11 = MathHelper.cos(var2.y * (float) Math.PI / 180.0F);
      float var12 = var2.bI() * var9;
      float var13 = Math.max(var7, 1.0F);
      var13 = var12 / var13;
      float var14 = var7 * var13;
      float var15 = -(var14 * var10);
      float var16 = var14 * var11;
      if (MathHelper.abs(var15) > MathHelper.abs(var16)) {
         if (var15 < 0.0F) {
            var15 -= this.thisEntity.J / 2.0F;
         }

         if (var15 > 0.0F) {
            var15 += this.thisEntity.J / 2.0F;
         }

         var16 = 0.0F;
      } else {
         var15 = 0.0F;
         if (var16 < 0.0F) {
            var16 -= this.thisEntity.J / 2.0F;
         }

         if (var16 > 0.0F) {
            var16 += this.thisEntity.J / 2.0F;
         }
      }

      int var17 = MathHelper.floor_double(this.thisEntity.s + var15);
      int var18 = MathHelper.floor_double(this.thisEntity.u + var16);
      int var19 = MathHelper.floor_float(this.thisEntity.J + 1.0F);
      int var20 = MathHelper.floor_float(this.thisEntity.K + var1.K + 1.0F);
      int var21 = MathHelper.floor_float(this.thisEntity.J + 1.0F);
      if (var4 != var17 || var6 != var18) {
         Block var22 = this.thisEntity.o.getBlockState(new BlockPos(var4, var5, var6)).getBlock();
         boolean var23 = !this.isStairOrSlab(var22)
            && (var22.getMaterial() != Material.air || !this.isStairOrSlab(this.thisEntity.o.getBlockState(new BlockPos(var4, var5 - 1, var6)).getBlock()));
         if (var23
            && 0 == WalkNodeProcessor.func_176170_a(this.thisEntity.o, this.thisEntity, var17, var5, var18, var19, var20, var21, false, false, true)
            && 1 == WalkNodeProcessor.func_176170_a(this.thisEntity.o, this.thisEntity, var4, var5 + 1, var6, var19, var20, var21, false, false, true)
            && 1 == WalkNodeProcessor.func_176170_a(this.thisEntity.o, this.thisEntity, var17, var5 + 1, var18, var19, var20, var21, false, false, true)) {
            var2.r().setJumping();
         }
      }

      if (!var1.bA.isCreativeMode && this.currentSpeed >= this.maxSpeed * 0.5F && this.thisEntity.getRNG().nextFloat() < 0.006F && !this.speedBoosted) {
         ItemStack var25 = var1.getHeldItem();
         if (var25 != null && var25.getItem() == Items.carrot_on_a_stick) {
            var25.damageItem(1, var1);
            if (var25.stackSize == 0) {
               ItemStack var26 = new ItemStack(Items.fishing_rod);
               var26.setTagCompound(var25.getTagCompound());
               var1.bi.mainInventory[var1.bi.currentItem] = var26;
            }
         }
      }

      this.thisEntity.moveEntityWithHeading(0.0F, var7);
   }

   @Override
   public void resetTask() {
      this.speedBoosted = false;
      this.currentSpeed = 0.0F;
   }

   public boolean isControlledByPlayer() {
      return !this.isSpeedBoosted() && this.currentSpeed > this.maxSpeed * 0.3F;
   }

   public EntityAIControlledByPlayer(EntityLiving var1, float var2) {
      this.thisEntity = var1;
      this.maxSpeed = var2;
      this.setMutexBits(7);
   }

   public boolean isSpeedBoosted() {
      return this.speedBoosted;
   }

   public boolean isStairOrSlab(Block var1) {
      return var1 instanceof BlockStairs || var1 instanceof BlockSlab;
   }
}
