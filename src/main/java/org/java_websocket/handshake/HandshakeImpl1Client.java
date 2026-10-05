package org.java_websocket.handshake;

public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder {
   public String resourceDescriptor = "*";

   @Override
   public String getResourceDescriptor() {
      return this.resourceDescriptor;
   }

   @Override
   public void setResourceDescriptor(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("http resource descriptor must not be null");
      } else {
         this.resourceDescriptor = var1;
      }
   }
}
