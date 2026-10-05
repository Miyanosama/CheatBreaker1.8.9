package net.minecraftforge.client.model.pipeline;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;

public interface IVertexConsumer {
   void put(int var1, float... var2);

   VertexFormat getVertexFormat();

   void setQuadColored();

   void setQuadTint(int var1);

   void setQuadOrientation(EnumFacing var1);
}
