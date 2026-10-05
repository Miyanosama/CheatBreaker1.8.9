package net.minecraft.block;

import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockLadder$EnumSwitch {
   public static int[] recoveredField2855 = new int[EnumFacing.values().length];

   static {
      try {
         recoveredField2855[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField2855[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField2855[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField2855[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
