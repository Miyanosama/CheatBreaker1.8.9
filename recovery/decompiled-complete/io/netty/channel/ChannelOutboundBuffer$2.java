package io.netty.channel;

import java.nio.channels.ClosedChannelException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.LayerIIIDecoder$III_side_info_t;
import junit.runner.StandardTestSuiteLoader;
import recovered.unidentified.UnidentifiedClass3810;

public class ChannelOutboundBuffer$2 implements Runnable {
   public StandardTestSuiteLoader __junk4572763539816298784;
   public LayerIIIDecoder$III_side_info_t __junk1994790665672879213;
   public UnidentifiedClass3810 __junk3183366351671374820;
   public Header __junk6196835085182671335;

   public ChannelOutboundBuffer$2(ChannelOutboundBuffer var1, ClosedChannelException var2) {
      this.this$0 = var1;
      this.val$cause = var2;
      super();
   }

   @Override
   public void run() {
      this.this$0.close(this.val$cause);
   }
}
