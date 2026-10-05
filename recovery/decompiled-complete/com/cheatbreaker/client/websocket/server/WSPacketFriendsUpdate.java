package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.PacketBuffer;

public class WSPacketFriendsUpdate extends WSPacket {
   public Map<String, List<String>> field_0004;
   public boolean field_0002;
   public Map<String, List<String>> field_0003;
   public NetHandlerLoginClient field_0000;
   public boolean field_0001;

   public boolean isAcceptingFriendRequests() {
      return this.field_0002;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0001 = var1.readBoolean();
      this.field_0002 = var1.readBoolean();
      int var3 = var1.readInt();
      int var4 = var1.readInt();
      this.field_0003 = new HashMap<>();

      for (int var2 = 0; var2 < var3; var2++) {
         this.field_0003
            .put(var1.readStringFromBuffer(52), Arrays.asList(var1.readStringFromBuffer(32), String.valueOf(var1.readInt()), var1.readStringFromBuffer(256)));
      }

      this.field_0004 = new HashMap<>();

      for (int var5 = 0; var5 < var4; var5++) {
         this.field_0004.put(var1.readStringFromBuffer(52), Arrays.asList(var1.readStringFromBuffer(32), String.valueOf(var1.readLong())));
      }
   }

   public WSPacketFriendsUpdate(boolean var1, boolean var2, Map<String, List<String>> var3, Map<String, List<String>> var4) {
      this.field_0001 = var1;
      this.field_0002 = var2;
      this.field_0003 = var3;
      this.field_0004 = var4;
   }

   public boolean isConsoleAllowed() {
      return this.field_0001;
   }

   public Map<String, List<String>> getOfflineMap() {
      return this.field_0004;
   }

   @Override
   public void write(PacketBuffer var1) {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleFriendsUpdate(this);
   }

   public WSPacketFriendsUpdate() {
   }

   public Map<String, List<String>> getOnlineMap() {
      return this.field_0003;
   }
}
