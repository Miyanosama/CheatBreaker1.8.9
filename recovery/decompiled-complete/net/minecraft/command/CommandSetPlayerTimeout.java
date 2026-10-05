package net.minecraft.command;

import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.server.MinecraftServer;

public class CommandSetPlayerTimeout extends CommandBase {
   public BlockStaticLiquid field_0000;

   @Override
   public String getCommandName() {
      return "setidletimeout";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.setidletimeout.usage";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length != 1) {
         throw new WrongUsageException("commands.setidletimeout.usage");
      } else {
         int var3 = parseInt(var2[0], 0);
         MinecraftServer.getServer().setPlayerIdleTimeout(var3);
         notifyOperators(var1, this, "commands.setidletimeout.success", var3);
      }
   }
}
