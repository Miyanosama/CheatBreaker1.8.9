package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import java.net.SocketAddress;
import javax.vecmath.Point2i;
import net.minecraft.tileentity.TileEntityEndPortal;

public class AbstractChannelHandlerContext$12 extends OneTimeTask {
   public TileEntityEndPortal __junk8047057151519711802;
   public Point2i __junk73827012070371563;
   public ChannelOption __junk8007159532892427490;

   public AbstractChannelHandlerContext$12(
      AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2, SocketAddress var3, SocketAddress var4, ChannelPromise var5
   ) {
      this.this$0 = var1;
      this.val$next = var2;
      this.val$remoteAddress = var3;
      this.val$localAddress = var4;
      this.val$promise = var5;
      super();
   }

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$1100(this.val$next, this.val$remoteAddress, this.val$localAddress, this.val$promise);
   }
}
