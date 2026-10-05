package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemDye extends Item {
   public static int[] dyeColors = new int[]{
      1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320
   };

   public static boolean applyBonemeal(ItemStack var0, World var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      if (var3.getBlock() instanceof IGrowable) {
         IGrowable var4 = (IGrowable)var3.getBlock();
         if (var4.canGrow(var1, var2, var3, var1.D)) {
            if (!var1.D) {
               if (var4.canUseBonemeal(var1, var1.s, var2, var3)) {
                  var4.grow(var1, var1.s, var2, var3);
               }

               var0.stackSize--;
            }

            return true;
         }
      }

      return false;
   }

   public static void spawnBonemealParticles(World var0, BlockPos var1, int var2) {
      if (var2 == 0) {
         var2 = 15;
      }

      Block var3 = var0.getBlockState(var1).getBlock();
      if (var3.getMaterial() != Material.air) {
         var3.setBlockBoundsBasedOnState(var0, var1);

         for (int var4 = 0; var4 < var2; var4++) {
            double var5 = g.nextGaussian() * 0.02;
            double var7 = g.nextGaussian() * 0.02;
            double var9 = g.nextGaussian() * 0.02;
            var0.spawnParticle(
               EnumParticleTypes.VILLAGER_HAPPY,
               var1.getX() + g.nextFloat(),
               var1.getY() + g.nextFloat() * var3.getBlockBoundsMaxY(),
               var1.getZ() + g.nextFloat(),
               var5,
               var7,
               var9
            );
         }
      }
   }

   public ItemDye() {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      int var2 = var1.getMetadata();
      return super.getUnlocalizedName() + "." + EnumDyeColor.byDyeDamage(var2).getUnlocalizedName();
   }

   @Override
   public void getSubItems(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (int var4 = 0; var4 < 16; var4++) {
         var3.add(new ItemStack(var1, 1, var4));
      }
   }

   @Override
   public boolean itemInteractionForEntity(ItemStack var1, EntityPlayer var2, EntityLivingBase var3) {
      if (var3 instanceof EntitySheep) {
         EntitySheep var4 = (EntitySheep)var3;
         EnumDyeColor var5 = EnumDyeColor.byDyeDamage(var1.getMetadata());
         if (!var4.getSheared() && var4.getFleeceColor() != var5) {
            var4.setFleeceColor(var5);
            var1.stackSize--;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      if (!var2.canPlayerEdit(var4.a(var5), var5, var1)) {
         return false;
      } else {
         EnumDyeColor var9 = EnumDyeColor.byDyeDamage(var1.getMetadata());
         if (var9 == EnumDyeColor.WHITE) {
            if (applyBonemeal(var1, var3, var4)) {
               if (!var3.D) {
                  var3.b(2005, var4, 0);
               }

               return true;
            }
         } else if (var9 == EnumDyeColor.BROWN) {
            IBlockState var10 = var3.getBlockState(var4);
            Block var11 = var10.getBlock();
            if (var11 == Blocks.log && var10.getValue(BlockPlanks.VARIANT) == BlockPlanks.EnumType.JUNGLE) {
               if (var5 == EnumFacing.DOWN) {
                  return false;
               }

               if (var5 == EnumFacing.UP) {
                  return false;
               }

               var4 = var4.a(var5);
               if (var3.isAirBlock(var4)) {
                  IBlockState var12 = Blocks.cocoa.onBlockPlaced(var3, var4, var5, var6, var7, var8, 0, var2);
                  var3.a(var4, var12, 2);
                  if (!var2.bA.isCreativeMode) {
                     var1.stackSize--;
                  }
               }

               return true;
            }
         }

         return false;
      }
   }
}
