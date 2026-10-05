package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.WorldChunkManager;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.viewer.LF5SwingUtils$1;

public enum BlockSand$EnumType implements IStringSerializable {
   RED_SAND(1, "red_sand", "red", MapColor.adobeColor),
   SAND(0, "sand", "default", MapColor.sandColor);

   public LogRecord field_0005;
   public WorldChunkManager field_0009;
   public LF5SwingUtils$1 field_0004;
   public String unlocalizedName;
   public ModelEnderCrystal field_0001;
   public int meta;
   public static BlockSand$EnumType[] META_LOOKUP = new BlockSand$EnumType[values().length];
   // $VF: synthetic field
   public static BlockSand$EnumType[] $VALUES = new BlockSand$EnumType[]{BlockSand$EnumType.SAND, BlockSand$EnumType.RED_SAND};
   public MapColor mapColor;
   public String name;

   @Override
   public String getName() {
      return this.name;
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public static BlockSand$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   @Override
   public String toString() {
      return this.name;
   }

   public BlockSand$EnumType(int var3, String var4, String var5, MapColor var6) {
      this.meta = var3;
      this.name = var4;
      this.mapColor = var6;
      this.unlocalizedName = var5;
   }

   static {
      for (BlockSand$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public int getMetadata() {
      return this.meta;
   }
}
