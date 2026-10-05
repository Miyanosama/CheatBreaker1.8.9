package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import io.netty.util.concurrent.DefaultPromise$1;
import io.netty.util.internal.chmv8.ForkJoinTask$AdaptedRunnable;
import java.util.List;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import org.newsclub.net.unix.AFUNIXSocketImpl$Lenient;

public abstract class MobSpawnerBaseLogic {
   public GuiEnchantment field_0014;
   public Entity cachedEntity;
   public String mobID;
   public MobSpawnerBaseLogic$WeightedRandomMinecart randomEntity;
   public List<MobSpawnerBaseLogic$WeightedRandomMinecart> minecartToSpawn;
   public int maxSpawnDelay;
   public int spawnDelay = 20;
   public int maxNearbyEntities;
   public int spawnCount;
   public int activatingRangeFromPlayer;
   public AFUNIXSocketImpl$Lenient field_0008;
   public ForkJoinTask$AdaptedRunnable field_0009;
   public int spawnRange;
   public int minSpawnDelay;
   public DefaultPromise$1 field_0013;
   public double field_0000;
   public double field_0005;

   public MobSpawnerBaseLogic() {
      this.mobID = "Pig";
      this.minecartToSpawn = Lists.newArrayList();
      this.minSpawnDelay = 200;
      this.maxSpawnDelay = 800;
      this.spawnCount = 4;
      this.maxNearbyEntities = 6;
      this.activatingRangeFromPlayer = 16;
      this.spawnRange = 4;
   }

   public double getMobRotation() {
      return this.field_0005;
   }

   public String getEntityNameToSpawn() {
      if (this.getRandomEntity() == null) {
         if (this.mobID != null && this.mobID.equals("Minecart")) {
            this.mobID = "MinecartRideable";
         }

         return this.mobID;
      } else {
         return MobSpawnerBaseLogic$WeightedRandomMinecart.access$000(this.getRandomEntity());
      }
   }

   public MobSpawnerBaseLogic$WeightedRandomMinecart getRandomEntity() {
      return this.randomEntity;
   }

   public boolean isActivated() {
      BlockPos var1 = this.getSpawnerPosition();
      return this.getSpawnerWorld().isAnyPlayerWithinRangeAt(var1.getX() + 0.5, var1.getY() + 0.5, var1.getZ() + 0.5, this.activatingRangeFromPlayer);
   }

