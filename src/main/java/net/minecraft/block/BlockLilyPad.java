package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLilyPad extends BlockBush {
   @Override
   public boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.water;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return new AxisAlignedBB(
         var2.getX() + this.B, var2.getY() + this.C, var2.getZ() + this.D, var2.getX() + this.E, var2.getY() + this.F, var2.getZ() + this.G
      );
   }

   @Override
   public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
      return 2129968;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return 0;
   }

   @Override
   public int getRenderColor(IBlockState var1) {
      return 7455580;
   }

   public BlockLilyPad() {
      float var1 = 0.5F;
      float var2 = 0.015625F;
      this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var2, 0.5F + var1);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public int getBlockColor() {
      return 7455580;
   }

   @Override
   public void addCollisionBoxesToList(World var1, BlockPos var2, IBlockState var3, AxisAlignedBB var4, List<AxisAlignedBB> var5, Entity var6) {
      if (var6 == null || !(var6 instanceof EntityBoat)) {
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      }
   }

   @Override
   public boolean canBlockStay(World var1, BlockPos var2, IBlockState var3) {
      if (var2.getY() >= 0 && var2.getY() < 256) {
         IBlockState var4 = var1.getBlockState(var2.down());
         return var4.getBlock().getMaterial() == Material.water && var4.getValue(BlockLiquid.b) == 0;
      } else {
         return false;
      }
   }
}
