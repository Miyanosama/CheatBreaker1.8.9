package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class BlockSandStone extends Block {
   public static PropertyEnum<BlockSandStone.EnumType> TYPE = PropertyEnum.create("type", BlockSandStone.EnumType.class);

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockSandStone.EnumType var7 : BlockSandStone.EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(TYPE).getMetadata();
   }

   public BlockSandStone() {
      super(Material.rock);
      this.setDefaultState(this.M.getBaseState().withProperty(TYPE, BlockSandStone.EnumType.DEFAULT));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return MapColor.sandColor;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(TYPE, BlockSandStone.EnumType.byMetadata(var1));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, TYPE);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(TYPE).getMetadata();
   }

   public static enum EnumType implements IStringSerializable {
      DEFAULT(0, "sandstone", "default"),
      CHISELED(1, "chiseled_sandstone", "chiseled"),
      SMOOTH(2, "smooth_sandstone", "smooth");
      // $VF: synthetic field
      public static BlockSandStone.EnumType[] $VALUES = new BlockSandStone.EnumType[]{
         BlockSandStone.EnumType.DEFAULT, BlockSandStone.EnumType.CHISELED, BlockSandStone.EnumType.SMOOTH
      };
      public int metadata;

      public static BlockSandStone.EnumType[] META_LOOKUP = new BlockSandStone.EnumType[values().length];
      public String name;
      public String unlocalizedName;

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      public static BlockSandStone.EnumType byMetadata(int var0) {
         if (var0 < 0 || var0 >= META_LOOKUP.length) {
            var0 = 0;
         }

         return META_LOOKUP[var0];
      }

      @Override
      public String getName() {
         return this.name;
      }

      public int getMetadata() {
         return this.metadata;
      }

      EnumType(int var3, String var4, String var5) {
         this.metadata = var3;
         this.name = var4;
         this.unlocalizedName = var5;
      }

      @Override
      public String toString() {
         return this.name;
      }

      static {
         for (BlockSandStone.EnumType var3 : values()) {
            META_LOOKUP[var3.getMetadata()] = var3;
         }
      }
   }
}
