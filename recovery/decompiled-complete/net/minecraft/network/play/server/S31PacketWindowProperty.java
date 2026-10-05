package net.minecraft.network.play.server;

import io.netty.channel.AbstractChannelHandlerContext$16;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.tileentity.TileEntity$3;

public class S31PacketWindowProperty implements Packet<INetHandlerPlayClient> {
   public AbstractChannelHandlerContext$16 field_0003;
   public TileEntity$3 field_0005;
   public int windowId;
   public int varIndex;
   public int varValue;
   public BlockRedstoneWire field_0001;

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.windowId = var1.readUnsignedByte();
      this.varIndex = var1.readShort();
      this.varValue = var1.readShort();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.windowId);
      var1.writeShort(this.varIndex);
      var1.writeShort(this.varValue);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleWindowProperty(this);
   }

   public S31PacketWindowProperty(int var1, int var2, int var3) {
      this.windowId = var1;
      this.varIndex = var2;
      this.varValue = var3;
   }

   public int getVarIndex() {
      return this.varIndex;
   }

   public int getVarValue() {
      return this.varValue;
   }

   public int getWindowId() {
      return this.windowId;
   }

   public S31PacketWindowProperty() {
   }
}
