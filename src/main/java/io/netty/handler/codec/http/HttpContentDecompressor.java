package io.netty.handler.codec.http;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import net.minecraft.util.ChatComponentProcessor;
import com.cheatbreaker.client.event.type.ScoreboardDrawEvent;
import net.optifine.Lagometer$TimerNano;

public class HttpContentDecompressor extends HttpContentDecoder {
   public boolean strict;

   public HttpContentDecompressor(boolean var1) {
      this.strict = var1;
   }

   @Override
   public EmbeddedChannel newContentDecoder(String var1) throws java.lang.Exception {
      if ("gzip".equalsIgnoreCase(var1) || "x-gzip".equalsIgnoreCase(var1)) {
         return new EmbeddedChannel(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));
      } else if (!"deflate".equalsIgnoreCase(var1) && !"x-deflate".equalsIgnoreCase(var1)) {
         return null;
      } else {
         ZlibWrapper var2;
         if (this.strict) {
            var2 = ZlibWrapper.ZLIB;
         } else {
            var2 = ZlibWrapper.ZLIB_OR_NONE;
         }

         return new EmbeddedChannel(ZlibCodecFactory.newZlibDecoder(var2));
      }
   }

   public HttpContentDecompressor() {
      this(false);
   }
}
