package net.minecraft.command.server;

import com.cheatbreaker.client.module.type.PotionCounterModule;
import io.netty.handler.ssl.SslHandler$3;
import javax.vecmath.Matrix3f;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import recovered.unidentified.UnidentifiedClass3897;

public class CommandSaveOff extends CommandBase {
   public SslHandler$3 field_0002;
   public Matrix3f field_0003;
   public UnidentifiedClass3897 field_0000;
   public PotionCounterModule field_0001;

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.save-off.usage";
   }

   @Override
   public String getCommandName() {
      return "save-off";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      MinecraftServer var3 = MinecraftServer.getServer();
      boolean var4 = false;

      for (int var5 = 0; var5 < var3.worldServers.length; var5++) {
         if (var3.worldServers[var5] != null) {
            WorldServer var6 = var3.worldServers[var5];
            if (!var6.disableLevelSaving) {
               var6.disableLevelSaving = true;
               var4 = true;
            }
         }
      }

      if (var4) {
         notifyOperators(var1, this, "commands.save.disabled");
      } else {
         throw new CommandException("commands.save-off.alreadyOff");
      }
   }
}
