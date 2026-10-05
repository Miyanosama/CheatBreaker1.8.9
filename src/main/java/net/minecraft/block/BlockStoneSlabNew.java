package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class BlockStoneSlabNew extends BlockSlab {
   public static PropertyBool SEAMLESS = PropertyBool.create("seamless");
   public static PropertyEnum<BlockStoneSlabNew.EnumType> VARIANT = PropertyEnum.create("variant", BlockStoneSlabNew.EnumType.class);

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.stone_slab2);
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + ".red_sandstone.name");
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).func_181068_c();
   }

   @Override
   public BlockState createBlockState() {
      return this.isDouble() ? new BlockState(this, SEAMLESS, VARIANT) : new BlockState(this, a, VARIANT);
   }

   @Override
   public Object getVariant(ItemStack var1) {
      return BlockStoneSlabNew.EnumType.byMetadata(var1.getMetadata() & 7);
   }

   public BlockStoneSlabNew() {
      super(Material.rock);
      IBlockState var1 = this.M.getBaseState();
      if (this.isDouble()) {
         var1 = var1.withProperty(SEAMLESS, false);
      } else {
         var1 = var1.withProperty(a, BlockSlab.EnumBlockHalf.BOTTOM);
      }

      this.setDefaultState(var1.withProperty(VARIANT, BlockStoneSlabNew.EnumType.RED_SANDSTONE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public String getUnlocalizedName(int var1) {
      return super.getUnlocalizedName() + "." + BlockStoneSlabNew.EnumType.byMetadata(var1).getUnlocalizedName();
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      if (var1 != Item.getItemFromBlock(Blocks.double_stone_slab2)) {
         for (BlockStoneSlabNew.EnumType var7 : BlockStoneSlabNew.EnumType.values()) {
            var3.add(new ItemStack(var1, 1, var7.getMetadata()));
         }
      }
   }

   @Override
   public IProperty<?> getVariantProperty() {
      return VARIANT;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      IBlockState var2 = this.getDefaultState().withProperty(VARIANT, BlockStoneSlabNew.EnumType.byMetadata(var1 & 7));
      if (this.isDouble()) {
         var2 = var2.withProperty(SEAMLESS, (var1 & 8) != 0);
      } else {
         var2 = var2.withProperty(a, (var1 & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
      }

      return var2;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(VARIANT).getMetadata();
      if (this.isDouble()) {
         if (var1.getValue(SEAMLESS)) {
            var2 |= 8;
         }
      } else if (var1.getValue(a) == BlockSlab.EnumBlockHalf.TOP) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Item.getItemFromBlock(Blocks.stone_slab2);
   }

   public static enum EnumType implements IStringSerializable {
      RED_SANDSTONE(0, "red_sandstone", BlockSand.EnumType.RED_SAND.getMapColor());
      public MapColor field_181069_e;
      // $VF: synthetic field
      public static BlockStoneSlabNew.EnumType[] $VALUES = new BlockStoneSlabNew.EnumType[]{BlockStoneSlabNew.EnumType.RED_SANDSTONE};
      public String name;
      public int meta;
      public static BlockStoneSlabNew.EnumType[] META_LOOKUP = new BlockStoneSlabNew.EnumType[values().length];

      EnumType(int var3, String var4, MapColor var5) {
         this.meta = var3;
         this.name = var4;
         this.field_181069_e = var5;
      }

      @Override
      public String toString() {
         return this.name;
      }

      public String getUnlocalizedName() {
         return this.name;
      }

      public static BlockStoneSlabNew.EnumType byMetadata(int var0) {
         if (var0 < 0 || var0 >= META_LOOKUP.length) {
            var0 = 0;
         }

         return META_LOOKUP[var0];
      }

      public MapColor func_181068_c() {
         return this.field_181069_e;
      }

      static {
         for (BlockStoneSlabNew.EnumType var3 : values()) {
            META_LOOKUP[var3.getMetadata()] = var3;
         }
      }

      public int getMetadata() {
         return this.meta;
      }

      @Override
      public String getName() {
         return this.name;
      }
   }
}
