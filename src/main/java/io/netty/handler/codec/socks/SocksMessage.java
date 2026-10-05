package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import com.cheatbreaker.client.ui.util.GuiThemeColors;

public abstract class SocksMessage {
   public SocksMessageType type;
   public SocksProtocolVersion protocolVersion = SocksProtocolVersion.SOCKS5;

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
