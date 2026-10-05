package net.minecraft.client.renderer.chunk;

import com.cheatbreaker.client.CheatBreaker$1;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.optifine.TextureAnimationFrame;

public class ChunkRenderDispatcher$2 implements Runnable {
   public TextureAnimationFrame field_0002;
   public WorldGenReed field_0004;
   public CheatBreaker$1 field_0000;

   @Override
   public void run() {
      ChunkRenderDispatcher.access$000(this.field_0003).remove(this.field_0001);
   }

   public ChunkRenderDispatcher$2(ChunkRenderDispatcher var1, ChunkCompileTaskGenerator var2) {
      this.field_0003 = var1;
      this.field_0001 = var2;
      super();
   }
}
