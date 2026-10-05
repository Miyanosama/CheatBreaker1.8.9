package net.minecraft.inventory;

import net.minecraft.util.IChatComponent;

public class AnimalChest extends InventoryBasic {
   public AnimalChest(IChatComponent var1, int var2) {
      super(var1, var2);
   }

   public AnimalChest(String var1, int var2) {
      super(var1, false, var2);
   }
}
