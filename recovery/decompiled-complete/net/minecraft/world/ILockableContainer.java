package net.minecraft.world;

import net.minecraft.inventory.IInventory;

public interface ILockableContainer extends IInventory, IInteractionObject {
   LockCode getLockCode();

   void setLockCode(LockCode var1);

   boolean B_();
}
