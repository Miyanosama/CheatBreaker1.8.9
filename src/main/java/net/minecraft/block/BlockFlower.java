package net.minecraft.block;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public abstract class BlockFlower extends BlockBush {
   public PropertyEnum<BlockFlower.EnumFlowerType> type;

   public BlockFlower() {
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(
               this.getTypeProperty(),
               this.getBlockType() == BlockFlower.EnumFlowerColor.RED ? BlockFlower.EnumFlowerType.POPPY : BlockFlower.EnumFlowerType.DANDELION
            )
      );
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(this.getTypeProperty(), BlockFlower.EnumFlowerType.getType(this.getBlockType(), var1));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, this.getTypeProperty());
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(this.getTypeProperty()).getMeta();
   }

   public abstract BlockFlower.EnumFlowerColor getBlockType();

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockFlower.EnumFlowerType var7 : BlockFlower.EnumFlowerType.getTypes(this.getBlockType())) {
         var3.add(new ItemStack(var1, 1, var7.getMeta()));
      }
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(this.getTypeProperty()).getMeta();
   }

   @Override
   public Block.EnumOffsetType getOffsetType() {
      return Block.EnumOffsetType.XZ;
   }

   public IProperty<BlockFlower.EnumFlowerType> getTypeProperty() {
      if (this.type == null) {
         this.type = PropertyEnum.create("type", BlockFlower.EnumFlowerType.class, new Predicate<BlockFlower.EnumFlowerType>() {
            public boolean apply(BlockFlower.EnumFlowerType var1) {
               return var1.getBlockType() == BlockFlower.this.getBlockType();
            }
         });
      }

      return this.type;
   }

   public static enum EnumFlowerColor {
      YELLOW,
      RED;

      public BlockFlower getBlock() {
         return this == YELLOW ? Blocks.yellow_flower : Blocks.red_flower;
      }
   }

   public static enum EnumFlowerType implements IStringSerializable {
      DANDELION(BlockFlower.EnumFlowerColor.YELLOW, 0, "dandelion"),
      POPPY(BlockFlower.EnumFlowerColor.RED, 0, "poppy"),
      BLUE_ORCHID(BlockFlower.EnumFlowerColor.RED, 1, "blue_orchid", "blueOrchid"),
      ALLIUM(BlockFlower.EnumFlowerColor.RED, 2, "allium"),
      HOUSTONIA(BlockFlower.EnumFlowerColor.RED, 3, "houstonia"),
      RED_TULIP(BlockFlower.EnumFlowerColor.RED, 4, "red_tulip", "tulipRed"),
      ORANGE_TULIP(BlockFlower.EnumFlowerColor.RED, 5, "orange_tulip", "tulipOrange"),
      WHITE_TULIP(BlockFlower.EnumFlowerColor.RED, 6, "white_tulip", "tulipWhite"),
      PINK_TULIP(BlockFlower.EnumFlowerColor.RED, 7, "pink_tulip", "tulipPink"),
      OXEYE_DAISY(BlockFlower.EnumFlowerColor.RED, 8, "oxeye_daisy", "oxeyeDaisy");

      public String name;
      public String unlocalizedName;
      public BlockFlower.EnumFlowerColor blockType;
      public static BlockFlower.EnumFlowerType[][] TYPES_FOR_BLOCK = new BlockFlower.EnumFlowerType[BlockFlower.EnumFlowerColor.values().length][];
      public int meta;

      public int getMeta() {
         return this.meta;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public static BlockFlower.EnumFlowerType[] getTypes(BlockFlower.EnumFlowerColor var0) {
         return TYPES_FOR_BLOCK[var0.ordinal()];
      }

      EnumFlowerType(BlockFlower.EnumFlowerColor var3, int var4, String var5) {
         this(var3, var4, var5, var5);
      }

      static {
         for (final BlockFlower.EnumFlowerColor var3 : BlockFlower.EnumFlowerColor.values()) {
            Collection var4 = Collections2.filter(Lists.newArrayList(values()), new Predicate<BlockFlower.EnumFlowerType>() {
               public boolean apply(BlockFlower.EnumFlowerType var1) {
                  return var1.getBlockType() == var3;
               }
            });
            TYPES_FOR_BLOCK[var3.ordinal()] = (net.minecraft.block.BlockFlower.EnumFlowerType[])var4.toArray(new BlockFlower.EnumFlowerType[var4.size()]);
         }
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      @Override
      public String toString() {
         return this.name;
      }

      public BlockFlower.EnumFlowerColor getBlockType() {
         return this.blockType;
      }

      public static BlockFlower.EnumFlowerType getType(BlockFlower.EnumFlowerColor var0, int var1) {
         BlockFlower.EnumFlowerType[] var2 = TYPES_FOR_BLOCK[var0.ordinal()];
         if (var1 < 0 || var1 >= var2.length) {
            var1 = 0;
         }

         return var2[var1];
      }

      EnumFlowerType(BlockFlower.EnumFlowerColor var3, int var4, String var5, String var6) {
         this.blockType = var3;
         this.meta = var4;
         this.name = var5;
         this.unlocalizedName = var6;
      }
   }
}
