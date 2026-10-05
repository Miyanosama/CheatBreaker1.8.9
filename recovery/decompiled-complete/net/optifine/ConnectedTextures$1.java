package net.optifine;

import io.netty.util.Recycler$1;
import io.netty.util.ResourceLeakException;
import net.minecraft.client.Minecraft$18;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.util.EnumFacing;
import net.optifine.reflect.FieldLocatorName;

// $VF: synthetic class
public class ConnectedTextures$1 {
   public ResourceLeakException field_0005;
   public Recycler$1 field_0002;
   public C12PacketUpdateSign field_0004;
   public Minecraft$18 field_0000;
   public FieldLocatorName field_0001;

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
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
