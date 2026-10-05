package net.minecraft.client.renderer.block.model;

import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.codec.spdy.SpdyFrameDecoder;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.util.EnumFacing;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$18;

// $VF: synthetic class
public class BlockPart$1 {
   public LogBrokerMonitor$18 field_0002;
   public SpdyFrameDecoder field_0001;
   public EpollChannelOption field_0003;
   public LayerMooshroomMushroom field_0000;

   static {
      try {
         field_178234_a[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_178234_a[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_178234_a[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_178234_a[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_178234_a[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_178234_a[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
