package net.minecraft.creativetab;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabs$10 extends CreativeTabs {
   public EnumCreatureType field_0000;

   public CreativeTabs$10(int var1, String var2) {
      super(var1, var2);
   }

   @Override
   public Item getTabIconItem() {
      return Items.apple;
   }
}
