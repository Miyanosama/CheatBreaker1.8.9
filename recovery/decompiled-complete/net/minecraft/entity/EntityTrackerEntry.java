package net.minecraft.entity;

import com.google.common.collect.Sets;
import io.netty.buffer.ByteBufInputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport$3;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S14PacketEntity$S15PacketEntityRelMove;
import net.minecraft.network.play.server.S14PacketEntity$S16PacketEntityLook;
import net.minecraft.network.play.server.S14PacketEntity$S17PacketEntityLookMove;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recovered.unidentified.UnidentifiedClass5123;

public class EntityTrackerEntry {
   public int encodedPosX;
   public boolean onGround;
   public int field_0012;
   public boolean field_0022;
   public CrashReport$3 field_0006;
   public int encodedRotationPitch;
   public double field_0026;
   public boolean playerEntitiesUpdated;
   public boolean field_0008;
   public double field_0027;
   public UnidentifiedClass5123 field_0005;
   public boolean sendVelocityUpdates;
   public static Logger logger = LogManager.getLogger();
   public int field_0010;
   public int encodedPosY;
   public double field_0024;
   public double field_0003;
   public Entity trackedEntity;
   public Set<EntityPlayerMP> trackingPlayers = Sets.newHashSet();
   public Entity field_85178_v;
   public int encodedRotationYaw;
   public ByteBufInputStream field_0001;
   public int updateFrequency;
   public double field_0000;
   public int trackingDistanceThreshold;
   public int encodedPosZ;
   public int lastHeadMotion;
   public double field_0011;

   public boolean func_180233_c(EntityPlayerMP var1) {
      double var2 = var1.s - this.encodedPosX / 32;
      double var4 = var1.u - this.encodedPosZ / 32;
      return var2 >= -this.trackingDistanceThreshold
         && var2 <= this.trackingDistanceThreshold
         && var4 >= -this.trackingDistanceThreshold
         && var4 <= this.trackingDistanceThreshold
         && this.trackedEntity.isSpectatedByPlayer(var1);
   }

   public void sendDestroyEntityPacketToTrackedPlayers() {
      for (EntityPlayerMP var2 : this.trackingPlayers) {
         var2.removeEntity(this.trackedEntity);
      }
   }

