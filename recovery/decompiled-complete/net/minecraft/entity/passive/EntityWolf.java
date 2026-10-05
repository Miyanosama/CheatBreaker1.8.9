package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EntitySpellParticleFX$AmbientMobFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureVillagePieces$Field2;
import org.java_websocket.drafts.Draft;

public class EntityWolf extends EntityTameable {
   public float headRotationCourse;
   public boolean isShaking;
   public float timeWolfIsShaking;
   public Draft field_0000;
   public boolean isWet;
   public EntitySpellParticleFX$AmbientMobFactory field_0005;
   public float prevTimeWolfIsShaking;
   public StructureVillagePieces$Field2 field_0002;
   public S43PacketCamera field_0007;
   public float headRotationCourseOld;

   public float getShakeAngle(float var1, float var2) {
      float var3 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * var1 + var2) / 1.8F;
      if (var3 < 0.0F) {
         var3 = 0.0F;
      } else if (var3 > 1.0F) {
         var3 = 1.0F;
      }

      return MathHelper.sin(var3 * (float) Math.PI) * MathHelper.sin(var3 * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
   }

   @Override
   public float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public boolean canMateWith(EntityAnimal var1) {
      if (var1 == this) {
         return false;
      } else if (!this.isTamed()) {
         return false;
      } else if (!(var1 instanceof EntityWolf)) {
         return false;
      } else {
         EntityWolf var2 = (EntityWolf)var1;
         return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
      if (this.isTamed()) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      }

      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
   }

