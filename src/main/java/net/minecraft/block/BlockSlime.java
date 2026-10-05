package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockSlime extends BlockBreakable {
   public BlockSlime() {
      super(Material.clay, false, MapColor.grassColor);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.L = 0.8F;
   }

   @Override
   public void onEntityCollidedWithBlock(World var1, BlockPos var2, Entity var3) {
      if (Math.abs(var3.w) < 0.1 && !var3.isSneaking()) {
         double var4 = 0.4 + Math.abs(var3.w) * 0.2;
         var3.v *= var4;
         var3.x *= var4;
      }

      super.onEntityCollidedWithBlock(var1, var2, var3);
   }

   @Override
   public void onLanded(World var1, Entity var2) {
      if (var2.isSneaking()) {
         super.onLanded(var1, var2);
      } else if (var2.w < 0.0) {
         var2.w = -var2.w;
      }
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   @Override
   public void onFallenUpon(World var1, BlockPos var2, Entity var3, float var4) {
      if (var3.isSneaking()) {
         super.onFallenUpon(var1, var2, var3, var4);
      } else {
         var3.fall(var4, 0.0F);
      }
   }
}
