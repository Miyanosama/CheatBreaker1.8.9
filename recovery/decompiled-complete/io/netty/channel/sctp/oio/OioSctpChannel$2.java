package io.netty.channel.sctp.oio;

import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.DefaultPromise$5;
import java.net.InetAddress;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import recovered.unidentified.UnidentifiedClass0525;

public class OioSctpChannel$2 implements Runnable {
   public DefaultPromise$5 __junk3131961367933682901;
   public UnidentifiedClass0525 __junk9057517866146270713;
   public EntityAIFindEntityNearestPlayer __junk6403265171475624586;

   @Override
   public void run() {
      this.this$0.unbindAddress(this.val$localAddress, this.val$promise);
   }

   public OioSctpChannel$2(OioSctpChannel var1, InetAddress var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$localAddress = var2;
      this.val$promise = var3;
      super();
   }
}
