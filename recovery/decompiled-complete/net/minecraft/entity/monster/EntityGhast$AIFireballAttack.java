package net.minecraft.entity.monster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemMapBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGhast$AIFireballAttack extends EntityAIBase {
   public ItemMapBase field_0001;
   public int attackTimer;
   public EntityGhast parentEntity;

   @Override
   public void updateTask() {
      EntityLivingBase var1 = this.parentEntity.getAttackTarget();
      double var2 = 64.0;
      if (var1.h(this.parentEntity) < var2 * var2 && this.parentEntity.t(var1)) {
         World var4 = this.parentEntity.o;
         this.attackTimer++;
         if (this.attackTimer == 10) {
            var4.playAuxSFXAtEntity((EntityPlayer)null, 1007, new BlockPos(this.parentEntity), 0);
         }

         if (this.attackTimer == 20) {
            double var5 = 4.0;
            Vec3 var7 = this.parentEntity.getLook(1.0F);
            double var8 = var1.s - (this.parentEntity.s + var7.xCoord * var5);
            double var10 = var1.getEntityBoundingBox().b + var1.K / 2.0F - (0.5 + this.parentEntity.t + this.parentEntity.K / 2.0F);
            double var12 = var1.u - (this.parentEntity.u + var7.zCoord * var5);
            var4.playAuxSFXAtEntity((EntityPlayer)null, 1008, new BlockPos(this.parentEntity), 0);
            EntityLargeFireball var14 = new EntityLargeFireball(var4, this.parentEntity, var8, var10, var12);
            var14.explosionPower = this.parentEntity.getFireballStrength();
            var14.s = this.parentEntity.s + var7.xCoord * var5;
            var14.t = this.parentEntity.t + this.parentEntity.K / 2.0F + 0.5;
            var14.u = this.parentEntity.u + var7.zCoord * var5;
            var4.spawnEntityInWorld(var14);
            this.attackTimer = -40;
         }
      } else if (this.attackTimer > 0) {
         this.attackTimer--;
      }

      this.parentEntity.setAttacking(this.attackTimer > 10);
   }

   public EntityGhast$AIFireballAttack(EntityGhast var1) {
      this.parentEntity = var1;
   }

   @Override
   public void startExecuting() {
      this.attackTimer = 0;
   }

   @Override
   public boolean shouldExecute() {
      return this.parentEntity.getAttackTarget() != null;
   }

   @Override
   public void resetTask() {
      this.parentEntity.setAttacking(false);
   }
}
