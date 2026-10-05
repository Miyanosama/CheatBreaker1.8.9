package net.minecraft.client.renderer.chunk;

import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$2;

public class ChunkRenderDispatcher$1 implements Runnable {
   public AbstractNioChannel$AbstractNioUnsafe$2 field_0002;

   public ChunkRenderDispatcher$1(ChunkRenderDispatcher var1, ChunkCompileTaskGenerator var2) {
      this.this$0 = var1;
      this.val$chunkcompiletaskgenerator = var2;
      super();
   }

   @Override
   public void run() {
      ChunkRenderDispatcher.access$000(this.this$0).remove(this.val$chunkcompiletaskgenerator);
   }
}
