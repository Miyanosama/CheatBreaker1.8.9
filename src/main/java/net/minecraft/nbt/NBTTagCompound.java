package net.minecraft.nbt;

import com.google.common.collect.Maps;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;

public class NBTTagCompound extends NBTBase {
   public Map<String, NBTBase> tagMap = Maps.newHashMap();

   public boolean hasKey(String var1, int var2) {
      byte var3 = this.method_26604(var1);
      if (var3 == var2) {
         return true;
      } else if (var2 != 99) {
         if (var3 > 0) {
         }

         return false;
      } else {
         return var3 == 1 || var3 == 2 || var3 == 3 || var3 == 4 || var3 == 5 || var3 == 6;
      }
   }

   public void setDouble(String var1, double var2) {
      this.tagMap.put(var1, new NBTTagDouble(var2));
   }

   public void setIntArray(String var1, int[] var2) {
      this.tagMap.put(var1, new NBTTagIntArray(var2));
   }

   public byte getByte(String var1) {
      try {
         return !this.hasKey(var1, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(var1)).getByte();
      } catch (ClassCastException var3) {
         return 0;
      }
   }

   public float getFloat(String var1) {
      try {
         return !this.hasKey(var1, 99) ? 0.0F : ((NBTBase.NBTPrimitive)this.tagMap.get(var1)).getFloat();
      } catch (ClassCastException var3) {
         return 0.0F;
      }
   }

   public String getString(String var1) {
      try {
         return !this.hasKey(var1, 8) ? "" : this.tagMap.get(var1).getString();
      } catch (ClassCastException var3) {
         return "";
      }
   }

   public int[] getIntArray(String var1) {
      try {
         return !this.hasKey(var1, 11) ? new int[0] : ((NBTTagIntArray)this.tagMap.get(var1)).getIntArray();
      } catch (ClassCastException var3) {
         throw new ReportedException(this.createCrashReport(var1, 11, var3));
      }
   }

   public void removeTag(String var1) {
      this.tagMap.remove(var1);
   }

   public static NBTBase readNBT(byte var0, String var1, DataInput var2, int var3, NBTSizeTracker var4) throws java.io.IOException {
      NBTBase var5 = NBTBase.createNewByType(var0);

      try {
         var5.read(var2, var3, var4);
         return var5;
      } catch (IOException var9) {
         CrashReport var7 = CrashReport.makeCrashReport(var9, "Loading NBT data");
         CrashReportCategory var8 = var7.makeCategory("NBT Tag");
         var8.addCrashSection("Tag name", var1);
         var8.addCrashSection("Tag type", var0);
         throw new ReportedException(var7);
      }
   }

   public void setString(String var1, String var2) {
      this.tagMap.put(var1, new NBTTagString(var2));
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("{");

      for (Entry var3 : this.tagMap.entrySet()) {
         if (var1.length() != 1) {
            var1.append(',');
         }

         var1.append((String)var3.getKey()).append(':').append(var3.getValue());
      }

      return var1.append('}').toString();
   }

   public void setByteArray(String var1, byte[] var2) {
      this.tagMap.put(var1, new NBTTagByteArray(var2));
   }

   public void setByte(String var1, byte var2) {
      this.tagMap.put(var1, new NBTTagByte(var2));
   }

   public double getDouble(String var1) {
      try {
         return !this.hasKey(var1, 99) ? 0.0 : ((NBTBase.NBTPrimitive)this.tagMap.get(var1)).getDouble();
      } catch (ClassCastException var3) {
         return 0.0;
      }
   }

   public static void writeEntry(String var0, NBTBase var1, DataOutput var2) throws java.io.IOException {
      var2.writeByte(var1.getId());
      if (var1.getId() != 0) {
         var2.writeUTF(var0);
         var1.write(var2);
      }
   }

