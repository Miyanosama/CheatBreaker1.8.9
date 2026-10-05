package net.minecraft.entity.passive;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.entity.ai.EntityAIOcelotAttack;

public class EntityOcelot extends EntityTameable {
   public EntityAITempt aiTempt;
   public EntityAIAvoidEntity<EntityPlayer> avoidEntity;

   @Override
   public void setupTamedAI() {
      if (this.avoidEntity == null) {
         this.avoidEntity = new EntityAIAvoidEntity<>(this, EntityPlayer.class, 16.0F, 0.8, 1.33);
      }

      this.i.removeTask(this.avoidEntity);
      if (!this.isTamed()) {
         this.i.addTask(4, this.avoidEntity);
      }
   }

   @Override
   public void updateAITasks() {
      if (this.q().isUpdating()) {
         double var1 = this.q().getSpeed();
         if (var1 == 0.6) {
            this.setSneaking(true);
            this.setSprinting(false);
         } else if (var1 == 1.33) {
            this.setSneaking(false);
            this.setSprinting(true);
         } else {
            this.setSneaking(false);
            this.setSprinting(false);
         }
      } else {
         this.setSneaking(false);
         this.setSprinting(false);
      }
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.o.s.nextInt(3) != 0;
   }

   public EntityOcelot(World var1) {
      super(var1);
      this.setSize(0.6F, 0.7F);
      ((PathNavigateGround)this.s()).setAvoidsWater(true);
      this.i.addTask(1, new EntityAISwimming(this));
      this.i.addTask(2, this.bm);
      this.i.addTask(3, this.aiTempt = new EntityAITempt(this, 0.6, Items.fish, true));
      this.i.addTask(5, new EntityAIFollowOwner(this, 1.0, 10.0F, 5.0F));
      this.i.addTask(6, new EntityAIOcelotSit(this, 0.8));
      this.i.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
      this.i.addTask(8, new EntityAIOcelotAttack(this));
      this.i.addTask(9, new EntityAIMate(this, 0.8));
      this.i.addTask(10, new EntityAIWander(this, 0.8));
      this.i.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
      this.bi.addTask(1, new EntityAITargetNonTamed<>(this, EntityChicken.class, false, (Predicate<? super EntityChicken>)null));
   }

   @Override
   public boolean isBreedingItem(ItemStack var1) {
      return var1 != null && var1.getItem() == Items.fish;
   }

   @Override
   public String getHurtSound() {
      return "mob.cat.hitt";
   }

   public int getTameSkin() {
      return this.ac.getWatchableObjectByte(18);
   }

   @Override
   public Item getDropItem() {
      return Items.leather;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("CatType", this.getTameSkin());
   }

   @Override
   public boolean canMateWith(EntityAnimal var1) {
      if (var1 == this) {
         return false;
      } else if (!this.isTamed()) {
         return false;
      } else if (!(var1 instanceof EntityOcelot)) {
         return false;
      } else {
         EntityOcelot var2 = (EntityOcelot)var1;
         return !var2.isTamed() ? false : this.isInLove() && var2.isInLove();
      }
   }

   public void setTameSkin(int var1) {
      this.ac.updateObject(18, (byte)var1);
   }

   @Override
   public String z_() {
      return this.u_() ? this.aM() : (this.isTamed() ? StatCollector.translateToLocal("entity.Cat.name") : super.z_());
   }

   @Override
   public boolean isNotColliding() {
      if (this.o.checkNoEntityCollision(this.getEntityBoundingBox(), this)
         && this.o.a(this, this.getEntityBoundingBox()).isEmpty()
         && !this.o.isAnyLiquid(this.getEntityBoundingBox())) {
         BlockPos var1 = new BlockPos(this.s, this.getEntityBoundingBox().b, this.u);
         if (var1.getY() < this.o.F()) {
            return false;
         }

         Block var2 = this.o.getBlockState(var1.down()).getBlock();
         if (var2 == Blocks.grass || var2.getMaterial() == Material.leaves) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (this.isTamed()) {
         if (this.isOwner(var1) && !this.o.D && !this.isBreedingItem(var2)) {
            this.bm.setSitting(!this.isSitting());
         }
      } else if (this.aiTempt.isRunning() && var2 != null && var2.getItem() == Items.fish && var1.h(this) < 9.0) {
         if (!var1.bA.isCreativeMode) {
            var2.stackSize--;
         }

         if (var2.stackSize <= 0) {
            var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
         }

         if (!this.o.D) {
            if (this.V.nextInt(3) == 0) {
               this.setTamed(true);
               this.setTameSkin(1 + this.o.s.nextInt(3));
               this.setOwnerId(var1.aK().toString());
               this.playTameEffect(true);
               this.bm.setSitting(true);
               this.o.setEntityState(this, (byte)7);
            } else {
               this.playTameEffect(false);
               this.o.setEntityState(this, (byte)6);
            }
         }

         return true;
      }

      return super.interact(var1);
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
   }

   @Override
   public String getDeathSound() {
      return "mob.cat.hitt";
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setTameSkin(var1.getInteger("CatType"));
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(18, (byte)0);
   }

   @Override
   public String getLivingSound() {
      return this.isTamed() ? (this.isInLove() ? "mob.cat.purr" : (this.V.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
   }

   @Override
   public void fall(float var1, float var2) {
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData var2) {
      var2 = super.onInitialSpawn(var1, var2);
      if (this.o.s.nextInt(7) == 0) {
         for (int var3 = 0; var3 < 2; var3++) {
            EntityOcelot var4 = new EntityOcelot(this.o);
            var4.a_(this.s, this.t, this.u, this.y, 0.0F);
            var4.setGrowingAge(-24000);
            this.o.spawnEntityInWorld(var4);
         }
      }

      return var2;
   }

   public EntityOcelot createChild(EntityAgeable var1) {
      EntityOcelot var2 = new EntityOcelot(this.o);
      if (this.isTamed()) {
         var2.setOwnerId(this.getOwnerId());
         var2.setTamed(true);
         var2.setTameSkin(this.getTameSkin());
      }

      return var2;
   }

   @Override
   public boolean attackEntityAsMob(Entity var1) {
      return var1.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
   }

   @Override
   public float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public boolean canDespawn() {
      return !this.isTamed() && this.W > 2400;
   }

   @Override
   public void setTamed(boolean var1) {
      super.setTamed(var1);
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         this.bm.setSitting(false);
         return super.attackEntityFrom(var1, var2);
      }
   }
}
