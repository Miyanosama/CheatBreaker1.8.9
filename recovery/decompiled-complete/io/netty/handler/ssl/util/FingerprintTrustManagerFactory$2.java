package io.netty.handler.ssl.util;

import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachEntryTask;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.X509TrustManager;
import net.minecraft.realms.RealmsLevelSummary;
import recovered.unidentified.UnidentifiedClass3460;

public class FingerprintTrustManagerFactory$2 implements X509TrustManager {
   public RealmsLevelSummary __junk7991087468150872862;
   public UnidentifiedClass3460 __junk2572460394819860904;
   public ConcurrentHashMapV8$ForEachEntryTask __junk5002719450019282978;

   @Override
   public void checkClientTrusted(X509Certificate[] var1, String var2) {
      this.checkTrusted("client", var1);
   }

   public FingerprintTrustManagerFactory$2(FingerprintTrustManagerFactory var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void checkServerTrusted(X509Certificate[] var1, String var2) {
      this.checkTrusted("server", var1);
   }

   public void checkTrusted(String var1, X509Certificate[] var2) {
      X509Certificate var3 = var2[0];
      byte[] var4 = this.fingerprint(var3);
      boolean var5 = false;

      for (byte[] var9 : FingerprintTrustManagerFactory.access$000(this.this$0)) {
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

   public byte[] fingerprint(X509Certificate var1) {
      MessageDigest var2 = (MessageDigest)FingerprintTrustManagerFactory.access$100().get();
      var2.reset();
      return var2.digest(var1.getEncoded());
   }
}
