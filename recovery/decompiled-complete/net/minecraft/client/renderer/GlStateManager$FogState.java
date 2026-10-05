package net.minecraft.client.renderer;

import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.resources.data.IMetadataSerializer;

public class GlStateManager$FogState {
   public GlStateManager$BooleanState fog = new GlStateManager$BooleanState(2912);
   public float start;
   public IMetadataSerializer field_0002;
   public float end;
   public float density;
   public ModelWitch field_0001;
   public int mode = 2048;

   public GlStateManager$FogState() {
      this.density = 1.0F;
      this.start = 0.0F;
      this.end = 1.0F;
   }
}
