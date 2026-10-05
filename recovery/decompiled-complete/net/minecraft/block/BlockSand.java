package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockSand extends BlockFalling {
   public DefaultStateMapper field_0000;
   public static PropertyEnum<BlockSand$EnumType> VARIANT = PropertyEnum.create("variant", BlockSand$EnumType.class);

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).getMapColor();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockSand$EnumType.byMetadata(var1));
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockSand$EnumType var7 : BlockSand$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   public BlockSand() {
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockSand$EnumType.SAND));
   }
}
