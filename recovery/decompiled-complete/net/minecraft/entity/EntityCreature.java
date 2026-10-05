package net.minecraft.entity;

import io.netty.handler.codec.marshalling.LimitingByteInput;
import java.util.UUID;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors$4;
import net.minecraft.util.RegistryDefaulted;
import net.minecraft.world.World;

public abstract class EntityCreature extends EntityLiving {
   public EntitySelectors$4 field_0008;
   public boolean isMovementAITaskSet;
   public BlockPos homePosition = BlockPos.ORIGIN;
   public LimitingByteInput field_0005;
   public float maximumHomeDistance = -1.0F;
   public static AttributeModifier FLEEING_SPEED_MODIFIER = new AttributeModifier(EntityCreature.FLEEING_SPEED_MODIFIER_UUID, "Fleeing speed bonus", 2.0, 2)
      .setSaved(false);
   public static UUID FLEEING_SPEED_MODIFIER_UUID = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
   public RegistryDefaulted field_0000;
   public EntityAIBase aiBase = new EntityAIMoveTowardsRestriction(this, 1.0);

   public BlockPos getHomePosition() {
      return this.homePosition;
   }

   public boolean isWithinHomeDistanceFromPosition(BlockPos var1) {
      return this.maximumHomeDistance == -1.0F ? true : this.homePosition.distanceSq(var1) < this.maximumHomeDistance * this.maximumHomeDistance;
   }

   public float getBlockPathWeight(BlockPos var1) {
      return 0.0F;
   }

   public void setHomePosAndDistance(BlockPos var1, int var2) {
      this.homePosition = var1;
      this.maximumHomeDistance = var2;
   }

   public void func_142017_o(float var1) {
   }

   public EntityCreature(World var1) {
      super(var1);
   }

   public boolean isWithinHomeDistanceCurrentPosition() {
      return this.isWithinHomeDistanceFromPosition(new BlockPos(this));
   }

   @Override
   public void updateLeashedState() {
      super.updateLeashedState();
      if (this.getLeashed() && this.getLeashedToEntity() != null && this.getLeashedToEntity().o == this.o) {
         Entity var1 = this.getLeashedToEntity();
         this.setHomePosAndDistance(new BlockPos((int)var1.s, (int)var1.t, (int)var1.u), 5);
         float var2 = this.g(var1);
         if (this instanceof EntityTameable && ((EntityTameable)this).isSitting()) {
            if (var2 > 10.0F) {
               this.a(true, true);
            }

            return;
         }

         if (!this.isMovementAITaskSet) {
            this.i.addTask(2, this.aiBase);
            if (this.s() instanceof PathNavigateGround) {
               ((PathNavigateGround)this.s()).setAvoidsWater(false);
            }

            this.isMovementAITaskSet = true;
         }

         this.func_142017_o(var2);
         if (var2 > 4.0F) {
            this.s().tryMoveToEntityLiving(var1, 1.0);
         }

         if (var2 > 6.0F) {
            double var3 = (var1.s - this.s) / var2;
            double var5 = (var1.t - this.t) / var2;
            double var7 = (var1.u - this.u) / var2;
            this.v = this.v + var3 * Math.abs(var3) * 0.4;
            this.w = this.w + var5 * Math.abs(var5) * 0.4;
            this.x = this.x + var7 * Math.abs(var7) * 0.4;
         }

         if (var2 > 10.0F) {
            this.a(true, true);
         }
      } else if (!this.getLeashed() && this.isMovementAITaskSet) {
         this.isMovementAITaskSet = false;
         this.i.removeTask(this.aiBase);
         if (this.s() instanceof PathNavigateGround) {
            ((PathNavigateGround)this.s()).setAvoidsWater(true);
         }

         this.detachHome();
      }
   }

   @Override
   public boolean getCanSpawnHere() {
      return super.getCanSpawnHere() && this.getBlockPathWeight(new BlockPos(this.s, this.getEntityBoundingBox().b, this.u)) >= 0.0F;
   }

   public boolean hasHome() {
      return this.maximumHomeDistance != -1.0F;
   }

   public boolean hasPath() {
      return !this.h.noPath();
   }

   public void detachHome() {
      this.maximumHomeDistance = -1.0F;
   }

   public float getMaximumHomeDistance() {
      return this.maximumHomeDistance;
   }
}
