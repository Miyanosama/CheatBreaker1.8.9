package net.minecraft.tileentity;

import net.minecraft.command.CommandResultStats$Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TileEntitySign$1 implements ICommandSender {
   @Override
   public boolean C_() {
      return false;
   }

   public TileEntitySign$1(TileEntitySign var1) {
      this.field_0000 = var1;
      super();
   }

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return true;
   }

   @Override
   public BlockPos getPosition() {
      return this.field_0000.c;
   }

   @Override
   public void setCommandStat(CommandResultStats$Type var1, int var2) {
   }

   @Override
   public Entity p_() {
      return null;
   }

   @Override
   public IChatComponent getDisplayName() {
      return new ChatComponentText(this.z_());
   }

   @Override
   public String z_() {
      return "Sign";
   }

   @Override
   public Vec3 q_() {
      return new Vec3(this.field_0000.c.getX() + 0.5, this.field_0000.c.getY() + 0.5, this.field_0000.c.getZ() + 0.5);
   }

   @Override
   public World s_() {
      return this.field_0000.b;
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
   }
}
