package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.util.ReferenceCountUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SpdyHttpResponseStreamIdHandler extends MessageToMessageCodec<Object, HttpMessage> {
   public Queue<Integer> ids = new LinkedList<>();
   public static Integer NO_ID = -1;

   @Override
   public void decode(ChannelHandlerContext var1, Object var2, List<Object> var3) {
      if (var2 instanceof HttpMessage) {
         boolean var4 = ((HttpMessage)var2).headers().contains("X-SPDY-Stream-ID");
         if (!var4) {
            this.ids.add(NO_ID);
         } else {
            this.ids.add(SpdyHttpHeaders.getStreamId((HttpMessage)var2));
         }
      } else if (var2 instanceof SpdyRstStreamFrame) {
         this.ids.remove(((SpdyRstStreamFrame)var2).streamId());
      }

      var3.add(ReferenceCountUtil.retain(var2));
   }

   @Override
   public boolean acceptInboundMessage(Object var1) {
      return var1 instanceof HttpMessage || var1 instanceof SpdyRstStreamFrame;
   }

   public void encode(ChannelHandlerContext var1, HttpMessage var2, List<Object> var3) {
      Integer var4 = this.ids.poll();
      if (var4 != null && var4 != NO_ID && !var2.headers().contains("X-SPDY-Stream-ID")) {
         SpdyHttpHeaders.setStreamId(var2, var4);
      }

      var3.add(ReferenceCountUtil.retain(var2));
   }
}
