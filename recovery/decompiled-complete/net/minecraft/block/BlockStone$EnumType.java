package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
import net.minecraft.item.crafting.RecipesIngots;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.StringUtils;

public enum BlockStone$EnumType implements IStringSerializable {
   GRANITE_SMOOTH(2, MapColor.dirtColor, "smooth_granite", "graniteSmooth"),
   ANDESITE_SMOOTH(6, MapColor.stoneColor, "smooth_andesite", "andesiteSmooth"),
   DIORITE_SMOOTH(4, MapColor.quartzColor, "smooth_diorite", "dioriteSmooth"),
   ANDESITE(5, MapColor.stoneColor, "andesite"),
   STONE(0, MapColor.stoneColor, "stone"),
   GRANITE(1, MapColor.dirtColor, "granite"),
   DIORITE(3, MapColor.quartzColor, "diorite");

   public StringUtils field_0011;
   public String name;
   public MapColor field_181073_l;
   // $VF: synthetic field
   public static BlockStone$EnumType[] $VALUES = new BlockStone$EnumType[]{
      BlockStone$EnumType.STONE, BlockStone$EnumType.GRANITE, GRANITE_SMOOTH, BlockStone$EnumType.DIORITE, DIORITE_SMOOTH, ANDESITE, ANDESITE_SMOOTH
   };
   public String unlocalizedName;
   public RecipesIngots field_0000;
   public static BlockStone$EnumType[] META_LOOKUP = new BlockStone$EnumType[values().length];
   public LayerSnowmanHead field_0004;
   public int meta;

   public static BlockStone$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   static {
      for (BlockStone$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public BlockStone$EnumType(int var3, MapColor var4, String var5, String var6) {
      this.meta = var3;
      this.name = var5;
      this.unlocalizedName = var6;
      this.field_181073_l = var4;
   }

   public BlockStone$EnumType(int var3, MapColor var4, String var5) {
      this(var3, var4, var5, var5);
   }

   public MapColor func_181072_c() {
      return this.field_181073_l;
   }

   @Override
   public String toString() {
      return this.name;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public int getMetadata() {
      return this.meta;
   }
}
