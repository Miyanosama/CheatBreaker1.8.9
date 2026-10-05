package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIFollowOwner extends EntityAIBase {
   public World theWorld;
   public double followSpeed;
   public int field_75343_h;
   public PathNavigate petPathfinder;
   public EntityTameable thePet;
   public EntityLivingBase theOwner;
   public float minDist;
   public boolean field_75344_i;
   public float maxDist;

   @Override
   public void startExecuting() {
      this.field_75343_h = 0;
      this.field_75344_i = ((PathNavigateGround)this.thePet.s()).getAvoidsWater();
      ((PathNavigateGround)this.thePet.s()).setAvoidsWater(false);
   }

   public EntityAIFollowOwner(EntityTameable var1, double var2, float var4, float var5) {
      this.thePet = var1;
      this.theWorld = var1.o;
      this.followSpeed = var2;
      this.petPathfinder = var1.s();
      this.minDist = var4;
      this.maxDist = var5;
      this.setMutexBits(3);
      if (!(var1.s() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
      }
   }

   @Override
   public boolean continueExecuting() {
      return !this.petPathfinder.noPath() && this.thePet.h(this.theOwner) > this.maxDist * this.maxDist && !this.thePet.isSitting();
   }

   public boolean func_181065_a(BlockPos var1) {
      IBlockState var2 = this.theWorld.getBlockState(var1);
      Block var3 = var2.getBlock();
      return var3 == Blocks.air ? true : !var3.isFullCube();
   }

   @Override
   public void updateTask() {
      this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, this.thePet.getVerticalFaceSpeed());
      if (!this.thePet.isSitting() && --this.field_75343_h <= 0) {
         this.field_75343_h = 10;
         if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.followSpeed) && !this.thePet.getLeashed() && this.thePet.h(this.theOwner) >= 144.0) {
            int var1 = MathHelper.floor_double(this.theOwner.s) - 2;
            int var2 = MathHelper.floor_double(this.theOwner.u) - 2;
            int var3 = MathHelper.floor_double(this.theOwner.getEntityBoundingBox().b);

            for (int var4 = 0; var4 <= 4; var4++) {
               for (int var5 = 0; var5 <= 4; var5++) {
                  if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3)
                     && World.doesBlockHaveSolidTopSurface(this.theWorld, new BlockPos(var1 + var4, var3 - 1, var2 + var5))
                     && this.func_181065_a(new BlockPos(var1 + var4, var3, var2 + var5))
                     && this.func_181065_a(new BlockPos(var1 + var4, var3 + 1, var2 + var5))) {
                     this.thePet.a_(var1 + var4 + 0.5F, var3, var2 + var5 + 0.5F, this.thePet.y, this.thePet.z);
                     this.petPathfinder.clearPathEntity();
                     return;
                  }
               }
            }
         }
      }
   }

   @Override
   public void resetTask() {
      this.theOwner = null;
      this.petPathfinder.clearPathEntity();
      ((PathNavigateGround)this.thePet.s()).setAvoidsWater(true);
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.thePet.getOwner();
      if (var1 == null) {
         return false;
      } else if (var1 instanceof EntityPlayer && ((EntityPlayer)var1).isSpectator()) {
         return false;
      } else if (this.thePet.isSitting()) {
         return false;
      } else if (this.thePet.h(var1) < this.minDist * this.minDist) {
         return false;
      } else {
         this.theOwner = var1;
         return true;
      }
   }
}
