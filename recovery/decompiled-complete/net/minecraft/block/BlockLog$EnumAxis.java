package net.minecraft.block;

import net.minecraft.client.resources.data.IMetadataSerializer$1;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.util.IStringSerializable;
import org.apache.log4j.helpers.SyslogWriter;

public enum BlockLog$EnumAxis implements IStringSerializable {
   Y("y"),
   NONE("none"),
   Z("z"),
   X("x");
   public String name;
   public IMetadataSerializer$1 field_0002;
   // $VF: synthetic field
   public static BlockLog$EnumAxis[] $VALUES = new BlockLog$EnumAxis[]{BlockLog$EnumAxis.X, Y, BlockLog$EnumAxis.Z, BlockLog$EnumAxis.NONE};
   public SyslogWriter field_0000;

   @Override
   public String getName() {
      return this.name;
   }

   public BlockLog$EnumAxis(String var3) {
      this.name = var3;
   }

   public static BlockLog$EnumAxis fromFacingAxis(EnumFacing$Axis var0) {
      switch (BlockLog$1.field_180167_a[var0.ordinal()]) {
         case 1:
            return X;
         case 2:
            return Y;
         case 3:
            return Z;
         default:
            return NONE;
      }
   }

   @Override
   public String toString() {
      return this.name;
   }
}
