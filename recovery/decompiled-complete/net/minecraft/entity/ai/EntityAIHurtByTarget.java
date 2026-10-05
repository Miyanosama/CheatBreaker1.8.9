package net.minecraft.entity.ai;

import io.netty.handler.codec.spdy.DefaultSpdyHeaders$HeaderEntry;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import recovered.unidentified.UnidentifiedClass1587;

public class EntityAIHurtByTarget extends EntityAITarget {
   public DefaultSpdyHeaders$HeaderEntry field_0002;
   public boolean entityCallsForHelp;
   public int revengeTimerOld;
   public UnidentifiedClass1587 field_0003;
   public Class[] targetClasses;

   @Override
   public void startExecuting() {
      this.e.setAttackTarget(this.e.getAITarget());
      this.revengeTimerOld = this.e.getRevengeTimer();
      if (this.entityCallsForHelp) {
         double var1 = this.f();

         for (EntityCreature var4 : this.e
            .o
            .getEntitiesWithinAABB(
               this.e.getClass(), new AxisAlignedBB(this.e.s, this.e.t, this.e.u, this.e.s + 1.0, this.e.t + 1.0, this.e.u + 1.0).expand(var1, 10.0, var1)
            )) {
            if (this.e != var4 && var4.getAttackTarget() == null && !var4.isOnSameTeam(this.e.getAITarget())) {
               boolean var5 = false;

               for (Class var9 : this.targetClasses) {
                  if (var4.getClass() == var9) {
                     var5 = true;
                     break;
                  }
               }

               if (!var5) {
                  this.setEntityAttackTarget(var4, this.e.getAITarget());
               }
            }
         }
      }

      super.startExecuting();
   }

   @Override
   public boolean shouldExecute() {
      int var1 = this.e.getRevengeTimer();
      return var1 != this.revengeTimerOld && this.a(this.e.getAITarget(), false);
   }

   public EntityAIHurtByTarget(EntityCreature var1, boolean var2, Class... var3) {
      super(var1, false);
      this.entityCallsForHelp = var2;
      this.targetClasses = var3;
      this.setMutexBits(1);
   }

   public void setEntityAttackTarget(EntityCreature var1, EntityLivingBase var2) {
      var1.setAttackTarget(var2);
   }
}
