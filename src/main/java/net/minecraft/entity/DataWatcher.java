package net.minecraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Rotations;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.commons.lang3.ObjectUtils;

public class DataWatcher {
   public ReadWriteLock lock;
   public BiomeGenBase spawnBiome;
   public Entity owner;
   public boolean isBlank = true;
   public static Map<Class<?>, Integer> dataTypes = Maps.newHashMap();
   public BlockPos spawnPosition;
   public boolean objectChanged;
   public Map<Integer, DataWatcher.WatchableObject> watchedObjects = Maps.newHashMap();

   public <T> void addObject(int var1, T var2) {
      Integer var3 = dataTypes.get(var2.getClass());
      if (var3 == null) {
         throw new IllegalArgumentException("Unknown data type: " + var2.getClass());
      } else if (var1 > 31) {
         throw new IllegalArgumentException("Data value id is too big with " + var1 + "! (Max is " + 31 + ")");
      } else if (this.watchedObjects.containsKey(var1)) {
         throw new IllegalArgumentException("Duplicate id value for " + var1 + "!");
      } else {
         DataWatcher.WatchableObject var4 = new DataWatcher.WatchableObject(var3, var1, var2);
         this.lock.writeLock().lock();
         this.watchedObjects.put(var1, var4);
         this.lock.writeLock().unlock();
         this.isBlank = false;
      }
   }

   public List<DataWatcher.WatchableObject> getAllWatched() {
      ArrayList var1 = null;
      this.lock.readLock().lock();

      for (DataWatcher.WatchableObject var3 : this.watchedObjects.values()) {
         if (var1 == null) {
            var1 = Lists.newArrayList();
         }

         var1.add(var3);
      }

      this.lock.readLock().unlock();
      return var1;
   }

   public Rotations getWatchableObjectRotations(int var1) {
      return (Rotations)this.getWatchedObject(var1).getObject();
   }

   public void updateWatchedObjectsFromList(List<DataWatcher.WatchableObject> var1) {
      this.lock.writeLock().lock();

      for (DataWatcher.WatchableObject var3 : var1) {
         DataWatcher.WatchableObject var4 = this.watchedObjects.get(var3.getDataValueId());
         if (var4 != null) {
            var4.setObject(var3.getObject());
            this.owner.onDataWatcherUpdate(var3.getDataValueId());
         }
      }

      this.lock.writeLock().unlock();
      this.objectChanged = true;
   }

   public ItemStack getWatchableObjectItemStack(int var1) {
      return (ItemStack)this.getWatchedObject(var1).getObject();
   }

   public float getWatchableObjectFloat(int var1) {
      return (Float)this.getWatchedObject(var1).getObject();
   }

   public DataWatcher.WatchableObject getWatchedObject(int var1) {
      this.lock.readLock().lock();

      DataWatcher.WatchableObject var2;
      try {
         var2 = this.watchedObjects.get(var1);
      } catch (Throwable var6) {
         CrashReport var4 = CrashReport.makeCrashReport(var6, "Getting synched entity data");
         CrashReportCategory var5 = var4.makeCategory("Synched entity data");
         var5.addCrashSection("Data ID", var1);
         throw new ReportedException(var4);
      }

      this.lock.readLock().unlock();
      return var2;
   }

   public void func_111144_e() {
      this.objectChanged = false;
   }

   public <T> void updateObject(int var1, T var2) {
      DataWatcher.WatchableObject var3 = this.getWatchedObject(var1);
      if (ObjectUtils.notEqual(var2, var3.getObject())) {
         var3.setObject(var2);
         this.owner.onDataWatcherUpdate(var1);
         var3.setWatched(true);
         this.objectChanged = true;
      }
   }

   static {
      dataTypes.put(Byte.class, 0);
      dataTypes.put(Short.class, 1);
      dataTypes.put(Integer.class, 2);
      dataTypes.put(Float.class, 3);
      dataTypes.put(String.class, 4);
      dataTypes.put(ItemStack.class, 5);
      dataTypes.put(BlockPos.class, 6);
      dataTypes.put(Rotations.class, 7);
   }

   public short getWatchableObjectShort(int var1) {
      return (Short)this.getWatchedObject(var1).getObject();
   }

   public boolean getIsBlank() {
      return this.isBlank;
   }

   public static void writeWatchedListToPacketBuffer(List<DataWatcher.WatchableObject> var0, PacketBuffer var1) throws java.io.IOException {
      if (var0 != null) {
         for (DataWatcher.WatchableObject var3 : var0) {
            writeWatchableObjectToPacketBuffer(var1, var3);
         }
      }

      var1.writeByte(127);
   }

   public String getWatchableObjectString(int var1) {
      return (String)this.getWatchedObject(var1).getObject();
   }

   public void setObjectWatched(int var1) {
      this.getWatchedObject(var1).watched = true;
      this.objectChanged = true;
   }

   public boolean hasObjectChanged() {
      return this.objectChanged;
   }

   public int getWatchableObjectInt(int var1) {
      return (Integer)this.getWatchedObject(var1).getObject();
   }

   public byte getWatchableObjectByte(int var1) {
      return (Byte)this.getWatchedObject(var1).getObject();
   }

