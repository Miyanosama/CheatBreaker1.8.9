package net.minecraft.command.server;

import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

public class CommandSaveAll extends CommandBase {
   public GuiFlatPresets field_0000;
   public EntityPainting field_0001;

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      MinecraftServer var3 = MinecraftServer.getServer();
      var1.addChatMessage(new ChatComponentTranslation("commands.save.start"));
      if (var3.getConfigurationManager() != null) {
         var3.getConfigurationManager().saveAllPlayerData();
      }

      try {
         for (int var4 = 0; var4 < var3.worldServers.length; var4++) {
            if (var3.worldServers[var4] != null) {
               WorldServer var5 = var3.worldServers[var4];
               boolean var6 = var5.disableLevelSaving;
               var5.disableLevelSaving = false;
               var5.saveAllChunks(true, (IProgressUpdate)null);
               var5.disableLevelSaving = var6;
            }
         }

         if (var2.length > 0 && "flush".equals(var2[0])) {
            var1.addChatMessage(new ChatComponentTranslation("commands.save.flushStart"));

            for (int var8 = 0; var8 < var3.worldServers.length; var8++) {
               if (var3.worldServers[var8] != null) {
                  WorldServer var9 = var3.worldServers[var8];
                  boolean var10 = var9.disableLevelSaving;
                  var9.disableLevelSaving = false;
                  var9.method_03794();
                  var9.disableLevelSaving = var10;
               }
            }

            var1.addChatMessage(new ChatComponentTranslation("commands.save.flushEnd"));
         }
      } catch (MinecraftException var7) {
         notifyOperators(var1, this, "commands.save.failed", var7.getMessage());
         return;
      }

      notifyOperators(var1, this, "commands.save.success");
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.save.usage";
   }

   @Override
   public String getCommandName() {
      return "save-all";
   }
}
