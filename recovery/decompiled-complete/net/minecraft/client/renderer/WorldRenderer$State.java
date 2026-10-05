package net.minecraft.client.renderer;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$ZDoubleRoomFitHelper;

public class WorldRenderer$State {
   public VertexFormat stateVertexFormat;
   public StructureOceanMonumentPieces$ZDoubleRoomFitHelper field_0001;
   public TextureAtlasSprite[] stateQuadSprites;
   public int[] stateRawBuffer;

   public WorldRenderer$State(WorldRenderer var1, int[] var2, VertexFormat var3) {
      this.this$0 = var1;
      super();
      this.stateRawBuffer = var2;
      this.stateVertexFormat = var3;
   }

   public int getVertexCount() {
      return this.stateRawBuffer.length / this.stateVertexFormat.getIntegerSize();
   }

   public int[] getRawBuffer() {
      return this.stateRawBuffer;
   }

   public WorldRenderer$State(WorldRenderer var1, int[] var2, VertexFormat var3, TextureAtlasSprite[] var4) {
      this.this$0 = var1;
      super();
      this.stateRawBuffer = var2;
      this.stateVertexFormat = var3;
      this.stateQuadSprites = var4;
   }

   public VertexFormat getVertexFormat() {
      return this.stateVertexFormat;
   }
}
