package net.minecraft.network.login.server;

import com.mojang.authlib.GameProfile;
import io.netty.buffer.SlicedByteBuf;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import java.util.UUID;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import org.apache.log4j.ProvisionNode;

public class S02PacketLoginSuccess implements Packet<INetHandlerLoginClient> {
   public SingleThreadEventLoop field_0002;
   public ObjectEncoderOutputStream field_0004;
   public SlicedByteBuf field_0001;
   public GameProfile profile;
   public ProvisionNode field_0000;

   public S02PacketLoginSuccess() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      UUID var2 = this.profile.getId();
      var1.writeString(var2 == null ? "" : var2.toString());
      var1.writeString(this.profile.getName());
   }

   public S02PacketLoginSuccess(GameProfile var1) {
      this.profile = var1;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      String var2 = var1.readStringFromBuffer(36);
      String var3 = var1.readStringFromBuffer(16);
      UUID var4 = UUID.fromString(var2);
      this.profile = new GameProfile(var4, var3);
   }

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleLoginSuccess(this);
   }

   public GameProfile getProfile() {
      return this.profile;
   }
}
