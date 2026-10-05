package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class BlockSand extends BlockFalling {
   public static PropertyEnum<BlockSand.EnumType> VARIANT = PropertyEnum.create("variant", BlockSand.EnumType.class);

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
      return this.getDefaultState().withProperty(VARIANT, BlockSand.EnumType.byMetadata(var1));
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockSand.EnumType var7 : BlockSand.EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   public BlockSand() {
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockSand.EnumType.SAND));
   }

   public static enum EnumType implements IStringSerializable {
      SAND(0, "sand", "default", MapColor.sandColor),
      RED_SAND(1, "red_sand", "red", MapColor.adobeColor);

      public String unlocalizedName;
      public int meta;
      // $VF: synthetic field
      public static BlockSand.EnumType[] $VALUES = new BlockSand.EnumType[]{BlockSand.EnumType.SAND, BlockSand.EnumType.RED_SAND};
      public static BlockSand.EnumType[] META_LOOKUP = new BlockSand.EnumType[values().length];
      public MapColor mapColor;
      public String name;

      @Override
      public String getName() {
         return this.name;
      }

      public MapColor getMapColor() {
         return this.mapColor;
      }

      public static BlockSand.EnumType byMetadata(int var0) {
         if (var0 < 0 || var0 >= META_LOOKUP.length) {
            var0 = 0;
         }

         return META_LOOKUP[var0];
      }

      @Override
      public String toString() {
         return this.name;
      }

      EnumType(int var3, String var4, String var5, MapColor var6) {
         this.meta = var3;
         this.name = var4;
         this.mapColor = var6;
         this.unlocalizedName = var5;
      }

      static {
         for (BlockSand.EnumType var3 : values()) {
            META_LOOKUP[var3.getMetadata()] = var3;
         }
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      public int getMetadata() {
         return this.meta;
      }
   }
}
