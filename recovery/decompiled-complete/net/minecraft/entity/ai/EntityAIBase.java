package net.minecraft.entity.ai;

public abstract class EntityAIBase {
   public int mutexBits;

   public boolean isInterruptible() {
      return true;
   }

   public boolean continueExecuting() {
      return this.shouldExecute();
   }

   public void resetTask() {
   }

   public void setMutexBits(int var1) {
      this.mutexBits = var1;
   }

   public abstract boolean shouldExecute();

   public int getMutexBits() {
      return this.mutexBits;
   }

   public void startExecuting() {
   }

   public void updateTask() {
   }
}
