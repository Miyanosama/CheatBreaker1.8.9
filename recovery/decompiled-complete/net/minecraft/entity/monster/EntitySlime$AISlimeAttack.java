package net.minecraft.entity.monster;

import net.minecraft.client.particle.EntitySpellParticleFX$WitchFactory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntitySlime$AISlimeAttack extends EntityAIBase {
   public EntitySlime slime;
   public int field_179465_b;
   public EntitySpellParticleFX$WitchFactory field_0000;

   public EntitySlime$AISlimeAttack(EntitySlime var1) {
      this.slime = var1;
      this.setMutexBits(2);
   }

   @Override
   public boolean continueExecuting() {
      EntityLivingBase var1 = this.slime.getAttackTarget();
      return var1 == null
         ? false
         : (!var1.isEntityAlive() ? false : (var1 instanceof EntityPlayer && ((EntityPlayer)var1).bA.disableDamage ? false : --this.field_179465_b > 0));
   }

   @Override
   public void startExecuting() {
      this.field_179465_b = 300;
      super.startExecuting();
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.slime.getAttackTarget();
      return var1 == null ? false : (!var1.isEntityAlive() ? false : !(var1 instanceof EntityPlayer) || !((EntityPlayer)var1).bA.disableDamage);
   }

   @Override
   public void updateTask() {
      this.slime.a(this.slime.getAttackTarget(), 10.0F, 10.0F);
      ((EntitySlime$SlimeMoveHelper)this.slime.q()).func_179920_a(this.slime.y, this.slime.canDamagePlayer());
   }
}
