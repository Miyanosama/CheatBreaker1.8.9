package net.minecraft.network.login.client;

import com.mojang.authlib.GameProfile;
import io.netty.channel.nio.NioEventLoopGroup;
import java.util.UUID;
import net.minecraft.command.CommandDifficulty;
import net.minecraft.network.NetHandlerPlayServer$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;

public class C00PacketLoginStart implements Packet<INetHandlerLoginServer> {
   public NioEventLoopGroup field_0001;
   public CommandDifficulty field_0003;
   public GameProfile profile;
   public NetHandlerPlayServer$1 field_0002;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.profile.getName());
   }

   public void processPacket(INetHandlerLoginServer var1) {
      var1.processLoginStart(this);
   }

   public GameProfile getProfile() {
      return this.profile;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.profile = new GameProfile((UUID)null, var1.readStringFromBuffer(16));
   }

   public C00PacketLoginStart() {
   }

   public C00PacketLoginStart(GameProfile var1) {
      this.profile = var1;
   }
}
