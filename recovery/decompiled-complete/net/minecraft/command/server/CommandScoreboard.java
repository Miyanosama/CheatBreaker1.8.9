package net.minecraft.command.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.netty.channel.local.LocalAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats$Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team$EnumVisible;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Corridor4;

public class CommandScoreboard extends CommandBase {
   public StructureNetherBridgePieces$Corridor4 field_0000;
   public LocalAddress field_0001;

   public void listObjectives(ICommandSender var1) {
      Scoreboard var2 = this.getScoreboard();
      Collection var3 = var2.getScoreObjectives();
      if (var3.size() <= 0) {
         throw new CommandException("commands.scoreboard.objectives.list.empty");
      } else {
         ChatComponentTranslation var4 = new ChatComponentTranslation("commands.scoreboard.objectives.list.count", var3.size());
         var4.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         var1.addChatMessage(var4);

         for (ScoreObjective var6 : var3) {
            var1.addChatMessage(
               new ChatComponentTranslation("commands.scoreboard.objectives.list.entry", var6.getName(), var6.getDisplayName(), var6.getCriteria().getName())
            );
         }
      }
   }

   public void method_00323(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      ScorePlayerTeam var5 = this.getTeam(var2[var3]);
      if (var5 != null) {
         ArrayList var6 = Lists.newArrayList(var5.getMembershipCollection());
         var1.setCommandStat(CommandResultStats$Type.AFFECTED_ENTITIES, var6.size());
         if (var6.isEmpty()) {
            throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", var5.getRegisteredName());
         }

         for (String var8 : var6) {
            var4.removePlayerFromTeam(var8, var5);
         }

         notifyOperators(var1, this, "commands.scoreboard.teams.empty.success", var6.size(), var5.getRegisteredName());
      }
   }

   public ScoreObjective getObjective(String var1, boolean var2) {
      Scoreboard var3 = this.getScoreboard();
      ScoreObjective var4 = var3.getObjective(var1);
      if (var4 == null) {
         throw new CommandException("commands.scoreboard.objectiveNotFound", var1);
      } else if (var2 && var4.getCriteria().isReadOnly()) {
         throw new CommandException("commands.scoreboard.objectiveReadOnly", var1);
      } else {
         return var4;
      }
   }

