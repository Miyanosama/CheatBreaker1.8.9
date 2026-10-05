package recovered.unidentified;

import io.netty.buffer.Unpooled;
import io.netty.channel.oio.OioByteStreamChannel$1;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.realms.RealmsConnect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Cartesian$1;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.optifine.expr.ParseException;

public class UnidentifiedClass4298 extends WorldGenTrees {
   public Cartesian$1 field_0004;
   public IBlockState field_0000;
   public ParseException field_0001;
   public IBlockState field_0005;
   public OioByteStreamChannel$1 field_0003;
   public RealmsConnect field_0002;
   public Unpooled field_0006;

   public UnidentifiedClass4298(IBlockState var1, IBlockState var2) {
      super(false);
      this.field_0000 = var1;
      this.field_0005 = var2;
   }

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      Block var4;
      while (((var4 = var1.getBlockState(var3).getBlock()).getMaterial() == Material.air || var4.getMaterial() == Material.leaves) && var3.getY() > 0) {
         var3 = var3.down();
      }

      Block var5 = var1.getBlockState(var3).getBlock();
      if (var5 == Blocks.dirt || var5 == Blocks.grass) {
         var3 = var3.up();
         this.setBlockAndNotifyAdequately(var1, var3, this.field_0000);

         for (int var6 = var3.getY(); var6 <= var3.getY() + 2; var6++) {
            int var7 = var6 - var3.getY();
            int var8 = 2 - var7;

            for (int var9 = var3.getX() - var8; var9 <= var3.getX() + var8; var9++) {
               int var10 = var9 - var3.getX();

               for (int var11 = var3.getZ() - var8; var11 <= var3.getZ() + var8; var11++) {
                  int var12 = var11 - var3.getZ();
                  if (Math.abs(var10) != var8 || Math.abs(var12) != var8 || var2.nextInt(2) != 0) {
                     BlockPos var13 = new BlockPos(var9, var6, var11);
                     if (!var1.getBlockState(var13).getBlock().isFullBlock()) {
                        this.setBlockAndNotifyAdequately(var1, var13, this.field_0005);
                     }
                  }
               }
            }
         }
      }

      return true;
   }
}
