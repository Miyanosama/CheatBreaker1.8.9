package net.minecraft.inventory;

import net.minecraft.block.BlockCrops;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.ItemStack;

public class ContainerHorseInventory$2 extends Slot {
   public BlockCrops field_0001;
   public CommandBlockLogic field_0003;

   @Override
   public boolean isItemValid(ItemStack var1) {
      return super.isItemValid(var1) && this.field_111241_a.canWearArmor() && EntityHorse.isArmorItem(var1.getItem());
   }

   public ContainerHorseInventory$2(ContainerHorseInventory var1, IInventory var2, int var3, int var4, int var5, EntityHorse var6) {
      this.field_111240_b = var1;
      this.field_111241_a = var6;
      super(var2, var3, var4, var5);
   }

   @Override
   public boolean canBeHovered() {
      return this.field_111241_a.canWearArmor();
   }
}
