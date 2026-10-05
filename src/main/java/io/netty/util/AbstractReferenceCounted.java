package io.netty.util;

import com.cheatbreaker.client.module.type.BossBarModule;
import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityFurnace;
import com.cheatbreaker.client.websocket.server.WSPacketEmote;

public abstract class AbstractReferenceCounted implements ReferenceCounted {
   public static AtomicIntegerFieldUpdater<AbstractReferenceCounted> refCntUpdater;
   public volatile int refCnt = 1;

   @Override
   public ReferenceCounted retain() {
      int var1;
      do {
         var1 = this.refCnt;
         if (var1 == 0) {
            throw new IllegalReferenceCountException(0, 1);
         }

         if (var1 == Integer.MAX_VALUE) {
            throw new IllegalReferenceCountException(Integer.MAX_VALUE, 1);
         }
      } while (!refCntUpdater.compareAndSet(this, var1, var1 + 1));

      return this;
   }

   @Override
   public boolean release() {
      int var1;
      do {
         var1 = this.refCnt;
         if (var1 == 0) {
            throw new IllegalReferenceCountException(0, -1);
         }
      } while (!refCntUpdater.compareAndSet(this, var1, var1 - 1));

      if (var1 == 1) {
         this.deallocate();
         return true;
      } else {
         return false;
      }
   }

   static {
      AtomicIntegerFieldUpdater var0 = PlatformDependent.newAtomicIntegerFieldUpdater(AbstractReferenceCounted.class, "refCnt");
      if (var0 == null) {
         var0 = AtomicIntegerFieldUpdater.newUpdater(AbstractReferenceCounted.class, "refCnt");
      }

      refCntUpdater = var0;
   }

   public abstract void deallocate();

   @Override
   public int refCnt() {
      return this.refCnt;
   }

   @Override
   public ReferenceCounted retain(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("increment: " + var1 + " (expected: > 0)");
      } else {
         int var2;
         do {
            var2 = this.refCnt;
            if (var2 == 0) {
               throw new IllegalReferenceCountException(0, 1);
            }

            if (var2 > Integer.MAX_VALUE - var1) {
               throw new IllegalReferenceCountException(var2, var1);
            }
         } while (!refCntUpdater.compareAndSet(this, var2, var2 + var1));

         return this;
      }
   }

   public void setRefCnt(int var1) {
      this.refCnt = var1;
   }

   @Override
   public boolean release(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("decrement: " + var1 + " (expected: > 0)");
      } else {
         int var2;
         do {
            var2 = this.refCnt;
            if (var2 < var1) {
               throw new IllegalReferenceCountException(var2, -var1);
            }
         } while (!refCntUpdater.compareAndSet(this, var2, var2 - var1));

         if (var2 == var1) {
            this.deallocate();
            return true;
         } else {
            return false;
         }
      }
   }
}
