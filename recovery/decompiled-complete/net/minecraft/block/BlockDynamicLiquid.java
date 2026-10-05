package net.minecraft.block;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityReddustFX$Factory;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.world.World;

public class BlockDynamicLiquid extends BlockLiquid {
   public MovingObjectPosition$MovingObjectType field_0001;
   public EntityReddustFX$Factory field_0002;
   public int adjacentSourceBlocks;

   public int func_176374_a(World var1, BlockPos var2, int var3, EnumFacing var4) {
      int var5 = 1000;

      for (EnumFacing var7 : EnumFacing$Plane.HORIZONTAL) {
         if (var7 != var4) {
            BlockPos var8 = var2.a(var7);
            IBlockState var9 = var1.getBlockState(var8);
            if (!this.isBlocked(var1, var8, var9) && (var9.getBlock().getMaterial() != this.J || var9.getValue(b) > 0)) {
               if (!this.isBlocked(var1, var8.down(), var9)) {
                  return var3;
               }

               if (var3 < 4) {
                  int var10 = this.func_176374_a(var1, var8, var3 + 1, var7.getOpposite());
                  if (var10 < var5) {
                     var5 = var10;
                  }
               }
            }
         }
      }

      return var5;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      int var5 = var3.getValue(b);
      byte var6 = 1;
      if (this.J == Material.lava && !var1.t.doesWaterVaporize()) {
         var6 = 2;
      }

      int var7 = this.tickRate(var1);
      if (var5 > 0) {
         int var8 = -100;
         this.adjacentSourceBlocks = 0;

         for (EnumFacing var10 : EnumFacing$Plane.HORIZONTAL) {
            var8 = this.checkAdjacentBlock(var1, var2.a(var10), var8);
         }

         int var15 = var8 + var6;
         if (var15 >= 8 || var8 < 0) {
            var15 = -1;
         }

         if (this.getLevel(var1, var2.up()) >= 0) {
            int var17 = this.getLevel(var1, var2.up());
            if (var17 >= 8) {
               var15 = var17;
            } else {
               var15 = var17 + 8;
            }
         }

         if (this.adjacentSourceBlocks >= 2 && this.J == Material.water) {
            IBlockState var18 = var1.getBlockState(var2.down());
            if (var18.getBlock().getMaterial().isSolid()) {
               var15 = 0;
            } else if (var18.getBlock().getMaterial() == this.J && var18.getValue(b) == 0) {
               var15 = 0;
            }
         }

         if (this.J == Material.lava && var5 < 8 && var15 < 8 && var15 > var5 && var4.nextInt(4) != 0) {
            var7 *= 4;
         }

         if (var15 == var5) {
            this.placeStaticBlock(var1, var2, var3);
         } else {
            var5 = var15;
            if (var15 < 0) {
               var1.setBlockToAir(var2);
            } else {
               var3 = var3.withProperty(b, var15);
               var1.a(var2, var3, 2);
               var1.scheduleUpdate(var2, this, var7);
               var1.notifyNeighborsOfStateChange(var2, this);
            }
         }
      } else {
         this.placeStaticBlock(var1, var2, var3);
      }

      IBlockState var14 = var1.getBlockState(var2.down());
      if (this.canFlowInto(var1, var2.down(), var14)) {
         if (this.J == Material.lava && var1.getBlockState(var2.down()).getBlock().getMaterial() == Material.water) {
            var1.setBlockState(var2.down(), Blocks.stone.getDefaultState());
            this.triggerMixEffects(var1, var2.down());
            return;
         }

         if (var5 >= 8) {
            this.tryFlowInto(var1, var2.down(), var14, var5);
         } else {
            this.tryFlowInto(var1, var2.down(), var14, var5 + 8);
         }
      } else if (var5 >= 0 && (var5 == 0 || this.isBlocked(var1, var2.down(), var14))) {
         Set var16 = this.getPossibleFlowDirections(var1, var2);
         int var19 = var5 + var6;
         if (var5 >= 8) {
            var19 = 1;
         }

         if (var19 >= 8) {
            return;
         }

         for (EnumFacing var12 : var16) {
            this.tryFlowInto(var1, var2.a(var12), var1.getBlockState(var2.a(var12)), var19);
         }
      }
   }

   public BlockDynamicLiquid(Material var1) {
      super(var1);
   }

   public void placeStaticBlock(World var1, BlockPos var2, IBlockState var3) {
      var1.a(var2, getStaticBlock(this.J).getDefaultState().withProperty(b, var3.getValue(b)), 2);
   }

   public Set<EnumFacing> getPossibleFlowDirections(World var1, BlockPos var2) {
      int var3 = 1000;
      EnumSet var4 = EnumSet.noneOf(EnumFacing.class);

      for (EnumFacing var6 : EnumFacing$Plane.HORIZONTAL) {
         BlockPos var7 = var2.a(var6);
         IBlockState var8 = var1.getBlockState(var7);
         if (!this.isBlocked(var1, var7, var8) && (var8.getBlock().getMaterial() != this.J || var8.getValue(b) > 0)) {
            int var9;
            if (this.isBlocked(var1, var7.down(), var1.getBlockState(var7.down()))) {
               var9 = this.func_176374_a(var1, var7, 1, var6.getOpposite());
            } else {
               var9 = 0;
            }

            if (var9 < var3) {
               var4.clear();
            }

            if (var9 <= var3) {
               var4.add(var6);
               var3 = var9;
            }
         }
      }

      return var4;
   }

   public void tryFlowInto(World var1, BlockPos var2, IBlockState var3, int var4) {
      if (this.canFlowInto(var1, var2, var3)) {
         if (var3.getBlock() != Blocks.air) {
            if (this.J == Material.lava) {
               this.triggerMixEffects(var1, var2);
            } else {
               var3.getBlock().dropBlockAsItem(var1, var2, var3, 0);
            }
         }

         var1.a(var2, this.getDefaultState().withProperty(b, var4), 3);
      }
   }

   public boolean canFlowInto(World var1, BlockPos var2, IBlockState var3) {
      Material var4 = var3.getBlock().getMaterial();
      return var4 != this.J && var4 != Material.lava && !this.isBlocked(var1, var2, var3);
   }

   public int checkAdjacentBlock(World var1, BlockPos var2, int var3) {
      int var4 = this.getLevel(var1, var2);
      if (var4 < 0) {
         return var3;
      } else {
         if (var4 == 0) {
            this.adjacentSourceBlocks++;
         }

         if (var4 >= 8) {
            var4 = 0;
         }

         return var3 >= 0 && var4 >= var3 ? var3 : var4;
      }
   }

   public boolean isBlocked(World var1, BlockPos var2, IBlockState var3) {
      Block var4 = var1.getBlockState(var2).getBlock();
      return !(var4 instanceof BlockDoor) && var4 != Blocks.standing_sign && var4 != Blocks.ladder && var4 != Blocks.reeds
         ? (var4.J == Material.portal ? true : var4.J.blocksMovement())
         : true;
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      if (!this.e(var1, var2, var3)) {
         var1.scheduleUpdate(var2, this, this.tickRate(var1));
      }
   }
}
