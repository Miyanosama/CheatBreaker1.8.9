package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectEncoder;
import io.netty.handler.codec.spdy.SpdyFrameDecoder$1;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.world.biome.BiomeCache$Block;

public abstract class RtspObjectEncoder<H extends HttpMessage> extends HttpObjectEncoder<H> {
   public BiomeCache$Block __junk8097995659273769576;
   public SpdyFrameDecoder$1 __junk7881545598178135092;
   public EntitySplashFX __junk6424993876977098915;

   @Override
   public boolean acceptOutboundMessage(Object var1) {
      return var1 instanceof FullHttpMessage;
   }
}
