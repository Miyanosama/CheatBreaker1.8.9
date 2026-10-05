package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class C07PacketPlayerDigging implements Packet<INetHandlerPlayServer> {
   public BlockPos position;
   public EnumFacing facing;
   public C07PacketPlayerDigging.Action status;

   public C07PacketPlayerDigging(C07PacketPlayerDigging.Action var1, BlockPos var2, EnumFacing var3) {
      this.status = var1;
      this.position = var2;
      this.facing = var3;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayerDigging(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.status = var1.readEnumValue(C07PacketPlayerDigging.Action.class);
      this.position = var1.readBlockPos();
      this.facing = EnumFacing.getFront(var1.readUnsignedByte());
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeEnumValue(this.status);
      var1.writeBlockPos(this.position);
      var1.writeByte(this.facing.getIndex());
   }

   public C07PacketPlayerDigging() {
   }

   public C07PacketPlayerDigging.Action getStatus() {
      return this.status;
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public static enum Action {
      START_DESTROY_BLOCK,
      ABORT_DESTROY_BLOCK,
      STOP_DESTROY_BLOCK,
      DROP_ALL_ITEMS,
      DROP_ITEM,
      RELEASE_USE_ITEM;
      // $VF: synthetic field
      public static C07PacketPlayerDigging.Action[] $VALUES = new C07PacketPlayerDigging.Action[]{
         START_DESTROY_BLOCK, ABORT_DESTROY_BLOCK, C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, DROP_ALL_ITEMS, DROP_ITEM, RELEASE_USE_ITEM
      };
   }
}
