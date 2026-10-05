package net.optifine.util;

import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$DesertPyramid;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;

public class CacheLocalByte {
   public int maxX = 18;
   public int offsetY;
   public int offsetX;
   public byte[] lastZs;
   public int lastDz;
   public int maxY = 128;
   public int maxZ = 18;
   public byte[][][] cache;
   public StructureStrongholdPieces$Stairs2 field_0003;
   public int offsetZ;
   public ComponentScatteredFeaturePieces$DesertPyramid field_0000;

   public CacheLocalByte(int var1, int var2, int var3) {
      this.offsetX = 0;
      this.offsetY = 0;
      this.offsetZ = 0;
      this.cache = (byte[][][])null;
      this.lastZs = null;
      this.lastDz = 0;
      this.maxX = var1;
      this.maxY = var2;
      this.maxZ = var3;
      this.cache = new byte[var1][var2][var3];
      this.resetCache();
   }

   public byte get(int var1, int var2, int var3) {
      try {
         this.lastZs = this.cache[var1 - this.offsetX][var2 - this.offsetY];
         this.lastDz = var3 - this.offsetZ;
         return this.lastZs[this.lastDz];
      } catch (ArrayIndexOutOfBoundsException var5) {
         var5.printStackTrace();
         return -1;
      }
   }

   public void setLast(byte var1) {
      try {
         this.lastZs[this.lastDz] = var1;
      } catch (Exception var3) {
         var3.printStackTrace();
      }
   }

   public void setOffset(int var1, int var2, int var3) {
      this.offsetX = var1;
      this.offsetY = var2;
      this.offsetZ = var3;
      this.resetCache();
   }

   public void resetCache() {
      for (int var1 = 0; var1 < this.maxX; var1++) {
         byte[][] var2 = this.cache[var1];

         for (int var3 = 0; var3 < this.maxY; var3++) {
            byte[] var4 = var2[var3];

            for (int var5 = 0; var5 < this.maxZ; var5++) {
               var4[var5] = -1;
            }
         }
      }
   }
}
