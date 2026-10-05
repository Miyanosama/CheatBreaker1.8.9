package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.Recycler;
import java.util.List;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import recovered.unidentified.UnidentifiedClass0546;

public class SocksInitResponseDecoder extends ReplayingDecoder<SocksInitResponseDecoder$State> {
   public static String name;
   public UnidentifiedClass0546 __junk7661322058259626871;
   public S49PacketUpdateEntityNBT __junk3659613270907171027;
   public SocksAuthScheme authScheme;
   public SocksResponse msg = SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE;
   public Recycler __junk1625785159368500457;
   public SocksProtocolVersion version;

   public SocksInitResponseDecoder() {
      super(SocksInitResponseDecoder$State.CHECK_PROTOCOL_VERSION);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      switch (SocksInitResponseDecoder$1.$SwitchMap$io$netty$handler$codec$socks$SocksInitResponseDecoder$State[this.state().ordinal()]) {
         case 1:
            this.version = SocksProtocolVersion.valueOf(var2.readByte());
            if (this.version != SocksProtocolVersion.SOCKS5) {
               break;
            }

            this.checkpoint(SocksInitResponseDecoder$State.READ_PREFFERED_AUTH_TYPE);
         case 2:
            this.authScheme = SocksAuthScheme.valueOf(var2.readByte());
            this.msg = new SocksInitResponse(this.authScheme);
      }

      var1.pipeline().remove(this);
      var3.add(this.msg);
   }

   public static String getName() {
      return "SOCKS_INIT_RESPONSE_DECODER";
   }
}
