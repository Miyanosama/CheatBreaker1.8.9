package net.minecraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.ThreadDeathWatcher$Entry;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.block.BlockStem;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.network.Packet;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.apache.log4j.lf5.util.LogFileParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTracker {
   public Set<EntityTrackerEntry> trackedEntities = Sets.newHashSet();
   public ThreadDeathWatcher$Entry field_0007;
   public static Logger logger = LogManager.getLogger();
   public int maxTrackingDistanceThreshold;
   public EntityEnchantmentTableParticleFX field_0000;
   public SelfSignedCertificate field_0001;
   public WorldServer theWorld;
   public IntHashMap<EntityTrackerEntry> trackedEntityHashTable = new IntHashMap<>();
   public LogFileParser field_0002;
   public BlockStem field_0009;

   public void func_151248_b(Entity var1, Packet var2) {
      EntityTrackerEntry var3 = this.trackedEntityHashTable.lookup(var1.F());
      if (var3 != null) {
         var3.func_151261_b(var2);
      }
   }

   public void updateTrackedEntities() {
      ArrayList var1 = Lists.newArrayList();

      for (EntityTrackerEntry var3 : this.trackedEntities) {
         var3.updatePlayerList(this.theWorld.j);
         if (var3.playerEntitiesUpdated && var3.trackedEntity instanceof EntityPlayerMP) {
            var1.add((EntityPlayerMP)var3.trackedEntity);
         }
      }

      for (int var6 = 0; var6 < var1.size(); var6++) {
         EntityPlayerMP var7 = (EntityPlayerMP)var1.get(var6);

         for (EntityTrackerEntry var5 : this.trackedEntities) {
            if (var5.trackedEntity != var7) {
               var5.updatePlayerEntity(var7);
            }
         }
      }
   }

   public void func_85172_a(EntityPlayerMP var1, Chunk var2) {
      for (EntityTrackerEntry var4 : this.trackedEntities) {
         if (var4.trackedEntity != var1 && var4.trackedEntity.chunkCoordX == var2.a && var4.trackedEntity.chunkCoordZ == var2.b) {
            var4.updatePlayerEntity(var1);
         }
      }
   }

   public void trackEntity(Entity var1, int var2, int var3) {
      this.addEntityToTracker(var1, var2, var3, false);
   }

   public void untrackEntity(Entity var1) {
      if (var1 instanceof EntityPlayerMP) {
         EntityPlayerMP var2 = (EntityPlayerMP)var1;

         for (EntityTrackerEntry var4 : this.trackedEntities) {
            var4.removeFromTrackedPlayers(var2);
         }
      }

      EntityTrackerEntry var5 = this.trackedEntityHashTable.removeObject(var1.F());
      if (var5 != null) {
         this.trackedEntities.remove(var5);
         var5.sendDestroyEntityPacketToTrackedPlayers();
      }
   }

   public void sendToAllTrackingEntity(Entity var1, Packet var2) {
      EntityTrackerEntry var3 = this.trackedEntityHashTable.lookup(var1.F());
      if (var3 != null) {
         var3.sendPacketToTrackedPlayers(var2);
      }
   }

   public void func_180245_a(EntityPlayerMP var1) {
      for (EntityTrackerEntry var3 : this.trackedEntities) {
         if (var3.trackedEntity == var1) {
            var3.updatePlayerEntities(this.theWorld.j);
         } else {
            var3.updatePlayerEntity(var1);
         }
      }
   }

   public void trackEntity(Entity var1) {
      if (var1 instanceof EntityPlayerMP) {
         this.trackEntity(var1, 512, 2);
         EntityPlayerMP var2 = (EntityPlayerMP)var1;

         for (EntityTrackerEntry var4 : this.trackedEntities) {
            if (var4.trackedEntity != var2) {
               var4.updatePlayerEntity(var2);
            }
         }
      } else if (var1 instanceof EntityFishHook) {
         this.addEntityToTracker(var1, 64, 5, true);
      } else if (var1 instanceof EntityArrow) {
         this.addEntityToTracker(var1, 64, 20, false);
      } else if (var1 instanceof EntitySmallFireball) {
         this.addEntityToTracker(var1, 64, 10, false);
      } else if (var1 instanceof EntityFireball) {
         this.addEntityToTracker(var1, 64, 10, false);
      } else if (var1 instanceof EntitySnowball) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if (var1 instanceof EntityEnderPearl) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if (var1 instanceof EntityEnderEye) {
         this.addEntityToTracker(var1, 64, 4, true);
      } else if (var1 instanceof EntityEgg) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if (var1 instanceof EntityPotion) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if (var1 instanceof EntityExpBottle) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if (var1 instanceof EntityFireworkRocket) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if (var1 instanceof EntityItem) {
         this.addEntityToTracker(var1, 64, 20, true);
      } else if (var1 instanceof EntityMinecart) {
         this.addEntityToTracker(var1, 80, 3, true);
      } else if (var1 instanceof EntityBoat) {
         this.addEntityToTracker(var1, 80, 3, true);
      } else if (var1 instanceof EntitySquid) {
         this.addEntityToTracker(var1, 64, 3, true);
      } else if (var1 instanceof EntityWither) {
         this.addEntityToTracker(var1, 80, 3, false);
      } else if (var1 instanceof EntityBat) {
         this.addEntityToTracker(var1, 80, 3, false);
      } else if (var1 instanceof EntityDragon) {
         this.addEntityToTracker(var1, 160, 3, true);
      } else if (var1 instanceof IAnimals) {
         this.addEntityToTracker(var1, 80, 3, true);
      } else if (var1 instanceof EntityTNTPrimed) {
         this.addEntityToTracker(var1, 160, 10, true);
      } else if (var1 instanceof EntityFallingBlock) {
         this.addEntityToTracker(var1, 160, 20, true);
      } else if (var1 instanceof EntityHanging) {
         this.addEntityToTracker(var1, 160, Integer.MAX_VALUE, false);
      } else if (var1 instanceof EntityArmorStand) {
         this.addEntityToTracker(var1, 160, 3, true);
      } else if (var1 instanceof EntityXPOrb) {
         this.addEntityToTracker(var1, 160, 20, true);
      } else if (var1 instanceof EntityEnderCrystal) {
         this.addEntityToTracker(var1, 256, Integer.MAX_VALUE, false);
      }
   }

   public void addEntityToTracker(Entity var1, int var2, int var3, boolean var4) {
      if (var2 > this.maxTrackingDistanceThreshold) {
         var2 = this.maxTrackingDistanceThreshold;
      }

      try {
         if (this.trackedEntityHashTable.containsItem(var1.F())) {
            throw new IllegalStateException("Entity is already tracked!");
         }

         EntityTrackerEntry var5 = new EntityTrackerEntry(var1, var2, var3, var4);
         this.trackedEntities.add(var5);
         this.trackedEntityHashTable.addKey(var1.F(), var5);
         var5.updatePlayerEntities(this.theWorld.j);
      } catch (Throwable var11) {
         CrashReport var6 = CrashReport.makeCrashReport(var11, "Adding entity to track");
         CrashReportCategory var7 = var6.makeCategory("Entity To Track");
         var7.addCrashSection("Tracking range", var2 + " blocks");
         var7.addCrashSectionCallable("Update interval", new EntityTracker$1(this, var3));
         var1.addEntityCrashInfo(var7);
         CrashReportCategory var8 = var6.makeCategory("Entity That Is Already Tracked");
         this.trackedEntityHashTable.lookup(var1.F()).trackedEntity.addEntityCrashInfo(var8);

         try {
            throw new ReportedException(var6);
         } catch (ReportedException var10) {
            logger.error("\"Silently\" catching entity tracking error.", var10);
         }
      }
   }

   public void removePlayerFromTrackers(EntityPlayerMP var1) {
      for (EntityTrackerEntry var3 : this.trackedEntities) {
         var3.removeTrackedPlayerSymmetric(var1);
      }
   }

   public EntityTracker(WorldServer var1) {
      this.theWorld = var1;
      this.maxTrackingDistanceThreshold = var1.getMinecraftServer().getConfigurationManager().getEntityViewDistance();
   }
}
