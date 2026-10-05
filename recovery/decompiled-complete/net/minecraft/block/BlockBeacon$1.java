package net.minecraft.block;

import io.netty.handler.codec.spdy.SpdySessionHandler$ClosingChannelFutureListener;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class BlockBeacon$1 implements Runnable {
   public SpdySessionHandler$ClosingChannelFutureListener field_0001;

   @Override
   public void run() {
      Chunk var1 = this.field_180358_a.getChunkFromBlockCoords(this.field_180357_b);

      for (int var2 = this.field_180357_b.getY() - 1; var2 >= 0; var2--) {
         BlockPos var3 = new BlockPos(this.field_180357_b.getX(), var2, this.field_180357_b.getZ());
         if (!var1.canSeeSky(var3)) {
            break;
         }

         IBlockState var4 = this.field_180358_a.getBlockState(var3);
         if (var4.getBlock() == Blocks.beacon) {
            ((WorldServer)this.field_180358_a).addScheduledTask(new BlockBeacon$1$1(this, var3));
         }
      }
   }

   public BlockBeacon$1(World var1, BlockPos var2) {
      this.field_180358_a = var1;
      this.field_180357_b = var2;
      super();
   }
}
