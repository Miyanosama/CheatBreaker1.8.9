package net.minecraft.network.play.client;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class C07PacketPlayerDigging implements Packet<INetHandlerPlayServer> {
   public BlockPos position;
   public EnumFacing facing;
   public C07PacketPlayerDigging$Action status;
   public WebSocketServerProtocolHandshakeHandler$1 field_0002;

   public C07PacketPlayerDigging(C07PacketPlayerDigging$Action var1, BlockPos var2, EnumFacing var3) {
      this.status = var1;
      this.position = var2;
      this.facing = var3;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayerDigging(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.status = var1.readEnumValue(C07PacketPlayerDigging$Action.class);
      this.position = var1.readBlockPos();
      this.facing = EnumFacing.getFront(var1.readUnsignedByte());
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeEnumValue(this.status);
      var1.writeBlockPos(this.position);
      var1.writeByte(this.facing.getIndex());
   }

   public C07PacketPlayerDigging() {
   }

   public C07PacketPlayerDigging$Action getStatus() {
      return this.status;
   }

   public BlockPos getPosition() {
      return this.position;
   }
}
