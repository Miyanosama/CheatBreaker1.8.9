package net.minecraft.network.play.server;

import com.cheatbreaker.client.ui.mainmenu.MainMenuBase;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.optifine.shaders.CustomTexture;

public class S25PacketBlockBreakAnim implements Packet<INetHandlerPlayClient> {
   public int progress;
   public CustomTexture field_0004;
   public int breakerId;
   public BlockPos position;
   public MainMenuBase field_0000;

   public BlockPos getPosition() {
      return this.position;
   }

   public int getBreakerId() {
      return this.breakerId;
   }

   public int getProgress() {
      return this.progress;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.breakerId = var1.readVarIntFromBuffer();
      this.position = var1.readBlockPos();
      this.progress = var1.readUnsignedByte();
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleBlockBreakAnim(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.breakerId);
      var1.writeBlockPos(this.position);
      var1.writeByte(this.progress);
   }

   public S25PacketBlockBreakAnim(int var1, BlockPos var2, int var3) {
      this.breakerId = var1;
      this.position = var2;
      this.progress = var3;
   }

   public S25PacketBlockBreakAnim() {
   }
}
