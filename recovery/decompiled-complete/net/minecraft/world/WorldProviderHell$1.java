package net.minecraft.world;

import net.minecraft.util.Cartesian$GetList;
import net.minecraft.world.border.WorldBorder;

public class WorldProviderHell$1 extends WorldBorder {
   public Cartesian$GetList field_0001;

   @Override
   public double getCenterX() {
      return super.getCenterX() / 8.0;
   }

   @Override
   public double getCenterZ() {
      return super.getCenterZ() / 8.0;
   }

   public WorldProviderHell$1(WorldProviderHell var1) {
      this.field_177764_a = var1;
      super();
   }
}
