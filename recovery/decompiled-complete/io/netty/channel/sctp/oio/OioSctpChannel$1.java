package io.netty.channel.sctp.oio;

import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.cors.CorsConfig;
import java.net.InetAddress;
import org.apache.log4j.lf5.viewer.categoryexplorer.TreeModelAdapter;

public class OioSctpChannel$1 implements Runnable {
   public TreeModelAdapter __junk5322032910695113570;
   public CorsConfig __junk7283273492771894604;

   public OioSctpChannel$1(OioSctpChannel var1, InetAddress var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$localAddress = var2;
      this.val$promise = var3;
      super();
   }

   @Override
   public void run() {
      this.this$0.bindAddress(this.val$localAddress, this.val$promise);
   }
}