   public static void writeWatchableObjectToPacketBuffer(PacketBuffer var0, DataWatcher.WatchableObject var1) throws java.io.IOException {
      int var2 = (var1.getObjectType() << 5 | var1.getDataValueId() & 31) & 0xFF;
      var0.writeByte(var2);
      switch (var1.getObjectType()) {
         case 0:
            var0.writeByte((Byte)var1.getObject());
            break;
         case 1:
            var0.writeShort((Short)var1.getObject());
            break;
         case 2:
            var0.writeInt((Integer)var1.getObject());
            break;
         case 3:
            var0.writeFloat((Float)var1.getObject());
            break;
         case 4:
            var0.writeString((String)var1.getObject());
            break;
         case 5:
            ItemStack var3 = (ItemStack)var1.getObject();
            var0.writeItemStackToBuffer(var3);
            break;
         case 6:
            BlockPos var4 = (BlockPos)var1.getObject();
            var0.writeInt(var4.getX());
            var0.writeInt(var4.getY());
            var0.writeInt(var4.getZ());
            break;
         case 7:
            Rotations var5 = (Rotations)var1.getObject();
            var0.writeFloat(var5.getX());
            var0.writeFloat(var5.getY());
            var0.writeFloat(var5.getZ());
      }
   }

   public DataWatcher(Entity var1) {
      this.lock = new ReentrantReadWriteLock();
      this.spawnBiome = BiomeGenBase.plains;
      this.spawnPosition = BlockPos.ORIGIN;
      this.owner = var1;
   }

   public void writeTo(PacketBuffer var1) throws java.io.IOException {
      this.lock.readLock().lock();

      for (DataWatcher.WatchableObject var3 : this.watchedObjects.values()) {
         writeWatchableObjectToPacketBuffer(var1, var3);
      }

      this.lock.readLock().unlock();
      var1.writeByte(127);
   }

   public void addObjectByDataType(int var1, int var2) {
      DataWatcher.WatchableObject var3 = new DataWatcher.WatchableObject(var2, var1, null);
      this.lock.writeLock().lock();
      this.watchedObjects.put(var1, var3);
      this.lock.writeLock().unlock();
      this.isBlank = false;
   }

   public List<DataWatcher.WatchableObject> getChanged() {
      ArrayList var1 = null;
      if (this.objectChanged) {
         this.lock.readLock().lock();

         for (DataWatcher.WatchableObject var3 : this.watchedObjects.values()) {
            if (var3.isWatched()) {
               var3.setWatched(false);
               if (var1 == null) {
                  var1 = Lists.newArrayList();
               }

               var1.add(var3);
            }
         }

         this.lock.readLock().unlock();
      }

      this.objectChanged = false;
      return var1;
   }

   public static List<DataWatcher.WatchableObject> readWatchedListFromPacketBuffer(PacketBuffer var0) throws java.io.IOException {
      ArrayList var1 = null;

      for (byte var2 = var0.readByte(); var2 != 127; var2 = var0.readByte()) {
         if (var1 == null) {
            var1 = Lists.newArrayList();
         }

         int var3 = (var2 & 224) >> 5;
         int var4 = var2 & 31;
         DataWatcher.WatchableObject var5 = null;
         switch (var3) {
            case 0:
               var5 = new DataWatcher.WatchableObject(var3, var4, var0.readByte());
               break;
            case 1:
               var5 = new DataWatcher.WatchableObject(var3, var4, var0.readShort());
               break;
            case 2:
               var5 = new DataWatcher.WatchableObject(var3, var4, var0.readInt());
               break;
            case 3:
               var5 = new DataWatcher.WatchableObject(var3, var4, var0.readFloat());
               break;
            case 4:
               var5 = new DataWatcher.WatchableObject(var3, var4, var0.readStringFromBuffer(32767));
               break;
            case 5:
               var5 = new DataWatcher.WatchableObject(var3, var4, var0.readItemStackFromBuffer());
               break;
            case 6:
               int var6 = var0.readInt();
               int var7 = var0.readInt();
               int var8 = var0.readInt();
               var5 = new DataWatcher.WatchableObject(var3, var4, new BlockPos(var6, var7, var8));
               break;
            case 7:
               float var9 = var0.readFloat();
               float var10 = var0.readFloat();
               float var11 = var0.readFloat();
               var5 = new DataWatcher.WatchableObject(var3, var4, new Rotations(var9, var10, var11));
         }

         var1.add(var5);
      }

      return var1;
   }

   public static class WatchableObject {
      public int dataValueId;
      public Object watchedObject;
      public int objectType;
      public boolean watched;

      public int getDataValueId() {
         return this.dataValueId;
      }

      public Object getObject() {
         return this.watchedObject;
      }

      public int getObjectType() {
         return this.objectType;
      }

      public WatchableObject(int var1, int var2, Object var3) {
         this.dataValueId = var2;
         this.watchedObject = var3;
         this.objectType = var1;
         this.watched = true;
      }

      public boolean isWatched() {
         return this.watched;
      }

      public void setObject(Object var1) {
         this.watchedObject = var1;
      }

      public void setWatched(boolean var1) {
         this.watched = var1;
      }
   }
}
