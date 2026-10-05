package io.netty.handler.ssl;

import io.netty.buffer.AbstractReferenceCountedByteBuf;
import java.security.Principal;
import java.security.cert.Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.X509Certificate;

public class JettyNpnSslSession implements SSLSession {
   public SSLEngine engine;
   public AbstractReferenceCountedByteBuf __junk6231026717970605408;
   public volatile String applicationProtocol;

   public SSLSession unwrap() {
      return this.engine.getSession();
   }

   @Override
   public int getApplicationBufferSize() {
      return this.unwrap().getApplicationBufferSize();
   }

   public void setApplicationProtocol(String var1) {
      if (var1 != null) {
         var1 = var1.replace(':', '_');
      }

      this.applicationProtocol = var1;
   }

   @Override
   public int getPacketBufferSize() {
      return this.unwrap().getPacketBufferSize();
   }

   @Override
   public long getLastAccessedTime() {
      return this.unwrap().getLastAccessedTime();
   }

   @Override
   public String getPeerHost() {
      return this.unwrap().getPeerHost();
   }

   @Override
   public void putValue(String var1, Object var2) {
      this.unwrap().putValue(var1, var2);
   }

   @Override
   public Certificate[] getLocalCertificates() {
      return this.unwrap().getLocalCertificates();
   }

   @Override
   public int getPeerPort() {
      return this.unwrap().getPeerPort();
   }

   @Override
   public Object getValue(String var1) {
      return this.unwrap().getValue(var1);
   }

   public JettyNpnSslSession(SSLEngine var1) {
      this.engine = var1;
   }

   @Override
   public X509Certificate[] getPeerCertificateChain() {
      return this.unwrap().getPeerCertificateChain();
   }

   @Override
   public void removeValue(String var1) {
      this.unwrap().removeValue(var1);
   }

   @Override
   public boolean isValid() {
      return this.unwrap().isValid();
   }

   @Override
   public void invalidate() {
      this.unwrap().invalidate();
   }

   @Override
   public long getCreationTime() {
      return this.unwrap().getCreationTime();
   }

   @Override
   public Principal getLocalPrincipal() {
      return this.unwrap().getLocalPrincipal();
   }

   @Override
   public Certificate[] getPeerCertificates() {
      return this.unwrap().getPeerCertificates();
   }

   @Override
   public Principal getPeerPrincipal() {
      return this.unwrap().getPeerPrincipal();
   }

   @Override
   public String[] getValueNames() {
      return this.unwrap().getValueNames();
   }

   @Override
   public String getProtocol() {
      String var1 = this.unwrap().getProtocol();
      String var2 = this.applicationProtocol;
      if (var2 == null) {
         return var1 != null ? var1.replace(':', '_') : null;
      } else {
         StringBuilder var3 = new StringBuilder(32);
         if (var1 != null) {
            var3.append(var1.replace(':', '_'));
            var3.append(':');
         } else {
            var3.append("null:");
         }

         var3.append(var2);
         return var3.toString();
      }
   }

   @Override
   public byte[] getId() {
      return this.unwrap().getId();
   }

   @Override
   public String getCipherSuite() {
      return this.unwrap().getCipherSuite();
   }

   @Override
   public SSLSessionContext getSessionContext() {
      return this.unwrap().getSessionContext();
   }
}
