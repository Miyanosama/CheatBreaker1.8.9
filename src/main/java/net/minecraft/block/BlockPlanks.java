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

public class BlockPlanks extends Block {
   public static PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class);

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(var1));
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   public BlockPlanks() {
      super(Material.wood);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockPlanks.EnumType var7 : BlockPlanks.EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).getMapColor();
   }

   public static enum EnumType implements IStringSerializable {
      OAK(0, "oak", MapColor.woodColor),
      SPRUCE(1, "spruce", MapColor.obsidianColor),
      BIRCH(2, "birch", MapColor.sandColor),
      JUNGLE(3, "jungle", MapColor.dirtColor),
      ACACIA(4, "acacia", MapColor.adobeColor),
      DARK_OAK(5, "dark_oak", "big_oak", MapColor.brownColor);
      // $VF: synthetic field
      public static BlockPlanks.EnumType[] $VALUES = new BlockPlanks.EnumType[]{
         BlockPlanks.EnumType.OAK, BlockPlanks.EnumType.SPRUCE, BlockPlanks.EnumType.BIRCH, BlockPlanks.EnumType.JUNGLE, ACACIA, BlockPlanks.EnumType.DARK_OAK
      };

      public static BlockPlanks.EnumType[] META_LOOKUP = new BlockPlanks.EnumType[values().length];
      public String unlocalizedName;
      public String name;
      public MapColor mapColor;
      public int meta;

      @Override
      public String getName() {
         return this.name;
      }

      EnumType(int var3, String var4, MapColor var5) {
         this(var3, var4, var4, var5);
      }

      EnumType(int var3, String var4, String var5, MapColor var6) {
         this.meta = var3;
         this.name = var4;
         this.unlocalizedName = var5;
         this.mapColor = var6;
      }

      static {
         for (BlockPlanks.EnumType var3 : values()) {
            META_LOOKUP[var3.getMetadata()] = var3;
         }
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      public int getMetadata() {
         return this.meta;
      }

      public MapColor getMapColor() {
         return this.mapColor;
      }

      public static BlockPlanks.EnumType byMetadata(int var0) {
         if (var0 < 0 || var0 >= META_LOOKUP.length) {
            var0 = 0;
         }

         return META_LOOKUP[var0];
      }

      @Override
      public String toString() {
         return this.name;
      }
   }
}