   public void updateSpawner() {
      if (this.isActivated()) {
         BlockPos var1 = this.getSpawnerPosition();
         if (this.getSpawnerWorld().D) {
            double var2 = var1.getX() + this.getSpawnerWorld().s.nextFloat();
            double var4 = var1.getY() + this.getSpawnerWorld().s.nextFloat();
            double var6 = var1.getZ() + this.getSpawnerWorld().s.nextFloat();
            this.getSpawnerWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var2, var4, var6, 0.0, 0.0, 0.0);
            this.getSpawnerWorld().spawnParticle(EnumParticleTypes.FLAME, var2, var4, var6, 0.0, 0.0, 0.0);
            if (this.spawnDelay > 0) {
               this.spawnDelay--;
            }

            this.field_0000 = this.field_0005;
            this.field_0005 = (this.field_0005 + 1000.0F / (this.spawnDelay + 200.0F)) % 360.0;
         } else {
            if (this.spawnDelay == -1) {
               this.method_06488();
            }

            if (this.spawnDelay > 0) {
               this.spawnDelay--;
               return;
            }

            boolean var13 = false;

            for (int var3 = 0; var3 < this.spawnCount; var3++) {
               Entity var14 = EntityList.createEntityByName(this.getEntityNameToSpawn(), this.getSpawnerWorld());
               if (var14 == null) {
                  return;
               }

               int var5 = this.getSpawnerWorld()
                  .getEntitiesWithinAABB(
                     var14.getClass(),
                     new AxisAlignedBB(var1.getX(), var1.getY(), var1.getZ(), var1.getX() + 1, var1.getY() + 1, var1.getZ() + 1)
                        .expand(this.spawnRange, this.spawnRange, this.spawnRange)
                  )
                  .size();
               if (var5 >= this.maxNearbyEntities) {
                  this.method_06488();
                  return;
               }

               double var15 = var1.getX() + (this.getSpawnerWorld().s.nextDouble() - this.getSpawnerWorld().s.nextDouble()) * this.spawnRange + 0.5;
               double var8 = var1.getY() + this.getSpawnerWorld().s.nextInt(3) - 1;
               double var10 = var1.getZ() + (this.getSpawnerWorld().s.nextDouble() - this.getSpawnerWorld().s.nextDouble()) * this.spawnRange + 0.5;
               EntityLiving var12 = var14 instanceof EntityLiving ? (EntityLiving)var14 : null;
               var14.a_(var15, var8, var10, this.getSpawnerWorld().s.nextFloat() * 360.0F, 0.0F);
               if (var12 == null || var12.getCanSpawnHere() && var12.isNotColliding()) {
                  this.spawnNewEntity(var14, true);
                  this.getSpawnerWorld().b(2004, var1, 0);
                  if (var12 != null) {
                     var12.spawnExplosionParticle();
                  }

                  var13 = true;
               }
            }

            if (var13) {
               this.method_06488();
            }
         }
      }
   }

   public abstract BlockPos getSpawnerPosition();

   public abstract void func_98267_a(int var1);

   public Entity spawnNewEntity(Entity var1, boolean var2) {
      if (this.getRandomEntity() != null) {
         NBTTagCompound var3 = new NBTTagCompound();
         var1.writeToNBTOptional(var3);

         for (String var5 : MobSpawnerBaseLogic$WeightedRandomMinecart.access$100(this.getRandomEntity()).getKeySet()) {
            NBTBase var6 = MobSpawnerBaseLogic$WeightedRandomMinecart.access$100(this.getRandomEntity()).getTag(var5);
            var3.setTag(var5, var6.copy());
         }

         var1.f(var3);
         if (var1.o != null && var2) {
            var1.o.spawnEntityInWorld(var1);
         }

         Entity var12 = var1;

         while (var3.hasKey("Riding", 10)) {
            NBTTagCompound var11 = var3.getCompoundTag("Riding");
            Entity var13 = EntityList.createEntityByName(var11.getString("id"), var1.o);
            if (var13 != null) {
               NBTTagCompound var7 = new NBTTagCompound();
               var13.writeToNBTOptional(var7);

               for (String var9 : var11.getKeySet()) {
                  NBTBase var10 = var11.getTag(var9);
                  var7.setTag(var9, var10.copy());
               }

               var13.f(var7);
               var13.a_(var12.s, var12.t, var12.u, var12.y, var12.z);
               if (var1.o != null && var2) {
                  var1.o.spawnEntityInWorld(var13);
               }

               var12.mountEntity(var13);
            }

            var12 = var13;
            var3 = var11;
         }
      } else if (var1 instanceof EntityLivingBase && var1.o != null && var2) {
         if (var1 instanceof EntityLiving) {
            ((EntityLiving)var1).onInitialSpawn(var1.o.E(new BlockPos(var1)), (IEntityLivingData)null);
         }

         var1.o.spawnEntityInWorld(var1);
      }

      return var1;
   }

   public Entity func_180612_a(World var1) {
      if (this.cachedEntity == null) {
         Entity var2 = EntityList.createEntityByName(this.getEntityNameToSpawn(), var1);
         if (var2 != null) {
            var2 = this.spawnNewEntity(var2, false);
            this.cachedEntity = var2;
         }
      }

      return this.cachedEntity;
   }

   public void method_06493(NBTTagCompound var1) {
      String var2 = this.getEntityNameToSpawn();
      if (!StringUtils.isNullOrEmpty(var2)) {
         var1.setString("EntityId", var2);
         var1.setShort("Delay", (short)this.spawnDelay);
         var1.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
         var1.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
         var1.setShort("SpawnCount", (short)this.spawnCount);
         var1.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
         var1.setShort("RequiredPlayerRange", (short)this.activatingRangeFromPlayer);
         var1.setShort("SpawnRange", (short)this.spawnRange);
         if (this.getRandomEntity() != null) {
            var1.setTag("SpawnData", MobSpawnerBaseLogic$WeightedRandomMinecart.access$100(this.getRandomEntity()).copy());
         }

         if (this.getRandomEntity() != null || this.minecartToSpawn.size() > 0) {
            NBTTagList var3 = new NBTTagList();
            if (this.minecartToSpawn.size() > 0) {
               for (MobSpawnerBaseLogic$WeightedRandomMinecart var5 : this.minecartToSpawn) {
                  var3.appendTag(var5.toNBT());
               }
            } else {
               var3.appendTag(this.getRandomEntity().toNBT());
            }

            var1.setTag("SpawnPotentials", var3);
         }
      }
   }

   public boolean setDelayToMin(int var1) {
      if (var1 == 1 && this.getSpawnerWorld().D) {
         this.spawnDelay = this.minSpawnDelay;
         return true;
      } else {
         return false;
      }
   }

   public void setEntityName(String var1) {
      this.mobID = var1;
   }

   public void method_06497(NBTTagCompound var1) {
      this.mobID = var1.getString("EntityId");
      this.spawnDelay = var1.getShort("Delay");
      this.minecartToSpawn.clear();
      if (var1.hasKey("SpawnPotentials", 9)) {
         NBTTagList var2 = var1.getTagList("SpawnPotentials", 10);

         for (int var3 = 0; var3 < var2.tagCount(); var3++) {
            this.minecartToSpawn.add(new MobSpawnerBaseLogic$WeightedRandomMinecart(this, var2.getCompoundTagAt(var3)));
         }
      }

      if (var1.hasKey("SpawnData", 10)) {
         this.setRandomEntity(new MobSpawnerBaseLogic$WeightedRandomMinecart(this, var1.getCompoundTag("SpawnData"), this.mobID));
      } else {
         this.setRandomEntity((MobSpawnerBaseLogic$WeightedRandomMinecart)null);
      }

      if (var1.hasKey("MinSpawnDelay", 99)) {
         this.minSpawnDelay = var1.getShort("MinSpawnDelay");
         this.maxSpawnDelay = var1.getShort("MaxSpawnDelay");
         this.spawnCount = var1.getShort("SpawnCount");
      }

      if (var1.hasKey("MaxNearbyEntities", 99)) {
         this.maxNearbyEntities = var1.getShort("MaxNearbyEntities");
         this.activatingRangeFromPlayer = var1.getShort("RequiredPlayerRange");
      }

      if (var1.hasKey("SpawnRange", 99)) {
         this.spawnRange = var1.getShort("SpawnRange");
      }

      if (this.getSpawnerWorld() != null) {
         this.cachedEntity = null;
      }
   }

   public double getPrevMobRotation() {
      return this.field_0000;
   }

   public void setRandomEntity(MobSpawnerBaseLogic$WeightedRandomMinecart var1) {
      this.randomEntity = var1;
   }

   public void method_06488() {
      if (this.maxSpawnDelay <= this.minSpawnDelay) {
         this.spawnDelay = this.minSpawnDelay;
      } else {
         int var1 = this.maxSpawnDelay - this.minSpawnDelay;
         this.spawnDelay = this.minSpawnDelay + this.getSpawnerWorld().s.nextInt(var1);
      }

      if (this.minecartToSpawn.size() > 0) {
         this.setRandomEntity(WeightedRandom.getRandomItem(this.getSpawnerWorld().s, this.minecartToSpawn));
      }

      this.func_98267_a(1);
   }

   public abstract World getSpawnerWorld();
}
