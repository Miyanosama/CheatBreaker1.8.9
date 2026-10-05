package net.minecraft.client.particle;

import io.netty.util.concurrent.MultithreadEventExecutorGroup$1;
import net.minecraft.command.CommandStats;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass0189;

public class EntitySpellParticleFX$Factory implements IParticleFactory {
   public CommandStats field_0001;
   public MultithreadEventExecutorGroup$1 field_0002;
   public UnidentifiedClass0189 field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntitySpellParticleFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
