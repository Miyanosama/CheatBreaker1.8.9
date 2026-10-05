package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleXYRoom;
import org.apache.log4j.AppenderSkeleton;

public class CommandPublishLocalServer extends CommandBase {
   public ItemPickaxe field_0002;
   public AppenderSkeleton field_0003;
   public PacketBuffer field_0000;
   public StructureOceanMonumentPieces$DoubleXYRoom field_0001;

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      String var3 = MinecraftServer.getServer().shareToLAN(WorldSettings$GameType.SURVIVAL, false);
      if (var3 != null) {
         notifyOperators(var1, this, "commands.publish.started", var3);
      } else {
         notifyOperators(var1, this, "commands.publish.failed");
      }
   }

   @Override
   public String getCommandName() {
      return "publish";
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.publish.usage";
   }
}
