package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BlockLog extends BlockRotatedPillar {
   public static PropertyEnum<BlockLog$EnumAxis> a = PropertyEnum.create("axis", BlockLog$EnumAxis.class);

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      byte var4 = 4;
      int var5 = var4 + 1;
      if (var1.isAreaLoaded(var2.add(-var5, -var5, -var5), var2.add(var5, var5, var5))) {
         for (BlockPos var7 : BlockPos.getAllInBox(var2.add(-var4, -var4, -var4), var2.add((int)var4, (int)var4, (int)var4))) {
            IBlockState var8 = var1.getBlockState(var7);
            if (var8.getBlock().getMaterial() == Material.leaves && !var8.getValue(BlockLeaves.b)) {
               var1.a(var7, var8.withProperty(BlockLeaves.b, true), 4);
            }
         }
      }
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return super.onBlockPlaced(var1, var2, var3, var4, var5, var6, var7, var8).withProperty(a, BlockLog$EnumAxis.fromFacingAxis(var3.getAxis()));
   }

   public BlockLog() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setHardness(2.0F);
      this.setStepSound(f);
   }
}
