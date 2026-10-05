package net.minecraft.client.renderer.block.model;

import java.util.Arrays;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class BreakingFour extends BakedQuad {
   public TextureAtlasSprite texture;

   public void remapVert(int var1) {
      int var2 = this.vertexData.length / 4;
      int var3 = var2 * var1;
      float var4 = Float.intBitsToFloat(this.vertexData[var3]);
      float var5 = Float.intBitsToFloat(this.vertexData[var3 + 1]);
      float var6 = Float.intBitsToFloat(this.vertexData[var3 + 2]);
      float var7 = 0.0F;
      float var8 = 0.0F;
      switch (this.face) {
         case DOWN:
            var7 = var4 * 16.0F;
            var8 = (1.0F - var6) * 16.0F;
            break;
         case UP:
            var7 = var4 * 16.0F;
            var8 = var6 * 16.0F;
            break;
         case NORTH:
            var7 = (1.0F - var4) * 16.0F;
            var8 = (1.0F - var5) * 16.0F;
            break;
         case SOUTH:
            var7 = var4 * 16.0F;
            var8 = (1.0F - var5) * 16.0F;
            break;
         case WEST:
            var7 = var6 * 16.0F;
            var8 = (1.0F - var5) * 16.0F;
            break;
         case EAST:
            var7 = (1.0F - var6) * 16.0F;
            var8 = (1.0F - var5) * 16.0F;
      }

      this.vertexData[var3 + 4] = Float.floatToRawIntBits(this.texture.getInterpolatedU(var7));
      this.vertexData[var3 + 4 + 1] = Float.floatToRawIntBits(this.texture.getInterpolatedV(var8));
   }

   public BreakingFour(BakedQuad var1, TextureAtlasSprite var2) {
      super(Arrays.copyOf(var1.getVertexData(), var1.getVertexData().length), var1.tintIndex, FaceBakery.getFacingFromVertexData(var1.getVertexData()));
      this.texture = var2;
      this.remapQuad();
      this.fixVertexData();
   }

   public void remapQuad() {
      for (int var1 = 0; var1 < 4; var1++) {
         this.remapVert(var1);
      }
   }
}
