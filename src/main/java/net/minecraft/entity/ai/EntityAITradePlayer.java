package net.minecraft.entity.ai;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class EntityAITradePlayer extends EntityAIBase {
   public EntityVillager villager;

   @Override
   public void resetTask() {
      this.villager.setCustomer((EntityPlayer)null);
   }

   @Override
   public void startExecuting() {
      this.villager.s().clearPathEntity();
   }

   public EntityAITradePlayer(EntityVillager var1) {
      this.villager = var1;
      this.setMutexBits(5);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.villager.isEntityAlive()) {
         return false;
      } else if (this.villager.V()) {
         return false;
      } else if (!this.villager.C) {
         return false;
      } else if (this.villager.G) {
         return false;
      } else {
         EntityPlayer var1 = this.villager.getCustomer();
         return var1 == null ? false : (this.villager.h(var1) > 16.0 ? false : var1.bk instanceof Container);
      }
   }
}
