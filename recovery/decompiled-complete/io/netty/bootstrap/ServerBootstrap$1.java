package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import java.util.Map.Entry;
import net.minecraft.world.gen.structure.MapGenVillage$Start;
import recovered.unidentified.UnidentifiedClass1927;

public class ServerBootstrap$1 extends ChannelInitializer<Channel> {
   public UnidentifiedClass1927 __junk7362505164815735599;
   public MapGenVillage$Start __junk3701953424123892961;

   public ServerBootstrap$1(ServerBootstrap var1, EventLoopGroup var2, ChannelHandler var3, Entry[] var4, Entry[] var5) {
      this.this$0 = var1;
      this.val$currentChildGroup = var2;
      this.val$currentChildHandler = var3;
      this.val$currentChildOptions = var4;
      this.val$currentChildAttrs = var5;
      super();
   }

   @Override
   public void initChannel(Channel var1) {
      var1.pipeline()
         .addLast(
            new ServerBootstrap$ServerBootstrapAcceptor(
               this.val$currentChildGroup, this.val$currentChildHandler, this.val$currentChildOptions, this.val$currentChildAttrs
            )
         );
   }
}
