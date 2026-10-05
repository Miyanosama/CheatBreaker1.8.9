package net.minecraft.network.play.server;

import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1;
import io.netty.handler.codec.compression.ZlibEncoder;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.IScoreObjectiveCriteria$EnumRenderType;
import net.minecraft.scoreboard.ScoreObjective;
import net.optifine.shaders.gui.GuiButtonShaderOption;

public class S3BPacketScoreboardObjective implements Packet<INetHandlerPlayClient> {
   public int field_149342_c;
   public GuiButtonShaderOption field_0005;
   public IScoreObjectiveCriteria$EnumRenderType type;
   public ZlibEncoder field_0004;
   public AbstractNioChannel$AbstractNioUnsafe$1 field_0000;
   public String objectiveValue;
   public String objectiveName;

   public String func_149337_d() {
      return this.objectiveValue;
   }

   public S3BPacketScoreboardObjective(ScoreObjective var1, int var2) {
      this.objectiveName = var1.getName();
      this.objectiveValue = var1.getDisplayName();
      this.type = var1.getCriteria().getRenderType();
      this.field_149342_c = var2;
   }

   public String func_149339_c() {
      return this.objectiveName;
   }

   public int func_149338_e() {
      return this.field_149342_c;
   }

   public IScoreObjectiveCriteria$EnumRenderType func_179817_d() {
      return this.type;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.objectiveName = var1.readStringFromBuffer(16);
      this.field_149342_c = var1.readByte();
      if (this.field_149342_c == 0 || this.field_149342_c == 2) {
         this.objectiveValue = var1.readStringFromBuffer(32);
         this.type = IScoreObjectiveCriteria$EnumRenderType.func_178795_a(var1.readStringFromBuffer(16));
      }
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleScoreboardObjective(this);
   }

   public S3BPacketScoreboardObjective() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.objectiveName);
      var1.writeByte(this.field_149342_c);
      if (this.field_149342_c == 0 || this.field_149342_c == 2) {
         var1.writeString(this.objectiveValue);
         var1.writeString(this.type.func_178796_a());
      }
   }
}
