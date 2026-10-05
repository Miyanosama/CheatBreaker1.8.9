package net.minecraft.client.renderer;

import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.SmartAnimations;

public abstract class ChunkRenderContainer {
   public List<RenderChunk> renderChunks = Lists.newArrayListWithCapacity(17424);
   public BitSet animatedSpritesRendered;
   public double viewEntityY;
   public boolean initialized;
   public double viewEntityZ;
   public double viewEntityX;
   public BitSet animatedSpritesCached = new BitSet();

   public void preRenderChunk(RenderChunk var1) {
      BlockPos var2 = var1.getPosition();
      GlStateManager.translate((float)(var2.getX() - this.viewEntityX), (float)(var2.getY() - this.viewEntityY), (float)(var2.getZ() - this.viewEntityZ));
   }

   public void addRenderChunk(RenderChunk var1, EnumWorldBlockLayer var2) {
      this.renderChunks.add(var1);
      if (this.animatedSpritesRendered != null) {
         BitSet var3 = var1.compiledChunk.getAnimatedSprites(var2);
         if (var3 != null) {
            this.animatedSpritesRendered.or(var3);
         }
      }
   }

   public void a(double var1, double var3, double var5) {
      this.initialized = true;
      this.renderChunks.clear();
      this.viewEntityX = var1;
      this.viewEntityY = var3;
      this.viewEntityZ = var5;
      if (SmartAnimations.isActive()) {
         if (this.animatedSpritesRendered != null) {
            SmartAnimations.spritesRendered(this.animatedSpritesRendered);
         } else {
            this.animatedSpritesRendered = this.animatedSpritesCached;
         }

         this.animatedSpritesRendered.clear();
      } else if (this.animatedSpritesRendered != null) {
         SmartAnimations.spritesRendered(this.animatedSpritesRendered);
         this.animatedSpritesRendered = null;
      }
   }

   public abstract void renderChunkLayer(EnumWorldBlockLayer var1);
}
