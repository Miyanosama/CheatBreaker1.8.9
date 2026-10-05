package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandSetDefaultSpawnpoint extends CommandBase {
   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException {
      BlockPos var3;
      if (var2.length == 0) {
         var3 = getCommandSenderAsPlayer(var1).getPosition();
      } else {
         if (var2.length != 3 || var1.s_() == null) {
            throw new WrongUsageException("commands.setworldspawn.usage");
         }

         var3 = parseBlockPos(var1, var2, 0, true);
      }

      var1.s_().B(var3);
      MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(new S05PacketSpawnPosition(var3));
      notifyOperators(var1, this, "commands.setworldspawn.success", var3.getX(), var3.getY(), var3.getZ());
   }

   @Override
   public String getCommandName() {
      return "setworldspawn";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length > 0 && var2.length <= 3 ? method_02118(var2, 0, var3) : null;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.setworldspawn.usage";
   }
}
