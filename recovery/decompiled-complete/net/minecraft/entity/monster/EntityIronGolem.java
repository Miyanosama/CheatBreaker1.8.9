package net.minecraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower$EnumFlowerType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityFirework$StarterFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayerSmooth;

public class EntityIronGolem extends EntityGolem {
   public GenLayerSmooth field_0002;
   public int homeCheckTimer;
   public Village villageObj;
   public EntityFirework$StarterFX field_0001;
   public int holdRoseTick;
   public int attackTimer;

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.attackTimer > 0) {
         this.attackTimer--;
      }

      if (this.holdRoseTick > 0) {
         this.holdRoseTick--;
      }

      if (this.v * this.v + this.x * this.x > 2.5000003E-7F && this.V.nextInt(5) == 0) {
         int var1 = MathHelper.floor_double(this.s);
         int var2 = MathHelper.floor_double(this.t - 0.2F);
         int var3 = MathHelper.floor_double(this.u);
         IBlockState var4 = this.o.getBlockState(new BlockPos(var1, var2, var3));
         Block var5 = var4.getBlock();
         if (var5.getMaterial() != Material.air) {
            this.o
               .spawnParticle(
                  EnumParticleTypes.BLOCK_CRACK,
                  this.s + (this.V.nextFloat() - 0.5) * this.J,
                  this.getEntityBoundingBox().b + 0.1,
                  this.u + (this.V.nextFloat() - 0.5) * this.J,
                  4.0 * (this.V.nextFloat() - 0.5),
                  0.5,
                  (this.V.nextFloat() - 0.5) * 4.0,
                  Block.getStateId(var4)
               );
         }
      }
   }

   public Village getVillage() {
      return this.villageObj;
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, (byte)0);
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(3);

      for (int var4 = 0; var4 < var3; var4++) {
         this.dropItemWithOffset(Item.getItemFromBlock(Blocks.red_flower), 1, BlockFlower$EnumFlowerType.POPPY.getMeta());
      }

      int var6 = 3 + this.V.nextInt(3);

      for (int var5 = 0; var5 < var6; var5++) {
         this.dropItem(Items.iron_ingot, 1);
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setPlayerCreated(var1.getBoolean("PlayerCreated"));
   }

   @Override
   public boolean attackEntityAsMob(Entity var1) {
      this.attackTimer = 10;
      this.o.setEntityState(this, (byte)4);
      boolean var2 = var1.attackEntityFrom(DamageSource.causeMobDamage(this), 7 + this.V.nextInt(15));
      if (var2) {
         var1.w += 0.4F;
         this.applyEnchantments(this, var1);
      }

      this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      return var2;
   }

   public int getAttackTimer() {
      return this.attackTimer;
   }

   @Override
   public void updateAITasks() {
      if (--this.homeCheckTimer <= 0) {
         this.homeCheckTimer = 70 + this.V.nextInt(50);
         this.villageObj = this.o.getVillageCollection().getNearestVillage(new BlockPos(this), 32);
         if (this.villageObj == null) {
            this.detachHome();
         } else {
            BlockPos var1 = this.villageObj.getCenter();
            this.setHomePosAndDistance(var1, (int)(this.villageObj.getVillageRadius() * 0.6F));
         }
      }

      super.updateAITasks();
   }

   public void setPlayerCreated(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         this.ac.updateObject(16, (byte)(var2 | 1));
      } else {
         this.ac.updateObject(16, (byte)(var2 & -2));
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
   }

   public boolean isPlayerCreated() {
      return (this.ac.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 4) {
         this.attackTimer = 10;
         this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      } else if (var1 == 11) {
         this.holdRoseTick = 400;
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public void collideWithEntity(Entity var1) {
      if (var1 instanceof IMob && !(var1 instanceof EntityCreeper) && this.getRNG().nextInt(20) == 0) {
         this.setAttackTarget((EntityLivingBase)var1);
      }

      super.collideWithEntity(var1);
   }

   @Override
   public String getHurtSound() {
      return "mob.irongolem.hit";
   }

   public void setHoldingRose(boolean var1) {
      this.holdRoseTick = var1 ? 400 : 0;
      this.o.setEntityState(this, (byte)11);
   }

   @Override
   public void onDeath(DamageSource var1) {
      if (!this.isPlayerCreated() && this.aN != null && this.villageObj != null) {
         this.villageObj.setReputationForPlayer(this.aN.z_(), -5);
      }

      super.onDeath(var1);
   }

   public EntityIronGolem(World var1) {
      super(var1);
      this.setSize(1.4F, 2.9F);
      ((PathNavigateGround)this.s()).setAvoidsWater(true);
      this.i.addTask(1, new EntityAIAttackOnCollide(this, 1.0, true));
      this.i.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9, 32.0F));
      this.i.addTask(3, new EntityAIMoveThroughVillage(this, 0.6, true));
      this.i.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.i.addTask(5, new EntityAILookAtVillager(this));
      this.i.addTask(6, new EntityAIWander(this, 0.6));
      this.i.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.i.addTask(8, new EntityAILookIdle(this));
      this.bi.addTask(1, new EntityAIDefendVillage(this));
      this.bi.addTask(2, new EntityAIHurtByTarget(this, false));
      this.bi.addTask(3, new EntityIronGolem$AINearestAttackableTargetNonCreeper<>(this, EntityLiving.class, 10, false, true, IMob.a_));
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
   }

   @Override
   public boolean canAttackClass(Class<? extends EntityLivingBase> var1) {
      return this.isPlayerCreated() && EntityPlayer.class.isAssignableFrom(var1) ? false : (var1 == EntityCreeper.class ? false : super.canAttackClass(var1));
   }

   public int getHoldRoseTick() {
      return this.holdRoseTick;
   }

   @Override
   public String getDeathSound() {
      return "mob.irongolem.death";
   }

   @Override
   public int decreaseAirSupply(int var1) {
      return var1;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("PlayerCreated", this.isPlayerCreated());
   }
}
