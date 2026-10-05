package net.minecraft.client.particle;

import io.netty.handler.codec.marshalling.LimitingByteInput$TooBigObjectException;
import net.minecraft.world.World;

public class EntitySpellParticleFX$AmbientMobFactory implements IParticleFactory {
   public LimitingByteInput$TooBigObjectException field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      EntitySpellParticleFX var16 = new EntitySpellParticleFX(var2, var3, var5, var7, var9, var11, var13);
      var16.i(0.15F);
      var16.b((float)var9, (float)var11, (float)var13);
      return var16;
   }
}
