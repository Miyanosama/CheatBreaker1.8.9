package net.minecraft.command;

import io.netty.handler.codec.spdy.SpdyHeaderBlockRawEncoder;
import java.util.List;
import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import org.apache.log4j.lf5.viewer.FilteredLogTableModel;

public class CommandServerKick extends CommandBase {
   public FilteredLogTableModel field_0001;
   public LayerWitherAura field_0002;
   public SpdyHeaderBlockRawEncoder field_0000;

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.kick.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length > 0 && var2[0].length() > 1) {
         EntityPlayerMP var3 = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(var2[0]);
         String var4 = "Kicked by an operator.";
         boolean var5 = false;
         if (var3 == null) {
            throw new PlayerNotFoundException();
         } else {
            if (var2.length >= 2) {
               var4 = getChatComponentFromNthArg(var1, var2, 1).getUnformattedText();
               var5 = true;
            }

            var3.playerNetServerHandler.kickPlayerFromServer(var4);
            if (var5) {
               notifyOperators(var1, this, "commands.kick.success.reason", var3.z_(), var4);
            } else {
               notifyOperators(var1, this, "commands.kick.success", var3.z_());
            }
         }
      } else {
         throw new WrongUsageException("commands.kick.usage");
      }
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length >= 1 ? getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public String getCommandName() {
      return "kick";
   }
}
