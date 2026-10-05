package net.minecraft.client.renderer.block.model;

import junit.awtui.TestRunner$1;
import net.minecraft.block.BlockPumpkin$1;
import net.minecraft.client.renderer.GlStateManager$BooleanState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import recovered.unidentified.UnidentifiedClass0998;

// $VF: synthetic class
public class FaceBakery$1 {
   public TestRunner$1 field_0003;
   public UnidentifiedClass0998 field_0002;
   public BlockPumpkin$1 field_0000;
   public AFUNIXSocketAddress field_0001;
   public GlStateManager$BooleanState field_0006;

   static {
      try {
         $SwitchMap$net$minecraft$util$EnumFacing$Axis[EnumFacing$Axis.X.ordinal()] = 1;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing$Axis[EnumFacing$Axis.Y.ordinal()] = 2;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing$Axis[EnumFacing$Axis.Z.ordinal()] = 3;
      } catch (NoSuchFieldError var7) {
      }

      $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
