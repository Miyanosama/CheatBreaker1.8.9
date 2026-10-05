package net.minecraft.command.server;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.util.internal.logging.MessageFormatter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.stream.IngestServerTester$3;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import org.apache.log4j.lf5.viewer.FilteredLogTableModel;

public class CommandOp extends CommandBase {
   public IngestServerTester$3 field_0001;
   public FilteredLogTableModel field_0002;
   public MessageFormatter field_0000;

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length == 1 && var2[0].length() > 0) {
         MinecraftServer var3 = MinecraftServer.getServer();
         GameProfile var4 = var3.getPlayerProfileCache().getGameProfileForUsername(var2[0]);
         if (var4 == null) {
            throw new CommandException("commands.op.failed", var2[0]);
         } else {
            var3.getConfigurationManager().addOp(var4);
            notifyOperators(var1, this, "commands.op.success", var2[0]);
         }
      } else {
         throw new WrongUsageException("commands.op.usage");
      }
   }

   @Override
   public String getCommandName() {
      return "op";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      if (var2.length == 1) {
         String var4 = var2[var2.length - 1];
         ArrayList var5 = Lists.newArrayList();

         for (GameProfile var9 : MinecraftServer.getServer().getGameProfiles()) {
            if (!MinecraftServer.getServer().getConfigurationManager().canSendCommands(var9) && doesStringStartWith(var4, var9.getName())) {
               var5.add(var9.getName());
            }
         }

         return var5;
      } else {
         return null;
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.op.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }
}
