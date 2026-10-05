package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.CryptManager;

public class WSPacketClientJoinServerResponse extends WSPacket {
   public byte[] recoveredField624;
   public byte[] recoveredField625;

   public WSPacketClientJoinServerResponse(SecretKey var1, PublicKey var2, byte[] var3) {
      this.recoveredField625 = CryptManager.encryptData(var2, var1.getEncoded());
      this.recoveredField624 = CryptManager.encryptData(var2, var3);
   }

   @Override
   public void read(PacketBuffer var1) {
   }

   @Override
   public void write(PacketBuffer var1) {
      this.writeKey(var1, this.recoveredField625);
      this.writeKey(var1, this.recoveredField624);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }
}
