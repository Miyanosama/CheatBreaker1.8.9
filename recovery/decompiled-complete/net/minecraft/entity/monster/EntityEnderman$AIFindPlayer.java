package net.minecraft.entity.monster;

import java.util.Collections;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;

public class EntityEnderman$AIFindPlayer extends EntityAINearestAttackableTarget {
   public EntityEnderman enderman;
   public int field_179451_i;
   public int field_179450_h;
   public EntityPlayer player;

   public EntityEnderman$AIFindPlayer(EntityEnderman var1) {
      super(var1, EntityPlayer.class, true);
      this.enderman = var1;
   }

   @Override
   public void startExecuting() {
      this.field_179450_h = 5;
      this.field_179451_i = 0;
   }

   @Override
   public void updateTask() {
      if (this.player != null) {
         if (--this.field_179450_h <= 0) {
            this.d = this.player;
            this.player = null;
            super.startExecuting();
            this.enderman.playSound("mob.endermen.stare", 1.0F, 1.0F);
            this.enderman.setScreaming(true);
            IAttributeInstance var1 = this.enderman.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            var1.applyModifier(EntityEnderman.access$000());
         }
      } else {
         if (this.d != null) {
            if (this.d instanceof EntityPlayer && EntityEnderman.access$100(this.enderman, (EntityPlayer)this.d)) {
               if (this.d.h(this.enderman) < 16.0) {
                  this.enderman.teleportRandomly();
               }

               this.field_179451_i = 0;
            } else if (this.d.h(this.enderman) > 256.0 && this.field_179451_i++ >= 30 && this.enderman.teleportToEntity(this.d)) {
               this.field_179451_i = 0;
            }
         }

         super.updateTask();
      }
   }

   @Override
   public void resetTask() {
      this.player = null;
      this.enderman.setScreaming(false);
      IAttributeInstance var1 = this.enderman.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      var1.removeModifier(EntityEnderman.access$000());
      super.resetTask();
   }

   @Override
   public boolean continueExecuting() {
      if (this.player != null) {
         if (!EntityEnderman.access$100(this.enderman, this.player)) {
            return false;
         } else {
            EntityEnderman.access$202(this.enderman, true);
            this.enderman.a(this.player, 10.0F, 10.0F);
            return true;
         }
      } else {
         return super.continueExecuting();
      }
   }

   @Override
   public boolean shouldExecute() {
      double var1 = this.f();
      List var3 = this.e.o.getEntitiesWithinAABB(EntityPlayer.class, this.e.getEntityBoundingBox().expand(var1, 4.0, var1), this.c);
      Collections.sort(var3, this.b);
      if (var3.isEmpty()) {
         return false;
      } else {
         this.player = (EntityPlayer)var3.get(0);
         return true;
      }
   }
}
