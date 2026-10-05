package net.optifine.shaders;

import com.cheatbreaker.client.ui.overlay.OverlayGui;
import net.minecraft.client.resources.model.WeightedBakedModel;

public class MultiTexID {
   public OverlayGui field_0002;
   public int base;
   public int norm;
   public int spec;
   public WeightedBakedModel field_0000;

   public MultiTexID(int var1, int var2, int var3) {
      this.base = var1;
      this.norm = var2;
      this.spec = var3;
   }
}
