package net.minecraft.client.particle;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapEntry;
import net.minecraft.world.World;
import net.optifine.shaders.HFNoiseTexture;

public class EntitySpellParticleFX$MobFactory implements IParticleFactory {
   public ConcurrentHashMapV8$MapEntry field_0000;
   public HFNoiseTexture field_0001;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      EntitySpellParticleFX var16 = new EntitySpellParticleFX(var2, var3, var5, var7, var9, var11, var13);
      var16.b((float)var9, (float)var11, (float)var13);
      return var16;
   }
}
