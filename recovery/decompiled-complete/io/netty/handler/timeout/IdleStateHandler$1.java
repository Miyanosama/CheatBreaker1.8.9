package io.netty.handler.timeout;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.command.CommandKill;
import net.minecraft.world.biome.BiomeGenJungle;

public class IdleStateHandler$1 implements ChannelFutureListener {
   public BiomeGenJungle __junk2720825874004876719;
   public CommandKill __junk8103160964185874536;

   public IdleStateHandler$1(IdleStateHandler var1) {
      this.this$0 = var1;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      this.this$0.lastWriteTime = System.nanoTime();
      IdleStateHandler.access$002(this.this$0, IdleStateHandler.access$102(this.this$0, true));
   }
}
