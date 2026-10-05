package net.minecraft.village;

import com.google.common.collect.Lists;
import com.jagrosh.discordipc.entities.Callback;
import com.mojang.authlib.GameProfile;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class Village {
   public Callback field_0006;
   public int lastAddDoorTimestamp;
   public TreeMap<String, Integer> playerReputation;
   public List<Village$VillageAggressor> villageAgressors;
   public int tickCounter;
   public int noBreedTicks;
   public BlockPos center;
   public BlockPos centerHelper;
   public WorldSettings field_0003;
   public int numIronGolems;
   public EnchantmentThorns field_0000;
   public ModelBakery field_0007;
   public World worldObj;
   public int numVillagers;
   public List<VillageDoorInfo> villageDoorInfoList = Lists.newArrayList();
   public int villageRadius;

   public void setDefaultPlayerReputation(int var1) {
      for (String var3 : this.playerReputation.keySet()) {
         this.setReputationForPlayer(var3, var1);
      }
   }

   public int getTicksSinceLastDoorAdding() {
      return this.tickCounter - this.lastAddDoorTimestamp;
   }

   public EntityLivingBase findNearestVillageAggressor(EntityLivingBase var1) {
      double var2 = Double.MAX_VALUE;
      Village$VillageAggressor var4 = null;

      for (int var5 = 0; var5 < this.villageAgressors.size(); var5++) {
         Village$VillageAggressor var6 = this.villageAgressors.get(var5);
         double var7 = var6.agressor.h(var1);
         if (var7 <= var2) {
            var4 = var6;
            var2 = var7;
         }
      }

      return var4 != null ? var4.agressor : null;
   }

   public void updateNumVillagers() {
      List var1 = this.worldObj
         .getEntitiesWithinAABB(
            EntityVillager.class,
            new AxisAlignedBB(
               this.center.getX() - this.villageRadius,
               this.center.getY() - 4,
               this.center.getZ() - this.villageRadius,
               this.center.getX() + this.villageRadius,
               this.center.getY() + 4,
               this.center.getZ() + this.villageRadius
            )
         );
      this.numVillagers = var1.size();
      if (this.numVillagers == 0) {
         this.playerReputation.clear();
      }
   }

   public VillageDoorInfo getDoorInfo(BlockPos var1) {
      VillageDoorInfo var2 = null;
      int var3 = Integer.MAX_VALUE;

      for (VillageDoorInfo var5 : this.villageDoorInfoList) {
         int var6 = var5.getDistanceToDoorBlockSq(var1);
         if (var6 > 256) {
            var6 *= 1000;
         } else {
            var6 = var5.getDoorOpeningRestrictionCounter();
         }

         if (var6 < var3) {
            var2 = var5;
            var3 = var6;
         }
      }

      return var2;
   }

   public void updateVillageRadiusAndCenter() {
      int var1 = this.villageDoorInfoList.size();
      if (var1 == 0) {
         this.center = new BlockPos(0, 0, 0);
         this.villageRadius = 0;
      } else {
         this.center = new BlockPos(this.centerHelper.getX() / var1, this.centerHelper.getY() / var1, this.centerHelper.getZ() / var1);
         int var2 = 0;

         for (VillageDoorInfo var4 : this.villageDoorInfoList) {
            var2 = Math.max(var4.getDistanceToDoorBlockSq(this.center), var2);
         }

         this.villageRadius = Math.max(32, (int)Math.sqrt(var2) + 1);
      }
   }

   public void addOrRenewAgressor(EntityLivingBase var1) {
      for (Village$VillageAggressor var3 : this.villageAgressors) {
         if (var3.agressor == var1) {
            var3.agressionTime = this.tickCounter;
            return;
         }
      }

      this.villageAgressors.add(new Village$VillageAggressor(this, var1, this.tickCounter));
   }

   public int getReputationForPlayer(String var1) {
      Integer var2 = this.playerReputation.get(var1);
      return var2 != null ? var2 : 0;
   }

   public int getNumVillagers() {
      return this.numVillagers;
   }

   public int getVillageRadius() {
      return this.villageRadius;
   }

   public void tick(int var1) {
      this.tickCounter = var1;
      this.removeDeadAndOutOfRangeDoors();
      this.removeDeadAndOldAgressors();
      if (var1 % 20 == 0) {
         this.updateNumVillagers();
      }

      if (var1 % 30 == 0) {
         this.updateNumIronGolems();
      }

      int var2 = this.numVillagers / 10;
      if (this.numIronGolems < var2 && this.villageDoorInfoList.size() > 20 && this.worldObj.s.nextInt(7000) == 0) {
         Vec3 var3 = this.func_179862_a(this.center, 2, 4, 2);
         if (var3 != null) {
            EntityIronGolem var4 = new EntityIronGolem(this.worldObj);
            var4.b(var3.xCoord, var3.yCoord, var3.zCoord);
            this.worldObj.spawnEntityInWorld(var4);
            this.numIronGolems++;
         }
      }
   }

   public void writeVillageDataToNBT(NBTTagCompound var1) {
      var1.setInteger("PopSize", this.numVillagers);
      var1.setInteger("Radius", this.villageRadius);
      var1.setInteger("Golems", this.numIronGolems);
      var1.setInteger("Stable", this.lastAddDoorTimestamp);
      var1.setInteger("Tick", this.tickCounter);
      var1.setInteger("MTick", this.noBreedTicks);
      var1.setInteger("CX", this.center.getX());
      var1.setInteger("CY", this.center.getY());
      var1.setInteger("CZ", this.center.getZ());
      var1.setInteger("ACX", this.centerHelper.getX());
      var1.setInteger("ACY", this.centerHelper.getY());
      var1.setInteger("ACZ", this.centerHelper.getZ());
      NBTTagList var2 = new NBTTagList();

      for (VillageDoorInfo var4 : this.villageDoorInfoList) {
         NBTTagCompound var5 = new NBTTagCompound();
         var5.setInteger("X", var4.getDoorBlockPos().getX());
         var5.setInteger("Y", var4.getDoorBlockPos().getY());
         var5.setInteger("Z", var4.getDoorBlockPos().getZ());
         var5.setInteger("IDX", var4.getInsideOffsetX());
         var5.setInteger("IDZ", var4.getInsideOffsetZ());
         var5.setInteger("TS", var4.getInsidePosY());
         var2.appendTag(var5);
      }

      var1.setTag("Doors", var2);
      NBTTagList var9 = new NBTTagList();

      for (String var11 : this.playerReputation.keySet()) {
         NBTTagCompound var6 = new NBTTagCompound();
         PlayerProfileCache var7 = MinecraftServer.getServer().getPlayerProfileCache();
         GameProfile var8 = var7.getGameProfileForUsername(var11);
         if (var8 != null) {
            var6.setString("UUID", var8.getId().toString());
            var6.setInteger("S", this.playerReputation.get(var11));
            var9.appendTag(var6);
         }
      }

      var1.setTag("Players", var9);
   }

   public void endMatingSeason() {
      this.noBreedTicks = this.tickCounter;
   }

   public boolean isWoodDoor(BlockPos var1) {
      Block var2 = this.worldObj.getBlockState(var1).getBlock();
      return var2 instanceof BlockDoor ? var2.getMaterial() == Material.wood : false;
   }

   public Village(World var1) {
      this.centerHelper = BlockPos.ORIGIN;
      this.center = BlockPos.ORIGIN;
      this.playerReputation = new TreeMap<>();
      this.villageAgressors = Lists.newArrayList();
      this.worldObj = var1;
   }

   public boolean isAnnihilated() {
      return this.villageDoorInfoList.isEmpty();
   }

   public void readVillageDataFromNBT(NBTTagCompound var1) {
      this.numVillagers = var1.getInteger("PopSize");
      this.villageRadius = var1.getInteger("Radius");
      this.numIronGolems = var1.getInteger("Golems");
      this.lastAddDoorTimestamp = var1.getInteger("Stable");
      this.tickCounter = var1.getInteger("Tick");
      this.noBreedTicks = var1.getInteger("MTick");
      this.center = new BlockPos(var1.getInteger("CX"), var1.getInteger("CY"), var1.getInteger("CZ"));
      this.centerHelper = new BlockPos(var1.getInteger("ACX"), var1.getInteger("ACY"), var1.getInteger("ACZ"));
      NBTTagList var2 = var1.getTagList("Doors", 10);

      for (int var3 = 0; var3 < var2.tagCount(); var3++) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         VillageDoorInfo var5 = new VillageDoorInfo(
            new BlockPos(var4.getInteger("X"), var4.getInteger("Y"), var4.getInteger("Z")),
            var4.getInteger("IDX"),
            var4.getInteger("IDZ"),
            var4.getInteger("TS")
         );
         this.villageDoorInfoList.add(var5);
      }

      NBTTagList var8 = var1.getTagList("Players", 10);

      for (int var9 = 0; var9 < var8.tagCount(); var9++) {
         NBTTagCompound var10 = var8.getCompoundTagAt(var9);
         if (var10.hasKey("UUID")) {
            PlayerProfileCache var6 = MinecraftServer.getServer().getPlayerProfileCache();
            GameProfile var7 = var6.getProfileByUUID(UUID.fromString(var10.getString("UUID")));
            if (var7 != null) {
               this.playerReputation.put(var7.getName(), var10.getInteger("S"));
            }
         } else {
            this.playerReputation.put(var10.getString("Name"), var10.getInteger("S"));
         }
      }
   }

   public int getNumVillageDoors() {
      return this.villageDoorInfoList.size();
   }

   public boolean func_179866_a(BlockPos var1) {
      return this.center.distanceSq(var1) < this.villageRadius * this.villageRadius;
   }

   public void addVillageDoorInfo(VillageDoorInfo var1) {
      this.villageDoorInfoList.add(var1);
      this.centerHelper = this.centerHelper.add(var1.getDoorBlockPos());
      this.updateVillageRadiusAndCenter();
      this.lastAddDoorTimestamp = var1.getInsidePosY();
   }

   public void updateNumIronGolems() {
      List var1 = this.worldObj
         .getEntitiesWithinAABB(
            EntityIronGolem.class,
            new AxisAlignedBB(
               this.center.getX() - this.villageRadius,
               this.center.getY() - 4,
               this.center.getZ() - this.villageRadius,
               this.center.getX() + this.villageRadius,
               this.center.getY() + 4,
               this.center.getZ() + this.villageRadius
            )
         );
      this.numIronGolems = var1.size();
   }

   public void removeDeadAndOutOfRangeDoors() {
      boolean var1 = false;
      boolean var2 = this.worldObj.s.nextInt(50) == 0;
      Iterator var3 = this.villageDoorInfoList.iterator();

      while (var3.hasNext()) {
         VillageDoorInfo var4 = (VillageDoorInfo)var3.next();
         if (var2) {
            var4.resetDoorOpeningRestrictionCounter();
         }

         if (!this.isWoodDoor(var4.getDoorBlockPos()) || Math.abs(this.tickCounter - var4.getInsidePosY()) > 1200) {
            this.centerHelper = this.centerHelper.subtract(var4.getDoorBlockPos());
            var1 = true;
            var4.setIsDetachedFromVillageFlag(true);
            var3.remove();
         }
      }

      if (var1) {
         this.updateVillageRadiusAndCenter();
      }
   }

   public boolean isMatingSeason() {
      return this.noBreedTicks == 0 || this.tickCounter - this.noBreedTicks >= 3600;
   }

   public List<VillageDoorInfo> getVillageDoorInfoList() {
      return this.villageDoorInfoList;
   }

   public void setWorld(World var1) {
      this.worldObj = var1;
   }

   public VillageDoorInfo getNearestDoor(BlockPos var1) {
      VillageDoorInfo var2 = null;
      int var3 = Integer.MAX_VALUE;

      for (VillageDoorInfo var5 : this.villageDoorInfoList) {
         int var6 = var5.getDistanceToDoorBlockSq(var1);
         if (var6 < var3) {
            var2 = var5;
            var3 = var6;
         }
      }

      return var2;
   }

   public boolean isPlayerReputationTooLow(String var1) {
      return this.getReputationForPlayer(var1) <= -15;
   }

   public EntityPlayer getNearestTargetPlayer(EntityLivingBase var1) {
      double var2 = Double.MAX_VALUE;
      EntityPlayer var4 = null;

      for (String var6 : this.playerReputation.keySet()) {
         if (this.isPlayerReputationTooLow(var6)) {
            EntityPlayer var7 = this.worldObj.getPlayerEntityByName(var6);
            if (var7 != null) {
               double var8 = var7.h(var1);
               if (var8 <= var2) {
                  var4 = var7;
                  var2 = var8;
               }
            }
         }
      }

      return var4;
   }

   public BlockPos getCenter() {
      return this.center;
   }

   public Vec3 func_179862_a(BlockPos var1, int var2, int var3, int var4) {
      for (int var5 = 0; var5 < 10; var5++) {
         BlockPos var6 = var1.add(this.worldObj.s.nextInt(16) - 8, this.worldObj.s.nextInt(6) - 3, this.worldObj.s.nextInt(16) - 8);
         if (this.func_179866_a(var6) && this.func_179861_a(new BlockPos(var2, var3, var4), var6)) {
            return new Vec3(var6.getX(), var6.getY(), var6.getZ());
         }
      }

      return null;
   }

   public Village() {
      this.centerHelper = BlockPos.ORIGIN;
      this.center = BlockPos.ORIGIN;
      this.playerReputation = new TreeMap<>();
      this.villageAgressors = Lists.newArrayList();
   }

   public boolean func_179861_a(BlockPos var1, BlockPos var2) {
      if (!World.doesBlockHaveSolidTopSurface(this.worldObj, var2.down())) {
         return false;
      } else {
         int var3 = var2.getX() - var1.getX() / 2;
         int var4 = var2.getZ() - var1.getZ() / 2;

         for (int var5 = var3; var5 < var3 + var1.getX(); var5++) {
            for (int var6 = var2.getY(); var6 < var2.getY() + var1.getY(); var6++) {
               for (int var7 = var4; var7 < var4 + var1.getZ(); var7++) {
                  if (this.worldObj.getBlockState(new BlockPos(var5, var6, var7)).getBlock().isNormalCube()) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   public int setReputationForPlayer(String var1, int var2) {
      int var3 = this.getReputationForPlayer(var1);
      int var4 = MathHelper.clamp_int(var3 + var2, -30, 10);
      this.playerReputation.put(var1, var4);
      return var4;
   }

   public void removeDeadAndOldAgressors() {
      Iterator var1 = this.villageAgressors.iterator();

      while (var1.hasNext()) {
         Village$VillageAggressor var2 = (Village$VillageAggressor)var1.next();
         if (!var2.agressor.isEntityAlive() || Math.abs(this.tickCounter - var2.agressionTime) > 300) {
            var1.remove();
         }
      }
   }

   public VillageDoorInfo getExistedDoor(BlockPos var1) {
      if (this.center.distanceSq(var1) > this.villageRadius * this.villageRadius) {
         return null;
      } else {
         for (VillageDoorInfo var3 : this.villageDoorInfoList) {
            if (var3.getDoorBlockPos().getX() == var1.getX()
               && var3.getDoorBlockPos().getZ() == var1.getZ()
               && Math.abs(var3.getDoorBlockPos().getY() - var1.getY()) <= 1) {
               return var3;
            }
         }

         return null;
      }
   }
}
