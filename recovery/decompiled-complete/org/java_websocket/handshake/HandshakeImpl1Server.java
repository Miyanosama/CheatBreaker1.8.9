package org.java_websocket.handshake;

import io.netty.handler.ssl.JettyNpnSslSession;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import org.slf4j.helpers.Util$1;

public class HandshakeImpl1Server extends HandshakedataImpl1 implements ServerHandshakeBuilder {
   public JettyNpnSslSession field_0003;
   public RangedAttribute field_0005;
   public String httpstatusmessage;
   public RenderBat field_0004;
   public short httpstatus;
   public Util$1 field_0001;

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
