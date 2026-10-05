package net.minecraft.command;

import com.cheatbreaker.client.ui.mainmenu.NewsMenu;
import io.netty.bootstrap.AbstractBootstrap;
import java.util.List;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.renderer.RenderGlobal$ContainerLocalRenderInformation;
import net.minecraft.client.resources.ResourcePackRepository$1;
import net.minecraft.command.server.CommandEmote;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandSetSpawnpoint extends CommandBase {
   public AbstractBootstrap field_0002;
   public CommandEmote field_0004;
   public RenderGlobal$ContainerLocalRenderInformation field_0000;
   public GuiGameOver field_0001;
   public ResourcePackRepository$1 field_0005;
   public NewsMenu field_0003;

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length > 1 && var2.length < 4) {
         throw new WrongUsageException("commands.spawnpoint.usage");
      } else {
         EntityPlayerMP var3 = var2.length > 0 ? getPlayer(var1, var2[0]) : getCommandSenderAsPlayer(var1);
         BlockPos var4 = var2.length > 3 ? parseBlockPos(var1, var2, 1, true) : var3.getPosition();
         if (var3.o != null) {
            var3.setSpawnPoint(var4, true);
            notifyOperators(var1, this, "commands.spawnpoint.success", var3.z_(), var4.getX(), var4.getY(), var4.getZ());
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.spawnpoint.usage";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames())
         : (var2.length > 1 && var2.length <= 4 ? method_02118(var2, 1, var3) : null);
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "spawnpoint";
   }
}
