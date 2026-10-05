package net.minecraft.command;

import com.cheatbreaker.client.util.friend.Status;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandResultStats$1 implements ICommandSender {
   public Status field_0001;
   public BossStatus field_0000;

   @Override
   public IChatComponent getDisplayName() {
      return this.field_0003.getDisplayName();
   }

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return true;
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
      this.field_0003.addChatMessage(var1);
   }

   @Override
   public void setCommandStat(CommandResultStats$Type var1, int var2) {
      this.field_0003.setCommandStat(var1, var2);
   }

   public CommandResultStats$1(CommandResultStats var1, ICommandSender var2) {
      this.field_0002 = var1;
      this.field_0003 = var2;
      super();
   }

   @Override
   public String z_() {
      return this.field_0003.z_();
   }

   @Override
   public boolean C_() {
      return this.field_0003.C_();
   }

   @Override
   public World s_() {
      return this.field_0003.s_();
   }

   @Override
   public Entity p_() {
      return this.field_0003.p_();
   }

   @Override
   public Vec3 q_() {
      return this.field_0003.q_();
   }

   @Override
   public BlockPos getPosition() {
      return this.field_0003.getPosition();
   }
}
