package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.collection.IntObjectHashMap$1;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
import java.util.List;
import net.minecraft.client.gui.GuiKeyBindingList$KeyEntry;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

public class SocksAuthRequestDecoder extends ReplayingDecoder<SocksAuthRequestDecoder$State> {
   public String password;
   public String username;
   public SocksRequest msg = SocksCommonUtils.UNKNOWN_SOCKS_REQUEST;
   public GuiKeyBindingList$KeyEntry __junk733307201854661183;
   public IntObjectHashMap$1 __junk6145496575306667555;
   public static String name;
   public Slf4JLoggerFactory __junk74747764198160252;
   public NoiseGeneratorOctaves __junk5345200422090907111;
   public int fieldLength;
   public SocksSubnegotiationVersion version;

   public SocksAuthRequestDecoder() {
      super(SocksAuthRequestDecoder$State.CHECK_PROTOCOL_VERSION);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      switch (SocksAuthRequestDecoder$1.$SwitchMap$io$netty$handler$codec$socks$SocksAuthRequestDecoder$State[this.state().ordinal()]) {
         case 1:
            this.version = SocksSubnegotiationVersion.valueOf(var2.readByte());
            if (this.version != SocksSubnegotiationVersion.AUTH_PASSWORD) {
               break;
            }

            this.checkpoint(SocksAuthRequestDecoder$State.READ_USERNAME);
         case 2:
            this.fieldLength = var2.readByte();
            this.username = var2.readBytes(this.fieldLength).toString(CharsetUtil.US_ASCII);
            this.checkpoint(SocksAuthRequestDecoder$State.READ_PASSWORD);
         case 3:
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
}
