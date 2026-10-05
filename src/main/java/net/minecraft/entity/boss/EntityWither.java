package net.minecraft.entity.boss;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityWither extends EntityMob implements IBossDisplayData, IRangedAttackMob {
   public float[] field_82217_f;
   public float[] field_82220_d = new float[2];
   public static Predicate<Entity> attackEntitySelector = new Predicate<Entity>() {
      public boolean apply(Entity var1) {
         return var1 instanceof EntityLivingBase && ((EntityLivingBase)var1).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;
      }
   };
   public int[] field_82223_h;
   public int[] field_82224_i;
   public float[] field_82221_e = new float[2];
   public int blockBreakCounter;
   public float[] field_82218_g;

   @Override
   public void updateAITasks() {
      if (this.getInvulTime() > 0) {
         int var1 = this.getInvulTime() - 1;
         if (var1 <= 0) {
            this.o.newExplosion(this, this.s, this.t + this.getEyeHeight(), this.u, 7.0F, false, this.o.Q().getBoolean("mobGriefing"));
            this.o.playBroadcastSound(1013, new BlockPos(this), 0);
         }

         this.setInvulTime(var1);
         if (this.W % 10 == 0) {
            this.heal(10.0F);
         }
      } else {
         super.updateAITasks();

         for (int var13 = 1; var13 < 3; var13++) {
            if (this.W >= this.field_82223_h[var13 - 1]) {
               this.field_82223_h[var13 - 1] = this.W + 10 + this.V.nextInt(10);
               if (this.o.getDifficulty() == EnumDifficulty.NORMAL || this.o.getDifficulty() == EnumDifficulty.HARD) {
                  int var2 = var13 - 1;
                  int var3 = this.field_82224_i[var13 - 1];
                  this.field_82224_i[var2] = this.field_82224_i[var13 - 1] + 1;
                  if (var3 > 15) {
                     float var4 = 10.0F;
                     float var5 = 5.0F;
                     double var6 = MathHelper.getRandomDoubleInRange(this.V, this.s - var4, this.s + var4);
                     double var8 = MathHelper.getRandomDoubleInRange(this.V, this.t - var5, this.t + var5);
                     double var10 = MathHelper.getRandomDoubleInRange(this.V, this.u - var4, this.u + var4);
                     this.launchWitherSkullToCoords(var13 + 1, var6, var8, var10, true);
                     this.field_82224_i[var13 - 1] = 0;
                  }
               }

               int var15 = this.getWatchedTargetId(var13);
               if (var15 > 0) {
                  Entity var18 = this.o.getEntityByID(var15);
                  if (var18 == null || !var18.isEntityAlive() || !(this.h(var18) <= 900.0) || !this.t(var18)) {
                     this.updateWatchedTargetId(var13, 0);
                  } else if (var18 instanceof EntityPlayer && ((EntityPlayer)var18).bA.disableDamage) {
                     this.updateWatchedTargetId(var13, 0);
                  } else {
                     this.launchWitherSkullToEntity(var13 + 1, (EntityLivingBase)var18);
                     this.field_82223_h[var13 - 1] = this.W + 40 + this.V.nextInt(20);
                     this.field_82224_i[var13 - 1] = 0;
                  }
               } else {
                  List var17 = this.o
                     .getEntitiesWithinAABB(
                        EntityLivingBase.class,
                        this.getEntityBoundingBox().expand(20.0, 8.0, 20.0),
                        Predicates.and(attackEntitySelector, EntitySelectors.NOT_SPECTATING)
                     );

                  for (int var20 = 0; var20 < 10 && !var17.isEmpty(); var20++) {
                     EntityLivingBase var22 = (EntityLivingBase)var17.get(this.V.nextInt(var17.size()));
                     if (var22 != this && var22.isEntityAlive() && this.t(var22)) {
                        if (var22 instanceof EntityPlayer) {
                           if (!((EntityPlayer)var22).bA.disableDamage) {
                              this.updateWatchedTargetId(var13, var22.F());
                           }
                        } else {
                           this.updateWatchedTargetId(var13, var22.F());
                        }
                        break;
                     }

                     var17.remove(var22);
                  }
               }
            }
         }

         if (this.getAttackTarget() != null) {
            this.updateWatchedTargetId(0, this.getAttackTarget().F());
         } else {
            this.updateWatchedTargetId(0, 0);
         }

         if (this.blockBreakCounter > 0) {
            this.blockBreakCounter--;
            if (this.blockBreakCounter == 0 && this.o.Q().getBoolean("mobGriefing")) {
               int var14 = MathHelper.floor_double(this.t);
               int var16 = MathHelper.floor_double(this.s);
               int var19 = MathHelper.floor_double(this.u);
               boolean var21 = false;

               for (int var23 = -1; var23 <= 1; var23++) {
                  for (int var24 = -1; var24 <= 1; var24++) {
                     for (int var7 = 0; var7 <= 3; var7++) {
                        int var25 = var16 + var23;
                        int var9 = var14 + var7;
                        int var26 = var19 + var24;
                        BlockPos var11 = new BlockPos(var25, var9, var26);
                        Block var12 = this.o.getBlockState(var11).getBlock();
                        if (var12.getMaterial() != Material.air && canDestroyBlock(var12)) {
                           var21 = this.o.destroyBlock(var11, true) || var21;
                        }
                     }
                  }
               }

               if (var21) {
                  this.o.playAuxSFXAtEntity((EntityPlayer)null, 1012, new BlockPos(this), 0);
               }
            }
         }

         if (this.W % 20 == 0) {
            this.heal(1.0F);
         }
      }
   }

   public double method_04808(int var1) {
      return var1 <= 0 ? this.t + 3.0 : this.t + 2.2;
   }

   @Override
   public String getHurtSound() {
      return "mob.wither.hurt";
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setInvulTime(var1.getInteger("Invul"));
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if (var1 == DamageSource.drown || var1.getEntity() instanceof EntityWither) {
         return false;
      } else if (this.getInvulTime() > 0 && var1 != DamageSource.outOfWorld) {
         return false;
      } else {
         if (this.isArmored()) {
            Entity var3 = var1.getSourceOfDamage();
            if (var3 instanceof EntityArrow) {
               return false;
            }
         }

         Entity var5 = var1.getEntity();
         if (var5 != null
            && !(var5 instanceof EntityPlayer)
            && var5 instanceof EntityLivingBase
            && ((EntityLivingBase)var5).getCreatureAttribute() == this.getCreatureAttribute()) {
            return false;
         } else {
            if (this.blockBreakCounter <= 0) {
               this.blockBreakCounter = 20;
            }

            for (int var4 = 0; var4 < this.field_82224_i.length; var4++) {
               this.field_82224_i[var4] = this.field_82224_i[var4] + 3;
            }

            return super.attackEntityFrom(var1, var2);
         }
      }
   }

   @Override
   public void onLivingUpdate() {
      this.w *= 0.6F;
      if (!this.o.D && this.getWatchedTargetId(0) > 0) {
         Entity var1 = this.o.getEntityByID(this.getWatchedTargetId(0));
         if (var1 != null) {
            if (this.t < var1.t || !this.isArmored() && this.t < var1.t + 5.0) {
               if (this.w < 0.0) {
                  this.w = 0.0;
               }

               this.w = this.w + (0.5 - this.w) * 0.6F;
            }

            double var2 = var1.s - this.s;
            double var4 = var1.u - this.u;
            double var6 = var2 * var2 + var4 * var4;
            if (var6 > 9.0) {
               double var8 = MathHelper.sqrt_double(var6);
               this.v = this.v + (var2 / var8 * 0.5 - this.v) * 0.6F;
               this.x = this.x + (var4 / var8 * 0.5 - this.x) * 0.6F;
            }
         }
      }

      if (this.v * this.v + this.x * this.x > 0.05F) {
         this.y = (float)MathHelper.atan2(this.x, this.v) * (180.0F / (float)Math.PI) - 90.0F;
      }

      super.onLivingUpdate();

      for (int var20 = 0; var20 < 2; var20++) {
         this.field_82218_g[var20] = this.field_82221_e[var20];
         this.field_82217_f[var20] = this.field_82220_d[var20];
      }

      for (int var21 = 0; var21 < 2; var21++) {
         int var23 = this.getWatchedTargetId(var21 + 1);
         Entity var3 = null;
         if (var23 > 0) {
            var3 = this.o.getEntityByID(var23);
         }

         if (var3 != null) {
            double var27 = this.func_82214_u(var21 + 1);
            double var28 = this.method_04808(var21 + 1);
            double var29 = this.func_82213_w(var21 + 1);
            double var10 = var3.s - var27;
            double var12 = var3.t + var3.getEyeHeight() - var28;
            double var14 = var3.u - var29;
            double var16 = MathHelper.sqrt_double(var10 * var10 + var14 * var14);
            float var18 = (float)(MathHelper.atan2(var14, var10) * 180.0 / Math.PI) - 90.0F;
            float var19 = (float)(-(MathHelper.atan2(var12, var16) * 180.0 / Math.PI));
            this.field_82220_d[var21] = this.func_82204_b(this.field_82220_d[var21], var19, 40.0F);
            this.field_82221_e[var21] = this.func_82204_b(this.field_82221_e[var21], var18, 10.0F);
         } else {
            this.field_82221_e[var21] = this.func_82204_b(this.field_82221_e[var21], this.aI, 10.0F);
         }
      }

      boolean var22 = this.isArmored();

      for (int var24 = 0; var24 < 3; var24++) {
         double var26 = this.func_82214_u(var24);
         double var5 = this.method_04808(var24);
         double var7 = this.func_82213_w(var24);
         this.o
            .spawnParticle(
               EnumParticleTypes.SMOKE_NORMAL,
               var26 + this.V.nextGaussian() * 0.3F,
               var5 + this.V.nextGaussian() * 0.3F,
               var7 + this.V.nextGaussian() * 0.3F,
               0.0,
               0.0,
               0.0
            );
         if (var22 && this.o.s.nextInt(4) == 0) {
            this.o
               .spawnParticle(
                  EnumParticleTypes.SPELL_MOB,
                  var26 + this.V.nextGaussian() * 0.3F,
                  var5 + this.V.nextGaussian() * 0.3F,
                  var7 + this.V.nextGaussian() * 0.3F,
                  0.7F,
                  0.7F,
                  0.5
               );
         }
      }

      if (this.getInvulTime() > 0) {
         for (int var25 = 0; var25 < 3; var25++) {
            this.o
               .spawnParticle(
                  EnumParticleTypes.SPELL_MOB,
                  this.s + this.V.nextGaussian() * 1.0,
                  this.t + this.V.nextFloat() * 3.3F,
                  this.u + this.V.nextGaussian() * 1.0,
                  0.7F,
                  0.7F,
                  0.9F
               );
         }
      }
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      this.launchWitherSkullToEntity(0, var1);
   }

   @Override
   public void c(PotionEffect var1) {
   }

   @Override
   public void setInWeb() {
   }

   public void launchWitherSkullToEntity(int var1, EntityLivingBase var2) {
      this.launchWitherSkullToCoords(var1, var2.s, var2.t + var2.getEyeHeight() * 0.5, var2.u, var1 == 0 && this.V.nextFloat() < 0.001F);
   }

   public float func_82204_b(float var1, float var2, float var3) {
      float var4 = MathHelper.wrapAngleTo180_float(var2 - var1);
      if (var4 > var3) {
         var4 = var3;
      }

      if (var4 < -var3) {
         var4 = -var3;
      }

      return var1 + var4;
   }

   public int getWatchedTargetId(int var1) {
      return this.ac.getWatchableObjectInt(17 + var1);
   }

   public double func_82213_w(int var1) {
      if (var1 <= 0) {
         return this.u;
      } else {
         float var2 = (this.aI + 180 * (var1 - 1)) / 180.0F * (float) Math.PI;
         float var3 = MathHelper.sin(var2);
         return this.u + var3 * 1.3;
      }
   }

   public boolean isArmored() {
      return this.getHealth() <= this.getMaxHealth() / 2.0F;
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public double func_82214_u(int var1) {
      if (var1 <= 0) {
         return this.s;
      } else {
         float var2 = (this.aI + 180 * (var1 - 1)) / 180.0F * (float) Math.PI;
         float var3 = MathHelper.cos(var2);
         return this.s + var3 * 1.3;
      }
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      EntityItem var3 = this.dropItem(Items.nether_star, 1);
      if (var3 != null) {
         var3.setNoDespawn();
      }

      if (!this.o.D) {
         for (EntityPlayer var5 : this.o.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(50.0, 100.0, 50.0))) {
            var5.triggerAchievement(AchievementList.killWither);
         }
      }
   }

   public int getInvulTime() {
      return this.ac.getWatchableObjectInt(20);
   }

   @Override
   public void despawnEntity() {
      this.aQ = 0;
   }

   public void func_82206_m() {
      this.setInvulTime(220);
      this.setHealth(this.getMaxHealth() / 3.0F);
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(17, new Integer(0));
      this.ac.addObject(18, new Integer(0));
      this.ac.addObject(19, new Integer(0));
      this.ac.addObject(20, new Integer(0));
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("Invul", this.getInvulTime());
   }

   public void launchWitherSkullToCoords(int var1, double var2, double var4, double var6, boolean var8) {
      this.o.playAuxSFXAtEntity((EntityPlayer)null, 1014, new BlockPos(this), 0);
      double var9 = this.func_82214_u(var1);
      double var11 = this.method_04808(var1);
      double var13 = this.func_82213_w(var1);
      double var15 = var2 - var9;
      double var17 = var4 - var11;
      double var19 = var6 - var13;
      EntityWitherSkull var21 = new EntityWitherSkull(this.o, this, var15, var17, var19);
      if (var8) {
         var21.setInvulnerable(true);
      }

      var21.t = var11;
      var21.s = var9;
      var21.u = var13;
      this.o.spawnEntityInWorld(var21);
   }

   @Override
   public String getLivingSound() {
      return "mob.wither.idle";
   }

   @Override
   public int b_(float var1) {
      return 15728880;
   }

   public float func_82207_a(int var1) {
      return this.field_82221_e[var1];
   }

   @Override
   public String getDeathSound() {
      return "mob.wither.death";
   }

   public void updateWatchedTargetId(int var1, int var2) {
      this.ac.updateObject(17 + var1, var2);
   }

   @Override
   public void mountEntity(Entity var1) {
      this.m = null;
   }

   public EntityWither(World var1) {
      super(var1);
      this.field_82217_f = new float[2];
      this.field_82218_g = new float[2];
      this.field_82223_h = new int[2];
      this.field_82224_i = new int[2];
      this.setHealth(this.getMaxHealth());
      this.setSize(0.9F, 3.5F);
      this.ab = true;
      ((PathNavigateGround)this.s()).setCanSwim(true);
      this.i.addTask(0, new EntityAISwimming(this));
      this.i.addTask(2, new EntityAIArrowAttack(this, 1.0, 40, 20.0F));
      this.i.addTask(5, new EntityAIWander(this, 1.0));
      this.i.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(7, new EntityAILookIdle(this));
      this.bi.addTask(1, new EntityAIHurtByTarget(this, false));
      this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityLiving.class, 0, false, false, attackEntitySelector));
      this.experienceValue = 50;
   }

   @Override
   public void fall(float var1, float var2) {
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6F);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
   }

   @Override
   public int getTotalArmorValue() {
      return 4;
   }

   public float func_82210_r(int var1) {
      return this.field_82220_d[var1];
   }

   public void setInvulTime(int var1) {
      this.ac.updateObject(20, var1);
   }

   public static boolean canDestroyBlock(Block var0) {
      return var0 != Blocks.bedrock && var0 != Blocks.end_portal && var0 != Blocks.end_portal_frame && var0 != Blocks.command_block && var0 != Blocks.barrier;
   }
}
