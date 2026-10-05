package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListWhitelistEntry;

public class CommandStop extends CommandBase {
   public UserListWhitelistEntry field_0000;

   @Override
   public String getCommandName() {
      return "stop";
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.stop.usage";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (MinecraftServer.getServer().worldServers != null) {
         notifyOperators(var1, this, "commands.stop.start");
      }

      MinecraftServer.getServer().initiateShutdown();
   }
}
