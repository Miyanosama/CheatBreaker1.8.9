package net.minecraft.command;

import io.netty.util.internal.PlatformDependent0$3;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.network.ServerStatusResponse$PlayerCountData$Serializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.WorldInfo;

public class CommandToggleDownfall extends CommandBase {
   public PlatformDependent0$3 field_0001;
   public ServerStatusResponse$PlayerCountData$Serializer field_0002;
   public ModelVillager field_0000;

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      this.toggleDownfall();
      notifyOperators(var1, this, "commands.downfall.success");
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.downfall.usage";
   }

   public void toggleDownfall() {
      WorldInfo var1 = MinecraftServer.getServer().worldServers[0].P();
      var1.setRaining(!var1.isRaining());
   }

   @Override
   public String getCommandName() {
      return "toggledownfall";
   }
}
