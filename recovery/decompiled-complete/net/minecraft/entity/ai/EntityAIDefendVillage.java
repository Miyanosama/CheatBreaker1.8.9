package net.minecraft.entity.ai;

import io.netty.channel.AbstractChannel$CloseFuture;
import net.minecraft.client.main.Main;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.village.Village;
import net.minecraft.world.gen.layer.GenLayerBiome;
import org.apache.log4j.or.DefaultRenderer;

public class EntityAIDefendVillage extends EntityAITarget {
   public Main field_0003;
   public EntityLivingBase villageAgressorTarget;
   public GenLayerBiome field_0002;
   public EntityIronGolem irongolem;
   public AbstractChannel$CloseFuture field_0000;
   public DefaultRenderer field_0001;

   @Override
   public boolean shouldExecute() {
      Village var1 = this.irongolem.getVillage();
      if (var1 == null) {
         return false;
      } else {
         this.villageAgressorTarget = var1.findNearestVillageAggressor(this.irongolem);
         if (this.villageAgressorTarget instanceof EntityCreeper) {
            return false;
         } else if (!this.a(this.villageAgressorTarget, false)) {
            if (this.e.getRNG().nextInt(20) == 0) {
               this.villageAgressorTarget = var1.getNearestTargetPlayer(this.irongolem);
               return this.a(this.villageAgressorTarget, false);
            } else {
               return false;
            }
         } else {
            return true;
         }
      }
   }

   public EntityAIDefendVillage(EntityIronGolem var1) {
      super(var1, false, true);
      this.irongolem = var1;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.irongolem.setAttackTarget(this.villageAgressorTarget);
      super.startExecuting();
   }
}
