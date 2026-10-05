package net.minecraft.world.gen.feature;

import io.netty.util.concurrent.DefaultEventExecutor;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.log4j.net.TelnetAppender;

public class WorldGenBlockBlob extends WorldGenerator {
   public TelnetAppender field_0002;
   public Block field_150545_a;
   public int field_150544_b;
   public DefaultEventExecutor field_0003;
   public BlockStandingSign field_0000;

   public WorldGenBlockBlob(Block var1, int var2) {
      super(false);
      this.field_150545_a = var1;
      this.field_150544_b = var2;
   }

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      while (true) {
         label50: {
            if (var3.getY() > 3) {
               if (var1.isAirBlock(var3.down())) {
                  break label50;
               }

               Block var4 = var1.getBlockState(var3.down()).getBlock();
               if (var4 != Blocks.grass && var4 != Blocks.dirt && var4 != Blocks.stone) {
                  break label50;
               }
            }

            if (var3.getY() <= 3) {
               return false;
            }

            int var12 = this.field_150544_b;

            for (int var5 = 0; var12 >= 0 && var5 < 3; var5++) {
               int var6 = var12 + var2.nextInt(2);
               int var7 = var12 + var2.nextInt(2);
               int var8 = var12 + var2.nextInt(2);
               float var9 = (var6 + var7 + var8) * 0.333F + 0.5F;

               for (BlockPos var11 : BlockPos.getAllInBox(var3.add(-var6, -var7, -var8), var3.add(var6, var7, var8))) {
                  if (var11.distanceSq(var3) <= var9 * var9) {
                     var1.a(var11, this.field_150545_a.getDefaultState(), 4);
                  }
               }

               var3 = var3.add(-(var12 + 1) + var2.nextInt(2 + var12 * 2), 0 - var2.nextInt(2), -(var12 + 1) + var2.nextInt(2 + var12 * 2));
            }

            return true;
         }

         var3 = var3.down();
      }
   }
}