   public boolean getBoolean(String var1) {
      return this.getByte(var1) != 0;
   }

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagCompound var2 = (NBTTagCompound)var1;
         return this.tagMap.entrySet().equals(var2.tagMap.entrySet());
      } else {
         return false;
      }
   }

   @Override
   public NBTBase copy() {
      NBTTagCompound var1 = new NBTTagCompound();

      for (String var3 : this.tagMap.keySet()) {
         var1.setTag(var3, this.tagMap.get(var3).copy());
      }

      return var1;
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.tagMap.hashCode();
   }

   public NBTTagCompound getCompoundTag(String var1) {
      try {
         return !this.hasKey(var1, 10) ? new NBTTagCompound() : (NBTTagCompound)this.tagMap.get(var1);
      } catch (ClassCastException var3) {
         throw new ReportedException(this.createCrashReport(var1, 10, var3));
      }
   }

   public static String readKey(DataInput var0, NBTSizeTracker var1) throws java.io.IOException {
      return var0.readUTF();
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) throws java.io.IOException {
      var3.read(384L);
      if (var2 > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.tagMap.clear();

         byte var4;
         while ((var4 = readType(var1, var3)) != 0) {
            String var5 = readKey(var1, var3);
            var3.read(224 + 16 * var5.length());
            NBTBase var6 = readNBT(var4, var5, var1, var2 + 1, var3);
            if (this.tagMap.put(var5, var6) != null) {
               var3.read(288L);
            }
         }
      }
   }

   public void setShort(String var1, short var2) {
      this.tagMap.put(var1, new NBTTagShort(var2));
   }

   public void merge(NBTTagCompound var1) {
      for (String var3 : var1.tagMap.keySet()) {
         NBTBase var4 = var1.tagMap.get(var3);
         if (var4.getId() == 10) {
            if (this.hasKey(var3, 10)) {
               NBTTagCompound var5 = this.getCompoundTag(var3);
               var5.merge((NBTTagCompound)var4);
            } else {
               this.setTag(var3, var4.copy());
            }
         } else {
            this.setTag(var3, var4.copy());
         }
      }
   }

   public NBTTagList getTagList(String var1, int var2) {
      try {
         if (this.method_26604(var1) != 9) {
            return new NBTTagList();
         } else {
            NBTTagList var3 = (NBTTagList)this.tagMap.get(var1);
            return var3.tagCount() > 0 && var3.getTagType() != var2 ? new NBTTagList() : var3;
         }
      } catch (ClassCastException var4) {
         throw new ReportedException(this.createCrashReport(var1, 9, var4));
      }
   }

   public Set<String> getKeySet() {
      return this.tagMap.keySet();
   }

   public CrashReport createCrashReport(final String var1, final int var2, ClassCastException var3) {
      CrashReport var4 = CrashReport.makeCrashReport(var3, "Reading NBT data");
      CrashReportCategory var5 = var4.makeCategoryDepth("Corrupt NBT tag", 1);
      var5.addCrashSectionCallable("Tag type found", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return NBTBase.NBT_TYPES[NBTTagCompound.this.tagMap.get(var1).getId()];
         }
      });
      var5.addCrashSectionCallable("Tag type expected", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return NBTBase.NBT_TYPES[var2];
         }
      });
      var5.addCrashSection("Tag name", var1);
      return var4;
   }

   public void setBoolean(String var1, boolean var2) {
      this.setByte(var1, (byte)(var2 ? 1 : 0));
   }

   @Override
   public boolean hasNoTags() {
      return this.tagMap.isEmpty();
   }

   public boolean hasKey(String var1) {
      return this.tagMap.containsKey(var1);
   }

   public short getShort(String var1) {
      try {
         return !this.hasKey(var1, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(var1)).getShort();
      } catch (ClassCastException var3) {
         return 0;
      }
   }

   public byte method_26604(String var1) {
      NBTBase var2 = this.tagMap.get(var1);
      return var2 != null ? var2.getId() : 0;
   }

   public int getInteger(String var1) {
      try {
         return !this.hasKey(var1, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(var1)).getInt();
      } catch (ClassCastException var3) {
         return 0;
      }
   }

   public static byte readType(DataInput var0, NBTSizeTracker var1) throws java.io.IOException {
      return var0.readByte();
   }

   public NBTBase getTag(String var1) {
      return this.tagMap.get(var1);
   }

   @Override
   public void write(DataOutput var1) throws java.io.IOException {
      for (String var3 : this.tagMap.keySet()) {
         NBTBase var4 = this.tagMap.get(var3);
         writeEntry(var3, var4, var1);
      }

      var1.writeByte(0);
   }

   @Override
   public byte getId() {
      return 10;
   }

   public void setTag(String var1, NBTBase var2) {
      this.tagMap.put(var1, var2);
   }

   public void setFloat(String var1, float var2) {
      this.tagMap.put(var1, new NBTTagFloat(var2));
   }

   public void setInteger(String var1, int var2) {
      this.tagMap.put(var1, new NBTTagInt(var2));
   }

   public long getLong(String var1) {
      try {
         return !this.hasKey(var1, 99) ? 0L : ((NBTBase.NBTPrimitive)this.tagMap.get(var1)).getLong();
      } catch (ClassCastException var3) {
         return 0L;
      }
   }

   public void setLong(String var1, long var2) {
      this.tagMap.put(var1, new NBTTagLong(var2));
   }

   public byte[] getByteArray(String var1) {
      try {
         return !this.hasKey(var1, 7) ? new byte[0] : ((NBTTagByteArray)this.tagMap.get(var1)).getByteArray();
      } catch (ClassCastException var3) {
         throw new ReportedException(this.createCrashReport(var1, 7, var3));
      }
   }
}
