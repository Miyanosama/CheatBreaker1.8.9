package net.minecraft.client.particle;

import net.minecraft.client.gui.GuiClickableScrolledSelectionListProxy;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityBreakingFX$SnowballFactory implements IParticleFactory {
   public GuiClickableScrolledSelectionListProxy field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityBreakingFX(var2, var3, var5, var7, Items.snowball);
   }
}
