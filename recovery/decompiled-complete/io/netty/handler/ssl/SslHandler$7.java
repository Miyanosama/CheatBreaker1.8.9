package io.netty.handler.ssl;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import java.util.concurrent.ScheduledFuture;
import javazoom.jl.decoder.LayerIDecoder$SubbandLayer1Stereo;
import net.optifine.shaders.config.ShaderOptionSwitch;
import org.java_websocket.SocketChannelIOHelper;

public class SslHandler$7 implements ChannelFutureListener {
   public SocketChannelIOHelper __junk6461740480775026697;
   public LayerIDecoder$SubbandLayer1Stereo __junk2153340300367374958;
   public ShaderOptionSwitch __junk440590539777969001;

   public SslHandler$7(SslHandler var1, ScheduledFuture var2, ChannelHandlerContext var3, ChannelPromise var4) {
      this.this$0 = var1;
      this.val$timeoutFuture = var2;
      this.val$ctx = var3;
      this.val$promise = var4;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (this.val$timeoutFuture != null) {
         this.val$timeoutFuture.cancel(false);
      }

      this.val$ctx.close(this.val$promise);
   }
}
