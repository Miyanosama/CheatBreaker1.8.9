package io.netty.handler.codec.http;

import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.compression.JdkZlibDecoder$1;

public class HttpServerCodec extends CombinedChannelDuplexHandler<HttpRequestDecoder, HttpResponseEncoder> {
   public JdkZlibDecoder$1 __junk1175723537628095741;

   public HttpServerCodec(int var1, int var2, int var3, boolean var4) {
      super(new HttpRequestDecoder(var1, var2, var3, var4), new HttpResponseEncoder());
   }

   public HttpServerCodec() {
      this(4096, 8192, 8192);
   }

   public HttpServerCodec(int var1, int var2, int var3) {
      super(new HttpRequestDecoder(var1, var2, var3), new HttpResponseEncoder());
   }
}
