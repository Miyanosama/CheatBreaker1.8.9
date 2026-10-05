package net.minecraft.network.play.server;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S35PacketUpdateTileEntity implements Packet<INetHandlerPlayClient> {
   public NBTTagCompound nbt;
   public int metadata;
   public BlockPos blockPos;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeBlockPos(this.blockPos);
      var1.writeByte((byte)this.metadata);
      var1.writeNBTTagCompoundToBuffer(this.nbt);
   }

   public S35PacketUpdateTileEntity() {
   }

   public BlockPos getPos() {
      return this.blockPos;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.blockPos = var1.readBlockPos();
      this.metadata = var1.readUnsignedByte();
      this.nbt = var1.readNBTTagCompoundFromBuffer();
   }

   public int getTileEntityType() {
      return this.metadata;
   }

   public NBTTagCompound getNbtCompound() {
      return this.nbt;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleUpdateTileEntity(this);
   }

   public S35PacketUpdateTileEntity(BlockPos var1, int var2, NBTTagCompound var3) {
      this.blockPos = var1;
      this.metadata = var2;
      this.nbt = var3;
   }
}
