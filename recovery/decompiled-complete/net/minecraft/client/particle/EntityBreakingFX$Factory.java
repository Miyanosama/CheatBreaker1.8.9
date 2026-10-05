package net.minecraft.client.particle;

import net.minecraft.item.Item;
import net.minecraft.realms.RealmsSliderButton;
import net.minecraft.world.World;

public class EntityBreakingFX$Factory implements IParticleFactory {
   public RealmsSliderButton field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      int var16 = var15.length > 1 ? var15[1] : 0;
      return new EntityBreakingFX(var2, var3, var5, var7, var9, var11, var13, Item.getItemById(var15[0]), var16);
   }
}
