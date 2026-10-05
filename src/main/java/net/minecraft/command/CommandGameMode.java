package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandGameMode extends CommandBase {
   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.gamemode.usage";
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 1;
   }

   public WorldSettings.GameType getGameModeFromCommand(ICommandSender var1, String var2) throws net.minecraft.command.NumberInvalidException {
      return var2.equalsIgnoreCase(WorldSettings.GameType.SURVIVAL.getName()) || var2.equalsIgnoreCase("s")
         ? WorldSettings.GameType.SURVIVAL
         : (
            var2.equalsIgnoreCase(WorldSettings.GameType.CREATIVE.getName()) || var2.equalsIgnoreCase("c")
               ? WorldSettings.GameType.CREATIVE
               : (
                  var2.equalsIgnoreCase(WorldSettings.GameType.ADVENTURE.getName()) || var2.equalsIgnoreCase("a")
                     ? WorldSettings.GameType.ADVENTURE
                     : (
                        !var2.equalsIgnoreCase(WorldSettings.GameType.SPECTATOR.getName()) && !var2.equalsIgnoreCase("sp")
                           ? WorldSettings.getGameTypeById(parseInt(var2, 0, WorldSettings.GameType.values().length - 2))
                           : WorldSettings.GameType.SPECTATOR
                     )
               )
         );
   }

   @Override
   public String getCommandName() {
      return "gamemode";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length <= 0) {
         throw new WrongUsageException("commands.gamemode.usage");
      } else {
         WorldSettings.GameType var3 = this.getGameModeFromCommand(var1, var2[0]);
         EntityPlayerMP var4 = var2.length >= 2 ? getPlayer(var1, var2[1]) : getCommandSenderAsPlayer(var1);
         var4.setGameType(var3);
         var4.O = 0.0F;
         if (var1.s_().Q().getBoolean("sendCommandFeedback")) {
            var4.addChatMessage(new ChatComponentTranslation("gameMode.changed"));
         }

         ChatComponentTranslation var5 = new ChatComponentTranslation("gameMode." + var3.getName());
         if (var4 != var1) {
            notifyOperators(var1, this, 1, "commands.gamemode.success.other", var4.z_(), var5);
         } else {
            notifyOperators(var1, this, 1, "commands.gamemode.success.self", var5);
         }
      }
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, "survival", "creative", "adventure", "spectator")
         : (var2.length == 2 ? getListOfStringsMatchingLastWord(var2, this.getListOfPlayerUsernames()) : null);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String[] getListOfPlayerUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }
}
