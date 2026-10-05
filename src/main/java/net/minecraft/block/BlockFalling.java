package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockFalling extends Block {
   public static boolean fallInstantly;

   public void onStartFalling(EntityFallingBlock var1) {
   }

   public BlockFalling(Material var1) {
      super(var1);
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (!var1.D) {
         this.checkFallable(var1, var2);
      }
   }

   public void onEndFalling(World var1, BlockPos var2) {
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      var1.scheduleUpdate(var2, this, this.tickRate(var1));
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      var1.scheduleUpdate(var2, this, this.tickRate(var1));
   }

   public BlockFalling() {
      super(Material.sand);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public void checkFallable(World var1, BlockPos var2) {
      if (canFallInto(var1, var2.down()) && var2.getY() >= 0) {
         byte var3 = 32;
         if (fallInstantly || !var1.isAreaLoaded(var2.add(-var3, -var3, -var3), var2.add((int)var3, (int)var3, (int)var3))) {
            var1.setBlockToAir(var2);
            BlockPos var5 = var2.down();

            while (canFallInto(var1, var5) && var5.getY() > 0) {
               var5 = var5.down();
            }

            if (var5.getY() > 0) {
               var1.setBlockState(var5.up(), this.getDefaultState());
            }
         } else if (!var1.D) {
            EntityFallingBlock var4 = new EntityFallingBlock(var1, var2.getX() + 0.5, var2.getY(), var2.getZ() + 0.5, var1.getBlockState(var2));
            this.onStartFalling(var4);
            var1.spawnEntityInWorld(var4);
         }
      }
   }

   @Override
   public int tickRate(World var1) {
      return 2;
   }

   public static boolean canFallInto(World var0, BlockPos var1) {
      Block var2 = var0.getBlockState(var1).getBlock();
      Material var3 = var2.J;
      return var2 == Blocks.fire || var3 == Material.air || var3 == Material.water || var3 == Material.lava;
   }
}
