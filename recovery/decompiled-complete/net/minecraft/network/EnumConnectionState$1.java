package net.minecraft.network;

import net.minecraft.network.handshake.client.C00Handshake;

public enum EnumConnectionState$1 {
   public EnumConnectionState$1(int var3) {
      this.registerPacket(EnumPacketDirection.SERVERBOUND, C00Handshake.class);
   }
}
