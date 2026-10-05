package net.minecraft.client.particle;

import junit.swingui.TestTreeModel;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.realms.RealmsButton;
import net.minecraft.world.World;

public class EntityEnchantmentTableParticleFX$EnchantmentTable implements IParticleFactory {
   public ShaderLinkHelper field_0001;
   public RealmsButton field_0003;
   public TestTreeModel field_0000;
   public ModelSheep1 field_0002;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityEnchantmentTableParticleFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
