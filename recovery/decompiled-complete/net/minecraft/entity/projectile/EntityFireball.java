package net.minecraft.entity.projectile;

import io.netty.bootstrap.ServerBootstrap$ServerBootstrapAcceptor;
import io.netty.handler.ssl.SslHandler$2;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityFireball extends Entity {
   public int zTile;
   public SslHandler$2 field_0013;
   public BlockSourceImpl field_0005;
   public Block inTile;
   public int field_0001;
   public int yTile;
   public double accelerationX;
   public int xTile = -1;
   public S14PacketEntity field_0003;
   public KeyBinding field_0015;
   public EntityLivingBase a;
   public int field_0007;
   public ServerBootstrap$ServerBootstrapAcceptor field_0008;
   public double accelerationY;
   public double accelerationZ;
   public boolean inGround;

   public float getMotionFactor() {
      return 0.95F;
   }

   public EntityFireball(World var1, EntityLivingBase var2, double var3, double var5, double var7) {
      super(var1);
      this.yTile = -1;
      this.zTile = -1;
      this.a = var2;
      this.setSize(1.0F, 1.0F);
      this.a_(var2.s, var2.t, var2.u, var2.y, var2.z);
      this.b(this.s, this.t, this.u);
      this.v = this.w = this.x = 0.0;
      var3 += this.V.nextGaussian() * 0.4;
      var5 += this.V.nextGaussian() * 0.4;
      var7 += this.V.nextGaussian() * 0.4;
      double var9 = MathHelper.sqrt_double(var3 * var3 + var5 * var5 + var7 * var7);
      this.accelerationX = var3 / var9 * 0.1;
      this.accelerationY = var5 / var9 * 0.1;
      this.accelerationZ = var7 / var9 * 0.1;
   }

   @Override
   public void k_() {
   }

   public EntityFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1);
      this.yTile = -1;
      this.zTile = -1;
      this.setSize(1.0F, 1.0F);
      this.a_(var2, var4, var6, this.y, this.z);
      this.b(var2, var4, var6);
      double var14 = MathHelper.sqrt_double(var8 * var8 + var10 * var10 + var12 * var12);
      this.accelerationX = var8 / var14 * 0.1;
      this.accelerationY = var10 / var14 * 0.1;
      this.accelerationZ = var12 / var14 * 0.1;
   }

   @Override
   public int b_(float var1) {
      return 15728880;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("xTile", (short)this.xTile);
      var1.setShort("yTile", (short)this.yTile);
      var1.setShort("zTile", (short)this.zTile);
      ResourceLocation var2 = Block.blockRegistry.getNameForObject(this.inTile);
      var1.setString("inTile", var2 == null ? "" : var2.toString());
      var1.setByte("inGround", (byte)(this.inGround ? 1 : 0));
      var1.setTag("direction", this.newDoubleNBTList(this.v, this.w, this.x));
   }

   @Override
   public float a_(float var1) {
      return 1.0F;
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

      this.inGround = var1.getByte("inGround") == 1;
      if (var1.hasKey("direction", 9)) {
         NBTTagList var2 = var1.getTagList("direction", 6);
         this.v = var2.getDoubleAt(0);
         this.w = var2.getDoubleAt(1);
         this.x = var2.getDoubleAt(2);
      } else {
         this.setDead();
      }
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

   public abstract void onImpact(MovingObjectPosition var1);

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   public EntityFireball(World var1) {
      super(var1);
      this.yTile = -1;
      this.zTile = -1;
      this.setSize(1.0F, 1.0F);
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         this.setBeenAttacked();
         if (var1.getEntity() != null) {
            Vec3 var3 = var1.getEntity().getLookVec();
            if (var3 != null) {
               this.v = var3.xCoord;
               this.w = var3.yCoord;
               this.x = var3.zCoord;
               this.accelerationX = this.v * 0.1;
               this.accelerationY = this.w * 0.1;
               this.accelerationZ = this.x * 0.1;
            }

            if (var1.getEntity() instanceof EntityLivingBase) {
               this.a = (EntityLivingBase)var1.getEntity();
            }

            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public void onUpdate() {
      if (this.o.D || (this.a == null || !this.a.I) && this.o.e(new BlockPos(this))) {
         super.onUpdate();
         this.setFire(1);
         if (this.inGround) {
            if (this.o.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile) {
               this.field_0007++;
               if (this.field_0007 == 600) {
                  this.setDead();
               }

               return;
            }

            this.inGround = false;
            this.v = this.v * (this.V.nextFloat() * 0.2F);
            this.w = this.w * (this.V.nextFloat() * 0.2F);
            this.x = this.x * (this.V.nextFloat() * 0.2F);
            this.field_0007 = 0;
            this.field_0001 = 0;
         } else {
            this.field_0001++;
         }

         Vec3 var1 = new Vec3(this.s, this.t, this.u);
         Vec3 var2 = new Vec3(this.s + this.v, this.t + this.w, this.u + this.x);
         MovingObjectPosition var3 = this.o.rayTraceBlocks(var1, var2);
         var1 = new Vec3(this.s, this.t, this.u);
         var2 = new Vec3(this.s + this.v, this.t + this.w, this.u + this.x);
         if (var3 != null) {
            var2 = new Vec3(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
         }

         Entity var4 = null;
         List var5 = this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.v, this.w, this.x).expand(1.0, 1.0, 1.0));
         double var6 = 0.0;

         for (int var8 = 0; var8 < var5.size(); var8++) {
            Entity var9 = (Entity)var5.get(var8);
            if (var9.canBeCollidedWith() && (!var9.isEntityEqual(this.a) || this.field_0001 >= 25)) {
               float var10 = 0.3F;
               AxisAlignedBB var11 = var9.getEntityBoundingBox().expand(var10, var10, var10);
               MovingObjectPosition var12 = var11.calculateIntercept(var1, var2);
               if (var12 != null) {
                  double var13 = var1.squareDistanceTo(var12.hitVec);
                  if (var13 < var6 || var6 == 0.0) {
                     var4 = var9;
                     var6 = var13;
                  }
               }
            }
         }

         if (var4 != null) {
            var3 = new MovingObjectPosition(var4);
         }

         if (var3 != null) {
            this.onImpact(var3);
         }

         this.s = this.s + this.v;
         this.t = this.t + this.w;
         this.u = this.u + this.x;
         float var17 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
         this.y = (float)(MathHelper.atan2(this.x, this.v) * 180.0 / Math.PI) + 90.0F;
         this.z = (float)(MathHelper.atan2(var17, this.w) * 180.0 / Math.PI) - 90.0F;

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
         float var18 = this.getMotionFactor();
         if (this.V()) {
            for (int var19 = 0; var19 < 4; var19++) {
               float var20 = 0.25F;
               this.o
                  .spawnParticle(
                     EnumParticleTypes.WATER_BUBBLE, this.s - this.v * var20, this.t - this.w * var20, this.u - this.x * var20, this.v, this.w, this.x
                  );
            }

            var18 = 0.8F;
         }

         this.v = this.v + this.accelerationX;
         this.w = this.w + this.accelerationY;
         this.x = this.x + this.accelerationZ;
         this.v *= var18;
         this.w *= var18;
         this.x *= var18;
         this.o.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.s, this.t + 0.5, this.u, 0.0, 0.0, 0.0);
         this.b(this.s, this.t, this.u);
      } else {
         this.setDead();
      }
   }

   @Override
   public float getCollisionBorderSize() {
      return 1.0F;
   }
}
