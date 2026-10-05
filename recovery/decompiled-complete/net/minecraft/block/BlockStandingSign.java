package net.minecraft.block;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenIceSpike;

public class BlockStandingSign extends BlockSign {
   public TextureOffset field_0000;
   public ItemRecord field_0003;
   public static PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);
   public WorldGenIceSpike field_0001;

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.getBlockState(var2.down()).getBlock().getMaterial().isSolid()) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
      }

      super.onNeighborBlockChange(var1, var2, var3, var4);
   }

   public BlockStandingSign() {
      this.setDefaultState(this.M.getBaseState().withProperty(ROTATION, 0));
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(ROTATION);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, ROTATION);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(ROTATION, var1);
   }
}
