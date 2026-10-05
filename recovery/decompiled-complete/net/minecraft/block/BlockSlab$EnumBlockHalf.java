package net.minecraft.block;

import junit.extensions.TestSetup$1;
import net.minecraft.util.IStringSerializable;

public enum BlockSlab$EnumBlockHalf implements IStringSerializable {
   BOTTOM("bottom"),
   TOP("top");

   // $VF: synthetic field
   public static BlockSlab$EnumBlockHalf[] $VALUES = new BlockSlab$EnumBlockHalf[]{BlockSlab$EnumBlockHalf.TOP, BlockSlab$EnumBlockHalf.BOTTOM};
   public TestSetup$1 field_0005;
   public String name;
   public BlockQuartz$EnumType field_0001;

   @Override
   public String toString() {
      return this.name;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public BlockSlab$EnumBlockHalf(String var3) {
      this.name = var3;
   }
}
