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
import recovered.unidentified.UnidentifiedClass0477;
import recovered.unidentified.UnidentifiedClass0599;
import recovered.unidentified.UnidentifiedClass0706;
import recovered.unidentified.UnidentifiedClass0792;
import recovered.unidentified.UnidentifiedClass0842;
import recovered.unidentified.UnidentifiedClass1658;
import recovered.unidentified.UnidentifiedClass1774;
import recovered.unidentified.UnidentifiedClass1812;
import recovered.unidentified.UnidentifiedClass1858;
import recovered.unidentified.UnidentifiedClass1954;
import recovered.unidentified.UnidentifiedClass3153;
import recovered.unidentified.UnidentifiedClass3311;
import recovered.unidentified.UnidentifiedClass3405;
import recovered.unidentified.UnidentifiedClass3870;
import recovered.unidentified.UnidentifiedClass3918;
import recovered.unidentified.UnidentifiedClass4814;

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
      REGISTRY.put(UnidentifiedClass0477.class, 0);
      REGISTRY.put(WSPacketClientJoinServerResponse.class, 1);
      REGISTRY.put(UnidentifiedClass3405.class, 2);
      REGISTRY.put(UnidentifiedClass0842.class, 3);
      REGISTRY.put(WSPacketFriendsUpdate.class, 4);
      REGISTRY.put(UnidentifiedClass1858.class, 5);
      REGISTRY.put(WSPacketServerUpdate.class, 6);
      REGISTRY.put(WSPacketBulkFriends.class, 7);
      REGISTRY.put(UnidentifiedClass4814.class, 8);
      REGISTRY.put(WSPacketFriendRequest.class, 9);
      REGISTRY.put(WSPacketFriendStatusUpdate.class, 16);
      REGISTRY.put(WSPacketClientFriendRemove.class, 17);
      REGISTRY.put(WSPacketFriendUpdate.class, 18);
      REGISTRY.put(UnidentifiedClass3153.class, 19);
      REGISTRY.put(UnidentifiedClass0792.class, 20);
      REGISTRY.put(UnidentifiedClass1658.class, 21);
      REGISTRY.put(WSPacketClientRequestsStatus.class, 22);
      REGISTRY.put(WSPacketClientCrashReport.class, 23);
      REGISTRY.put(UnidentifiedClass3870.class, 24);
      REGISTRY.put(UnidentifiedClass3918.class, 25);
      REGISTRY.put(UnidentifiedClass0706.class, 32);
      REGISTRY.put(UnidentifiedClass3311.class, 33);
      REGISTRY.put(UnidentifiedClass1774.class, 34);
      REGISTRY.put(UnidentifiedClass0599.class, 36);
      REGISTRY.put(UnidentifiedClass1812.class, 37);
      REGISTRY.put(UnidentifiedClass1954.class, 39);
   }

   public void writeKey(ByteBuf var1, byte[] var2) {
      var1.writeShort(var2.length);
      var1.writeBytes(var2);
   }

   public abstract void handle(AssetsWebSocket var1);
}
