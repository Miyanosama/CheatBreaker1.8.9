package net.minecraft.block;

import io.netty.channel.AbstractChannelHandlerContext$12;
import net.minecraft.util.IStringSerializable;

public enum BlockPrismarine$EnumType implements IStringSerializable {
   BRICKS(1, "prismarine_bricks", "bricks"),
   DARK(2, "dark_prismarine", "dark"),
   ROUGH(0, "prismarine", "rough");
   public String name;
   public String unlocalizedName;
   public AbstractChannelHandlerContext$12 field_0006;
   public static BlockPrismarine$EnumType[] META_LOOKUP = new BlockPrismarine$EnumType[values().length];
   // $VF: synthetic field
   public static BlockPrismarine$EnumType[] $VALUES = new BlockPrismarine$EnumType[]{BlockPrismarine$EnumType.ROUGH, BRICKS, DARK};
   public int meta;

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public BlockPrismarine$EnumType(int var3, String var4, String var5) {
      this.meta = var3;
      this.name = var4;
      this.unlocalizedName = var5;
   }

   @Override
   public String toString() {
      return this.name;
   }

   @Override
   public String getName() {
      return this.name;
   }

   static {
      for (BlockPrismarine$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public int getMetadata() {
      return this.meta;
   }

   public static BlockPrismarine$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }
}
