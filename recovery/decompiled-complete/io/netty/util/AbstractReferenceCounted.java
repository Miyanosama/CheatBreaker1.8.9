package io.netty.util;

import com.cheatbreaker.client.module.type.BossBarModule;
import io.netty.handler.codec.MessageToMessageCodec$1;
import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityFurnace;
import recovered.unidentified.UnidentifiedClass1954;

public abstract class AbstractReferenceCounted implements ReferenceCounted {
   public TileEntityFurnace __junk5202491914916325590;
   public static AtomicIntegerFieldUpdater<AbstractReferenceCounted> refCntUpdater;
   public BossBarModule __junk6798153266074335130;
   public volatile int refCnt = 1;
   public MessageToMessageCodec$1 __junk2698860300027950039;
   public UnidentifiedClass1954 __junk8659970134828718629;
   public Entity __junk4488741805525723468;

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
