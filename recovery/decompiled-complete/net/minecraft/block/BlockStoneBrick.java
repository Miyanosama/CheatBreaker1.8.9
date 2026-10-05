package net.minecraft.block;

import com.cheatbreaker.client.util.dash.CBDashManager;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockStoneBrick extends Block {
   public static int CHISELED_META = BlockStoneBrick$EnumType.CHISELED.getMetadata();
   public static int DEFAULT_META = BlockStoneBrick$EnumType.DEFAULT.getMetadata();
   public static int CRACKED_META = BlockStoneBrick$EnumType.CRACKED.getMetadata();
   public static PropertyEnum<BlockStoneBrick$EnumType> VARIANT = PropertyEnum.create("variant", BlockStoneBrick$EnumType.class);
   public static int MOSSY_META = BlockStoneBrick$EnumType.MOSSY.getMetadata();
   public CBDashManager field_0004;

   public BlockStoneBrick() {
      super(Material.rock);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockStoneBrick$EnumType.DEFAULT));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockStoneBrick$EnumType var7 : BlockStoneBrick$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockStoneBrick$EnumType.byMetadata(var1));
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }
}
