package net.minecraft.block;

import io.netty.channel.rxtx.RxtxChannelConfig$Paritybit;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import recovered.unidentified.UnidentifiedClass4880;

public class BlockSandStone extends Block {
   public static PropertyEnum<BlockSandStone$EnumType> TYPE = PropertyEnum.create("type", BlockSandStone$EnumType.class);
   public RxtxChannelConfig$Paritybit field_0003;
   public UnidentifiedClass4880 field_0000;
   public BlockDirt field_0001;

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockSandStone$EnumType var7 : BlockSandStone$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(TYPE).getMetadata();
   }

   public BlockSandStone() {
      super(Material.rock);
      this.setDefaultState(this.M.getBaseState().withProperty(TYPE, BlockSandStone$EnumType.DEFAULT));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return MapColor.sandColor;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(TYPE, BlockSandStone$EnumType.byMetadata(var1));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, TYPE);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(TYPE).getMetadata();
   }
}
