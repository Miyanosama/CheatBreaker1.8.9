package net.minecraft.util;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;

public class EntitySelectors$3 implements Predicate<Entity> {
   public BlockStoneSlabNew field_0000;
   public GuiDisconnected field_0001;

   public boolean apply(Entity var1) {
      return var1 instanceof IInventory && var1.isEntityAlive();
   }
}
