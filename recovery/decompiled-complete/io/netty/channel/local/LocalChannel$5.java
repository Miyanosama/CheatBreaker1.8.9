package io.netty.channel.local;

import io.netty.channel.ChannelPipeline;
import java.util.Collections;

public class LocalChannel$5 implements Runnable {
   public LocalChannel$5(LocalChannel var1, LocalChannel var2, Object[] var3, ChannelPipeline var4) {
      this.this$0 = var1;
      this.val$peer = var2;
      this.val$msgsCopy = var3;
      this.val$peerPipeline = var4;
      super();
   }

   @Override
   public void run() {
      Collections.addAll(LocalChannel.access$000(this.val$peer), this.val$msgsCopy);
      LocalChannel.access$400(this.val$peer, this.val$peerPipeline);
   }
}
