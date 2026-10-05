package net.minecraft.client.renderer.chunk;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.item.ItemNameTag;
import net.minecraft.util.EnumWorldBlockLayer;

public class ChunkRenderDispatcher$3 implements Runnable {
   public ItemNameTag field_0003;

   public ChunkRenderDispatcher$3(ChunkRenderDispatcher var1, EnumWorldBlockLayer var2, WorldRenderer var3, RenderChunk var4, CompiledChunk var5) {
      this.this$0 = var1;
      this.val$p_178503_1_ = var2;
      this.val$p_178503_2_ = var3;
      this.val$p_178503_3_ = var4;
      this.val$p_178503_4_ = var5;
      super();
   }

   @Override
   public void run() {
      this.this$0.uploadChunk(this.val$p_178503_1_, this.val$p_178503_2_, this.val$p_178503_3_, this.val$p_178503_4_);
   }
}
