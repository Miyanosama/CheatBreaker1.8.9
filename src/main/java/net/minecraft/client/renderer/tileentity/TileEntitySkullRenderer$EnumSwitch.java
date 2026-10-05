package net.minecraft.client.renderer.tileentity;

import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class TileEntitySkullRenderer$EnumSwitch {
   public static int[] recoveredField2283 = new int[EnumFacing.values().length];

   static {
      try {
         recoveredField2283[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField2283[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField2283[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField2283[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
