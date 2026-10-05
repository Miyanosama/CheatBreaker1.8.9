package net.minecraft.block;

import io.netty.channel.group.CombinedIterator;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Bootstrap$5;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockHugeMushroom extends Block {
   public CombinedIterator field_0002;
   public Block smallBlock;
   public static PropertyEnum<BlockHugeMushroom$EnumType> VARIANT = PropertyEnum.create("variant", BlockHugeMushroom$EnumType.class);
   public EntityMinecart field_0001;
   public Bootstrap$5 field_0005;
   public BlockStone$EnumType field_0004;

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockHugeMushroom$EnumType.byMetadata(var1));
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return this.getDefaultState();
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   public BlockHugeMushroom(Material var1, MapColor var2, Block var3) {
      super(var1, var2);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockHugeMushroom$EnumType.ALL_OUTSIDE));
      this.smallBlock = var3;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Item.getItemFromBlock(this.smallBlock);
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Item.getItemFromBlock(this.smallBlock);
   }

   @Override
   public int quantityDropped(Random var1) {
      return Math.max(0, var1.nextInt(10) - 7);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      switch (BlockHugeMushroom$1.field_181092_a[var1.getValue(VARIANT).ordinal()]) {
         case 1:
            return MapColor.clothColor;
         case 2:
            return MapColor.sandColor;
         case 3:
            return MapColor.sandColor;
         default:
            return super.getMapColor(var1);
      }
   }
}
