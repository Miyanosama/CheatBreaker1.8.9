package net.minecraft.client.particle;

import net.minecraft.world.World;

public class EntityBubbleFX$Factory implements IParticleFactory {
   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityBubbleFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
