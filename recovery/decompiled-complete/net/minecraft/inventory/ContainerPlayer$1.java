package net.minecraft.inventory;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerPlayer$1 extends Slot {
   public EntitySheep field_0001;

   public ContainerPlayer$1(ContainerPlayer var1, IInventory var2, int var3, int var4, int var5, int var6) {
      this.field_75235_b = var1;
      this.field_75236_a = var6;
      super(var2, var3, var4, var5);
   }

   @Override
   public int getSlotStackLimit() {
      return 1;
   }

   @Override
   public boolean isItemValid(ItemStack var1) {
      return var1 == null
         ? false
         : (
            var1.getItem() instanceof ItemArmor
               ? ((ItemArmor)var1.getItem()).armorType == this.field_75236_a
               : (var1.getItem() != Item.getItemFromBlock(Blocks.pumpkin) && var1.getItem() != Items.skull ? false : this.field_75236_a == 0)
         );
   }

   @Override
   public String getSlotTexture() {
      return ItemArmor.EMPTY_SLOT_NAMES[this.field_75236_a];
   }
}
