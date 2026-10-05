package net.minecraft.command;

import net.minecraft.network.NetworkSystem$4;
import net.minecraft.network.play.server.S00PacketKeepAlive;

public class EntityNotFoundException extends CommandException {
   public NetworkSystem$4 field_0000;
   public S00PacketKeepAlive field_0001;

   public EntityNotFoundException() {
      this("commands.generic.entity.notFound");
   }

   public EntityNotFoundException(String var1, Object... var2) {
      super(var1, var2);
   }
}
