package net.minecraft.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class CommandShowSeed extends CommandBase {
   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "seed";
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.seed.usage";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      net.minecraft.world.World var3 = var1 instanceof EntityPlayer ? ((EntityPlayer)var1).o : MinecraftServer.getServer().worldServerForDimension(0);
      var1.addChatMessage(new ChatComponentTranslation("commands.seed.success", ((World)var3).J()));
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender var1) {
      return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(var1);
   }
}
