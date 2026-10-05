package net.minecraft.entity.projectile;

import io.netty.channel.sctp.nio.NioSctpServerChannel$2;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceValuesToDoubleTask;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.optifine.util.CompoundKey;
import org.slf4j.helpers.NOPLogger;
import recovered.unidentified.UnidentifiedClass3876;

public abstract class EntityThrowable extends Entity implements IProjectile {
   public boolean inGround;
   public int zTile;
   public C18PacketSpectate field_0016;
   public int ticksInAir;
   public Block inTile;
   public NioSctpServerChannel$2 field_0011;
   public ConcurrentHashMapV8$MapReduceValuesToDoubleTask field_0008;
   public String throwerName;
   public int xTile = -1;
   public int ticksInGround;
   public CompoundKey field_0007;
   public BlockWoodSlab field_0010;
   public UnidentifiedClass3876 field_0014;
   public EntityLivingBase thrower;
   public int yTile = -1;
   public int throwableShake;
   public NOPLogger field_0000;

   public EntityLivingBase getThrower() {
      if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
         this.thrower = this.o.getPlayerEntityByName(this.throwerName);
         if (this.thrower == null && this.o instanceof WorldServer) {
            try {
               Entity var1 = ((WorldServer)this.o).getEntityFromUuid(UUID.fromString(this.throwerName));
               if (var1 instanceof EntityLivingBase) {
                  this.thrower = (EntityLivingBase)var1;
               }
            } catch (Throwable var2) {
               this.thrower = null;
            }
         }
      }

      return this.thrower;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      this.xTile = var1.getShort("xTile");
      this.yTile = var1.getShort("yTile");
      this.zTile = var1.getShort("zTile");
      if (var1.hasKey("inTile", 8)) {
         this.inTile = Block.getBlockFromName(var1.getString("inTile"));
      } else {
         this.inTile = Block.getBlockById(var1.getByte("inTile") & 255);
      }

      this.throwableShake = var1.getByte("shake") & 255;
      this.inGround = var1.getByte("inGround") == 1;
      this.thrower = null;
      this.throwerName = var1.getString("ownerName");
      if (this.throwerName != null && this.throwerName.length() == 0) {
         this.throwerName = null;
      }

