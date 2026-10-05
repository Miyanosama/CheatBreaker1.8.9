package net.minecraft.network.play.server;

import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$2;
import io.netty.util.internal.PlatformDependent0$2;
import net.minecraft.block.Block;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;
import net.optifine.entity.model.ModelAdapterPigZombie;

public class S22PacketMultiBlockChange implements Packet<INetHandlerPlayClient> {
   public PlatformDependent0$2 field_0002;
   public AbstractNioChannel$AbstractNioUnsafe$2 field_0004;
   public ModelAdapterPigZombie field_0001;
   public S22PacketMultiBlockChange$BlockUpdateData[] changedBlocks;
   public ChunkCoordIntPair chunkPosCoord;

   public S22PacketMultiBlockChange(int var1, short[] var2, Chunk var3) {
      this.chunkPosCoord = new ChunkCoordIntPair(var3.a, var3.b);
      this.changedBlocks = new S22PacketMultiBlockChange$BlockUpdateData[var1];

      for (int var4 = 0; var4 < this.changedBlocks.length; var4++) {
         this.changedBlocks[var4] = new S22PacketMultiBlockChange$BlockUpdateData(this, var2[var4], var3);
      }
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.chunkPosCoord.chunkXPos);
      var1.writeInt(this.chunkPosCoord.chunkZPos);
      var1.writeVarIntToBuffer(this.changedBlocks.length);

      for (S22PacketMultiBlockChange$BlockUpdateData var5 : this.changedBlocks) {
         var1.writeShort(var5.func_180089_b());
         var1.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(var5.getBlockState()));
      }
   }

   public S22PacketMultiBlockChange$BlockUpdateData[] getChangedBlocks() {
      return this.changedBlocks;
   }

   public S22PacketMultiBlockChange() {
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleMultiBlockChange(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.chunkPosCoord = new ChunkCoordIntPair(var1.readInt(), var1.readInt());
      this.changedBlocks = new S22PacketMultiBlockChange$BlockUpdateData[var1.readVarIntFromBuffer()];

      for (int var2 = 0; var2 < this.changedBlocks.length; var2++) {
         this.changedBlocks[var2] = new S22PacketMultiBlockChange$BlockUpdateData(
            this, var1.readShort(), Block.BLOCK_STATE_IDS.getByValue(var1.readVarIntFromBuffer())
         );
      }
   }
}
