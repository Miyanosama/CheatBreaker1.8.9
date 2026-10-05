package net.minecraft.network.play.server;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S3APacketTabComplete implements Packet<INetHandlerPlayClient> {
   public EntityLightningBolt field_0000;
   public String[] matches;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.matches.length);

      for (String var5 : this.matches) {
         var1.writeString(var5);
      }
   }

   public S3APacketTabComplete() {
   }

   public String[] func_149630_c() {
      return this.matches;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.matches = new String[var1.readVarIntFromBuffer()];

      for (int var2 = 0; var2 < this.matches.length; var2++) {
         this.matches[var2] = var1.readStringFromBuffer(32767);
      }
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleTabComplete(this);
   }

   public S3APacketTabComplete(String[] var1) {
      this.matches = var1;
   }
}
