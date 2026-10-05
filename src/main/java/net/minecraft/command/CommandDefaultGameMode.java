package net.minecraft.command;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandDefaultGameMode extends CommandGameMode {
   @Override
   public String getCommandName() {
      return "defaultgamemode";
   }

   public void setGameType(WorldSettings.GameType var1) {
      MinecraftServer var2 = MinecraftServer.getServer();
      var2.setGameType(var1);
      if (var2.getForceGamemode()) {
         for (EntityPlayerMP var4 : MinecraftServer.getServer().getConfigurationManager().getPlayerList()) {
            var4.setGameType(var1);
            var4.O = 0.0F;
         }
      }
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length <= 0) {
         throw new WrongUsageException("commands.defaultgamemode.usage");
      } else {
         WorldSettings.GameType var3 = this.getGameModeFromCommand(var1, var2[0]);
         this.setGameType(var3);
         notifyOperators(var1, this, "commands.defaultgamemode.success", new ChatComponentTranslation("gameMode." + var3.getName()));
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.defaultgamemode.usage";
   }
}
