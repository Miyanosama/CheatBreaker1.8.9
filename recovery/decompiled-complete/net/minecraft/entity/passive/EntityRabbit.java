package net.minecraft.entity.passive;

import com.cheatbreaker.client.ui.fading.AbstractFade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderSettings$Factory;

public class EntityRabbit extends EntityAnimal {
   public EntityMinecart field_0005;
   public EntityRabbit$EnumMoveType moveType;
   public EntityPlayer field_175543_bt;
   public boolean field_175537_bp;
   public int field_175535_bn;
   public int carrotTicks;
   public EntityRabbit$AIAvoidEntity<EntityWolf> aiAvoidWolves;
   public int field_175540_bm = 0;
   public ChunkProviderSettings$Factory field_0008;
   public boolean field_175536_bo;
   public BlockBrewingStand field_0004;
   public int currentMoveTypeDuration;
   public AbstractFade field_0012;

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setRabbitType(var1.getInteger("RabbitType"));
      this.carrotTicks = var1.getInteger("MoreCarrotTicks");
   }

   @Override
   public void updateAITasks() {
      if (this.f.getSpeed() > 0.8) {
         this.setMoveType(EntityRabbit$EnumMoveType.SPRINT);
      } else if (this.moveType != EntityRabbit$EnumMoveType.ATTACK) {
         this.setMoveType(EntityRabbit$EnumMoveType.HOP);
      }

      if (this.currentMoveTypeDuration > 0) {
         this.currentMoveTypeDuration--;
      }

      if (this.carrotTicks > 0) {
         this.carrotTicks = this.carrotTicks - this.V.nextInt(3);
         if (this.carrotTicks < 0) {
            this.carrotTicks = 0;
         }
      }

      if (this.C) {
         if (!this.field_175537_bp) {
            this.setJumping(false, EntityRabbit$EnumMoveType.NONE);
            this.func_175517_cu();
         }

         if (this.getRabbitType() == 99 && this.currentMoveTypeDuration == 0) {
            EntityLivingBase var1 = this.getAttackTarget();
            if (var1 != null && this.h(var1) < 16.0) {
               this.calculateRotationYaw(var1.s, var1.u);
               this.f.setMoveTo(var1.s, var1.t, var1.u, this.f.getSpeed());
               this.doMovementAction(EntityRabbit$EnumMoveType.ATTACK);
               this.field_175537_bp = true;
            }
         }

         EntityRabbit$RabbitJumpHelper var4 = (EntityRabbit$RabbitJumpHelper)this.g;
         if (!var4.getIsJumping()) {
            if (this.f.isUpdating() && this.currentMoveTypeDuration == 0) {
               PathEntity var2 = this.h.getPath();
               Vec3 var3 = new Vec3(this.f.getX(), this.f.getY(), this.f.getZ());
               if (var2 != null && var2.getCurrentPathIndex() < var2.getCurrentPathLength()) {
                  var3 = var2.getPosition(this);
               }

               this.calculateRotationYaw(var3.xCoord, var3.zCoord);
               this.doMovementAction(this.moveType);
            }
         } else if (!var4.func_180065_d()) {
            this.func_175518_cr();
         }
      }

      this.field_175537_bp = this.C;
   }

   public void func_175518_cr() {
      ((EntityRabbit$RabbitJumpHelper)this.g).func_180066_a(true);
   }

   @Override
   public String getDeathSound() {
      return "mob.rabbit.death";
   }

   public void doMovementAction(EntityRabbit$EnumMoveType var1) {
      this.setJumping(true, var1);
      this.field_175535_bn = var1.func_180073_d();
      this.field_175540_bm = 0;
   }

   @Override
   public void addRandomDrop() {
      this.a(new ItemStack(Items.rabbit_foot, 1), 0.0F);
   }

   @Override
   public String getHurtSound() {
      return "mob.rabbit.hurt";
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData var2) {
      var2 = super.onInitialSpawn(var1, var2);
      int var3 = this.V.nextInt(6);
      boolean var4 = false;
      if (var2 instanceof EntityRabbit$RabbitTypeData) {
         var3 = ((EntityRabbit$RabbitTypeData)var2).typeData;
         var4 = true;
      } else {
         var2 = new EntityRabbit$RabbitTypeData(var3);
      }

      this.setRabbitType(var3);
      if (var4) {
         this.setGrowingAge(-24000);
      }

      return var2;
   }

   public EntityRabbit(World var1) {
      super(var1);
      this.field_175535_bn = 0;
      this.field_175536_bo = false;
      this.field_175537_bp = false;
      this.currentMoveTypeDuration = 0;
      this.moveType = EntityRabbit$EnumMoveType.HOP;
      this.carrotTicks = 0;
      this.field_175543_bt = null;
      this.setSize(0.6F, 0.7F);
      this.g = new EntityRabbit$RabbitJumpHelper(this, this);
      this.f = new EntityRabbit$RabbitMoveHelper(this);
      ((PathNavigateGround)this.s()).setAvoidsWater(true);
      this.h.setHeightRequirement(2.5F);
      this.i.addTask(1, new EntityAISwimming(this));
      this.i.addTask(1, new EntityRabbit$AIPanic(this, 1.33));
      this.i.addTask(2, new EntityAITempt(this, 1.0, Items.carrot, false));
      this.i.addTask(2, new EntityAITempt(this, 1.0, Items.golden_carrot, false));
      this.i.addTask(2, new EntityAITempt(this, 1.0, Item.getItemFromBlock(Blocks.yellow_flower), false));
      this.i.addTask(3, new EntityAIMate(this, 0.8));
      this.i.addTask(5, new EntityRabbit$AIRaidFarm(this));
      this.i.addTask(5, new EntityAIWander(this, 0.6));
      this.i.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
      this.aiAvoidWolves = new EntityRabbit$AIAvoidEntity<>(this, EntityWolf.class, 16.0F, 1.33, 1.33);
      this.i.addTask(4, this.aiAvoidWolves);
      this.setMovementSpeed(0.0);
   }

   public int getMoveTypeDuration() {
      return this.moveType.getDuration();
   }

   public void createEatingParticles() {
      this.o
         .spawnParticle(
            EnumParticleTypes.BLOCK_DUST,
            this.s + this.V.nextFloat() * this.J * 2.0F - this.J,
            this.t + 0.5 + this.V.nextFloat() * this.K,
            this.u + this.V.nextFloat() * this.J * 2.0F - this.J,
            0.0,
            0.0,
            0.0,
            Block.getStateId(Blocks.carrots.getStateFromMeta(7))
         );
      this.carrotTicks = 100;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
   }

   public EntityRabbit createChild(EntityAgeable var1) {
      EntityRabbit var2 = new EntityRabbit(this.o);
      if (var1 instanceof EntityRabbit) {
         var2.setRabbitType(this.V.nextBoolean() ? this.getRabbitType() : ((EntityRabbit)var1).getRabbitType());
      }

      return var2;
   }

   public void setMovementSpeed(double var1) {
      this.s().setSpeed(var1);
      this.f.setMoveTo(this.f.getX(), this.f.getY(), this.f.getZ(), var1);
   }

   public void setRabbitType(int var1) {
      if (var1 == 99) {
         this.i.removeTask(this.aiAvoidWolves);
         this.i.addTask(4, new EntityRabbit$AIEvilAttack(this));
         this.bi.addTask(1, new EntityAIHurtByTarget(this, false));
         this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
         this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityWolf.class, true));
         if (!this.u_()) {
            this.a(StatCollector.translateToLocal("entity.KillerBunny.name"));
         }
      }

      this.ac.updateObject(18, (byte)var1);
   }

   @Override
   public boolean attackEntityAsMob(Entity var1) {
      if (this.getRabbitType() == 99) {
         this.playSound("mob.attack", 1.0F, (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
         return var1.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
      } else {
         return var1.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
      }
   }

   public boolean func_175523_cj() {
      return this.field_175536_bo;
   }

   @Override
   public String getLivingSound() {
      return "mob.rabbit.idle";
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 1) {
         this.Z();
         this.field_175535_bn = 10;
         this.field_175540_bm = 0;
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("RabbitType", this.getRabbitType());
      var1.setInteger("MoreCarrotTicks", this.carrotTicks);
   }

   public boolean isCarrotEaten() {
      return this.carrotTicks == 0;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return this.isEntityInvulnerable(var1) ? false : super.attackEntityFrom(var1, var2);
   }

   @Override
   public float getJumpUpwardsMotion() {
      return this.f.isUpdating() && this.f.getY() > this.t + 0.5 ? 0.5F : this.moveType.func_180074_b();
   }

   public void setJumping(boolean var1, EntityRabbit$EnumMoveType var2) {
      super.i(var1);
      if (!var1) {
         if (this.moveType == EntityRabbit$EnumMoveType.ATTACK) {
            this.moveType = EntityRabbit$EnumMoveType.HOP;
         }
      } else {
         this.setMovementSpeed(1.5 * var2.getSpeed());
         this.playSound(this.getJumpingSound(), this.getSoundVolume(), ((this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F) * 0.8F);
      }

      this.field_175536_bo = var1;
   }

   public void calculateRotationYaw(double var1, double var3) {
      this.y = (float)(MathHelper.atan2(var3 - this.u, var1 - this.s) * 180.0 / Math.PI) - 90.0F;
   }

   public String getJumpingSound() {
      return "mob.rabbit.hop";
   }

   public int getRabbitType() {
      return this.ac.getWatchableObjectByte(18);
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.field_175540_bm != this.field_175535_bn) {
         if (this.field_175540_bm == 0 && !this.o.D) {
            this.o.setEntityState(this, (byte)1);
         }

         this.field_175540_bm++;
      } else if (this.field_175535_bn != 0) {
         this.field_175540_bm = 0;
         this.field_175535_bn = 0;
      }
   }

   public float func_175521_o(float var1) {
      return this.field_175535_bn == 0 ? 0.0F : (this.field_175540_bm + var1) / this.field_175535_bn;
   }

   @Override
   public int getTotalArmorValue() {
      return this.getRabbitType() == 99 ? 8 : super.getTotalArmorValue();
   }

   public boolean isRabbitBreedingItem(Item var1) {
      return var1 == Items.carrot || var1 == Items.golden_carrot || var1 == Item.getItemFromBlock(Blocks.yellow_flower);
   }

   public void setMoveType(EntityRabbit$EnumMoveType var1) {
      this.moveType = var1;
   }

   public void func_175517_cu() {
      this.updateMoveTypeDuration();
      this.func_175520_cs();
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(18, (byte)0);
   }

   public void updateMoveTypeDuration() {
      this.currentMoveTypeDuration = this.getMoveTypeDuration();
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(2) + this.V.nextInt(1 + var2);

      for (int var4 = 0; var4 < var3; var4++) {
         this.dropItem(Items.rabbit_hide, 1);
      }

      var3 = this.V.nextInt(2);

      for (int var6 = 0; var6 < var3; var6++) {
         if (this.isBurning()) {
            this.dropItem(Items.cooked_rabbit, 1);
         } else {
            this.dropItem(Items.rabbit, 1);
         }
      }
   }

   @Override
   public boolean isBreedingItem(ItemStack var1) {
      return var1 != null && this.isRabbitBreedingItem(var1.getItem());
   }

   @Override
   public void spawnRunningParticles() {
   }

   public void func_175520_cs() {
      ((EntityRabbit$RabbitJumpHelper)this.g).func_180066_a(false);
   }
}
