package io.netty.handler.codec.socks;

import com.cheatbreaker.client.util.friend.Friend;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedSocketAddress;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;
import org.apache.log4j.config.PropertyGetter;
import org.slf4j.event.Level;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$YDoubleRoomFitHelper;

public class SocksAuthResponseDecoder extends ReplayingDecoder<SocksAuthResponseDecoder.State> {
   public static final String name = "SOCKS_AUTH_RESPONSE_DECODER";
   public SocksSubnegotiationVersion version;
   public SocksAuthStatus authStatus;
   public SocksResponse msg = SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE;

   public SocksAuthResponseDecoder() {
      super(SocksAuthResponseDecoder.State.CHECK_PROTOCOL_VERSION);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      switch (this.state()) {
         case CHECK_PROTOCOL_VERSION:
            this.version = SocksSubnegotiationVersion.valueOf(var2.readByte());
            if (this.version != SocksSubnegotiationVersion.AUTH_PASSWORD) {
               break;
            }

            this.checkpoint(SocksAuthResponseDecoder.State.READ_AUTH_RESPONSE);
         case READ_AUTH_RESPONSE:
            this.authStatus = SocksAuthStatus.valueOf(var2.readByte());
            this.msg = new SocksAuthResponse(this.authStatus);
      }

      var1.pipeline().remove(this);
      var3.add(this.msg);
   }

   public static String getName() {
      return "SOCKS_AUTH_RESPONSE_DECODER";
   }

   public static enum State {
      CHECK_PROTOCOL_VERSION,
      READ_AUTH_RESPONSE;

      // $VF: synthetic field
      public static SocksAuthResponseDecoder.State[] $VALUES = new SocksAuthResponseDecoder.State[]{
         SocksAuthResponseDecoder.State.CHECK_PROTOCOL_VERSION, SocksAuthResponseDecoder.State.READ_AUTH_RESPONSE
      };
   }
}
