package io.netty.channel.udt;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.ssl.JdkSslServerContext;
import net.minecraft.init.Bootstrap$8;

public class UdtMessage extends DefaultByteBufHolder {
   public JdkSslServerContext __junk396070658319767350;
   public LengthFieldPrepender __junk8198307156259278722;
   public Bootstrap$8 __junk206648097460571138;

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
