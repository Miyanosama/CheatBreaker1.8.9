package net.optifine;

import com.cheatbreaker.client.event.type.LoadWorldEvent;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$3;
import recovered.unidentified.UnidentifiedClass1132;

public class LightMapPack {
   public int[] colorBuffer2;
   public ModelOcelot field_0007;
   public LightMap lightMapThunder;
   public LightMap lightMap;
   public LogBrokerMonitor$3 field_0000;
   public int[] colorBuffer1 = new int[0];
   public UnidentifiedClass1132 field_0008;
   public LightMap lightMapRain;
   public LoadWorldEvent field_0002;

   public boolean updateLightmap(World var1, float var2, int[] var3, boolean var4, float var5) {
      if (this.lightMapRain == null && this.lightMapThunder == null) {
         return this.lightMap.updateLightmap(var1, var2, var3, var4);
      } else {
         int var6 = var1.t.getDimensionId();
         if (var6 != 1 && var6 != -1) {
            float var7 = var1.j(var5);
            float var8 = var1.h(var5);
            float var9 = 1.0E-4F;
            boolean var10 = var7 > var9;
            boolean var11 = var8 > var9;
            if (!var10 && !var11) {
               return this.lightMap.updateLightmap(var1, var2, var3, var4);
            } else {
               if (var7 > 0.0F) {
                  var8 /= var7;
               }

               float var12 = 1.0F - var7;
               float var13 = var7 - var8;
               if (this.colorBuffer1.length != var3.length) {
                  this.colorBuffer1 = new int[var3.length];
                  this.colorBuffer2 = new int[var3.length];
               }

               int var14 = 0;
               int[][] var15 = new int[][]{var3, this.colorBuffer1, this.colorBuffer2};
               float[] var16 = new float[3];
               if (var12 > var9 && this.lightMap.updateLightmap(var1, var2, var15[var14], var4)) {
                  var16[var14] = var12;
                  var14++;
               }

               if (var13 > var9 && this.lightMapRain != null && this.lightMapRain.updateLightmap(var1, var2, var15[var14], var4)) {
                  var16[var14] = var13;
                  var14++;
               }

               if (var8 > var9 && this.lightMapThunder != null && this.lightMapThunder.updateLightmap(var1, var2, var15[var14], var4)) {
                  var16[var14] = var8;
                  var14++;
               }

               return var14 == 2
                  ? this.blend(var15[0], var16[0], var15[1], var16[1])
                  : (var14 == 3 ? this.blend(var15[0], var16[0], var15[1], var16[1], var15[2], var16[2]) : true);
            }
         } else {
            return this.lightMap.updateLightmap(var1, var2, var3, var4);
         }
      }
   }

   public boolean blend(int[] var1, float var2, int[] var3, float var4) {
      if (var3.length != var1.length) {
         return false;
      } else {
         for (int var5 = 0; var5 < var1.length; var5++) {
            int var6 = var1[var5];
            int var7 = var6 >> 16 & 0xFF;
            int var8 = var6 >> 8 & 0xFF;
            int var9 = var6 & 0xFF;
            int var10 = var3[var5];
            int var11 = var10 >> 16 & 0xFF;
            int var12 = var10 >> 8 & 0xFF;
            int var13 = var10 & 0xFF;
            int var14 = (int)(var7 * var2 + var11 * var4);
            int var15 = (int)(var8 * var2 + var12 * var4);
            int var16 = (int)(var9 * var2 + var13 * var4);
            var1[var5] = 0xFF000000 | var14 << 16 | var15 << 8 | var16;
         }

         return true;
      }
   }

   public LightMapPack(LightMap var1, LightMap var2, LightMap var3) {
      this.colorBuffer2 = new int[0];
      if (var2 != null || var3 != null) {
         if (var2 == null) {
            var2 = var1;
         }

         if (var3 == null) {
            var3 = var2;
         }
      }

      this.lightMap = var1;
      this.lightMapRain = var2;
      this.lightMapThunder = var3;
   }

   public boolean blend(int[] var1, float var2, int[] var3, float var4, int[] var5, float var6) {
      if (var3.length == var1.length && var5.length == var1.length) {
         for (int var7 = 0; var7 < var1.length; var7++) {
            int var8 = var1[var7];
            int var9 = var8 >> 16 & 0xFF;
            int var10 = var8 >> 8 & 0xFF;
            int var11 = var8 & 0xFF;
            int var12 = var3[var7];
            int var13 = var12 >> 16 & 0xFF;
            int var14 = var12 >> 8 & 0xFF;
            int var15 = var12 & 0xFF;
            int var16 = var5[var7];
            int var17 = var16 >> 16 & 0xFF;
            int var18 = var16 >> 8 & 0xFF;
            int var19 = var16 & 0xFF;
            int var20 = (int)(var9 * var2 + var13 * var4 + var17 * var6);
            int var21 = (int)(var10 * var2 + var14 * var4 + var18 * var6);
            int var22 = (int)(var11 * var2 + var15 * var4 + var19 * var6);
            var1[var7] = 0xFF000000 | var20 << 16 | var21 << 8 | var22;
         }

         return true;
      } else {
         return false;
      }
   }
}
