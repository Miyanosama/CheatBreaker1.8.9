package io.netty.handler.stream;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import junit.runner.BaseTestRunner;
import net.minecraft.world.ChunkCache;
import net.optifine.shaders.CustomTextureRaw$1;

public class ChunkedWriteHandler$4 implements ChannelFutureListener {
   public BaseTestRunner __junk1828576791893493111;
   public ChunkCache __junk4188516741463598935;
   public CustomTextureRaw$1 __junk8479231318976916650;

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         ChunkedWriteHandler.closeInput((ChunkedInput<?>)this.val$pendingMessage);
         this.val$currentWrite.fail(var1.cause());
      } else {
         this.val$currentWrite.progress(this.val$amount);
         if (this.val$channel.isWritable()) {
            this.this$0.resumeTransfer();
         }
      }
   }

   public ChunkedWriteHandler$4(ChunkedWriteHandler var1, Object var2, ChunkedWriteHandler$PendingWrite var3, int var4, Channel var5) {
      this.this$0 = var1;
      this.val$pendingMessage = var2;
      this.val$currentWrite = var3;
      this.val$amount = var4;
      this.val$channel = var5;
      super();
   }
}
