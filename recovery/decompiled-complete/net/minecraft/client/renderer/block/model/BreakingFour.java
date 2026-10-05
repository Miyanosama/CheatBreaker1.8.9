package net.minecraft.client.renderer.block.model;

import io.netty.util.HashedWheelTimer$HashedWheelBucket;
import java.util.Arrays;
import javazoom.jl.player.JavaSoundAudioDevice;
import net.minecraft.client.Minecraft$11;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class BreakingFour extends BakedQuad {
   public JavaSoundAudioDevice field_0001;
   public TextureAtlasSprite texture;
   public Minecraft$11 field_0002;
   public HashedWheelTimer$HashedWheelBucket field_0003;

   public void remapVert(int var1) {
      int var2 = this.vertexData.length / 4;
      int var3 = var2 * var1;
      float var4 = Float.intBitsToFloat(this.vertexData[var3]);
      float var5 = Float.intBitsToFloat(this.vertexData[var3 + 1]);
      float var6 = Float.intBitsToFloat(this.vertexData[var3 + 2]);
      float var7 = 0.0F;
      float var8 = 0.0F;
      switch (BreakingFour$1.$SwitchMap$net$minecraft$util$EnumFacing[this.face.ordinal()]) {
         case 1:
            var7 = var4 * 16.0F;
            var8 = (1.0F - var6) * 16.0F;
            break;
         case 2:
            var7 = var4 * 16.0F;
            var8 = var6 * 16.0F;
            break;
         case 3:
            var7 = (1.0F - var4) * 16.0F;
            var8 = (1.0F - var5) * 16.0F;
            break;
         case 4:
            var7 = var4 * 16.0F;
            var8 = (1.0F - var5) * 16.0F;
            break;
         case 5:
            var7 = var6 * 16.0F;
            var8 = (1.0F - var5) * 16.0F;
            break;
         case 6:
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
