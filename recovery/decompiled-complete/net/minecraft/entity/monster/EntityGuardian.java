package net.minecraft.entity.monster;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood$FishType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentStyle$2;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public class EntityGuardian extends EntityMob {
   public float field_175482_b;
   public float field_175483_bk;
   public EntityAIWander wander;
   public float field_175485_bl;
   public float field_175486_bm;
   public int field_175479_bo;
   public float field_175484_c;
   public boolean field_175480_bp;
   public EntityLivingBase targetedEntity;
   public ChatComponentStyle$2 field_0003;

   public EntityLivingBase getTargetedEntity() {
      if (!this.hasTargetedEntity()) {
         return null;
      } else if (this.o.D) {
         if (this.targetedEntity != null) {
            return this.targetedEntity;
         } else {
            Entity var1 = this.o.getEntityByID(this.ac.getWatchableObjectInt(17));
            if (var1 instanceof EntityLivingBase) {
               this.targetedEntity = (EntityLivingBase)var1;
               return this.targetedEntity;
            } else {
               return null;
            }
         }
      } else {
         return this.getAttackTarget();
      }
   }

   public boolean hasTargetedEntity() {
      return this.ac.getWatchableObjectInt(17) != 0;
   }

   @Override
   public String getHurtSound() {
      return !this.V() ? "mob.guardian.land.hit" : (this.isElder() ? "mob.guardian.elder.hit" : "mob.guardian.hit");
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (!this.func_175472_n() && !var1.isMagicDamage() && var1.getSourceOfDamage() instanceof EntityLivingBase) {
         EntityLivingBase var3 = (EntityLivingBase)var1.getSourceOfDamage();
         if (!var1.isExplosion()) {
            var3.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
            var3.playSound("damage.thorns", 0.5F, 1.0F);
         }
      }

      this.wander.makeUpdate();
      return super.attackEntityFrom(var1, var2);
   }

   public boolean func_175472_n() {
      return this.isSyncedFlagSet(2);
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(3) + this.V.nextInt(var2 + 1);
      if (var3 > 0) {
         this.a(new ItemStack(Items.prismarine_shard, var3, 0), 1.0F);
      }

      if (this.V.nextInt(3 + var2) > 1) {
         this.a(new ItemStack(Items.fish, 1, ItemFishFood$FishType.COD.getMetadata()), 1.0F);
      } else if (this.V.nextInt(3 + var2) > 1) {
         this.a(new ItemStack(Items.prismarine_crystals, 1, 0), 1.0F);
      }

      if (var1 && this.isElder()) {
         this.a(new ItemStack(Blocks.sponge, 1, 1), 1.0F);
      }
   }

   @Override
   public boolean l_() {
      return false;
   }

   @Override
   public float getBlockPathWeight(BlockPos var1) {
      return this.o.getBlockState(var1).getBlock().getMaterial() == Material.water ? 10.0F + this.o.o(var1) - 0.5F : super.getBlockPathWeight(var1);
   }

   public float func_175469_o(float var1) {
      return this.field_175486_bm + (this.field_175485_bl - this.field_175486_bm) * var1;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
   }

   @Override
   public String getLivingSound() {
      return !this.V() ? "mob.guardian.land.idle" : (this.isElder() ? "mob.guardian.elder.idle" : "mob.guardian.idle");
   }

   @Override
   public void addRandomDrop() {
      ItemStack var1 = WeightedRandom.getRandomItem(this.V, EntityFishHook.func_174855_j()).getItemStack(this.V);
      this.a(var1, 1.0F);
   }

   @Override
   public void onDataWatcherUpdate(int var1) {
      super.onDataWatcherUpdate(var1);
      if (var1 == 16) {
         if (this.isElder() && this.J < 1.0F) {
            this.setSize(1.9975F, 1.9975F);
         }
      } else if (var1 == 17) {
         this.field_175479_bo = 0;
         this.targetedEntity = null;
      }
   }

   @Override
   public int getVerticalFaceSpeed() {
      return 180;
   }

   @Override
   public void moveEntityWithHeading(float var1, float var2) {
      if (this.isServerWorld()) {
         if (this.V()) {
            this.a(var1, var2, 0.1F);
            this.d(this.v, this.w, this.x);
            this.v *= 0.9F;
            this.w *= 0.9F;
            this.x *= 0.9F;
            if (!this.func_175472_n() && this.getAttackTarget() == null) {
               this.w -= 0.005;
            }
         } else {
            super.moveEntityWithHeading(var1, var2);
         }
      } else {
         super.moveEntityWithHeading(var1, var2);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("Elder", this.isElder());
   }

   public EntityGuardian(World var1) {
      super(var1);
      this.b_ = 10;
      this.setSize(0.85F, 0.85F);
      this.i.addTask(4, new EntityGuardian$AIGuardianAttack(this));
      EntityAIMoveTowardsRestriction var2;
      this.i.addTask(5, var2 = new EntityAIMoveTowardsRestriction(this, 1.0));
      this.i.addTask(7, this.wander = new EntityAIWander(this, 1.0, 80));
      this.i.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(8, new EntityAIWatchClosest(this, EntityGuardian.class, 12.0F, 0.01F));
      this.i.addTask(9, new EntityAILookIdle(this));
      this.wander.setMutexBits(3);
      var2.setMutexBits(3);
      this.bi.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, 10, true, false, new EntityGuardian$GuardianTargetSelector(this)));
      this.f = new EntityGuardian$GuardianMoveHelper(this);
      this.field_175484_c = this.field_175482_b = this.V.nextFloat();
   }

   public void setTargetedEntity(int var1) {
      this.ac.updateObject(17, var1);
   }

   public void func_175476_l(boolean var1) {
      this.setSyncedFlag(2, var1);
   }

   public void setSyncedFlag(int var1, boolean var2) {
      int var3 = this.ac.getWatchableObjectInt(16);
      if (var2) {
         this.ac.updateObject(16, var3 | var1);
      } else {
         this.ac.updateObject(16, var3 & ~var1);
      }
   }

   public boolean isSyncedFlagSet(int var1) {
      return (this.ac.getWatchableObjectInt(16) & var1) != 0;
   }

   public int func_175464_ck() {
      return this.isElder() ? 60 : 80;
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, 0);
      this.ac.addObject(17, 0);
   }

   @Override
   public void onLivingUpdate() {
      if (this.o.D) {
         this.field_175484_c = this.field_175482_b;
         if (!this.V()) {
            this.field_175483_bk = 2.0F;
            if (this.w > 0.0 && this.field_175480_bp && !this.R()) {
               this.o.playSound(this.s, this.t, this.u, "mob.guardian.flop", 1.0F, 1.0F, false);
            }

            this.field_175480_bp = this.w < 0.0 && this.o.isBlockNormalCube(new BlockPos(this).down(), false);
         } else if (this.func_175472_n()) {
            if (this.field_175483_bk < 0.5F) {
               this.field_175483_bk = 4.0F;
            } else {
               this.field_175483_bk = this.field_175483_bk + (0.5F - this.field_175483_bk) * 0.1F;
            }
         } else {
            this.field_175483_bk = this.field_175483_bk + (0.125F - this.field_175483_bk) * 0.2F;
         }

         this.field_175482_b = this.field_175482_b + this.field_175483_bk;
         this.field_175486_bm = this.field_175485_bl;
         if (!this.V()) {
            this.field_175485_bl = this.V.nextFloat();
         } else if (this.func_175472_n()) {
            this.field_175485_bl = this.field_175485_bl + (0.0F - this.field_175485_bl) * 0.25F;
         } else {
            this.field_175485_bl = this.field_175485_bl + (1.0F - this.field_175485_bl) * 0.06F;
         }

         if (this.func_175472_n() && this.V()) {
            Vec3 var1 = this.getLook(0.0F);

            for (int var2 = 0; var2 < 2; var2++) {
               this.o
                  .spawnParticle(
                     EnumParticleTypes.WATER_BUBBLE,
                     this.s + (this.V.nextDouble() - 0.5) * this.J - var1.xCoord * 1.5,
                     this.t + this.V.nextDouble() * this.K - var1.yCoord * 1.5,
                     this.u + (this.V.nextDouble() - 0.5) * this.J - var1.zCoord * 1.5,
                     0.0,
                     0.0,
                     0.0
                  );
            }
         }

         if (this.hasTargetedEntity()) {
            if (this.field_175479_bo < this.func_175464_ck()) {
               this.field_175479_bo++;
            }

            EntityLivingBase var14 = this.getTargetedEntity();
            if (var14 != null) {
               this.getLookHelper().setLookPositionWithEntity(var14, 90.0F, 90.0F);
               this.getLookHelper().onUpdateLook();
               double var15 = this.func_175477_p(0.0F);
               double var4 = var14.s - this.s;
               double var6 = var14.t + var14.K * 0.5F - (this.t + this.getEyeHeight());
               double var8 = var14.u - this.u;
               double var10 = Math.sqrt(var4 * var4 + var6 * var6 + var8 * var8);
               var4 /= var10;
               var6 /= var10;
               var8 /= var10;
               double var12 = this.V.nextDouble();

               while (var12 < var10) {
                  var12 += 1.8 - var15 + this.V.nextDouble() * (1.7 - var15);
                  this.o
                     .spawnParticle(
                        EnumParticleTypes.WATER_BUBBLE,
                        this.s + var4 * var12,
                        this.t + var6 * var12 + this.getEyeHeight(),
                        this.u + var8 * var12,
                        0.0,
                        0.0,
                        0.0
                     );
               }
            }
         }
      }

      if (this.Y) {
         this.setAir(300);
      } else if (this.C) {
         this.w += 0.5;
         this.v = this.v + (this.V.nextFloat() * 2.0F - 1.0F) * 0.4F;
         this.x = this.x + (this.V.nextFloat() * 2.0F - 1.0F) * 0.4F;
         this.y = this.V.nextFloat() * 360.0F;
         this.C = false;
         this.ai = true;
      }

      if (this.hasTargetedEntity()) {
         this.y = this.aK;
      }

      super.onLivingUpdate();
   }

   @Override
   public String getDeathSound() {
      return !this.V() ? "mob.guardian.land.death" : (this.isElder() ? "mob.guardian.elder.death" : "mob.guardian.death");
   }

   public float func_175471_a(float var1) {
      return this.field_175484_c + (this.field_175482_b - this.field_175484_c) * var1;
   }

   @Override
   public boolean getCanSpawnHere() {
      return (this.V.nextInt(20) == 0 || !this.o.canBlockSeeSky(new BlockPos(this))) && super.getCanSpawnHere();
   }

   @Override
   public float getEyeHeight() {
      return this.K * 0.5F;
   }

   public void setElder() {
      this.setElder(true);
      this.field_175486_bm = this.field_175485_bl = 1.0F;
   }

   public void setElder(boolean var1) {
      this.setSyncedFlag(4, var1);
      if (var1) {
         this.setSize(1.9975F, 1.9975F);
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
         this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
         this.enablePersistence();
         this.wander.setExecutionChance(400);
      }
   }

   public float func_175477_p(float var1) {
      return (this.field_175479_bo + var1) / this.func_175464_ck();
   }

   @Override
   public boolean A_() {
      return true;
   }

   @Override
   public PathNavigate getNewNavigator(World var1) {
      return new PathNavigateSwimmer(this, var1);
   }

   @Override
   public int getTalkInterval() {
      return 160;
   }

   public boolean isElder() {
      return this.isSyncedFlagSet(4);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setElder(var1.getBoolean("Elder"));
   }

   @Override
   public boolean isNotColliding() {
      return this.o.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.o.a(this, this.getEntityBoundingBox()).isEmpty();
   }

   @Override
   public void updateAITasks() {
      super.updateAITasks();
      if (this.isElder()) {
         short var1 = 1200;
         short var2 = 1200;
         short var3 = 6000;
         byte var4 = 2;
         if ((this.W + this.F()) % 1200 == 0) {
            Potion var5 = Potion.digSlowdown;

            for (EntityPlayerMP var7 : this.o.method_10010(EntityPlayerMP.class, new EntityGuardian$1(this))) {
               if (!var7.isPotionActive(var5) || var7.getActivePotionEffect(var5).getAmplifier() < 2 || var7.getActivePotionEffect(var5).getDuration() < 1200) {
                  var7.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(10, 0.0F));
                  var7.c(new PotionEffect(var5.id, 6000, 2));
               }
            }
         }

         if (!this.hasHome()) {
            this.setHomePosAndDistance(new BlockPos(this), 16);
         }
      }
   }
}
