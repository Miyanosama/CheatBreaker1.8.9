package io.netty.channel;

import io.netty.handler.codec.socks.SocksCommonUtils;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.world.chunk.storage.NibbleArrayReader;
import net.optifine.shaders.config.RenderScale;
import net.optifine.shaders.config.ShaderOptionResolver;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.MDC;

public interface ChannelFutureListener extends GenericFutureListener<ChannelFuture> {
   ChannelFutureListener CLOSE = new ChannelFutureListener() {

      public void operationComplete(ChannelFuture var1) {
         var1.channel().close();
      }
   };
   ChannelFutureListener CLOSE_ON_FAILURE = new ChannelFutureListener() {

      public void operationComplete(ChannelFuture var1) {
         if (!var1.isSuccess()) {
            var1.channel().close();
         }
      }
   };
   ChannelFutureListener FIRE_EXCEPTION_ON_FAILURE = new ChannelFutureListener() {

      public void operationComplete(ChannelFuture var1) {
         if (!var1.isSuccess()) {
            var1.channel().pipeline().fireExceptionCaught(var1.cause());
         }
      }
   };
}
