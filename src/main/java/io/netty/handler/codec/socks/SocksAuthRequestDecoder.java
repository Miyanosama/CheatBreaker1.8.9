package io.netty.handler.codec.socks;

import com.cheatbreaker.client.util.thread.AliasesThread;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SucceededChannelFuture;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
import java.util.List;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import org.apache.log4j.lf5.LF5Appender;

public class SocksAuthRequestDecoder extends ReplayingDecoder<SocksAuthRequestDecoder.State> {
   public String password;
   public String username;
   public SocksRequest msg = SocksCommonUtils.UNKNOWN_SOCKS_REQUEST;
   public static final String name = "SOCKS_AUTH_REQUEST_DECODER";
   public int fieldLength;
   public SocksSubnegotiationVersion version;

   public SocksAuthRequestDecoder() {
      super(SocksAuthRequestDecoder.State.CHECK_PROTOCOL_VERSION);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      switch (this.state()) {
         case CHECK_PROTOCOL_VERSION:
            this.version = SocksSubnegotiationVersion.valueOf(var2.readByte());
            if (this.version != SocksSubnegotiationVersion.AUTH_PASSWORD) {
               break;
            }

            this.checkpoint(SocksAuthRequestDecoder.State.READ_USERNAME);
         case READ_USERNAME:
            this.fieldLength = var2.readByte();
            this.username = var2.readBytes(this.fieldLength).toString(CharsetUtil.US_ASCII);
            this.checkpoint(SocksAuthRequestDecoder.State.READ_PASSWORD);
         case READ_PASSWORD:
            this.fieldLength = var2.readByte();
            this.password = var2.readBytes(this.fieldLength).toString(CharsetUtil.US_ASCII);
            this.msg = new SocksAuthRequest(this.username, this.password);
      }

      var1.pipeline().remove(this);
      var3.add(this.msg);
   }

   public static String getName() {
      return "SOCKS_AUTH_REQUEST_DECODER";
   }

   public static enum State {
      CHECK_PROTOCOL_VERSION,
      READ_USERNAME,
      READ_PASSWORD;

   }
}
