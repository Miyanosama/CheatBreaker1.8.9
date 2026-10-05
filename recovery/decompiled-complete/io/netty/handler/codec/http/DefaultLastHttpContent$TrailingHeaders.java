package io.netty.handler.codec.http;

import io.netty.handler.codec.compression.JdkZlibEncoder$3;
import net.minecraft.client.stream.ChatController$EnumChannelState;
import net.optifine.util.StrUtils;

public class DefaultLastHttpContent$TrailingHeaders extends DefaultHttpHeaders {
   public JdkZlibEncoder$3 __junk7283643857095681839;
   public ChatController$EnumChannelState __junk4836715818619966404;
   public StrUtils __junk2338374469850181338;

   public DefaultLastHttpContent$TrailingHeaders(boolean var1) {
      super(var1);
   }

   @Override
   public void validateHeaderName0(CharSequence var1) {
      super.validateHeaderName0(var1);
      if (HttpHeaders.equalsIgnoreCase("Content-Length", var1)
         || HttpHeaders.equalsIgnoreCase("Transfer-Encoding", var1)
         || HttpHeaders.equalsIgnoreCase("Trailer", var1)) {
         throw new IllegalArgumentException("prohibited trailing header: " + var1);
      }
   }
}
