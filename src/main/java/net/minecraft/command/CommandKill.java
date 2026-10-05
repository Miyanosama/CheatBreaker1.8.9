package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandKill extends CommandBase {
   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length == 0) {
         EntityPlayerMP var3 = getCommandSenderAsPlayer(var1);
         var3.onKillCommand();
         notifyOperators(var1, this, "commands.kill.successful", var3.getDisplayName());
      } else {
         Entity var4 = getEntity(var1, var2[0]);
         var4.onKillCommand();
         notifyOperators(var1, this, "commands.kill.successful", var4.getDisplayName());
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "kill";
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.kill.usage";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1 ? getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames()) : null;
   }
}
