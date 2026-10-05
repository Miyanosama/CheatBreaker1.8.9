package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;

public class EntityAINearestAttackableTarget<T extends EntityLivingBase> extends EntityAITarget {
   public EntityAINearestAttackableTarget.Sorter b;
   public EntityLivingBase d;
   public int targetChance;
   public Predicate<? super T> c;
   public Class<T> targetClass;

   public EntityAINearestAttackableTarget(EntityCreature var1, Class<T> var2, boolean var3, boolean var4) {
      this(var1, var2, 10, var3, var4, (Predicate<? super T>)null);
   }

   @Override
   public void startExecuting() {
      this.e.setAttackTarget(this.d);
      super.startExecuting();
   }

   public EntityAINearestAttackableTarget(EntityCreature var1, Class<T> var2, int var3, boolean var4, boolean var5, final Predicate<? super T> var6) {
      super(var1, var4, var5);
      this.targetClass = var2;
      this.targetChance = var3;
      this.b = new EntityAINearestAttackableTarget.Sorter(var1);
      this.setMutexBits(1);
      this.c = new Predicate<T>() {
         public boolean apply(T var1) {
            if (var6 != null && !var6.apply(var1)) {
               return false;
            } else {
               if (var1 instanceof EntityPlayer) {
                  double var2x = EntityAINearestAttackableTarget.this.f();
                  if (var1.isSneaking()) {
                     var2x *= 0.8F;
                  }

                  if (var1.isInvisible()) {
                     float var4x = ((EntityPlayer)var1).getArmorVisibility();
                     if (var4x < 0.1F) {
                        var4x = 0.1F;
                     }

                     var2x *= 0.7F * var4x;
                  }

                  if (var1.g(EntityAINearestAttackableTarget.this.e) > var2x) {
                     return false;
                  }
               }

               return EntityAINearestAttackableTarget.this.a(var1, false);
            }
         }
      };
   }

   public EntityAINearestAttackableTarget(EntityCreature var1, Class<T> var2, boolean var3) {
      this(var1, var2, var3, false);
   }

   @Override
   public boolean shouldExecute() {
      if (this.targetChance > 0 && this.e.getRNG().nextInt(this.targetChance) != 0) {
         return false;
      } else {
         double var1 = this.f();
         List var3 = this.e
            .o
            .getEntitiesWithinAABB(
               this.targetClass, this.e.getEntityBoundingBox().expand(var1, 4.0, var1), Predicates.and(this.c, EntitySelectors.NOT_SPECTATING)
            );
         Collections.sort(var3, this.b);
         if (var3.isEmpty()) {
            return false;
         } else {
            this.d = (EntityLivingBase)var3.get(0);
            return true;
         }
      }
   }

   public static class Sorter implements Comparator<Entity> {
      public Entity theEntity;

      public Sorter(Entity var1) {
         this.theEntity = var1;
      }

      public int compare(Entity var1, Entity var2) {
         double var3 = this.theEntity.h(var1);
         double var5 = this.theEntity.h(var2);
         return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
      }
   }
}
