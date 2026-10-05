package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.util.IStringSerializable;

public enum BlockStoneSlabNew$EnumType implements IStringSerializable {
   RED_SANDSTONE(0, "red_sandstone", BlockSand$EnumType.RED_SAND.getMapColor());
   public MapColor field_181069_e;
   // $VF: synthetic field
   public static BlockStoneSlabNew$EnumType[] $VALUES = new BlockStoneSlabNew$EnumType[]{BlockStoneSlabNew$EnumType.RED_SANDSTONE};
   public String name;
   public int meta;
   public ModelArmorStandArmor field_0000;
   public static BlockStoneSlabNew$EnumType[] META_LOOKUP = new BlockStoneSlabNew$EnumType[values().length];

   public BlockStoneSlabNew$EnumType(int var3, String var4, MapColor var5) {
      this.meta = var3;
      this.name = var4;
      this.field_181069_e = var5;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public String getUnlocalizedName() {
      return this.name;
   }

   public static BlockStoneSlabNew$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public MapColor func_181068_c() {
      return this.field_181069_e;
   }

   static {
      for (BlockStoneSlabNew$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public int getMetadata() {
      return this.meta;
   }

   @Override
   public String getName() {
      return this.name;
   }
}
