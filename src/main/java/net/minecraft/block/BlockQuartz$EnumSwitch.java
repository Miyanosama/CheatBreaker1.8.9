package net.minecraft.block;

import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockQuartz$EnumSwitch {
   public static int[] recoveredField1826 = new int[EnumFacing.Axis.values().length];

   static {
      try {
         recoveredField1826[EnumFacing.Axis.Z.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField1826[EnumFacing.Axis.X.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField1826[EnumFacing.Axis.Y.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
