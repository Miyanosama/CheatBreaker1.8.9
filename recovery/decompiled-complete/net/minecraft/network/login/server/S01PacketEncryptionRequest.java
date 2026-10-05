package net.minecraft.network.login.server;

import java.security.PublicKey;
import net.minecraft.entity.DataWatcher$WatchableObject;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.util.CryptManager;

public class S01PacketEncryptionRequest implements Packet<INetHandlerLoginClient> {
   public DataWatcher$WatchableObject field_0001;
   public PublicKey publicKey;
   public byte[] verifyToken;
   public String hashedServerId;

   public S01PacketEncryptionRequest(String var1, PublicKey var2, byte[] var3) {
      this.hashedServerId = var1;
      this.publicKey = var2;
      this.verifyToken = var3;
   }

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleEncryptionRequest(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.hashedServerId = var1.readStringFromBuffer(20);
      this.publicKey = CryptManager.decodePublicKey(var1.readByteArray());
      this.verifyToken = var1.readByteArray();
   }

   public S01PacketEncryptionRequest() {
   }

   public String getServerId() {
      return this.hashedServerId;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.hashedServerId);
      var1.writeByteArray(this.publicKey.getEncoded());
      var1.writeByteArray(this.verifyToken);
   }

   public byte[] getVerifyToken() {
      return this.verifyToken;
   }

   public PublicKey getPublicKey() {
      return this.publicKey;
   }
}
