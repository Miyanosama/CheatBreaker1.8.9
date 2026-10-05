package io.netty.bootstrap;

import com.cheatbreaker.client.websocket.server.WSPacketFriendStatusUpdate;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelFutureListener$1;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.compression.ZlibUtil$1;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory$1;
import java.net.SocketAddress;
import net.minecraft.client.renderer.GlStateManager$ColorMaterialState;

public class AbstractBootstrap$1 implements ChannelFutureListener {
   public InsecureTrustManagerFactory$1 __junk2840626272974545412;
   public WSPacketFriendStatusUpdate __junk2800328317640874659;
   public ZlibUtil$1 __junk8269114906170720707;
   public GlStateManager$ColorMaterialState __junk5934755131679391710;
   public ChannelFutureListener$1 __junk4743239577685371355;

   public AbstractBootstrap$1(AbstractBootstrap var1, ChannelFuture var2, Channel var3, SocketAddress var4, ChannelPromise var5) {
      this.this$0 = var1;
      this.val$regFuture = var2;
      this.val$channel = var3;
      this.val$localAddress = var4;
      this.val$promise = var5;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      AbstractBootstrap.access$100(this.val$regFuture, this.val$channel, this.val$localAddress, this.val$promise);
   }
}
