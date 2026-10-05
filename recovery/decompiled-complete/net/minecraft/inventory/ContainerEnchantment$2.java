package net.minecraft.inventory;

import net.minecraft.client.audio.SoundHandler$2;
import net.minecraft.item.ItemStack;

public class ContainerEnchantment$2 extends Slot {
   public SoundHandler$2 field_0000;

   public ContainerEnchantment$2(ContainerEnchantment var1, IInventory var2, int var3, int var4, int var5) {
      this.field_75227_a = var1;
      super(var2, var3, var4, var5);
   }

   @Override
   public boolean isItemValid(ItemStack var1) {
      return true;
   }

   @Override
   public int getSlotStackLimit() {
      return 1;
   }
}
