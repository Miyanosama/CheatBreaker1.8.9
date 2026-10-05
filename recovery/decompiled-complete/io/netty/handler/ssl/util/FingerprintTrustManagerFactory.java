package io.netty.handler.ssl.util;

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PoolArena$DirectArena;
import io.netty.buffer.Unpooled;
import io.netty.handler.stream.ChunkedNioStream;
import io.netty.util.concurrent.FastThreadLocal;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import net.minecraft.client.shader.ShaderGroup;

public class FingerprintTrustManagerFactory extends SimpleTrustManagerFactory {
   public static Pattern FINGERPRINT_PATTERN = Pattern.compile("^[0-9a-fA-F:]+$");
   public TrustManager tm = new FingerprintTrustManagerFactory$2(this);
   public static int SHA1_BYTE_LEN;
   public PoolArena$DirectArena __junk4098791918326542584;
   public ChunkedNioStream __junk5661183730707319819;
   public byte[][] fingerprints;
   public ShaderGroup __junk1900341735442026738;
   public static FastThreadLocal<MessageDigest> tlmd = new FingerprintTrustManagerFactory$1();
   public static int SHA1_HEX_LEN;
   public static Pattern FINGERPRINT_STRIP_PATTERN = Pattern.compile(":");

   public FingerprintTrustManagerFactory(byte[]... var1) {
      if (var1 == null) {
         throw new NullPointerException("fingerprints");
      } else {
         ArrayList var2 = new ArrayList();

         for (byte[] var6 : var1) {
            if (var6 == null) {
               break;
            }

            if (var6.length != 20) {
               throw new IllegalArgumentException("malformed fingerprint: " + ByteBufUtil.hexDump(Unpooled.wrappedBuffer(var6)) + " (expected: SHA1)");
            }

            var2.add(var6.clone());
         }

         this.fingerprints = var2.toArray(new byte[var2.size()][]);
      }
   }

   @Override
   public void engineInit(ManagerFactoryParameters var1) {
   }

   @Override
   public TrustManager[] engineGetTrustManagers() {
      return new TrustManager[]{this.tm};
   }

   public FingerprintTrustManagerFactory(String... var1) {
      this(toFingerprintArray(Arrays.asList(var1)));
   }

   public static byte[][] toFingerprintArray(Iterable<String> var0) {
      if (var0 == null) {
         throw new NullPointerException("fingerprints");
      } else {
         ArrayList var1 = new ArrayList();

         for (String var3 : var0) {
            if (var3 == null) {
               break;
            }

            if (!FINGERPRINT_PATTERN.matcher(var3).matches()) {
               throw new IllegalArgumentException("malformed fingerprint: " + var3);
            }

            var3 = FINGERPRINT_STRIP_PATTERN.matcher(var3).replaceAll("");
            if (var3.length() != 40) {
               throw new IllegalArgumentException("malformed fingerprint: " + var3 + " (expected: SHA1)");
            }

            byte[] var4 = new byte[20];

            for (int var5 = 0; var5 < var4.length; var5++) {
               int var6 = var5 << 1;
               var4[var5] = (byte)Integer.parseInt(var3.substring(var6, var6 + 2), 16);
            }
         }

         return var1.toArray(new byte[var1.size()][]);
      }
   }

   public FingerprintTrustManagerFactory(Iterable<String> var1) {
      this(toFingerprintArray(var1));
   }

   @Override
   public void engineInit(KeyStore var1) {
   }
}
