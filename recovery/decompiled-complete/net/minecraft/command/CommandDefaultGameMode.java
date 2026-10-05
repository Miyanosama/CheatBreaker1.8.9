package net.minecraft.command;

import io.netty.channel.group.ChannelMatchers$InvertMatcher;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.WorldSettings$GameType;
import net.optifine.NaturalTextures;
import recovered.unidentified.UnidentifiedClass0347;

public class CommandDefaultGameMode extends CommandGameMode {
   public NaturalTextures field_0002;
   public UnidentifiedClass0347 field_0003;
   public ChannelMatchers$InvertMatcher field_0000;
   public WeightedRandomChestContent field_0001;
   public EntityTNTPrimed field_0004;

   @Override
   public String getCommandName() {
      return "defaultgamemode";
   }

   public void setGameType(WorldSettings$GameType var1) {
      MinecraftServer var2 = MinecraftServer.getServer();
      var2.setGameType(var1);
      if (var2.getForceGamemode()) {
         for (EntityPlayerMP var4 : MinecraftServer.getServer().getConfigurationManager().getPlayerList()) {
            var4.setGameType(var1);
            var4.O = 0.0F;
         }
      }
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length <= 0) {
         throw new WrongUsageException("commands.defaultgamemode.usage");
      } else {
         WorldSettings$GameType var3 = this.getGameModeFromCommand(var1, var2[0]);
         this.setGameType(var3);
         notifyOperators(var1, this, "commands.defaultgamemode.success", new ChatComponentTranslation("gameMode." + var3.getName()));
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.defaultgamemode.usage";
   }
}
