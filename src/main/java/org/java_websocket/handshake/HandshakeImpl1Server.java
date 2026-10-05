package org.java_websocket.handshake;

public class HandshakeImpl1Server extends HandshakedataImpl1 implements ServerHandshakeBuilder {
   public String httpstatusmessage;
   public short httpstatus;

   @Override
   public void setHttpStatusMessage(String var1) {
      this.httpstatusmessage = var1;
   }

   @Override
   public void setHttpStatus(short var1) {
      this.httpstatus = var1;
   }

   @Override
   public String getHttpStatusMessage() {
      return this.httpstatusmessage;
   }

   @Override
   public short getHttpStatus() {
      return this.httpstatus;
   }
}
