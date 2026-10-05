package net.optifine.model;

import io.netty.channel.embedded.EmbeddedChannel$DefaultUnsafe;
import io.netty.channel.sctp.DefaultSctpChannelConfig;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class QuadBounds$1 {
   public EmbeddedChannel$DefaultUnsafe field_0001;
   public DefaultSctpChannelConfig field_0000;
   public GuiScreenResourcePacks field_0002;

   static {
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
