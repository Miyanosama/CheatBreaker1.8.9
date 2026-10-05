package net.minecraft.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.WorldInfo;

public class CommandToggleDownfall extends CommandBase {
   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      this.toggleDownfall();
      notifyOperators(var1, this, "commands.downfall.success");
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.downfall.usage";
   }

   public void toggleDownfall() {
      WorldInfo var1 = MinecraftServer.getServer().worldServers[0].P();
      var1.setRaining(!var1.isRaining());
   }

   @Override
   public String getCommandName() {
      return "toggledownfall";
   }
}
