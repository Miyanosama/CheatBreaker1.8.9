package net.minecraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityEndermite extends EntityMob {
   public int lifetime = 0;
   public boolean playerSpawned = false;

   @Override
   public boolean getCanSpawnHere() {
      if (super.getCanSpawnHere()) {
         EntityPlayer var1 = this.o.getClosestPlayerToEntity(this, 5.0);
         return var1 == null;
      } else {
         return false;
      }
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.silverfish.step", 0.15F, 1.0F);
   }

   @Override
   public void onUpdate() {
      this.aI = this.y;
      super.onUpdate();
   }

   @Override
   public String getHurtSound() {
      return "mob.silverfish.hit";
   }

   @Override
   public boolean A_() {
      return true;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.o.D) {
         for (int var1 = 0; var1 < 2; var1++) {
            this.o
               .spawnParticle(
                  EnumParticleTypes.PORTAL,
                  this.s + (this.V.nextDouble() - 0.5) * this.J,
                  this.t + this.V.nextDouble() * this.K,
                  this.u + (this.V.nextDouble() - 0.5) * this.J,
                  (this.V.nextDouble() - 0.5) * 2.0,
                  -this.V.nextDouble(),
                  (this.V.nextDouble() - 0.5) * 2.0
               );
         }
      } else {
         if (!this.isNoDespawnRequired()) {
            this.lifetime++;
         }

         if (this.lifetime >= 2400) {
            this.setDead();
         }
      }
   }

   @Override
   public boolean l_() {
      return false;
   }

   public void setSpawnedByPlayer(boolean var1) {
      this.playerSpawned = var1;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
   }

   @Override
   public Item getDropItem() {
      return null;
   }

   @Override
   public float getEyeHeight() {
      return 0.1F;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.lifetime = var1.getInteger("Lifetime");
      this.playerSpawned = var1.getBoolean("PlayerSpawned");
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("Lifetime", this.lifetime);
      var1.setBoolean("PlayerSpawned", this.playerSpawned);
   }

   public boolean isSpawnedByPlayer() {
      return this.playerSpawned;
   }

   @Override
   public String getDeathSound() {
      return "mob.silverfish.kill";
   }

   public EntityEndermite(World var1) {
      super(var1);
      this.experienceValue = 3;
      this.setSize(0.4F, 0.3F);
      this.i.addTask(1, new EntityAISwimming(this));
      this.i.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
      this.i.addTask(3, new EntityAIWander(this, 1.0));
      this.i.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(8, new EntityAILookIdle(this));
      this.bi.addTask(1, new EntityAIHurtByTarget(this, true));
      this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
   }

   @Override
   public String getLivingSound() {
      return "mob.silverfish.say";
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ARTHROPOD;
   }
}
