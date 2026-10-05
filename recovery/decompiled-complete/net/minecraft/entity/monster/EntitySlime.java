package net.minecraft.entity.monster;

import io.netty.handler.codec.ByteToMessageCodec$Encoder;
import net.minecraft.client.gui.GuiPageButtonList$GuiEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class EntitySlime extends EntityLiving implements IMob {
   public float squishFactor;
   public float squishAmount;
   public float prevSquishFactor;
   public boolean wasOnGround;
   public GuiPageButtonList$GuiEntry field_0003;
   public EntityGhast$AIRandomFly field_0002;
   public ByteToMessageCodec$Encoder field_0006;

   public EnumParticleTypes getParticleType() {
      return EnumParticleTypes.SLIME;
   }

   public boolean makesSoundOnJump() {
      return this.getSlimeSize() > 0;
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, (byte)1);
   }

   @Override
   public void onDataWatcherUpdate(int var1) {
      if (var1 == 16) {
         int var2 = this.getSlimeSize();
         this.setSize(0.51000005F * var2, 0.51000005F * var2);
         this.y = this.aK;
         this.aI = this.aK;
         if (this.V() && this.V.nextInt(20) == 0) {
            this.X();
         }
      }

      super.onDataWatcherUpdate(var1);
   }

   @Override
   public boolean getCanSpawnHere() {
      BlockPos var1 = new BlockPos(MathHelper.floor_double(this.s), 0, MathHelper.floor_double(this.u));
      Chunk var2 = this.o.getChunkFromBlockCoords(var1);
      if (this.o.P().getTerrainType() == WorldType.FLAT && this.V.nextInt(4) != 1) {
         return false;
      } else {
         if (this.o.getDifficulty() != EnumDifficulty.PEACEFUL) {
            BiomeGenBase var3 = this.o.getBiomeGenForCoords(var1);
            if (var3 == BiomeGenBase.swampland
               && this.t > 50.0
               && this.t < 70.0
               && this.V.nextFloat() < 0.5F
               && this.V.nextFloat() < this.o.getCurrentMoonPhaseFactor()
               && this.o.getLightFromNeighbors(new BlockPos(this)) <= this.V.nextInt(8)) {
               return super.getCanSpawnHere();
            }

            if (this.V.nextInt(10) == 0 && var2.getRandomWithSeed(987251295L & -8936120331346113665L).nextInt(10) == 0 && this.t < 40.0) {
               return super.getCanSpawnHere();
            }
         }

         return false;
      }
   }

   public EntitySlime(World var1) {
      super(var1);
      this.f = new EntitySlime$SlimeMoveHelper(this);
      this.i.addTask(1, new EntitySlime$AISlimeFloat(this));
      this.i.addTask(2, new EntitySlime$AISlimeAttack(this));
      this.i.addTask(3, new EntitySlime$AISlimeFaceRandom(this));
      this.i.addTask(5, new EntitySlime$AISlimeHop(this));
      this.bi.addTask(1, new EntityAIFindEntityNearestPlayer(this));
      this.bi.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
   }

   @Override
   public void applyEntityCollision(Entity var1) {
      super.applyEntityCollision(var1);
      if (var1 instanceof EntityIronGolem && this.canDamagePlayer()) {
         this.func_175451_e((EntityLivingBase)var1);
      }
   }

   public String getJumpSound() {
      return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
   }

   @Override
   public void b_(EntityPlayer var1) {
      if (this.canDamagePlayer()) {
         this.func_175451_e(var1);
      }
   }

   @Override
   public float getEyeHeight() {
      return 0.625F * this.K;
   }

   @Override
   public void setDead() {
      int var1 = this.getSlimeSize();
      if (!this.o.D && var1 > 1 && this.getHealth() <= 0.0F) {
         int var2 = 2 + this.V.nextInt(3);

         for (int var3 = 0; var3 < var2; var3++) {
            float var4 = (var3 % 2 - 0.5F) * var1 / 4.0F;
            float var5 = (var3 / 2 - 0.5F) * var1 / 4.0F;
            EntitySlime var6 = this.createInstance();
            if (this.u_()) {
               var6.a(this.aM());
            }

            if (this.isNoDespawnRequired()) {
               var6.enablePersistence();
            }

            var6.setSlimeSize(var1 / 2);
            var6.a_(this.s + var4, this.t + 0.5, this.u + var5, this.V.nextFloat() * 360.0F, 0.0F);
            this.o.spawnEntityInWorld(var6);
         }
      }

      super.setDead();
   }

   public EntitySlime createInstance() {
      return new EntitySlime(this.o);
   }

   public void func_175451_e(EntityLivingBase var1) {
      int var2 = this.getSlimeSize();
      if (this.t(var1) && this.h(var1) < 0.6 * var2 * 0.6 * var2 && var1.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength())) {
         this.playSound("mob.attack", 1.0F, (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
         this.applyEnchantments(this, var1);
      }
   }

   public void alterSquishAmount() {
      this.squishAmount *= 0.6F;
   }

   @Override
   public void jump() {
      this.w = 0.42F;
      this.ai = true;
   }

   public boolean makesSoundOnLand() {
      return this.getSlimeSize() > 2;
   }

   public int getSlimeSize() {
      return this.ac.getWatchableObjectByte(16);
   }

   @Override
   public float getSoundVolume() {
      return 0.4F * this.getSlimeSize();
   }

   @Override
   public String getDeathSound() {
      return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
   }

   @Override
   public void onUpdate() {
      if (!this.o.D && this.o.getDifficulty() == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0) {
         this.I = true;
      }

      this.squishFactor = this.squishFactor + (this.squishAmount - this.squishFactor) * 0.5F;
      this.prevSquishFactor = this.squishFactor;
      super.onUpdate();
      if (this.C && !this.wasOnGround) {
         int var1 = this.getSlimeSize();

         for (int var2 = 0; var2 < var1 * 8; var2++) {
            float var3 = this.V.nextFloat() * (float) Math.PI * 2.0F;
            float var4 = this.V.nextFloat() * 0.5F + 0.5F;
            float var5 = MathHelper.sin(var3) * var1 * 0.5F * var4;
            float var6 = MathHelper.cos(var3) * var1 * 0.5F * var4;
            World var7 = this.o;
            EnumParticleTypes var8 = this.getParticleType();
            double var9 = this.s + var5;
            double var11 = this.u + var6;
            var7.spawnParticle(var8, var9, this.getEntityBoundingBox().b, var11, 0.0, 0.0, 0.0);
         }

         if (this.makesSoundOnLand()) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F) / 0.8F);
         }

         this.squishAmount = -0.5F;
      } else if (!this.C && this.wasOnGround) {
         this.squishAmount = 1.0F;
      }

      this.wasOnGround = this.C;
      this.alterSquishAmount();
   }

   @Override
   public String getHurtSound() {
      return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
   }

   public int getAttackStrength() {
      return this.getSlimeSize();
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData var2) {
      int var3 = this.V.nextInt(3);
      if (var3 < 2 && this.V.nextFloat() < 0.5F * var1.getClampedAdditionalDifficulty()) {
         var3++;
      }

      int var4 = 1 << var3;
      this.setSlimeSize(var4);
      return super.onInitialSpawn(var1, var2);
   }

   public int getJumpDelay() {
      return this.V.nextInt(20) + 10;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      int var2 = var1.getInteger("Size");
      if (var2 < 0) {
         var2 = 0;
      }

      this.setSlimeSize(var2 + 1);
      this.wasOnGround = var1.getBoolean("wasOnGround");
   }

   public void setSlimeSize(int var1) {
      this.ac.updateObject(16, (byte)var1);
      this.setSize(0.51000005F * var1, 0.51000005F * var1);
      this.b(this.s, this.t, this.u);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(var1 * var1);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F + 0.1F * var1);
      this.setHealth(this.getMaxHealth());
      this.b_ = var1;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("Size", this.getSlimeSize() - 1);
      var1.setBoolean("wasOnGround", this.wasOnGround);
   }

   @Override
   public int getVerticalFaceSpeed() {
      return 0;
   }

   @Override
   public Item getDropItem() {
      return this.getSlimeSize() == 1 ? Items.slime_ball : null;
   }

   public boolean canDamagePlayer() {
      return this.getSlimeSize() > 1;
   }
}
