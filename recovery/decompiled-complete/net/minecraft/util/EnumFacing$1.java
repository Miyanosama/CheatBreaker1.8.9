package net.minecraft.util;

import com.jagrosh.discordipc.entities.Packet$OpCode;
import io.netty.channel.group.ChannelMatchers$InvertMatcher;
import io.netty.util.internal.MpscLinkedQueue$1;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity$1;

// $VF: synthetic class
public class EnumFacing$1 {
   public MpscLinkedQueue$1 field_0003;
   public BlockLiquid field_0006;
   public Entity$1 field_0002;
   public Packet$OpCode field_0005;
   public ChannelMatchers$InvertMatcher field_0001;

   static {
      try {
         $SwitchMap$net$minecraft$util$EnumFacing$Plane[EnumFacing$Plane.HORIZONTAL.ordinal()] = 1;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing$Plane[EnumFacing$Plane.VERTICAL.ordinal()] = 2;
      } catch (NoSuchFieldError var10) {
      }

      $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 2;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.UP.ordinal()] = 5;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.DOWN.ordinal()] = 6;
      } catch (NoSuchFieldError var4) {
      }

      $SwitchMap$net$minecraft$util$EnumFacing$Axis = new int[EnumFacing$Axis.values().length];

      try {
         $SwitchMap$net$minecraft$util$EnumFacing$Axis[EnumFacing$Axis.X.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing$Axis[EnumFacing$Axis.Y.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing$Axis[EnumFacing$Axis.Z.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
