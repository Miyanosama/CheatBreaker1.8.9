package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.module.type.ParticlesModule;
import io.netty.buffer.ByteBuf;
import io.netty.util.internal.PlatformDependent;
import net.minecraft.init.Blocks;

public abstract class SpdyHeaderBlockEncoder {

   public static SpdyHeaderBlockEncoder newInstance(SpdyVersion var0, int var1, int var2, int var3) {
      return (SpdyHeaderBlockEncoder)(PlatformDependent.javaVersion() >= 7
         ? new SpdyHeaderBlockZlibEncoder(var0, var1)
         : new SpdyHeaderBlockJZlibEncoder(var0, var1, var2, var3));
   }

   public abstract void end();

   public abstract ByteBuf encode(SpdyHeadersFrame var1) throws java.lang.Exception ;
}
