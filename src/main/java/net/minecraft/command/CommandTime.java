package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;

public class CommandTime extends CommandBase {
   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   public void setTime(ICommandSender var1, int var2) {
      for (int var3 = 0; var3 < MinecraftServer.getServer().worldServers.length; var3++) {
         MinecraftServer.getServer().worldServers[var3].setWorldTime(var2);
      }
   }

   public void addTime(ICommandSender var1, int var2) {
      for (int var3 = 0; var3 < MinecraftServer.getServer().worldServers.length; var3++) {
         WorldServer var4 = MinecraftServer.getServer().worldServers[var3];
         var4.setWorldTime(var4.L() + var2);
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.time.usage";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length > 1) {
         if (var2[0].equals("set")) {
            int var6;
            if (var2[1].equals("day")) {
               var6 = 1000;
            } else if (var2[1].equals("night")) {
               var6 = 13000;
            } else {
               var6 = parseInt(var2[1], 0);
            }

            this.setTime(var1, var6);
            notifyOperators(var1, this, "commands.time.set", var6);
            return;
         }

         if (var2[0].equals("add")) {
            int var5 = parseInt(var2[1], 0);
            this.addTime(var1, var5);
            notifyOperators(var1, this, "commands.time.added", var5);
            return;
         }

         if (var2[0].equals("query")) {
            if (var2[1].equals("daytime")) {
               int var4 = (int)(var1.s_().L() % 2147483647L);
               var1.setCommandStat(CommandResultStats.Type.QUERY_RESULT, var4);
               notifyOperators(var1, this, "commands.time.query", var4);
               return;
            }

            if (var2[1].equals("gametime")) {
               int var3 = (int)(var1.s_().K() % 2147483647L);
               var1.setCommandStat(CommandResultStats.Type.QUERY_RESULT, var3);
               notifyOperators(var1, this, "commands.time.query", var3);
               return;
            }
         }
      }

      throw new WrongUsageException("commands.time.usage");
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, "set", "add", "query")
         : (
            var2.length == 2 && var2[0].equals("set")
               ? getListOfStringsMatchingLastWord(var2, "day", "night")
               : (var2.length == 2 && var2[0].equals("query") ? getListOfStringsMatchingLastWord(var2, "daytime", "gametime") : null)
         );
   }

   @Override
   public String getCommandName() {
      return "time";
   }
}
