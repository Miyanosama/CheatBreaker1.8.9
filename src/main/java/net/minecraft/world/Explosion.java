package net.minecraft.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Explosion {
   public double explosionX;
   public double explosionY;
   public boolean isFlaming;
   public boolean isSmoking;
   public Entity exploder;
   public Map<EntityPlayer, Vec3> playerKnockbackMap;
   public World worldObj;
   public List<BlockPos> affectedBlockPositions;
   public double explosionZ;
   public float explosionSize;
   public Random explosionRNG = new Random();

   public Map<EntityPlayer, Vec3> getPlayerKnockbackMap() {
      return this.playerKnockbackMap;
   }

   public void doExplosionB(boolean var1) {
      this.worldObj
         .playSoundEffect(
            this.explosionX,
            this.explosionY,
            this.explosionZ,
            "random.explode",
            4.0F,
            (1.0F + (this.worldObj.s.nextFloat() - this.worldObj.s.nextFloat()) * 0.2F) * 0.7F
         );
      if (this.explosionSize >= 2.0F && this.isSmoking) {
         this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0, 0.0, 0.0);
      } else {
         this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0, 0.0, 0.0);
      }

      if (this.isSmoking) {
         for (BlockPos var3 : this.affectedBlockPositions) {
            Block var4 = this.worldObj.getBlockState(var3).getBlock();
            if (var1) {
               double var5 = var3.getX() + this.worldObj.s.nextFloat();
               double var7 = var3.getY() + this.worldObj.s.nextFloat();
               double var9 = var3.getZ() + this.worldObj.s.nextFloat();
               double var11 = var5 - this.explosionX;
               double var13 = var7 - this.explosionY;
               double var15 = var9 - this.explosionZ;
               double var17 = MathHelper.sqrt_double(var11 * var11 + var13 * var13 + var15 * var15);
               var11 /= var17;
               var13 /= var17;
               var15 /= var17;
               double var19 = 0.5 / (var17 / this.explosionSize + 0.1);
               var19 *= this.worldObj.s.nextFloat() * this.worldObj.s.nextFloat() + 0.3F;
               var11 *= var19;
               var13 *= var19;
               var15 *= var19;
               this.worldObj
                  .spawnParticle(
                     EnumParticleTypes.EXPLOSION_NORMAL,
                     (var5 + this.explosionX * 1.0) / 2.0,
                     (var7 + this.explosionY * 1.0) / 2.0,
                     (var9 + this.explosionZ * 1.0) / 2.0,
                     var11,
                     var13,
                     var15
                  );
               this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var5, var7, var9, var11, var13, var15);
            }

            if (var4.getMaterial() != Material.air) {
               if (var4.canDropFromExplosion(this)) {
                  var4.dropBlockAsItemWithChance(this.worldObj, var3, this.worldObj.getBlockState(var3), 1.0F / this.explosionSize, 0);
               }

               this.worldObj.a(var3, Blocks.air.getDefaultState(), 3);
               var4.onBlockDestroyedByExplosion(this.worldObj, var3, this);
            }
         }
      }

      if (this.isFlaming) {
         for (BlockPos var22 : this.affectedBlockPositions) {
            if (this.worldObj.getBlockState(var22).getBlock().getMaterial() == Material.air
               && this.worldObj.getBlockState(var22.down()).getBlock().isFullBlock()
               && this.explosionRNG.nextInt(3) == 0) {
               this.worldObj.setBlockState(var22, Blocks.fire.getDefaultState());
            }
         }
      }
   }

   public Explosion(World var1, Entity var2, double var3, double var5, double var7, float var9, boolean var10, boolean var11) {
      this.affectedBlockPositions = Lists.newArrayList();
      this.playerKnockbackMap = Maps.newHashMap();
      this.worldObj = var1;
      this.exploder = var2;
      this.explosionSize = var9;
      this.explosionX = var3;
      this.explosionY = var5;
      this.explosionZ = var7;
      this.isFlaming = var10;
      this.isSmoking = var11;
   }

   public Explosion(World var1, Entity var2, double var3, double var5, double var7, float var9, boolean var10, boolean var11, List<BlockPos> var12) {
      this(var1, var2, var3, var5, var7, var9, var10, var11);
      this.affectedBlockPositions.addAll(var12);
   }

   public List<BlockPos> getAffectedBlockPositions() {
      return this.affectedBlockPositions;
   }

   public void clearAffectedBlockPositions() {
      this.affectedBlockPositions.clear();
   }

   public EntityLivingBase getExplosivePlacedBy() {
      return this.exploder == null
         ? null
         : (
            this.exploder instanceof EntityTNTPrimed
               ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy()
               : (this.exploder instanceof EntityLivingBase ? (EntityLivingBase)this.exploder : null)
         );
   }

   public Explosion(World var1, Entity var2, double var3, double var5, double var7, float var9, List<BlockPos> var10) {
      this(var1, var2, var3, var5, var7, var9, false, true, var10);
   }

   public void doExplosionA() {
      HashSet var1 = Sets.newHashSet();
      byte var2 = 16;

      for (int var3 = 0; var3 < 16; var3++) {
         for (int var4 = 0; var4 < 16; var4++) {
            for (int var5 = 0; var5 < 16; var5++) {
               if (var3 == 0 || var3 == 15 || var4 == 0 || var4 == 15 || var5 == 0 || var5 == 15) {
                  double var6 = var3 / 15.0F * 2.0F - 1.0F;
                  double var8 = var4 / 15.0F * 2.0F - 1.0F;
                  double var10 = var5 / 15.0F * 2.0F - 1.0F;
                  double var12 = Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
                  var6 /= var12;
                  var8 /= var12;
                  var10 /= var12;
                  float var14 = this.explosionSize * (0.7F + this.worldObj.s.nextFloat() * 0.6F);
                  double var15 = this.explosionX;
                  double var17 = this.explosionY;
                  double var19 = this.explosionZ;

                  for (float var21 = 0.3F; var14 > 0.0F; var14 -= 0.22500001F) {
                     BlockPos var22 = new BlockPos(var15, var17, var19);
                     IBlockState var23 = this.worldObj.getBlockState(var22);
                     if (var23.getBlock().getMaterial() != Material.air) {
                        float var24 = this.exploder != null
                           ? this.exploder.getExplosionResistance(this, this.worldObj, var22, var23)
                           : var23.getBlock().getExplosionResistance((Entity)null);
                        var14 -= (var24 + 0.3F) * 0.3F;
                     }

                     if (var14 > 0.0F && (this.exploder == null || this.exploder.verifyExplosion(this, this.worldObj, var22, var23, var14))) {
                        var1.add(var22);
                     }

                     var15 += var6 * 0.3F;
                     var17 += var8 * 0.3F;
                     var19 += var10 * 0.3F;
                  }
               }
            }
         }
      }

      this.affectedBlockPositions.addAll(var1);
      float var30 = this.explosionSize * 2.0F;
      int var31 = MathHelper.floor_double(this.explosionX - var30 - 1.0);
      int var32 = MathHelper.floor_double(this.explosionX + var30 + 1.0);
      int var34 = MathHelper.floor_double(this.explosionY - var30 - 1.0);
      int var7 = MathHelper.floor_double(this.explosionY + var30 + 1.0);
      int var36 = MathHelper.floor_double(this.explosionZ - var30 - 1.0);
      int var9 = MathHelper.floor_double(this.explosionZ + var30 + 1.0);
      List var38 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(var31, var34, var36, var32, var7, var9));
      Vec3 var11 = new Vec3(this.explosionX, this.explosionY, this.explosionZ);

      for (int var39 = 0; var39 < var38.size(); var39++) {
         Entity var13 = (Entity)var38.get(var39);
         if (!var13.method_10521()) {
            double var40 = var13.getDistance(this.explosionX, this.explosionY, this.explosionZ) / var30;
            if (var40 <= 1.0) {
               double var16 = var13.s - this.explosionX;
               double var18 = var13.t + var13.getEyeHeight() - this.explosionY;
               double var20 = var13.u - this.explosionZ;
               double var44 = MathHelper.sqrt_double(var16 * var16 + var18 * var18 + var20 * var20);
               if (var44 != 0.0) {
                  var16 /= var44;
                  var18 /= var44;
                  var20 /= var44;
                  double var45 = this.worldObj.getBlockDensity(var11, var13.getEntityBoundingBox());
                  double var26 = (1.0 - var40) * var45;
                  var13.attackEntityFrom(DamageSource.setExplosionSource(this), (int)((var26 * var26 + var26) / 2.0 * 8.0 * var30 + 1.0));
                  double var28 = EnchantmentProtection.func_92092_a(var13, var26);
                  var13.v += var16 * var28;
                  var13.w += var18 * var28;
                  var13.x += var20 * var28;
                  if (var13 instanceof EntityPlayer && !((EntityPlayer)var13).bA.disableDamage) {
                     this.playerKnockbackMap.put((EntityPlayer)var13, new Vec3(var16 * var26, var18 * var26, var20 * var26));
                  }
               }
            }
         }
      }
   }
}
