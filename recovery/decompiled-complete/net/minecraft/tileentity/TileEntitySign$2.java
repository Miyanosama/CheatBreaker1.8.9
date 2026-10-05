package net.minecraft.tileentity;

import net.minecraft.command.CommandResultStats$Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$1;
import org.apache.log4j.lf5.LF5Appender;

public class TileEntitySign$2 implements ICommandSender {
   public ComponentScatteredFeaturePieces$1 field_0001;
   public LF5Appender field_0003;

   @Override
   public BlockPos getPosition() {
      return this.field_0002.c;
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
   }

   @Override
   public World s_() {
      return this.field_0000.s_();
   }

   @Override
   public String z_() {
      return this.field_0000.z_();
   }

   public TileEntitySign$2(TileEntitySign var1, EntityPlayer var2) {
      this.field_0002 = var1;
      this.field_0000 = var2;
      super();
   }

   @Override
   public Entity p_() {
      return this.field_0000;
   }

   @Override
   public IChatComponent getDisplayName() {
      return this.field_0000.getDisplayName();
   }

   @Override
   public Vec3 q_() {
      return new Vec3(this.field_0002.c.getX() + 0.5, this.field_0002.c.getY() + 0.5, this.field_0002.c.getZ() + 0.5);
   }

   @Override
   public void setCommandStat(CommandResultStats$Type var1, int var2) {
      TileEntitySign.access$000(this.field_0002).setCommandStatScore(this, var1, var2);
   }

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return var1 <= 2;
   }

   @Override
   public boolean C_() {
      return false;
   }
}
