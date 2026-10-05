package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureMap$3;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockOldLog extends BlockLog {
   public static PropertyEnum<BlockPlanks$EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks$EnumType.class, new BlockOldLog$1());
   public TextureMap$3 field_0000;

   @Override
   public ItemStack createStackedBlock(IBlockState var1) {
      return new ItemStack(Item.getItemFromBlock(this), 1, var1.getValue(VARIANT).getMetadata());
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(VARIANT).getMetadata();
      switch (BlockOldLog$2.field_180203_a[var1.getValue(a).ordinal()]) {
         case 1:
            var2 |= 4;
            break;
         case 2:
            var2 |= 8;
            break;
         case 3:
            var2 |= 12;
      }

      return var2;
   }

   public BlockOldLog() {
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPlanks$EnumType.OAK).withProperty(a, BlockLog$EnumAxis.Y));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT, a);
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      IBlockState var2 = this.getDefaultState().withProperty(VARIANT, BlockPlanks$EnumType.byMetadata((var1 & 3) % 4));
      switch (var1 & 12) {
         case 0:
            var2 = var2.withProperty(a, BlockLog$EnumAxis.Y);
            break;
         case 4:
            var2 = var2.withProperty(a, BlockLog$EnumAxis.X);
            break;
         case 8:
            var2 = var2.withProperty(a, BlockLog$EnumAxis.Z);
            break;
         default:
            var2 = var2.withProperty(a, BlockLog$EnumAxis.NONE);
      }

      return var2;
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.OAK.getMetadata()));
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.SPRUCE.getMetadata()));
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.BIRCH.getMetadata()));
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.JUNGLE.getMetadata()));
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      BlockPlanks$EnumType var2 = var1.getValue(VARIANT);
      switch (BlockOldLog$2.field_180203_a[var1.getValue(a).ordinal()]) {
         case 1:
         case 2:
         case 3:
         default:
            switch (BlockOldLog$2.field_181094_a[var2.ordinal()]) {
               case 1:
               default:
                  return BlockPlanks$EnumType.SPRUCE.getMapColor();
               case 2:
                  return BlockPlanks$EnumType.DARK_OAK.getMapColor();
               case 3:
                  return MapColor.quartzColor;
               case 4:
                  return BlockPlanks$EnumType.SPRUCE.getMapColor();
            }
         case 4:
            return var2.getMapColor();
      }
   }
}
