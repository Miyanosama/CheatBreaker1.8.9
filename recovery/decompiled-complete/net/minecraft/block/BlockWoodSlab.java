package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class BlockWoodSlab extends BlockSlab {
   public static PropertyEnum<BlockPlanks$EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks$EnumType.class);

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.wooden_slab);
   }

   public BlockWoodSlab() {
      super(Material.wood);
      IBlockState var1 = this.M.getBaseState();
      if (!this.isDouble()) {
         var1 = var1.withProperty(a, BlockSlab$EnumBlockHalf.BOTTOM);
      }

      this.setDefaultState(var1.withProperty(VARIANT, BlockPlanks$EnumType.OAK));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return this.isDouble() ? new BlockState(this, VARIANT) : new BlockState(this, a, VARIANT);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      IBlockState var2 = this.getDefaultState().withProperty(VARIANT, BlockPlanks$EnumType.byMetadata(var1 & 7));
      if (!this.isDouble()) {
         var2 = var2.withProperty(a, (var1 & 8) == 0 ? BlockSlab$EnumBlockHalf.BOTTOM : BlockSlab$EnumBlockHalf.TOP);
      }

      return var2;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Item.getItemFromBlock(Blocks.wooden_slab);
   }

   @Override
   public String getUnlocalizedName(int var1) {
      return super.getUnlocalizedName() + "." + BlockPlanks$EnumType.byMetadata(var1).getUnlocalizedName();
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).getMapColor();
   }

   @Override
   public IProperty<?> getVariantProperty() {
      return VARIANT;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(VARIANT).getMetadata();
      if (!this.isDouble() && var1.getValue(a) == BlockSlab$EnumBlockHalf.TOP) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      if (var1 != Item.getItemFromBlock(Blocks.double_wooden_slab)) {
         for (BlockPlanks$EnumType var7 : BlockPlanks$EnumType.values()) {
            var3.add(new ItemStack(var1, 1, var7.getMetadata()));
         }
      }
   }

   @Override
   public Object getVariant(ItemStack var1) {
      return BlockPlanks$EnumType.byMetadata(var1.getMetadata() & 7);
   }
}
