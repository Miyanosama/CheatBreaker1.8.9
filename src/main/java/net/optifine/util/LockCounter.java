package net.optifine.util;

public class LockCounter {
   public int lockCount;

   @Override
   public String toString() {
      return "lockCount: " + this.lockCount;
   }

   public boolean lock() {
      this.lockCount++;
      return this.lockCount == 1;
   }

   public boolean unlock() {
      if (this.lockCount <= 0) {
         return false;
      } else {
         this.lockCount--;
         return this.lockCount == 0;
      }
   }

   public int getLockCount() {
      return this.lockCount;
   }

   public boolean isLocked() {
      return this.lockCount > 0;
   }
}
