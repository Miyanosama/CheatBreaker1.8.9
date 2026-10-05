package io.netty.handler.codec.socks;

import com.cheatbreaker.client.ui.overlay.element.RadioVolumeSlider;

import com.cheatbreaker.client.module.staff.XRayModule;
import io.netty.handler.codec.spdy.SpdyFrameDecoder;
import org.slf4j.helpers.NOPMDCAdapter;
import com.cheatbreaker.client.ui.overlay.element.RadioVolumeSlider$EnumSwitch;

public abstract class SocksResponse extends SocksMessage {
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
