package net.minecraft.block;

import io.netty.buffer.Unpooled;
import java.util.List;
import java.util.Random;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.log4j.chainsaw.ControlPanel$5;

public class BlockSapling extends BlockBush implements IGrowable {
   public ControlPanel$5 field_0002;
   public static PropertyEnum<BlockPlanks$EnumType> TYPE = PropertyEnum.create("type", BlockPlanks$EnumType.class);
   public Unpooled field_0001;
   public static PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

   public boolean func_181624_a(World var1, BlockPos var2, int var3, int var4, BlockPlanks$EnumType var5) {
      return this.isTypeAt(var1, var2.add(var3, 0, var4), var5)
         && this.isTypeAt(var1, var2.add(var3 + 1, 0, var4), var5)
         && this.isTypeAt(var1, var2.add(var3, 0, var4 + 1), var5)
         && this.isTypeAt(var1, var2.add(var3 + 1, 0, var4 + 1), var5);
   }

   @Override
   public boolean canUseBonemeal(World var1, Random var2, BlockPos var3, IBlockState var4) {
      return var1.s.nextFloat() < 0.45;
   }

   public void generateTree(World var1, BlockPos var2, IBlockState var3, Random var4) {
      Object var5 = var4.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
      int var6 = 0;
      int var7 = 0;
      boolean var8 = false;
      switch (BlockSapling$1.field_0001[var3.getValue(TYPE).ordinal()]) {
         case 1:
            label68:
            for (var6 = 0; var6 >= -1; var6--) {
               for (var7 = 0; var7 >= -1; var7--) {
                  if (this.func_181624_a(var1, var2, var6, var7, BlockPlanks$EnumType.SPRUCE)) {
                     var5 = new WorldGenMegaPineTree(false, var4.nextBoolean());
                     var8 = true;
                     break label68;
                  }
               }
            }

            if (!var8) {
               var7 = 0;
               var6 = 0;
               var5 = new WorldGenTaiga2(true);
            }
            break;
         case 2:
            var5 = new WorldGenForest(true, false);
            break;
         case 3:
            IBlockState var9 = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks$EnumType.JUNGLE);
            IBlockState var10 = Blocks.leaves
               .getDefaultState()
               .withProperty(BlockOldLeaf.VARIANT, BlockPlanks$EnumType.JUNGLE)
               .withProperty(BlockLeaves.b, false);

            label82:
            for (var6 = 0; var6 >= -1; var6--) {
               for (var7 = 0; var7 >= -1; var7--) {
                  if (this.func_181624_a(var1, var2, var6, var7, BlockPlanks$EnumType.JUNGLE)) {
                     var5 = new WorldGenMegaJungle(true, 10, 20, var9, var10);
                     var8 = true;
                     break label82;
                  }
               }
            }

            if (!var8) {
               var7 = 0;
               var6 = 0;
               var5 = new WorldGenTrees(true, 4 + var4.nextInt(7), var9, var10, false);
            }
            break;
         case 4:
            var5 = new WorldGenSavannaTree(true);
            break;
         case 5:
            label96:
            for (var6 = 0; var6 >= -1; var6--) {
               for (var7 = 0; var7 >= -1; var7--) {
                  if (this.func_181624_a(var1, var2, var6, var7, BlockPlanks$EnumType.DARK_OAK)) {
                     var5 = new WorldGenCanopyTree(true);
                     var8 = true;
                     break label96;
                  }
               }
            }

            if (!var8) {
               return;
            }
         case 6:
      }

      IBlockState var11 = Blocks.air.getDefaultState();
      if (var8) {
         var1.a(var2.add(var6, 0, var7), var11, 4);
         var1.a(var2.add(var6 + 1, 0, var7), var11, 4);
         var1.a(var2.add(var6, 0, var7 + 1), var11, 4);
         var1.a(var2.add(var6 + 1, 0, var7 + 1), var11, 4);
      } else {
         var1.a(var2, var11, 4);
      }

      if (!((WorldGenerator)var5).generate(var1, var4, var2.add(var6, 0, var7))) {
         if (var8) {
            var1.a(var2.add(var6, 0, var7), var3, 4);
            var1.a(var2.add(var6 + 1, 0, var7), var3, 4);
            var1.a(var2.add(var6, 0, var7 + 1), var3, 4);
            var1.a(var2.add(var6 + 1, 0, var7 + 1), var3, 4);
         } else {
            var1.a(var2, var3, 4);
         }
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(TYPE, BlockPlanks$EnumType.byMetadata(var1 & 7)).withProperty(STAGE, (var1 & 8) >> 3);
   }

   @Override
   public boolean canGrow(World var1, BlockPos var2, IBlockState var3, boolean var4) {
      return true;
   }

   public void grow(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (var3.getValue(STAGE) == 0) {
         var1.a(var2, var3.cycleProperty(STAGE), 4);
      } else {
         this.generateTree(var1, var2, var3, var4);
      }
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (!var1.D) {
         super.updateTick(var1, var2, var3, var4);
         if (var1.getLightFromNeighbors(var2.up()) >= 9 && var4.nextInt(7) == 0) {
            this.grow(var1, var2, var3, var4);
         }
      }
   }

   @Override
   public void grow(World var1, Random var2, BlockPos var3, IBlockState var4) {
      this.grow(var1, var3, var4, var2);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, TYPE, STAGE);
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(TYPE).getMetadata();
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(TYPE).getMetadata();
      return var2 | var1.getValue(STAGE) << 3;
   }

   public BlockSapling() {
      this.setDefaultState(this.M.getBaseState().withProperty(TYPE, BlockPlanks$EnumType.OAK).withProperty(STAGE, 0));
      float var1 = 0.4F;
      this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var1 * 2.0F, 0.5F + var1);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public boolean isTypeAt(World var1, BlockPos var2, BlockPlanks$EnumType var3) {
      IBlockState var4 = var1.getBlockState(var2);
      return var4.getBlock() == this && var4.getValue(TYPE) == var3;
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockPlanks$EnumType var7 : BlockPlanks$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockPlanks$EnumType.OAK.getUnlocalizedName() + ".name");
   }
}
