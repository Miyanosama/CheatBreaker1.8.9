package net.minecraft.block;

import io.netty.buffer.ByteBufProcessor$6;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockTorch$2 {
   public ByteBufProcessor$6 field_0001;
   public BlockDispenser field_0003;
   public S00PacketKeepAlive field_0000;

   static {
      try {
         field_176609_a[EnumFacing.EAST.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_176609_a[EnumFacing.WEST.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_176609_a[EnumFacing.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_176609_a[EnumFacing.NORTH.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_176609_a[EnumFacing.DOWN.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_176609_a[EnumFacing.UP.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
