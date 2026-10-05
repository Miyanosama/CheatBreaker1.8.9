package net.optifine.util;

import io.netty.util.concurrent.DefaultPromise$3;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.command.server.CommandSaveOff;

public class CacheLocal {
   public int maxZ;
   public int[] lastZs;
   public int offsetY;
   public ShaderGroup field_0009;
   public int offsetZ;
   public int maxY;
   public TextureManager field_0011;
   public int lastDz;
   public CommandSaveOff field_0003;
   public DefaultPromise$3 field_0012;
   public int offsetX;
   public int maxX = 18;
   public int[][][] cache;

   public CacheLocal(int var1, int var2, int var3) {
      this.maxY = 128;
      this.maxZ = 18;
      this.offsetX = 0;
      this.offsetY = 0;
      this.offsetZ = 0;
      this.cache = (int[][][])null;
      this.lastZs = null;
      this.lastDz = 0;
      this.maxX = var1;
      this.maxY = var2;
      this.maxZ = var3;
      this.cache = new int[var1][var2][var3];
      this.resetCache();
   }

   public void setLast(int var1) {
      try {
         this.lastZs[this.lastDz] = var1;
      } catch (Exception var3) {
         var3.printStackTrace();
      }
   }

   public void resetCache() {
      for (int var1 = 0; var1 < this.maxX; var1++) {
         int[][] var2 = this.cache[var1];

         for (int var3 = 0; var3 < this.maxY; var3++) {
            int[] var4 = var2[var3];

            for (int var5 = 0; var5 < this.maxZ; var5++) {
               var4[var5] = -1;
            }
         }
      }
   }

   public void setOffset(int var1, int var2, int var3) {
      this.offsetX = var1;
      this.offsetY = var2;
      this.offsetZ = var3;
      this.resetCache();
   }

   public int get(int var1, int var2, int var3) {
      try {
         this.lastZs = this.cache[var1 - this.offsetX][var2 - this.offsetY];
         this.lastDz = var3 - this.offsetZ;
         return this.lastZs[this.lastDz];
      } catch (ArrayIndexOutOfBoundsException var5) {
         var5.printStackTrace();
         return -1;
      }
   }
}
