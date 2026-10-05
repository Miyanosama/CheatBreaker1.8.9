package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockSlab extends Block {
   public static PropertyEnum<BlockSlab.EnumBlockHalf> a = PropertyEnum.create("half", BlockSlab.EnumBlockHalf.class);

   public abstract boolean isDouble();

   @Override
   public int quantityDropped(Random var1) {
      return this.isDouble() ? 2 : 1;
   }

   public BlockSlab(Material var1) {
      super(var1);
      if (this.isDouble()) {
         this.fullBlock = true;
      } else {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }

      this.setLightOpacity(255);
   }

   public abstract String getUnlocalizedName(int var1);

   public abstract IProperty<?> getVariantProperty();

   public abstract Object getVariant(ItemStack var1);

   @Override
   public int getDamageValue(World var1, BlockPos var2) {
      return super.getDamageValue(var1, var2) & 7;
   }

   @Override
   public boolean canSilkHarvest() {
      return false;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      if (this.isDouble()) {
         return super.shouldSideBeRendered(var1, var2, var3);
      } else if (var3 != EnumFacing.UP && var3 != EnumFacing.DOWN && !super.shouldSideBeRendered(var1, var2, var3)) {
         return false;
      } else {
         BlockPos var4 = var2.a(var3.getOpposite());
         IBlockState var5 = var1.getBlockState(var2);
         IBlockState var6 = var1.getBlockState(var4);
         boolean var7 = isSlab(var5.getBlock()) && var5.getValue(a) == BlockSlab.EnumBlockHalf.TOP;
         boolean var8 = isSlab(var6.getBlock()) && var6.getValue(a) == BlockSlab.EnumBlockHalf.TOP;
         return var8
            ? (
               var3 == EnumFacing.DOWN
                  ? true
                  : (var3 == EnumFacing.UP && super.shouldSideBeRendered(var1, var2, var3) ? true : !isSlab(var5.getBlock()) || !var7)
            )
            : (
               var3 == EnumFacing.UP
                  ? true
                  : (var3 == EnumFacing.DOWN && super.shouldSideBeRendered(var1, var2, var3) ? true : !isSlab(var5.getBlock()) || var7)
            );
      }
   }

   @Override
   public void addCollisionBoxesToList(World var1, BlockPos var2, IBlockState var3, AxisAlignedBB var4, List<AxisAlignedBB> var5, Entity var6) {
      this.setBlockBoundsBasedOnState(var1, var2);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      IBlockState var9 = super.onBlockPlaced(var1, var2, var3, var4, var5, var6, var7, var8).withProperty(a, BlockSlab.EnumBlockHalf.BOTTOM);
      return this.isDouble()
         ? var9
         : (var3 == EnumFacing.DOWN || var3 != EnumFacing.UP && !(var5 <= 0.5) ? var9.withProperty(a, BlockSlab.EnumBlockHalf.TOP) : var9);
   }

   public static boolean isSlab(Block var0) {
      return var0 == Blocks.stone_slab || var0 == Blocks.wooden_slab || var0 == Blocks.stone_slab2;
   }

   @Override
   public boolean isFullCube() {
      return this.isDouble();
   }

   @Override
   public boolean isOpaqueCube() {
      return this.isDouble();
   }

   @Override
   public void setBlockBoundsForItemRender() {
      if (this.isDouble()) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      if (this.isDouble()) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         IBlockState var3 = var1.getBlockState(var2);
         if (var3.getBlock() == this) {
            if (var3.getValue(a) == BlockSlab.EnumBlockHalf.TOP) {
               this.a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
         }
      }
   }

   public static enum EnumBlockHalf implements IStringSerializable {
      TOP("top"),
      BOTTOM("bottom");
      // $VF: synthetic field
      public static BlockSlab.EnumBlockHalf[] $VALUES = new BlockSlab.EnumBlockHalf[]{BlockSlab.EnumBlockHalf.TOP, BlockSlab.EnumBlockHalf.BOTTOM};
      public String name;

      @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }

      EnumBlockHalf(String var3) {
         this.name = var3;
      }
   }
}
