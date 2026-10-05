package net.minecraft.client.renderer;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ReduceKeysTask;
import net.minecraft.block.BlockStem$1;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockModelRenderer$1 {
   public S07PacketRespawn field_0001;
   public ConcurrentHashMapV8$ReduceKeysTask field_0000;
   public BlockStem$1 field_0002;

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
