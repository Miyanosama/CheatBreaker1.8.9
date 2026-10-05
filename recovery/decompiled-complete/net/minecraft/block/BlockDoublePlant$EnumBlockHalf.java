package net.minecraft.block;

import net.minecraft.client.renderer.EntityRenderer$2;
import net.minecraft.tileentity.TileEntityChest$1;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.BiomeEndDecorator;

public enum BlockDoublePlant$EnumBlockHalf implements IStringSerializable {
   LOWER,
   UPPER;
   public BiomeEndDecorator field_0003;
   // $VF: synthetic field
   public static BlockDoublePlant$EnumBlockHalf[] $VALUES = new BlockDoublePlant$EnumBlockHalf[]{
      BlockDoublePlant$EnumBlockHalf.UPPER, BlockDoublePlant$EnumBlockHalf.LOWER
   };
   public TileEntityChest$1 field_0002;
   public EntityRenderer$2 field_0000;

   @Override
   public String getName() {
      return this == UPPER ? "upper" : "lower";
   }

   @Override
   public String toString() {
      return this.getName();
   }
}
