package net.minecraft.entity.passive;

import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$FitSimpleRoomHelper;
import net.optifine.config.RangeListInt;

public class EntityBat extends EntityAmbientCreature {
   public BlockPos spawnPosition;
   public StructureOceanMonumentPieces$FitSimpleRoomHelper field_0000;
   public RangeListInt field_0001;
   public RtspResponseStatuses field_0003;

   public boolean getIsBatHanging() {
      return (this.ac.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public float getEyeHeight() {
      return this.K / 2.0F;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         if (!this.o.D && this.getIsBatHanging()) {
            this.setIsBatHanging(false);
         }

         return super.attackEntityFrom(var1, var2);
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.bat.hurt";
   }

   @Override
   public void collideWithEntity(Entity var1) {
   }

   @Override
   public void collideWithNearbyEntities() {
   }

   @Override
   public boolean m_() {
      return false;
   }

   @Override
   public void updateAITasks() {
      super.updateAITasks();
      BlockPos var1 = new BlockPos(this);
      BlockPos var2 = var1.up();
      if (this.getIsBatHanging()) {
         if (!this.o.getBlockState(var2).getBlock().isNormalCube()) {
            this.setIsBatHanging(false);
            this.o.playAuxSFXAtEntity((EntityPlayer)null, 1015, var1, 0);
         } else {
            if (this.V.nextInt(200) == 0) {
               this.aK = this.V.nextInt(360);
            }

            if (this.o.getClosestPlayerToEntity(this, 4.0) != null) {
               this.setIsBatHanging(false);
               this.o.playAuxSFXAtEntity((EntityPlayer)null, 1015, var1, 0);
            }
         }
      } else {
         if (this.spawnPosition != null && (!this.o.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
            this.spawnPosition = null;
         }

         if (this.spawnPosition == null || this.V.nextInt(30) == 0 || this.spawnPosition.distanceSq((int)this.s, (int)this.t, (int)this.u) < 4.0) {
            this.spawnPosition = new BlockPos(
               (int)this.s + this.V.nextInt(7) - this.V.nextInt(7), (int)this.t + this.V.nextInt(6) - 2, (int)this.u + this.V.nextInt(7) - this.V.nextInt(7)
            );
         }

         double var3 = this.spawnPosition.getX() + 0.5 - this.s;
         double var5 = this.spawnPosition.getY() + 0.1 - this.t;
         double var7 = this.spawnPosition.getZ() + 0.5 - this.u;
         this.v = this.v + (Math.signum(var3) * 0.5 - this.v) * 0.1F;
         this.w = this.w + (Math.signum(var5) * 0.7F - this.w) * 0.1F;
         this.x = this.x + (Math.signum(var7) * 0.5 - this.x) * 0.1F;
         float var9 = (float)(MathHelper.atan2(this.x, this.v) * 180.0 / Math.PI) - 90.0F;
         float var10 = MathHelper.wrapAngleTo180_float(var9 - this.y);
         this.ba = 0.5F;
         this.y += var10;
         if (this.V.nextInt(100) == 0 && this.o.getBlockState(var2).getBlock().isNormalCube()) {
            this.setIsBatHanging(true);
         }
      }
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, new Byte((byte)0));
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setByte("BatFlags", this.ac.getWatchableObjectByte(16));
   }

   @Override
   public boolean doesEntityNotTriggerPressurePlate() {
      return true;
   }

   public void setIsBatHanging(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         this.ac.updateObject(16, (byte)(var2 | 1));
      } else {
         this.ac.updateObject(16, (byte)(var2 & -2));
      }
   }

   @Override
   public void fall(float var1, float var2) {
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.getIsBatHanging()) {
         this.v = this.w = this.x = 0.0;
         this.t = MathHelper.floor_double(this.t) + 1.0 - this.K;
      } else {
         this.w *= 0.6F;
      }
   }

   @Override
   public boolean getCanSpawnHere() {
      BlockPos var1 = new BlockPos(this.s, this.getEntityBoundingBox().b, this.u);
      if (var1.getY() >= this.o.F()) {
         return false;
      } else {
         int var2 = this.o.getLightFromNeighbors(var1);
         byte var3 = 4;
         if (this.isDateAroundHalloween(this.o.getCurrentDate())) {
            var3 = 7;
         } else if (this.V.nextBoolean()) {
            return false;
         }

         return var2 > this.V.nextInt(var3) ? false : super.getCanSpawnHere();
      }
   }

   public EntityBat(World var1) {
      super(var1);
      this.setSize(0.5F, 0.9F);
      this.setIsBatHanging(true);
   }

   @Override
   public void updateFallState(double var1, boolean var3, Block var4, BlockPos var5) {
   }

   @Override
   public boolean l_() {
      return false;
   }

   @Override
   public float getSoundVolume() {
      return 0.1F;
   }

   @Override
   public float bC() {
      return super.bC() * 0.95F;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.ac.updateObject(16, var1.getByte("BatFlags"));
   }

   @Override
   public String getLivingSound() {
      return this.getIsBatHanging() && this.V.nextInt(4) != 0 ? null : "mob.bat.idle";
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0);
   }

   public boolean isDateAroundHalloween(Calendar var1) {
      return var1.get(2) + 1 == 10 && var1.get(5) >= 20 || var1.get(2) + 1 == 11 && var1.get(5) <= 3;
   }

   @Override
   public String getDeathSound() {
      return "mob.bat.death";
   }
}
