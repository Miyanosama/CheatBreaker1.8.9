package io.netty.handler.codec.compression;

import io.netty.channel.AbstractChannelHandlerContext$5;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.apache.log4j.chainsaw.MyTableModel;

public class JZlibEncoder$2 implements ChannelFutureListener {
   public MyTableModel __junk6901088146806283922;
   public AbstractChannelHandlerContext$5 __junk2283153317729002004;

   public JZlibEncoder$2(JZlibEncoder var1, ChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$ctx = var2;
      this.val$promise = var3;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      this.val$ctx.close(this.val$promise);
   }
}
