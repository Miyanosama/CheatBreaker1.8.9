package io.netty.util.concurrent;

import io.netty.channel.epoll.EpollDatagramChannelConfig;
import net.minecraft.client.renderer.entity.RenderZombie;

public class MultithreadEventExecutorGroup$GenericEventExecutorChooser implements MultithreadEventExecutorGroup$EventExecutorChooser {
   public RenderZombie __junk6494356110259993297;
   public EpollDatagramChannelConfig __junk4348762365942401819;

   public MultithreadEventExecutorGroup$GenericEventExecutorChooser(MultithreadEventExecutorGroup var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public EventExecutor next() {
      return MultithreadEventExecutorGroup.access$300(this.this$0)[Math.abs(
         MultithreadEventExecutorGroup.access$500(this.this$0).getAndIncrement() % MultithreadEventExecutorGroup.access$300(this.this$0).length
      )];
   }
}
