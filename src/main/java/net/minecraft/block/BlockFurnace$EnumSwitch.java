package net.minecraft.block;

import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockFurnace$EnumSwitch {
   public static int[] recoveredField3228 = new int[EnumFacing.values().length];

   static {
      try {
         recoveredField3228[EnumFacing.WEST.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField3228[EnumFacing.EAST.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField3228[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField3228[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
