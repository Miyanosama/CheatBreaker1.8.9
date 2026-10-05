package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockButton extends Block {
   public static PropertyDirection FACING = PropertyDirection.create("facing");
   public static PropertyBool POWERED = PropertyBool.create("powered");
   public boolean wooden;

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (!var1.D && var3.getValue(POWERED)) {
         if (this.wooden) {
            this.checkForArrows(var1, var2, var3);
         } else {
            var1.setBlockState(var2, var3.withProperty(POWERED, false));
            this.notifyNeighbors(var1, var2, var3.getValue(FACING));
            var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, "random.click", 0.3F, 0.5F);
            var1.markBlockRangeForRenderUpdate(var2, var2);
         }
      }
   }

   @Override
   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3) {
      return func_181088_a(var1, var2, var3.getOpposite());
   }

   public boolean checkForDrop(World var1, BlockPos var2, IBlockState var3) {
      if (this.canPlaceBlockAt(var1, var2)) {
         return true;
      } else {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
         return false;
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   public void updateBlockBounds(IBlockState var1) {
      EnumFacing var2 = var1.getValue(FACING);
      boolean var3 = var1.getValue(POWERED);
      float var4 = 0.25F;
      float var5 = 0.375F;
      float var6 = (var3 ? 1 : 2) / 16.0F;
      float var7 = 0.125F;
      float var8 = 0.1875F;
      switch (var2) {
         case EAST:
            this.a(0.0F, 0.375F, 0.3125F, var6, 0.625F, 0.6875F);
            break;
         case WEST:
            this.a(1.0F - var6, 0.375F, 0.3125F, 1.0F, 0.625F, 0.6875F);
            break;
         case SOUTH:
            this.a(0.3125F, 0.375F, 0.0F, 0.6875F, 0.625F, var6);
            break;
         case NORTH:
            this.a(0.3125F, 0.375F, 1.0F - var6, 0.6875F, 0.625F, 1.0F);
            break;
         case UP:
            this.a(0.3125F, 0.0F, 0.375F, 0.6875F, 0.0F + var6, 0.625F);
            break;
         case DOWN:
            this.a(0.3125F, 1.0F - var6, 0.375F, 0.6875F, 1.0F, 0.625F);
      }
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      for (EnumFacing var6 : EnumFacing.values()) {
         if (func_181088_a(var1, var2, var6)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity var4) {
      if (!var1.D && this.wooden && !var3.getValue(POWERED)) {
         this.checkForArrows(var1, var2, var3);
      }
   }

   public BlockButton(boolean var1) {
      super(Material.circuits);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.wooden = var1;
   }

   public void checkForArrows(World var1, BlockPos var2, IBlockState var3) {
      this.updateBlockBounds(var3);
      List var4 = var1.getEntitiesWithinAABB(
         EntityArrow.class,
         new AxisAlignedBB(var2.getX() + this.B, var2.getY() + this.C, var2.getZ() + this.D, var2.getX() + this.E, var2.getY() + this.F, var2.getZ() + this.G)
      );
      boolean var5 = !var4.isEmpty();
      boolean var6 = var3.getValue(POWERED);
      if (var5 && !var6) {
         var1.setBlockState(var2, var3.withProperty(POWERED, true));
         this.notifyNeighbors(var1, var2, var3.getValue(FACING));
         var1.markBlockRangeForRenderUpdate(var2, var2);
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, "random.click", 0.3F, 0.6F);
      }

      if (!var5 && var6) {
         var1.setBlockState(var2, var3.withProperty(POWERED, false));
         this.notifyNeighbors(var1, var2, var3.getValue(FACING));
         var1.markBlockRangeForRenderUpdate(var2, var2);
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, "random.click", 0.3F, 0.5F);
      }

      if (var5) {
         var1.scheduleUpdate(var2, this, this.tickRate(var1));
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2;
      switch (var1.getValue(FACING)) {
         case EAST:
            var2 = 1;
            break;
         case WEST:
            var2 = 2;
            break;
         case SOUTH:
            var2 = 3;
            break;
         case NORTH:
            var2 = 4;
            break;
         case UP:
         default:
            var2 = 5;
            break;
         case DOWN:
            var2 = 0;
      }

      if (var1.getValue(POWERED)) {
         var2 |= 8;
      }

      return var2;
   }

   public void notifyNeighbors(World var1, BlockPos var2, EnumFacing var3) {
      var1.notifyNeighborsOfStateChange(var2, this);
      var1.notifyNeighborsOfStateChange(var2.a(var3.getOpposite()), this);
   }

   @Override
   public int tickRate(World var1) {
      return this.wooden ? 30 : 20;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (this.checkForDrop(var1, var2, var3) && !func_181088_a(var1, var2, var3.getValue(FACING).getOpposite())) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, POWERED);
   }

   @Override
   public void randomTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return func_181088_a(var1, var2, var3.getOpposite())
         ? this.getDefaultState().withProperty(FACING, var3).withProperty(POWERED, false)
         : this.getDefaultState().withProperty(FACING, EnumFacing.DOWN).withProperty(POWERED, false);
   }

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return var3.getValue(POWERED) ? 15 : 0;
   }

   public static boolean func_181088_a(World var0, BlockPos var1, EnumFacing var2) {
      BlockPos var3 = var1.a(var2);
      return var2 == EnumFacing.DOWN ? World.doesBlockHaveSolidTopSurface(var0, var3) : var0.getBlockState(var3).getBlock().isNormalCube();
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      this.updateBlockBounds(var1.getBlockState(var2));
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      EnumFacing var2;
      switch (var1 & 7) {
         case 0:
            var2 = EnumFacing.DOWN;
            break;
         case 1:
            var2 = EnumFacing.EAST;
            break;
         case 2:
            var2 = EnumFacing.WEST;
            break;
         case 3:
            var2 = EnumFacing.SOUTH;
            break;
         case 4:
            var2 = EnumFacing.NORTH;
            break;
         case 5:
         default:
            var2 = EnumFacing.UP;
      }

      return this.getDefaultState().withProperty(FACING, var2).withProperty(POWERED, (var1 & 8) > 0);
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      if (var3.getValue(POWERED)) {
         this.notifyNeighbors(var1, var2, var3.getValue(FACING));
      }

      super.breakBlock(var1, var2, var3);
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var3.getValue(POWERED)) {
         return true;
      } else {
         var1.a(var2, var3.withProperty(POWERED, true), 3);
         var1.markBlockRangeForRenderUpdate(var2, var2);
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, "random.click", 0.3F, 0.6F);
         this.notifyNeighbors(var1, var2, var3.getValue(FACING));
         var1.scheduleUpdate(var2, this, this.tickRate(var1));
         return true;
      }
   }

   @Override
   public int getStrongPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return !var3.getValue(POWERED) ? 0 : (var3.getValue(FACING) == var4 ? 15 : 0);
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float var1 = 0.1875F;
      float var2 = 0.125F;
      float var3 = 0.125F;
      this.a(0.5F - var1, 0.5F - var2, 0.5F - var3, 0.5F + var1, 0.5F + var2, 0.5F + var3);
   }
}
