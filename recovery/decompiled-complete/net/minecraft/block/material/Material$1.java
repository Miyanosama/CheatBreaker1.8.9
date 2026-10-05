package net.minecraft.block.material;

import net.minecraft.block.BlockAir;
import net.minecraft.item.crafting.RecipesBanners;

public class Material$1 extends Material {
   public RecipesBanners field_0000;
   public BlockAir field_0001;

   @Override
   public boolean blocksMovement() {
      return false;
   }

   public Material$1(MapColor var1) {
      super(var1);
   }
}
