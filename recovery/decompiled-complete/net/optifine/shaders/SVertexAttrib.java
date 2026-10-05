package net.optifine.shaders;

import junit.swingui.TestHierarchyRunView$1;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumType;
import net.minecraft.entity.EntityList$EntityEggInfo;

public class SVertexAttrib {
   public int index;
   public int offset;
   public VertexFormatElement$EnumType type;
   public EntityList$EntityEggInfo field_0004;
   public int count;
   public TestHierarchyRunView$1 field_0001;

   public SVertexAttrib(int var1, int var2, VertexFormatElement$EnumType var3) {
      this.index = var1;
      this.count = var2;
      this.type = var3;
   }
}
