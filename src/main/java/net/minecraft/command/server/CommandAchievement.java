package net.minecraft.command.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;

public class CommandAchievement extends CommandBase {
   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 2;
   }

   @Override
   public String getCommandName() {
      return "achievement";
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.achievement.usage";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      if (var2.length < 2) {
         throw new WrongUsageException("commands.achievement.usage");
      } else {
         final StatBase var3 = StatList.getOneShotStat(var2[1]);
         if (var3 == null && !var2[1].equals("*")) {
            throw new CommandException("commands.achievement.unknownAchievement", var2[1]);
         } else {
            final EntityPlayerMP var4 = var2.length >= 3 ? getPlayer(var1, var2[2]) : getCommandSenderAsPlayer(var1);
            boolean var5 = var2[0].equalsIgnoreCase("give");
            boolean var6 = var2[0].equalsIgnoreCase("take");
            if (var5 || var6) {
               if (var3 == null) {
                  if (var5) {
                     for (Achievement var8 : AchievementList.achievementList) {
                        var4.triggerAchievement(var8);
                     }

                     notifyOperators(var1, this, "commands.achievement.give.success.all", var4.z_());
                  } else if (var6) {
                     for (Achievement var16 : Lists.reverse(AchievementList.achievementList)) {
                        var4.func_175145_a(var16);
                     }

                     notifyOperators(var1, this, "commands.achievement.take.success.all", var4.z_());
                  }
               } else {
                  if (var3 instanceof Achievement) {
                     Achievement var15 = (Achievement)var3;
                     if (var5) {
                        if (var4.getStatFile().hasAchievementUnlocked(var15)) {
                           throw new CommandException("commands.achievement.alreadyHave", var4.z_(), var3.createChatComponent());
                        }

                        ArrayList var18;
                        for (var18 = Lists.newArrayList();
                           var15.parentAchievement != null && !var4.getStatFile().hasAchievementUnlocked(var15.parentAchievement);
                           var15 = var15.parentAchievement
                        ) {
                           var18.add(var15.parentAchievement);
                        }

                        for (Achievement var21 : (Iterable<Achievement>)(Iterable<?>)(Lists.reverse(var18))) {
                           var4.triggerAchievement(var21);
                        }
                     } else if (var6) {
                        if (!var4.getStatFile().hasAchievementUnlocked(var15)) {
                           throw new CommandException("commands.achievement.dontHave", var4.z_(), var3.createChatComponent());
                        }

                        ArrayList var17 = Lists.newArrayList(Iterators.filter(AchievementList.achievementList.iterator(), new Predicate<Achievement>() {
                           public boolean apply(Achievement var1) {
                              return var4.getStatFile().hasAchievementUnlocked(var1) && var1 != var3;
                           }
                        }));
                        ArrayList var9 = Lists.newArrayList(var17);

                        for (Achievement var11 : (Iterable<Achievement>)(Iterable<?>)(var17)) {
                           Achievement var12 = var11;

                           boolean var13;
                           for (var13 = false; var12 != null; var12 = var12.parentAchievement) {
                              if (var12 == var3) {
                                 var13 = true;
                              }
                           }

                           if (!var13) {
                              for (Achievement var23 = var11; var23 != null; var23 = var23.parentAchievement) {
                                 var9.remove(var11);
                              }
                           }
                        }

                        for (Achievement var22 : (Iterable<Achievement>)(Iterable<?>)(var9)) {
                           var4.func_175145_a(var22);
                        }
                     }
                  }

                  if (var5) {
                     var4.triggerAchievement(var3);
                     notifyOperators(var1, this, "commands.achievement.give.success.one", var4.z_(), var3.createChatComponent());
                  } else if (var6) {
                     var4.func_175145_a(var3);
                     notifyOperators(var1, this, "commands.achievement.take.success.one", var3.createChatComponent(), var4.z_());
                  }
               }
            }
         }
      }
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      if (var2.length == 1) {
         return getListOfStringsMatchingLastWord(var2, "give", "take");
      } else if (var2.length != 2) {
         return var2.length == 3 ? getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames()) : null;
      } else {
         ArrayList var4 = Lists.newArrayList();

         for (StatBase var6 : StatList.allStats) {
            var4.add(var6.statId);
         }

         return getListOfStringsMatchingLastWord(var2, var4);
      }
   }
}
