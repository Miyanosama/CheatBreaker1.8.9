package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScoreObjective;

public class S3DPacketDisplayScoreboard implements Packet<INetHandlerPlayClient> {
   public String scoreName;
   public int position;

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.position = var1.readByte();
      this.scoreName = var1.readStringFromBuffer(16);
   }

   public String func_149370_d() {
      return this.scoreName;
   }

   public S3DPacketDisplayScoreboard(int var1, ScoreObjective var2) {
      this.position = var1;
      if (var2 == null) {
         this.scoreName = "";
      } else {
         this.scoreName = var2.getName();
      }
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.position);
      var1.writeString(this.scoreName);
   }

   public int func_149371_c() {
      return this.position;
   }

   public S3DPacketDisplayScoreboard() {
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleDisplayScoreboard(this);
   }
}
