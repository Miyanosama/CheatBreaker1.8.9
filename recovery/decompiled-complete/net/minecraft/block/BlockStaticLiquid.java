package net.minecraft.block;

import io.netty.util.internal.chmv8.ForkJoinPool$DefaultForkJoinWorkerThreadFactory;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer$1;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo$6;

public class BlockStaticLiquid extends BlockLiquid {
   public ForkJoinPool$DefaultForkJoinWorkerThreadFactory field_0001;
   public WorldInfo$6 field_0002;
   public ItemFood field_0000;
   public EntityAILookAtVillager field_0004;
   public EntityAIFindEntityNearestPlayer$1 field_0003;

   public boolean isSurroundingBlockFlammable(World var1, BlockPos var2) {
      for (EnumFacing var6 : EnumFacing.values()) {
         if (this.getCanBlockBurn(var1, var2.a(var6))) {
            return true;
         }
      }

      return false;
   }

   public BlockStaticLiquid(Material var1) {
      super(var1);
      this.setTickRandomly(false);
      if (var1 == Material.lava) {
         this.setTickRandomly(true);
      }
   }

   public void updateLiquid(World var1, BlockPos var2, IBlockState var3) {
      BlockDynamicLiquid var4 = getFlowingBlock(this.J);
      var1.a(var2, var4.getDefaultState().withProperty(b, var3.getValue(b)), 2);
      var1.scheduleUpdate(var2, var4, this.tickRate(var1));
   }

   public boolean getCanBlockBurn(World var1, BlockPos var2) {
      return var1.getBlockState(var2).getBlock().getMaterial().getCanBurn();
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (this.J == Material.lava && var1.Q().getBoolean("doFireTick")) {
         int var5 = var4.nextInt(3);
         if (var5 > 0) {
            BlockPos var6 = var2;

            for (int var7 = 0; var7 < var5; var7++) {
               var6 = var6.add(var4.nextInt(3) - 1, 1, var4.nextInt(3) - 1);
               Block var8 = var1.getBlockState(var6).getBlock();
               if (var8.J == Material.air) {
                  if (this.isSurroundingBlockFlammable(var1, var6)) {
                     var1.setBlockState(var6, Blocks.fire.getDefaultState());
                     return;
                  }
               } else if (var8.J.blocksMovement()) {
                  return;
               }
            }
         } else {
            for (int var9 = 0; var9 < 3; var9++) {
               BlockPos var10 = var2.add(var4.nextInt(3) - 1, 0, var4.nextInt(3) - 1);
               if (var1.isAirBlock(var10.up()) && this.getCanBlockBurn(var1, var10)) {
                  var1.setBlockState(var10.up(), Blocks.fire.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!this.e(var1, var2, var3)) {
         this.updateLiquid(var1, var2, var3);
      }
   }
}
