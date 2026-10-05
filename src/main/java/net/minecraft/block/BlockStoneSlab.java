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
import net.minecraft.world.World;

public abstract class BlockStoneSlab extends BlockSlab {
   public static PropertyBool SEAMLESS = PropertyBool.create("seamless");
   public static PropertyEnum<BlockStoneSlab.EnumType> VARIANT = PropertyEnum.create("variant", BlockStoneSlab.EnumType.class);

   public BlockStoneSlab() {
      super(Material.rock);
      IBlockState var1 = this.M.getBaseState();
      if (this.isDouble()) {
         var1 = var1.withProperty(SEAMLESS, false);
      } else {
         var1 = var1.withProperty(a, BlockSlab.EnumBlockHalf.BOTTOM);
      }

      this.setDefaultState(var1.withProperty(VARIANT, BlockStoneSlab.EnumType.STONE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).func_181074_c();
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
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      if (var1 != Item.getItemFromBlock(Blocks.double_stone_slab)) {
         for (BlockStoneSlab.EnumType var7 : BlockStoneSlab.EnumType.values()) {
            if (var7 != BlockStoneSlab.EnumType.WOOD) {
               var3.add(new ItemStack(var1, 1, var7.getMetadata()));
            }
         }
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      IBlockState var2 = this.getDefaultState().withProperty(VARIANT, BlockStoneSlab.EnumType.byMetadata(var1 & 7));
      if (this.isDouble()) {
         var2 = var2.withProperty(SEAMLESS, (var1 & 8) != 0);
      } else {
         var2 = var2.withProperty(a, (var1 & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
      }

      return var2;
   }

   @Override
   public String getUnlocalizedName(int var1) {
      return super.getUnlocalizedName() + "." + BlockStoneSlab.EnumType.byMetadata(var1).getUnlocalizedName();
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.stone_slab);
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Item.getItemFromBlock(Blocks.stone_slab);
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public Object getVariant(ItemStack var1) {
      return BlockStoneSlab.EnumType.byMetadata(var1.getMetadata() & 7);
   }

   @Override
   public IProperty<?> getVariantProperty() {
      return VARIANT;
   }

   @Override
   public BlockState createBlockState() {
      return this.isDouble() ? new BlockState(this, SEAMLESS, VARIANT) : new BlockState(this, a, VARIANT);
   }

   public static enum EnumType implements IStringSerializable {
      STONE(0, MapColor.stoneColor, "stone"),
      SAND(1, MapColor.sandColor, "sandstone", "sand"),
      WOOD(2, MapColor.woodColor, "wood_old", "wood"),
      COBBLESTONE(3, MapColor.stoneColor, "cobblestone", "cobble"),
      BRICK(4, MapColor.redColor, "brick"),
      SMOOTHBRICK(5, MapColor.stoneColor, "stone_brick", "smoothStoneBrick"),
      NETHERBRICK(6, MapColor.netherrackColor, "nether_brick", "netherBrick"),
      QUARTZ(7, MapColor.quartzColor, "quartz");

      public int meta;
      // $VF: synthetic field
      public static BlockStoneSlab.EnumType[] $VALUES = new BlockStoneSlab.EnumType[]{
         BlockStoneSlab.EnumType.STONE,
         SAND,
         BlockStoneSlab.EnumType.WOOD,
         BlockStoneSlab.EnumType.COBBLESTONE,
         BlockStoneSlab.EnumType.BRICK,
         BlockStoneSlab.EnumType.SMOOTHBRICK,
         BlockStoneSlab.EnumType.NETHERBRICK,
         QUARTZ
      };
      public String unlocalizedName;
      public MapColor field_181075_k;
      public static BlockStoneSlab.EnumType[] META_LOOKUP = new BlockStoneSlab.EnumType[values().length];
      public String name;

      static {
         for (BlockStoneSlab.EnumType var3 : values()) {
            META_LOOKUP[var3.getMetadata()] = var3;
         }
      }

      public static BlockStoneSlab.EnumType byMetadata(int var0) {
         if (var0 < 0 || var0 >= META_LOOKUP.length) {
            var0 = 0;
         }

         return META_LOOKUP[var0];
      }

      EnumType(int var3, MapColor var4, String var5, String var6) {
         this.meta = var3;
         this.field_181075_k = var4;
         this.name = var5;
         this.unlocalizedName = var6;
      }

      public int getMetadata() {
         return this.meta;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public MapColor func_181074_c() {
         return this.field_181075_k;
      }

      @Override
      public String toString() {
         return this.name;
      }

      EnumType(int var3, MapColor var4, String var5) {
         this(var3, var4, var5, var5);
      }
   }
}
