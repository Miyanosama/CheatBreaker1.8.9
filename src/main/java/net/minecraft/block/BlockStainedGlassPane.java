package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockStainedGlassPane extends BlockPane {
   public static PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(COLOR).getMetadata();
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(COLOR).getMetadata();
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      if (!var1.D) {
         BlockBeacon.updateColorAsync(var1, var2);
      }
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (int var4 = 0; var4 < EnumDyeColor.values().length; var4++) {
         var3.add(new ItemStack(var1, 1, var4));
      }
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      if (!var1.D) {
         BlockBeacon.updateColorAsync(var1, var2);
      }
   }

   public BlockStainedGlassPane() {
      super(Material.glass, false);
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(b, false)
            .withProperty(N, false)
            .withProperty(O, false)
            .withProperty(P, false)
            .withProperty(COLOR, EnumDyeColor.WHITE)
      );
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, b, N, P, O, COLOR);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(COLOR).getMapColor();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(var1));
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }
}
