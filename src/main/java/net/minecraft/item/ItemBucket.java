package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBucket extends Item {
   public Block isFull;

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      boolean var4 = this.isFull == Blocks.air;
      MovingObjectPosition var5 = this.a(var2, var3, var4);
      if (var5 == null) {
         return var1;
      } else {
         if (var5.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            BlockPos var6 = var5.getBlockPos();
            if (!var2.isBlockModifiable(var3, var6)) {
               return var1;
            }

            if (var4) {
               if (!var3.canPlayerEdit(var6.a(var5.sideHit), var5.sideHit, var1)) {
                  return var1;
               }

               IBlockState var7 = var2.getBlockState(var6);
               Material var8 = var7.getBlock().getMaterial();
               if (var8 == Material.water && var7.getValue(BlockLiquid.b) == 0) {
                  var2.setBlockToAir(var6);
                  var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                  return this.fillBucket(var1, var3, Items.water_bucket);
               }

               if (var8 == Material.lava && var7.getValue(BlockLiquid.b) == 0) {
                  var2.setBlockToAir(var6);
                  var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                  return this.fillBucket(var1, var3, Items.lava_bucket);
               }
            } else {
               if (this.isFull == Blocks.air) {
                  return new ItemStack(Items.bucket);
               }

               BlockPos var9 = var6.a(var5.sideHit);
               if (!var3.canPlayerEdit(var9, var5.sideHit, var1)) {
                  return var1;
               }

               if (this.tryPlaceContainedLiquid(var2, var9) && !var3.bA.isCreativeMode) {
                  var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                  return new ItemStack(Items.bucket);
               }
            }
         }

         return var1;
      }
   }

   public boolean tryPlaceContainedLiquid(World var1, BlockPos var2) {
      if (this.isFull == Blocks.air) {
         return false;
      } else {
         Material var3 = var1.getBlockState(var2).getBlock().getMaterial();
         boolean var4 = !var3.isSolid();
         if (!var1.isAirBlock(var2) && !var4) {
            return false;
         } else {
            if (var1.t.doesWaterVaporize() && this.isFull == Blocks.flowing_water) {
               int var5 = var2.getX();
               int var6 = var2.getY();
               int var7 = var2.getZ();
               var1.playSoundEffect(var5 + 0.5F, var6 + 0.5F, var7 + 0.5F, "random.fizz", 0.5F, 2.6F + (var1.s.nextFloat() - var1.s.nextFloat()) * 0.8F);

               for (int var8 = 0; var8 < 8; var8++) {
                  var1.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var5 + Math.random(), var6 + Math.random(), var7 + Math.random(), 0.0, 0.0, 0.0);
               }
            } else {
               if (!var1.D && var4 && !var3.isLiquid()) {
                  var1.destroyBlock(var2, true);
               }

               var1.a(var2, this.isFull.getDefaultState(), 3);
            }

            return true;
         }
      }
   }

   public ItemStack fillBucket(ItemStack var1, EntityPlayer var2, Item var3) {
      if (var2.bA.isCreativeMode) {
         return var1;
      } else if (--var1.stackSize <= 0) {
         return new ItemStack(var3);
      } else {
         if (!var2.bi.addItemStackToInventory(new ItemStack(var3))) {
            var2.dropPlayerItemWithRandomChoice(new ItemStack(var3, 1, 0), false);
         }

         return var1;
      }
   }

   public ItemBucket(Block var1) {
      this.h = 1;
      this.isFull = var1;
      this.setCreativeTab(CreativeTabs.tabMisc);
   }
}
