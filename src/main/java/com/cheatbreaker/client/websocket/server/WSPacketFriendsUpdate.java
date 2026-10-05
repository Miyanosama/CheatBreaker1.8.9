package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.network.PacketBuffer;

public class WSPacketFriendsUpdate extends WSPacket {
   public Map<String, List<String>> recoveredField2923;
   public boolean recoveredField2924;
   public Map<String, List<String>> recoveredField2925;
   public boolean recoveredField2926;

   public boolean isAcceptingFriendRequests() {
      return this.recoveredField2924;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField2926 = var1.readBoolean();
      this.recoveredField2924 = var1.readBoolean();
      int var3 = var1.readInt();
      int var4 = var1.readInt();
      this.recoveredField2925 = new HashMap<>();

      for (int var2 = 0; var2 < var3; var2++) {
         this.recoveredField2925
            .put(var1.readStringFromBuffer(52), Arrays.asList(var1.readStringFromBuffer(32), String.valueOf(var1.readInt()), var1.readStringFromBuffer(256)));
      }

      this.recoveredField2923 = new HashMap<>();

      for (int var5 = 0; var5 < var4; var5++) {
         this.recoveredField2923.put(var1.readStringFromBuffer(52), Arrays.asList(var1.readStringFromBuffer(32), String.valueOf(var1.readLong())));
      }
   }

   public WSPacketFriendsUpdate(boolean var1, boolean var2, Map<String, List<String>> var3, Map<String, List<String>> var4) {
      this.recoveredField2926 = var1;
      this.recoveredField2924 = var2;
      this.recoveredField2925 = var3;
      this.recoveredField2923 = var4;
   }

   public boolean isConsoleAllowed() {
      return this.recoveredField2926;
   }

   public Map<String, List<String>> getOfflineMap() {
      return this.recoveredField2923;
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
      return this.recoveredField2925;
   }
}
