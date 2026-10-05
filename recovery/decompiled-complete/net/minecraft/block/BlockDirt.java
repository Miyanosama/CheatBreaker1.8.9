package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDirt extends Block {
   public BlockSilverfish$EnumType$6 field_0001;
   public static PropertyEnum<BlockDirt$DirtType> VARIANT = PropertyEnum.create("variant", BlockDirt$DirtType.class);
   public static PropertyBool SNOWY = PropertyBool.create("snowy");

   @Override
   public int damageDropped(IBlockState var1) {
      BlockDirt$DirtType var2 = var1.getValue(VARIANT);
      if (var2 == BlockDirt$DirtType.PODZOL) {
         var2 = BlockDirt$DirtType.DIRT;
      }

      return var2.getMetadata();
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).func_181066_d();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT, SNOWY);
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      if (var1.getValue(VARIANT) == BlockDirt$DirtType.PODZOL) {
         Block var4 = var2.getBlockState(var3.up()).getBlock();
         var1 = var1.withProperty(SNOWY, var4 == Blocks.snow || var4 == Blocks.snow_layer);
      }

      return var1;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   public BlockDirt() {
      super(Material.ground);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockDirt$DirtType.DIRT).withProperty(SNOWY, false));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(this, 1, BlockDirt$DirtType.DIRT.getMetadata()));
      var3.add(new ItemStack(this, 1, BlockDirt$DirtType.COARSE_DIRT.getMetadata()));
      var3.add(new ItemStack(this, 1, BlockDirt$DirtType.PODZOL.getMetadata()));
   }

   @Override
   public int getDamageValue(World var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      return var3.getBlock() != this ? 0 : var3.getValue(VARIANT).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockDirt$DirtType.byMetadata(var1));
   }
}
