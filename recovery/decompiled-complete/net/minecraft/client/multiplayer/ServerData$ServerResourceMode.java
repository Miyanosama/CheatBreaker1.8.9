package net.minecraft.client.multiplayer;

import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleYRoom;

public enum ServerData$ServerResourceMode {
   DISABLED("disabled"),
   PROMPT("prompt"),
   ENABLED("enabled");

   public IChatComponent motd;
   public StructureOceanMonumentPieces$DoubleYRoom field_0005;
   public EntityEnderCrystal field_0000;
   public S00PacketServerInfo field_0007;

   public ServerData$ServerResourceMode(String var3) {
      this.motd = new ChatComponentTranslation("addServer.resourcePack." + var3);
   }

   public IChatComponent getMotd() {
      return this.motd;
   }
}
