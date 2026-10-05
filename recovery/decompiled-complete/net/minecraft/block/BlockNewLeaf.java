package net.minecraft.block;

import java.util.List;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureMap$3;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Bootstrap$16;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass3918;

public class BlockNewLeaf extends BlockLeaves {
   public TextureMap$3 field_0001;
   public UnidentifiedClass3918 field_0003;
   public Teleporter field_0004;
   public static PropertyEnum<BlockPlanks$EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks$EnumType.class, new BlockNewLeaf$1());
   public Bootstrap$16 field_0002;

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
   }

   @Override
   public void dropApple(World var1, BlockPos var2, IBlockState var3, int var4) {
      if (var3.getValue(VARIANT) == BlockPlanks$EnumType.DARK_OAK && var1.s.nextInt(var4) == 0) {
         a(var1, var2, new ItemStack(Items.apple, 1, 0));
      }
   }

   @Override
   public ItemStack createStackedBlock(IBlockState var1) {
      return new ItemStack(Item.getItemFromBlock(this), 1, var1.getValue(VARIANT).getMetadata() - 4);
   }

   @Override
   public void harvestBlock(World var1, EntityPlayer var2, BlockPos var3, IBlockState var4, TileEntity var5) {
      if (!var1.D && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().getItem() == Items.shears) {
         var2.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         a(var1, var3, new ItemStack(Item.getItemFromBlock(this), 1, var4.getValue(VARIANT).getMetadata() - 4));
      } else {
         super.harvestBlock(var1, var2, var3, var4, var5);
      }
   }

   @Override
   public int getDamageValue(World var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      return var3.getBlock().getMetaFromState(var3) & 3;
   }

   public BlockNewLeaf() {
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPlanks$EnumType.ACACIA).withProperty(b, true).withProperty(a, true));
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(VARIANT).getMetadata() - 4;
      if (!var1.getValue(a)) {
         var2 |= 4;
      }

      if (var1.getValue(b)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public BlockPlanks$EnumType getWoodType(int var1) {
      return BlockPlanks$EnumType.byMetadata((var1 & 3) + 4);
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT, b, a);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, this.getWoodType(var1)).withProperty(a, (var1 & 4) == 0).withProperty(b, (var1 & 8) > 0);
   }
}
