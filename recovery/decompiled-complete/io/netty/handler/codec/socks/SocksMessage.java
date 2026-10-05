package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import recovered.unidentified.UnidentifiedClass5100;

public abstract class SocksMessage {
   public SocksMessageType type;
   public SocksProtocolVersion protocolVersion = SocksProtocolVersion.SOCKS5;
   public UnidentifiedClass5100 __junk4002325567799585116;

   public SocksMessageType type() {
      return this.type;
   }

   public abstract void encodeAsByteBuf(ByteBuf var1);

   public SocksMessage(SocksMessageType var1) {
      if (var1 == null) {
         throw new NullPointerException("type");
      } else {
         this.type = var1;
      }
   }

   public SocksProtocolVersion protocolVersion() {
      return this.protocolVersion;
   }
}
