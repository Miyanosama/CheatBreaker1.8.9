package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectEncoder;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.world.biome.BiomeCache;

public abstract class RtspObjectEncoder<H extends HttpMessage> extends HttpObjectEncoder<H> {

   @Override
   public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
      return var1 instanceof FullHttpMessage;
   }
}
