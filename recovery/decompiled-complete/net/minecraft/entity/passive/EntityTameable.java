package net.minecraft.entity.passive;

import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S3CPacketUpdateScore$Action;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.optifine.texture.PixelType;

public abstract class EntityTameable extends EntityAnimal implements IEntityOwnable {
   public PixelType field_0000;
   public EntityAISit bm = new EntityAISit(this);
   public S3CPacketUpdateScore$Action field_0002;

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      if (this.getOwnerId() == null) {
         var1.setString("OwnerUUID", "");
      } else {
         var1.setString("OwnerUUID", this.getOwnerId());
      }

      var1.setBoolean("Sitting", this.isSitting());
   }

   public void setOwnerId(String var1) {
      this.ac.updateObject(17, var1);
   }

   public boolean isTamed() {
      return (this.ac.getWatchableObjectByte(16) & 4) != 0;
   }

   public EntityLivingBase getOwner() {
      try {
         UUID var1 = UUID.fromString(this.getOwnerId());
         return var1 == null ? null : this.o.getPlayerEntityByUUID(var1);
      } catch (IllegalArgumentException var2) {
         return null;
      }
   }

   @Override
   public Team getTeam() {
      if (this.isTamed()) {
         EntityLivingBase var1 = this.getOwner();
         if (var1 != null) {
            return var1.getTeam();
         }
      }

      return super.getTeam();
   }

   @Override
   public void onDeath(DamageSource var1) {
      if (!this.o.D && this.o.Q().getBoolean("showDeathMessages") && this.u_() && this.getOwner() instanceof EntityPlayerMP) {
         ((EntityPlayerMP)this.getOwner()).addChatMessage(this.getCombatTracker().getDeathMessage());
      }

      super.onDeath(var1);
   }

   public boolean isOwner(EntityLivingBase var1) {
      return var1 == this.getOwner();
   }

   public void setSitting(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         this.ac.updateObject(16, (byte)(var2 | 1));
      } else {
         this.ac.updateObject(16, (byte)(var2 & -2));
      }
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, (byte)0);
      this.ac.addObject(17, "");
   }

   public void playTameEffect(boolean var1) {
      EnumParticleTypes var2 = EnumParticleTypes.HEART;
      if (!var1) {
         var2 = EnumParticleTypes.SMOKE_NORMAL;
      }

      for (int var3 = 0; var3 < 7; var3++) {
         double var4 = this.V.nextGaussian() * 0.02;
         double var6 = this.V.nextGaussian() * 0.02;
         double var8 = this.V.nextGaussian() * 0.02;
         this.o
            .spawnParticle(
               var2,
               this.s + this.V.nextFloat() * this.J * 2.0F - this.J,
               this.t + 0.5 + this.V.nextFloat() * this.K,
               this.u + this.V.nextFloat() * this.J * 2.0F - this.J,
               var4,
               var6,
               var8
            );
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      String var2 = "";
      if (var1.hasKey("OwnerUUID", 8)) {
         var2 = var1.getString("OwnerUUID");
      } else {
         String var3 = var1.getString("Owner");
         var2 = PreYggdrasilConverter.getStringUUIDFromName(var3);
      }

      if (var2.length() > 0) {
         this.setOwnerId(var2);
         this.setTamed(true);
      }

      this.bm.setSitting(var1.getBoolean("Sitting"));
      this.setSitting(var1.getBoolean("Sitting"));
   }

   public EntityTameable(World var1) {
      super(var1);
      this.setupTamedAI();
   }

   public boolean isSitting() {
      return (this.ac.getWatchableObjectByte(16) & 1) != 0;
   }

   public boolean shouldAttackEntity(EntityLivingBase var1, EntityLivingBase var2) {
      return true;
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 7) {
         this.playTameEffect(true);
      } else if (var1 == 6) {
         this.playTameEffect(false);
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   public void setTamed(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         this.ac.updateObject(16, (byte)(var2 | 4));
      } else {
         this.ac.updateObject(16, (byte)(var2 & -5));
      }

      this.setupTamedAI();
   }

   public EntityAISit cp() {
      return this.bm;
   }

   @Override
   public boolean isOnSameTeam(EntityLivingBase var1) {
      if (this.isTamed()) {
         EntityLivingBase var2 = this.getOwner();
         if (var1 == var2) {
            return true;
         }

         if (var2 != null) {
            return var2.isOnSameTeam(var1);
         }
      }

      return super.isOnSameTeam(var1);
   }

   @Override
   public String getOwnerId() {
      return this.ac.getWatchableObjectString(17);
   }

   public void setupTamedAI() {
   }
}
