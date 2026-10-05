package net.minecraft.block;

import com.cheatbreaker.client.nethandler.shared.PacketAddWaypoint;
import io.netty.channel.socket.nio.NioServerSocketChannel$NioServerSocketChannelConfig;
import io.netty.channel.socket.oio.OioSocketChannel;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockFarmland$1 {
   public CommandTeleport field_0002;
   public NioServerSocketChannel$NioServerSocketChannelConfig field_0004;
   public PacketAddWaypoint field_0001;
   public OioSocketChannel field_0003;

   static {
      try {
         field_181625_a[EnumFacing.UP.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_181625_a[EnumFacing.NORTH.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_181625_a[EnumFacing.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_181625_a[EnumFacing.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_181625_a[EnumFacing.EAST.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
