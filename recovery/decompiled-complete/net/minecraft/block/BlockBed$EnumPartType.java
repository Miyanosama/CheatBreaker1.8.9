package net.minecraft.block;

import io.netty.channel.epoll.EpollSocketChannelConfig;
import io.netty.handler.codec.spdy.DefaultSpdyGoAwayFrame;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.util.IStringSerializable;

public enum BlockBed$EnumPartType implements IStringSerializable {
   FOOT("foot"),
   HEAD("head");

   public EpollSocketChannelConfig field_0003;
   public S1BPacketEntityAttach field_0005;
   public DefaultSpdyGoAwayFrame field_0004;
   public String name;

   @Override
   public String getName() {
      return this.name;
   }

   public BlockBed$EnumPartType(String var3) {
      this.name = var3;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
