package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.spdy.DefaultSpdySettingsFrame$Setting;
import java.util.Collections;
import java.util.List;

public class SocksInitRequest extends SocksRequest {
   public List<SocksAuthScheme> authSchemes;
   public DefaultSpdySettingsFrame$Setting __junk3312444075152019512;

   public List<SocksAuthScheme> authSchemes() {
      return Collections.unmodifiableList(this.authSchemes);
   }

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
      var1.writeByte(this.protocolVersion().byteValue());
      var1.writeByte(this.authSchemes.size());

      for (SocksAuthScheme var3 : this.authSchemes) {
         var1.writeByte(var3.byteValue());
      }
   }

   public SocksInitRequest(List<SocksAuthScheme> var1) {
      super(SocksRequestType.INIT);
      if (var1 == null) {
         throw new NullPointerException("authSchemes");
      } else {
         this.authSchemes = var1;
      }
   }
}
