package net.minecraft.world.chunk.storage;

import com.google.common.collect.Lists;
import io.netty.util.internal.chmv8.CountedCompleter$1;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.server.MinecraftServer;

public class RegionFile {
   public List<Boolean> sectorFree;
   public static byte[] emptySector = new byte[4096];
   public RandomAccessFile dataFile;
   public long lastModified;
   public GuiNewChat field_0000;
   public CountedCompleter$1 field_0001;
   public File field_0008;
   public int sizeDelta;
   public int[] offsets = new int[1024];
   public int[] chunkTimestamps = new int[1024];

   public void close() {
      if (this.dataFile != null) {
         this.dataFile.close();
      }
   }

   public synchronized void write(int var1, int var2, byte[] var3, int var4) {
      try {
         int var5 = this.getOffset(var1, var2);
         int var6 = var5 >> 8;
         int var7 = var5 & 0xFF;
         int var8 = (var4 + 5) / 4096 + 1;
         if (var8 >= 256) {
            return;
         }

         if (var6 != 0 && var7 == var8) {
            this.write(var6, var3, var4);
         } else {
            for (int var9 = 0; var9 < var7; var9++) {
               this.sectorFree.set(var6 + var9, true);
            }

            int var15 = this.sectorFree.indexOf(true);
            int var10 = 0;
            if (var15 != -1) {
               for (int var11 = var15; var11 < this.sectorFree.size(); var11++) {
                  if (var10 != 0) {
                     if (this.sectorFree.get(var11)) {
                        var10++;
                     } else {
                        var10 = 0;
                     }
                  } else if (this.sectorFree.get(var11)) {
                     var15 = var11;
                     var10 = 1;
                  }

                  if (var10 >= var8) {
                     break;
                  }
               }
            }

            if (var10 >= var8) {
               var6 = var15;
               this.setOffset(var1, var2, var15 << 8 | var8);

               for (int var17 = 0; var17 < var8; var17++) {
                  this.sectorFree.set(var6 + var17, false);
               }

               this.write(var6, var3, var4);
            } else {
               this.dataFile.seek(this.dataFile.length());
               var6 = this.sectorFree.size();

               for (int var16 = 0; var16 < var8; var16++) {
                  this.dataFile.write(emptySector);
                  this.sectorFree.add(false);
               }

               this.sizeDelta += 4096 * var8;
               this.write(var6, var3, var4);
               this.setOffset(var1, var2, var6 << 8 | var8);
            }
         }

         this.setChunkTimestamp(var1, var2, (int)(MinecraftServer.getCurrentTimeMillis() / (6938843882411018216L & -6938843883017096216L)));
      } catch (IOException var12) {
         var12.printStackTrace();
      }
   }

   public void write(int var1, byte[] var2, int var3) {
      this.dataFile.seek(var1 * 4096);
      this.dataFile.writeInt(var3 + 1);
      this.dataFile.writeByte(2);
      this.dataFile.write(var2, 0, var3);
   }

   public void setChunkTimestamp(int var1, int var2, int var3) {
      this.chunkTimestamps[var1 + var2 * 32] = var3;
      this.dataFile.seek(4096 + (var1 + var2 * 32) * 4);
      this.dataFile.writeInt(var3);
   }

   public RegionFile(File var1) {
      this.field_0008 = var1;
      this.sizeDelta = 0;

      try {
         if (var1.exists()) {
            this.lastModified = var1.lastModified();
         }

         this.dataFile = new RandomAccessFile(var1, "rw");
         if (this.dataFile.length() < (17575953L & 1480575699104340032L)) {
            for (int var2 = 0; var2 < 1024; var2++) {
               this.dataFile.writeInt(0);
            }

            for (int var7 = 0; var7 < 1024; var7++) {
               this.dataFile.writeInt(0);
            }

            this.sizeDelta += 8192;
         }

         if ((this.dataFile.length() & 2176909374176907263L & -2176909374506364929L) != (2739727745571882000L & -2739727745698102720L)) {
            for (int var8 = 0; var8 < (this.dataFile.length() & -879449052819091457L & 20479L); var8++) {
               this.dataFile.write(0);
            }
         }

         int var9 = (int)this.dataFile.length() / 4096;
         this.sectorFree = Lists.newArrayListWithCapacity(var9);

         for (int var3 = 0; var3 < var9; var3++) {
            this.sectorFree.add(true);
         }

         this.sectorFree.set(0, false);
         this.sectorFree.set(1, false);
         this.dataFile.seek(1611251717L & 6699174490204538370L);

         for (int var10 = 0; var10 < 1024; var10++) {
            int var4 = this.dataFile.readInt();
            this.offsets[var10] = var4;
            if (var4 != 0 && (var4 >> 8) + (var4 & 0xFF) <= this.sectorFree.size()) {
               for (int var5 = 0; var5 < (var4 & 0xFF); var5++) {
                  this.sectorFree.set((var4 >> 8) + var5, false);
               }
            }
         }

         for (int var11 = 0; var11 < 1024; var11++) {
            int var12 = this.dataFile.readInt();
            this.chunkTimestamps[var11] = var12;
         }
      } catch (IOException var6) {
         var6.printStackTrace();
      }
   }

   public synchronized DataInputStream getChunkDataInputStream(int var1, int var2) {
      if (this.outOfBounds(var1, var2)) {
         return null;
      } else {
         try {
            int var3 = this.getOffset(var1, var2);
            if (var3 == 0) {
               return null;
            } else {
               int var4 = var3 >> 8;
               int var5 = var3 & 0xFF;
               if (var4 + var5 > this.sectorFree.size()) {
                  return null;
               } else {
                  this.dataFile.seek(var4 * 4096);
                  int var6 = this.dataFile.readInt();
                  if (var6 > 4096 * var5) {
                     return null;
                  } else if (var6 <= 0) {
                     return null;
                  } else {
                     byte var7 = this.dataFile.readByte();
                     if (var7 == 1) {
                        byte[] var10 = new byte[var6 - 1];
                        this.dataFile.read(var10);
                        return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(var10))));
                     } else if (var7 == 2) {
                        byte[] var8 = new byte[var6 - 1];
                        this.dataFile.read(var8);
                        return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(var8))));
                     } else {
                        return null;
                     }
                  }
               }
            }
         } catch (IOException var9) {
            return null;
         }
      }
   }

   public int getOffset(int var1, int var2) {
      return this.offsets[var1 + var2 * 32];
   }

   public boolean isChunkSaved(int var1, int var2) {
      return this.getOffset(var1, var2) != 0;
   }

   public DataOutputStream getChunkDataOutputStream(int var1, int var2) {
      return this.outOfBounds(var1, var2) ? null : new DataOutputStream(new DeflaterOutputStream(new RegionFile$ChunkBuffer(this, var1, var2)));
   }

   public boolean outOfBounds(int var1, int var2) {
      return var1 < 0 || var1 >= 32 || var2 < 0 || var2 >= 32;
   }

   public void setOffset(int var1, int var2, int var3) {
      this.offsets[var1 + var2 * 32] = var3;
      this.dataFile.seek((var1 + var2 * 32) * 4);
      this.dataFile.writeInt(var3);
   }
}
