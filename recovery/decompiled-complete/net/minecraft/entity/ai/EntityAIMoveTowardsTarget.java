package net.minecraft.entity.ai;

import io.netty.handler.codec.socks.SocksCmdRequestDecoder$1;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.Vec3;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$Penthouse;

public class EntityAIMoveTowardsTarget extends EntityAIBase {
   public double movePosZ;
   public double movePosY;
   public float maxTargetDistance;
   public EntityCreature theEntity;
   public double movePosX;
   public GameSettings field_0002;
   public double speed;
   public SharedMonsterAttributes field_0006;
   public SocksCmdRequestDecoder$1 field_0003;
   public EntityLivingBase targetEntity;
   public StructureOceanMonumentPieces$Penthouse field_0000;

   @Override
   public boolean shouldExecute() {
      this.targetEntity = this.theEntity.getAttackTarget();
      if (this.targetEntity == null) {
         return false;
      } else if (this.targetEntity.h(this.theEntity) > this.maxTargetDistance * this.maxTargetDistance) {
         return false;
      } else {
         Vec3 var1 = RandomPositionGenerator.findRandomTargetBlockTowards(
            this.theEntity, 16, 7, new Vec3(this.targetEntity.s, this.targetEntity.t, this.targetEntity.u)
         );
         if (var1 == null) {
            return false;
         } else {
            this.movePosX = var1.xCoord;
            this.movePosY = var1.yCoord;
            this.movePosZ = var1.zCoord;
            return true;
         }
      }
   }

   public EntityAIMoveTowardsTarget(EntityCreature var1, double var2, float var4) {
      this.theEntity = var1;
      this.speed = var2;
      this.maxTargetDistance = var4;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.theEntity.s().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
   }

   @Override
   public boolean continueExecuting() {
      return !this.theEntity.s().noPath()
         && this.targetEntity.isEntityAlive()
         && this.targetEntity.h(this.theEntity) < this.maxTargetDistance * this.maxTargetDistance;
   }

   @Override
   public void resetTask() {
      this.targetEntity = null;
   }
}
