package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer$3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapter;
import recovered.unidentified.UnidentifiedClass4867;

public class BlockFarmland extends Block {
   public EffectRenderer$3 field_0002;
   public UnidentifiedClass4867 field_0003;
   public static PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 7);
   public ModelAdapter field_0001;

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      switch (BlockFarmland$1.field_181625_a[var3.ordinal()]) {
         case 1:
            return true;
         case 2:
         case 3:
         case 4:
         case 5:
            Block var4 = var1.getBlockState(var2).getBlock();
            return !var4.isOpaqueCube() && var4 != Blocks.farmland;
         default:
            return super.shouldSideBeRendered(var1, var2, var3);
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(MOISTURE, var1 & 7);
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      super.onNeighborBlockChange(var1, var2, var3, var4);
      if (var1.getBlockState(var2.up()).getBlock().getMaterial().isSolid()) {
         var1.setBlockState(var2, Blocks.dirt.getDefaultState());
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Item.getItemFromBlock(Blocks.dirt);
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Blocks.dirt.getItemDropped(Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt$DirtType.DIRT), var2, var3);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(MOISTURE);
   }

   public boolean hasWater(World var1, BlockPos var2) {
      for (BlockPos$MutableBlockPos var4 : BlockPos.getAllInBoxMutable(var2.add(-4, 0, -4), var2.add(4, 1, 4))) {
         if (var1.getBlockState(var4).getBlock().getMaterial() == Material.water) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      int var5 = var3.getValue(MOISTURE);
      if (!this.hasWater(var1, var2) && !var1.isRainingAt(var2.up())) {
         if (var5 > 0) {
            var1.a(var2, var3.withProperty(MOISTURE, var5 - 1), 2);
         } else if (!this.hasCrops(var1, var2)) {
            var1.setBlockState(var2, Blocks.dirt.getDefaultState());
         }
      } else if (var5 < 7) {
         var1.a(var2, var3.withProperty(MOISTURE, 7), 2);
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return new AxisAlignedBB(var2.getX(), var2.getY(), var2.getZ(), var2.getX() + 1, var2.getY() + 1, var2.getZ() + 1);
   }

   public BlockFarmland() {
      super(Material.ground);
      this.setDefaultState(this.M.getBaseState().withProperty(MOISTURE, 0));
      this.setTickRandomly(true);
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
      this.setLightOpacity(255);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, MOISTURE);
   }

   public boolean hasCrops(World var1, BlockPos var2) {
      Block var3 = var1.getBlockState(var2.up()).getBlock();
      return var3 instanceof BlockCrops || var3 instanceof BlockStem;
   }

   @Override
   public void onFallenUpon(World var1, BlockPos var2, Entity var3, float var4) {
      if (var3 instanceof EntityLivingBase) {
         if (!var1.D && var1.s.nextFloat() < var4 - 0.5F) {
            if (!(var3 instanceof EntityPlayer) && !var1.Q().getBoolean("mobGriefing")) {
               return;
            }

            var1.setBlockState(var2, Blocks.dirt.getDefaultState());
         }

         super.onFallenUpon(var1, var2, var3, var4);
      }
   }
}
