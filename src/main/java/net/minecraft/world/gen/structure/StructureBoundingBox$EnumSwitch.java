package net.minecraft.world.gen.structure;

import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class StructureBoundingBox$EnumSwitch {
   public static int[] recoveredField3790 = new int[EnumFacing.values().length];

   static {
      try {
         recoveredField3790[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField3790[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField3790[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField3790[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
