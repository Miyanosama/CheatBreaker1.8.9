package net.minecraft.command.server;

import io.netty.util.internal.logging.JdkLogger;
import net.minecraft.client.resources.SkinManager$3;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandResultStats$Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import org.apache.log4j.pattern.LogEvent;

public class CommandListPlayers extends CommandBase {
   public LogEvent field_0001;
   public JdkLogger field_0002;
   public SkinManager$3 field_0000;

   @Override
   public String getCommandName() {
      return "list";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      int var3 = MinecraftServer.getServer().getCurrentPlayerCount();
      var1.addChatMessage(new ChatComponentTranslation("commands.players.list", var3, MinecraftServer.getServer().getMaxPlayers()));
      var1.addChatMessage(
         new ChatComponentText(MinecraftServer.getServer().getConfigurationManager().func_181058_b(var2.length > 0 && "uuids".equalsIgnoreCase(var2[0])))
      );
      var1.setCommandStat(CommandResultStats$Type.QUERY_RESULT, var3);
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
