package net.minecraft.block;

import com.cheatbreaker.client.nethandler.server.PacketAddHologram;
import io.netty.handler.codec.http.HttpHeaders$Values;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonMoving extends BlockContainer {
   public static PropertyEnum<BlockPistonExtension$EnumPistonType> TYPE = BlockPistonExtension.TYPE;
   public static PropertyDirection FACING = BlockPistonExtension.FACING;
   public ModelGhast field_0000;
   public PacketAddHologram field_0001;
   public HttpHeaders$Values field_0004;

   public TileEntityPiston getTileEntity(IBlockAccess var1, BlockPos var2) {
      TileEntity var3 = var1.getTileEntity(var2);
      return var3 instanceof TileEntityPiston ? (TileEntityPiston)var3 : null;
   }

   @Override
   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3) {
      return false;
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      TileEntityPiston var4 = this.getTileEntity(var1, var2);
      if (var4 == null) {
         return null;
      } else {
         float var5 = var4.getProgress(0.0F);
         if (var4.isExtending()) {
            var5 = 1.0F - var5;
         }

         return this.getBoundingBox(var1, var2, var4.getPistonState(), var5, var4.getFacing());
      }
   }

   public BlockPistonMoving() {
      super(Material.piston);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, BlockPistonExtension$EnumPistonType.DEFAULT));
      this.setHardness(-1.0F);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      TileEntityPiston var3 = this.getTileEntity(var1, var2);
      if (var3 != null) {
         IBlockState var4 = var3.getPistonState();
         Block var5 = var4.getBlock();
         if (var5 == this || var5.getMaterial() == Material.air) {
            return;
         }

         float var6 = var3.getProgress(0.0F);
         if (var3.isExtending()) {
            var6 = 1.0F - var6;
         }

         var5.setBlockBoundsBasedOnState(var1, var2);
         if (var5 == Blocks.piston || var5 == Blocks.sticky_piston) {
            var6 = 0.0F;
         }

         EnumFacing var7 = var3.getFacing();
         this.B = var5.getBlockBoundsMinX() - var7.getFrontOffsetX() * var6;
         this.C = var5.getBlockBoundsMinY() - var7.getFrontOffsetY() * var6;
         this.D = var5.getBlockBoundsMinZ() - var7.getFrontOffsetZ() * var6;
         this.E = var5.getBlockBoundsMaxX() - var7.getFrontOffsetX() * var6;
         this.F = var5.getBlockBoundsMaxY() - var7.getFrontOffsetY() * var6;
         this.G = var5.getBlockBoundsMaxZ() - var7.getFrontOffsetZ() * var6;
      }
   }

   public AxisAlignedBB getBoundingBox(World var1, BlockPos var2, IBlockState var3, float var4, EnumFacing var5) {
      if (var3.getBlock() != this && var3.getBlock().getMaterial() != Material.air) {
         AxisAlignedBB var6 = var3.getBlock().getCollisionBoundingBox(var1, var2, var3);
         if (var6 == null) {
            return null;
         } else {
            double var7 = var6.a;
            double var9 = var6.b;
            double var11 = var6.c;
            double var13 = var6.d;
            double var15 = var6.e;
            double var17 = var6.f;
            if (var5.getFrontOffsetX() < 0) {
               var7 -= var5.getFrontOffsetX() * var4;
            } else {
               var13 -= var5.getFrontOffsetX() * var4;
            }

            if (var5.getFrontOffsetY() < 0) {
               var9 -= var5.getFrontOffsetY() * var4;
            } else {
               var15 -= var5.getFrontOffsetY() * var4;
            }

            if (var5.getFrontOffsetZ() < 0) {
               var11 -= var5.getFrontOffsetZ() * var4;
            } else {
               var17 -= var5.getFrontOffsetZ() * var4;
            }

            return new AxisAlignedBB(var7, var9, var11, var13, var15, var17);
         }
      } else {
         return null;
      }
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return null;
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      if (!var1.D) {
         TileEntityPiston var6 = this.getTileEntity(var1, var2);
         if (var6 != null) {
            IBlockState var7 = var6.getPistonState();
            var7.getBlock().dropBlockAsItem(var1, var2, var7, 0);
         }
      }
   }

   @Override
   public void onBlockDestroyedByPlayer(World var1, BlockPos var2, IBlockState var3) {
      BlockPos var4 = var2.a(var3.getValue(FACING).getOpposite());
      IBlockState var5 = var1.getBlockState(var4);
      if (var5.getBlock() instanceof BlockPistonBase && var5.getValue(BlockPistonBase.EXTENDED)) {
         var1.setBlockToAir(var4);
      }
   }

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return null;
   }

   @Override
   public MovingObjectPosition collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4) {
      return null;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState()
         .withProperty(FACING, BlockPistonExtension.getFacing(var1))
         .withProperty(TYPE, (var1 & 8) > 0 ? BlockPistonExtension$EnumPistonType.STICKY : BlockPistonExtension$EnumPistonType.DEFAULT);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return null;
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      TileEntity var4 = var1.getTileEntity(var2);
      if (var4 instanceof TileEntityPiston) {
         ((TileEntityPiston)var4).clearPistonTileEntity();
      } else {
         super.breakBlock(var1, var2, var3);
      }
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (!var1.D && var1.getTileEntity(var2) == null) {
         var1.setBlockToAir(var2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.D) {
         var1.getTileEntity(var2);
      }
   }

   public static TileEntity newTileEntity(IBlockState var0, EnumFacing var1, boolean var2, boolean var3) {
      return new TileEntityPiston(var0, var1, var2, var3);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(FACING).getIndex();
      if (var1.getValue(TYPE) == BlockPistonExtension$EnumPistonType.STICKY) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, TYPE);
   }
}
