package net.minecraft.tileentity;

import javazoom.jl.decoder.BitReserve;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.command.server.CommandBlockLogic$2;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.EnumChatFormatting;

public class TileEntityCommandBlock extends TileEntity {
   public CommandBlockLogic$2 field_0002;
   public EnumChatFormatting field_0004;
   public CommandBlockLogic commandBlockLogic = new TileEntityCommandBlock$1(this);
   public DefaultResourcePack field_0003;
   public BitReserve field_0000;

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
