package net.minecraft.inventory;

import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.util.IChatComponent;

public class AnimalChest extends InventoryBasic {
   public EntityPickupFX field_0000;

   public AnimalChest(IChatComponent var1, int var2) {
      super(var1, var2);
   }

   public AnimalChest(String var1, int var2) {
      super(var1, false, var2);
   }
}
