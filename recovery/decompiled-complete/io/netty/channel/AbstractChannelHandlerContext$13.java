package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.play.client.C07PacketPlayerDigging$Action;
import net.minecraft.pathfinding.PathNavigate;

public class AbstractChannelHandlerContext$13 extends OneTimeTask {
   public NetworkPlayerInfo __junk2556120022786802585;
   public C07PacketPlayerDigging$Action __junk156898752325918490;
   public PathNavigate __junk5748027929854943478;

   public AbstractChannelHandlerContext$13(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$next = var2;
      this.val$promise = var3;
      super();
   }

   @Override
   public void run() {
      if (!this.this$0.channel().metadata().hasDisconnect()) {
         AbstractChannelHandlerContext.access$1200(this.val$next, this.val$promise);
      } else {
         AbstractChannelHandlerContext.access$1300(this.val$next, this.val$promise);
      }
   }
}