   public float getShadingWhileWet(float var1) {
      return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * var1) / 2.0F * 0.25F;
   }

   @Override
   public void setAttackTarget(EntityLivingBase var1) {
      super.setAttackTarget(var1);
      if (var1 == null) {
         this.setAngry(false);
      } else if (!this.isTamed()) {
         this.setAngry(true);
      }
   }

   public void setAngry(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         this.ac.updateObject(16, (byte)(var2 | 2));
      } else {
         this.ac.updateObject(16, (byte)(var2 & -3));
      }
   }

   public float getInterestedAngle(float var1) {
      return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * var1) * 0.15F * (float) Math.PI;
   }

   public float getTailRotation() {
      return this.isAngry()
         ? 1.5393804F
         : (this.isTamed() ? (0.55F - (20.0F - this.ac.getWatchableObjectFloat(18)) * 0.02F) * (float) Math.PI : (float) (Math.PI / 5));
   }

   public boolean isAngry() {
      return (this.ac.getWatchableObjectByte(16) & 2) != 0;
   }

   public boolean isBegging() {
      return this.ac.getWatchableObjectByte(19) == 1;
   }

   @Override
   public boolean shouldAttackEntity(EntityLivingBase var1, EntityLivingBase var2) {
      if (!(var1 instanceof EntityCreeper) && !(var1 instanceof EntityGhast)) {
         if (var1 instanceof EntityWolf) {
            EntityWolf var3 = (EntityWolf)var1;
            if (var3.isTamed() && var3.getOwner() == var2) {
               return false;
            }
         }

         return var1 instanceof EntityPlayer && var2 instanceof EntityPlayer && !((EntityPlayer)var2).canAttackPlayer((EntityPlayer)var1)
            ? false
            : !(var1 instanceof EntityHorse) || !((EntityHorse)var1).isTame();
      } else {
         return false;
      }
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.wolf.step", 0.15F, 1.0F);
   }

   @Override
   public int getVerticalFaceSpeed() {
      return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
   }

   @Override
   public String getLivingSound() {
      return this.isAngry()
         ? "mob.wolf.growl"
         : (this.V.nextInt(3) == 0 ? (this.isTamed() && this.ac.getWatchableObjectFloat(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
   }

   @Override
   public String getHurtSound() {
      return "mob.wolf.hurt";
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("Angry", this.isAngry());
      var1.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
   }

   @Override
   public boolean canDespawn() {
      return !this.isTamed() && this.W > 2400;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setAngry(var1.getBoolean("Angry"));
      if (var1.hasKey("CollarColor", 99)) {
         this.setCollarColor(EnumDyeColor.byDyeDamage(var1.getByte("CollarColor")));
      }
   }

   @Override
   public boolean isBreedingItem(ItemStack var1) {
      return var1 == null ? false : (!(var1.getItem() instanceof ItemFood) ? false : ((ItemFood)var1.getItem()).isWolfsFavoriteMeat());
   }

   public void setBegging(boolean var1) {
      if (var1) {
         this.ac.updateObject(19, (byte)1);
      } else {
         this.ac.updateObject(19, (byte)0);
      }
   }

   @Override
   public void updateAITasks() {
      this.ac.updateObject(18, this.getHealth());
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      this.headRotationCourseOld = this.headRotationCourse;
      if (this.isBegging()) {
         this.headRotationCourse = this.headRotationCourse + (1.0F - this.headRotationCourse) * 0.4F;
      } else {
         this.headRotationCourse = this.headRotationCourse + (0.0F - this.headRotationCourse) * 0.4F;
      }

      if (this.U()) {
         this.isWet = true;
         this.isShaking = false;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else if ((this.isWet || this.isShaking) && this.isShaking) {
         if (this.timeWolfIsShaking == 0.0F) {
            this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
         }

         this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
         this.timeWolfIsShaking += 0.05F;
         if (this.prevTimeWolfIsShaking >= 2.0F) {
            this.isWet = false;
            this.isShaking = false;
            this.prevTimeWolfIsShaking = 0.0F;
            this.timeWolfIsShaking = 0.0F;
         }

         if (this.timeWolfIsShaking > 0.4F) {
            float var1 = (float)this.getEntityBoundingBox().b;
            int var2 = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float) Math.PI) * 7.0F);

            for (int var3 = 0; var3 < var2; var3++) {
               float var4 = (this.V.nextFloat() * 2.0F - 1.0F) * this.J * 0.5F;
               float var5 = (this.V.nextFloat() * 2.0F - 1.0F) * this.J * 0.5F;
               this.o.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.s + var4, var1 + 0.8F, this.u + var5, this.v, this.w, this.x);
            }
         }
      }
   }

   public EntityWolf(World var1) {
      super(var1);
      this.setSize(0.6F, 0.8F);
      ((PathNavigateGround)this.s()).setAvoidsWater(true);
      this.i.addTask(1, new EntityAISwimming(this));
      this.i.addTask(2, this.bm);
      this.i.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
      this.i.addTask(4, new EntityAIAttackOnCollide(this, 1.0, true));
      this.i.addTask(5, new EntityAIFollowOwner(this, 1.0, 10.0F, 2.0F));
      this.i.addTask(6, new EntityAIMate(this, 1.0));
      this.i.addTask(7, new EntityAIWander(this, 1.0));
      this.i.addTask(8, new EntityAIBeg(this, 8.0F));
      this.i.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(9, new EntityAILookIdle(this));
      this.bi.addTask(1, new EntityAIOwnerHurtByTarget(this));
      this.bi.addTask(2, new EntityAIOwnerHurtTarget(this));
      this.bi.addTask(3, new EntityAIHurtByTarget(this, true));
      this.bi.addTask(4, new EntityAITargetNonTamed<>(this, EntityAnimal.class, false, new EntityWolf$1(this)));
      this.bi.addTask(5, new EntityAINearestAttackableTarget<>(this, EntitySkeleton.class, false));
      this.setTamed(false);
   }

   public void setCollarColor(EnumDyeColor var1) {
      this.ac.updateObject(20, (byte)(var1.getDyeDamage() & 15));
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         Entity var3 = var1.getEntity();
         this.bm.setSitting(false);
         if (var3 != null && !(var3 instanceof EntityPlayer) && !(var3 instanceof EntityArrow)) {
            var2 = (var2 + 1.0F) / 2.0F;
         }

         return super.attackEntityFrom(var1, var2);
      }
   }

   @Override
   public float getEyeHeight() {
      return this.K * 0.8F;
   }

   @Override
   public boolean allowLeashing() {
      return !this.isAngry() && super.allowLeashing();
   }

   @Override
   public String getDeathSound() {
      return "mob.wolf.death";
   }

   @Override
   public void setTamed(boolean var1) {
      super.setTamed(var1);
      if (var1) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      }

      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(18, new Float(this.getHealth()));
      this.ac.addObject(19, new Byte((byte)0));
      this.ac.addObject(20, new Byte((byte)EnumDyeColor.RED.getMetadata()));
   }

   @Override
   public boolean attackEntityAsMob(Entity var1) {
      boolean var2 = var1.attackEntityFrom(
         DamageSource.causeMobDamage(this), (int)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue()
      );
      if (var2) {
         this.applyEnchantments(this, var1);
      }

      return var2;
   }

   @Override
   public int getMaxSpawnedInChunk() {
      return 8;
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (this.isTamed()) {
         if (var2 != null) {
            if (var2.getItem() instanceof ItemFood) {
               ItemFood var3 = (ItemFood)var2.getItem();
               if (var3.isWolfsFavoriteMeat() && this.ac.getWatchableObjectFloat(18) < 20.0F) {
                  if (!var1.bA.isCreativeMode) {
                     var2.stackSize--;
                  }

                  this.heal(var3.getHealAmount(var2));
                  if (var2.stackSize <= 0) {
                     var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            } else if (var2.getItem() == Items.dye) {
               EnumDyeColor var4 = EnumDyeColor.byDyeDamage(var2.getMetadata());
               if (var4 != this.getCollarColor()) {
                  this.setCollarColor(var4);
                  if (!var1.bA.isCreativeMode && --var2.stackSize <= 0) {
                     var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            }
         }

         if (this.isOwner(var1) && !this.o.D && !this.isBreedingItem(var2)) {
            this.bm.setSitting(!this.isSitting());
            this.aY = false;
            this.h.clearPathEntity();
            this.setAttackTarget((EntityLivingBase)null);
         }
      } else if (var2 != null && var2.getItem() == Items.bone && !this.isAngry()) {
         if (!var1.bA.isCreativeMode) {
            var2.stackSize--;
         }

         if (var2.stackSize <= 0) {
            var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
         }

         if (!this.o.D) {
            if (this.V.nextInt(3) == 0) {
               this.setTamed(true);
               this.h.clearPathEntity();
               this.setAttackTarget((EntityLivingBase)null);
               this.bm.setSitting(true);
               this.setHealth(20.0F);
               this.setOwnerId(var1.aK().toString());
               this.playTameEffect(true);
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

   public EntityWolf createChild(EntityAgeable var1) {
      EntityWolf var2 = new EntityWolf(this.o);
      String var3 = this.getOwnerId();
      if (var3 != null && var3.trim().length() > 0) {
         var2.setOwnerId(var3);
         var2.setTamed(true);
      }

      return var2;
   }

   public boolean isWolfWet() {
      return this.isWet;
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 8) {
         this.isShaking = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.o.D && this.isWet && !this.isShaking && !this.hasPath() && this.C) {
         this.isShaking = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
         this.o.setEntityState(this, (byte)8);
      }

      if (!this.o.D && this.getAttackTarget() == null && this.isAngry()) {
         this.setAngry(false);
      }
   }

   public EnumDyeColor getCollarColor() {
      return EnumDyeColor.byDyeDamage(this.ac.getWatchableObjectByte(20) & 15);
   }

   @Override
   public Item getDropItem() {
      return Item.getItemById(-1);
   }
}
