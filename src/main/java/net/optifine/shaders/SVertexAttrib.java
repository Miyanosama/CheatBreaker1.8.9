package net.optifine.shaders;

import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class SVertexAttrib {
   public int index;
   public int offset;
   public VertexFormatElement.EnumType type;
   public int count;

   public SVertexAttrib(int var1, int var2, VertexFormatElement.EnumType var3) {
      this.index = var1;
      this.count = var2;
      this.type = var3;
   }
}
