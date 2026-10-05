package net.minecraft.entity.ai;

import io.netty.channel.socket.oio.OioServerSocketChannel;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;

public class EntityAILookAtVillager extends EntityAIBase {
   public EntityIronGolem theGolem;
   public OioServerSocketChannel field_0004;
   public EntityVillager theVillager;
   public ItemRenderer field_0003;
   public int lookTime;

   @Override
   public boolean continueExecuting() {
      return this.lookTime > 0;
   }

   @Override
   public void startExecuting() {
      this.lookTime = 400;
      this.theGolem.setHoldingRose(true);
   }

   @Override
   public void resetTask() {
      this.theGolem.setHoldingRose(false);
      this.theVillager = null;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theGolem.o.isDaytime()) {
         return false;
      } else if (this.theGolem.getRNG().nextInt(8000) != 0) {
         return false;
      } else {
         this.theVillager = this.theGolem
            .o
            .findNearestEntityWithinAABB(EntityVillager.class, this.theGolem.getEntityBoundingBox().expand(6.0, 2.0, 6.0), this.theGolem);
         return this.theVillager != null;
      }
   }

   @Override
   public void updateTask() {
      this.theGolem.getLookHelper().setLookPositionWithEntity(this.theVillager, 30.0F, 30.0F);
      this.lookTime--;
   }

   public EntityAILookAtVillager(EntityIronGolem var1) {
      this.theGolem = var1;
      this.setMutexBits(3);
   }
}
