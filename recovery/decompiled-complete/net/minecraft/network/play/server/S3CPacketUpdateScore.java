package net.minecraft.network.play.server;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderSkeleton$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import recovered.unidentified.UnidentifiedClass1818;

public class S3CPacketUpdateScore implements Packet<INetHandlerPlayClient> {
   public S3CPacketUpdateScore$Action action;
   public String objective;
   public UnidentifiedClass1818 field_0002;
   public int value;
   public String name = "";
   public WorldRenderer field_0001;
   public RenderSkeleton$1 field_0006;

   public String getObjectiveName() {
      return this.objective;
   }

   public S3CPacketUpdateScore() {
      this.objective = "";
   }

   public int getScoreValue() {
      return this.value;
   }

   public String getPlayerName() {
      return this.name;
   }

   public S3CPacketUpdateScore(String var1, ScoreObjective var2) {
      this.objective = "";
      this.name = var1;
      this.objective = var2.getName();
      this.value = 0;
      this.action = S3CPacketUpdateScore$Action.REMOVE;
   }

   public S3CPacketUpdateScore$Action getScoreAction() {
      return this.action;
   }

   public S3CPacketUpdateScore(Score var1) {
      this.objective = "";
      this.name = var1.getPlayerName();
      this.objective = var1.getObjective().getName();
      this.value = var1.getScorePoints();
      this.action = S3CPacketUpdateScore$Action.CHANGE;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.name = var1.readStringFromBuffer(40);
      this.action = var1.readEnumValue(S3CPacketUpdateScore$Action.class);
      this.objective = var1.readStringFromBuffer(16);
      if (this.action != S3CPacketUpdateScore$Action.REMOVE) {
         this.value = var1.readVarIntFromBuffer();
      }
   }

   public S3CPacketUpdateScore(String var1) {
      this.objective = "";
      this.name = var1;
      this.objective = "";
      this.value = 0;
      this.action = S3CPacketUpdateScore$Action.REMOVE;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleUpdateScore(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.name);
      var1.writeEnumValue(this.action);
      var1.writeString(this.objective);
      if (this.action != S3CPacketUpdateScore$Action.REMOVE) {
         var1.writeVarIntToBuffer(this.value);
      }
   }
}
