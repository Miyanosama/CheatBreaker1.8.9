package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRail extends BlockRailBase {
   public static PropertyEnum<BlockRailBase.EnumRailDirection> SHAPE = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class);

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(SHAPE).getMetadata();
   }

   @Override
   public void onNeighborChangedInternal(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (var4.canProvidePower() && new BlockRailBase.Rail(var1, var2, var3).countAdjacentRails() == 3) {
         this.a(var1, var2, var3, false);
      }
   }

   @Override
   public IProperty<BlockRailBase.EnumRailDirection> getShapeProperty() {
      return SHAPE;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, SHAPE);
   }

   public BlockRail() {
      super(false);
      this.setDefaultState(this.M.getBaseState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.NORTH_SOUTH));
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.byMetadata(var1));
   }
}
