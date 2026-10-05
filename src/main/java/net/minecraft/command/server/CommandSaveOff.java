package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandSaveOff extends CommandBase {
   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.save-off.usage";
   }

   @Override
   public String getCommandName() {
      return "save-off";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      MinecraftServer var3 = MinecraftServer.getServer();
      boolean var4 = false;

      for (int var5 = 0; var5 < var3.worldServers.length; var5++) {
         if (var3.worldServers[var5] != null) {
            WorldServer var6 = var3.worldServers[var5];
            if (!var6.disableLevelSaving) {
               var6.disableLevelSaving = true;
               var4 = true;
            }
         }
      }

      if (var4) {
         notifyOperators(var1, this, "commands.save.disabled");
      } else {
         throw new CommandException("commands.save-off.alreadyOff");
      }
   }
}
