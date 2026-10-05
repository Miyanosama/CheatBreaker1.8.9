package com.cheatbreaker.client.module.type;

import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockOverlayModule$EnumSwitch {
   public static int[] recoveredField1016 = new int[EnumFacing.values().length];

   static {
      try {
         recoveredField1016[EnumFacing.UP.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         recoveredField1016[EnumFacing.DOWN.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         recoveredField1016[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField1016[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField1016[EnumFacing.SOUTH.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField1016[EnumFacing.WEST.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
