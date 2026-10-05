package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import net.optifine.Lagometer;
import net.optifine.override.PlayerControllerOF;

public class SocksInitResponse extends SocksResponse {
   public Lagometer __junk4992997278459399641;
   public PlayerControllerOF __junk2196623056672830495;
   public SocksAuthScheme authScheme;

   public SocksAuthScheme authScheme() {
      return this.authScheme;
   }

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
      var1.writeByte(this.protocolVersion().byteValue());
      var1.writeByte(this.authScheme.byteValue());
   }

   public SocksInitResponse(SocksAuthScheme var1) {
      super(SocksResponseType.INIT);
      if (var1 == null) {
         throw new NullPointerException("authScheme");
      } else {
         this.authScheme = var1;
      }
   }
}
