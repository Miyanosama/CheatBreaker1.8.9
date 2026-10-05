package io.netty.handler.traffic;

import io.netty.channel.ChannelPromise;

public class ChannelTrafficShapingHandler$ToSend {
   public Object toSend;
   public ChannelPromise promise;
   public long date;

   public ChannelTrafficShapingHandler$ToSend(long var1, Object var3, ChannelPromise var4) {
      this.date = System.currentTimeMillis() + var1;
      this.toSend = var3;
      this.promise = var4;
   }
}
