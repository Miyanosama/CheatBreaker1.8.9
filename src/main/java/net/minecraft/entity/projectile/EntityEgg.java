package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEgg extends EntityThrowable {
   public EntityEgg(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   @Override
   public void onImpact(MovingObjectPosition var1) {
      if (var1.entityHit != null) {
         var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
      }

      if (!this.o.D && this.V.nextInt(8) == 0) {
         byte var2 = 1;
         if (this.V.nextInt(32) == 0) {
            var2 = 4;
         }

         for (int var3 = 0; var3 < var2; var3++) {
            EntityChicken var4 = new EntityChicken(this.o);
            var4.setGrowingAge(-24000);
            var4.a_(this.s, this.t, this.u, this.y, 0.0F);
            this.o.spawnEntityInWorld(var4);
         }
      }

      double var5 = 0.08;

      for (int var6 = 0; var6 < 8; var6++) {
         this.o
            .spawnParticle(
               EnumParticleTypes.ITEM_CRACK,
               this.s,
               this.t,
               this.u,
               (this.V.nextFloat() - 0.5) * 0.08,
               (this.V.nextFloat() - 0.5) * 0.08,
               (this.V.nextFloat() - 0.5) * 0.08,
               Item.getIdFromItem(Items.egg)
            );
      }

      if (!this.o.D) {
         this.setDead();
      }
   }

   public EntityEgg(World var1) {
      super(var1);
   }

   public EntityEgg(World var1, EntityLivingBase var2) {
      super(var1, var2);
   }
}
