package io.netty.handler.codec.compression;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelPromiseNotifier;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$TreeNode;
import junit.swingui.TestRunner$1;
import net.minecraft.client.renderer.GlStateManager$Color;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.boss.BossStatus;
import net.optifine.http.HttpPipelineReceiver;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$29;

public class JZlibEncoder$1 implements Runnable {
   public BossStatus __junk8803862770790506347;
   public TestRunner$1 __junk554958363047515499;
   public LogBrokerMonitor$29 __junk8757113809512985518;
   public EntityHanging __junk763283050132276253;
   public HttpPipelineReceiver __junk1934971961168347850;
   public GlStateManager$Color __junk2362108454405388593;
   public ConcurrentHashMapV8$TreeNode __junk1949178942120140430;

   public JZlibEncoder$1(JZlibEncoder var1, ChannelPromise var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$p = var2;
      this.val$promise = var3;
      super();
   }

   @Override
   public void run() {
      ChannelFuture var1 = JZlibEncoder.access$100(this.this$0, JZlibEncoder.access$000(this.this$0), this.val$p);
      var1.addListener(new ChannelPromiseNotifier(this.val$promise));
   }
}
