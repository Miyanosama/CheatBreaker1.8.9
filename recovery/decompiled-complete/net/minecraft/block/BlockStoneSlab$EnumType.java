package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.util.IStringSerializable;

public enum BlockStoneSlab$EnumType implements IStringSerializable {
   SAND(1, MapColor.sandColor, "sandstone", "sand"),
   QUARTZ(7, MapColor.quartzColor, "quartz"),
   BRICK(4, MapColor.redColor, "brick"),
   SMOOTHBRICK(5, MapColor.stoneColor, "stone_brick", "smoothStoneBrick"),
   NETHERBRICK(6, MapColor.netherrackColor, "nether_brick", "netherBrick"),
   WOOD(2, MapColor.woodColor, "wood_old", "wood"),
   STONE(0, MapColor.stoneColor, "stone"),
   COBBLESTONE(3, MapColor.stoneColor, "cobblestone", "cobble");

   public ItemEnderPearl field_0006;
   public int meta;
   // $VF: synthetic field
   public static BlockStoneSlab$EnumType[] $VALUES = new BlockStoneSlab$EnumType[]{
      BlockStoneSlab$EnumType.STONE,
      SAND,
      BlockStoneSlab$EnumType.WOOD,
      BlockStoneSlab$EnumType.COBBLESTONE,
      BlockStoneSlab$EnumType.BRICK,
      BlockStoneSlab$EnumType.SMOOTHBRICK,
      BlockStoneSlab$EnumType.NETHERBRICK,
      QUARTZ
   };
   public String unlocalizedName;
   public MapColor field_181075_k;
   public static BlockStoneSlab$EnumType[] META_LOOKUP = new BlockStoneSlab$EnumType[values().length];
   public String name;

   static {
      for (BlockStoneSlab$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public static BlockStoneSlab$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public BlockStoneSlab$EnumType(int var3, MapColor var4, String var5, String var6) {
      this.meta = var3;
      this.field_181075_k = var4;
      this.name = var5;
      this.unlocalizedName = var6;
   }

   public int getMetadata() {
      return this.meta;
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public MapColor func_181074_c() {
      return this.field_181075_k;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public BlockStoneSlab$EnumType(int var3, MapColor var4, String var5) {
      this(var3, var4, var5, var5);
   }
}
