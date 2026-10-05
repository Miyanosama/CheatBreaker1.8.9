package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityPainting$EnumArt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenRiver;

public class BlockLever extends Block {
   public static PropertyEnum<BlockLever$EnumOrientation> FACING = PropertyEnum.create("facing", BlockLever$EnumOrientation.class);
   public EntityPainting$EnumArt field_0003;
   public BiomeGenRiver field_0000;
   public ItemPiston field_0001;
   public static PropertyBool POWERED = PropertyBool.create("powered");
   public MathHelper field_0004;

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(FACING).getMetadata();
      if (var1.getValue(POWERED)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      if (var3.getValue(POWERED)) {
         var1.notifyNeighborsOfStateChange(var2, this);
         EnumFacing var4 = var3.getValue(FACING).getFacing();
         var1.notifyNeighborsOfStateChange(var2.a(var4.getOpposite()), this);
      }

      super.breakBlock(var1, var2, var3);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(FACING, BlockLever$EnumOrientation.byMetadata(var1 & 7)).withProperty(POWERED, (var1 & 8) > 0);
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (this.func_181091_e(var1, var2, var3) && !func_181090_a(var1, var2, var3.getValue(FACING).getFacing().getOpposite())) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
      }
   }

   @Override
   public int getStrongPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return !var3.getValue(POWERED) ? 0 : (var3.getValue(FACING).getFacing() == var4 ? 15 : 0);
   }

   public BlockLever() {
      super(Material.circuits);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, BlockLever$EnumOrientation.NORTH).withProperty(POWERED, false));
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, POWERED);
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var1.D) {
         return true;
      } else {
         var3 = var3.cycleProperty(POWERED);
         var1.a(var2, var3, 3);
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, "random.click", 0.3F, var3.getValue(POWERED) ? 0.6F : 0.5F);
         var1.notifyNeighborsOfStateChange(var2, this);
         EnumFacing var9 = var3.getValue(FACING).getFacing();
         var1.notifyNeighborsOfStateChange(var2.a(var9.getOpposite()), this);
         return true;
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   public static boolean func_181090_a(World var0, BlockPos var1, EnumFacing var2) {
      return BlockButton.func_181088_a(var0, var1, var2);
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      IBlockState var9 = this.getDefaultState().withProperty(POWERED, false);
      if (func_181090_a(var1, var2, var3.getOpposite())) {
         return var9.withProperty(FACING, BlockLever$EnumOrientation.forFacings(var3, var8.getHorizontalFacing()));
      } else {
         for (EnumFacing var11 : EnumFacing$Plane.HORIZONTAL) {
            if (var11 != var3 && func_181090_a(var1, var2, var11.getOpposite())) {
               return var9.withProperty(FACING, BlockLever$EnumOrientation.forFacings(var11, var8.getHorizontalFacing()));
            }
         }

         return World.doesBlockHaveSolidTopSurface(var1, var2.down())
            ? var9.withProperty(FACING, BlockLever$EnumOrientation.forFacings(EnumFacing.UP, var8.getHorizontalFacing()))
            : var9;
      }
   }

   public static int getMetadataForFacing(EnumFacing var0) {
      switch (BlockLever$1.field_180165_a[var0.ordinal()]) {
         case 1:
            return 0;
         case 2:
            return 5;
         case 3:
            return 4;
         case 4:
            return 3;
         case 5:
            return 2;
         case 6:
            return 1;
         default:
            return -1;
      }
   }

   public boolean func_181091_e(World var1, BlockPos var2, IBlockState var3) {
      if (this.canPlaceBlockAt(var1, var2)) {
         return true;
      } else {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
         return false;
      }
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      float var3 = 0.1875F;
      switch (BlockLever$1.field_180163_b[var1.getBlockState(var2).getValue(FACING).ordinal()]) {
         case 1:
            this.a(0.0F, 0.2F, 0.5F - var3, var3 * 2.0F, 0.8F, 0.5F + var3);
            break;
         case 2:
            this.a(1.0F - var3 * 2.0F, 0.2F, 0.5F - var3, 1.0F, 0.8F, 0.5F + var3);
            break;
         case 3:
            this.a(0.5F - var3, 0.2F, 0.0F, 0.5F + var3, 0.8F, var3 * 2.0F);
            break;
         case 4:
            this.a(0.5F - var3, 0.2F, 1.0F - var3 * 2.0F, 0.5F + var3, 0.8F, 1.0F);
            break;
         case 5:
         case 6:
            var3 = 0.25F;
            this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.6F, 0.5F + var3);
            break;
         case 7:
         case 8:
            var3 = 0.25F;
            this.a(0.5F - var3, 0.4F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return var3.getValue(POWERED) ? 15 : 0;
   }

   @Override
   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3) {
      return func_181090_a(var1, var2, var3.getOpposite());
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      for (EnumFacing var6 : EnumFacing.values()) {
         if (func_181090_a(var1, var2, var6)) {
            return true;
         }
      }

      return false;
   }
}
