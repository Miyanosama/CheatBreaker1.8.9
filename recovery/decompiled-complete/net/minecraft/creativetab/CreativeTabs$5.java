package net.minecraft.creativetab;

import io.netty.channel.AbstractChannelHandlerContext$11;
import net.minecraft.block.BlockDoublePlant$EnumPlantType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;

public class CreativeTabs$5 extends CreativeTabs {
   public Bootstrap field_0001;
   public AbstractChannelHandlerContext$11 field_0000;

   @Override
   public int getIconItemDamage() {
      return BlockDoublePlant$EnumPlantType.PAEONIA.getMeta();
   }

   @Override
   public Item getTabIconItem() {
      return Item.getItemFromBlock(Blocks.double_plant);
   }

   public CreativeTabs$5(int var1, String var2) {
      super(var1, var2);
   }
}
