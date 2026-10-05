package net.minecraft.entity.monster;

import net.minecraft.block.BlockEnderChest;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StringUtils;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGhast extends EntityFlying implements IMob {
   public BlockEnderChest field_0002;
   public int explosionStrength = 1;
   public StringUtils field_0001;

   @Override
   public boolean getCanSpawnHere() {
      return this.V.nextInt(20) == 0 && super.getCanSpawnHere() && this.o.getDifficulty() != EnumDifficulty.PEACEFUL;
   }

   public int getFireballStrength() {
      return this.explosionStrength;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("ExplosionPower", this.explosionStrength);
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(2) + this.V.nextInt(1 + var2);

      for (int var4 = 0; var4 < var3; var4++) {
         this.dropItem(Items.ghast_tear, 1);
      }

      var3 = this.V.nextInt(3) + this.V.nextInt(1 + var2);

      for (int var6 = 0; var6 < var3; var6++) {
         this.dropItem(Items.gunpowder, 1);
      }
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, (byte)0);
   }

   @Override
   public float getEyeHeight() {
      return 2.6F;
   }

   @Override
   public String getLivingSound() {
      return "mob.ghast.moan";
   }

   public EntityGhast(World var1) {
      super(var1);
      this.setSize(4.0F, 4.0F);
      this.ab = true;
      this.b_ = 5;
      this.f = new EntityGhast$GhastMoveHelper(this);
      this.i.addTask(5, new EntityGhast$AIRandomFly(this));
      this.i.addTask(7, new EntityGhast$AILookAround(this));
      this.i.addTask(7, new EntityGhast$AIFireballAttack(this));
      this.bi.addTask(1, new EntityAIFindEntityNearestPlayer(this));
   }

   public void setAttacking(boolean var1) {
      this.ac.updateObject(16, (byte)(var1 ? 1 : 0));
   }

   @Override
   public Item getDropItem() {
      return Items.gunpowder;
   }

   @Override
   public String getHurtSound() {
      return "mob.ghast.scream";
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if ("fireball".equals(var1.getDamageType()) && var1.getEntity() instanceof EntityPlayer) {
         super.attackEntityFrom(var1, 1000.0F);
         ((EntityPlayer)var1.getEntity()).triggerAchievement(AchievementList.ghast);
         return true;
      } else {
         return super.attackEntityFrom(var1, var2);
      }
   }

   @Override
   public float getSoundVolume() {
      return 10.0F;
   }

   @Override
   public String getDeathSound() {
      return "mob.ghast.death";
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0);
   }

   @Override
   public int getMaxSpawnedInChunk() {
      return 1;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.o.D && this.o.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.hasKey("ExplosionPower", 99)) {
         this.explosionStrength = var1.getInteger("ExplosionPower");
      }
   }

   public boolean isAttacking() {
      return this.ac.getWatchableObjectByte(16) != 0;
   }
}
