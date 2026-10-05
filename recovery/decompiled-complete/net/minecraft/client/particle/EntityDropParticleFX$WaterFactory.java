package net.minecraft.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.network.NetworkSystem$3;
import net.minecraft.world.World;
import net.optifine.expr.FunctionType;

public class EntityDropParticleFX$WaterFactory implements IParticleFactory {
   public FunctionType field_0000;
   public NetworkSystem$3 field_0001;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityDropParticleFX(var2, var3, var5, var7, Material.water);
   }
}
