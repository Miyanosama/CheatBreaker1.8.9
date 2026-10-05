package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemLead extends Item {
   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      Block var9 = var3.getBlockState(var4).getBlock();
      if (var9 instanceof BlockFence) {
         if (var3.D) {
            return true;
         } else {
            attachToFence(var2, var3, var4);
            return true;
         }
      } else {
         return false;
      }
   }

   public ItemLead() {
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   public static boolean attachToFence(EntityPlayer var0, World var1, BlockPos var2) {
      EntityLeashKnot var3 = EntityLeashKnot.getKnotForPosition(var1, var2);
      boolean var4 = false;
      double var5 = 7.0;
      int var7 = var2.getX();
      int var8 = var2.getY();
      int var9 = var2.getZ();

      for (EntityLiving var11 : var1.getEntitiesWithinAABB(
         EntityLiving.class, new AxisAlignedBB(var7 - var5, var8 - var5, var9 - var5, var7 + var5, var8 + var5, var9 + var5)
      )) {
         if (var11.getLeashed() && var11.getLeashedToEntity() == var0) {
            if (var3 == null) {
               var3 = EntityLeashKnot.createKnot(var1, var2);
            }

            var11.setLeashedToEntity(var3, true);
            var4 = true;
         }
      }

      return var4;
   }
}
