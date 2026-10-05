package io.netty.handler.codec.socks;

import net.minecraft.client.renderer.GlStateManager$StencilState;
import net.minecraft.util.EnumFacing$Plane;

public abstract class SocksRequest extends SocksMessage {
   public GlStateManager$StencilState __junk1039309058686653786;
   public EnumFacing$Plane __junk7958633270420697885;
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
