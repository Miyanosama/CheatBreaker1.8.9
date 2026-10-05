package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;

public class ItemCoal extends Item {
   @Override
   public String getUnlocalizedName(ItemStack var1) {
      return var1.getMetadata() == 1 ? "item.charcoal" : "item.coal";
   }

   public ItemCoal() {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   @Override
   public void getSubItems(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
   }
}
