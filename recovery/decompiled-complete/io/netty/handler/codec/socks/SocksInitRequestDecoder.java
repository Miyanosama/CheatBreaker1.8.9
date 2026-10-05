package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.AxisAngle4f;
import net.minecraft.client.renderer.GlStateManager$StencilFunc;
import net.minecraft.util.EnumFacing$AxisDirection;
import net.optifine.config.VillagerProfession;
import org.apache.log4j.chainsaw.ControlPanel$4;
import org.json.JSONTokener;

public class SocksInitRequestDecoder extends ReplayingDecoder<SocksInitRequestDecoder$State> {
   public JSONTokener __junk217344006802249462;
   public SocksProtocolVersion version;
   public EnumFacing$AxisDirection __junk6704065162563898786;
   public byte authSchemeNum;
   public AxisAngle4f __junk5375756519920522420;
   public GlStateManager$StencilFunc __junk8470984903506080314;
   public List<SocksAuthScheme> authSchemes = new ArrayList<>();
   public ControlPanel$4 __junk123973634902615221;
   public static String name;
   public VillagerProfession __junk3593861768929679091;
   public SocksRequest msg = SocksCommonUtils.UNKNOWN_SOCKS_REQUEST;

   public static String getName() {
      return "SOCKS_INIT_REQUEST_DECODER";
   }

   public SocksInitRequestDecoder() {
      super(SocksInitRequestDecoder$State.CHECK_PROTOCOL_VERSION);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      switch (SocksInitRequestDecoder$1.$SwitchMap$io$netty$handler$codec$socks$SocksInitRequestDecoder$State[this.state().ordinal()]) {
         case 1:
            this.version = SocksProtocolVersion.valueOf(var2.readByte());
            if (this.version != SocksProtocolVersion.SOCKS5) {
               break;
            }

            this.checkpoint(SocksInitRequestDecoder$State.READ_AUTH_SCHEMES);
         case 2:
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
}
