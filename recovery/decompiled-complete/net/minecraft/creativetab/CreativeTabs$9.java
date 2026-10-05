package net.minecraft.creativetab;

import net.minecraft.client.resources.LanguageManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabs$9 extends CreativeTabs {
   public LanguageManager field_0000;

   @Override
   public Item getTabIconItem() {
      return Items.compass;
   }

   public CreativeTabs$9(int var1, String var2) {
      super(var1, var2);
   }
}
