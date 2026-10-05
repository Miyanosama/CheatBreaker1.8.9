package io.netty.handler.ssl;

import org.apache.tomcat.jni.SSLContext;

public class OpenSslSessionStats {
   public JdkSslClientContext __junk2777064501180011730;
   public long context;

   public long accept() {
      return SSLContext.sessionAccept(this.context);
   }

   public long acceptGood() {
      return SSLContext.sessionAcceptGood(this.context);
   }

   public long number() {
      return SSLContext.sessionNumber(this.context);
   }

   public OpenSslSessionStats(long var1) {
      this.context = var1;
   }

   public long misses() {
      return SSLContext.sessionMisses(this.context);
   }

   public long hits() {
      return SSLContext.sessionHits(this.context);
   }

   public long acceptRenegotiate() {
      return SSLContext.sessionAcceptRenegotiate(this.context);
   }

   public long connectRenegotiate() {
      return SSLContext.sessionConnectRenegotiate(this.context);
   }

   public long cacheFull() {
      return SSLContext.sessionCacheFull(this.context);
   }

   public long timeouts() {
      return SSLContext.sessionTimeouts(this.context);
   }

   public long connectGood() {
      return SSLContext.sessionConnectGood(this.context);
   }

   public long cbHits() {
      return SSLContext.sessionCbHits(this.context);
   }

   public long connect() {
      return SSLContext.sessionConnect(this.context);
   }
}
