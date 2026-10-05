package net.minecraft.block;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.ssl.JettyNpnSslEngine$2;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachMappingTask;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ReduceKeysTask;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.optifine.util.LinkedList;

public abstract class BlockStoneSlab extends BlockSlab {
   public static PropertyEnum<BlockStoneSlab$EnumType> VARIANT = PropertyEnum.create("variant", BlockStoneSlab$EnumType.class);
   public GuiNewChat field_0002;
   public ConcurrentHashMapV8$ReduceKeysTask field_0007;
   public ConcurrentHashMapV8$ForEachMappingTask field_0005;
   public static PropertyBool SEAMLESS = PropertyBool.create("seamless");
   public LinkedList field_0000;
   public DefaultHttpHeaders field_0004;
   public JettyNpnSslEngine$2 field_0003;
   public WebSocketServerHandshaker field_0008;

   public BlockStoneSlab() {
      super(Material.rock);
      IBlockState var1 = this.M.getBaseState();
      if (this.isDouble()) {
         var1 = var1.withProperty(SEAMLESS, false);
      } else {
         var1 = var1.withProperty(a, BlockSlab$EnumBlockHalf.BOTTOM);
      }

      this.setDefaultState(var1.withProperty(VARIANT, BlockStoneSlab$EnumType.STONE));
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
      } else if (var1.getValue(a) == BlockSlab$EnumBlockHalf.TOP) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      if (var1 != Item.getItemFromBlock(Blocks.double_stone_slab)) {
         for (BlockStoneSlab$EnumType var7 : BlockStoneSlab$EnumType.values()) {
            if (var7 != BlockStoneSlab$EnumType.WOOD) {
               var3.add(new ItemStack(var1, 1, var7.getMetadata()));
            }
         }
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      IBlockState var2 = this.getDefaultState().withProperty(VARIANT, BlockStoneSlab$EnumType.byMetadata(var1 & 7));
      if (this.isDouble()) {
         var2 = var2.withProperty(SEAMLESS, (var1 & 8) != 0);
      } else {
         var2 = var2.withProperty(a, (var1 & 8) == 0 ? BlockSlab$EnumBlockHalf.BOTTOM : BlockSlab$EnumBlockHalf.TOP);
      }

      return var2;
   }

   @Override
   public String getUnlocalizedName(int var1) {
      return super.getUnlocalizedName() + "." + BlockStoneSlab$EnumType.byMetadata(var1).getUnlocalizedName();
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
      return BlockStoneSlab$EnumType.byMetadata(var1.getMetadata() & 7);
   }

   @Override
   public IProperty<?> getVariantProperty() {
      return VARIANT;
   }

   @Override
   public BlockState createBlockState() {
      return this.isDouble() ? new BlockState(this, SEAMLESS, VARIANT) : new BlockState(this, a, VARIANT);
   }
}
