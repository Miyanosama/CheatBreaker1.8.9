package net.minecraft.entity.ai;

import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$1;
import io.netty.handler.codec.socks.SocksCmdRequestDecoder$State;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIOwnerHurtByTarget extends EntityAITarget {
   public EntityLivingBase theOwnerAttacker;
   public SocksCmdRequestDecoder$State field_0004;
   public EntityTameable theDefendingTameable;
   public int field_142051_e;
   public EpollSocketChannel$EpollSocketUnsafe$1 field_0000;

   @Override
   public boolean shouldExecute() {
      if (!this.theDefendingTameable.isTamed()) {
         return false;
      } else {
         EntityLivingBase var1 = this.theDefendingTameable.getOwner();
         if (var1 == null) {
            return false;
         } else {
            this.theOwnerAttacker = var1.getAITarget();
            int var2 = var1.getRevengeTimer();
            return var2 != this.field_142051_e
               && this.a(this.theOwnerAttacker, false)
               && this.theDefendingTameable.shouldAttackEntity(this.theOwnerAttacker, var1);
         }
      }
   }

   @Override
   public void startExecuting() {
      this.e.setAttackTarget(this.theOwnerAttacker);
      EntityLivingBase var1 = this.theDefendingTameable.getOwner();
      if (var1 != null) {
         this.field_142051_e = var1.getRevengeTimer();
      }

      super.startExecuting();
   }

   public EntityAIOwnerHurtByTarget(EntityTameable var1) {
      super(var1, false);
      this.theDefendingTameable = var1;
      this.setMutexBits(1);
   }
}
