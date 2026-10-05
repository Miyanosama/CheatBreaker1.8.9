package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import org.apache.commons.lang3.StringUtils;

public class C14PacketTabComplete implements Packet<INetHandlerPlayServer> {
   public String message;
   public BlockPos targetBlock;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeString(StringUtils.substring(this.message, 0, 32767));
      boolean var2 = this.targetBlock != null;
      var1.writeBoolean(var2);
      if (var2) {
         var1.writeBlockPos(this.targetBlock);
      }
   }

   public C14PacketTabComplete(String var1, BlockPos var2) {
      this.message = var1;
      this.targetBlock = var2;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.message = var1.readStringFromBuffer(32767);
      boolean var2 = var1.readBoolean();
      if (var2) {
         this.targetBlock = var1.readBlockPos();
      }
   }

   public String getMessage() {
      return this.message;
   }

   public BlockPos getTargetBlock() {
      return this.targetBlock;
   }

   public C14PacketTabComplete() {
   }

   public C14PacketTabComplete(String var1) {
      this(var1, (BlockPos)null);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processTabComplete(this);
   }
}
