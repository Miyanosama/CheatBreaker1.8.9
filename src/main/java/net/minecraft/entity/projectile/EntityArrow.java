package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArrow extends Entity implements IProjectile {
   public boolean inGround;
   public int xTile = -1;
   public int zTile;
   public int arrowShake;
   public int canBePickedUp;
   public int ticksInAir;
   public int ticksInGround;
   public int knockbackStrength;
   public Entity shootingEntity;
   public int yTile = -1;
   public Block inTile;
   public int inData;
   public double damage;

   public boolean getIsCritical() {
      byte var1 = this.ac.getWatchableObjectByte(16);
      return (var1 & 1) != 0;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.B == 0.0F && this.A == 0.0F) {
         float var1 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
         this.A = this.y = (float)(MathHelper.atan2(this.v, this.x) * 180.0 / Math.PI);
         this.B = this.z = (float)(MathHelper.atan2(this.w, var1) * 180.0 / Math.PI);
      }

      BlockPos var18 = new BlockPos(this.xTile, this.yTile, this.zTile);
      IBlockState var2 = this.o.getBlockState(var18);
      Block var3 = var2.getBlock();
      if (var3.getMaterial() != Material.air) {
         var3.setBlockBoundsBasedOnState(this.o, var18);
         AxisAlignedBB var4 = var3.getCollisionBoundingBox(this.o, var18, var2);
         if (var4 != null && var4.isVecInside(new Vec3(this.s, this.t, this.u))) {
            this.inGround = true;
         }
      }

      if (this.arrowShake > 0) {
         this.arrowShake--;
      }

      if (this.inGround) {
         int var19 = var3.getMetaFromState(var2);
         if (var3 == this.inTile && var19 == this.inData) {
            this.ticksInGround++;
            if (this.ticksInGround >= 1200) {
               this.setDead();
            }
         } else {
            this.inGround = false;
            this.v = this.v * (this.V.nextFloat() * 0.2F);
            this.w = this.w * (this.V.nextFloat() * 0.2F);
            this.x = this.x * (this.V.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         }
      } else {
         this.ticksInAir++;
         Vec3 var20 = new Vec3(this.s, this.t, this.u);
         Vec3 var5 = new Vec3(this.s + this.v, this.t + this.w, this.u + this.x);
         MovingObjectPosition var6 = this.o.rayTraceBlocks(var20, var5, false, true, false);
         var20 = new Vec3(this.s, this.t, this.u);
         var5 = new Vec3(this.s + this.v, this.t + this.w, this.u + this.x);
         if (var6 != null) {
            var5 = new Vec3(var6.hitVec.xCoord, var6.hitVec.yCoord, var6.hitVec.zCoord);
         }

         Entity var7 = null;
         List var8 = this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.v, this.w, this.x).expand(1.0, 1.0, 1.0));
         double var9 = 0.0;

         for (int var11 = 0; var11 < var8.size(); var11++) {
            Entity var12 = (Entity)var8.get(var11);
            if (var12.canBeCollidedWith() && (var12 != this.shootingEntity || this.ticksInAir >= 5)) {
               float var13 = 0.3F;
               AxisAlignedBB var14 = var12.getEntityBoundingBox().expand(var13, var13, var13);
               MovingObjectPosition var15 = var14.calculateIntercept(var20, var5);
               if (var15 != null) {
                  double var16 = var20.squareDistanceTo(var15.hitVec);
                  if (var16 < var9 || var9 == 0.0) {
                     var7 = var12;
                     var9 = var16;
                  }
               }
            }
         }

         if (var7 != null) {
            var6 = new MovingObjectPosition(var7);
         }

         if (var6 != null && var6.entityHit != null && var6.entityHit instanceof EntityPlayer) {
            EntityPlayer var23 = (EntityPlayer)var6.entityHit;
            if (var23.bA.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(var23)) {
               var6 = null;
            }
         }

         if (var6 != null) {
            if (var6.entityHit != null) {
               float var24 = MathHelper.sqrt_double(this.v * this.v + this.w * this.w + this.x * this.x);
               int var28 = MathHelper.ceiling_double_int(var24 * this.damage);
               if (this.getIsCritical()) {
                  var28 += this.V.nextInt(var28 / 2 + 2);
               }

               DamageSource var31;
               if (this.shootingEntity == null) {
                  var31 = DamageSource.causeArrowDamage(this, this);
               } else {
                  var31 = DamageSource.causeArrowDamage(this, this.shootingEntity);
               }

               if (this.isBurning() && !(var6.entityHit instanceof EntityEnderman)) {
                  var6.entityHit.setFire(5);
               }

               if (var6.entityHit.attackEntityFrom(var31, var28)) {
                  if (var6.entityHit instanceof EntityLivingBase) {
                     EntityLivingBase var34 = (EntityLivingBase)var6.entityHit;
                     if (!this.o.D) {
                        var34.setArrowCountInEntity(var34.getArrowCountInEntity() + 1);
                     }

                     if (this.knockbackStrength > 0) {
                        float var36 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
                        if (var36 > 0.0F) {
                           var6.entityHit.addVelocity(this.v * this.knockbackStrength * 0.6F / var36, 0.1, this.x * this.knockbackStrength * 0.6F / var36);
                        }
                     }

                     if (this.shootingEntity instanceof EntityLivingBase) {
                        EnchantmentHelper.applyThornEnchantments(var34, this.shootingEntity);
                        EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, var34);
                     }

                     if (this.shootingEntity != null
                        && var6.entityHit != this.shootingEntity
                        && var6.entityHit instanceof EntityPlayer
                        && this.shootingEntity instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
                     }
                  }

                  this.playSound("random.bowhit", 1.0F, 1.2F / (this.V.nextFloat() * 0.2F + 0.9F));
                  if (!(var6.entityHit instanceof EntityEnderman)) {
                     this.setDead();
                  }
               } else {
                  this.v *= -0.1F;
                  this.w *= -0.1F;
                  this.x *= -0.1F;
                  this.y += 180.0F;
                  this.A += 180.0F;
                  this.ticksInAir = 0;
               }
            } else {
               BlockPos var25 = var6.getBlockPos();
               this.xTile = var25.getX();
               this.yTile = var25.getY();
               this.zTile = var25.getZ();
               IBlockState var29 = this.o.getBlockState(var25);
               this.inTile = var29.getBlock();
               this.inData = this.inTile.getMetaFromState(var29);
               this.v = (float)(var6.hitVec.xCoord - this.s);
               this.w = (float)(var6.hitVec.yCoord - this.t);
               this.x = (float)(var6.hitVec.zCoord - this.u);
               float var32 = MathHelper.sqrt_double(this.v * this.v + this.w * this.w + this.x * this.x);
               this.s = this.s - this.v / var32 * 0.05F;
               this.t = this.t - this.w / var32 * 0.05F;
               this.u = this.u - this.x / var32 * 0.05F;
               this.playSound("random.bowhit", 1.0F, 1.2F / (this.V.nextFloat() * 0.2F + 0.9F));
               this.inGround = true;
               this.arrowShake = 7;
               this.setIsCritical(false);
               if (this.inTile.getMaterial() != Material.air) {
                  this.inTile.onEntityCollidedWithBlock(this.o, var25, var29, this);
               }
            }
         }

         if (this.getIsCritical()) {
            for (int var26 = 0; var26 < 4; var26++) {
               this.o
                  .spawnParticle(
                     EnumParticleTypes.CRIT,
                     this.s + this.v * var26 / 4.0,
                     this.t + this.w * var26 / 4.0,
                     this.u + this.x * var26 / 4.0,
                     -this.v,
                     -this.w + 0.2,
                     -this.x
                  );
            }
         }

         this.s = this.s + this.v;
         this.t = this.t + this.w;
         this.u = this.u + this.x;
         float var27 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
         this.y = (float)(MathHelper.atan2(this.v, this.x) * 180.0 / Math.PI);
         this.z = (float)(MathHelper.atan2(this.w, var27) * 180.0 / Math.PI);

         while (this.z - this.B < -180.0F) {
            this.B -= 360.0F;
         }

         while (this.z - this.B >= 180.0F) {
            this.B += 360.0F;
         }

         while (this.y - this.A < -180.0F) {
            this.A -= 360.0F;
         }

         while (this.y - this.A >= 180.0F) {
            this.A += 360.0F;
         }

         this.z = this.B + (this.z - this.B) * 0.2F;
         this.y = this.A + (this.y - this.A) * 0.2F;
         float var30 = 0.99F;
         float var33 = 0.05F;
         if (this.V()) {
            for (int var35 = 0; var35 < 4; var35++) {
               float var37 = 0.25F;
               this.o
                  .spawnParticle(
                     EnumParticleTypes.WATER_BUBBLE, this.s - this.v * var37, this.t - this.w * var37, this.u - this.x * var37, this.v, this.w, this.x
                  );
            }

            var30 = 0.6F;
         }

         if (this.U()) {
            this.extinguish();
         }

         this.v *= var30;
         this.w *= var30;
         this.x *= var30;
         this.w -= var33;
         this.b(this.s, this.t, this.u);
         this.doBlockCollisions();
      }
   }

   public EntityArrow(World var1, EntityLivingBase var2, EntityLivingBase var3, float var4, float var5) {
      super(var1);
      this.zTile = -1;
      this.damage = 2.0;
      this.j = 10.0;
      this.shootingEntity = var2;
      if (var2 instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      this.t = var2.t + var2.getEyeHeight() - 0.1F;
      double var6 = var3.s - var2.s;
      double var8 = var3.getEntityBoundingBox().b + var3.K / 3.0F - this.t;
      double var10 = var3.u - var2.u;
      double var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10);
      if (var12 >= 1.0E-7) {
         float var14 = (float)(MathHelper.atan2(var10, var6) * 180.0 / Math.PI) - 90.0F;
         float var15 = (float)(-(MathHelper.atan2(var8, var12) * 180.0 / Math.PI));
         double var16 = var6 / var12;
         double var18 = var10 / var12;
         this.a_(var2.s + var16, this.t, var2.u + var18, var14, var15);
         float var20 = (float)(var12 * 0.2F);
         this.setThrowableHeading(var6, var8 + var20, var10, var4, var5);
      }
   }

   public EntityArrow(World var1) {
      super(var1);
      this.zTile = -1;
      this.damage = 2.0;
      this.j = 10.0;
      this.setSize(0.5F, 0.5F);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("xTile", (short)this.xTile);
      var1.setShort("yTile", (short)this.yTile);
      var1.setShort("zTile", (short)this.zTile);
      var1.setShort("life", (short)this.ticksInGround);
      ResourceLocation var2 = Block.blockRegistry.getNameForObject(this.inTile);
      var1.setString("inTile", var2 == null ? "" : var2.toString());
      var1.setByte("inData", (byte)this.inData);
      var1.setByte("shake", (byte)this.arrowShake);
      var1.setByte("inGround", (byte)(this.inGround ? 1 : 0));
      var1.setByte("pickup", (byte)this.canBePickedUp);
      var1.setDouble("damage", this.damage);
   }

   @Override
   public void setVelocity(double var1, double var3, double var5) {
      this.v = var1;
      this.w = var3;
      this.x = var5;
      if (this.B == 0.0F && this.A == 0.0F) {
         float var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
         this.A = this.y = (float)(MathHelper.atan2(var1, var5) * 180.0 / Math.PI);
         this.B = this.z = (float)(MathHelper.atan2(var3, var7) * 180.0 / Math.PI);
         this.B = this.z;
         this.A = this.y;
         this.a_(this.s, this.t, this.u, this.y, this.z);
         this.ticksInGround = 0;
      }
   }

   public boolean method_24577() {
      return this.inGround;
   }

   @Override
   public boolean l_() {
      return false;
   }

   @Override
   public void setThrowableHeading(double var1, double var3, double var5, float var7, float var8) {
      float var9 = MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);
      var1 /= var9;
      var3 /= var9;
      var5 /= var9;
      var1 += this.V.nextGaussian() * (this.V.nextBoolean() ? -1 : 1) * 0.0075F * var8;
      var3 += this.V.nextGaussian() * (this.V.nextBoolean() ? -1 : 1) * 0.0075F * var8;
      var5 += this.V.nextGaussian() * (this.V.nextBoolean() ? -1 : 1) * 0.0075F * var8;
      var1 *= var7;
      var3 *= var7;
      var5 *= var7;
      this.v = var1;
      this.w = var3;
      this.x = var5;
      float var10 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
      this.A = this.y = (float)(MathHelper.atan2(var1, var5) * 180.0 / Math.PI);
      this.B = this.z = (float)(MathHelper.atan2(var3, var10) * 180.0 / Math.PI);
      this.ticksInGround = 0;
   }

   @Override
   public void k_() {
      this.ac.addObject(16, (byte)0);
   }

   @Override
   public boolean r_() {
      return false;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      this.xTile = var1.getShort("xTile");
      this.yTile = var1.getShort("yTile");
      this.zTile = var1.getShort("zTile");
      this.ticksInGround = var1.getShort("life");
      if (var1.hasKey("inTile", 8)) {
         this.inTile = Block.getBlockFromName(var1.getString("inTile"));
      } else {
         this.inTile = Block.getBlockById(var1.getByte("inTile") & 255);
      }

      this.inData = var1.getByte("inData") & 255;
      this.arrowShake = var1.getByte("shake") & 255;
      this.inGround = var1.getByte("inGround") == 1;
      if (var1.hasKey("damage", 99)) {
         this.damage = var1.getDouble("damage");
      }

      if (var1.hasKey("pickup", 99)) {
         this.canBePickedUp = var1.getByte("pickup");
      } else if (var1.hasKey("player", 99)) {
         this.canBePickedUp = var1.getBoolean("player") ? 1 : 0;
      }
   }

   public void setIsCritical(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         this.ac.updateObject(16, (byte)(var2 | 1));
      } else {
         this.ac.updateObject(16, (byte)(var2 & -2));
      }
   }

   public void setDamage(double var1) {
      this.damage = var1;
   }

   public double getDamage() {
      return this.damage;
   }

   @Override
   public float getEyeHeight() {
      return 0.0F;
   }

   public void setKnockbackStrength(int var1) {
      this.knockbackStrength = var1;
   }

   public EntityArrow(World var1, EntityLivingBase var2, float var3) {
      super(var1);
      this.zTile = -1;
      this.damage = 2.0;
      this.j = 10.0;
      this.shootingEntity = var2;
      if (var2 instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      this.setSize(0.5F, 0.5F);
      this.a_(var2.s, var2.t + var2.getEyeHeight(), var2.u, var2.y, var2.z);
      this.s = this.s - MathHelper.cos(this.y / 180.0F * (float) Math.PI) * 0.16F;
      this.t -= 0.1F;
      this.u = this.u - MathHelper.sin(this.y / 180.0F * (float) Math.PI) * 0.16F;
      this.b(this.s, this.t, this.u);
      this.v = -MathHelper.sin(this.y / 180.0F * (float) Math.PI) * MathHelper.cos(this.z / 180.0F * (float) Math.PI);
      this.x = MathHelper.cos(this.y / 180.0F * (float) Math.PI) * MathHelper.cos(this.z / 180.0F * (float) Math.PI);
      this.w = -MathHelper.sin(this.z / 180.0F * (float) Math.PI);
      this.setThrowableHeading(this.v, this.w, this.x, var3 * 1.5F, 1.0F);
   }

   @Override
   public void b_(EntityPlayer var1) {
      if (!this.o.D && this.inGround && this.arrowShake <= 0) {
         boolean var2 = this.canBePickedUp == 1 || this.canBePickedUp == 2 && var1.bA.isCreativeMode;
         if (this.canBePickedUp == 1 && !var1.bi.addItemStackToInventory(new ItemStack(Items.arrow, 1))) {
            var2 = false;
         }

         if (var2) {
            this.playSound("random.pop", 0.2F, ((this.V.nextFloat() - this.V.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            var1.a(this, 1);
            this.setDead();
         }
      }
   }

   @Override
   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
      this.b(var1, var3, var5);
      this.setRotation(var7, var8);
   }

   public EntityArrow(World var1, double var2, double var4, double var6) {
      super(var1);
      this.zTile = -1;
      this.damage = 2.0;
      this.j = 10.0;
      this.setSize(0.5F, 0.5F);
      this.b(var2, var4, var6);
   }
}
