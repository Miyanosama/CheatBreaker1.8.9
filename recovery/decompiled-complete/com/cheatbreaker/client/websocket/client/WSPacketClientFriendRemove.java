package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Vec4b;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.extensions.CompressionExtension;
import recovered.unidentified.UnidentifiedClass1883;

public class WSPacketClientFriendRemove extends WSPacket {
   public UnidentifiedClass1883 field_0004;
   public InvalidDataException field_0002;
   public String playerId;
   public Vec4b field_0000;
   public CompressionExtension field_0001;

   public WSPacketClientFriendRemove() {
   }

   public String getPlayerId() {
      return this.playerId;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.playerId);
   }

   public WSPacketClientFriendRemove(String var1) {
      this.playerId = var1;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.playerId = var1.readStringFromBuffer(52);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleFriendRemove(this);
   }
}
