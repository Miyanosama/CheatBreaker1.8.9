package net.minecraft.block;

import java.util.List;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentArrowKnockback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$5;

public class BlockOldLeaf extends BlockLeaves {
   public static PropertyEnum<BlockPlanks$EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks$EnumType.class, new BlockOldLeaf$1());
   public LogBrokerMonitor$5 field_0002;
   public EnchantmentArrowKnockback field_0003;
   public RenderEntity field_0000;

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT, b, a);
   }

   @Override
   public BlockPlanks$EnumType getWoodType(int var1) {
      return BlockPlanks$EnumType.byMetadata((var1 & 3) % 4);
   }

   @Override
   public int getRenderColor(IBlockState var1) {
      if (var1.getBlock() != this) {
         return super.getRenderColor(var1);
      } else {
         BlockPlanks$EnumType var2 = var1.getValue(VARIANT);
         return var2 == BlockPlanks$EnumType.SPRUCE
            ? ColorizerFoliage.getFoliageColorPine()
            : (var2 == BlockPlanks$EnumType.BIRCH ? ColorizerFoliage.getFoliageColorBirch() : super.getRenderColor(var1));
      }
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.OAK.getMetadata()));
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.SPRUCE.getMetadata()));
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.BIRCH.getMetadata()));
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.JUNGLE.getMetadata()));
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public ItemStack createStackedBlock(IBlockState var1) {
      return new ItemStack(Item.getItemFromBlock(this), 1, var1.getValue(VARIANT).getMetadata());
   }

   @Override
   public int getSaplingDropChance(IBlockState var1) {
      return var1.getValue(VARIANT) == BlockPlanks$EnumType.JUNGLE ? 40 : super.getSaplingDropChance(var1);
   }

   @Override
   public void harvestBlock(World var1, EntityPlayer var2, BlockPos var3, IBlockState var4, TileEntity var5) {
      if (!var1.D && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().getItem() == Items.shears) {
         var2.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         a(var1, var3, new ItemStack(Item.getItemFromBlock(this), 1, var4.getValue(VARIANT).getMetadata()));
      } else {
         super.harvestBlock(var1, var2, var3, var4, var5);
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, this.getWoodType(var1)).withProperty(a, (var1 & 4) == 0).withProperty(b, (var1 & 8) > 0);
   }

   public BlockOldLeaf() {
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPlanks$EnumType.OAK).withProperty(b, true).withProperty(a, true));
   }

   @Override
   public void dropApple(World var1, BlockPos var2, IBlockState var3, int var4) {
      if (var3.getValue(VARIANT) == BlockPlanks$EnumType.OAK && var1.s.nextInt(var4) == 0) {
         a(var1, var2, new ItemStack(Items.apple, 1, 0));
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(VARIANT).getMetadata();
      if (!var1.getValue(a)) {
         var2 |= 4;
      }

      if (var1.getValue(b)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
      IBlockState var4 = var1.getBlockState(var2);
      if (var4.getBlock() == this) {
         BlockPlanks$EnumType var5 = var4.getValue(VARIANT);
         if (var5 == BlockPlanks$EnumType.SPRUCE) {
            return ColorizerFoliage.getFoliageColorPine();
         }

         if (var5 == BlockPlanks$EnumType.BIRCH) {
            return ColorizerFoliage.getFoliageColorBirch();
         }
      }

      return super.colorMultiplier(var1, var2, var3);
   }
}
