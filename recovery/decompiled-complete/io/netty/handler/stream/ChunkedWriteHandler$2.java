package io.netty.handler.stream;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.ssl.NotSslRecordException;
import net.minecraft.client.Minecraft$5;

public class ChunkedWriteHandler$2 implements ChannelFutureListener {
   public NotSslRecordException __junk4351042273223678093;
   public Minecraft$5 __junk3323967351803772023;

   public void operationComplete(ChannelFuture var1) {
      this.val$currentWrite.progress(this.val$amount);
      this.val$currentWrite.success();
      ChunkedWriteHandler.closeInput(this.val$chunks);
   }

   public ChunkedWriteHandler$2(ChunkedWriteHandler var1, ChunkedWriteHandler$PendingWrite var2, int var3, ChunkedInput var4) {
      this.this$0 = var1;
      this.val$currentWrite = var2;
      this.val$amount = var3;
      this.val$chunks = var4;
      super();
   }
}
