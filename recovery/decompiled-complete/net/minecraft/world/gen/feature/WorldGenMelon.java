package net.minecraft.world.gen.feature;

import io.netty.handler.codec.spdy.SpdyHttpDecoder;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceValuesToLongTask;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenMelon extends WorldGenerator {
   public ConcurrentHashMapV8$MapReduceValuesToLongTask field_0000;
   public SpdyHttpDecoder field_0001;

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      for (int var4 = 0; var4 < 64; var4++) {
         BlockPos var5 = var3.add(var2.nextInt(8) - var2.nextInt(8), var2.nextInt(4) - var2.nextInt(4), var2.nextInt(8) - var2.nextInt(8));
         if (Blocks.melon_block.canPlaceBlockAt(var1, var5) && var1.getBlockState(var5.down()).getBlock() == Blocks.grass) {
            var1.a(var5, Blocks.melon_block.getDefaultState(), 2);
         }
      }

      return true;
   }
}
