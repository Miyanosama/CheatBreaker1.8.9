package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$6;

public enum BlockStoneBrick$EnumType implements IStringSerializable {
   CRACKED(2, "cracked_stonebrick", "cracked"),
   DEFAULT(0, "stonebrick", "default"),
   MOSSY(1, "mossy_stonebrick", "mossy"),
   CHISELED(3, "chiseled_stonebrick", "chiseled");
   public int meta;
   public String unlocalizedName;
   public BlockBanner$BlockBannerStanding field_0002;
   public static BlockStoneBrick$EnumType[] META_LOOKUP = new BlockStoneBrick$EnumType[values().length];
   public CategoryNodeEditor$6 field_0006;
   public String name;

   static {
      for (BlockStoneBrick$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public static BlockStoneBrick$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   @Override
   public String getName() {
      return this.name;
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public BlockStoneBrick$EnumType(int var3, String var4, String var5) {
      this.meta = var3;
      this.name = var4;
      this.unlocalizedName = var5;
   }

   public int getMetadata() {
      return this.meta;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
