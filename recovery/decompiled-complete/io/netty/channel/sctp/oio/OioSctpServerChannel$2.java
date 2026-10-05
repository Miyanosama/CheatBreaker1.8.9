package io.netty.channel.sctp.oio;

import io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import net.minecraft.entity.Entity$3;
import net.minecraft.tileentity.TileEntityFlowerPot;

public class OioSctpServerChannel$2 implements Runnable {
   public TileEntityFlowerPot __junk3039952707551328293;
   public Entity$3 __junk7973556985702927108;

   @Override
   public void run() {
      this.this$0.unbindAddress(this.val$localAddress, this.val$promise);
   }

   public OioSctpServerChannel$2(OioSctpServerChannel var1, InetAddress var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$localAddress = var2;
      this.val$promise = var3;
      super();
   }
}
