package net.minecraft.network;

import io.netty.util.concurrent.DefaultProgressivePromise;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;

public enum EnumConnectionState$3 {
   public DefaultProgressivePromise field_0000;

   public EnumConnectionState$3(int var3) {
      this.registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketServerQuery.class);
      this.registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketServerInfo.class);
      this.registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketPing.class);
      this.registerPacket(EnumPacketDirection.CLIENTBOUND, S01PacketPong.class);
   }
}
