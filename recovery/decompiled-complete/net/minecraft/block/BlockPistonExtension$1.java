package net.minecraft.block;

import com.cheatbreaker.client.nethandler.server.PacketUpdateHologram;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ReduceKeysTask;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockPistonExtension$1 {
   public ConcurrentHashMapV8$ReduceKeysTask field_0002;
   public BlockStandingSign field_0004;
   public PacketUpdateHologram field_0003;
   public EffectRenderer field_0000;

   static {
      try {
         field_177247_a[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_177247_a[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_177247_a[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_177247_a[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_177247_a[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_177247_a[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
