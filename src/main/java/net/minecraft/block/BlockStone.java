package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.StatCollector;

public class BlockStone extends Block {
   public static PropertyEnum<BlockStone.EnumType> VARIANT = PropertyEnum.create("variant", BlockStone.EnumType.class);

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).func_181072_c();
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return var1.getValue(VARIANT) == BlockStone.EnumType.STONE ? Item.getItemFromBlock(Blocks.cobblestone) : Item.getItemFromBlock(Blocks.stone);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.byMetadata(var1));
   }

   public BlockStone() {
      super(Material.rock);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockStone.EnumType.STONE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockStone.EnumType var7 : BlockStone.EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockStone.EnumType.STONE.getUnlocalizedName() + ".name");
   }

   public static enum EnumType implements IStringSerializable {
      STONE(0, MapColor.stoneColor, "stone"),
      GRANITE(1, MapColor.dirtColor, "granite"),
      GRANITE_SMOOTH(2, MapColor.dirtColor, "smooth_granite", "graniteSmooth"),
      DIORITE(3, MapColor.quartzColor, "diorite"),
      DIORITE_SMOOTH(4, MapColor.quartzColor, "smooth_diorite", "dioriteSmooth"),
      ANDESITE(5, MapColor.stoneColor, "andesite"),
      ANDESITE_SMOOTH(6, MapColor.stoneColor, "smooth_andesite", "andesiteSmooth");

      public String name;
      public MapColor field_181073_l;
      // $VF: synthetic field
      public static BlockStone.EnumType[] $VALUES = new BlockStone.EnumType[]{
         BlockStone.EnumType.STONE, BlockStone.EnumType.GRANITE, GRANITE_SMOOTH, BlockStone.EnumType.DIORITE, DIORITE_SMOOTH, ANDESITE, ANDESITE_SMOOTH
      };
      public String unlocalizedName;
      public static BlockStone.EnumType[] META_LOOKUP = new BlockStone.EnumType[values().length];
      public int meta;

      public static BlockStone.EnumType byMetadata(int var0) {
         if (var0 < 0 || var0 >= META_LOOKUP.length) {
            var0 = 0;
         }

         return META_LOOKUP[var0];
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      static {
         for (BlockStone.EnumType var3 : values()) {
            META_LOOKUP[var3.getMetadata()] = var3;
         }
      }

      EnumType(int var3, MapColor var4, String var5, String var6) {
         this.meta = var3;
         this.name = var5;
         this.unlocalizedName = var6;
         this.field_181073_l = var4;
      }

      EnumType(int var3, MapColor var4, String var5) {
         this(var3, var4, var5, var5);
      }

      public MapColor func_181072_c() {
         return this.field_181073_l;
      }

      @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public int getMetadata() {
         return this.meta;
      }
   }
}
