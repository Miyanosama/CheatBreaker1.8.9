package io.netty.channel.nio;

import io.netty.channel.ChannelPromise;
import io.netty.channel.ConnectTimeoutException;
import io.netty.util.internal.OneTimeTask;
import java.net.SocketAddress;
import javax.vecmath.Point3d;
import net.minecraft.block.state.pattern.BlockHelper;

public class AbstractNioChannel$AbstractNioUnsafe$1 extends OneTimeTask {
   public Point3d __junk7953340582254058975;
   public BlockHelper __junk8434406240070661216;

   @Override
   public void run() {
      ChannelPromise var1 = AbstractNioChannel.access$000(this.this$1.this$0);
      ConnectTimeoutException var2 = new ConnectTimeoutException("connection timed out: " + this.val$remoteAddress);
      if (var1 != null && var1.tryFailure(var2)) {
         this.this$1.close(this.this$1.voidPromise());
      }
   }

   public AbstractNioChannel$AbstractNioUnsafe$1(AbstractNioChannel$AbstractNioUnsafe var1, SocketAddress var2) {
      this.this$1 = var1;
      this.val$remoteAddress = var2;
      super();
   }
}