   public void updatePlayerEntity(EntityPlayerMP var1) {
      if (var1 != this.trackedEntity) {
         if (this.func_180233_c(var1)) {
            if (!this.trackingPlayers.contains(var1) && (this.isPlayerWatchingThisChunk(var1) || this.trackedEntity.n)) {
               this.trackingPlayers.add(var1);
               Packet var2 = this.createSpawnPacket();
               var1.playerNetServerHandler.sendPacket(var2);
               if (!this.trackedEntity.H().getIsBlank()) {
                  var1.playerNetServerHandler.sendPacket(new S1CPacketEntityMetadata(this.trackedEntity.F(), this.trackedEntity.H(), true));
               }

               NBTTagCompound var3 = this.trackedEntity.getNBTTagCompound();
               if (var3 != null) {
                  var1.playerNetServerHandler.sendPacket(new S49PacketUpdateEntityNBT(this.trackedEntity.F(), var3));
               }

               if (this.trackedEntity instanceof EntityLivingBase) {
                  ServersideAttributeMap var4 = (ServersideAttributeMap)((EntityLivingBase)this.trackedEntity).getAttributeMap();
                  Collection var5 = var4.getWatchedAttributes();
                  if (!var5.isEmpty()) {
                     var1.playerNetServerHandler.sendPacket(new S20PacketEntityProperties(this.trackedEntity.F(), var5));
                  }
               }

               this.field_0027 = this.trackedEntity.v;
               this.field_0026 = this.trackedEntity.w;
               this.field_0003 = this.trackedEntity.x;
               if (this.sendVelocityUpdates && !(var2 instanceof S0FPacketSpawnMob)) {
                  var1.playerNetServerHandler
                     .sendPacket(new S12PacketEntityVelocity(this.trackedEntity.F(), this.trackedEntity.v, this.trackedEntity.w, this.trackedEntity.x));
               }

               if (this.trackedEntity.m != null) {
                  var1.playerNetServerHandler.sendPacket(new S1BPacketEntityAttach(0, this.trackedEntity, this.trackedEntity.m));
               }

               if (this.trackedEntity instanceof EntityLiving && ((EntityLiving)this.trackedEntity).getLeashedToEntity() != null) {
                  var1.playerNetServerHandler
                     .sendPacket(new S1BPacketEntityAttach(1, this.trackedEntity, ((EntityLiving)this.trackedEntity).getLeashedToEntity()));
               }

               if (this.trackedEntity instanceof EntityLivingBase) {
                  for (int var7 = 0; var7 < 5; var7++) {
                     ItemStack var10 = ((EntityLivingBase)this.trackedEntity).getEquipmentInSlot(var7);
                     if (var10 != null) {
                        var1.playerNetServerHandler.sendPacket(new S04PacketEntityEquipment(this.trackedEntity.F(), var7, var10));
                     }
                  }
               }

               if (this.trackedEntity instanceof EntityPlayer) {
                  EntityPlayer var8 = (EntityPlayer)this.trackedEntity;
                  if (var8.bJ()) {
                     var1.playerNetServerHandler.sendPacket(new S0APacketUseBed(var8, new BlockPos(this.trackedEntity)));
                  }
               }

               if (this.trackedEntity instanceof EntityLivingBase) {
                  EntityLivingBase var9 = (EntityLivingBase)this.trackedEntity;

                  for (PotionEffect var6 : var9.getActivePotionEffects()) {
                     var1.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.trackedEntity.F(), var6));
                  }
               }
            }
         } else if (this.trackingPlayers.contains(var1)) {
            this.trackingPlayers.remove(var1);
            var1.removeEntity(this.trackedEntity);
         }
      }
   }

   public Packet createSpawnPacket() {
      if (this.trackedEntity.I) {
         logger.warn("Fetching addPacket for removed entity");
      }

      if (this.trackedEntity instanceof EntityItem) {
         return new S0EPacketSpawnObject(this.trackedEntity, 2, 1);
      } else if (this.trackedEntity instanceof EntityPlayerMP) {
         return new S0CPacketSpawnPlayer((EntityPlayer)this.trackedEntity);
      } else if (this.trackedEntity instanceof EntityMinecart) {
         EntityMinecart var9 = (EntityMinecart)this.trackedEntity;
         return new S0EPacketSpawnObject(this.trackedEntity, 10, var9.getMinecartType().getNetworkID());
      } else if (this.trackedEntity instanceof EntityBoat) {
         return new S0EPacketSpawnObject(this.trackedEntity, 1);
      } else if (this.trackedEntity instanceof IAnimals) {
         this.lastHeadMotion = MathHelper.floor_float(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
         return new S0FPacketSpawnMob((EntityLivingBase)this.trackedEntity);
      } else if (this.trackedEntity instanceof EntityFishHook) {
         EntityPlayer var8 = ((EntityFishHook)this.trackedEntity).angler;
         return new S0EPacketSpawnObject(this.trackedEntity, 90, var8 != null ? var8.F() : this.trackedEntity.F());
      } else if (this.trackedEntity instanceof EntityArrow) {
         Entity var7 = ((EntityArrow)this.trackedEntity).shootingEntity;
         return new S0EPacketSpawnObject(this.trackedEntity, 60, var7 != null ? var7.F() : this.trackedEntity.F());
      } else if (this.trackedEntity instanceof EntitySnowball) {
         return new S0EPacketSpawnObject(this.trackedEntity, 61);
      } else if (this.trackedEntity instanceof EntityPotion) {
         return new S0EPacketSpawnObject(this.trackedEntity, 73, ((EntityPotion)this.trackedEntity).getPotionDamage());
      } else if (this.trackedEntity instanceof EntityExpBottle) {
         return new S0EPacketSpawnObject(this.trackedEntity, 75);
      } else if (this.trackedEntity instanceof EntityEnderPearl) {
         return new S0EPacketSpawnObject(this.trackedEntity, 65);
      } else if (this.trackedEntity instanceof EntityEnderEye) {
         return new S0EPacketSpawnObject(this.trackedEntity, 72);
      } else if (this.trackedEntity instanceof EntityFireworkRocket) {
         return new S0EPacketSpawnObject(this.trackedEntity, 76);
      } else if (this.trackedEntity instanceof EntityFireball) {
         EntityFireball var6 = (EntityFireball)this.trackedEntity;
         S0EPacketSpawnObject var11 = null;
         byte var14 = 63;
         if (this.trackedEntity instanceof EntitySmallFireball) {
            var14 = 64;
         } else if (this.trackedEntity instanceof EntityWitherSkull) {
            var14 = 66;
         }

         if (var6.a != null) {
            var11 = new S0EPacketSpawnObject(this.trackedEntity, var14, ((EntityFireball)this.trackedEntity).a.F());
         } else {
            var11 = new S0EPacketSpawnObject(this.trackedEntity, var14, 0);
         }

         var11.setSpeedX((int)(var6.accelerationX * 8000.0));
         var11.setSpeedY((int)(var6.accelerationY * 8000.0));
         var11.setSpeedZ((int)(var6.accelerationZ * 8000.0));
         return var11;
      } else if (this.trackedEntity instanceof EntityEgg) {
         return new S0EPacketSpawnObject(this.trackedEntity, 62);
      } else if (this.trackedEntity instanceof EntityTNTPrimed) {
         return new S0EPacketSpawnObject(this.trackedEntity, 50);
      } else if (this.trackedEntity instanceof EntityEnderCrystal) {
         return new S0EPacketSpawnObject(this.trackedEntity, 51);
      } else if (this.trackedEntity instanceof EntityFallingBlock) {
         EntityFallingBlock var5 = (EntityFallingBlock)this.trackedEntity;
         return new S0EPacketSpawnObject(this.trackedEntity, 70, Block.getStateId(var5.getBlock()));
      } else if (this.trackedEntity instanceof EntityArmorStand) {
         return new S0EPacketSpawnObject(this.trackedEntity, 78);
      } else if (this.trackedEntity instanceof EntityPainting) {
         return new S10PacketSpawnPainting((EntityPainting)this.trackedEntity);
      } else if (this.trackedEntity instanceof EntityItemFrame) {
         EntityItemFrame var4 = (EntityItemFrame)this.trackedEntity;
         S0EPacketSpawnObject var10 = new S0EPacketSpawnObject(this.trackedEntity, 71, var4.b.getHorizontalIndex());
         BlockPos var13 = var4.n();
         var10.setX(MathHelper.floor_float(var13.getX() * 32));
         var10.setY(MathHelper.floor_float(var13.getY() * 32));
         var10.setZ(MathHelper.floor_float(var13.getZ() * 32));
         return var10;
      } else if (this.trackedEntity instanceof EntityLeashKnot) {
         EntityLeashKnot var1 = (EntityLeashKnot)this.trackedEntity;
         S0EPacketSpawnObject var2 = new S0EPacketSpawnObject(this.trackedEntity, 77);
         BlockPos var3 = var1.n();
         var2.setX(MathHelper.floor_float(var3.getX() * 32));
         var2.setY(MathHelper.floor_float(var3.getY() * 32));
         var2.setZ(MathHelper.floor_float(var3.getZ() * 32));
         return var2;
      } else if (this.trackedEntity instanceof EntityXPOrb) {
         return new S11PacketSpawnExperienceOrb((EntityXPOrb)this.trackedEntity);
      } else {
         throw new IllegalArgumentException("Don't know how to add " + this.trackedEntity.getClass() + "!");
      }
   }

   public EntityTrackerEntry(Entity var1, int var2, int var3, boolean var4) {
      this.trackedEntity = var1;
      this.trackingDistanceThreshold = var2;
      this.updateFrequency = var3;
      this.sendVelocityUpdates = var4;
      this.encodedPosX = MathHelper.floor_double(var1.s * 32.0);
      this.encodedPosY = MathHelper.floor_double(var1.t * 32.0);
      this.encodedPosZ = MathHelper.floor_double(var1.u * 32.0);
      this.encodedRotationYaw = MathHelper.floor_float(var1.y * 256.0F / 360.0F);
      this.encodedRotationPitch = MathHelper.floor_float(var1.z * 256.0F / 360.0F);
      this.lastHeadMotion = MathHelper.floor_float(var1.getRotationYawHead() * 256.0F / 360.0F);
      this.onGround = var1.C;
   }

   public void removeTrackedPlayerSymmetric(EntityPlayerMP var1) {
      if (this.trackingPlayers.contains(var1)) {
         this.trackingPlayers.remove(var1);
         var1.removeEntity(this.trackedEntity);
      }
   }

   public void func_151261_b(Packet var1) {
      this.sendPacketToTrackedPlayers(var1);
      if (this.trackedEntity instanceof EntityPlayerMP) {
         ((EntityPlayerMP)this.trackedEntity).playerNetServerHandler.sendPacket(var1);
      }
   }

   public void updatePlayerEntities(List<EntityPlayer> var1) {
      for (int var2 = 0; var2 < var1.size(); var2++) {
         this.updatePlayerEntity((EntityPlayerMP)var1.get(var2));
      }
   }

   public void sendMetadataToAllAssociatedPlayers() {
      DataWatcher var1 = this.trackedEntity.H();
      if (var1.hasObjectChanged()) {
         this.func_151261_b(new S1CPacketEntityMetadata(this.trackedEntity.F(), var1, false));
      }

      if (this.trackedEntity instanceof EntityLivingBase) {
         ServersideAttributeMap var2 = (ServersideAttributeMap)((EntityLivingBase)this.trackedEntity).getAttributeMap();
         Set var3 = var2.getAttributeInstanceSet();
         if (!var3.isEmpty()) {
            this.func_151261_b(new S20PacketEntityProperties(this.trackedEntity.F(), var3));
         }

         var3.clear();
      }
   }

   public boolean isPlayerWatchingThisChunk(EntityPlayerMP var1) {
      return var1.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(var1, this.trackedEntity.chunkCoordX, this.trackedEntity.chunkCoordZ);
   }

   public void removeFromTrackedPlayers(EntityPlayerMP var1) {
      if (this.trackingPlayers.contains(var1)) {
         var1.removeEntity(this.trackedEntity);
         this.trackingPlayers.remove(var1);
      }
   }

   public void updatePlayerList(List<EntityPlayer> var1) {
      this.playerEntitiesUpdated = false;
      if (!this.field_0022 || this.trackedEntity.e(this.field_0024, this.field_0000, this.field_0011) > 16.0) {
         this.field_0024 = this.trackedEntity.s;
         this.field_0000 = this.trackedEntity.t;
         this.field_0011 = this.trackedEntity.u;
         this.field_0022 = true;
         this.playerEntitiesUpdated = true;
         this.updatePlayerEntities(var1);
      }

      if (this.field_85178_v != this.trackedEntity.m || this.trackedEntity.m != null && this.field_0012 % 60 == 0) {
         this.field_85178_v = this.trackedEntity.m;
         this.sendPacketToTrackedPlayers(new S1BPacketEntityAttach(0, this.trackedEntity, this.trackedEntity.m));
      }

      if (this.trackedEntity instanceof EntityItemFrame && this.field_0012 % 10 == 0) {
         EntityItemFrame var2 = (EntityItemFrame)this.trackedEntity;
         ItemStack var3 = var2.getDisplayedItem();
         if (var3 != null && var3.getItem() instanceof ItemMap) {
            MapData var4 = Items.filled_map.getMapData(var3, this.trackedEntity.o);

            for (EntityPlayer var6 : var1) {
               EntityPlayerMP var7 = (EntityPlayerMP)var6;
               var4.updateVisiblePlayers(var7, var3);
               Packet var8 = Items.filled_map.createMapDataPacket(var3, this.trackedEntity.o, var7);
               if (var8 != null) {
                  var7.playerNetServerHandler.sendPacket(var8);
               }
            }
         }

         this.sendMetadataToAllAssociatedPlayers();
      }

      if (this.field_0012 % this.updateFrequency == 0 || this.trackedEntity.ai || this.trackedEntity.H().hasObjectChanged()) {
         if (this.trackedEntity.m == null) {
            this.field_0010++;
            int var24 = MathHelper.floor_double(this.trackedEntity.s * 32.0);
            int var27 = MathHelper.floor_double(this.trackedEntity.t * 32.0);
            int var29 = MathHelper.floor_double(this.trackedEntity.u * 32.0);
            int var30 = MathHelper.floor_float(this.trackedEntity.y * 256.0F / 360.0F);
            int var31 = MathHelper.floor_float(this.trackedEntity.z * 256.0F / 360.0F);
            int var32 = var24 - this.encodedPosX;
            int var33 = var27 - this.encodedPosY;
            int var9 = var29 - this.encodedPosZ;
            Object var10 = null;
            boolean var11 = Math.abs(var32) >= 4 || Math.abs(var33) >= 4 || Math.abs(var9) >= 4 || this.field_0012 % 60 == 0;
            boolean var12 = Math.abs(var30 - this.encodedRotationYaw) >= 4 || Math.abs(var31 - this.encodedRotationPitch) >= 4;
            if (this.field_0012 > 0 || this.trackedEntity instanceof EntityArrow) {
               if (var32 >= -128
                  && var32 < 128
                  && var33 >= -128
                  && var33 < 128
                  && var9 >= -128
                  && var9 < 128
                  && this.field_0010 <= 400
                  && !this.field_0008
                  && this.onGround == this.trackedEntity.C) {
                  if ((!var11 || !var12) && !(this.trackedEntity instanceof EntityArrow)) {
                     if (var11) {
                        var10 = new S14PacketEntity$S15PacketEntityRelMove(this.trackedEntity.F(), (byte)var32, (byte)var33, (byte)var9, this.trackedEntity.C);
                     } else if (var12) {
                        var10 = new S14PacketEntity$S16PacketEntityLook(this.trackedEntity.F(), (byte)var30, (byte)var31, this.trackedEntity.C);
                     }
                  } else {
                     var10 = new S14PacketEntity$S17PacketEntityLookMove(
                        this.trackedEntity.F(), (byte)var32, (byte)var33, (byte)var9, (byte)var30, (byte)var31, this.trackedEntity.C
                     );
                  }
               } else {
                  this.onGround = this.trackedEntity.C;
                  this.field_0010 = 0;
                  var10 = new S18PacketEntityTeleport(this.trackedEntity.F(), var24, var27, var29, (byte)var30, (byte)var31, this.trackedEntity.C);
               }
            }

            if (this.sendVelocityUpdates) {
               double var13 = this.trackedEntity.v - this.field_0027;
               double var15 = this.trackedEntity.w - this.field_0026;
               double var17 = this.trackedEntity.x - this.field_0003;
               double var19 = 0.02;
               double var21 = var13 * var13 + var15 * var15 + var17 * var17;
               if (var21 > var19 * var19 || var21 > 0.0 && this.trackedEntity.v == 0.0 && this.trackedEntity.w == 0.0 && this.trackedEntity.x == 0.0) {
                  this.field_0027 = this.trackedEntity.v;
                  this.field_0026 = this.trackedEntity.w;
                  this.field_0003 = this.trackedEntity.x;
                  this.sendPacketToTrackedPlayers(new S12PacketEntityVelocity(this.trackedEntity.F(), this.field_0027, this.field_0026, this.field_0003));
               }
            }

            if (var10 != null) {
               this.sendPacketToTrackedPlayers((Packet)var10);
            }

            this.sendMetadataToAllAssociatedPlayers();
            if (var11) {
               this.encodedPosX = var24;
               this.encodedPosY = var27;
               this.encodedPosZ = var29;
            }

            if (var12) {
               this.encodedRotationYaw = var30;
               this.encodedRotationPitch = var31;
            }

            this.field_0008 = false;
         } else {
            int var23 = MathHelper.floor_float(this.trackedEntity.y * 256.0F / 360.0F);
            int var26 = MathHelper.floor_float(this.trackedEntity.z * 256.0F / 360.0F);
            boolean var28 = Math.abs(var23 - this.encodedRotationYaw) >= 4 || Math.abs(var26 - this.encodedRotationPitch) >= 4;
            if (var28) {
               this.sendPacketToTrackedPlayers(new S14PacketEntity$S16PacketEntityLook(this.trackedEntity.F(), (byte)var23, (byte)var26, this.trackedEntity.C));
               this.encodedRotationYaw = var23;
               this.encodedRotationPitch = var26;
            }

            this.encodedPosX = MathHelper.floor_double(this.trackedEntity.s * 32.0);
            this.encodedPosY = MathHelper.floor_double(this.trackedEntity.t * 32.0);
            this.encodedPosZ = MathHelper.floor_double(this.trackedEntity.u * 32.0);
            this.sendMetadataToAllAssociatedPlayers();
            this.field_0008 = true;
         }

         int var25 = MathHelper.floor_float(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
         if (Math.abs(var25 - this.lastHeadMotion) >= 4) {
            this.sendPacketToTrackedPlayers(new S19PacketEntityHeadLook(this.trackedEntity, (byte)var25));
            this.lastHeadMotion = var25;
         }

         this.trackedEntity.ai = false;
      }

      this.field_0012++;
      if (this.trackedEntity.G) {
         this.func_151261_b(new S12PacketEntityVelocity(this.trackedEntity));
         this.trackedEntity.G = false;
      }
   }

   @Override
   public int hashCode() {
      return this.trackedEntity.F();
   }

   public void sendPacketToTrackedPlayers(Packet var1) {
      for (EntityPlayerMP var3 : this.trackingPlayers) {
         var3.playerNetServerHandler.sendPacket(var1);
      }
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof EntityTrackerEntry ? ((EntityTrackerEntry)var1).trackedEntity.F() == this.trackedEntity.F() : false;
   }
}
