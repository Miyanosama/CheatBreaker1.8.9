package io.netty.util.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public abstract class MpscLinkedQueueTailRef<E> extends MpscLinkedQueuePad1<E> {
   public static AtomicReferenceFieldUpdater<MpscLinkedQueueTailRef, MpscLinkedQueueNode> UPDATER;
   public transient volatile MpscLinkedQueueNode<E> tailRef;
   public static final long serialVersionUID = 8717072462993327429L;

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
