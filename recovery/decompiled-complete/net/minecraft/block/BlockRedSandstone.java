package net.minecraft.block;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler$ServerHandshakeStateEvent;
import io.netty.handler.codec.http.websocketx.WebSocketUtil;
import java.util.List;
import junit.awtui.TestRunner$7;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Bootstrap$15;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import recovered.unidentified.UnidentifiedClass1748;
import recovered.unidentified.UnidentifiedClass4999;

public class BlockRedSandstone extends Block {
   public TestRunner$7 field_0002;
   public static PropertyEnum<BlockRedSandstone$EnumType> TYPE = PropertyEnum.create("type", BlockRedSandstone$EnumType.class);
   public Bootstrap$15 field_0000;
   public WebSocketServerProtocolHandler$ServerHandshakeStateEvent field_0001;
   public WebSocketUtil field_0005;
   public UnidentifiedClass4999 field_0004;
   public UnidentifiedClass1748 field_0006;

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockRedSandstone$EnumType var7 : BlockRedSandstone$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   public BlockRedSandstone() {
      super(Material.rock, BlockSand$EnumType.RED_SAND.getMapColor());
      this.setDefaultState(this.M.getBaseState().withProperty(TYPE, BlockRedSandstone$EnumType.DEFAULT));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(TYPE).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(TYPE, BlockRedSandstone$EnumType.byMetadata(var1));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, TYPE);
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(TYPE).getMetadata();
   }
}
