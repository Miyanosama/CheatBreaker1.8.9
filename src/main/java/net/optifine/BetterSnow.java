package net.optifine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BetterSnow {
   public static IBakedModel modelSnowLayer = null;

   public static boolean shouldRender(IBlockAccess var0, IBlockState var1, BlockPos var2) {
      Block var3 = var1.getBlock();
      return !checkBlock(var3, var1) ? false : hasSnowNeighbours(var0, var2);
   }

   public static IBakedModel getModelSnowLayer() {
      return modelSnowLayer;
   }

   public static void update() {
      modelSnowLayer = Config.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(Blocks.snow_layer.getDefaultState());
   }

   public static boolean hasSnowNeighbours(IBlockAccess var0, BlockPos var1) {
      Block var2 = Blocks.snow_layer;
      return var0.getBlockState(var1.north()).getBlock() != var2
            && var0.getBlockState(var1.south()).getBlock() != var2
            && var0.getBlockState(var1.west()).getBlock() != var2
            && var0.getBlockState(var1.east()).getBlock() != var2
         ? false
         : var0.getBlockState(var1.down()).getBlock().isOpaqueCube();
   }

   public static IBlockState getStateSnowLayer() {
      return Blocks.snow_layer.getDefaultState();
   }

   public static boolean checkBlock(Block var0, IBlockState var1) {
      if (var0.isFullCube()) {
         return false;
      } else if (var0.isOpaqueCube()) {
         return false;
      } else if (var0 instanceof BlockSnow) {
         return false;
      } else if (!(var0 instanceof BlockBush)
         || !(var0 instanceof BlockDoublePlant)
            && !(var0 instanceof BlockFlower)
            && !(var0 instanceof BlockMushroom)
            && !(var0 instanceof BlockSapling)
            && !(var0 instanceof BlockTallGrass)) {
         if (var0 instanceof BlockFence
            || var0 instanceof BlockFenceGate
            || var0 instanceof BlockFlowerPot
            || var0 instanceof BlockPane
            || var0 instanceof BlockReed
            || var0 instanceof BlockWall) {
            return true;
         } else if (var0 instanceof BlockRedstoneTorch && var1.getValue(BlockTorch.FACING) == EnumFacing.UP) {
            return true;
         } else {
            if (var0 instanceof BlockLever) {
               Comparable var2 = var1.getValue(BlockLever.FACING);
               if (var2 == BlockLever.EnumOrientation.UP_X || var2 == BlockLever.EnumOrientation.UP_Z) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }
}
