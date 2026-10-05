package io.netty.handler.ssl.util;

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PoolArena;
import io.netty.buffer.Unpooled;
import io.netty.handler.stream.ChunkedNioStream;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.item.ItemPotion;
import net.minecraft.realms.RealmsLevelSummary;
import com.cheatbreaker.client.emote.type.FlossEmote$EnumSwitch;

public class FingerprintTrustManagerFactory extends SimpleTrustManagerFactory {
   public static final int SHA1_BYTE_LEN = 20;
   public TrustManager tm = new X509TrustManager() {

      @Override
      public void checkClientTrusted(X509Certificate[] var1, String var2) throws java.security.cert.CertificateException {
         this.checkTrusted("client", var1);
      }

      @Override
      public void checkServerTrusted(X509Certificate[] var1, String var2) throws java.security.cert.CertificateException {
         this.checkTrusted("server", var1);
      }

      public void checkTrusted(String var1, X509Certificate[] var2) throws java.security.cert.CertificateException {
         X509Certificate var3 = var2[0];
         byte[] var4 = this.fingerprint(var3);
         boolean var5 = false;

         for (byte[] var9 : FingerprintTrustManagerFactory.this.fingerprints) {
            if (Arrays.equals(var4, var9)) {
               var5 = true;
               break;
            }
         }

         if (!var5) {
            throw new CertificateException(var1 + " certificate with unknown fingerprint: " + var3.getSubjectDN());
         }
      }

      @Override
      public X509Certificate[] getAcceptedIssuers() {
         return EmptyArrays.EMPTY_X509_CERTIFICATES;
      }

      public byte[] fingerprint(X509Certificate var1) throws java.security.cert.CertificateEncodingException {
         MessageDigest var2 = FingerprintTrustManagerFactory.tlmd.get();
         var2.reset();
         return var2.digest(var1.getEncoded());
      }
   };
   public static final int SHA1_HEX_LEN = 40;
   public byte[][] fingerprints;
   public static Pattern FINGERPRINT_PATTERN = Pattern.compile("^[0-9a-fA-F:]+$");
   public static Pattern FINGERPRINT_STRIP_PATTERN = Pattern.compile(":");
   public static FastThreadLocal<MessageDigest> tlmd = new FastThreadLocal<MessageDigest>() {

      public MessageDigest initialValue() {
         try {
            return MessageDigest.getInstance("SHA1");
         } catch (NoSuchAlgorithmException var2) {
            throw new Error(var2);
         }
      }
   };

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

         this.fingerprints = (byte[][])var2.toArray(new byte[var2.size()][]);
      }
   }

   @Override
   public void engineInit(ManagerFactoryParameters var1) throws java.lang.Exception {
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

         return (byte[][])var1.toArray(new byte[var1.size()][]);
      }
   }

   public FingerprintTrustManagerFactory(Iterable<String> var1) {
      this(toFingerprintArray(var1));
   }

   @Override
   public void engineInit(KeyStore var1) throws java.lang.Exception {
   }
}
