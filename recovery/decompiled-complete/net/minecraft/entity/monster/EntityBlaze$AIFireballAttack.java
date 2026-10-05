package net.minecraft.entity.monster;

import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.server.management.PlayerProfileCache$ProfileEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class EntityBlaze$AIFireballAttack extends EntityAIBase {
   public EntityBlaze blaze;
   public PlayerProfileCache$ProfileEntry field_0004;
   public int field_179468_c;
   public int field_179467_b;
   public LayerMooshroomMushroom field_0000;

   public EntityBlaze$AIFireballAttack(EntityBlaze var1) {
      this.blaze = var1;
      this.setMutexBits(3);
   }

   @Override
   public void resetTask() {
      this.blaze.setOnFire(false);
   }

   @Override
   public void updateTask() {
      this.field_179468_c--;
      EntityLivingBase var1 = this.blaze.getAttackTarget();
      double var2 = this.blaze.h(var1);
      if (var2 < 4.0) {
         if (this.field_179468_c <= 0) {
            this.field_179468_c = 20;
            this.blaze.attackEntityAsMob(var1);
         }

         this.blaze.q().setMoveTo(var1.s, var1.t, var1.u, 1.0);
      } else if (var2 < 256.0) {
         double var4 = var1.s - this.blaze.s;
         double var6 = var1.getEntityBoundingBox().b + var1.K / 2.0F - (this.blaze.t + this.blaze.K / 2.0F);
         double var8 = var1.u - this.blaze.u;
         if (this.field_179468_c <= 0) {
            this.field_179467_b++;
            if (this.field_179467_b == 1) {
               this.field_179468_c = 60;
               this.blaze.setOnFire(true);
            } else if (this.field_179467_b <= 4) {
               this.field_179468_c = 6;
            } else {
               this.field_179468_c = 100;
               this.field_179467_b = 0;
               this.blaze.setOnFire(false);
            }

            if (this.field_179467_b > 1) {
               float var10 = MathHelper.sqrt_float(MathHelper.sqrt_double(var2)) * 0.5F;
               this.blaze.o.playAuxSFXAtEntity((EntityPlayer)null, 1009, new BlockPos((int)this.blaze.s, (int)this.blaze.t, (int)this.blaze.u), 0);

               for (int var11 = 0; var11 < 1; var11++) {
                  EntitySmallFireball var12 = new EntitySmallFireball(
                     this.blaze.o, this.blaze, var4 + this.blaze.getRNG().nextGaussian() * var10, var6, var8 + this.blaze.getRNG().nextGaussian() * var10
                  );
                  var12.t = this.blaze.t + this.blaze.K / 2.0F + 0.5;
                  this.blaze.o.spawnEntityInWorld(var12);
               }
            }
         }

         this.blaze.getLookHelper().setLookPositionWithEntity(var1, 10.0F, 10.0F);
      } else {
         this.blaze.s().clearPathEntity();
         this.blaze.q().setMoveTo(var1.s, var1.t, var1.u, 1.0);
      }

      super.updateTask();
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.blaze.getAttackTarget();
      return var1 != null && var1.isEntityAlive();
   }

   @Override
   public void startExecuting() {
      this.field_179467_b = 0;
   }
}
