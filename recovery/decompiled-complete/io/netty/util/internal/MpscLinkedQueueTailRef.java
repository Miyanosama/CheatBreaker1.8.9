package io.netty.util.internal;

import io.netty.util.concurrent.SingleThreadEventExecutor$5;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import net.minecraft.inventory.ContainerPlayer$1;

public abstract class MpscLinkedQueueTailRef<E> extends MpscLinkedQueuePad1<E> {
   public static AtomicReferenceFieldUpdater<MpscLinkedQueueTailRef, MpscLinkedQueueNode> UPDATER;
   public SingleThreadEventExecutor$5 __junk1592955550676355281;
   public ContainerPlayer$1 __junk7360083005512891676;
   public transient volatile MpscLinkedQueueNode<E> tailRef;
   public static long serialVersionUID;

   public void setTailRef(MpscLinkedQueueNode<E> var1) {
      this.tailRef = var1;
   }

   static {
      AtomicReferenceFieldUpdater var0 = PlatformDependent.newAtomicReferenceFieldUpdater(MpscLinkedQueueTailRef.class, "tailRef");
      if (var0 == null) {
         var0 = AtomicReferenceFieldUpdater.newUpdater(MpscLinkedQueueTailRef.class, MpscLinkedQueueNode.class, "tailRef");
      }

      UPDATER = var0;
   }

   public MpscLinkedQueueNode<E> tailRef() {
      return this.tailRef;
   }

   public MpscLinkedQueueNode<E> getAndSetTailRef(MpscLinkedQueueNode<E> var1) {
      return UPDATER.getAndSet(this, var1);
   }
}
