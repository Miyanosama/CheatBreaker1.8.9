package io.netty.handler.codec.socks;

import com.cheatbreaker.client.module.staff.XRayModule;
import io.netty.handler.codec.spdy.SpdyFrameDecoder$State;
import org.slf4j.helpers.NOPMDCAdapter;
import recovered.unidentified.UnidentifiedClass3368;

public abstract class SocksResponse extends SocksMessage {
   public XRayModule __junk2932575318653648587;
   public SpdyFrameDecoder$State __junk6606640242220108229;
   public UnidentifiedClass3368 __junk3084544871017900276;
   public NOPMDCAdapter __junk5935334662350409902;
   public SocksResponseType responseType;

   public SocksResponseType responseType() {
      return this.responseType;
   }

   public SocksResponse(SocksResponseType var1) {
      super(SocksMessageType.RESPONSE);
      if (var1 == null) {
         throw new NullPointerException("responseType");
      } else {
         this.responseType = var1;
      }
   }
}
