package net.minecraft.inventory;

import net.minecraft.client.gui.inventory.GuiContainerCreative$CreativeSlot;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntityFurnace;
import net.optifine.gui.TooltipProviderOptions;

public class SlotFurnaceFuel extends Slot {
   public S1EPacketRemoveEntityEffect field_0001;
   public GuiContainerCreative$CreativeSlot field_0003;
   public PlayerManager field_0000;
   public TooltipProviderOptions field_0002;

   @Override
   public boolean isItemValid(ItemStack var1) {
      return TileEntityFurnace.isItemFuel(var1) || isBucket(var1);
   }

   public static boolean isBucket(ItemStack var0) {
      return var0 != null && var0.getItem() != null && var0.getItem() == Items.bucket;
   }

   public SlotFurnaceFuel(IInventory var1, int var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public int getItemStackLimit(ItemStack var1) {
      return isBucket(var1) ? 1 : super.getItemStackLimit(var1);
   }
}
