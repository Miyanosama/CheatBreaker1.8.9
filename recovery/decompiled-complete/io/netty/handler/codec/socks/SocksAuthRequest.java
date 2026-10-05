package io.netty.handler.codec.socks;

import com.jagrosh.discordipc.IPCClient$1;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import java.nio.charset.CharsetEncoder;
import javax.vecmath.Matrix4f;
import junit.swingui.FailureRunView$1;
import net.minecraft.block.BlockLever$1;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog$3;

public class SocksAuthRequest extends SocksRequest {
   public Matrix4f __junk2192146433126443014;
   public String password;
   public static CharsetEncoder asciiEncoder = CharsetUtil.getEncoder(CharsetUtil.US_ASCII);
   public BlockLever$1 __junk6255196339594803315;
   public String username;
   public LogFactor5InputDialog$3 __junk5704790318647882489;
   public IPCClient$1 __junk1547504510054972855;
   public FailureRunView$1 __junk2341136425567047981;
   public static SocksSubnegotiationVersion SUBNEGOTIATION_VERSION = SocksSubnegotiationVersion.AUTH_PASSWORD;

   public String password() {
      return this.password;
   }

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
      var1.writeByte(SUBNEGOTIATION_VERSION.byteValue());
      var1.writeByte(this.username.length());
      var1.writeBytes(this.username.getBytes(CharsetUtil.US_ASCII));
      var1.writeByte(this.password.length());
      var1.writeBytes(this.password.getBytes(CharsetUtil.US_ASCII));
   }

   public String username() {
      return this.username;
   }

   public SocksAuthRequest(String var1, String var2) {
      super(SocksRequestType.AUTH);
      if (var1 == null) {
         throw new NullPointerException("username");
      } else if (var2 == null) {
         throw new NullPointerException("username");
      } else if (asciiEncoder.canEncode(var1) && asciiEncoder.canEncode(var2)) {
         if (var1.length() > 255) {
            throw new IllegalArgumentException(var1 + " exceeds 255 char limit");
         } else if (var2.length() > 255) {
            throw new IllegalArgumentException(var2 + " exceeds 255 char limit");
         } else {
            this.username = var1;
            this.password = var2;
         }
      } else {
         throw new IllegalArgumentException(" username: " + var1 + " or password: " + var2 + " values should be in pure ascii");
      }
   }
}
