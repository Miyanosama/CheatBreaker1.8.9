package io.netty.buffer;

import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;

public class ByteBufProcessor$10 implements ByteBufProcessor {
   public WebSocketFrameAggregator __junk6056023642345565961;
   public DefaultChannelGroup __junk3753261855797814279;

   @Override
   public boolean process(byte var1) {
      return var1 == 32 || var1 == 9;
   }
}
