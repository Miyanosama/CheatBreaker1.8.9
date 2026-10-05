package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFenceGate extends BlockDirectional {
   public static PropertyBool OPEN = PropertyBool.create("open");
   public static PropertyBool POWERED = PropertyBool.create("powered");
   public static PropertyBool IN_WALL = PropertyBool.create("in_wall");

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, O, OPEN, POWERED, IN_WALL);
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return var1.getBlockState(var2.down()).getBlock().getMaterial().isSolid() ? super.canPlaceBlockAt(var1, var2) : false;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var3.getValue(OPEN)) {
         var3 = var3.withProperty(OPEN, false);
         var1.a(var2, var3, 2);
      } else {
         EnumFacing var9 = EnumFacing.fromAngle(var4.y);
         if (var3.getValue(O) == var9.getOpposite()) {
            var3 = var3.withProperty(O, var9);
         }

         var3 = var3.withProperty(OPEN, true);
         var1.a(var2, var3, 2);
      }

      var1.playAuxSFXAtEntity(var4, var3.getValue(OPEN) ? 1003 : 1006, var2, 0);
      return true;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      EnumFacing.Axis var3 = var1.getBlockState(var2).getValue(O).getAxis();
      if (var3 == EnumFacing.Axis.Z) {
         this.a(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
      } else {
         this.a(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
      }
   }

   @Override
   public boolean isPassable(IBlockAccess var1, BlockPos var2) {
      return var1.getBlockState(var2).getValue(OPEN);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(O, EnumFacing.getHorizontal(var1)).withProperty(OPEN, (var1 & 4) != 0).withProperty(POWERED, (var1 & 8) != 0);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      if (var3.getValue(OPEN)) {
         return null;
      } else {
         EnumFacing.Axis var4 = var3.getValue(O).getAxis();
         return var4 == EnumFacing.Axis.Z
            ? new AxisAlignedBB(var2.getX(), var2.getY(), var2.getZ() + 0.375F, var2.getX() + 1, var2.getY() + 1.5F, var2.getZ() + 0.625F)
            : new AxisAlignedBB(var2.getX() + 0.375F, var2.getY(), var2.getZ(), var2.getX() + 0.625F, var2.getY() + 1.5F, var2.getZ() + 1);
      }
   }

   public BlockFenceGate(BlockPlanks.EnumType var1) {
      super(Material.wood, var1.getMapColor());
      this.setDefaultState(this.M.getBaseState().withProperty(OPEN, false).withProperty(POWERED, false).withProperty(IN_WALL, false));
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return this.getDefaultState()
         .withProperty(O, var8.getHorizontalFacing())
         .withProperty(OPEN, false)
         .withProperty(POWERED, false)
         .withProperty(IN_WALL, false);
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      EnumFacing.Axis var4 = var1.getValue(O).getAxis();
      if (var4 == EnumFacing.Axis.Z
            && (var2.getBlockState(var3.west()).getBlock() == Blocks.cobblestone_wall || var2.getBlockState(var3.east()).getBlock() == Blocks.cobblestone_wall)
         || var4 == EnumFacing.Axis.X
            && (
               var2.getBlockState(var3.north()).getBlock() == Blocks.cobblestone_wall || var2.getBlockState(var3.south()).getBlock() == Blocks.cobblestone_wall
            )) {
         var1 = var1.withProperty(IN_WALL, true);
      }

      return var1;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(O).getHorizontalIndex();
      if (var1.getValue(POWERED)) {
         var2 |= 8;
      }

      if (var1.getValue(OPEN)) {
         var2 |= 4;
      }

      return var2;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.D) {
         boolean var5 = var1.isBlockPowered(var2);
         if (var5 || var4.canProvidePower()) {
            if (var5 && !var3.getValue(OPEN) && !var3.getValue(POWERED)) {
               var1.a(var2, var3.withProperty(OPEN, true).withProperty(POWERED, true), 2);
               var1.playAuxSFXAtEntity((EntityPlayer)null, 1003, var2, 0);
            } else if (!var5 && var3.getValue(OPEN) && var3.getValue(POWERED)) {
               var1.a(var2, var3.withProperty(OPEN, false).withProperty(POWERED, false), 2);
               var1.playAuxSFXAtEntity((EntityPlayer)null, 1006, var2, 0);
            } else if (var5 != var3.getValue(POWERED)) {
               var1.a(var2, var3.withProperty(POWERED, var5), 2);
            }
         }
      }
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return true;
   }
}
