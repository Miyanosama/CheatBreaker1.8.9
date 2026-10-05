package net.minecraft.inventory;

import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S22PacketMultiBlockChange$BlockUpdateData;

public class ContainerEnchantment$3 extends Slot {
   public GuiAchievements field_0001;
   public S22PacketMultiBlockChange$BlockUpdateData field_0002;

   @Override
   public boolean isItemValid(ItemStack var1) {
      return var1.getItem() == Items.dye && EnumDyeColor.byDyeDamage(var1.getMetadata()) == EnumDyeColor.BLUE;
   }

   public ContainerEnchantment$3(ContainerEnchantment var1, IInventory var2, int var3, int var4, int var5) {
      this.field_178172_a = var1;
      super(var2, var3, var4, var5);
   }
}
