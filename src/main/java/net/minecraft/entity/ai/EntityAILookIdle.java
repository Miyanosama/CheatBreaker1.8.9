package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;

public class EntityAILookIdle extends EntityAIBase {
   public int idleTime;
   public double lookX;
   public double lookZ;
   public EntityLiving idleEntity;

   public EntityAILookIdle(EntityLiving var1) {
      this.idleEntity = var1;
      this.setMutexBits(3);
   }

   @Override
   public boolean continueExecuting() {
      return this.idleTime >= 0;
   }

   @Override
   public void startExecuting() {
      double var1 = (Math.PI * 2) * this.idleEntity.getRNG().nextDouble();
      this.lookX = Math.cos(var1);
      this.lookZ = Math.sin(var1);
      this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
   }

   @Override
   public boolean shouldExecute() {
      return this.idleEntity.getRNG().nextFloat() < 0.02F;
   }

   @Override
   public void updateTask() {
      this.idleTime--;
      this.idleEntity
         .getLookHelper()
         .setLookPosition(
            this.idleEntity.s + this.lookX,
            this.idleEntity.t + this.idleEntity.getEyeHeight(),
            this.idleEntity.u + this.lookZ,
            10.0F,
            this.idleEntity.getVerticalFaceSpeed()
         );
   }
}
