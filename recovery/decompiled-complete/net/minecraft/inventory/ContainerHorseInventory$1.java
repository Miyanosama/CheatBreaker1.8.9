package net.minecraft.inventory;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;

public class ContainerHorseInventory$1 extends Slot {
   public Item field_0001;
   public DefaultSSLWebSocketServerFactory field_0000;

   @Override
   public boolean isItemValid(ItemStack var1) {
      return super.isItemValid(var1) && var1.getItem() == Items.saddle && !this.getHasStack();
   }

   public ContainerHorseInventory$1(ContainerHorseInventory var1, IInventory var2, int var3, int var4, int var5) {
      this.field_111239_a = var1;
      super(var2, var3, var4, var5);
   }
}
