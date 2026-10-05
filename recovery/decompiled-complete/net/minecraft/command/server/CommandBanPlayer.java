package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.List;
import net.minecraft.client.Minecraft$18;
import net.minecraft.client.renderer.EnumFaceDirection$1;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.util.BlockPos;

public class CommandBanPlayer extends CommandBase {
   public Minecraft$18 field_0001;
   public RenderArrow field_0002;
   public EnumFaceDirection$1 field_0000;

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length >= 1 ? getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length >= 1 && var2[0].length() > 0) {
         MinecraftServer var3 = MinecraftServer.getServer();
         GameProfile var4 = var3.getPlayerProfileCache().getGameProfileForUsername(var2[0]);
         if (var4 == null) {
            throw new CommandException("commands.ban.failed", var2[0]);
         } else {
            String var5 = null;
            if (var2.length >= 2) {
               var5 = getChatComponentFromNthArg(var1, var2, 1).getUnformattedText();
            }

            UserListBansEntry var6 = new UserListBansEntry(var4, (Date)null, var1.z_(), (Date)null, var5);
            var3.getConfigurationManager().getBannedPlayers().addEntry(var6);
            EntityPlayerMP var7 = var3.getConfigurationManager().getPlayerByUsername(var2[0]);
            if (var7 != null) {
               var7.playerNetServerHandler.kickPlayerFromServer("You are banned from this server.");
            }

            notifyOperators(var1, this, "commands.ban.success", var2[0]);
         }
      } else {
         throw new WrongUsageException("commands.ban.usage");
      }
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender var1) {
      return MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isLanServer() && super.canCommandSenderUseCommand(var1);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.ban.usage";
   }

   @Override
   public String getCommandName() {
      return "ban";
   }
}