      this.thrower = this.getThrower();
   }

   @Override
   public boolean isInRangeToRenderDist(double var1) {
      double var3 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0;
      if (Double.isNaN(var3)) {
         var3 = 4.0;
      }

      var3 *= 64.0;
      return var1 < var3 * var3;
   }

   public float getInaccuracy() {
      return 0.0F;
   }

   @Override
   public void k_() {
   }

   @Override
   public void onUpdate() {
      this.P = this.s;
      this.Q = this.t;
      this.R = this.u;
      super.onUpdate();
      if (this.throwableShake > 0) {
         this.throwableShake--;
      }

      if (this.inGround) {
         if (this.o.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile) {
            this.ticksInGround++;
            if (this.ticksInGround == 1200) {
               this.setDead();
            }

            return;
         }

         this.inGround = false;
         this.v = this.v * (this.V.nextFloat() * 0.2F);
         this.w = this.w * (this.V.nextFloat() * 0.2F);
         this.x = this.x * (this.V.nextFloat() * 0.2F);
         this.ticksInGround = 0;
         this.ticksInAir = 0;
      } else {
         this.ticksInAir++;
      }

      Vec3 var1 = new Vec3(this.s, this.t, this.u);
      Vec3 var2 = new Vec3(this.s + this.v, this.t + this.w, this.u + this.x);
      MovingObjectPosition var3 = this.o.rayTraceBlocks(var1, var2);
      var1 = new Vec3(this.s, this.t, this.u);
      var2 = new Vec3(this.s + this.v, this.t + this.w, this.u + this.x);
      if (var3 != null) {
         var2 = new Vec3(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
      }

      if (!this.o.D) {
         Entity var4 = null;
         List var5 = this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.v, this.w, this.x).expand(1.0, 1.0, 1.0));
         double var6 = 0.0;
         EntityLivingBase var8 = this.getThrower();

         for (int var9 = 0; var9 < var5.size(); var9++) {
            Entity var10 = (Entity)var5.get(var9);
            if (var10.canBeCollidedWith() && (var10 != var8 || this.ticksInAir >= 5)) {
               float var11 = 0.3F;
               AxisAlignedBB var12 = var10.getEntityBoundingBox().expand(var11, var11, var11);
               MovingObjectPosition var13 = var12.calculateIntercept(var1, var2);
               if (var13 != null) {
                  double var14 = var1.squareDistanceTo(var13.hitVec);
                  if (var14 < var6 || var6 == 0.0) {
                     var4 = var10;
                     var6 = var14;
                  }
               }
            }
         }

         if (var4 != null) {
            var3 = new MovingObjectPosition(var4);
         }
      }

      if (var3 != null) {
         if (var3.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK && this.o.getBlockState(var3.getBlockPos()).getBlock() == Blocks.portal) {
            this.setPortal(var3.getBlockPos());
         } else {
            this.onImpact(var3);
         }
      }

      this.s = this.s + this.v;
      this.t = this.t + this.w;
      this.u = this.u + this.x;
      float var18 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
      this.y = (float)(MathHelper.atan2(this.v, this.x) * 180.0 / Math.PI);
      this.z = (float)(MathHelper.atan2(this.w, var18) * 180.0 / Math.PI);

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
      float var19 = 0.99F;
      float var20 = this.getGravityVelocity();
      if (this.V()) {
         for (int var7 = 0; var7 < 4; var7++) {
            float var21 = 0.25F;
            this.o
               .spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.s - this.v * var21, this.t - this.w * var21, this.u - this.x * var21, this.v, this.w, this.x);
         }

         var19 = 0.8F;
      }

      this.v *= var19;
      this.w *= var19;
      this.x *= var19;
      this.w -= var20;
      this.b(this.s, this.t, this.u);
   }

   public float getVelocity() {
      return 1.5F;
   }

   public abstract void onImpact(MovingObjectPosition var1);

   public float getGravityVelocity() {
      return 0.03F;
   }

   public EntityThrowable(World var1, EntityLivingBase var2) {
      super(var1);
      this.zTile = -1;
      this.thrower = var2;
      this.setSize(0.25F, 0.25F);
      this.a_(var2.s, var2.t + var2.getEyeHeight(), var2.u, var2.y, var2.z);
      this.s = this.s - MathHelper.cos(this.y / 180.0F * (float) Math.PI) * 0.16F;
      this.t -= 0.1F;
      this.u = this.u - MathHelper.sin(this.y / 180.0F * (float) Math.PI) * 0.16F;
      this.b(this.s, this.t, this.u);
      float var3 = 0.4F;
      this.v = -MathHelper.sin(this.y / 180.0F * (float) Math.PI) * MathHelper.cos(this.z / 180.0F * (float) Math.PI) * var3;
      this.x = MathHelper.cos(this.y / 180.0F * (float) Math.PI) * MathHelper.cos(this.z / 180.0F * (float) Math.PI) * var3;
      this.w = -MathHelper.sin((this.z + this.getInaccuracy()) / 180.0F * (float) Math.PI) * var3;
      this.setThrowableHeading(this.v, this.w, this.x, this.getVelocity(), 1.0F);
   }

   public EntityThrowable(World var1) {
      super(var1);
      this.zTile = -1;
      this.setSize(0.25F, 0.25F);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("xTile", (short)this.xTile);
      var1.setShort("yTile", (short)this.yTile);
      var1.setShort("zTile", (short)this.zTile);
      ResourceLocation var2 = Block.blockRegistry.getNameForObject(this.inTile);
      var1.setString("inTile", var2 == null ? "" : var2.toString());
      var1.setByte("shake", (byte)this.throwableShake);
      var1.setByte("inGround", (byte)(this.inGround ? 1 : 0));
      if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower instanceof EntityPlayer) {
         this.throwerName = this.thrower.z_();
      }

      var1.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
   }

   @Override
   public void setThrowableHeading(double var1, double var3, double var5, float var7, float var8) {
      float var9 = MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);
      var1 /= var9;
      var3 /= var9;
      var5 /= var9;
      var1 += this.V.nextGaussian() * 0.0075F * var8;
      var3 += this.V.nextGaussian() * 0.0075F * var8;
      var5 += this.V.nextGaussian() * 0.0075F * var8;
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
   public void setVelocity(double var1, double var3, double var5) {
      this.v = var1;
      this.w = var3;
      this.x = var5;
      if (this.B == 0.0F && this.A == 0.0F) {
         float var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
         this.A = this.y = (float)(MathHelper.atan2(var1, var5) * 180.0 / Math.PI);
         this.B = this.z = (float)(MathHelper.atan2(var3, var7) * 180.0 / Math.PI);
      }
   }

   public EntityThrowable(World var1, double var2, double var4, double var6) {
      super(var1);
      this.zTile = -1;
      this.ticksInGround = 0;
      this.setSize(0.25F, 0.25F);
      this.b(var2, var4, var6);
   }
}
