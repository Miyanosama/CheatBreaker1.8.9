package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S28PacketEffect implements Packet<INetHandlerPlayClient> {
   public BlockPos soundPos;
   public int soundData;
   public int soundType;
   public boolean serverWide;

   public S28PacketEffect(int var1, BlockPos var2, int var3, boolean var4) {
      this.soundType = var1;
      this.soundPos = var2;
      this.soundData = var3;
      this.serverWide = var4;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.soundType = var1.readInt();
      this.soundPos = var1.readBlockPos();
      this.soundData = var1.readInt();
      this.serverWide = var1.readBoolean();
   }

   public int getSoundData() {
      return this.soundData;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEffect(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeInt(this.soundType);
      var1.writeBlockPos(this.soundPos);
      var1.writeInt(this.soundData);
      var1.writeBoolean(this.serverWide);
   }

   public S28PacketEffect() {
   }

   public int getSoundType() {
      return this.soundType;
   }

   public BlockPos getSoundPos() {
      return this.soundPos;
   }

   public boolean isSoundServerwide() {
      return this.serverWide;
   }
}
