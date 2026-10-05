package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.util.AttributeKey;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ReservationNode;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.ThreadDownloadImageData;

public class ServerBootstrap$ServerBootstrapAcceptor extends ChannelInboundHandlerAdapter {
   public ConcurrentHashMapV8$ReservationNode __junk4562851002996490053;
   public Entry<AttributeKey<?>, Object>[] childAttrs;
   public EventLoopGroup childGroup;
   public ThreadDownloadImageData __junk1221372860225635941;
   public Entry<ChannelOption<?>, Object>[] childOptions;
   public ChannelHandler childHandler;

   public ServerBootstrap$ServerBootstrapAcceptor(
      EventLoopGroup var1, ChannelHandler var2, Entry<ChannelOption<?>, Object>[] var3, Entry<AttributeKey<?>, Object>[] var4
   ) {
      this.childGroup = var1;
      this.childHandler = var2;
      this.childOptions = var3;
      this.childAttrs = var4;
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      ChannelConfig var3 = var1.channel().config();
      if (var3.isAutoRead()) {
         var3.setAutoRead(false);
         var1.channel()
            .eventLoop()
            .schedule(new ServerBootstrap$ServerBootstrapAcceptor$2(this, var3), -2748905244707451903L & 2748905242998235409L, TimeUnit.SECONDS);
      }

      var1.fireExceptionCaught(var2);
   }

   public static void forceClose(Channel var0, Throwable var1) {
      var0.unsafe().closeForcibly();
      ServerBootstrap.access$000().warn("Failed to register an accepted channel: " + var0, var1);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      Channel var3 = (Channel)var2;
      var3.pipeline().addLast(this.childHandler);

      for (Entry var7 : this.childOptions) {
         try {
            if (!var3.config().setOption((ChannelOption<Object>)var7.getKey(), var7.getValue())) {
               ServerBootstrap.access$000().warn("Unknown channel option: " + var7);
            }
         } catch (Throwable var10) {
            ServerBootstrap.access$000().warn("Failed to set a channel option: " + var3, var10);
         }
      }

      for (Entry var14 : this.childAttrs) {
         var3.<Object>attr((AttributeKey<Object>)var14.getKey()).set(var14.getValue());
      }

      try {
         this.childGroup.register(var3).addListener(new ServerBootstrap$ServerBootstrapAcceptor$1(this, var3));
      } catch (Throwable var9) {
         forceClose(var3, var9);
      }
   }
}
