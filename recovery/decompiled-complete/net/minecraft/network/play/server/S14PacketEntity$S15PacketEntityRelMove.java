package net.minecraft.network.play.server;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker00;
import net.minecraft.client.Minecraft$18;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.network.PacketBuffer;
import org.apache.log4j.AsyncAppender$DiscardSummary;

public class S14PacketEntity$S15PacketEntityRelMove extends S14PacketEntity {
   public AsyncAppender$DiscardSummary field_0001;
   public Minecraft$18 field_0002;
   public WebSocketServerHandshaker00 field_0000;
   public EntityAIPlay field_0003;

   @Override
   public void readPacketData(PacketBuffer var1) {
      super.readPacketData(var1);
      this.b = var1.readByte();
      this.c = var1.readByte();
      this.d = var1.readByte();
      this.g = var1.readBoolean();
   }

   public S14PacketEntity$S15PacketEntityRelMove(int var1, byte var2, byte var3, byte var4, boolean var5) {
      super(var1);
      this.b = var2;
      this.c = var3;
      this.d = var4;
      this.g = var5;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      super.writePacketData(var1);
      var1.writeByte(this.b);
      var1.writeByte(this.c);
      var1.writeByte(this.d);
      var1.writeBoolean(this.g);
   }

   public S14PacketEntity$S15PacketEntityRelMove() {
   }
}
