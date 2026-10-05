package net.minecraft.network.rcon;

import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RConConsoleSource implements ICommandSender {
   public StringBuffer buffer = new StringBuffer();
   public static RConConsoleSource instance = new RConConsoleSource();

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return true;
   }

   @Override
   public World s_() {
      return MinecraftServer.getServer().s_();
   }

   @Override
   public String z_() {
      return "Rcon";
   }

   @Override
   public Entity p_() {
      return null;
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(0, 0, 0);
   }

   @Override
   public void setCommandStat(CommandResultStats.Type var1, int var2) {
   }

   @Override
   public Vec3 q_() {
      return new Vec3(0.0, 0.0, 0.0);
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
      this.buffer.append(var1.getUnformattedText());
   }

   @Override
   public boolean C_() {
      return true;
   }

   @Override
   public IChatComponent getDisplayName() {
      return new ChatComponentText(this.z_());
   }
}