   public void method_00310(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      String var5 = getEntityName(var1, var2[var3++]);
      ScoreObjective var6 = this.getObjective(var2[var3++], true);
      String var7 = var2[var3++];
      String var8 = getEntityName(var1, var2[var3++]);
      ScoreObjective var9 = this.getObjective(var2[var3], false);
      if (var5.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", var5, 40);
      } else if (var8.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", var8, 40);
      } else {
         Score var10 = var4.getValueFromObjective(var5, var6);
         if (!var4.entityHasObjective(var8, var9)) {
            throw new CommandException("commands.scoreboard.players.operation.notFound", var9.getName(), var8);
         } else {
            Score var11 = var4.getValueFromObjective(var8, var9);
            if (var7.equals("+=")) {
               var10.setScorePoints(var10.getScorePoints() + var11.getScorePoints());
            } else if (var7.equals("-=")) {
               var10.setScorePoints(var10.getScorePoints() - var11.getScorePoints());
            } else if (var7.equals("*=")) {
               var10.setScorePoints(var10.getScorePoints() * var11.getScorePoints());
            } else if (var7.equals("/=")) {
               if (var11.getScorePoints() != 0) {
                  var10.setScorePoints(var10.getScorePoints() / var11.getScorePoints());
               }
            } else if (var7.equals("%=")) {
               if (var11.getScorePoints() != 0) {
                  var10.setScorePoints(var10.getScorePoints() % var11.getScorePoints());
               }
            } else if (var7.equals("=")) {
               var10.setScorePoints(var11.getScorePoints());
            } else if (var7.equals("<")) {
               var10.setScorePoints(Math.min(var10.getScorePoints(), var11.getScorePoints()));
            } else if (var7.equals(">")) {
               var10.setScorePoints(Math.max(var10.getScorePoints(), var11.getScorePoints()));
            } else {
               if (!var7.equals("><")) {
                  throw new CommandException("commands.scoreboard.players.operation.invalidOperation", var7);
               }

               int var12 = var10.getScorePoints();
               var10.setScorePoints(var11.getScorePoints());
               var11.setScorePoints(var12);
            }

            notifyOperators(var1, this, "commands.scoreboard.players.operation.success");
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.scoreboard.usage";
   }

   public void method_00320(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      HashSet var5 = Sets.newHashSet();
      HashSet var6 = Sets.newHashSet();
      if (var1 instanceof EntityPlayer && var3 == var2.length) {
         String var11 = getCommandSenderAsPlayer(var1).z_();
         if (var4.removePlayerFromTeams(var11)) {
            var5.add(var11);
         } else {
            var6.add(var11);
         }
      } else {
         while (var3 < var2.length) {
            String var7 = var2[var3++];
            if (var7.startsWith("@")) {
               for (Entity var9 : func_175763_c(var1, var7)) {
                  String var10 = getEntityName(var1, var9.aK().toString());
                  if (var4.removePlayerFromTeams(var10)) {
                     var5.add(var10);
                  } else {
                     var6.add(var10);
                  }
               }
            } else {
               String var8 = getEntityName(var1, var7);
               if (var4.removePlayerFromTeams(var8)) {
                  var5.add(var8);
               } else {
                  var6.add(var8);
               }
            }
         }
      }

      if (!var5.isEmpty()) {
         var1.setCommandStat(CommandResultStats$Type.AFFECTED_ENTITIES, var5.size());
         notifyOperators(var1, this, "commands.scoreboard.teams.leave.success", var5.size(), joinNiceString(var5.toArray(new String[var5.size()])));
      }

      if (!var6.isEmpty()) {
         throw new CommandException("commands.scoreboard.teams.leave.failure", var6.size(), joinNiceString(var6.toArray(new String[var6.size()])));
      }
   }

   public void func_175779_n(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      String var5 = getPlayerName(var1, var2[var3++]);
      if (var5.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", var5, 40);
      } else {
         ScoreObjective var6 = this.getObjective(var2[var3], false);
         if (var6.getCriteria() != IScoreObjectiveCriteria.TRIGGER) {
            throw new CommandException("commands.scoreboard.players.enable.noTrigger", var6.getName());
         } else {
            Score var7 = var4.getValueFromObjective(var5, var6);
            var7.setLocked(false);
            notifyOperators(var1, this, "commands.scoreboard.players.enable.success", var6.getName(), var5);
         }
      }
   }

   public void setObjectiveDisplay(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      String var5 = var2[var3++];
      int var6 = Scoreboard.getObjectiveDisplaySlotNumber(var5);
      ScoreObjective var7 = null;
      if (var2.length == 4) {
         var7 = this.getObjective(var2[var3], false);
      }

      if (var6 < 0) {
         throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", var5);
      } else {
         var4.setObjectiveInDisplaySlot(var6, var7);
         if (var7 != null) {
            notifyOperators(var1, this, "commands.scoreboard.objectives.setdisplay.successSet", Scoreboard.getObjectiveDisplaySlot(var6), var7.getName());
         } else {
            notifyOperators(var1, this, "commands.scoreboard.objectives.setdisplay.successCleared", Scoreboard.getObjectiveDisplaySlot(var6));
         }
      }
   }

   public void method_00324(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      String var5 = var2[var3++];
      HashSet var6 = Sets.newHashSet();
      HashSet var7 = Sets.newHashSet();
      if (var1 instanceof EntityPlayer && var3 == var2.length) {
         String var13 = getCommandSenderAsPlayer(var1).z_();
         if (var4.addPlayerToTeam(var13, var5)) {
            var6.add(var13);
         } else {
            var7.add(var13);
         }
      } else {
         while (var3 < var2.length) {
            String var8 = var2[var3++];
            if (var8.startsWith("@")) {
               for (Entity var10 : func_175763_c(var1, var8)) {
                  String var11 = getEntityName(var1, var10.aK().toString());
                  if (var4.addPlayerToTeam(var11, var5)) {
                     var6.add(var11);
                  } else {
                     var7.add(var11);
                  }
               }
            } else {
               String var9 = getEntityName(var1, var8);
               if (var4.addPlayerToTeam(var9, var5)) {
                  var6.add(var9);
               } else {
                  var7.add(var9);
               }
            }
         }
      }

      if (!var6.isEmpty()) {
         var1.setCommandStat(CommandResultStats$Type.AFFECTED_ENTITIES, var6.size());
         notifyOperators(var1, this, "commands.scoreboard.teams.join.success", var6.size(), var5, joinNiceString(var6.toArray(new String[var6.size()])));
      }

      if (!var7.isEmpty()) {
         throw new CommandException("commands.scoreboard.teams.join.failure", var7.size(), var5, joinNiceString(var7.toArray(new String[var7.size()])));
      }
   }

   public void method_00316(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      String var5 = getEntityName(var1, var2[var3++]);
      if (var5.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", var5, 40);
      } else {
         ScoreObjective var6 = this.getObjective(var2[var3++], false);
         if (!var4.entityHasObjective(var5, var6)) {
            throw new CommandException("commands.scoreboard.players.test.notFound", var6.getName(), var5);
         } else {
            int var7 = var2[var3].equals("*") ? Integer.MIN_VALUE : parseInt(var2[var3]);
            var3++;
            int var8 = var3 < var2.length && !var2[var3].equals("*") ? parseInt(var2[var3], var7) : Integer.MAX_VALUE;
            Score var9 = var4.getValueFromObjective(var5, var6);
            if (var9.getScorePoints() >= var7 && var9.getScorePoints() <= var8) {
               notifyOperators(var1, this, "commands.scoreboard.players.test.success", var9.getScorePoints(), var7, var8);
            } else {
               throw new CommandException("commands.scoreboard.players.test.failed", var9.getScorePoints(), var7, var8);
            }
         }
      }
   }

   public void addTeam(ICommandSender var1, String[] var2, int var3) {
      String var4 = var2[var3++];
      Scoreboard var5 = this.getScoreboard();
      if (var5.getTeam(var4) != null) {
         throw new CommandException("commands.scoreboard.teams.add.alreadyExists", var4);
      } else if (var4.length() > 16) {
         throw new SyntaxErrorException("commands.scoreboard.teams.add.tooLong", var4, 16);
      } else if (var4.length() == 0) {
         throw new WrongUsageException("commands.scoreboard.teams.add.usage");
      } else {
         if (var2.length > var3) {
            String var6 = getChatComponentFromNthArg(var1, var2, var3).getUnformattedText();
            if (var6.length() > 32) {
               throw new SyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", var6, 32);
            }

            if (var6.length() > 0) {
               var5.createTeam(var4).setTeamName(var6);
            } else {
               var5.createTeam(var4);
            }
         } else {
            var5.createTeam(var4);
         }

         notifyOperators(var1, this, "commands.scoreboard.teams.add.success", var4);
      }
   }

   public void setPlayer(ICommandSender var1, String[] var2, int var3) {
      String var4 = var2[var3 - 1];
      int var5 = var3;
      String var6 = getEntityName(var1, var2[var3++]);
      if (var6.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", var6, 40);
      } else {
         ScoreObjective var7 = this.getObjective(var2[var3++], true);
         int var8 = var4.equalsIgnoreCase("set") ? parseInt(var2[var3++]) : parseInt(var2[var3++], 0);
         if (var2.length > var3) {
            Entity var9 = getEntity(var1, var2[var5]);

            try {
               NBTTagCompound var10 = JsonToNBT.getTagFromJson(buildString(var2, var3));
               NBTTagCompound var11 = new NBTTagCompound();
               var9.e(var11);
               if (!NBTUtil.func_181123_a(var10, var11, true)) {
                  throw new CommandException("commands.scoreboard.players.set.tagMismatch", var6);
               }
            } catch (NBTException var12) {
               throw new CommandException("commands.scoreboard.players.set.tagError", var12.getMessage());
            }
         }

         Scoreboard var16 = this.getScoreboard();
         Score var17 = var16.getValueFromObjective(var6, var7);
         if (var4.equalsIgnoreCase("set")) {
            var17.setScorePoints(var8);
         } else if (var4.equalsIgnoreCase("add")) {
            var17.increseScore(var8);
         } else {
            var17.decreaseScore(var8);
         }

         notifyOperators(var1, this, "commands.scoreboard.players.set.success", var7.getName(), var6, var17.getScorePoints());
      }
   }

   public void removeObjective(ICommandSender var1, String var2) {
      Scoreboard var3 = this.getScoreboard();
      ScoreObjective var4 = this.getObjective(var2, false);
      var3.removeObjective(var4);
      notifyOperators(var1, this, "commands.scoreboard.objectives.remove.success", var2);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   public void method_00303(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      if (var2.length > var3) {
         String var5 = getEntityName(var1, var2[var3]);
         Map var6 = var4.getObjectivesForEntity(var5);
         var1.setCommandStat(CommandResultStats$Type.QUERY_RESULT, var6.size());
         if (var6.size() <= 0) {
            throw new CommandException("commands.scoreboard.players.list.player.empty", var5);
         }

         ChatComponentTranslation var7 = new ChatComponentTranslation("commands.scoreboard.players.list.player.count", var6.size(), var5);
         var7.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         var1.addChatMessage(var7);

         for (Score var9 : var6.values()) {
            var1.addChatMessage(
               new ChatComponentTranslation(
                  "commands.scoreboard.players.list.player.entry", var9.getScorePoints(), var9.getObjective().getDisplayName(), var9.getObjective().getName()
               )
            );
         }
      } else {
         Collection var10 = var4.getObjectiveNames();
         var1.setCommandStat(CommandResultStats$Type.QUERY_RESULT, var10.size());
         if (var10.size() <= 0) {
            throw new CommandException("commands.scoreboard.players.list.empty");
         }

         ChatComponentTranslation var11 = new ChatComponentTranslation("commands.scoreboard.players.list.count", var10.size());
         var11.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         var1.addChatMessage(var11);
         var1.addChatMessage(new ChatComponentText(joinNiceString(var10.toArray())));
      }
   }

   public void addObjective(ICommandSender var1, String[] var2, int var3) {
      String var4 = var2[var3++];
      String var5 = var2[var3++];
      Scoreboard var6 = this.getScoreboard();
      IScoreObjectiveCriteria var7 = IScoreObjectiveCriteria.INSTANCES.get(var5);
      if (var7 == null) {
         throw new WrongUsageException("commands.scoreboard.objectives.add.wrongType", var5);
      } else if (var6.getObjective(var4) != null) {
         throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", var4);
      } else if (var4.length() > 16) {
         throw new SyntaxErrorException("commands.scoreboard.objectives.add.tooLong", var4, 16);
      } else if (var4.length() == 0) {
         throw new WrongUsageException("commands.scoreboard.objectives.add.usage");
      } else {
         if (var2.length > var3) {
            String var8 = getChatComponentFromNthArg(var1, var2, var3).getUnformattedText();
            if (var8.length() > 32) {
               throw new SyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", var8, 32);
            }

            if (var8.length() > 0) {
               var6.addScoreObjective(var4, var7).setDisplayName(var8);
            } else {
               var6.addScoreObjective(var4, var7);
            }
         } else {
            var6.addScoreObjective(var4, var7);
         }

         notifyOperators(var1, this, "commands.scoreboard.objectives.add.success", var4);
      }
   }

   public boolean func_175780_b(ICommandSender var1, String[] var2) {
      int var3 = -1;

      for (int var4 = 0; var4 < var2.length; var4++) {
         if (this.isUsernameIndex(var2, var4) && "*".equals(var2[var4])) {
            if (var3 >= 0) {
               throw new CommandException("commands.scoreboard.noMultiWildcard");
            }

            var3 = var4;
         }
      }

      if (var3 < 0) {
         return false;
      } else {
         ArrayList var12 = Lists.newArrayList(this.getScoreboard().getObjectiveNames());
         String var5 = var2[var3];
         ArrayList var6 = Lists.newArrayList();

         for (String var8 : var12) {
            var2[var3] = var8;

            try {
               this.processCommand(var1, var2);
               var6.add(var8);
            } catch (CommandException var11) {
               ChatComponentTranslation var10 = new ChatComponentTranslation(var11.getMessage(), var11.getErrorObjects());
               var10.getChatStyle().setColor(EnumChatFormatting.RED);
               var1.addChatMessage(var10);
            }
         }

         var2[var3] = var5;
         var1.setCommandStat(CommandResultStats$Type.AFFECTED_ENTITIES, var6.size());
         if (var6.size() == 0) {
            throw new WrongUsageException("commands.scoreboard.allMatchesFailed");
         } else {
            return true;
         }
      }
   }

   public List<String> func_147184_a(boolean var1) {
      Collection var2 = this.getScoreboard().getScoreObjectives();
      ArrayList var3 = Lists.newArrayList();

      for (ScoreObjective var5 : var2) {
         if (!var1 || !var5.getCriteria().isReadOnly()) {
            var3.add(var5.getName());
         }
      }

      return var3;
   }

   @Override
   public String getCommandName() {
      return "scoreboard";
   }

   public void method_00314(ICommandSender var1, String[] var2, int var3) {
      ScorePlayerTeam var4 = this.getTeam(var2[var3++]);
      if (var4 != null) {
         String var5 = var2[var3++].toLowerCase();
         if (!var5.equalsIgnoreCase("color")
            && !var5.equalsIgnoreCase("friendlyfire")
            && !var5.equalsIgnoreCase("seeFriendlyInvisibles")
            && !var5.equalsIgnoreCase("nametagVisibility")
            && !var5.equalsIgnoreCase("deathMessageVisibility")) {
            throw new WrongUsageException("commands.scoreboard.teams.option.usage");
         }

         if (var2.length == 4) {
            if (var5.equalsIgnoreCase("color")) {
               throw new WrongUsageException(
                  "commands.scoreboard.teams.option.noValue", var5, joinNiceStringFromCollection(EnumChatFormatting.getValidValues(true, false))
               );
            }

            if (!var5.equalsIgnoreCase("friendlyfire") && !var5.equalsIgnoreCase("seeFriendlyInvisibles")) {
               if (!var5.equalsIgnoreCase("nametagVisibility") && !var5.equalsIgnoreCase("deathMessageVisibility")) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.usage");
               }

               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", var5, joinNiceString(Team$EnumVisible.func_178825_a()));
            }

            throw new WrongUsageException("commands.scoreboard.teams.option.noValue", var5, joinNiceStringFromCollection(Arrays.asList("true", "false")));
         }

         String var6 = var2[var3];
         if (var5.equalsIgnoreCase("color")) {
            EnumChatFormatting var7 = EnumChatFormatting.getValueByName(var6);
            if (var7 == null || var7.isFancyStyling()) {
               throw new WrongUsageException(
                  "commands.scoreboard.teams.option.noValue", var5, joinNiceStringFromCollection(EnumChatFormatting.getValidValues(true, false))
               );
            }

            var4.setChatFormat(var7);
            var4.setNamePrefix(var7.toString());
            var4.setNameSuffix(EnumChatFormatting.RESET.toString());
         } else if (var5.equalsIgnoreCase("friendlyfire")) {
            if (!var6.equalsIgnoreCase("true") && !var6.equalsIgnoreCase("false")) {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", var5, joinNiceStringFromCollection(Arrays.asList("true", "false")));
            }

            var4.setAllowFriendlyFire(var6.equalsIgnoreCase("true"));
         } else if (var5.equalsIgnoreCase("seeFriendlyInvisibles")) {
            if (!var6.equalsIgnoreCase("true") && !var6.equalsIgnoreCase("false")) {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", var5, joinNiceStringFromCollection(Arrays.asList("true", "false")));
            }

            var4.setSeeFriendlyInvisiblesEnabled(var6.equalsIgnoreCase("true"));
         } else if (var5.equalsIgnoreCase("nametagVisibility")) {
            Team$EnumVisible var10 = Team$EnumVisible.func_178824_a(var6);
            if (var10 == null) {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", var5, joinNiceString(Team$EnumVisible.func_178825_a()));
            }

            var4.setNameTagVisibility(var10);
         } else if (var5.equalsIgnoreCase("deathMessageVisibility")) {
            Team$EnumVisible var11 = Team$EnumVisible.func_178824_a(var6);
            if (var11 == null) {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", var5, joinNiceString(Team$EnumVisible.func_178825_a()));
            }

            var4.setDeathMessageVisibility(var11);
         }

         notifyOperators(var1, this, "commands.scoreboard.teams.option.success", var5, var4.getRegisteredName(), var6);
      }
   }

   public void method_00309(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      String var5 = getEntityName(var1, var2[var3++]);
      if (var2.length > var3) {
         ScoreObjective var6 = this.getObjective(var2[var3++], false);
         var4.removeObjectiveFromEntity(var5, var6);
         notifyOperators(var1, this, "commands.scoreboard.players.resetscore.success", var6.getName(), var5);
      } else {
         var4.removeObjectiveFromEntity(var5, (ScoreObjective)null);
         notifyOperators(var1, this, "commands.scoreboard.players.reset.success", var5);
      }
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return !var1[0].equalsIgnoreCase("players")
         ? (var1[0].equalsIgnoreCase("teams") ? var2 == 2 : false)
         : (var1.length > 1 && var1[1].equalsIgnoreCase("operation") ? var2 == 2 || var2 == 5 : var2 == 2);
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      if (var2.length == 1) {
         return getListOfStringsMatchingLastWord(var2, "objectives", "players", "teams");
      } else {
         if (var2[0].equalsIgnoreCase("objectives")) {
            if (var2.length == 2) {
               return getListOfStringsMatchingLastWord(var2, "list", "add", "remove", "setdisplay");
            }

            if (var2[1].equalsIgnoreCase("add")) {
               if (var2.length == 4) {
                  Set var4 = IScoreObjectiveCriteria.INSTANCES.keySet();
                  return getListOfStringsMatchingLastWord(var2, var4);
               }
            } else if (var2[1].equalsIgnoreCase("remove")) {
               if (var2.length == 3) {
                  return getListOfStringsMatchingLastWord(var2, this.func_147184_a(false));
               }
            } else if (var2[1].equalsIgnoreCase("setdisplay")) {
               if (var2.length == 3) {
                  return getListOfStringsMatchingLastWord(var2, Scoreboard.getDisplaySlotStrings());
               }

               if (var2.length == 4) {
                  return getListOfStringsMatchingLastWord(var2, this.func_147184_a(false));
               }
            }
         } else if (var2[0].equalsIgnoreCase("players")) {
            if (var2.length == 2) {
               return getListOfStringsMatchingLastWord(var2, "set", "add", "remove", "reset", "list", "enable", "test", "operation");
            }

            if (!var2[1].equalsIgnoreCase("set")
               && !var2[1].equalsIgnoreCase("add")
               && !var2[1].equalsIgnoreCase("remove")
               && !var2[1].equalsIgnoreCase("reset")) {
               if (var2[1].equalsIgnoreCase("enable")) {
                  if (var2.length == 3) {
                     return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
                  }

                  if (var2.length == 4) {
                     return getListOfStringsMatchingLastWord(var2, this.func_175782_e());
                  }
               } else if (!var2[1].equalsIgnoreCase("list") && !var2[1].equalsIgnoreCase("test")) {
                  if (var2[1].equalsIgnoreCase("operation")) {
                     if (var2.length == 3) {
                        return getListOfStringsMatchingLastWord(var2, this.getScoreboard().getObjectiveNames());
                     }

                     if (var2.length == 4) {
                        return getListOfStringsMatchingLastWord(var2, this.func_147184_a(true));
                     }

                     if (var2.length == 5) {
                        return getListOfStringsMatchingLastWord(var2, "+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><");
                     }

                     if (var2.length == 6) {
                        return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
                     }

                     if (var2.length == 7) {
                        return getListOfStringsMatchingLastWord(var2, this.func_147184_a(false));
                     }
                  }
               } else {
                  if (var2.length == 3) {
                     return getListOfStringsMatchingLastWord(var2, this.getScoreboard().getObjectiveNames());
                  }

                  if (var2.length == 4 && var2[1].equalsIgnoreCase("test")) {
                     return getListOfStringsMatchingLastWord(var2, this.func_147184_a(false));
                  }
               }
            } else {
               if (var2.length == 3) {
                  return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
               }

               if (var2.length == 4) {
                  return getListOfStringsMatchingLastWord(var2, this.func_147184_a(true));
               }
            }
         } else if (var2[0].equalsIgnoreCase("teams")) {
            if (var2.length == 2) {
               return getListOfStringsMatchingLastWord(var2, "add", "remove", "join", "leave", "empty", "list", "option");
            }

            if (var2[1].equalsIgnoreCase("join")) {
               if (var2.length == 3) {
                  return getListOfStringsMatchingLastWord(var2, this.getScoreboard().getTeamNames());
               }

               if (var2.length >= 4) {
                  return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
               }
            } else {
               if (var2[1].equalsIgnoreCase("leave")) {
                  return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
               }

               if (!var2[1].equalsIgnoreCase("empty") && !var2[1].equalsIgnoreCase("list") && !var2[1].equalsIgnoreCase("remove")) {
                  if (var2[1].equalsIgnoreCase("option")) {
                     if (var2.length == 3) {
                        return getListOfStringsMatchingLastWord(var2, this.getScoreboard().getTeamNames());
                     }

                     if (var2.length == 4) {
                        return getListOfStringsMatchingLastWord(
                           var2, "color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility"
                        );
                     }

                     if (var2.length == 5) {
                        if (var2[3].equalsIgnoreCase("color")) {
                           return getListOfStringsMatchingLastWord(var2, EnumChatFormatting.getValidValues(true, false));
                        }

                        if (var2[3].equalsIgnoreCase("nametagVisibility") || var2[3].equalsIgnoreCase("deathMessageVisibility")) {
                           return getListOfStringsMatchingLastWord(var2, Team$EnumVisible.func_178825_a());
                        }

                        if (var2[3].equalsIgnoreCase("friendlyfire") || var2[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                           return getListOfStringsMatchingLastWord(var2, "true", "false");
                        }
                     }
                  }
               } else if (var2.length == 3) {
                  return getListOfStringsMatchingLastWord(var2, this.getScoreboard().getTeamNames());
               }
            }
         }

         return null;
      }
   }

   public void method_00317(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      ScorePlayerTeam var5 = this.getTeam(var2[var3]);
      if (var5 != null) {
         var4.method_25157(var5);
         notifyOperators(var1, this, "commands.scoreboard.teams.remove.success", var5.getRegisteredName());
      }
   }

   public void method_00321(ICommandSender var1, String[] var2, int var3) {
      Scoreboard var4 = this.getScoreboard();
      if (var2.length > var3) {
         ScorePlayerTeam var5 = this.getTeam(var2[var3]);
         if (var5 == null) {
            return;
         }

         Collection var6 = var5.getMembershipCollection();
         var1.setCommandStat(CommandResultStats$Type.QUERY_RESULT, var6.size());
         if (var6.size() <= 0) {
            throw new CommandException("commands.scoreboard.teams.list.player.empty", var5.getRegisteredName());
         }

         ChatComponentTranslation var7 = new ChatComponentTranslation("commands.scoreboard.teams.list.player.count", var6.size(), var5.getRegisteredName());
         var7.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         var1.addChatMessage(var7);
         var1.addChatMessage(new ChatComponentText(joinNiceString(var6.toArray())));
      } else {
         Collection var9 = var4.getTeams();
         var1.setCommandStat(CommandResultStats$Type.QUERY_RESULT, var9.size());
         if (var9.size() <= 0) {
            throw new CommandException("commands.scoreboard.teams.list.empty");
         }

         ChatComponentTranslation var10 = new ChatComponentTranslation("commands.scoreboard.teams.list.count", var9.size());
         var10.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
         var1.addChatMessage(var10);

         for (ScorePlayerTeam var8 : var9) {
            var1.addChatMessage(
               new ChatComponentTranslation(
                  "commands.scoreboard.teams.list.entry", var8.getRegisteredName(), var8.getTeamName(), var8.getMembershipCollection().size()
               )
            );
         }
      }
   }

   public Scoreboard getScoreboard() {
      return MinecraftServer.getServer().worldServerForDimension(0).Z();
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (!this.func_175780_b(var1, var2)) {
         if (var2.length < 1) {
            throw new WrongUsageException("commands.scoreboard.usage");
         }

         if (var2[0].equalsIgnoreCase("objectives")) {
            if (var2.length == 1) {
               throw new WrongUsageException("commands.scoreboard.objectives.usage");
            }

            if (var2[1].equalsIgnoreCase("list")) {
               this.listObjectives(var1);
            } else if (var2[1].equalsIgnoreCase("add")) {
               if (var2.length < 4) {
                  throw new WrongUsageException("commands.scoreboard.objectives.add.usage");
               }

               this.addObjective(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("remove")) {
               if (var2.length != 3) {
                  throw new WrongUsageException("commands.scoreboard.objectives.remove.usage");
               }

               this.removeObjective(var1, var2[2]);
            } else {
               if (!var2[1].equalsIgnoreCase("setdisplay")) {
                  throw new WrongUsageException("commands.scoreboard.objectives.usage");
               }

               if (var2.length != 3 && var2.length != 4) {
                  throw new WrongUsageException("commands.scoreboard.objectives.setdisplay.usage");
               }

               this.setObjectiveDisplay(var1, var2, 2);
            }
         } else if (var2[0].equalsIgnoreCase("players")) {
            if (var2.length == 1) {
               throw new WrongUsageException("commands.scoreboard.players.usage");
            }

            if (var2[1].equalsIgnoreCase("list")) {
               if (var2.length > 3) {
                  throw new WrongUsageException("commands.scoreboard.players.list.usage");
               }

               this.method_00303(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("add")) {
               if (var2.length < 5) {
                  throw new WrongUsageException("commands.scoreboard.players.add.usage");
               }

               this.setPlayer(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("remove")) {
               if (var2.length < 5) {
                  throw new WrongUsageException("commands.scoreboard.players.remove.usage");
               }

               this.setPlayer(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("set")) {
               if (var2.length < 5) {
                  throw new WrongUsageException("commands.scoreboard.players.set.usage");
               }

               this.setPlayer(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("reset")) {
               if (var2.length != 3 && var2.length != 4) {
                  throw new WrongUsageException("commands.scoreboard.players.reset.usage");
               }

               this.method_00309(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("enable")) {
               if (var2.length != 4) {
                  throw new WrongUsageException("commands.scoreboard.players.enable.usage");
               }

               this.func_175779_n(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("test")) {
               if (var2.length != 5 && var2.length != 6) {
                  throw new WrongUsageException("commands.scoreboard.players.test.usage");
               }

               this.method_00316(var1, var2, 2);
            } else {
               if (!var2[1].equalsIgnoreCase("operation")) {
                  throw new WrongUsageException("commands.scoreboard.players.usage");
               }

               if (var2.length != 7) {
                  throw new WrongUsageException("commands.scoreboard.players.operation.usage");
               }

               this.method_00310(var1, var2, 2);
            }
         } else {
            if (!var2[0].equalsIgnoreCase("teams")) {
               throw new WrongUsageException("commands.scoreboard.usage");
            }

            if (var2.length == 1) {
               throw new WrongUsageException("commands.scoreboard.teams.usage");
            }

            if (var2[1].equalsIgnoreCase("list")) {
               if (var2.length > 3) {
                  throw new WrongUsageException("commands.scoreboard.teams.list.usage");
               }

               this.method_00321(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("add")) {
               if (var2.length < 3) {
                  throw new WrongUsageException("commands.scoreboard.teams.add.usage");
               }

               this.addTeam(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("remove")) {
               if (var2.length != 3) {
                  throw new WrongUsageException("commands.scoreboard.teams.remove.usage");
               }

               this.method_00317(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("empty")) {
               if (var2.length != 3) {
                  throw new WrongUsageException("commands.scoreboard.teams.empty.usage");
               }

               this.method_00323(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("join")) {
               if (var2.length < 4 && (var2.length != 3 || !(var1 instanceof EntityPlayer))) {
                  throw new WrongUsageException("commands.scoreboard.teams.join.usage");
               }

               this.method_00324(var1, var2, 2);
            } else if (var2[1].equalsIgnoreCase("leave")) {
               if (var2.length < 3 && !(var1 instanceof EntityPlayer)) {
                  throw new WrongUsageException("commands.scoreboard.teams.leave.usage");
               }

               this.method_00320(var1, var2, 2);
            } else {
               if (!var2[1].equalsIgnoreCase("option")) {
                  throw new WrongUsageException("commands.scoreboard.teams.usage");
               }

               if (var2.length != 4 && var2.length != 5) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.usage");
               }

               this.method_00314(var1, var2, 2);
            }
         }
      }
   }

   public ScorePlayerTeam getTeam(String var1) {
      Scoreboard var2 = this.getScoreboard();
      ScorePlayerTeam var3 = var2.getTeam(var1);
      if (var3 == null) {
         throw new CommandException("commands.scoreboard.teamNotFound", var1);
      } else {
         return var3;
      }
   }

   public List<String> func_175782_e() {
      Collection var1 = this.getScoreboard().getScoreObjectives();
      ArrayList var2 = Lists.newArrayList();

      for (ScoreObjective var4 : var1) {
         if (var4.getCriteria() == IScoreObjectiveCriteria.TRIGGER) {
            var2.add(var4.getName());
         }
      }

      return var2;
   }
}
