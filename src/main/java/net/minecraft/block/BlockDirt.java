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
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDirt extends Block {
   public static PropertyEnum<BlockDirt.DirtType> VARIANT = PropertyEnum.create("variant", BlockDirt.DirtType.class);
   public static PropertyBool SNOWY = PropertyBool.create("snowy");

   @Override
   public int damageDropped(IBlockState var1) {
      BlockDirt.DirtType var2 = var1.getValue(VARIANT);
      if (var2 == BlockDirt.DirtType.PODZOL) {
         var2 = BlockDirt.DirtType.DIRT;
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
      if (var1.getValue(VARIANT) == BlockDirt.DirtType.PODZOL) {
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
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockDirt.DirtType.DIRT).withProperty(SNOWY, false));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(this, 1, BlockDirt.DirtType.DIRT.getMetadata()));
      var3.add(new ItemStack(this, 1, BlockDirt.DirtType.COARSE_DIRT.getMetadata()));
      var3.add(new ItemStack(this, 1, BlockDirt.DirtType.PODZOL.getMetadata()));
   }

   @Override
   public int getDamageValue(World var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      return var3.getBlock() != this ? 0 : var3.getValue(VARIANT).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockDirt.DirtType.byMetadata(var1));
   }

   public static enum DirtType implements IStringSerializable {
      DIRT(0, "dirt", "default", MapColor.dirtColor),
      COARSE_DIRT(1, "coarse_dirt", "coarse", MapColor.dirtColor),
      PODZOL(2, "podzol", MapColor.obsidianColor);

      public String unlocalizedName;
      public String name;
      // $VF: synthetic field
      public static BlockDirt.DirtType[] $VALUES = new BlockDirt.DirtType[]{BlockDirt.DirtType.DIRT, BlockDirt.DirtType.COARSE_DIRT, BlockDirt.DirtType.PODZOL};
      public int metadata;
      public static BlockDirt.DirtType[] METADATA_LOOKUP = new BlockDirt.DirtType[values().length];
      public MapColor field_181067_h;

      public MapColor func_181066_d() {
         return this.field_181067_h;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public int getMetadata() {
         return this.metadata;
      }

      public static BlockDirt.DirtType byMetadata(int var0) {
         if (var0 < 0 || var0 >= METADATA_LOOKUP.length) {
            var0 = 0;
         }

         return METADATA_LOOKUP[var0];
      }

      static {
         for (BlockDirt.DirtType var3 : values()) {
            METADATA_LOOKUP[var3.getMetadata()] = var3;
         }
      }

      DirtType(int var3, String var4, MapColor var5) {
         this(var3, var4, var4, var5);
      }

      DirtType(int var3, String var4, String var5, MapColor var6) {
         this.metadata = var3;
         this.name = var4;
         this.unlocalizedName = var5;
         this.field_181067_h = var6;
      }

      @Override
      public String toString() {
         return this.name;
      }
   }
}
