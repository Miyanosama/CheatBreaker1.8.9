package net.minecraft.entity.boss;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterSkeleton;

public class EntityDragon extends EntityLiving implements IEntityMultiPart, IBossDisplayData, IMob {
   public boolean field_0011;
   public Entity target;
   public float field_0010;
   public double targetY;
   public boolean field_0002;
   public double targetX;
   public int ringBufferIndex;
   public EntityDragonPart[] dragonPartArray;
   public EntityDragonPart dragonPartWing2;
   public EntityDragonPart dragonPartHead;
   public EntityDragonPart dragonPartTail1;
   public EntityEnderCrystal healingEnderCrystal;
   public ModelAdapterSkeleton field_0013;
   public double targetZ;
   public int deathTicks;
   public EntityDragonPart dragonPartTail3;
   public double[][] ringBuffer = new double[64][3];
   public EntityDragonPart dragonPartBody;
   public EntityDragonPart dragonPartTail2;
   public float field_0009;
   public EntityDragonPart dragonPartWing1;

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
   }

   public boolean destroyBlocksInAABB(AxisAlignedBB var1) {
      int var2 = MathHelper.floor_double(var1.a);
      int var3 = MathHelper.floor_double(var1.b);
      int var4 = MathHelper.floor_double(var1.c);
      int var5 = MathHelper.floor_double(var1.d);
      int var6 = MathHelper.floor_double(var1.e);
      int var7 = MathHelper.floor_double(var1.f);
      boolean var8 = false;
      boolean var9 = false;

      for (int var10 = var2; var10 <= var5; var10++) {
         for (int var11 = var3; var11 <= var6; var11++) {
            for (int var12 = var4; var12 <= var7; var12++) {
               BlockPos var13 = new BlockPos(var10, var11, var12);
               Block var14 = this.o.getBlockState(var13).getBlock();
               if (var14.getMaterial() != Material.air) {
                  if (var14 != Blocks.barrier
                     && var14 != Blocks.obsidian
                     && var14 != Blocks.end_stone
                     && var14 != Blocks.bedrock
                     && var14 != Blocks.command_block
                     && this.o.Q().getBoolean("mobGriefing")) {
                     var9 = this.o.setBlockToAir(var13) || var9;
                  } else {
                     var8 = true;
                  }
               }
            }
         }
      }

      if (var9) {
         double var16 = var1.a + (var1.d - var1.a) * this.V.nextFloat();
         double var17 = var1.b + (var1.e - var1.b) * this.V.nextFloat();
         double var18 = var1.c + (var1.f - var1.c) * this.V.nextFloat();
         this.o.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, var16, var17, var18, 0.0, 0.0, 0.0);
      }

      return var8;
   }

   public EntityDragon(World var1) {
      super(var1);
      this.ringBufferIndex = -1;
      this.dragonPartArray = new EntityDragonPart[]{
         this.dragonPartHead = new EntityDragonPart(this, "head", 6.0F, 6.0F),
         this.dragonPartBody = new EntityDragonPart(this, "body", 8.0F, 8.0F),
         this.dragonPartTail1 = new EntityDragonPart(this, "tail", 4.0F, 4.0F),
         this.dragonPartTail2 = new EntityDragonPart(this, "tail", 4.0F, 4.0F),
         this.dragonPartTail3 = new EntityDragonPart(this, "tail", 4.0F, 4.0F),
         this.dragonPartWing1 = new EntityDragonPart(this, "wing", 4.0F, 4.0F),
         this.dragonPartWing2 = new EntityDragonPart(this, "wing", 4.0F, 4.0F)
      };
      this.setHealth(this.getMaxHealth());
      this.setSize(16.0F, 8.0F);
      this.T = true;
      this.ab = true;
      this.targetY = 100.0;
      this.ah = true;
   }

   public void attackEntitiesInList(List<Entity> var1) {
      for (int var2 = 0; var2 < var1.size(); var2++) {
         Entity var3 = (Entity)var1.get(var2);
         if (var3 instanceof EntityLivingBase) {
            var3.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0F);
            this.applyEnchantments(this, var3);
         }
      }
   }

   @Override
   public float getSoundVolume() {
      return 5.0F;
   }

   public void method_03591() {
      this.field_0002 = false;
      ArrayList var1 = Lists.newArrayList(this.o.j);
      Iterator var2 = var1.iterator();

      while (var2.hasNext()) {
         if (((EntityPlayer)var2.next()).isSpectator()) {
            var2.remove();
         }
      }

      if (this.V.nextInt(2) == 0 && !var1.isEmpty()) {
         this.target = (Entity)var1.get(this.V.nextInt(var1.size()));
      } else {
         boolean var9;
         do {
            this.targetX = 0.0;
            this.targetY = 70.0F + this.V.nextFloat() * 50.0F;
            this.targetZ = 0.0;
            this.targetX = this.targetX + (this.V.nextFloat() * 120.0F - 60.0F);
            this.targetZ = this.targetZ + (this.V.nextFloat() * 120.0F - 60.0F);
            double var3 = this.s - this.targetX;
            double var5 = this.t - this.targetY;
            double var7 = this.u - this.targetZ;
            var9 = var3 * var3 + var5 * var5 + var7 * var7 > 100.0;
         } while (!var9);

         this.target = null;
      }
   }

   @Override
   public void despawnEntity() {
   }

   @Override
   public boolean attackEntityFromPart(EntityDragonPart var1, DamageSource var2, float var3) {
      if (var1 != this.dragonPartHead) {
         var3 = var3 / 4.0F + 1.0F;
      }

      float var4 = this.y * (float) Math.PI / 180.0F;
      float var5 = MathHelper.sin(var4);
      float var6 = MathHelper.cos(var4);
      this.targetX = this.s + var5 * 5.0F + (this.V.nextFloat() - 0.5F) * 2.0F;
      this.targetY = this.t + this.V.nextFloat() * 3.0F + 1.0;
      this.targetZ = this.u - var6 * 5.0F + (this.V.nextFloat() - 0.5F) * 2.0F;
      this.target = null;
      if (var2.getEntity() instanceof EntityPlayer || var2.isExplosion()) {
         this.attackDragonFrom(var2, var3);
      }

      return true;
   }

   public float simplifyAngle(double var1) {
      return (float)MathHelper.wrapAngleTo180_double(var1);
   }

   @Override
   public String getHurtSound() {
      return "mob.enderdragon.hit";
   }

   @Override
   public World getWorld() {
      return this.o;
   }

   public void generatePortal(BlockPos var1) {
      byte var2 = 4;
      double var3 = 12.25;
      double var5 = 6.25;

      for (int var7 = -1; var7 <= 32; var7++) {
         for (int var8 = -4; var8 <= 4; var8++) {
            for (int var9 = -4; var9 <= 4; var9++) {
               double var10 = var8 * var8 + var9 * var9;
               if (var10 <= 12.25) {
                  BlockPos var12 = var1.add(var8, var7, var9);
                  if (var7 < 0) {
                     if (var10 <= 6.25) {
                        this.o.setBlockState(var12, Blocks.bedrock.getDefaultState());
                     }
                  } else if (var7 > 0) {
                     this.o.setBlockState(var12, Blocks.air.getDefaultState());
                  } else if (var10 > 6.25) {
                     this.o.setBlockState(var12, Blocks.bedrock.getDefaultState());
                  } else {
                     this.o.setBlockState(var12, Blocks.end_portal.getDefaultState());
                  }
               }
            }
         }
      }

      this.o.setBlockState(var1, Blocks.bedrock.getDefaultState());
      this.o.setBlockState(var1.up(), Blocks.bedrock.getDefaultState());
      BlockPos var13 = var1.up(2);
      this.o.setBlockState(var13, Blocks.bedrock.getDefaultState());
      this.o.setBlockState(var13.west(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST));
      this.o.setBlockState(var13.east(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST));
      this.o.setBlockState(var13.north(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH));
      this.o.setBlockState(var13.south(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH));
      this.o.setBlockState(var1.up(3), Blocks.bedrock.getDefaultState());
      this.o.setBlockState(var1.up(4), Blocks.dragon_egg.getDefaultState());
   }

   @Override
   public void onLivingUpdate() {
      if (this.o.D) {
         float var1 = MathHelper.cos(this.field_0010 * (float) Math.PI * 2.0F);
         float var2 = MathHelper.cos(this.field_0009 * (float) Math.PI * 2.0F);
         if (var2 <= -0.3F && var1 >= -0.3F && !this.R()) {
            this.o.playSound(this.s, this.t, this.u, "mob.enderdragon.wings", 5.0F, 0.8F + this.V.nextFloat() * 0.3F, false);
         }
      }

      this.field_0009 = this.field_0010;
      if (this.getHealth() <= 0.0F) {
         float var27 = (this.V.nextFloat() - 0.5F) * 8.0F;
         float var30 = (this.V.nextFloat() - 0.5F) * 4.0F;
         float var3 = (this.V.nextFloat() - 0.5F) * 8.0F;
         this.o.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.s + var27, this.t + 2.0 + var30, this.u + var3, 0.0, 0.0, 0.0);
      } else {
         this.method_03600();
         float var28 = 0.2F / (MathHelper.sqrt_double(this.v * this.v + this.x * this.x) * 10.0F + 1.0F);
         var28 *= (float)Math.pow(2.0, this.w);
         if (this.field_0011) {
            this.field_0010 += var28 * 0.5F;
         } else {
            this.field_0010 += var28;
         }

         this.y = MathHelper.wrapAngleTo180_float(this.y);
         if (this.isAIDisabled()) {
            this.field_0010 = 0.5F;
         } else {
            if (this.ringBufferIndex < 0) {
               for (int var31 = 0; var31 < this.ringBuffer.length; var31++) {
                  this.ringBuffer[var31][0] = this.y;
                  this.ringBuffer[var31][1] = this.t;
               }
            }

            if (++this.ringBufferIndex == this.ringBuffer.length) {
               this.ringBufferIndex = 0;
            }

            this.ringBuffer[this.ringBufferIndex][0] = this.y;
            this.ringBuffer[this.ringBufferIndex][1] = this.t;
            if (this.o.D) {
               if (this.newPosRotationIncrements > 0) {
                  double var33 = this.s + (this.newPosX - this.s) / this.newPosRotationIncrements;
                  double var38 = this.t + (this.newPosY - this.t) / this.newPosRotationIncrements;
                  double var40 = this.u + (this.newPosZ - this.u) / this.newPosRotationIncrements;
                  double var42 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - this.y);
                  this.y = (float)(this.y + var42 / this.newPosRotationIncrements);
                  this.z = (float)(this.z + (this.newRotationPitch - this.z) / this.newPosRotationIncrements);
                  this.newPosRotationIncrements--;
                  this.b(var33, var38, var40);
                  this.setRotation(this.y, this.z);
               }
            } else {
               double var32 = this.targetX - this.s;
               double var4 = this.targetY - this.t;
               double var6 = this.targetZ - this.u;
               double var8 = var32 * var32 + var4 * var4 + var6 * var6;
               if (this.target != null) {
                  this.targetX = this.target.s;
                  this.targetZ = this.target.u;
                  double var10 = this.targetX - this.s;
                  double var12 = this.targetZ - this.u;
                  double var14 = Math.sqrt(var10 * var10 + var12 * var12);
                  double var16 = 0.4F + var14 / 80.0 - 1.0;
                  if (var16 > 10.0) {
                     var16 = 10.0;
                  }

                  this.targetY = this.target.getEntityBoundingBox().b + var16;
               } else {
                  this.targetX = this.targetX + this.V.nextGaussian() * 2.0;
                  this.targetZ = this.targetZ + this.V.nextGaussian() * 2.0;
               }

               if (this.field_0002 || var8 < 100.0 || var8 > 22500.0 || this.D || this.field_0052) {
                  this.method_03591();
               }

               var4 /= MathHelper.sqrt_double(var32 * var32 + var6 * var6);
               float var44 = 0.6F;
               var4 = MathHelper.clamp_double(var4, -var44, var44);
               this.w += var4 * 0.1F;
               this.y = MathHelper.wrapAngleTo180_float(this.y);
               double var11 = 180.0 - MathHelper.atan2(var32, var6) * 180.0 / Math.PI;
               double var13 = MathHelper.wrapAngleTo180_double(var11 - this.y);
               if (var13 > 50.0) {
                  var13 = 50.0;
               }

               if (var13 < -50.0) {
                  var13 = -50.0;
               }

               Vec3 var15 = new Vec3(this.targetX - this.s, this.targetY - this.t, this.targetZ - this.u).normalize();
               double var51 = -MathHelper.cos(this.y * (float) Math.PI / 180.0F);
               Vec3 var18 = new Vec3(MathHelper.sin(this.y * (float) Math.PI / 180.0F), this.w, var51).normalize();
               float var19 = ((float)var18.dotProduct(var15) + 0.5F) / 1.5F;
               if (var19 < 0.0F) {
                  var19 = 0.0F;
               }

               this.field_0036 *= 0.8F;
               float var20 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x) * 1.0F + 1.0F;
               double var21 = Math.sqrt(this.v * this.v + this.x * this.x) * 1.0 + 1.0;
               if (var21 > 40.0) {
                  var21 = 40.0;
               }

               this.field_0036 = (float)(this.field_0036 + var13 * (0.7F / var21 / var20));
               this.y = this.y + this.field_0036 * 0.1F;
               float var23 = (float)(2.0 / (var21 + 1.0));
               float var24 = 0.06F;
               this.a(0.0F, -1.0F, var24 * (var19 * var23 + (1.0F - var23)));
               if (this.field_0011) {
                  this.d(this.v * 0.8F, this.w * 0.8F, this.x * 0.8F);
               } else {
                  this.d(this.v, this.w, this.x);
               }

               Vec3 var25 = new Vec3(this.v, this.w, this.x).normalize();
               float var26 = ((float)var25.dotProduct(var18) + 1.0F) / 2.0F;
               var26 = 0.8F + 0.15F * var26;
               this.v *= var26;
               this.x *= var26;
               this.w *= 0.91F;
            }

            this.aI = this.y;
            this.dragonPartHead.J = this.dragonPartHead.K = 3.0F;
            this.dragonPartTail1.J = this.dragonPartTail1.K = 2.0F;
            this.dragonPartTail2.J = this.dragonPartTail2.K = 2.0F;
            this.dragonPartTail3.J = this.dragonPartTail3.K = 2.0F;
            this.dragonPartBody.K = 3.0F;
            this.dragonPartBody.J = 5.0F;
            this.dragonPartWing1.K = 2.0F;
            this.dragonPartWing1.J = 4.0F;
            this.dragonPartWing2.K = 3.0F;
            this.dragonPartWing2.J = 4.0F;
            float var34 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * (float) Math.PI;
            float var35 = MathHelper.cos(var34);
            float var39 = -MathHelper.sin(var34);
            float var5 = this.y * (float) Math.PI / 180.0F;
            float var41 = MathHelper.sin(var5);
            float var7 = MathHelper.cos(var5);
            this.dragonPartBody.onUpdate();
            this.dragonPartBody.a_(this.s + var41 * 0.5F, this.t, this.u - var7 * 0.5F, 0.0F, 0.0F);
            this.dragonPartWing1.onUpdate();
            this.dragonPartWing1.a_(this.s + var7 * 4.5F, this.t + 2.0, this.u + var41 * 4.5F, 0.0F, 0.0F);
            this.dragonPartWing2.onUpdate();
            this.dragonPartWing2.a_(this.s - var7 * 4.5F, this.t + 2.0, this.u - var41 * 4.5F, 0.0F, 0.0F);
            if (!this.o.D && this.au == 0) {
               this.collideWithEntities(
                  this.o.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing1.getEntityBoundingBox().expand(4.0, 2.0, 4.0).offset(0.0, -2.0, 0.0))
               );
               this.collideWithEntities(
                  this.o.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing2.getEntityBoundingBox().expand(4.0, 2.0, 4.0).offset(0.0, -2.0, 0.0))
               );
               this.attackEntitiesInList(this.o.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartHead.getEntityBoundingBox().expand(1.0, 1.0, 1.0)));
            }

            double[] var43 = this.getMovementOffsets(5, 1.0F);
            double[] var9 = this.getMovementOffsets(0, 1.0F);
            float var45 = MathHelper.sin(this.y * (float) Math.PI / 180.0F - this.field_0036 * 0.01F);
            float var46 = MathHelper.cos(this.y * (float) Math.PI / 180.0F - this.field_0036 * 0.01F);
            this.dragonPartHead.onUpdate();
            this.dragonPartHead
               .a_(this.s + var45 * 5.5F * var35, this.t + (var9[1] - var43[1]) * 1.0 + var39 * 5.5F, this.u - var46 * 5.5F * var35, 0.0F, 0.0F);

            for (int var47 = 0; var47 < 3; var47++) {
               EntityDragonPart var48 = null;
               if (var47 == 0) {
                  var48 = this.dragonPartTail1;
               }

               if (var47 == 1) {
                  var48 = this.dragonPartTail2;
               }

               if (var47 == 2) {
                  var48 = this.dragonPartTail3;
               }

               double[] var49 = this.getMovementOffsets(12 + var47 * 2, 1.0F);
               float var50 = this.y * (float) Math.PI / 180.0F + this.simplifyAngle(var49[0] - var43[0]) * (float) Math.PI / 180.0F * 1.0F;
               float var52 = MathHelper.sin(var50);
               float var17 = MathHelper.cos(var50);
               float var53 = 1.5F;
               float var54 = (var47 + 1) * 2.0F;
               var48.onUpdate();
               var48.a_(
                  this.s - (var41 * var53 + var52 * var54) * var35,
                  this.t + (var49[1] - var43[1]) * 1.0 - (var54 + var53) * var39 + 1.5,
                  this.u + (var7 * var53 + var17 * var54) * var35,
                  0.0F,
                  0.0F
               );
            }

            if (!this.o.D) {
               this.field_0011 = this.destroyBlocksInAABB(this.dragonPartHead.getEntityBoundingBox())
                  | this.destroyBlocksInAABB(this.dragonPartBody.getEntityBoundingBox());
            }
         }
      }
   }

   @Override
   public Entity[] getParts() {
      return this.dragonPartArray;
   }

   @Override
   public void onDeathUpdate() {
      this.deathTicks++;
      if (this.deathTicks >= 180 && this.deathTicks <= 200) {
         float var1 = (this.V.nextFloat() - 0.5F) * 8.0F;
         float var2 = (this.V.nextFloat() - 0.5F) * 4.0F;
         float var3 = (this.V.nextFloat() - 0.5F) * 8.0F;
         this.o.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.s + var1, this.t + 2.0 + var2, this.u + var3, 0.0, 0.0, 0.0);
      }

      boolean var4 = this.o.Q().getBoolean("doMobLoot");
      if (!this.o.D) {
         if (this.deathTicks > 150 && this.deathTicks % 5 == 0 && var4) {
            int var5 = 1000;

            while (var5 > 0) {
               int var7 = EntityXPOrb.getXPSplit(var5);
               var5 -= var7;
               this.o.spawnEntityInWorld(new EntityXPOrb(this.o, this.s, this.t, this.u, var7));
            }
         }

         if (this.deathTicks == 1) {
            this.o.playBroadcastSound(1018, new BlockPos(this), 0);
         }
      }

      this.d(0.0, 0.1F, 0.0);
      this.aI = this.y += 20.0F;
      if (this.deathTicks == 200 && !this.o.D) {
         if (var4) {
            int var6 = 2000;

            while (var6 > 0) {
               int var8 = EntityXPOrb.getXPSplit(var6);
               var6 -= var8;
               this.o.spawnEntityInWorld(new EntityXPOrb(this.o, this.s, this.t, this.u, var8));
            }
         }

         this.generatePortal(new BlockPos(this.s, 64.0, this.u));
         this.setDead();
      }
   }

   public void method_03600() {
      if (this.healingEnderCrystal != null) {
         if (this.healingEnderCrystal.I) {
            if (!this.o.D) {
               this.attackEntityFromPart(this.dragonPartHead, DamageSource.setExplosionSource((Explosion)null), 10.0F);
            }

            this.healingEnderCrystal = null;
         } else if (this.W % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
            this.setHealth(this.getHealth() + 1.0F);
         }
      }

      if (this.V.nextInt(10) == 0) {
         float var1 = 32.0F;
         List var2 = this.o.getEntitiesWithinAABB(EntityEnderCrystal.class, this.getEntityBoundingBox().expand(var1, var1, var1));
         EntityEnderCrystal var3 = null;
         double var4 = Double.MAX_VALUE;

         for (EntityEnderCrystal var7 : var2) {
            double var8 = var7.h(this);
            if (var8 < var4) {
               var4 = var8;
               var3 = var7;
            }
         }

         this.healingEnderCrystal = var3;
      }
   }

   @Override
   public boolean canBeCollidedWith() {
      return false;
   }

   public double[] getMovementOffsets(int var1, float var2) {
      if (this.getHealth() <= 0.0F) {
         var2 = 0.0F;
      }

      var2 = 1.0F - var2;
      int var3 = this.ringBufferIndex - var1 * 1 & 63;
      int var4 = this.ringBufferIndex - var1 * 1 - 1 & 63;
      double[] var5 = new double[3];
      double var6 = this.ringBuffer[var3][0];
      double var8 = MathHelper.wrapAngleTo180_double(this.ringBuffer[var4][0] - var6);
      var5[0] = var6 + var8 * var2;
      var6 = this.ringBuffer[var3][1];
      var8 = this.ringBuffer[var4][1] - var6;
      var5[1] = var6 + var8 * var2;
      var5[2] = this.ringBuffer[var3][2] + (this.ringBuffer[var4][2] - this.ringBuffer[var3][2]) * var2;
      return var5;
   }

   public void collideWithEntities(List<Entity> var1) {
      double var2 = (this.dragonPartBody.getEntityBoundingBox().a + this.dragonPartBody.getEntityBoundingBox().d) / 2.0;
      double var4 = (this.dragonPartBody.getEntityBoundingBox().c + this.dragonPartBody.getEntityBoundingBox().f) / 2.0;

      for (Entity var7 : var1) {
         if (var7 instanceof EntityLivingBase) {
            double var8 = var7.s - var2;
            double var10 = var7.u - var4;
            double var12 = var8 * var8 + var10 * var10;
            var7.addVelocity(var8 / var12 * 4.0, 0.2F, var10 / var12 * 4.0);
         }
      }
   }

   @Override
   public void onKillCommand() {
      this.setDead();
   }

   public boolean attackDragonFrom(DamageSource var1, float var2) {
      return super.attackEntityFrom(var1, var2);
   }

   @Override
   public void k_() {
      super.k_();
   }

   @Override
   public String getLivingSound() {
      return "mob.enderdragon.growl";
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (var1 instanceof EntityDamageSource && ((EntityDamageSource)var1).getIsThornsDamage()) {
         this.attackDragonFrom(var1, var2);
      }

      return false;
   }
}
