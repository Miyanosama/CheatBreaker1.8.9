package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderItem$5;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRailPowered extends BlockRailBase {
   public BlockSilverfish$EnumType field_0000;
   public RenderItem$5 field_0001;
   public static PropertyBool POWERED = PropertyBool.create("powered");
   public static PropertyEnum<BlockRailBase$EnumRailDirection> SHAPE = PropertyEnum.create(
      "shape", BlockRailBase$EnumRailDirection.class, new BlockRailPowered$1()
   );

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, SHAPE, POWERED);
   }

   @Override
   public void onNeighborChangedInternal(World var1, BlockPos var2, IBlockState var3, Block var4) {
      boolean var5 = var3.getValue(POWERED);
      boolean var6 = var1.isBlockPowered(var2) || this.func_176566_a(var1, var2, var3, true, 0) || this.func_176566_a(var1, var2, var3, false, 0);
      if (var6 != var5) {
         var1.a(var2, var3.withProperty(POWERED, var6), 3);
         var1.notifyNeighborsOfStateChange(var2.down(), this);
         if (var3.getValue(SHAPE).isAscending()) {
            var1.notifyNeighborsOfStateChange(var2.up(), this);
         }
      }
   }

   public BlockRailPowered() {
      super(true);
      this.setDefaultState(this.M.getBaseState().withProperty(SHAPE, BlockRailBase$EnumRailDirection.NORTH_SOUTH).withProperty(POWERED, false));
   }

   public boolean func_176567_a(World var1, BlockPos var2, boolean var3, int var4, BlockRailBase$EnumRailDirection var5) {
      IBlockState var6 = var1.getBlockState(var2);
      if (var6.getBlock() != this) {
         return false;
      } else {
         BlockRailBase$EnumRailDirection var7 = var6.getValue(SHAPE);
         return var5 != BlockRailBase$EnumRailDirection.EAST_WEST
               || var7 != BlockRailBase$EnumRailDirection.NORTH_SOUTH
                  && var7 != BlockRailBase$EnumRailDirection.ASCENDING_NORTH
                  && var7 != BlockRailBase$EnumRailDirection.ASCENDING_SOUTH
            ? (
               var5 != BlockRailBase$EnumRailDirection.NORTH_SOUTH
                     || var7 != BlockRailBase$EnumRailDirection.EAST_WEST
                        && var7 != BlockRailBase$EnumRailDirection.ASCENDING_EAST
                        && var7 != BlockRailBase$EnumRailDirection.ASCENDING_WEST
                  ? (var6.getValue(POWERED) ? (var1.isBlockPowered(var2) ? true : this.func_176566_a(var1, var2, var6, var3, var4 + 1)) : false)
                  : false
            )
            : false;
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(SHAPE).getMetadata();
      if (var1.getValue(POWERED)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(SHAPE, BlockRailBase$EnumRailDirection.byMetadata(var1 & 7)).withProperty(POWERED, (var1 & 8) > 0);
   }

   @Override
   public IProperty<BlockRailBase$EnumRailDirection> getShapeProperty() {
      return SHAPE;
   }

   public boolean func_176566_a(World var1, BlockPos var2, IBlockState var3, boolean var4, int var5) {
      if (var5 >= 8) {
         return false;
      } else {
         int var6 = var2.getX();
         int var7 = var2.getY();
         int var8 = var2.getZ();
         boolean var9 = true;
         BlockRailBase$EnumRailDirection var10 = var3.getValue(SHAPE);
         switch (BlockRailPowered$2.field_0002[var10.ordinal()]) {
            case 1:
               if (var4) {
                  var8++;
               } else {
                  var8--;
               }
               break;
            case 2:
               if (var4) {
                  var6--;
               } else {
                  var6++;
               }
               break;
            case 3:
               if (var4) {
                  var6--;
               } else {
                  var6++;
                  var7++;
                  var9 = false;
               }

               var10 = BlockRailBase$EnumRailDirection.EAST_WEST;
               break;
            case 4:
               if (var4) {
                  var6--;
                  var7++;
                  var9 = false;
               } else {
                  var6++;
               }

               var10 = BlockRailBase$EnumRailDirection.EAST_WEST;
               break;
            case 5:
               if (var4) {
                  var8++;
               } else {
                  var8--;
                  var7++;
                  var9 = false;
               }

               var10 = BlockRailBase$EnumRailDirection.NORTH_SOUTH;
               break;
            case 6:
               if (var4) {
                  var8++;
                  var7++;
                  var9 = false;
               } else {
                  var8--;
               }

               var10 = BlockRailBase$EnumRailDirection.NORTH_SOUTH;
         }

         return this.func_176567_a(var1, new BlockPos(var6, var7, var8), var4, var5, var10)
            ? true
            : var9 && this.func_176567_a(var1, new BlockPos(var6, var7 - 1, var8), var4, var5, var10);
      }
   }
}
