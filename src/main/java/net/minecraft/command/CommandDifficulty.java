package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class CommandDifficulty extends CommandBase {
   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length <= 0) {
         throw new WrongUsageException("commands.difficulty.usage");
      } else {
         EnumDifficulty var3 = this.getDifficultyFromCommand(var2[0]);
         MinecraftServer.getServer().setDifficultyForAllWorlds(var3);
         notifyOperators(var1, this, "commands.difficulty.success", new ChatComponentTranslation(var3.getDifficultyResourceKey()));
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.difficulty.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   public EnumDifficulty getDifficultyFromCommand(String var1) throws net.minecraft.command.NumberInvalidException {
      return var1.equalsIgnoreCase("peaceful") || var1.equalsIgnoreCase("p")
         ? EnumDifficulty.PEACEFUL
         : (
            var1.equalsIgnoreCase("easy") || var1.equalsIgnoreCase("e")
               ? EnumDifficulty.EASY
               : (
                  !var1.equalsIgnoreCase("normal") && !var1.equalsIgnoreCase("n")
                     ? (
                        !var1.equalsIgnoreCase("hard") && !var1.equalsIgnoreCase("h")
                           ? EnumDifficulty.getDifficultyEnum(parseInt(var1, 0, 3))
                           : EnumDifficulty.HARD
                     )
                     : EnumDifficulty.NORMAL
               )
         );
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1 ? getListOfStringsMatchingLastWord(var2, "peaceful", "easy", "normal", "hard") : null;
   }

   @Override
   public String getCommandName() {
      return "difficulty";
   }
}
