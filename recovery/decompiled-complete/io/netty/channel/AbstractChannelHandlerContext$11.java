package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import java.net.SocketAddress;
import net.minecraft.block.BlockSandStone;
import net.optifine.model.QuadBounds;

public class AbstractChannelHandlerContext$11 extends OneTimeTask {
   public QuadBounds __junk6173104083591587860;
   public BlockSandStone __junk2590395706737283843;

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$1000(this.val$next, this.val$localAddress, this.val$promise);
   }

   public AbstractChannelHandlerContext$11(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2, SocketAddress var3, ChannelPromise var4) {
      this.this$0 = var1;
      this.val$next = var2;
      this.val$localAddress = var3;
      this.val$promise = var4;
      super();
   }
}
