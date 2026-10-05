package io.netty.handler.codec.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$2;
import java.util.List;
import net.optifine.SmartLeaves;
import org.java_websocket.client.WebSocketClient$WebsocketWriteThread;
import recovered.unidentified.UnidentifiedClass0499;

public class HttpClientCodec$Encoder extends HttpRequestEncoder {
   public EpollSocketChannel$EpollSocketUnsafe$2 __junk4273849599739744450;
   public UnidentifiedClass0499 __junk7966302366119844655;
   public WebSocketClient$WebsocketWriteThread __junk6869201457107336643;
   public SmartLeaves __junk6171904678034439925;

   public HttpClientCodec$Encoder(HttpClientCodec var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void encode(ChannelHandlerContext var1, Object var2, List<Object> var3) {
      if (var2 instanceof HttpRequest && !HttpClientCodec.access$100(this.this$0)) {
         HttpClientCodec.access$200(this.this$0).offer(((HttpRequest)var2).getMethod());
      }

      super.encode(var1, var2, var3);
      if (HttpClientCodec.access$300(this.this$0) && var2 instanceof LastHttpContent) {
         HttpClientCodec.access$400(this.this$0).incrementAndGet();
      }
   }
}
