package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.AxisAngle4f;
import junit.swingui.TestRunner$11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.realms.RealmsDefaultVertexFormat;
import net.minecraft.util.EnumFacing;
import net.optifine.config.VillagerProfession;
import org.apache.log4j.chainsaw.ControlPanel$4;
import org.json.JSONTokener;

public class SocksInitRequestDecoder extends ReplayingDecoder<SocksInitRequestDecoder.State> {
   public SocksProtocolVersion version;
   public byte authSchemeNum;
   public List<SocksAuthScheme> authSchemes = new ArrayList<>();
   public static final String name = "SOCKS_INIT_REQUEST_DECODER";
   public SocksRequest msg = SocksCommonUtils.UNKNOWN_SOCKS_REQUEST;

   public static String getName() {
      return "SOCKS_INIT_REQUEST_DECODER";
   }

   public SocksInitRequestDecoder() {
      super(SocksInitRequestDecoder.State.CHECK_PROTOCOL_VERSION);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      switch (this.state()) {
         case CHECK_PROTOCOL_VERSION:
            this.version = SocksProtocolVersion.valueOf(var2.readByte());
            if (this.version != SocksProtocolVersion.SOCKS5) {
               break;
            }

            this.checkpoint(SocksInitRequestDecoder.State.READ_AUTH_SCHEMES);
         case READ_AUTH_SCHEMES:
            this.authSchemes.clear();
            this.authSchemeNum = var2.readByte();

            for (int var4 = 0; var4 < this.authSchemeNum; var4++) {
               this.authSchemes.add(SocksAuthScheme.valueOf(var2.readByte()));
            }

            this.msg = new SocksInitRequest(this.authSchemes);
      }

      var1.pipeline().remove(this);
      var3.add(this.msg);
   }

   public static enum State {
      CHECK_PROTOCOL_VERSION,
      READ_AUTH_SCHEMES;
      // $VF: synthetic field
      public static SocksInitRequestDecoder.State[] $VALUES = new SocksInitRequestDecoder.State[]{
         SocksInitRequestDecoder.State.CHECK_PROTOCOL_VERSION, SocksInitRequestDecoder.State.READ_AUTH_SCHEMES
      };
   }
}
