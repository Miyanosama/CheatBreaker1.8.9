package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.security.PublicKey;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.CryptManager;

public class WSPacketJoinServer extends WSPacket {
   public PublicKey recoveredField1384;
   public byte[] recoveredField1385;

   @Override
   public void write(PacketBuffer var1) {
   }

   public PublicKey method_03517() {
      return this.recoveredField1384;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10115(this);
   }

   public WSPacketJoinServer(PublicKey var1, byte[] var2) {
      this.recoveredField1384 = var1;
      this.recoveredField1385 = var2;
   }

   public WSPacketJoinServer() {
   }

   public byte[] method_03518() {
      return this.recoveredField1385;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField1384 = CryptManager.decodePublicKey(this.readKey(var1));
      this.recoveredField1385 = this.readKey(var1);
   }
}
