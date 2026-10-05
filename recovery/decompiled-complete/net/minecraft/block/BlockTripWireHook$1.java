package net.minecraft.block;

import net.minecraft.client.renderer.GlStateManager$ColorMask;
import net.minecraft.util.EnumFacing;
import org.apache.log4j.lf5.viewer.LogFactor5LoadingDialog;

// $VF: synthetic class
public class BlockTripWireHook$1 {
   public GlStateManager$ColorMask field_0003;
   public BlockRailBase$1 field_0000;
   public LogFactor5LoadingDialog field_0002;

   static {
      try {
         field_177056_a[EnumFacing.EAST.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_177056_a[EnumFacing.WEST.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_177056_a[EnumFacing.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_177056_a[EnumFacing.NORTH.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
