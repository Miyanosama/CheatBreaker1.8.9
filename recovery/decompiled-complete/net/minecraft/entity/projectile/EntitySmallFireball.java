package net.minecraft.entity.projectile;

import io.netty.util.internal.InternalThreadLocalMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayer;

public class EntitySmallFireball extends EntityFireball {
   public GenLayer field_0000;
   public InternalThreadLocalMap field_0001;

   public EntitySmallFireball(World var1, EntityLivingBase var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public boolean canBeCollidedWith() {
      return false;
   }

   public EntitySmallFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return false;
   }

   public EntitySmallFireball(World var1) {
      super(var1);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public void onImpact(MovingObjectPosition var1) {
      if (!this.o.D) {
         if (var1.entityHit != null) {
            boolean var2 = var1.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.a), 5.0F);
            if (var2) {
               this.applyEnchantments(this.a, var1.entityHit);
               if (!var1.entityHit.isImmuneToFire()) {
                  var1.entityHit.setFire(5);
               }
            }
         } else {
            boolean var4 = true;
            if (this.a != null && this.a instanceof EntityLiving) {
               var4 = this.o.Q().getBoolean("mobGriefing");
            }

            if (var4) {
               BlockPos var3 = var1.getBlockPos().a(var1.sideHit);
               if (this.o.isAirBlock(var3)) {
                  this.o.setBlockState(var3, Blocks.fire.getDefaultState());
               }
            }
         }

         this.setDead();
      }
   }
}
