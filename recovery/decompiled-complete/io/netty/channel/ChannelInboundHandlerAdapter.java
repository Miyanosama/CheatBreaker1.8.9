package io.netty.channel;

import com.jagrosh.discordipc.entities.Packet;
import net.minecraft.block.BlockStoneSlab$EnumType;
import net.minecraft.client.renderer.entity.RenderMinecartMobSpawner;
import recovered.unidentified.UnidentifiedClass3394;

public class ChannelInboundHandlerAdapter extends ChannelHandlerAdapter implements ChannelInboundHandler {
   public BlockStoneSlab$EnumType __junk8516234210140654634;
   public RenderMinecartMobSpawner __junk7674852536501814545;
   public Packet __junk1303640905458811231;
   public UnidentifiedClass3394 __junk6501427986711178724;

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      var1.fireChannelInactive();
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) {
      var1.fireChannelRegistered();
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      var1.fireExceptionCaught(var2);
   }

   @Override
   public void channelUnregistered(ChannelHandlerContext var1) {
      var1.fireChannelUnregistered();
   }

   @Override
   public void channelReadComplete(ChannelHandlerContext var1) {
      var1.fireChannelReadComplete();
   }

   @Override
   public void userEventTriggered(ChannelHandlerContext var1, Object var2) {
      var1.fireUserEventTriggered(var2);
   }

   @Override
   public void channelWritabilityChanged(ChannelHandlerContext var1) {
      var1.fireChannelWritabilityChanged();
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) {
      var1.fireChannelActive();
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      var1.fireChannelRead(var2);
   }
}
