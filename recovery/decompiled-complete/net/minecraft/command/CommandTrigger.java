package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandTrigger extends CommandBase {
   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.trigger.usage";
   }

   @Override
   public String getCommandName() {
      return "trigger";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length < 3) {
         throw new WrongUsageException("commands.trigger.usage");
      } else {
         EntityPlayerMP var3;
         if (var1 instanceof EntityPlayerMP) {
            var3 = (EntityPlayerMP)var1;
         } else {
            Entity var4 = var1.p_();
            if (!(var4 instanceof EntityPlayerMP)) {
               throw new CommandException("commands.trigger.invalidPlayer");
            }

            var3 = (EntityPlayerMP)var4;
         }

         Scoreboard var8 = MinecraftServer.getServer().worldServerForDimension(0).Z();
         ScoreObjective var5 = var8.getObjective(var2[0]);
         if (var5 != null && var5.getCriteria() == IScoreObjectiveCriteria.TRIGGER) {
            int var6 = parseInt(var2[2]);
            if (!var8.entityHasObjective(var3.z_(), var5)) {
               throw new CommandException("commands.trigger.invalidObjective", var2[0]);
            } else {
               Score var7 = var8.getValueFromObjective(var3.z_(), var5);
               if (var7.isLocked()) {
                  throw new CommandException("commands.trigger.disabled", var2[0]);
               } else {
                  if ("set".equals(var2[1])) {
                     var7.setScorePoints(var6);
                  } else {
                     if (!"add".equals(var2[1])) {
                        throw new CommandException("commands.trigger.invalidMode", var2[1]);
                     }

                     var7.increseScore(var6);
                  }

                  var7.setLocked(true);
                  if (var3.theItemInWorldManager.isCreative()) {
                     notifyOperators(var1, this, "commands.trigger.success", var2[0], var2[1], var2[2]);
                  }
               }
            }
         } else {
            throw new CommandException("commands.trigger.invalidObjective", var2[0]);
         }
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      if (var2.length == 1) {
         Scoreboard var4 = MinecraftServer.getServer().worldServerForDimension(0).Z();
         ArrayList var5 = Lists.newArrayList();

         for (ScoreObjective var7 : var4.getScoreObjectives()) {
            if (var7.getCriteria() == IScoreObjectiveCriteria.TRIGGER) {
               var5.add(var7.getName());
            }
         }

         return getListOfStringsMatchingLastWord(var2, var5.toArray(new String[var5.size()]));
      } else {
         return var2.length == 2 ? getListOfStringsMatchingLastWord(var2, "add", "set") : null;
      }
   }
}
