package net.minecraft.entity.ai;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAILookAtTradePlayer extends EntityAIWatchClosest {
   public EntityVillager theMerchant;

   public EntityAILookAtTradePlayer(EntityVillager var1) {
      super(var1, EntityPlayer.class, 8.0F);
      this.theMerchant = var1;
   }

   @Override
   public boolean shouldExecute() {
      if (this.theMerchant.isTrading()) {
         this.b = this.theMerchant.getCustomer();
         return true;
      } else {
         return false;
      }
   }
}
