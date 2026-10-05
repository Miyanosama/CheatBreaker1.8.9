package io.netty.handler.codec.socks;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;

public abstract class SocksRequest extends SocksMessage {
   public SocksRequestType requestType;

   public SocksRequest(SocksRequestType var1) {
      super(SocksMessageType.REQUEST);
      if (var1 == null) {
         throw new NullPointerException("requestType");
      } else {
         this.requestType = var1;
      }
   }

   public SocksRequestType requestType() {
      return this.requestType;
   }
}
