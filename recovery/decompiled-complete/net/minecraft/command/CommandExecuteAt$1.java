package net.minecraft.command;

import net.minecraft.client.stream.TwitchStream$1;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandExecuteAt$1 implements ICommandSender {
   public TwitchStream$1 field_0005;

   @Override
   public void addChatMessage(IChatComponent var1) {
      this.field_174802_b.addChatMessage(var1);
   }

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return this.field_174802_b.canCommandSenderUseCommand(var1, var2);
   }

   @Override
   public Entity p_() {
      return this.field_174804_a;
   }

   @Override
   public void setCommandStat(CommandResultStats$Type var1, int var2) {
      this.field_174804_a.setCommandStat(var1, var2);
   }

   @Override
   public BlockPos getPosition() {
      return this.field_174803_c;
   }

   @Override
   public boolean C_() {
      MinecraftServer var1 = MinecraftServer.getServer();
      return var1 == null || var1.worldServers[0].Q().getBoolean("commandBlockOutput");
   }

   @Override
   public IChatComponent getDisplayName() {
      return this.field_174804_a.getDisplayName();
   }

   @Override
   public Vec3 q_() {
      return new Vec3(this.field_174800_d, this.field_174801_e, this.field_174798_f);
   }

   @Override
   public String z_() {
      return this.field_174804_a.z_();
   }

   @Override
   public World s_() {
      return this.field_174804_a.o;
   }

   public CommandExecuteAt$1(CommandExecuteAt var1, Entity var2, ICommandSender var3, BlockPos var4, double var5, double var7, double var9) {
      this.field_174799_g = var1;
      this.field_174804_a = var2;
      this.field_174802_b = var3;
      this.field_174803_c = var4;
      this.field_174800_d = var5;
      this.field_174801_e = var7;
      this.field_174798_f = var9;
      super();
   }
}
