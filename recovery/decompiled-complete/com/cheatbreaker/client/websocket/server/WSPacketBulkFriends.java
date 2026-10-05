package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import net.minecraft.block.BlockDoor$EnumDoorHalf;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.network.PacketBuffer;

public class WSPacketBulkFriends extends WSPacket {
   public WebSocketClientProtocolHandler field_0004;
   public MovingSound field_0002;
   public String rawString;
   public JsonArray bulkArray;
   public BlockDoor$EnumDoorHalf field_0001;

   public String method_22325() {
      return this.rawString;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleBulkFriends(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.rawString = var1.readStringFromBuffer(32767);
      this.bulkArray = new JsonParser().parse(this.rawString).getAsJsonObject().getAsJsonArray("bulk");
   }

   public WSPacketBulkFriends(String var1, JsonArray var2) {
      this.rawString = var1;
      this.bulkArray = var2;
   }

   public WSPacketBulkFriends() {
   }

   public JsonArray getBulkArray() {
      return this.bulkArray;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.rawString);
   }
}
