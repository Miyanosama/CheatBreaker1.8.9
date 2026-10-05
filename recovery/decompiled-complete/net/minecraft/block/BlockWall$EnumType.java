package net.minecraft.block;

import net.minecraft.client.gui.stream.GuiStreamUnavailable$Reason;
import net.minecraft.util.IStringSerializable;

public enum BlockWall$EnumType implements IStringSerializable {
   NORMAL(0, "cobblestone", "normal"),
   MOSSY(1, "mossy_cobblestone", "mossy");

   // $VF: synthetic field
   public static BlockWall$EnumType[] $VALUES = new BlockWall$EnumType[]{NORMAL, BlockWall$EnumType.MOSSY};
   public static BlockWall$EnumType[] META_LOOKUP = new BlockWall$EnumType[values().length];
   public String unlocalizedName;
   public String name;
   public GuiStreamUnavailable$Reason field_0001;
   public int meta;

   public int getMetadata() {
      return this.meta;
   }

   static {
      for (BlockWall$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public BlockWall$EnumType(int var3, String var4, String var5) {
      this.meta = var3;
      this.name = var4;
      this.unlocalizedName = var5;
   }

   public static BlockWall$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   @Override
   public String getName() {
      return this.name;
   }
}
