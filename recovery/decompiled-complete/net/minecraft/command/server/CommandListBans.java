package net.minecraft.command.server;

import java.util.List;
import net.minecraft.client.renderer.entity.RenderPigZombie;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.gen.layer.GenLayerEdge$Mode;
import net.optifine.shaders.config.ShaderMacro;

public class CommandListBans extends CommandBase {
   public ContainerChest field_0002;
   public ShaderMacro field_0003;
   public RenderPigZombie field_0000;
   public GenLayerEdge$Mode field_0001;

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length >= 1 && var2[0].equalsIgnoreCase("ips")) {
         var1.addChatMessage(
            new ChatComponentTranslation("commands.banlist.ips", MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getKeys().length)
         );
         var1.addChatMessage(new ChatComponentText(joinNiceString(MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getKeys())));
      } else {
         var1.addChatMessage(
            new ChatComponentTranslation("commands.banlist.players", MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().getKeys().length)
         );
         var1.addChatMessage(new ChatComponentText(joinNiceString(MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().getKeys())));
      }
   }

   @Override
   public String getCommandName() {
      return "banlist";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.banlist.usage";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1 ? getListOfStringsMatchingLastWord(var2, "players", "ips") : null;
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender var1) {
      return (
            MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isLanServer()
               || MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isLanServer()
         )
         && super.canCommandSenderUseCommand(var1);
   }
}
