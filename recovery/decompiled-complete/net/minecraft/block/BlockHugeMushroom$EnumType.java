package net.minecraft.block;

import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.util.IStringSerializable;

public enum BlockHugeMushroom$EnumType implements IStringSerializable {
   SOUTH_EAST(9, "south_east"),
   NORTH_WEST(1, "north_west"),
   STEM(10, "stem"),
   CENTER(5, "center"),
   SOUTH(8, "south"),
   EAST(6, "east"),
   ALL_INSIDE(0, "all_inside"),
   NORTH(2, "north"),
   WEST(4, "west"),
   ALL_OUTSIDE(14, "all_outside"),
   SOUTH_WEST(7, "south_west"),
   ALL_STEM(15, "all_stem"),
   NORTH_EAST(3, "north_east");

   public String name;
   // $VF: synthetic field
   public static BlockHugeMushroom$EnumType[] $VALUES = new BlockHugeMushroom$EnumType[]{
      NORTH_WEST,
      BlockHugeMushroom$EnumType.NORTH,
      BlockHugeMushroom$EnumType.NORTH_EAST,
      BlockHugeMushroom$EnumType.WEST,
      CENTER,
      EAST,
      BlockHugeMushroom$EnumType.SOUTH_WEST,
      SOUTH,
      SOUTH_EAST,
      STEM,
      BlockHugeMushroom$EnumType.ALL_INSIDE,
      BlockHugeMushroom$EnumType.ALL_OUTSIDE,
      BlockHugeMushroom$EnumType.ALL_STEM
   };
   public EntityNoteFX field_0018;
   public BlockStone$EnumType field_0001;
   public int meta;
   public static BlockHugeMushroom$EnumType[] META_LOOKUP = new BlockHugeMushroom$EnumType[16];

   @Override
   public String toString() {
      return this.name;
   }

   public BlockHugeMushroom$EnumType(int var3, String var4) {
      this.meta = var3;
      this.name = var4;
   }

   public static BlockHugeMushroom$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      BlockHugeMushroom$EnumType var1 = META_LOOKUP[var0];
      return var1 == null ? META_LOOKUP[0] : var1;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public int getMetadata() {
      return this.meta;
   }

   static {
      for (BlockHugeMushroom$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }
}
