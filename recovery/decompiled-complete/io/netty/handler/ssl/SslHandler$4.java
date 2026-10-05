package io.netty.handler.ssl;

import com.cheatbreaker.client.network.CheatBreakerPingHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.concurrent.ScheduledFuture;
import junit.swingui.TestRunner$12;
import net.minecraft.command.PlayerSelector$6;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.optifine.config.ConnectedParser$1;
import net.optifine.shaders.uniform.ShaderExpressionResolver;

public class SslHandler$4 implements GenericFutureListener<Future<Channel>> {
   public ShaderExpressionResolver __junk928666959397881983;
   public PlayerSelector$6 __junk10851307770305577;
   public GenLayerIsland __junk3929916835750855694;
   public ChannelOutboundHandlerAdapter __junk8407587636180892152;
   public WorldGenGlowStone2 __junk6530287986752152550;
   public ConnectedParser$1 __junk1935340161134037070;
   public CheatBreakerPingHandler __junk8481124595444552300;
   public TestRunner$12 __junk237209715388796646;

   @Override
   public void operationComplete(Future<Channel> var1) {
      if (this.val$timeoutFuture != null) {
         this.val$timeoutFuture.cancel(false);
      }
   }

   public SslHandler$4(SslHandler var1, ScheduledFuture var2) {
      this.this$0 = var1;
      this.val$timeoutFuture = var2;
      super();
   }
}
