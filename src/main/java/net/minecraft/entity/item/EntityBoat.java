package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoat extends Entity {
   public double recoveredField1370;
   public double velocityX;
   public boolean isBoatEmpty = true;
   public double recoveredField1371;
   public double velocityY;
   public double recoveredField1372;
   public int boatPosRotationIncrements;
   public double velocityZ;
   public double recoveredField1373;
   public double recoveredField1374;
   public double speedMultiplier = 0.07;

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
   }

   public void setForwardDirection(int var1) {
      this.ac.updateObject(18, var1);
   }

   @Override
   public double getMountedYOffset() {
      return -0.3;
   }

   @Override
   public boolean a_(EntityPlayer var1) {
      if (this.l != null && this.l instanceof EntityPlayer && this.l != var1) {
         return true;
      } else {
         if (!this.o.D) {
            var1.mountEntity(this);
         }

         return true;
      }
   }

   @Override
   public void setVelocity(double var1, double var3, double var5) {
      this.velocityX = this.v = var1;
      this.velocityY = this.w = var3;
      this.velocityZ = this.x = var5;
   }

   @Override
   public void performHurtAnimation() {
      this.setForwardDirection(-this.getForwardDirection());
      this.setTimeSinceHit(10);
      this.setDamageTaken(this.getDamageTaken() * 11.0F);
   }

   @Override
   public AxisAlignedBB t_() {
      return this.getEntityBoundingBox();
   }

   public void setTimeSinceHit(int var1) {
      this.ac.updateObject(17, var1);
   }

   @Override
   public boolean l_() {
      return false;
   }

   public void setDamageTaken(float var1) {
      this.ac.updateObject(19, var1);
   }

   public void setIsBoatEmpty(boolean var1) {
      this.isBoatEmpty = var1;
   }

   @Override
   public boolean m_() {
      return true;
   }

   @Override
   public void k_() {
      this.ac.addObject(17, new Integer(0));
      this.ac.addObject(18, new Integer(1));
      this.ac.addObject(19, new Float(0.0F));
   }

   @Override
   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
      if (var10 && this.l != null) {
         this.p = this.s = var1;
         this.q = this.t = var3;
         this.r = this.u = var5;
         this.y = var7;
         this.z = var8;
         this.boatPosRotationIncrements = 0;
         this.b(var1, var3, var5);
         this.v = this.velocityX = 0.0;
         this.w = this.velocityY = 0.0;
         this.x = this.velocityZ = 0.0;
      } else {
         if (this.isBoatEmpty) {
            this.boatPosRotationIncrements = var9 + 5;
         } else {
            double var11 = var1 - this.s;
            double var13 = var3 - this.t;
            double var15 = var5 - this.u;
            double var17 = var11 * var11 + var13 * var13 + var15 * var15;
            if (var17 <= 1.0) {
               return;
            }

            this.boatPosRotationIncrements = 3;
         }

         this.recoveredField1372 = var1;
         this.recoveredField1374 = var3;
         this.recoveredField1371 = var5;
         this.recoveredField1373 = var7;
         this.recoveredField1370 = var8;
         this.v = this.velocityX;
         this.w = this.velocityY;
         this.x = this.velocityZ;
      }
   }

   public int getTimeSinceHit() {
      return this.ac.getWatchableObjectInt(17);
   }

   public int getForwardDirection() {
      return this.ac.getWatchableObjectInt(18);
   }

   public EntityBoat(World var1) {
      super(var1);
      this.k = true;
      this.setSize(1.5F, 0.6F);
   }

   @Override
   public void updateRiderPosition() {
      if (this.l != null) {
         double var1 = Math.cos(this.y * Math.PI / 180.0) * 0.4;
         double var3 = Math.sin(this.y * Math.PI / 180.0) * 0.4;
         this.l.b(this.s + var1, this.t + this.getMountedYOffset() + this.l.getYOffset(), this.u + var3);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if (!this.o.D && !this.I) {
         if (this.l != null && this.l == var1.getEntity() && var1 instanceof EntityDamageSourceIndirect) {
            return false;
         } else {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + var2 * 10.0F);
            this.setBeenAttacked();
            boolean var3 = var1.getEntity() instanceof EntityPlayer && ((EntityPlayer)var1.getEntity()).bA.isCreativeMode;
            if (var3 || this.getDamageTaken() > 40.0F) {
               if (this.l != null) {
                  this.l.mountEntity(this);
               }

               if (!var3 && this.o.Q().getBoolean("doEntityDrops")) {
                  this.dropItemWithOffset(Items.boat, 1, 0.0F);
               }

               this.setDead();
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public float getDamageTaken() {
      return this.ac.getWatchableObjectFloat(19);
   }

   @Override
   public AxisAlignedBB getCollisionBox(Entity var1) {
      return var1.getEntityBoundingBox();
   }

   @Override
   public void updateFallState(double var1, boolean var3, Block var4, BlockPos var5) {
      if (var3) {
         if (this.O > 3.0F) {
            this.fall(this.O, 1.0F);
            if (!this.o.D && !this.I) {
               this.setDead();
               if (this.o.Q().getBoolean("doEntityDrops")) {
                  for (int var6 = 0; var6 < 3; var6++) {
                     this.dropItemWithOffset(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
                  }

                  for (int var7 = 0; var7 < 2; var7++) {
                     this.dropItemWithOffset(Items.stick, 1, 0.0F);
                  }
               }
            }

            this.O = 0.0F;
         }
      } else if (this.o.getBlockState(new BlockPos(this).down()).getBlock().getMaterial() != Material.water && var1 < 0.0) {
         this.O = (float)(this.O - var1);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.getTimeSinceHit() > 0) {
         this.setTimeSinceHit(this.getTimeSinceHit() - 1);
      }

      if (this.getDamageTaken() > 0.0F) {
         this.setDamageTaken(this.getDamageTaken() - 1.0F);
      }

      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      byte var1 = 5;
      double var2 = 0.0;

      for (int var4 = 0; var4 < var1; var4++) {
         double var5 = this.getEntityBoundingBox().b + (this.getEntityBoundingBox().e - this.getEntityBoundingBox().b) * (var4 + 0) / var1 - 0.125;
         double var7 = this.getEntityBoundingBox().b + (this.getEntityBoundingBox().e - this.getEntityBoundingBox().b) * (var4 + 1) / var1 - 0.125;
         AxisAlignedBB var9 = new AxisAlignedBB(
            this.getEntityBoundingBox().a, var5, this.getEntityBoundingBox().c, this.getEntityBoundingBox().d, var7, this.getEntityBoundingBox().f
         );
         if (this.o.isAABBInMaterial(var9, Material.water)) {
            var2 += 1.0 / var1;
         }
      }

      double var19 = Math.sqrt(this.v * this.v + this.x * this.x);
      if (var19 > 0.2975) {
         double var6 = Math.cos(this.y * Math.PI / 180.0);
         double var8 = Math.sin(this.y * Math.PI / 180.0);

         for (int var10 = 0; var10 < 1.0 + var19 * 60.0; var10++) {
            double var11 = this.V.nextFloat() * 2.0F - 1.0F;
            double var13 = (this.V.nextInt(2) * 2 - 1) * 0.7;
            if (this.V.nextBoolean()) {
               double var15 = this.s - var6 * var11 * 0.8 + var8 * var13;
               double var17 = this.u - var8 * var11 * 0.8 - var6 * var13;
               this.o.spawnParticle(EnumParticleTypes.WATER_SPLASH, var15, this.t - 0.125, var17, this.v, this.w, this.x);
            } else {
               double var43 = this.s + var6 + var8 * var11 * 0.7;
               double var44 = this.u + var8 - var6 * var11 * 0.7;
               this.o.spawnParticle(EnumParticleTypes.WATER_SPLASH, var43, this.t - 0.125, var44, this.v, this.w, this.x);
            }
         }
      }

      if (this.o.D && this.isBoatEmpty) {
         if (this.boatPosRotationIncrements > 0) {
            double var23 = this.s + (this.recoveredField1372 - this.s) / this.boatPosRotationIncrements;
            double var31 = this.t + (this.recoveredField1374 - this.t) / this.boatPosRotationIncrements;
            double var36 = this.u + (this.recoveredField1371 - this.u) / this.boatPosRotationIncrements;
            double var40 = MathHelper.wrapAngleTo180_double(this.recoveredField1373 - this.y);
            this.y = (float)(this.y + var40 / this.boatPosRotationIncrements);
            this.z = (float)(this.z + (this.recoveredField1370 - this.z) / this.boatPosRotationIncrements);
            this.boatPosRotationIncrements--;
            this.b(var23, var31, var36);
            this.setRotation(this.y, this.z);
         } else {
            double var24 = this.s + this.v;
            double var32 = this.t + this.w;
            double var37 = this.u + this.x;
            this.b(var24, var32, var37);
            if (this.C) {
               this.v *= 0.5;
               this.w *= 0.5;
               this.x *= 0.5;
            }

            this.v *= 0.99F;
            this.w *= 0.95F;
            this.x *= 0.99F;
         }
      } else {
         if (var2 < 1.0) {
            double var20 = var2 * 2.0 - 1.0;
            this.w += 0.04F * var20;
         } else {
            if (this.w < 0.0) {
               this.w /= 2.0;
            }

            this.w += 0.007F;
         }

         if (this.l instanceof EntityLivingBase) {
            EntityLivingBase var21 = (EntityLivingBase)this.l;
            float var25 = this.l.y + -var21.aZ * 90.0F;
            this.v = this.v + -Math.sin(var25 * (float) Math.PI / 180.0F) * this.speedMultiplier * var21.ba * 0.05F;
            this.x = this.x + Math.cos(var25 * (float) Math.PI / 180.0F) * this.speedMultiplier * var21.ba * 0.05F;
         }

         double var22 = Math.sqrt(this.v * this.v + this.x * this.x);
         if (var22 > 0.35) {
            double var26 = 0.35 / var22;
            this.v *= var26;
            this.x *= var26;
            var22 = 0.35;
         }

         if (var22 > var19 && this.speedMultiplier < 0.35) {
            this.speedMultiplier = this.speedMultiplier + (0.35 - this.speedMultiplier) / 35.0;
            if (this.speedMultiplier > 0.35) {
               this.speedMultiplier = 0.35;
            }
         } else {
            this.speedMultiplier = this.speedMultiplier - (this.speedMultiplier - 0.07) / 35.0;
            if (this.speedMultiplier < 0.07) {
               this.speedMultiplier = 0.07;
            }
         }

         for (int var27 = 0; var27 < 4; var27++) {
            int var33 = MathHelper.floor_double(this.s + (var27 % 2 - 0.5) * 0.8);
            int var34 = MathHelper.floor_double(this.u + (var27 / 2 - 0.5) * 0.8);

            for (int var38 = 0; var38 < 2; var38++) {
               int var12 = MathHelper.floor_double(this.t) + var38;
               BlockPos var41 = new BlockPos(var33, var12, var34);
               Block var14 = this.o.getBlockState(var41).getBlock();
               if (var14 == Blocks.snow_layer) {
                  this.o.setBlockToAir(var41);
                  this.D = false;
               } else if (var14 == Blocks.waterlily) {
                  this.o.destroyBlock(var41, true);
                  this.D = false;
               }
            }
         }

         if (this.C) {
            this.v *= 0.5;
            this.w *= 0.5;
            this.x *= 0.5;
         }

         this.d(this.v, this.w, this.x);
         if (this.D && var19 > 0.2975) {
            if (!this.o.D && !this.I) {
               this.setDead();
               if (this.o.Q().getBoolean("doEntityDrops")) {
                  for (int var28 = 0; var28 < 3; var28++) {
                     this.dropItemWithOffset(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
                  }

                  for (int var29 = 0; var29 < 2; var29++) {
                     this.dropItemWithOffset(Items.stick, 1, 0.0F);
                  }
               }
            }
         } else {
            this.v *= 0.99F;
            this.w *= 0.95F;
            this.x *= 0.99F;
         }

         this.z = 0.0F;
         double var30 = this.y;
         double var35 = this.p - this.s;
         double var39 = this.r - this.u;
         if (var35 * var35 + var39 * var39 > 0.001) {
            var30 = (float)(MathHelper.atan2(var39, var35) * 180.0 / Math.PI);
         }

         double var42 = MathHelper.wrapAngleTo180_double(var30 - this.y);
         if (var42 > 20.0) {
            var42 = 20.0;
         }

         if (var42 < -20.0) {
            var42 = -20.0;
         }

         this.y = (float)(this.y + var42);
         this.setRotation(this.y, this.z);
         if (!this.o.D) {
            List var16 = this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(0.2F, 0.0, 0.2F));
            if (var16 != null && !var16.isEmpty()) {
               for (int var45 = 0; var45 < var16.size(); var45++) {
                  Entity var18 = (Entity)var16.get(var45);
                  if (var18 != this.l && var18.m_() && var18 instanceof EntityBoat) {
                     var18.applyEntityCollision(this);
                  }
               }
            }

            if (this.l != null && this.l.I) {
               this.l = null;
            }
         }
      }
   }

   public EntityBoat(World var1, double var2, double var4, double var6) {
      this(var1);
      this.b(var2, var4, var6);
      this.v = 0.0;
      this.w = 0.0;
      this.x = 0.0;
      this.p = var2;
      this.q = var4;
      this.r = var6;
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.I;
   }
}
