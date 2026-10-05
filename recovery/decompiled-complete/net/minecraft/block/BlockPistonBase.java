package net.minecraft.block;

import com.cheatbreaker.client.websocket.shared.WSPacketFriendUpdate;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockPistonStructureHelper;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass5030;

public class BlockPistonBase extends Block {
   public UnidentifiedClass5030 field_0002;
   public static PropertyBool EXTENDED = PropertyBool.create("extended");
   public static PropertyDirection FACING = PropertyDirection.create("facing");
   public boolean isSticky;
   public GuiCreateWorld field_0005;
   public WSPacketFriendUpdate field_0004;

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      if (var3.getBlock() == this && var3.getValue(EXTENDED)) {
         float var4 = 0.25F;
         EnumFacing var5 = var3.getValue(FACING);
         if (var5 != null) {
            switch (BlockPistonBase$1.field_177243_a[var5.ordinal()]) {
               case 1:
                  this.a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case 2:
                  this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                  break;
               case 3:
                  this.a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                  break;
               case 4:
                  this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                  break;
               case 5:
                  this.a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case 6:
                  this.a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
         }
      } else {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   @Override
   public void addCollisionBoxesToList(World var1, BlockPos var2, IBlockState var3, AxisAlignedBB var4, List<AxisAlignedBB> var5, Entity var6) {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return this.getDefaultState().withProperty(FACING, getFacingFromEntity(var1, var2, var8)).withProperty(EXTENDED, false);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public void checkForMove(World var1, BlockPos var2, IBlockState var3) {
      EnumFacing var4 = var3.getValue(FACING);
      boolean var5 = this.shouldBeExtended(var1, var2, var4);
      if (var5 && !var3.getValue(EXTENDED)) {
         if (new BlockPistonStructureHelper(var1, var2, var4, true).canMove()) {
            var1.addBlockEvent(var2, this, 0, var4.getIndex());
         }
      } else if (!var5 && var3.getValue(EXTENDED)) {
         var1.a(var2, var3.withProperty(EXTENDED, false), 2);
         var1.addBlockEvent(var2, this, 1, var4.getIndex());
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, EXTENDED);
   }

   @Override
   public boolean onBlockEventReceived(World var1, BlockPos var2, IBlockState var3, int var4, int var5) {
      EnumFacing var6 = var3.getValue(FACING);
      if (!var1.D) {
         boolean var7 = this.shouldBeExtended(var1, var2, var6);
         if (var7 && var4 == 1) {
            var1.a(var2, var3.withProperty(EXTENDED, true), 2);
            return false;
         }

         if (!var7 && var4 == 0) {
            return false;
         }
      }

      if (var4 == 0) {
         if (!this.doMove(var1, var2, var6, true)) {
            return false;
         }

         var1.a(var2, var3.withProperty(EXTENDED, true), 2);
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, "tile.piston.out", 0.5F, var1.s.nextFloat() * 0.25F + 0.6F);
      } else if (var4 == 1) {
         TileEntity var13 = var1.getTileEntity(var2.a(var6));
         if (var13 instanceof TileEntityPiston) {
            ((TileEntityPiston)var13).clearPistonTileEntity();
         }

         var1.a(
            var2,
            Blocks.piston_extension
               .getDefaultState()
               .withProperty(BlockPistonMoving.FACING, var6)
               .withProperty(BlockPistonMoving.TYPE, this.isSticky ? BlockPistonExtension$EnumPistonType.STICKY : BlockPistonExtension$EnumPistonType.DEFAULT),
            3
         );
         var1.setTileEntity(var2, BlockPistonMoving.newTileEntity(this.getStateFromMeta(var5), var6, false, true));
         if (this.isSticky) {
            BlockPos var8 = var2.add(var6.getFrontOffsetX() * 2, var6.getFrontOffsetY() * 2, var6.getFrontOffsetZ() * 2);
            Block var9 = var1.getBlockState(var8).getBlock();
            boolean var10 = false;
            if (var9 == Blocks.piston_extension) {
               TileEntity var11 = var1.getTileEntity(var8);
               if (var11 instanceof TileEntityPiston) {
                  TileEntityPiston var12 = (TileEntityPiston)var11;
                  if (var12.getFacing() == var6 && var12.isExtending()) {
                     var12.clearPistonTileEntity();
                     var10 = true;
                  }
               }
            }

            if (!var10
               && var9.getMaterial() != Material.air
               && canPush(var9, var1, var8, var6.getOpposite(), false)
               && (var9.getMobilityFlag() == 0 || var9 == Blocks.piston || var9 == Blocks.sticky_piston)) {
               this.doMove(var1, var2, var6, false);
            }
         } else {
            var1.setBlockToAir(var2.a(var6));
         }

         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, "tile.piston.in", 0.5F, var1.s.nextFloat() * 0.15F + 0.6F);
      }

      return true;
   }

   public static EnumFacing getFacing(int var0) {
      int var1 = var0 & 7;
      return var1 > 5 ? null : EnumFacing.getFront(var1);
   }

   public static boolean canPush(Block var0, World var1, BlockPos var2, EnumFacing var3, boolean var4) {
      if (var0 == Blocks.obsidian) {
         return false;
      } else if (!var1.af().contains(var2)) {
         return false;
      } else if (var2.getY() >= 0 && (var3 != EnumFacing.DOWN || var2.getY() != 0)) {
         if (var2.getY() <= var1.getHeight() - 1 && (var3 != EnumFacing.UP || var2.getY() != var1.getHeight() - 1)) {
            if (var0 != Blocks.piston && var0 != Blocks.sticky_piston) {
               if (var0.getBlockHardness(var1, var2) == -1.0F) {
                  return false;
               }

               if (var0.getMobilityFlag() == 2) {
                  return false;
               }

               if (var0.getMobilityFlag() == 1) {
                  if (!var4) {
                     return false;
                  }

                  return true;
               }
            } else if (var1.getBlockState(var2).getValue(EXTENDED)) {
               return false;
            }

            return !(var0 instanceof ITileEntityProvider);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      if (!var1.D && var1.getTileEntity(var2) == null) {
         this.checkForMove(var1, var2, var3);
      }
   }

   public boolean doMove(World var1, BlockPos var2, EnumFacing var3, boolean var4) {
      if (!var4) {
         var1.setBlockToAir(var2.a(var3));
      }

      BlockPistonStructureHelper var5 = new BlockPistonStructureHelper(var1, var2, var3, var4);
      List var6 = var5.getBlocksToMove();
      List var7 = var5.getBlocksToDestroy();
      if (!var5.canMove()) {
         return false;
      } else {
         int var8 = var6.size() + var7.size();
         Block[] var9 = new Block[var8];
         EnumFacing var10 = var4 ? var3 : var3.getOpposite();

         for (int var11 = var7.size() - 1; var11 >= 0; var11--) {
            BlockPos var12 = (BlockPos)var7.get(var11);
            Block var13 = var1.getBlockState(var12).getBlock();
            var13.dropBlockAsItem(var1, var12, var1.getBlockState(var12), 0);
            var1.setBlockToAir(var12);
            var9[--var8] = var13;
         }

         for (int var15 = var6.size() - 1; var15 >= 0; var15--) {
            BlockPos var17 = (BlockPos)var6.get(var15);
            IBlockState var22 = var1.getBlockState(var17);
            Block var14 = var22.getBlock();
            var14.getMetaFromState(var22);
            var1.setBlockToAir(var17);
            var17 = var17.a(var10);
            var1.a(var17, Blocks.piston_extension.getDefaultState().withProperty(FACING, var3), 4);
            var1.setTileEntity(var17, BlockPistonMoving.newTileEntity(var22, var3, var4, false));
            var9[--var8] = var14;
         }

         BlockPos var16 = var2.a(var3);
         if (var4) {
            BlockPistonExtension$EnumPistonType var19 = this.isSticky
               ? BlockPistonExtension$EnumPistonType.STICKY
               : BlockPistonExtension$EnumPistonType.DEFAULT;
            IBlockState var23 = Blocks.piston_head
               .getDefaultState()
               .withProperty(BlockPistonExtension.FACING, var3)
               .withProperty(BlockPistonExtension.TYPE, var19);
            IBlockState var24 = Blocks.piston_extension
               .getDefaultState()
               .withProperty(BlockPistonMoving.FACING, var3)
               .withProperty(BlockPistonMoving.TYPE, this.isSticky ? BlockPistonExtension$EnumPistonType.STICKY : BlockPistonExtension$EnumPistonType.DEFAULT);
            var1.a(var16, var24, 4);
            var1.setTileEntity(var16, BlockPistonMoving.newTileEntity(var23, var3, true, false));
         }

         for (int var20 = var7.size() - 1; var20 >= 0; var20--) {
            var1.notifyNeighborsOfStateChange((BlockPos)var7.get(var20), var9[var8++]);
         }

         for (int var21 = var6.size() - 1; var21 >= 0; var21--) {
            var1.notifyNeighborsOfStateChange((BlockPos)var6.get(var21), var9[var8++]);
         }

         if (var4) {
            var1.notifyNeighborsOfStateChange(var16, Blocks.piston_head);
            var1.notifyNeighborsOfStateChange(var2, this);
         }

         return true;
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getCollisionBoundingBox(var1, var2, var3);
   }

   @Override
   public IBlockState getStateForEntityRender(IBlockState var1) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.UP);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(FACING, getFacing(var1)).withProperty(EXTENDED, (var1 & 8) > 0);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(FACING).getIndex();
      if (var1.getValue(EXTENDED)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.D) {
         this.checkForMove(var1, var2, var3);
      }
   }

   public boolean shouldBeExtended(World var1, BlockPos var2, EnumFacing var3) {
      for (EnumFacing var7 : EnumFacing.values()) {
         if (var7 != var3 && var1.isSidePowered(var2.a(var7), var7)) {
            return true;
         }
      }

      if (var1.isSidePowered(var2, EnumFacing.DOWN)) {
         return true;
      } else {
         BlockPos var9 = var2.up();

         for (EnumFacing var8 : EnumFacing.values()) {
            if (var8 != EnumFacing.DOWN && var1.isSidePowered(var9.a(var8), var8)) {
               return true;
            }
         }

         return false;
      }
   }

   public BlockPistonBase(boolean var1) {
      super(Material.piston);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EXTENDED, false));
      this.isSticky = var1;
      this.setStepSound(i);
      this.setHardness(0.5F);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public static EnumFacing getFacingFromEntity(World var0, BlockPos var1, EntityLivingBase var2) {
      if (MathHelper.abs((float)var2.s - var1.getX()) < 2.0F && MathHelper.abs((float)var2.u - var1.getZ()) < 2.0F) {
         double var3 = var2.t + var2.getEyeHeight();
         if (var3 - var1.getY() > 2.0) {
            return EnumFacing.UP;
         }

         if (var1.getY() - var3 > 0.0) {
            return EnumFacing.DOWN;
         }
      }

      return var2.getHorizontalFacing().getOpposite();
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      var1.a(var2, var3.withProperty(FACING, getFacingFromEntity(var1, var2, var4)), 2);
      if (!var1.D) {
         this.checkForMove(var1, var2, var3);
      }
   }
}
