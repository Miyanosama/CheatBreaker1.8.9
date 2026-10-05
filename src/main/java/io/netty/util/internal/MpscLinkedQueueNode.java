package io.netty.util.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import net.optifine.util.DisplayModeComparator;

public abstract class MpscLinkedQueueNode<T> {
   public volatile MpscLinkedQueueNode<T> next;
   public static AtomicReferenceFieldUpdater<MpscLinkedQueueNode, MpscLinkedQueueNode> nextUpdater;

   public abstract T value();

   static {
      AtomicReferenceFieldUpdater var0 = PlatformDependent.newAtomicReferenceFieldUpdater(MpscLinkedQueueNode.class, "next");
      if (var0 == null) {
         var0 = AtomicReferenceFieldUpdater.newUpdater(MpscLinkedQueueNode.class, MpscLinkedQueueNode.class, "next");
      }

      nextUpdater = var0;
   }

   public T clearMaybe() {
      return this.value();
   }

   public MpscLinkedQueueNode<T> next() {
      return this.next;
   }

   public void unlink() {
      this.setNext(null);
   }

   public void setNext(MpscLinkedQueueNode<T> var1) {
      nextUpdater.lazySet(this, var1);
   }
}
