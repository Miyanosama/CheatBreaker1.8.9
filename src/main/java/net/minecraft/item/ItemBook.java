package net.minecraft.item;

public class ItemBook extends Item {
   @Override
   public int getItemEnchantability() {
      return 1;
   }

   @Override
   public boolean isItemTool(ItemStack var1) {
      return var1.stackSize == 1;
   }
}
