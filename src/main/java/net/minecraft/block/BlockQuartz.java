package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraft.block.BlockQuartz$EnumSwitch;

public class BlockQuartz extends Block {
   public static PropertyEnum<BlockQuartz.EnumType> VARIANT = PropertyEnum.create("variant", BlockQuartz.EnumType.class);

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      if (var7 == BlockQuartz.EnumType.LINES_Y.getMetadata()) {
         switch (BlockQuartz$EnumSwitch.recoveredField1826[var3.getAxis().ordinal()]) {
            case 1:
               return this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.LINES_Z);
            case 2:
               return this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.LINES_X);
            case 3:
            default:
               return this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.LINES_Y);
         }
      } else {
         return var7 == BlockQuartz.EnumType.CHISELED.getMetadata()
            ? this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.CHISELED)
            : this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.DEFAULT);
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockQuartz.EnumType.byMetadata(var1));
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return MapColor.quartzColor;
   }

   @Override
   public ItemStack createStackedBlock(IBlockState var1) {
      BlockQuartz.EnumType var2 = var1.getValue(VARIANT);
      return var2 != BlockQuartz.EnumType.LINES_X && var2 != BlockQuartz.EnumType.LINES_Z
         ? super.createStackedBlock(var1)
         : new ItemStack(Item.getItemFromBlock(this), 1, BlockQuartz.EnumType.LINES_Y.getMetadata());
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, BlockQuartz.EnumType.DEFAULT.getMetadata()));
      var3.add(new ItemStack(var1, 1, BlockQuartz.EnumType.CHISELED.getMetadata()));
      var3.add(new ItemStack(var1, 1, BlockQuartz.EnumType.LINES_Y.getMetadata()));
   }

   public BlockQuartz() {
      super(Material.rock);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockQuartz.EnumType.DEFAULT));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int damageDropped(IBlockState var1) {
      BlockQuartz.EnumType var2 = var1.getValue(VARIANT);
      return var2 != BlockQuartz.EnumType.LINES_X && var2 != BlockQuartz.EnumType.LINES_Z ? var2.getMetadata() : BlockQuartz.EnumType.LINES_Y.getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }

   public static enum EnumType implements IStringSerializable {
      DEFAULT(0, "default", "default"),
      CHISELED(1, "chiseled", "chiseled"),
      LINES_Y(2, "lines_y", "lines"),
      LINES_X(3, "lines_x", "lines"),
      LINES_Z(4, "lines_z", "lines");
      public String unlocalizedName;
      // $VF: synthetic field
      public static BlockQuartz.EnumType[] $VALUES = new BlockQuartz.EnumType[]{
         BlockQuartz.EnumType.DEFAULT, BlockQuartz.EnumType.CHISELED, LINES_Y, LINES_X, LINES_Z
      };
      public String field_176805_h;
      public static BlockQuartz.EnumType[] META_LOOKUP = new BlockQuartz.EnumType[values().length];
      public int meta;

      public static BlockQuartz.EnumType byMetadata(int var0) {
         if (var0 < 0 || var0 >= META_LOOKUP.length) {
            var0 = 0;
         }

         return META_LOOKUP[var0];
      }

      @Override
      public String toString() {
         return this.unlocalizedName;
      }

      EnumType(int var3, String var4, String var5) {
         this.meta = var3;
         this.field_176805_h = var4;
         this.unlocalizedName = var5;
      }

      public int getMetadata() {
         return this.meta;
      }

      @Override
      public String getName() {
         return this.field_176805_h;
      }

      static {
         for (BlockQuartz.EnumType var3 : values()) {
            META_LOOKUP[var3.getMetadata()] = var3;
         }
      }
   }
}
