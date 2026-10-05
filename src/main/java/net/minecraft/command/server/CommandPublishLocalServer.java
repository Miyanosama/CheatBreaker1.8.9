package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldSettings;

public class CommandPublishLocalServer extends CommandBase {
   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      String var3 = MinecraftServer.getServer().shareToLAN(WorldSettings.GameType.SURVIVAL, false);
      if (var3 != null) {
         notifyOperators(var1, this, "commands.publish.started", var3);
      } else {
         notifyOperators(var1, this, "commands.publish.failed");
      }
   }

   @Override
   public String getCommandName() {
      return "publish";
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.publish.usage";
   }
}
