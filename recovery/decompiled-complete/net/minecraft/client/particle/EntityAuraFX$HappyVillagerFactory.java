package net.minecraft.client.particle;

import net.minecraft.item.Item$5;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class EntityAuraFX$HappyVillagerFactory implements IParticleFactory {
   public StructureVillagePieces field_0000;
   public Item$5 field_0001;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      EntityAuraFX var16 = new EntityAuraFX(var2, var3, var5, var7, var9, var11, var13);
      var16.k(82);
      var16.b(1.0F, 1.0F, 1.0F);
      return var16;
   }
}
