package net.minecraft.client.particle;

import java.util.concurrent.Callable;
import net.minecraft.world.WorldProviderHell;
import recovered.unidentified.UnidentifiedClass4502;

public class EffectRenderer$4 implements Callable<String> {
   public WorldProviderHell field_0003;
   public UnidentifiedClass4502 field_0000;

   public EffectRenderer$4(EffectRenderer var1, int var2) {
      this.field_0001 = var1;
      this.field_0002 = var2;
      super();
   }

   public String method_25078() {
      return this.field_0002 == 0
         ? "MISC_TEXTURE"
         : (this.field_0002 == 1 ? "TERRAIN_TEXTURE" : (this.field_0002 == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + this.field_0002));
   }
}
