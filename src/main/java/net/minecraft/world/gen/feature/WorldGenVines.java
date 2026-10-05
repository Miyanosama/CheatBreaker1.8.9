package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenVines extends WorldGenerator {
   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      while (var3.getY() < 128) {
         if (var1.isAirBlock(var3)) {
            for (EnumFacing var7 : EnumFacing.Plane.HORIZONTAL.facings()) {
               if (Blocks.vine.canPlaceBlockOnSide(var1, var3, var7)) {
                  IBlockState var8 = Blocks.vine
                     .getDefaultState()
                     .withProperty(BlockVine.NORTH, var7 == EnumFacing.NORTH)
                     .withProperty(BlockVine.EAST, var7 == EnumFacing.EAST)
                     .withProperty(BlockVine.SOUTH, var7 == EnumFacing.SOUTH)
                     .withProperty(BlockVine.WEST, var7 == EnumFacing.WEST);
                  var1.a(var3, var8, 2);
                  break;
               }
            }
         } else {
            var3 = var3.add(var2.nextInt(4) - var2.nextInt(4), 0, var2.nextInt(4) - var2.nextInt(4));
         }

         var3 = var3.up();
      }

      return true;
   }
}
