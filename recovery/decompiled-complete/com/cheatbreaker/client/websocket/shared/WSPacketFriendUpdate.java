package com.cheatbreaker.client.websocket.shared;

import com.cheatbreaker.client.util.friend.Friend$Builder;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker$1;
import net.minecraft.network.PacketBuffer;
import net.minecraft.realms.RealmsEditBox;

public class WSPacketFriendUpdate extends WSPacket {
   public String playerId;
   public long offlineSince;
   public String name;
   public RealmsEditBox field_0000;
   public boolean online;
   public Friend$Builder field_0006;
   public WebSocketServerHandshaker$1 field_0003;

   public long getOfflineSince() {
      return this.offlineSince;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.playerId);
      var1.writeString(this.name);
      var1.writeLong(this.offlineSince);
      var1.writeBoolean(this.online);
   }

   public boolean isOnline() {
      return this.online;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleFriendUpdate(this);
   }

   public String getPlayerId() {
      return this.playerId;
   }

   public String getName() {
      return this.name;
   }

   public WSPacketFriendUpdate() {
   }

   @Override
   public void read(PacketBuffer var1) {
      this.playerId = var1.readStringFromBuffer(52);
      this.name = var1.readStringFromBuffer(32);
      this.offlineSince = var1.readLong();
      this.online = var1.readBoolean();
      System.out.println(this.name);
   }

   public WSPacketFriendUpdate(String var1, String var2, long var3, boolean var5) {
      this.playerId = var1;
      this.name = var2;
      this.offlineSince = var3;
      this.online = var5;
   }
}
