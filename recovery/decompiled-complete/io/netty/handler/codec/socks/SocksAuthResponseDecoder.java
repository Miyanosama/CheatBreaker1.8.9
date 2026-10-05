package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedSocketAddress;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;
import org.apache.log4j.config.PropertyGetter;
import org.slf4j.event.Level;

public class SocksAuthResponseDecoder extends ReplayingDecoder<SocksAuthResponseDecoder$State> {
   public EmbeddedSocketAddress __junk1633053494181677189;
   public static String name;
   public SocksSubnegotiationVersion version;
   public SocksAuthStatus authStatus;
   public SocksResponse msg = SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE;
   public PropertyGetter __junk1944619187954675003;
   public Level __junk9198726108090836023;

   public SocksAuthResponseDecoder() {
      super(SocksAuthResponseDecoder$State.CHECK_PROTOCOL_VERSION);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      switch (SocksAuthResponseDecoder$1.$SwitchMap$io$netty$handler$codec$socks$SocksAuthResponseDecoder$State[this.state().ordinal()]) {
         case 1:
            this.version = SocksSubnegotiationVersion.valueOf(var2.readByte());
            if (this.version != SocksSubnegotiationVersion.AUTH_PASSWORD) {
               break;
            }

            this.checkpoint(SocksAuthResponseDecoder$State.READ_AUTH_RESPONSE);
         case 2:
            this.authStatus = SocksAuthStatus.valueOf(var2.readByte());
            this.msg = new SocksAuthResponse(this.authStatus);
      }

      var1.pipeline().remove(this);
      var3.add(this.msg);
   }

   public static String getName() {
      return "SOCKS_AUTH_RESPONSE_DECODER";
   }
}
