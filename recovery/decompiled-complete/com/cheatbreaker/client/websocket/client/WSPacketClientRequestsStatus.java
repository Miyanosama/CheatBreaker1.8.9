package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.command.CommandReplaceItem;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.network.PacketBuffer;
import net.optifine.entity.model.ModelAdapterEnderman;
import org.apache.log4j.DailyRollingFileAppender;

public class WSPacketClientRequestsStatus extends WSPacket {
   public DailyRollingFileAppender field_0004;
   public CommandTeleport field_0002;
   public CommandReplaceItem field_0003;
   public boolean accepting;
   public ModelAdapterEnderman field_0001;

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
      this.accepting = var1.readBoolean();
   }

   public WSPacketClientRequestsStatus() {
   }

   public boolean isAccepting() {
      return this.accepting;
   }

   public WSPacketClientRequestsStatus(boolean var1) {
      this.accepting = var1;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeBoolean(this.accepting);
   }
}
