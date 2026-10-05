package net.minecraft.tileentity;

import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class TileEntityPiston$EnumSwitch {
   public static int[] recoveredField160 = new int[EnumFacing.Axis.values().length];

   static {
      try {
         recoveredField160[EnumFacing.Axis.X.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField160[EnumFacing.Axis.Y.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField160[EnumFacing.Axis.Z.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
