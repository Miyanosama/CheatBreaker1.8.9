package net.minecraft.world.gen.layer;

import net.minecraft.client.particle.EntitySpellParticleFX$Factory;
import org.apache.log4j.lf5.util.DateFormatManager;

public class GenLayerFuzzyZoom extends GenLayerZoom {
   public EntitySpellParticleFX$Factory field_0000;
   public DateFormatManager field_0001;

   public GenLayerFuzzyZoom(long var1, GenLayer var3) {
      super(var1, var3);
   }

   @Override
   public int b(int var1, int var2, int var3, int var4) {
      return this.selectRandom(var1, var2, var3, var4);
   }
}
