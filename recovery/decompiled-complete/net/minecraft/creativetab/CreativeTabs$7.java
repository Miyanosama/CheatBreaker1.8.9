package net.minecraft.creativetab;

import io.netty.handler.codec.http.cors.CorsConfig$ConstantValueGenerator;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CreativeTabs$7 extends CreativeTabs {
   public RenderGlobal field_0001;
   public CorsConfig$ConstantValueGenerator field_0000;

   @Override
   public Item getTabIconItem() {
      return Item.getItemFromBlock(Blocks.golden_rail);
   }

   public CreativeTabs$7(int var1, String var2) {
      super(var1, var2);
   }
}
