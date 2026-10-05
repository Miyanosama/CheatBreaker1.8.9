package net.minecraft.network.login.client;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;

public class C00PacketLoginStart implements Packet<INetHandlerLoginServer> {
   public GameProfile profile;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeString(this.profile.getName());
   }

   public void processPacket(INetHandlerLoginServer var1) {
      var1.processLoginStart(this);
   }

   public GameProfile getProfile() {
      return this.profile;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.profile = new GameProfile((UUID)null, var1.readStringFromBuffer(16));
   }

   public C00PacketLoginStart() {
   }

   public C00PacketLoginStart(GameProfile var1) {
      this.profile = var1;
   }
}
