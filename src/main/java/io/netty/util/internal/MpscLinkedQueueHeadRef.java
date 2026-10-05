package io.netty.util.internal;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import net.minecraft.client.particle.EntityFirework_StarterFX;

public abstract class MpscLinkedQueueHeadRef<E> extends MpscLinkedQueuePad0<E> implements Serializable {
   public static final long serialVersionUID = 8467054865577874285L;
   public transient volatile MpscLinkedQueueNode<E> headRef;
   public static AtomicReferenceFieldUpdater<MpscLinkedQueueHeadRef, MpscLinkedQueueNode> UPDATER;

   public void lazySetHeadRef(MpscLinkedQueueNode<E> var1) {
      UPDATER.lazySet(this, var1);
   }

   public void setHeadRef(MpscLinkedQueueNode<E> var1) {
      this.headRef = var1;
   }

   public MpscLinkedQueueNode<E> headRef() {
      return this.headRef;
   }

   static {
      AtomicReferenceFieldUpdater var0 = PlatformDependent.newAtomicReferenceFieldUpdater(MpscLinkedQueueHeadRef.class, "headRef");
      if (var0 == null) {
         var0 = AtomicReferenceFieldUpdater.newUpdater(MpscLinkedQueueHeadRef.class, MpscLinkedQueueNode.class, "headRef");
      }

      UPDATER = var0;
   }
}
