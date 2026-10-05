package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper$HurtIterator;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$29;

public class BlockSilverfish extends Block {
   public LogBrokerMonitor$29 field_0001;
   public static PropertyEnum<BlockSilverfish$EnumType> VARIANT = PropertyEnum.create("variant", BlockSilverfish$EnumType.class);
   public EnchantmentHelper$HurtIterator field_0000;

   @Override
   public int quantityDropped(Random var1) {
      return 0;
   }

   public BlockSilverfish() {
      super(Material.clay);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockSilverfish$EnumType.STONE));
      this.setHardness(0.0F);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockSilverfish$EnumType.byMetadata(var1));
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockSilverfish$EnumType var7 : BlockSilverfish$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   public static boolean canContainSilverfish(IBlockState var0) {
      Block var1 = var0.getBlock();
      return var0 == Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone$EnumType.STONE)
         || var1 == Blocks.cobblestone
         || var1 == Blocks.stonebrick;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      if (!var1.D && var1.Q().getBoolean("doTileDrops")) {
         EntitySilverfish var6 = new EntitySilverfish(var1);
         var6.a_(var2.getX() + 0.5, var2.getY(), var2.getZ() + 0.5, 0.0F, 0.0F);
         var1.spawnEntityInWorld(var6);
         var6.spawnExplosionParticle();
      }
   }

   @Override
   public int getDamageValue(World var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      return var3.getBlock().getMetaFromState(var3);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }

   @Override
   public ItemStack createStackedBlock(IBlockState var1) {
      switch (BlockSilverfish$1.field_180178_a[var1.getValue(VARIANT).ordinal()]) {
         case 1:
            return new ItemStack(Blocks.cobblestone);
         case 2:
            return new ItemStack(Blocks.stonebrick);
         case 3:
            return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick$EnumType.MOSSY.getMetadata());
         case 4:
            return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick$EnumType.CRACKED.getMetadata());
         case 5:
            return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick$EnumType.CHISELED.getMetadata());
         default:
            return new ItemStack(Blocks.stone);
      }
   }
}
