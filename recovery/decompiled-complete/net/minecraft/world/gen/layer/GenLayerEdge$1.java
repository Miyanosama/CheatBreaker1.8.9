package net.minecraft.world.gen.layer;

import io.netty.channel.DefaultChannelPipeline$1;
import io.netty.channel.nio.NioEventLoopGroup;

// $VF: synthetic class
public class GenLayerEdge$1 {
   public NioEventLoopGroup field_0001;
   public DefaultChannelPipeline$1 field_0002;

   static {
      try {
         field_151642_a[GenLayerEdge$Mode.COOL_WARM.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_151642_a[GenLayerEdge$Mode.HEAT_ICE.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_151642_a[GenLayerEdge$Mode.SPECIAL.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
