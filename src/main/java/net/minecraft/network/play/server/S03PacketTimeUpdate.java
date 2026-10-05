package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S03PacketTimeUpdate implements Packet<INetHandlerPlayClient> {
   public long worldTime;
   public long totalWorldTime;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleTimeUpdate(this);
   }

   public long getWorldTime() {
      return this.worldTime;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.totalWorldTime = var1.readLong();
      this.worldTime = var1.readLong();
   }

   public S03PacketTimeUpdate(long var1, long var3, boolean var5) {
      this.totalWorldTime = var1;
      this.worldTime = var3;
      if (!var5) {
         this.worldTime = -this.worldTime;
         if (this.worldTime == 0L) {
            this.worldTime = -1L;
         }
      }
   }

   public S03PacketTimeUpdate() {
   }

   public long getTotalWorldTime() {
      return this.totalWorldTime;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeLong(this.totalWorldTime);
      var1.writeLong(this.worldTime);
   }
}
