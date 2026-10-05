package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface ICommandSender {
   World s_();

   boolean C_();

   Entity p_();

   IChatComponent getDisplayName();

   String z_();

   void addChatMessage(IChatComponent var1);

   boolean canCommandSenderUseCommand(int var1, String var2);

   Vec3 q_();

   BlockPos getPosition();

   void setCommandStat(CommandResultStats.Type var1, int var2);
}
