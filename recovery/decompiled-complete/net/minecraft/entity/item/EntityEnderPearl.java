package net.minecraft.entity.item;

import io.netty.channel.group.ChannelMatchers$CompositeMatcher;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGuardian$GuardianTargetSelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderPearl extends EntityThrowable {
   public ChannelMatchers$CompositeMatcher field_0002;
   public BlockTorch field_0004;
   public EntityLivingBase field_181555_c;
   public EntityGuardian$GuardianTargetSelector field_0003;
   public BlockButtonWood field_0000;

   public EntityEnderPearl(World var1, EntityLivingBase var2) {
      super(var1, var2);
      this.field_181555_c = var2;
   }

   public EntityEnderPearl(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   public EntityEnderPearl(World var1) {
      super(var1);
   }

   @Override
   public void onUpdate() {
      EntityLivingBase var1 = this.getThrower();
      if (var1 != null && var1 instanceof EntityPlayer && !var1.isEntityAlive()) {
         this.setDead();
      } else {
         super.onUpdate();
      }
   }

   @Override
   public void onImpact(MovingObjectPosition var1) {
      EntityLivingBase var2 = this.getThrower();
      if (var1.entityHit != null) {
         if (var1.entityHit == this.field_181555_c) {
            return;
         }

         var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, var2), 0.0F);
      }

      for (int var3 = 0; var3 < 32; var3++) {
         this.o.spawnParticle(EnumParticleTypes.PORTAL, this.s, this.t + this.V.nextDouble() * 2.0, this.u, this.V.nextGaussian(), 0.0, this.V.nextGaussian());
      }

      if (!this.o.D) {
         if (var2 instanceof EntityPlayerMP) {
            EntityPlayerMP var5 = (EntityPlayerMP)var2;
            if (var5.playerNetServerHandler.getNetworkManager().isChannelOpen() && var5.o == this.o && !var5.bJ()) {
               if (this.V.nextFloat() < 0.05F && this.o.Q().getBoolean("doMobSpawning")) {
                  EntityEndermite var4 = new EntityEndermite(this.o);
                  var4.setSpawnedByPlayer(true);
                  var4.a_(var2.s, var2.t, var2.u, var2.y, var2.z);
                  this.o.spawnEntityInWorld(var4);
               }

               if (var2.au()) {
                  var2.mountEntity((Entity)null);
               }

               var2.setPositionAndUpdate(this.s, this.t, this.u);
               var2.O = 0.0F;
               var2.attackEntityFrom(DamageSource.fall, 5.0F);
            }
         } else if (var2 != null) {
            var2.setPositionAndUpdate(this.s, this.t, this.u);
            var2.O = 0.0F;
         }

         this.setDead();
      }
   }
}
