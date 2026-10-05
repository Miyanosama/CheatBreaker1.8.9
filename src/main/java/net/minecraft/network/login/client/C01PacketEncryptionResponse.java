package net.minecraft.network.login.client;

import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;
import net.minecraft.util.CryptManager;

public class C01PacketEncryptionResponse implements Packet<INetHandlerLoginServer> {
   public byte[] secretKeyEncrypted = new byte[0];
   public byte[] verifyTokenEncrypted = new byte[0];

   public void processPacket(INetHandlerLoginServer var1) {
      var1.processEncryptionResponse(this);
   }

   public SecretKey getSecretKey(PrivateKey var1) {
      return CryptManager.decryptSharedKey(var1, this.secretKeyEncrypted);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.secretKeyEncrypted = var1.readByteArray();
      this.verifyTokenEncrypted = var1.readByteArray();
   }

   public byte[] getVerifyToken(PrivateKey var1) {
      return var1 == null ? this.verifyTokenEncrypted : CryptManager.decryptData(var1, this.verifyTokenEncrypted);
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByteArray(this.secretKeyEncrypted);
      var1.writeByteArray(this.verifyTokenEncrypted);
   }

   public C01PacketEncryptionResponse(SecretKey var1, PublicKey var2, byte[] var3) {
      this.secretKeyEncrypted = CryptManager.encryptData(var2, var1.getEncoded());
      this.verifyTokenEncrypted = CryptManager.encryptData(var2, var3);
   }

   public C01PacketEncryptionResponse() {
   }
}
