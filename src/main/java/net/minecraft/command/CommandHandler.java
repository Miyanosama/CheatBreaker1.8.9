package net.minecraft.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandHandler implements ICommandManager {
   public Set<ICommand> commandSet;
   public Map<String, ICommand> commandMap = Maps.newHashMap();
   public static Logger logger = LogManager.getLogger();

   public int getUsernameIndex(ICommand var1, String[] var2) {
      if (var1 == null) {
         return -1;
      } else {
         for (int var3 = 0; var3 < var2.length; var3++) {
            if (var1.isUsernameIndex(var2, var3) && PlayerSelector.matchesMultiplePlayers(var2[var3])) {
               return var3;
            }
         }

         return -1;
      }
   }

   public CommandHandler() {
      this.commandSet = Sets.newHashSet();
   }

   @Override
   public List<ICommand> getPossibleCommands(ICommandSender var1) {
      ArrayList var2 = Lists.newArrayList();

      for (ICommand var4 : this.commandSet) {
         if (var4.canCommandSenderUseCommand(var1)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   @Override
   public List<String> getTabCompletionOptions(ICommandSender var1, String var2, BlockPos var3) {
      String[] var4 = var2.split(" ", -1);
      String var5 = var4[0];
      if (var4.length == 1) {
         ArrayList var9 = Lists.newArrayList();

         for (Entry var8 : this.commandMap.entrySet()) {
            if (CommandBase.doesStringStartWith(var5, (String)var8.getKey()) && ((ICommand)var8.getValue()).canCommandSenderUseCommand(var1)) {
               var9.add(var8.getKey());
            }
         }

         return var9;
      } else {
         if (var4.length > 1) {
            ICommand var6 = this.commandMap.get(var5);
            if (var6 != null && var6.canCommandSenderUseCommand(var1)) {
               return var6.addTabCompletionOptions(var1, dropFirstString(var4), var3);
            }
         }

         return null;
      }
   }

   @Override
   public Map<String, ICommand> getCommands() {
      return this.commandMap;
   }

   public boolean tryExecute(ICommandSender var1, String[] var2, ICommand var3, String var4) {
      try {
         var3.processCommand(var1, var2);
         return true;
      } catch (WrongUsageException var7) {
         ChatComponentTranslation var11 = new ChatComponentTranslation(
            "commands.generic.usage", new ChatComponentTranslation(var7.getMessage(), var7.getErrorObjects())
         );
         var11.getChatStyle().setColor(EnumChatFormatting.RED);
         var1.addChatMessage(var11);
      } catch (CommandException var8) {
         ChatComponentTranslation var10 = new ChatComponentTranslation(var8.getMessage(), var8.getErrorObjects());
         var10.getChatStyle().setColor(EnumChatFormatting.RED);
         var1.addChatMessage(var10);
      } catch (Throwable var9) {
         ChatComponentTranslation var6 = new ChatComponentTranslation("commands.generic.exception");
         var6.getChatStyle().setColor(EnumChatFormatting.RED);
         var1.addChatMessage(var6);
         logger.warn("Couldn't process command: '" + var4 + "'");
      }

      return false;
   }

   @Override
   public int executeCommand(ICommandSender var1, String var2) {
      var2 = var2.trim();
      if (var2.startsWith("/")) {
         var2 = var2.substring(1);
      }

      String[] var3 = var2.split(" ");
      String var4 = var3[0];
      var3 = dropFirstString(var3);
      ICommand var5 = this.commandMap.get(var4);
      int var6 = this.getUsernameIndex(var5, var3);
      int var7 = 0;
      if (var5 == null) {
         ChatComponentTranslation var8 = new ChatComponentTranslation("commands.generic.notFound");
         var8.getChatStyle().setColor(EnumChatFormatting.RED);
         var1.addChatMessage(var8);
      } else if (var5.canCommandSenderUseCommand(var1)) {
         if (var6 > -1) {
            List var14 = PlayerSelector.matchEntities(var1, var3[var6], Entity.class);
            String var9 = var3[var6];
            var1.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, var14.size());

            for (Entity var11 : (Iterable<Entity>)(Iterable<?>)(var14)) {
               var3[var6] = var11.aK().toString();
               if (this.tryExecute(var1, var3, var5, var2)) {
                  var7++;
               }
            }

            var3[var6] = var9;
         } else {
            var1.setCommandStat(CommandResultStats.Type.AFFECTED_ENTITIES, 1);
            if (this.tryExecute(var1, var3, var5, var2)) {
               var7++;
            }
         }
      } else {
         ChatComponentTranslation var15 = new ChatComponentTranslation("commands.generic.permission");
         var15.getChatStyle().setColor(EnumChatFormatting.RED);
         var1.addChatMessage(var15);
      }

      var1.setCommandStat(CommandResultStats.Type.SUCCESS_COUNT, var7);
      return var7;
   }

   public static String[] dropFirstString(String[] var0) {
      String[] var1 = new String[var0.length - 1];
      System.arraycopy(var0, 1, var1, 0, var0.length - 1);
      return var1;
   }

   public ICommand a(ICommand var1) {
      this.commandMap.put(var1.getCommandName(), var1);
      this.commandSet.add(var1);

      for (String var3 : var1.getCommandAliases()) {
         ICommand var4 = this.commandMap.get(var3);
         if (var4 == null || !var4.getCommandName().equals(var3)) {
            this.commandMap.put(var3, var1);
         }
      }

      return var1;
   }
}
