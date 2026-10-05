package net.minecraft.server.integrated;

import com.mojang.authlib.GameProfile;
import io.netty.handler.ssl.SslProvider;
import java.net.SocketAddress;
import net.minecraft.block.BlockHugeMushroom$1;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.ServerConfigurationManager;
import recovered.unidentified.UnidentifiedClass3581;

public class IntegratedPlayerList extends ServerConfigurationManager {
   public BlockHugeMushroom$1 field_0001;
   public NBTTagCompound hostPlayerData;
   public UnidentifiedClass3581 field_0000;
   public SslProvider field_0002;

   @Override
   public void writePlayerData(EntityPlayerMP var1) {
      if (var1.z_().equals(this.getServerInstance().getServerOwner())) {
         this.hostPlayerData = new NBTTagCompound();
         var1.e(this.hostPlayerData);
      }

      super.writePlayerData(var1);
   }

   @Override
   public String allowUserToConnect(SocketAddress var1, GameProfile var2) {
      return var2.getName().equalsIgnoreCase(this.getServerInstance().getServerOwner()) && this.getPlayerByUsername(var2.getName()) != null
         ? "That name is already taken."
         : super.allowUserToConnect(var1, var2);
   }

   @Override
   public NBTTagCompound getHostPlayerData() {
      return this.hostPlayerData;
   }

   public IntegratedServer getServerInstance() {
      return (IntegratedServer)super.getServerInstance();
   }

   public IntegratedPlayerList(IntegratedServer var1) {
      super(var1);
      this.setViewDistance(10);
   }
}
