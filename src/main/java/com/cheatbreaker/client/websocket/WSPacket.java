package com.cheatbreaker.client.websocket;

import com.cheatbreaker.client.websocket.client.WSPacketClientCrashReport;
import com.cheatbreaker.client.websocket.client.WSPacketClientFriendRemove;
import com.cheatbreaker.client.websocket.client.WSPacketClientJoinServerResponse;
import com.cheatbreaker.client.websocket.client.WSPacketClientRequestsStatus;
import com.cheatbreaker.client.websocket.server.WSPacketBulkFriends;
import com.cheatbreaker.client.websocket.server.WSPacketFriendStatusUpdate;
import com.cheatbreaker.client.websocket.server.WSPacketFriendsUpdate;
import com.cheatbreaker.client.websocket.shared.WSPacketFriendRequest;
import com.cheatbreaker.client.websocket.shared.WSPacketFriendUpdate;
import com.cheatbreaker.client.websocket.shared.WSPacketServerUpdate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import com.cheatbreaker.client.websocket.server.WSPacketJoinServer;
import com.cheatbreaker.client.websocket.client.WSPacketClientProcessList;
import com.cheatbreaker.client.websocket.server.WSPacketKeyRequest;
import com.cheatbreaker.client.websocket.client.WSPacketClientCosmetics;
import com.cheatbreaker.client.websocket.server.WSPacketFormattedConsoleOutput;
import com.cheatbreaker.client.websocket.shared.WSPacketClientFriendRequestUpdate;
import com.cheatbreaker.client.websocket.client.WSPacketClientProfilesExist;
import com.cheatbreaker.client.websocket.client.WSPacketClientKeySync;
import com.cheatbreaker.client.websocket.shared.WSPacketMessage;
import com.cheatbreaker.client.websocket.server.WSPacketEmote;
import com.cheatbreaker.client.websocket.client.WSPacketClientPlayerJoin;
import com.cheatbreaker.client.websocket.server.WSPacketForceCrash;
import com.cheatbreaker.client.websocket.shared.WSPacketConsole;
import com.cheatbreaker.client.websocket.client.WSPacketClientSync;
import com.cheatbreaker.client.websocket.client.WSPacketClientKeyResponse;
import com.cheatbreaker.client.websocket.server.WSPacketCosmetics;

public abstract class WSPacket {
   public static BiMap<Class<? extends WSPacket>, Integer> REGISTRY = HashBiMap.create();

   public byte[] readKey(ByteBuf var1) {
      short var2 = var1.readShort();
      if (var2 < 0) {
         System.out.println("[WS] Key was smaller than nothing!  Weird key!");
         return new byte[0];
      } else {
         byte[] var3 = new byte[var2];
         var1.readBytes(var3);
         return var3;
      }
   }

   public abstract void write(PacketBuffer var1);

   public abstract void read(PacketBuffer var1);

   static {
      REGISTRY.put(WSPacketJoinServer.class, 0);
      REGISTRY.put(WSPacketClientJoinServerResponse.class, 1);
      REGISTRY.put(WSPacketConsole.class, 2);
      REGISTRY.put(WSPacketFormattedConsoleOutput.class, 3);
      REGISTRY.put(WSPacketFriendsUpdate.class, 4);
      REGISTRY.put(WSPacketMessage.class, 5);
      REGISTRY.put(WSPacketServerUpdate.class, 6);
      REGISTRY.put(WSPacketBulkFriends.class, 7);
      REGISTRY.put(WSPacketCosmetics.class, 8);
      REGISTRY.put(WSPacketFriendRequest.class, 9);
      REGISTRY.put(WSPacketFriendStatusUpdate.class, 16);
      REGISTRY.put(WSPacketClientFriendRemove.class, 17);
      REGISTRY.put(WSPacketFriendUpdate.class, 18);
      REGISTRY.put(WSPacketClientPlayerJoin.class, 19);
      REGISTRY.put(WSPacketClientCosmetics.class, 20);
      REGISTRY.put(WSPacketClientFriendRequestUpdate.class, 21);
      REGISTRY.put(WSPacketClientRequestsStatus.class, 22);
      REGISTRY.put(WSPacketClientCrashReport.class, 23);
      REGISTRY.put(WSPacketClientSync.class, 24);
      REGISTRY.put(WSPacketClientKeyResponse.class, 25);
      REGISTRY.put(WSPacketKeyRequest.class, 32);
      REGISTRY.put(WSPacketForceCrash.class, 33);
      REGISTRY.put(WSPacketClientProfilesExist.class, 34);
      REGISTRY.put(WSPacketClientProcessList.class, 36);
      REGISTRY.put(WSPacketClientKeySync.class, 37);
      REGISTRY.put(WSPacketEmote.class, 39);
   }

   public void writeKey(ByteBuf var1, byte[] var2) {
      var1.writeShort(var2.length);
      var1.writeBytes(var2);
   }

   public abstract void handle(AssetsWebSocket var1);
}
