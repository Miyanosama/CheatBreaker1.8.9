package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.channel.AbstractChannelHandlerContext$AbstractWriteTask;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.network.PacketBuffer;

public class WSPacketFriendStatusUpdate extends WSPacket {
   public AbstractChannelHandlerContext$AbstractWriteTask field_0004;
   public DefaultResourcePack field_0002;
   public String playerId;
   public DestroyBlockProgress field_0000;
   public boolean friend;
   public String name;

   public WSPacketFriendStatusUpdate(String var1, String var2, boolean var3) {
      this.playerId = var1;
      this.name = var2;
      this.friend = var3;
   }

   public String getName() {
      return this.name;
   }

   public boolean isFriend() {
      return this.friend;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleFriendRequest(this, true);
   }

   public WSPacketFriendStatusUpdate() {
   }

   public String getPlayerId() {
      return this.playerId;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.playerId = var1.readStringFromBuffer(52);
      this.name = var1.readStringFromBuffer(32);
      this.friend = var1.readBoolean();
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.playerId);
      var1.writeString(this.name);
      var1.writeBoolean(this.friend);
   }
}
