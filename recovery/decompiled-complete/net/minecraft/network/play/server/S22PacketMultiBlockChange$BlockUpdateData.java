package net.minecraft.network.play.server;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.chunk.Chunk;
import net.optifine.RandomEntityRule;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$18;
import org.json.XMLTokener;

public class S22PacketMultiBlockChange$BlockUpdateData {
   public IBlockState blockState;
   public RandomEntityRule field_0005;
   public short chunkPosCrammed;
   public LogBrokerMonitor$18 field_0004;
   public XMLTokener field_0000;
   public WorldSettings$GameType field_0001;

   public S22PacketMultiBlockChange$BlockUpdateData(S22PacketMultiBlockChange var1, short var2, IBlockState var3) {
      this.field_180093_a = var1;
      super();
      this.chunkPosCrammed = var2;
      this.blockState = var3;
   }

   public BlockPos getPos() {
      return new BlockPos(
         S22PacketMultiBlockChange.access$000(this.field_180093_a)
            .getBlock(this.chunkPosCrammed >> 12 & 15, this.chunkPosCrammed & 255, this.chunkPosCrammed >> 8 & 15)
      );
   }

   public S22PacketMultiBlockChange$BlockUpdateData(S22PacketMultiBlockChange var1, short var2, Chunk var3) {
      this.field_180093_a = var1;
      super();
      this.chunkPosCrammed = var2;
      this.blockState = var3.getBlockState(this.getPos());
   }

   public IBlockState getBlockState() {
      return this.blockState;
   }

   public short func_180089_b() {
      return this.chunkPosCrammed;
   }
}
