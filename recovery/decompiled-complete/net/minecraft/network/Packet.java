package net.minecraft.network;

public interface Packet<T extends INetHandler> {
   void processPacket(T var1);

   void readPacketData(PacketBuffer var1);

   void writePacketData(PacketBuffer var1);
}
