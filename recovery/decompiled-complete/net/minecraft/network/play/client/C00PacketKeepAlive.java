package net.minecraft.network.play.client;

import io.netty.util.concurrent.AbstractEventExecutor;
import net.minecraft.client.resources.ResourcePackRepository$3;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C00PacketKeepAlive implements Packet<INetHandlerPlayServer> {
   public ResourcePackRepository$3 field_0001;
   public int key;
   public AbstractEventExecutor field_0000;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.key);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.key = var1.readVarIntFromBuffer();
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processKeepAlive(this);
   }

   public int getKey() {
      return this.key;
   }

   public C00PacketKeepAlive() {
   }

   public C00PacketKeepAlive(int var1) {
      this.key = var1;
   }
}
