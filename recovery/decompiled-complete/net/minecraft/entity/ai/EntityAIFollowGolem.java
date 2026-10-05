package net.minecraft.entity.ai;

import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.util.internal.OneTimeTask;
import java.util.List;
import net.minecraft.block.BlockHopper$1;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.optifine.entity.model.ModelAdapter;

public class EntityAIFollowGolem extends EntityAIBase {
   public EntityVillager theVillager;
   public int takeGolemRoseTick;
   public ProtobufEncoder field_0003;
   public boolean tookGolemRose;
   public BlockHopper$1 field_0000;
   public EntityIronGolem theGolem;
   public RenderCreeper field_0008;
   public ModelAdapter field_0005;
   public OneTimeTask field_0002;

   @Override
   public void startExecuting() {
      this.takeGolemRoseTick = this.theVillager.getRNG().nextInt(320);
      this.tookGolemRose = false;
      this.theGolem.s().clearPathEntity();
   }

   @Override
   public void resetTask() {
      this.theGolem = null;
      this.theVillager.s().clearPathEntity();
   }

   @Override
   public boolean continueExecuting() {
      return this.theGolem.getHoldRoseTick() > 0;
   }

   @Override
   public boolean shouldExecute() {
      if (this.theVillager.l() >= 0) {
         return false;
      } else if (!this.theVillager.o.isDaytime()) {
         return false;
      } else {
         List var1 = this.theVillager.o.getEntitiesWithinAABB(EntityIronGolem.class, this.theVillager.getEntityBoundingBox().expand(6.0, 2.0, 6.0));
         if (var1.isEmpty()) {
            return false;
         } else {
            for (EntityIronGolem var3 : var1) {
               if (var3.getHoldRoseTick() > 0) {
                  this.theGolem = var3;
                  break;
               }
            }

            return this.theGolem != null;
         }
      }
   }

   public EntityAIFollowGolem(EntityVillager var1) {
      this.theVillager = var1;
      this.setMutexBits(3);
   }

   @Override
   public void updateTask() {
      this.theVillager.getLookHelper().setLookPositionWithEntity(this.theGolem, 30.0F, 30.0F);
      if (this.theGolem.getHoldRoseTick() == this.takeGolemRoseTick) {
         this.theVillager.s().tryMoveToEntityLiving(this.theGolem, 0.5);
         this.tookGolemRose = true;
      }

      if (this.tookGolemRose && this.theVillager.h(this.theGolem) < 4.0) {
         this.theGolem.setHoldingRose(false);
         this.theVillager.s().clearPathEntity();
      }
   }
}
