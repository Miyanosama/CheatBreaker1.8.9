package net.optifine.util;

import io.netty.handler.codec.marshalling.MarshallingDecoder;
import org.apache.log4j.lf5.viewer.LogTable;

public class LockCounter {
   public LogTable field_0001;
   public int lockCount;
   public MarshallingDecoder field_0000;

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
