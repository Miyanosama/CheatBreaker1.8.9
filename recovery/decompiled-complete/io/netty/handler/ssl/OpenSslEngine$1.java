package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import java.security.Principal;
import java.security.cert.Certificate;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.X509Certificate;
import org.apache.log4j.lf5.viewer.LogFactor5Dialog;
import recovered.unidentified.UnidentifiedClass1350;

public class OpenSslEngine$1 implements SSLSession {
   public UnidentifiedClass1350 __junk5989653236930850905;
   public LogFactor5Dialog __junk6123420892657824898;

   @Override
   public void removeValue(String var1) {
   }

   @Override
   public String getPeerHost() {
      return null;
   }

   @Override
   public int getPeerPort() {
      return 0;
   }

   @Override
   public Certificate[] getPeerCertificates() {
      return OpenSslEngine.access$100();
   }

   @Override
   public Object getValue(String var1) {
      return null;
   }

   @Override
   public Certificate[] getLocalCertificates() {
      return OpenSslEngine.access$100();
   }

   @Override
   public String getProtocol() {
      String var1 = OpenSslEngine.access$400(this.this$0);
      return var1 == null ? "unknown" : "unknown:" + var1;
   }

   @Override
   public String[] getValueNames() {
      return EmptyArrays.EMPTY_STRINGS;
   }

   @Override
   public int getPacketBufferSize() {
      return 18713;
   }

   @Override
   public long getLastAccessedTime() {
      return 6947491074907605349L & -6947491075846045696L;
   }

   public OpenSslEngine$1(OpenSslEngine var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public boolean isValid() {
      return false;
   }

   @Override
   public X509Certificate[] getPeerCertificateChain() {
      return OpenSslEngine.access$200();
   }

   @Override
   public int getApplicationBufferSize() {
      return 16384;
   }

   @Override
   public void invalidate() {
   }

   @Override
   public String getCipherSuite() {
      return OpenSslEngine.access$300(this.this$0);
   }

   @Override
   public Principal getPeerPrincipal() {
      return null;
   }

   @Override
   public void putValue(String var1, Object var2) {
   }

   @Override
   public long getCreationTime() {
      return 1668223591406511169L & 1413496856L;
   }

   @Override
   public Principal getLocalPrincipal() {
      return null;
   }

   @Override
   public byte[] getId() {
      return String.valueOf(OpenSslEngine.access$000(this.this$0)).getBytes();
   }

   @Override
   public SSLSessionContext getSessionContext() {
      return null;
   }
}
