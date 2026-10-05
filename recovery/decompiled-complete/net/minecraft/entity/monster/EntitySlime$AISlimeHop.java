package net.minecraft.entity.monster;

import io.netty.buffer.PooledUnsafeDirectByteBuf$1;
import io.netty.channel.ChannelFutureListener$2;
import net.minecraft.enchantment.EnchantmentWaterWorker;
import net.minecraft.entity.ai.EntityAIBase;

public class EntitySlime$AISlimeHop extends EntityAIBase {
   public EnchantmentWaterWorker field_0001;
   public EntitySlime slime;
   public PooledUnsafeDirectByteBuf$1 field_0000;
   public ChannelFutureListener$2 field_0002;

   public EntitySlime$AISlimeHop(EntitySlime var1) {
      this.slime = var1;
      this.setMutexBits(5);
   }

   @Override
   public boolean shouldExecute() {
      return true;
   }

   @Override
   public void updateTask() {
      ((EntitySlime$SlimeMoveHelper)this.slime.q()).setSpeed(1.0);
   }
}
