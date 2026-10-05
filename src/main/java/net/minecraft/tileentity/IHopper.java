package net.minecraft.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public interface IHopper extends IInventory {
   double getYPos();

   World getWorld();

   double getXPos();

   double getZPos();
}
