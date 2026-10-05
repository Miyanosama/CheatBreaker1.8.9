package io.netty.handler.codec.http;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceKeysToIntTask;
import net.minecraft.crash.CrashReportCategory$6;
import net.minecraft.util.ChatComponentProcessor;
import recovered.unidentified.UnidentifiedClass1798;
import recovered.unidentified.UnidentifiedClass5108;

public class HttpContentDecompressor extends HttpContentDecoder {
   public UnidentifiedClass1798 __junk2250469536021767012;
   public ChatComponentProcessor __junk2742452947020349093;
   public UnidentifiedClass5108 __junk8157136200005980285;
   public CrashReportCategory$6 __junk8358982408843563990;
   public boolean strict;
   public ConcurrentHashMapV8$MapReduceKeysToIntTask __junk4905857813367520781;

   public HttpContentDecompressor(boolean var1) {
      this.strict = var1;
   }

   @Override
   public EmbeddedChannel newContentDecoder(String var1) {
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
