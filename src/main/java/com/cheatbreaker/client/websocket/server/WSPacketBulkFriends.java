package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import net.minecraft.network.PacketBuffer;

public class WSPacketBulkFriends extends WSPacket {
   public String rawString;
   public JsonArray bulkArray;

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
