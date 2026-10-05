package io.netty.handler.codec.socks;

import com.cheatbreaker.client.nethandler.client.PacketClientVoice;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.Recycler;
import java.util.List;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import com.cheatbreaker.client.ui.mainmenu.FadingTextElement;
import com.cheatbreaker.client.websocket.shared.WSPacketClientFriendRequestUpdate;

public class SocksInitResponseDecoder extends ReplayingDecoder<SocksInitResponseDecoder.State> {
   public static final String name = "SOCKS_INIT_RESPONSE_DECODER";
   public SocksAuthScheme authScheme;
   public SocksResponse msg = SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE;
   public SocksProtocolVersion version;

   public SocksInitResponseDecoder() {
      super(SocksInitResponseDecoder.State.CHECK_PROTOCOL_VERSION);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      switch (this.state()) {
         case CHECK_PROTOCOL_VERSION:
            this.version = SocksProtocolVersion.valueOf(var2.readByte());
            if (this.version != SocksProtocolVersion.SOCKS5) {
               break;
            }

            this.checkpoint(SocksInitResponseDecoder.State.READ_PREFFERED_AUTH_TYPE);
         case READ_PREFFERED_AUTH_TYPE:
            this.authScheme = SocksAuthScheme.valueOf(var2.readByte());
            this.msg = new SocksInitResponse(this.authScheme);
      }

      var1.pipeline().remove(this);
      var3.add(this.msg);
   }

   public static String getName() {
      return "SOCKS_INIT_RESPONSE_DECODER";
   }

   public static enum State {
      CHECK_PROTOCOL_VERSION,
      READ_PREFFERED_AUTH_TYPE;
      // $VF: synthetic field
      public static SocksInitResponseDecoder.State[] $VALUES = new SocksInitResponseDecoder.State[]{
         CHECK_PROTOCOL_VERSION, SocksInitResponseDecoder.State.READ_PREFFERED_AUTH_TYPE
      };
   }
}
