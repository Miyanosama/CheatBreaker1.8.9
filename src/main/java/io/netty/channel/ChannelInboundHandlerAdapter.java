package io.netty.channel;

import com.jagrosh.discordipc.entities.Packet;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.client.renderer.entity.RenderMinecartMobSpawner;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class ChannelInboundHandlerAdapter extends ChannelHandlerAdapter implements ChannelInboundHandler {

   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      var1.fireChannelInactive();
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) throws java.lang.Exception {
      var1.fireChannelRegistered();
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception {
      var1.fireExceptionCaught(var2);
   }

   @Override
   public void channelUnregistered(ChannelHandlerContext var1) throws java.lang.Exception {
      var1.fireChannelUnregistered();
   }

   @Override
   public void channelReadComplete(ChannelHandlerContext var1) throws java.lang.Exception {
      var1.fireChannelReadComplete();
   }

   @Override
   public void userEventTriggered(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      var1.fireUserEventTriggered(var2);
   }

   @Override
   public void channelWritabilityChanged(ChannelHandlerContext var1) throws java.lang.Exception {
      var1.fireChannelWritabilityChanged();
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) throws java.lang.Exception {
      var1.fireChannelActive();
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      var1.fireChannelRead(var2);
   }
}
