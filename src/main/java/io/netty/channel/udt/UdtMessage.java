package io.netty.channel.udt;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.ssl.JdkSslServerContext;

public class UdtMessage extends DefaultByteBufHolder {

   public UdtMessage duplicate() {
      return new UdtMessage(this.content().duplicate());
   }

   public UdtMessage copy() {
      return new UdtMessage(this.content().copy());
   }

   public UdtMessage(ByteBuf var1) {
      super(var1);
   }

   public UdtMessage retain(int var1) {
      super.retain(var1);
      return this;
   }

   public UdtMessage retain() {
      super.retain();
      return this;
   }
}
