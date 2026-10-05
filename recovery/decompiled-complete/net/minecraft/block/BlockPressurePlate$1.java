package net.minecraft.block;

import io.netty.channel.oio.OioEventLoopGroup;
import net.minecraft.world.WorldProvider;

// $VF: synthetic class
public class BlockPressurePlate$1 {
   public WorldProvider field_0001;
   public OioEventLoopGroup field_0002;

   static {
      try {
         field_0000[BlockPressurePlate$Sensitivity.EVERYTHING.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0000[BlockPressurePlate$Sensitivity.MOBS.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
