package net.minecraft.network.status.client;

import net.minecraft.block.BlockSlime;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;
import net.minecraft.util.Tuple;
import recovered.unidentified.UnidentifiedClass0189;
import recovered.unidentified.UnidentifiedClass0313;

public class C01PacketPing implements Packet<INetHandlerStatusServer> {
   public BlockSlime field_0002;
   public UnidentifiedClass0313 field_0004;
   public UnidentifiedClass0189 field_0001;
   public Tuple field_0003;
   public long clientTime;

   public C01PacketPing() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.clientTime = var1.readLong();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeLong(this.clientTime);
   }

   public long getClientTime() {
      return this.clientTime;
   }

   public void processPacket(INetHandlerStatusServer var1) {
      var1.processPing(this);
   }

   public C01PacketPing(long var1) {
      this.clientTime = var1;
   }
}
