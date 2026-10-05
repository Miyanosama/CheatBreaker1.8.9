package net.minecraft.entity.ai;

import io.netty.handler.codec.serialization.ObjectDecoder;
import java.util.List;
import java.util.Random;
import net.minecraft.client.main.llIlIIlIIllllIlIIllIIIlll;
import net.minecraft.crash.CrashReportCategory$1;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSaddle;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$JunglePyramid$Stones;

public class EntityAIMate extends EntityAIBase {
   public ObjectDecoder field_0004;
   public EntityAnimal targetMate;
   public llIlIIlIIllllIlIIllIIIlll field_0003;
   public CrashReportCategory$1 field_0006;
   public EntityAnimal theAnimal;
   public ItemSaddle field_0001;
   public ComponentScatteredFeaturePieces$JunglePyramid$Stones field_0008;
   public int spawnBabyDelay;
   public double moveSpeed;
   public World theWorld;

   public EntityAnimal getNearbyMate() {
      float var1 = 8.0F;
      List var2 = this.theWorld.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.getEntityBoundingBox().expand(var1, var1, var1));
      double var3 = Double.MAX_VALUE;
      EntityAnimal var5 = null;

      for (EntityAnimal var7 : var2) {
         if (this.theAnimal.canMateWith(var7) && this.theAnimal.h(var7) < var3) {
            var5 = var7;
            var3 = this.theAnimal.h(var7);
         }
      }

      return var5;
   }

   public EntityAIMate(EntityAnimal var1, double var2) {
      this.theAnimal = var1;
      this.theWorld = var1.o;
      this.moveSpeed = var2;
      this.setMutexBits(3);
   }

   @Override
   public void resetTask() {
      this.targetMate = null;
      this.spawnBabyDelay = 0;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theAnimal.isInLove()) {
         return false;
      } else {
         this.targetMate = this.getNearbyMate();
         return this.targetMate != null;
      }
   }

   @Override
   public void updateTask() {
      this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, this.theAnimal.getVerticalFaceSpeed());
      this.theAnimal.s().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
      this.spawnBabyDelay++;
      if (this.spawnBabyDelay >= 60 && this.theAnimal.h(this.targetMate) < 9.0) {
         this.spawnBaby();
      }
   }

   @Override
   public boolean continueExecuting() {
      return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
   }

   public void spawnBaby() {
      EntityAgeable var1 = this.theAnimal.createChild(this.targetMate);
      if (var1 != null) {
         EntityPlayer var2 = this.theAnimal.getPlayerInLove();
         if (var2 == null && this.targetMate.getPlayerInLove() != null) {
            var2 = this.targetMate.getPlayerInLove();
         }

         if (var2 != null) {
            var2.triggerAchievement(StatList.animalsBredStat);
            if (this.theAnimal instanceof EntityCow) {
               var2.triggerAchievement(AchievementList.field_0035);
            }
         }

         this.theAnimal.setGrowingAge(6000);
         this.targetMate.setGrowingAge(6000);
         this.theAnimal.resetInLove();
         this.targetMate.resetInLove();
         var1.setGrowingAge(-24000);
         var1.a_(this.theAnimal.s, this.theAnimal.t, this.theAnimal.u, 0.0F, 0.0F);
         this.theWorld.spawnEntityInWorld(var1);
         Random var3 = this.theAnimal.getRNG();

         for (int var4 = 0; var4 < 7; var4++) {
            double var5 = var3.nextGaussian() * 0.02;
            double var7 = var3.nextGaussian() * 0.02;
            double var9 = var3.nextGaussian() * 0.02;
            double var11 = var3.nextDouble() * this.theAnimal.J * 2.0 - this.theAnimal.J;
            double var13 = 0.5 + var3.nextDouble() * this.theAnimal.K;
            double var15 = var3.nextDouble() * this.theAnimal.J * 2.0 - this.theAnimal.J;
            this.theWorld
               .spawnParticle(EnumParticleTypes.HEART, this.theAnimal.s + var11, this.theAnimal.t + var13, this.theAnimal.u + var15, var5, var7, var9);
         }

         if (this.theWorld.Q().getBoolean("doMobLoot")) {
            this.theWorld.spawnEntityInWorld(new EntityXPOrb(this.theWorld, this.theAnimal.s, this.theAnimal.t, this.theAnimal.u, var3.nextInt(7) + 1));
         }
      }
   }
}
