package net.minecraft.entity.projectile;

import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityFishHook extends Entity {
   public double fishY;
   public int zTile;
   public float fishApproachAngle;
   public Block inTile;
   public double clientMotionY;
   public int ticksCatchable;
   public int xTile = -1;
   public double fishX;
   public int yTile = -1;
   public int recoveredField1302;
   public int recoveredField1303;
   public double fishPitch;
   public static List<WeightedRandomFishable> JUNK = Arrays.asList(
      new WeightedRandomFishable(new ItemStack(Items.leather_boots), 10).setMaxDamagePercent(0.9F),
      new WeightedRandomFishable(new ItemStack(Items.leather), 10),
      new WeightedRandomFishable(new ItemStack(Items.bone), 10),
      new WeightedRandomFishable(new ItemStack(Items.potionitem), 10),
      new WeightedRandomFishable(new ItemStack(Items.string), 5),
      new WeightedRandomFishable(new ItemStack(Items.fishing_rod), 2).setMaxDamagePercent(0.9F),
      new WeightedRandomFishable(new ItemStack(Items.bowl), 10),
      new WeightedRandomFishable(new ItemStack(Items.stick), 5),
      new WeightedRandomFishable(new ItemStack(Items.dye, 10, EnumDyeColor.BLACK.getDyeDamage()), 1),
      new WeightedRandomFishable(new ItemStack(Blocks.tripwire_hook), 10),
      new WeightedRandomFishable(new ItemStack(Items.rotten_flesh), 10)
   );
   public static List<WeightedRandomFishable> TREASURE = Arrays.asList(
      new WeightedRandomFishable(new ItemStack(Blocks.waterlily), 1),
      new WeightedRandomFishable(new ItemStack(Items.name_tag), 1),
      new WeightedRandomFishable(new ItemStack(Items.saddle), 1),
      new WeightedRandomFishable(new ItemStack(Items.bow), 1).setMaxDamagePercent(0.25F).setEnchantable(),
      new WeightedRandomFishable(new ItemStack(Items.fishing_rod), 1).setMaxDamagePercent(0.25F).setEnchantable(),
      new WeightedRandomFishable(new ItemStack(Items.book), 1).setEnchantable()
   );
   public double fishZ;
   public double clientMotionX;
   public double fishYaw;
   public int recoveredField1304;
   public boolean inGround;
   public int fishPosRotationIncrements;
   public Entity caughtEntity;
   public EntityPlayer angler;
   public int shake;
   public int recoveredField1305;
   public static List<WeightedRandomFishable> FISH = Arrays.asList(
      new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.getMetadata()), 60),
      new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.getMetadata()), 25),
      new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.CLOWNFISH.getMetadata()), 2),
      new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.getMetadata()), 13)
   );
   public double clientMotionZ;

   @Override
   public void setVelocity(double var1, double var3, double var5) {
      this.clientMotionX = this.v = var1;
      this.clientMotionY = this.w = var3;
      this.clientMotionZ = this.x = var5;
   }

   public static List<WeightedRandomFishable> func_174855_j() {
      return FISH;
   }

   public void handleHookCasting(double var1, double var3, double var5, float var7, float var8) {
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
      this.recoveredField1302 = 0;
   }

   public EntityFishHook(World var1, EntityPlayer var2) {
      super(var1);
      this.zTile = -1;
      this.ah = true;
      this.angler = var2;
      this.angler.fishEntity = this;
      this.setSize(0.25F, 0.25F);
      this.a_(var2.s, var2.t + var2.getEyeHeight(), var2.u, var2.y, var2.z);
      this.s = this.s - MathHelper.cos(this.y / 180.0F * (float) Math.PI) * 0.16F;
      this.t -= 0.1F;
      this.u = this.u - MathHelper.sin(this.y / 180.0F * (float) Math.PI) * 0.16F;
      this.b(this.s, this.t, this.u);
      float var3 = 0.4F;
      this.v = -MathHelper.sin(this.y / 180.0F * (float) Math.PI) * MathHelper.cos(this.z / 180.0F * (float) Math.PI) * var3;
      this.x = MathHelper.cos(this.y / 180.0F * (float) Math.PI) * MathHelper.cos(this.z / 180.0F * (float) Math.PI) * var3;
      this.w = -MathHelper.sin(this.z / 180.0F * (float) Math.PI) * var3;
      this.handleHookCasting(this.v, this.w, this.x, 1.5F, 1.0F);
   }

   @Override
   public void setDead() {
      super.setDead();
      if (this.angler != null) {
         this.angler.fishEntity = null;
      }
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.fishPosRotationIncrements > 0) {
         double var1 = this.s + (this.fishX - this.s) / this.fishPosRotationIncrements;
         double var3 = this.t + (this.fishY - this.t) / this.fishPosRotationIncrements;
         double var5 = this.u + (this.fishZ - this.u) / this.fishPosRotationIncrements;
         double var7 = MathHelper.wrapAngleTo180_double(this.fishYaw - this.y);
         this.y = (float)(this.y + var7 / this.fishPosRotationIncrements);
         this.z = (float)(this.z + (this.fishPitch - this.z) / this.fishPosRotationIncrements);
         this.fishPosRotationIncrements--;
         this.b(var1, var3, var5);
         this.setRotation(this.y, this.z);
      } else {
         if (!this.o.D) {
            ItemStack var28 = this.angler.getCurrentEquippedItem();
            if (this.angler.I || !this.angler.isEntityAlive() || var28 == null || var28.getItem() != Items.fishing_rod || this.h(this.angler) > 1024.0) {
               this.setDead();
               this.angler.fishEntity = null;
               return;
            }

            if (this.caughtEntity != null) {
               if (!this.caughtEntity.I) {
                  this.s = this.caughtEntity.s;
                  double var32 = this.caughtEntity.K;
                  this.t = this.caughtEntity.getEntityBoundingBox().b + var32 * 0.8;
                  this.u = this.caughtEntity.u;
                  return;
               }

               this.caughtEntity = null;
            }
         }

         if (this.shake > 0) {
            this.shake--;
         }

         if (this.inGround) {
            if (this.o.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile) {
               this.recoveredField1302++;
               if (this.recoveredField1302 == 1200) {
                  this.setDead();
               }

               return;
            }

            this.inGround = false;
            this.v = this.v * (this.V.nextFloat() * 0.2F);
            this.w = this.w * (this.V.nextFloat() * 0.2F);
            this.x = this.x * (this.V.nextFloat() * 0.2F);
            this.recoveredField1302 = 0;
            this.recoveredField1304 = 0;
         } else {
            this.recoveredField1304++;
         }

         Vec3 var29 = new Vec3(this.s, this.t, this.u);
         Vec3 var2 = new Vec3(this.s + this.v, this.t + this.w, this.u + this.x);
         MovingObjectPosition var33 = this.o.rayTraceBlocks(var29, var2);
         var29 = new Vec3(this.s, this.t, this.u);
         var2 = new Vec3(this.s + this.v, this.t + this.w, this.u + this.x);
         if (var33 != null) {
            var2 = new Vec3(var33.hitVec.xCoord, var33.hitVec.yCoord, var33.hitVec.zCoord);
         }

         Entity var4 = null;
         List var34 = this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.v, this.w, this.x).expand(1.0, 1.0, 1.0));
         double var6 = 0.0;

         for (int var8 = 0; var8 < var34.size(); var8++) {
            Entity var9 = (Entity)var34.get(var8);
            if (var9.canBeCollidedWith() && (var9 != this.angler || this.recoveredField1304 >= 5)) {
               float var10 = 0.3F;
               AxisAlignedBB var11 = var9.getEntityBoundingBox().expand(var10, var10, var10);
               MovingObjectPosition var12 = var11.calculateIntercept(var29, var2);
               if (var12 != null) {
                  double var13 = var29.squareDistanceTo(var12.hitVec);
                  if (var13 < var6 || var6 == 0.0) {
                     var4 = var9;
                     var6 = var13;
                  }
               }
            }
         }

         if (var4 != null) {
            var33 = new MovingObjectPosition(var4);
         }

         if (var33 != null) {
            if (var33.entityHit != null) {
               if (var33.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.angler), 0.0F)) {
                  this.caughtEntity = var33.entityHit;
               }
            } else {
               this.inGround = true;
            }
         }

         if (!this.inGround) {
            this.d(this.v, this.w, this.x);
            float var35 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
            this.y = (float)(MathHelper.atan2(this.v, this.x) * 180.0 / Math.PI);
            this.z = (float)(MathHelper.atan2(this.w, var35) * 180.0 / Math.PI);

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
            float var36 = 0.92F;
            if (this.C || this.D) {
               var36 = 0.5F;
            }

            byte var37 = 5;
            double var38 = 0.0;

            for (int var39 = 0; var39 < var37; var39++) {
               AxisAlignedBB var14 = this.getEntityBoundingBox();
               double var15 = var14.e - var14.b;
               double var17 = var14.b + var15 * var39 / var37;
               double var19 = var14.b + var15 * (var39 + 1) / var37;
               AxisAlignedBB var21 = new AxisAlignedBB(var14.a, var17, var14.c, var14.d, var19, var14.f);
               if (this.o.isAABBInMaterial(var21, Material.water)) {
                  var38 += 1.0 / var37;
               }
            }

            if (!this.o.D && var38 > 0.0) {
               WorldServer var40 = (WorldServer)this.o;
               int var42 = 1;
               BlockPos var43 = new BlockPos(this).up();
               if (this.V.nextFloat() < 0.25F && this.o.isRainingAt(var43)) {
                  var42 = 2;
               }

               if (this.V.nextFloat() < 0.5F && !this.o.canSeeSky(var43)) {
                  var42--;
               }

               if (this.ticksCatchable > 0) {
                  this.ticksCatchable--;
                  if (this.ticksCatchable <= 0) {
                     this.recoveredField1305 = 0;
                     this.recoveredField1303 = 0;
                  }
               } else if (this.recoveredField1303 > 0) {
                  this.recoveredField1303 -= var42;
                  if (this.recoveredField1303 <= 0) {
                     this.w -= 0.2F;
                     this.playSound("random.splash", 0.25F, 1.0F + (this.V.nextFloat() - this.V.nextFloat()) * 0.4F);
                     float var16 = MathHelper.floor_double(this.getEntityBoundingBox().b);
                     var40.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.s, var16 + 1.0F, this.u, (int)(1.0F + this.J * 20.0F), this.J, 0.0, this.J, 0.2F);
                     var40.spawnParticle(EnumParticleTypes.WATER_WAKE, this.s, var16 + 1.0F, this.u, (int)(1.0F + this.J * 20.0F), this.J, 0.0, this.J, 0.2F);
                     this.ticksCatchable = MathHelper.getRandomIntegerInRange(this.V, 10, 30);
                  } else {
                     this.fishApproachAngle = (float)(this.fishApproachAngle + this.V.nextGaussian() * 4.0);
                     float var44 = this.fishApproachAngle * (float) (Math.PI / 180.0);
                     float var46 = MathHelper.sin(var44);
                     float var18 = MathHelper.cos(var44);
                     double var49 = this.s + var46 * this.recoveredField1303 * 0.1F;
                     double var51 = MathHelper.floor_double(this.getEntityBoundingBox().b) + 1.0F;
                     double var23 = this.u + var18 * this.recoveredField1303 * 0.1F;
                     Block var25 = var40.getBlockState(new BlockPos((int)var49, (int)var51 - 1, (int)var23)).getBlock();
                     if (var25 == Blocks.water || var25 == Blocks.flowing_water) {
                        if (this.V.nextFloat() < 0.15F) {
                           var40.spawnParticle(EnumParticleTypes.WATER_BUBBLE, var49, var51 - 0.1F, var23, 1, var46, 0.1, var18, 0.0);
                        }

                        float var26 = var46 * 0.04F;
                        float var27 = var18 * 0.04F;
                        var40.spawnParticle(EnumParticleTypes.WATER_WAKE, var49, var51, var23, 0, var27, 0.01, -var26, 1.0);
                        var40.spawnParticle(EnumParticleTypes.WATER_WAKE, var49, var51, var23, 0, -var27, 0.01, var26, 1.0);
                     }
                  }
               } else if (this.recoveredField1305 > 0) {
                  this.recoveredField1305 -= var42;
                  float var45 = 0.15F;
                  if (this.recoveredField1305 < 20) {
                     var45 = (float)(var45 + (20 - this.recoveredField1305) * 0.05);
                  } else if (this.recoveredField1305 < 40) {
                     var45 = (float)(var45 + (40 - this.recoveredField1305) * 0.02);
                  } else if (this.recoveredField1305 < 60) {
                     var45 = (float)(var45 + (60 - this.recoveredField1305) * 0.01);
                  }

                  if (this.V.nextFloat() < var45) {
                     float var47 = MathHelper.randomFloatClamp(this.V, 0.0F, 360.0F) * (float) (Math.PI / 180.0);
                     float var48 = MathHelper.randomFloatClamp(this.V, 25.0F, 60.0F);
                     double var50 = this.s + MathHelper.sin(var47) * var48 * 0.1F;
                     double var52 = MathHelper.floor_double(this.getEntityBoundingBox().b) + 1.0F;
                     double var53 = this.u + MathHelper.cos(var47) * var48 * 0.1F;
                     Block var54 = var40.getBlockState(new BlockPos((int)var50, (int)var52 - 1, (int)var53)).getBlock();
                     if (var54 == Blocks.water || var54 == Blocks.flowing_water) {
                        var40.spawnParticle(EnumParticleTypes.WATER_SPLASH, var50, var52, var53, 2 + this.V.nextInt(2), 0.1F, 0.0, 0.1F, 0.0);
                     }
                  }

                  if (this.recoveredField1305 <= 0) {
                     this.fishApproachAngle = MathHelper.randomFloatClamp(this.V, 0.0F, 360.0F);
                     this.recoveredField1303 = MathHelper.getRandomIntegerInRange(this.V, 20, 80);
                  }
               } else {
                  this.recoveredField1305 = MathHelper.getRandomIntegerInRange(this.V, 100, 900);
                  this.recoveredField1305 = this.recoveredField1305 - EnchantmentHelper.method_08068(this.angler) * 20 * 5;
               }

               if (this.ticksCatchable > 0) {
                  this.w = this.w - this.V.nextFloat() * this.V.nextFloat() * this.V.nextFloat() * 0.2;
               }
            }

            double var41 = var38 * 2.0 - 1.0;
            this.w += 0.04F * var41;
            if (var38 > 0.0) {
               var36 = (float)(var36 * 0.9);
               this.w *= 0.8;
            }

            this.v *= var36;
            this.w *= var36;
            this.x *= var36;
            this.b(this.s, this.t, this.u);
         }
      }
   }

   public int handleHookRetraction() {
      if (this.o.D) {
         return 0;
      } else {
         byte var1 = 0;
         if (this.caughtEntity != null) {
            double var2 = this.angler.s - this.s;
            double var4 = this.angler.t - this.t;
            double var6 = this.angler.u - this.u;
            double var8 = MathHelper.sqrt_double(var2 * var2 + var4 * var4 + var6 * var6);
            double var10 = 0.1;
            this.caughtEntity.v += var2 * var10;
            this.caughtEntity.w = this.caughtEntity.w + (var4 * var10 + MathHelper.sqrt_double(var8) * 0.08);
            this.caughtEntity.x += var6 * var10;
            var1 = 3;
         } else if (this.ticksCatchable > 0) {
            EntityItem var13 = new EntityItem(this.o, this.s, this.t, this.u, this.getFishingResult());
            double var3 = this.angler.s - this.s;
            double var5 = this.angler.t - this.t;
            double var7 = this.angler.u - this.u;
            double var9 = MathHelper.sqrt_double(var3 * var3 + var5 * var5 + var7 * var7);
            double var11 = 0.1;
            var13.v = var3 * var11;
            var13.w = var5 * var11 + MathHelper.sqrt_double(var9) * 0.08;
            var13.x = var7 * var11;
            this.o.spawnEntityInWorld(var13);
            this.angler.o.spawnEntityInWorld(new EntityXPOrb(this.angler.o, this.angler.s, this.angler.t + 0.5, this.angler.u + 0.5, this.V.nextInt(6) + 1));
            var1 = 1;
         }

         if (this.inGround) {
            var1 = 2;
         }

         this.setDead();
         this.angler.fishEntity = null;
         return var1;
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

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("xTile", (short)this.xTile);
      var1.setShort("yTile", (short)this.yTile);
      var1.setShort("zTile", (short)this.zTile);
      ResourceLocation var2 = Block.blockRegistry.getNameForObject(this.inTile);
      var1.setString("inTile", var2 == null ? "" : var2.toString());
      var1.setByte("shake", (byte)this.shake);
      var1.setByte("inGround", (byte)(this.inGround ? 1 : 0));
   }

   @Override
   public void k_() {
   }

   public ItemStack getFishingResult() {
      float var1 = this.o.s.nextFloat();
      int var2 = EnchantmentHelper.method_08067(this.angler);
      int var3 = EnchantmentHelper.method_08068(this.angler);
      float var4 = 0.1F - var2 * 0.025F - var3 * 0.01F;
      float var5 = 0.05F + var2 * 0.01F - var3 * 0.01F;
      var4 = MathHelper.clamp_float(var4, 0.0F, 1.0F);
      var5 = MathHelper.clamp_float(var5, 0.0F, 1.0F);
      if (var1 < var4) {
         this.angler.triggerAchievement(StatList.junkFishedStat);
         return WeightedRandom.getRandomItem(this.V, JUNK).getItemStack(this.V);
      } else {
         var1 -= var4;
         if (var1 < var5) {
            this.angler.triggerAchievement(StatList.treasureFishedStat);
            return WeightedRandom.getRandomItem(this.V, TREASURE).getItemStack(this.V);
         } else {
            float var6 = var1 - var5;
            this.angler.triggerAchievement(StatList.fishCaughtStat);
            return WeightedRandom.getRandomItem(this.V, FISH).getItemStack(this.V);
         }
      }
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

      this.shake = var1.getByte("shake") & 255;
      this.inGround = var1.getByte("inGround") == 1;
   }

   public EntityFishHook(World var1) {
      super(var1);
      this.zTile = -1;
      this.setSize(0.25F, 0.25F);
      this.ah = true;
   }

   public EntityFishHook(World var1, double var2, double var4, double var6, EntityPlayer var8) {
      this(var1);
      this.b(var2, var4, var6);
      this.ah = true;
      this.angler = var8;
      var8.fishEntity = this;
   }

   @Override
   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
      this.fishX = var1;
      this.fishY = var3;
      this.fishZ = var5;
      this.fishYaw = var7;
      this.fishPitch = var8;
      this.fishPosRotationIncrements = var9;
      this.v = this.clientMotionX;
      this.w = this.clientMotionY;
      this.x = this.clientMotionZ;
   }
}
