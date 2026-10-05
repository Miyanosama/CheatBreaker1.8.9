package net.minecraft.tileentity;

import io.netty.buffer.ByteBuf;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TileEntityCommandBlock extends TileEntity {
   public CommandBlockLogic commandBlockLogic = new CommandBlockLogic() {
      @Override
      public Vec3 q_() {
         return new Vec3(TileEntityCommandBlock.this.c.getX() + 0.5, TileEntityCommandBlock.this.c.getY() + 0.5, TileEntityCommandBlock.this.c.getZ() + 0.5);
      }

      @Override
      public void func_145757_a(ByteBuf var1) {
         var1.writeInt(TileEntityCommandBlock.this.c.getX());
         var1.writeInt(TileEntityCommandBlock.this.c.getY());
         var1.writeInt(TileEntityCommandBlock.this.c.getZ());
      }

      @Override
      public void updateCommand() {
         TileEntityCommandBlock.this.getWorld().h(TileEntityCommandBlock.this.c);
      }

      @Override
      public int func_145751_f() {
         return 0;
      }

      @Override
      public Entity p_() {
         return null;
      }

      @Override
      public BlockPos getPosition() {
         return TileEntityCommandBlock.this.c;
      }

      @Override
      public void setCommand(String var1) {
         super.setCommand(var1);
         TileEntityCommandBlock.this.markDirty();
      }

      @Override
      public World s_() {
         return TileEntityCommandBlock.this.getWorld();
      }
   };

   @Override
   public boolean func_183000_F() {
      return true;
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.commandBlockLogic.readDataFromNBT(var1);
   }

   public CommandBlockLogic getCommandBlockLogic() {
      return this.commandBlockLogic;
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      return new S35PacketUpdateTileEntity(this.c, 2, var1);
   }

   public CommandResultStats getCommandResultStats() {
      return this.commandBlockLogic.getCommandResultStats();
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      this.commandBlockLogic.writeDataToNBT(var1);
   }
}
