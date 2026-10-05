package net.minecraft.block;

import io.netty.handler.codec.socks.SocksAuthResponseDecoder$1;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import recovered.unidentified.UnidentifiedClass3897;

public class BlockClay extends Block {
   public SocksAuthResponseDecoder$1 field_0000;
   public UnidentifiedClass3897 field_0001;

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.clay_ball;
   }

   public BlockClay() {
      super(Material.clay);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int quantityDropped(Random var1) {
      return 4;
   }
}
