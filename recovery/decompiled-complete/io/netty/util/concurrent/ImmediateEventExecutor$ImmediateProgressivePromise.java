package io.netty.util.concurrent;

import io.netty.channel.nio.AbstractNioMessageChannel$NioMessageUnsafe;
import io.netty.handler.codec.rtsp.RtspHeaders$Names;
import net.minecraft.entity.monster.EntityGhast$GhastMoveHelper;

public class ImmediateEventExecutor$ImmediateProgressivePromise<V> extends DefaultProgressivePromise<V> {
   public RtspHeaders$Names __junk5143636394326616115;
   public EntityGhast$GhastMoveHelper __junk8284409456269257359;
   public AbstractNioMessageChannel$NioMessageUnsafe __junk5314524895833941717;

   @Override
   public void checkDeadLock() {
   }

   public ImmediateEventExecutor$ImmediateProgressivePromise(EventExecutor var1) {
      super(var1);
   }
}
