package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandListPlayers extends CommandBase {
   @Override
   public String getCommandName() {
      return "list";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      int var3 = MinecraftServer.getServer().getCurrentPlayerCount();
      var1.addChatMessage(new ChatComponentTranslation("commands.players.list", var3, MinecraftServer.getServer().getMaxPlayers()));
      var1.addChatMessage(
         new ChatComponentText(MinecraftServer.getServer().getConfigurationManager().func_181058_b(var2.length > 0 && "uuids".equalsIgnoreCase(var2[0])))
      );
      var1.setCommandStat(CommandResultStats.Type.QUERY_RESULT, var3);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.players.usage";
   }
}
