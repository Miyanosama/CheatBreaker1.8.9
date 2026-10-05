package net.minecraft.world.gen.structure;

import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import net.minecraft.client.renderer.vertex.VertexFormat$1;
import net.optifine.shaders.SimpleShaderTexture;

public abstract class StructureVillagePieces$Road extends StructureVillagePieces$Village {
   public VertexFormat$1 field_0002;
   public SimpleShaderTexture field_0000;
   public GlobalTrafficShapingHandler field_0001;

   public StructureVillagePieces$Road() {
   }

   public StructureVillagePieces$Road(StructureVillagePieces$Start var1, int var2) {
      super(var1, var2);
   }
}
