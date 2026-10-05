package io.netty.util;

import io.netty.util.internal.MpscLinkedQueueNode;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Corridor4;

public class ThreadDeathWatcher$Entry extends MpscLinkedQueueNode<ThreadDeathWatcher$Entry> {
   public StructureNetherBridgePieces$Corridor4 __junk3526352842546930331;
   public Thread thread;
   public boolean isWatch;
   public Runnable task;

   public ThreadDeathWatcher$Entry value() {
      return this;
   }

   @Override
   public int hashCode() {
      return this.thread.hashCode() ^ this.task.hashCode();
   }

   public ThreadDeathWatcher$Entry(Thread var1, Runnable var2, boolean var3) {
      this.thread = var1;
      this.task = var2;
      this.isWatch = var3;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ThreadDeathWatcher$Entry)) {
         return false;
      } else {
         ThreadDeathWatcher$Entry var2 = (ThreadDeathWatcher$Entry)var1;
         return this.thread == var2.thread && this.task == var2.task;
      }
   }
}
