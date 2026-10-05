package net.minecraft.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.apache.log4j.Layout;

public class EntityLargeFireball extends EntityFireball {
   public Layout field_0000;
   public int explosionPower = 1;

   public EntityLargeFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
   }

   public EntityLargeFireball(World var1) {
      super(var1);
   }

   @Override
   public void onImpact(MovingObjectPosition var1) {
      if (!this.o.D) {
         if (var1.entityHit != null) {
            var1.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.a), 6.0F);
            this.applyEnchantments(this.a, var1.entityHit);
         }

         boolean var2 = this.o.Q().getBoolean("mobGriefing");
         this.o.newExplosion((Entity)null, this.s, this.t, this.u, this.explosionPower, var2, var2);
         this.setDead();
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.hasKey("ExplosionPower", 99)) {
         this.explosionPower = var1.getInteger("ExplosionPower");
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("ExplosionPower", this.explosionPower);
   }

   public EntityLargeFireball(World var1, EntityLivingBase var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
   }
}
