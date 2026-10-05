package io.netty.bootstrap;

import com.cheatbreaker.client.nethandler.server.PacketVoiceChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$CollectionView;
import java.net.SocketAddress;
import net.minecraft.client.audio.PositionedSoundRecord;

public class AbstractBootstrap$2 implements Runnable {
   public PositionedSoundRecord __junk5775794100341651578;
   public PacketVoiceChannel __junk4971882975718234180;
   public IdleStateEvent __junk5834261566789706550;
   public ConcurrentHashMapV8$CollectionView __junk8845958054206036290;

   @Override
   public void run() {
      if (this.val$regFuture.isSuccess()) {
         this.val$channel.bind(this.val$localAddress, this.val$promise).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
      } else {
         this.val$promise.setFailure(this.val$regFuture.cause());
      }
   }

   public AbstractBootstrap$2(ChannelFuture var1, Channel var2, SocketAddress var3, ChannelPromise var4) {
      this.val$regFuture = var1;
      this.val$channel = var2;
      this.val$localAddress = var3;
      this.val$promise = var4;
      super();
   }
}
