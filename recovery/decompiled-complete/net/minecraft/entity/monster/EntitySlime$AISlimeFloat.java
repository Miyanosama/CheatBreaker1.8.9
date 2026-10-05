package net.minecraft.entity.monster;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$EntryIterator;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;
import recovered.unidentified.UnidentifiedClass1468;

public class EntitySlime$AISlimeFloat extends EntityAIBase {
   public EntitySlime slime;
   public ConcurrentHashMapV8$EntryIterator field_0002;
   public UnidentifiedClass1468 field_0000;

   public EntitySlime$AISlimeFloat(EntitySlime var1) {
      this.slime = var1;
      this.setMutexBits(5);
      ((PathNavigateGround)var1.s()).setCanSwim(true);
   }

   @Override
   public boolean shouldExecute() {
      return this.slime.V() || this.slime.ab();
   }

   @Override
   public void updateTask() {
      if (this.slime.getRNG().nextFloat() < 0.8F) {
         this.slime.r().setJumping();
      }

      ((EntitySlime$SlimeMoveHelper)this.slime.q()).setSpeed(1.2);
   }
}
