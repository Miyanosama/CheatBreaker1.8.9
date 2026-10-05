package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFireball extends Item {
   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var3.D) {
         return true;
      } else {
         var4 = var4.a(var5);
         if (!var2.canPlayerEdit(var4, var5, var1)) {
            return false;
         } else {
            if (var3.getBlockState(var4).getBlock().getMaterial() == Material.air) {
               var3.playSoundEffect(
                  var4.getX() + 0.5, var4.getY() + 0.5, var4.getZ() + 0.5, "item.fireCharge.use", 1.0F, (g.nextFloat() - g.nextFloat()) * 0.2F + 1.0F
               );
               var3.setBlockState(var4, Blocks.fire.getDefaultState());
            }

            if (!var2.bA.isCreativeMode) {
               var1.stackSize--;
            }

            return true;
         }
      }
   }

   public ItemFireball() {
      this.setCreativeTab(CreativeTabs.tabMisc);
   }
}
