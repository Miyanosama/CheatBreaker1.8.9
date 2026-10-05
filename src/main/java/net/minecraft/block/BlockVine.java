package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVine extends Block {
   public static PropertyBool UP = PropertyBool.create("up");
   public static PropertyBool NORTH = PropertyBool.create("north");
   public static PropertyBool EAST = PropertyBool.create("east");
   public static PropertyBool SOUTH = PropertyBool.create("south");
   public static PropertyBool WEST = PropertyBool.create("west");
   public static PropertyBool[] ALL_FACES = new PropertyBool[]{UP, BlockVine.NORTH, SOUTH, WEST, EAST};

   @Override
   public int quantityDropped(Random var1) {
      return 0;
   }

   public boolean recheckGrownSides(World var1, BlockPos var2, IBlockState var3) {
      IBlockState var4 = var3;

      for (EnumFacing var6 : EnumFacing.Plane.HORIZONTAL) {
         PropertyBool var7 = getPropertyFor(var6);
         if (var3.getValue(var7) && !this.canPlaceOn(var1.getBlockState(var2.a(var6)).getBlock())) {
            IBlockState var8 = var1.getBlockState(var2.up());
            if (var8.getBlock() != this || !var8.getValue(var7)) {
               var3 = var3.withProperty(var7, false);
            }
         }
      }

      if (getNumGrownFaces(var3) == 0) {
         return false;
      } else {
         if (var4 != var3) {
            var1.a(var2, var3, 2);
         }

         return true;
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState()
         .withProperty(SOUTH, (var1 & 1) > 0)
         .withProperty(WEST, (var1 & 2) > 0)
         .withProperty(NORTH, (var1 & 4) > 0)
         .withProperty(EAST, (var1 & 8) > 0);
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public boolean canPlaceOn(Block var1) {
      return var1.isFullCube() && var1.J.blocksMovement();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, UP, NORTH, EAST, SOUTH, WEST);
   }

   public static PropertyBool getPropertyFor(EnumFacing var0) {
      switch (var0) {
         case UP:
            return UP;
         case NORTH:
            return NORTH;
         case SOUTH:
            return SOUTH;
         case EAST:
            return EAST;
         case WEST:
            return WEST;
         default:
            throw new IllegalArgumentException(var0 + " is an invalid choice");
      }
   }

   public BlockVine() {
      super(Material.vine);
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(UP, false)
            .withProperty(NORTH, false)
            .withProperty(EAST, false)
            .withProperty(SOUTH, false)
            .withProperty(WEST, false)
      );
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.D && !this.recheckGrownSides(var1, var2, var3)) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
      }
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      float var3 = 0.0625F;
      float var4 = 1.0F;
      float var5 = 1.0F;
      float var6 = 1.0F;
      float var7 = 0.0F;
      float var8 = 0.0F;
      float var9 = 0.0F;
      boolean var10 = false;
      if (var1.getBlockState(var2).getValue(WEST)) {
         var7 = Math.max(var7, 0.0625F);
         var4 = 0.0F;
         var5 = 0.0F;
         var8 = 1.0F;
         var6 = 0.0F;
         var9 = 1.0F;
         var10 = true;
      }

      if (var1.getBlockState(var2).getValue(EAST)) {
         var4 = Math.min(var4, 0.9375F);
         var7 = 1.0F;
         var5 = 0.0F;
         var8 = 1.0F;
         var6 = 0.0F;
         var9 = 1.0F;
         var10 = true;
      }

      if (var1.getBlockState(var2).getValue(NORTH)) {
         var9 = Math.max(var9, 0.0625F);
         var6 = 0.0F;
         var4 = 0.0F;
         var7 = 1.0F;
         var5 = 0.0F;
         var8 = 1.0F;
         var10 = true;
      }

      if (var1.getBlockState(var2).getValue(SOUTH)) {
         var6 = Math.min(var6, 0.9375F);
         var9 = 1.0F;
         var4 = 0.0F;
         var7 = 1.0F;
         var5 = 0.0F;
         var8 = 1.0F;
         var10 = true;
      }

      if (!var10 && this.canPlaceOn(var1.getBlockState(var2.up()).getBlock())) {
         var5 = Math.min(var5, 0.9375F);
         var8 = 1.0F;
         var4 = 0.0F;
         var7 = 1.0F;
         var6 = 0.0F;
         var9 = 1.0F;
      }

      this.a(var4, var5, var6, var7, var8, var9);
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return var1.withProperty(UP, var2.getBlockState(var3.up()).getBlock().isBlockNormalCube());
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return null;
   }

   public static int getNumGrownFaces(IBlockState var0) {
      int var1 = 0;

      for (PropertyBool var5 : ALL_FACES) {
         if (var0.getValue(var5)) {
            var1++;
         }
      }

      return var1;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
      return var1.getBiomeGenForCoords(var2).getFoliageColorAtPos(var2);
   }

   @Override
   public void harvestBlock(World var1, EntityPlayer var2, BlockPos var3, IBlockState var4, TileEntity var5) {
      if (!var1.D && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().getItem() == Items.shears) {
         var2.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         a(var1, var3, new ItemStack(Blocks.vine, 1, 0));
      } else {
         super.harvestBlock(var1, var2, var3, var4, var5);
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public boolean isReplaceable(World var1, BlockPos var2) {
      return true;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (!var1.D && var1.s.nextInt(4) == 0) {
         byte var5 = 4;
         int var6 = 5;
         boolean var7 = false;

         label182:
         for (int var8 = -var5; var8 <= var5; var8++) {
            for (int var9 = -var5; var9 <= var5; var9++) {
               for (int var10 = -1; var10 <= 1; var10++) {
                  if (var1.getBlockState(var2.add(var8, var10, var9)).getBlock() == this) {
                     if (--var6 <= 0) {
                        var7 = true;
                        break label182;
                     }
                  }
               }
            }
         }

         EnumFacing var18 = EnumFacing.random(var4);
         BlockPos var19 = var2.up();
         if (var18 == EnumFacing.UP && var2.getY() < 255 && var1.isAirBlock(var19)) {
            if (!var7) {
               IBlockState var22 = var3;

               for (EnumFacing var26 : EnumFacing.Plane.HORIZONTAL) {
                  if (var4.nextBoolean() || !this.canPlaceOn(var1.getBlockState(var19.a(var26)).getBlock())) {
                     var22 = var22.withProperty(getPropertyFor(var26), false);
                  }
               }

               if (var22.getValue(NORTH) || var22.getValue(EAST) || var22.getValue(SOUTH) || var22.getValue(WEST)) {
                  var1.a(var19, var22, 2);
               }
            }
         } else if (!var18.getAxis().isHorizontal() || var3.getValue(getPropertyFor(var18))) {
            if (var2.getY() > 1) {
               BlockPos var21 = var2.down();
               IBlockState var23 = var1.getBlockState(var21);
               Block var25 = var23.getBlock();
               if (var25.J == Material.air) {
                  IBlockState var27 = var3;

                  for (EnumFacing var31 : EnumFacing.Plane.HORIZONTAL) {
                     if (var4.nextBoolean()) {
                        var27 = var27.withProperty(getPropertyFor(var31), false);
                     }
                  }

                  if (var27.getValue(NORTH) || var27.getValue(EAST) || var27.getValue(SOUTH) || var27.getValue(WEST)) {
                     var1.a(var21, var27, 2);
                  }
               } else if (var25 == this) {
                  IBlockState var28 = var23;

                  for (EnumFacing var32 : EnumFacing.Plane.HORIZONTAL) {
                     PropertyBool var33 = getPropertyFor(var32);
                     if (var4.nextBoolean() && var3.getValue(var33)) {
                        var28 = var28.withProperty(var33, true);
                     }
                  }

                  if (var28.getValue(NORTH) || var28.getValue(EAST) || var28.getValue(SOUTH) || var28.getValue(WEST)) {
                     var1.a(var21, var28, 2);
                  }
               }
            }
         } else if (!var7) {
            BlockPos var20 = var2.a(var18);
            Block var11 = var1.getBlockState(var20).getBlock();
            if (var11.J == Material.air) {
               EnumFacing var12 = var18.rotateY();
               EnumFacing var13 = var18.rotateYCCW();
               boolean var14 = var3.getValue(getPropertyFor(var12));
               boolean var15 = var3.getValue(getPropertyFor(var13));
               BlockPos var16 = var20.a(var12);
               BlockPos var17 = var20.a(var13);
               if (var14 && this.canPlaceOn(var1.getBlockState(var16).getBlock())) {
                  var1.a(var20, this.getDefaultState().withProperty(getPropertyFor(var12), true), 2);
               } else if (var15 && this.canPlaceOn(var1.getBlockState(var17).getBlock())) {
                  var1.a(var20, this.getDefaultState().withProperty(getPropertyFor(var13), true), 2);
               } else if (var14 && var1.isAirBlock(var16) && this.canPlaceOn(var1.getBlockState(var2.a(var12)).getBlock())) {
                  var1.a(var16, this.getDefaultState().withProperty(getPropertyFor(var18.getOpposite()), true), 2);
               } else if (var15 && var1.isAirBlock(var17) && this.canPlaceOn(var1.getBlockState(var2.a(var13)).getBlock())) {
                  var1.a(var17, this.getDefaultState().withProperty(getPropertyFor(var18.getOpposite()), true), 2);
               } else if (this.canPlaceOn(var1.getBlockState(var20.up()).getBlock())) {
                  var1.a(var20, this.getDefaultState(), 2);
               }
            } else if (var11.J.isOpaque() && var11.isFullCube()) {
               var1.a(var2, var3.withProperty(getPropertyFor(var18), true), 2);
            }
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      if (var1.getValue(SOUTH)) {
         var2 |= 1;
      }

      if (var1.getValue(WEST)) {
         var2 |= 2;
      }

      if (var1.getValue(NORTH)) {
         var2 |= 4;
      }

      if (var1.getValue(EAST)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public int getRenderColor(IBlockState var1) {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3) {
      switch (var3) {
         case UP:
            return this.canPlaceOn(var1.getBlockState(var2.up()).getBlock());
         case NORTH:
         case SOUTH:
         case EAST:
         case WEST:
            return this.canPlaceOn(var1.getBlockState(var2.a(var3.getOpposite())).getBlock());
         default:
            return false;
      }
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      IBlockState var9 = this.getDefaultState()
         .withProperty(UP, false)
         .withProperty(NORTH, false)
         .withProperty(EAST, false)
         .withProperty(SOUTH, false)
         .withProperty(WEST, false);
      return var3.getAxis().isHorizontal() ? var9.withProperty(getPropertyFor(var3.getOpposite()), true) : var9;
   }

   @Override
   public int getBlockColor() {
      return ColorizerFoliage.getFoliageColorBasic();
   }
}
