package io.netty.channel.nio;

import io.netty.channel.rxtx.RxtxDeviceAddress;
import io.netty.handler.codec.http.multipart.MixedFileUpload;

public class NioEventLoop$1 implements Runnable {
   public MixedFileUpload __junk6381627839140800596;
   public RxtxDeviceAddress __junk7220628926967228610;

   @Override
   public void run() {
      this.this$0.rebuildSelector();
   }

   public NioEventLoop$1(NioEventLoop var1) {
      this.this$0 = var1;
      super();
   }
}
