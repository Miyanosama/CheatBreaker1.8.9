package net.minecraft.entity.monster;

import io.netty.channel.ChannelOption;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.optifine.gui.GuiMessage;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$20;

public class EntityBlaze extends EntityMob {
   public GuiMessage field_0003;
   public ChunkProviderEnd field_0005;
   public ChannelOption field_0002;
   public int heightOffsetUpdateTime;
   public float heightOffset = 0.5F;
   public LogBrokerMonitor$20 field_0001;

   @Override
   public boolean isBurning() {
      return this.func_70845_n();
   }

   @Override
   public String getHurtSound() {
      return "mob.blaze.hit";
   }

   public void setOnFire(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         var2 = (byte)(var2 | 1);
      } else {
         var2 = (byte)(var2 & -2);
      }

      this.ac.updateObject(16, var2);
   }

   @Override
   public int b_(float var1) {
      return 15728880;
   }

   @Override
   public void fall(float var1, float var2) {
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      if (var1) {
         int var3 = this.V.nextInt(2 + var2);

         for (int var4 = 0; var4 < var3; var4++) {
            this.dropItem(Items.blaze_rod, 1);
         }
      }
   }

   @Override
   public Item getDropItem() {
      return Items.blaze_rod;
   }

   @Override
   public String getDeathSound() {
      return "mob.blaze.death";
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23F);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0);
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, new Byte((byte)0));
   }

   @Override
   public String getLivingSound() {
      return "mob.blaze.breathe";
   }

   public EntityBlaze(World var1) {
      super(var1);
      this.ab = true;
      this.b_ = 10;
      this.i.addTask(4, new EntityBlaze$AIFireballAttack(this));
      this.i.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.i.addTask(7, new EntityAIWander(this, 1.0));
      this.i.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(8, new EntityAILookIdle(this));
      this.bi.addTask(1, new EntityAIHurtByTarget(this, true));
      this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
   }

   @Override
   public void updateAITasks() {
      if (this.U()) {
         this.attackEntityFrom(DamageSource.drown, 1.0F);
      }

      this.heightOffsetUpdateTime--;
      if (this.heightOffsetUpdateTime <= 0) {
         this.heightOffsetUpdateTime = 100;
         this.heightOffset = 0.5F + (float)this.V.nextGaussian() * 3.0F;
      }

      EntityLivingBase var1 = this.getAttackTarget();
      if (var1 != null && var1.t + var1.getEyeHeight() > this.t + this.getEyeHeight() + this.heightOffset) {
         this.w = this.w + (0.3F - this.w) * 0.3F;
         this.ai = true;
      }

      super.updateAITasks();
   }

   @Override
   public boolean A_() {
      return true;
   }

   @Override
   public void onLivingUpdate() {
      if (!this.C && this.w < 0.0) {
         this.w *= 0.6;
      }

      if (this.o.D) {
         if (this.V.nextInt(24) == 0 && !this.R()) {
            this.o.playSound(this.s + 0.5, this.t + 0.5, this.u + 0.5, "fire.fire", 1.0F + this.V.nextFloat(), this.V.nextFloat() * 0.7F + 0.3F, false);
         }

         for (int var1 = 0; var1 < 2; var1++) {
            this.o
               .spawnParticle(
                  EnumParticleTypes.SMOKE_LARGE,
                  this.s + (this.V.nextDouble() - 0.5) * this.J,
                  this.t + this.V.nextDouble() * this.K,
                  this.u + (this.V.nextDouble() - 0.5) * this.J,
                  0.0,
                  0.0,
                  0.0
               );
         }
      }

      super.onLivingUpdate();
   }

   @Override
   public float a_(float var1) {
      return 1.0F;
   }

   public boolean func_70845_n() {
      return (this.ac.getWatchableObjectByte(16) & 1) != 0;
   }
}
