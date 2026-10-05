package net.minecraft.creativetab;

import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import org.java_websocket.util.Charsetfunctions;
import recovered.unidentified.UnidentifiedClass0878;

public class CreativeTabs$1 extends CreativeTabs {
   public UnidentifiedClass0878 field_0001;
   public RenderEnderCrystal field_0000;
   public Charsetfunctions field_0002;

   @Override
   public Item getTabIconItem() {
      return Item.getItemFromBlock(Blocks.brick_block);
   }

   public CreativeTabs$1(int var1, String var2) {
      super(var1, var2);
   }
}
