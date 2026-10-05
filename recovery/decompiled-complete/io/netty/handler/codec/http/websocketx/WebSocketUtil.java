package io.netty.handler.codec.http.websocketx;

import com.cheatbreaker.client.util.LegacyClientCrashReporter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.codec.rtsp.RtspHeaders$Values;
import io.netty.util.CharsetUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import net.optifine.BlockPosM;
import net.optifine.shaders.uniform.ShaderParameterFloat;

public class WebSocketUtil {
   public ShaderParameterFloat __junk8533014410847524533;
   public RtspHeaders$Values __junk1133569811345205921;
   public LegacyClientCrashReporter __junk6711069741311937144;
   public BlockPosM __junk8619183481430931769;

   public static String base64(byte[] var0) {
      ByteBuf var1 = Unpooled.wrappedBuffer(var0);
      ByteBuf var2 = Base64.encode(var1);
      String var3 = var2.toString(CharsetUtil.UTF_8);
      var2.release();
      return var3;
   }

   public static byte[] md5(byte[] var0) {
      try {
         MessageDigest var1 = MessageDigest.getInstance("MD5");
         return var1.digest(var0);
      } catch (NoSuchAlgorithmException var2) {
         throw new InternalError("MD5 not supported on this platform - Outdated?");
      }
   }

   public static int randomNumber(int var0, int var1) {
      return (int)(Math.random() * var1 + var0);
   }

   public static byte[] randomBytes(int var0) {
      byte[] var1 = new byte[var0];

      for (int var2 = 0; var2 < var0; var2++) {
         var1[var2] = (byte)randomNumber(0, 255);
      }

      return var1;
   }

   public static byte[] sha1(byte[] var0) {
      try {
         MessageDigest var1 = MessageDigest.getInstance("SHA1");
         return var1.digest(var0);
      } catch (NoSuchAlgorithmException var2) {
         throw new InternalError("SHA-1 is not supported on this platform - Outdated?");
      }
   }
}
