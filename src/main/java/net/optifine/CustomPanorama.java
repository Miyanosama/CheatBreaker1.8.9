package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.util.MathUtils;
import net.optifine.util.PropertiesOrdered;

public class CustomPanorama {
   public static CustomPanoramaProperties customPanoramaProperties = null;
   public static Random random = new Random();

   public static int getRandomIndex(int[] var0) {
      int var1 = MathUtils.getSum(var0);
      int var2 = random.nextInt(var1);
      int var3 = 0;

      for (int var4 = 0; var4 < var0.length; var4++) {
         var3 += var0[var4];
         if (var3 > var2) {
            return var4;
         }
      }

      return var0.length - 1;
   }

   public static void update() {
      customPanoramaProperties = null;
      String[] var0 = getPanoramaFolders();
      if (var0.length > 1) {
         Properties[] var1 = getPanoramaProperties(var0);
         int[] var2 = getWeights(var1);
         int var3 = getRandomIndex(var2);
         String var4 = var0[var3];
         Object var5 = var1[var3];
         if (var5 == null) {
            var5 = var1[0];
         }

         if (var5 == null) {
            var5 = new PropertiesOrdered();
         }

         CustomPanoramaProperties var6 = new CustomPanoramaProperties(var4, (Properties)var5);
         customPanoramaProperties = var6;
      }
   }

   public static int[] getWeights(Properties[] var0) {
      int[] var1 = new int[var0.length];

      for (int var2 = 0; var2 < var1.length; var2++) {
         Properties var3 = var0[var2];
         if (var3 == null) {
            var3 = var0[0];
         }

         if (var3 == null) {
            var1[var2] = 1;
         } else {
            String var4 = var3.getProperty("weight", (String)null);
            var1[var2] = Config.parseInt(var4, 1);
         }
      }

      return var1;
   }

   public static Properties[] getPanoramaProperties(String[] var0) {
      Properties[] var1 = new Properties[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         String var3 = var0[var2];
         if (var2 == 0) {
            var3 = "optifine/gui";
         } else {
            Config.dbg("CustomPanorama: " + var3);
         }

         ResourceLocation var4 = new ResourceLocation(var3 + "/background.properties");

         try {
            InputStream var5 = Config.getResourceStream(var4);
            if (var5 != null) {
               PropertiesOrdered var6 = new PropertiesOrdered();
               var6.load(var5);
               Config.dbg("CustomPanorama: " + var4.getResourcePath());
               var1[var2] = var6;
               var5.close();
            }
         } catch (IOException var7) {
         }
      }

      return var1;
   }

   public static CustomPanoramaProperties getCustomPanoramaProperties() {
      return customPanoramaProperties;
   }

   public static String[] getPanoramaFolders() {
      ArrayList var0 = new ArrayList();
      var0.add("textures/gui/title/background");

      for (int var1 = 0; var1 < 100; var1++) {
         String var2 = "optifine/gui/background" + var1;
         String var3 = var2 + "/panorama_0.png";
         ResourceLocation var4 = new ResourceLocation(var3);
         if (Config.hasResource(var4)) {
            var0.add(var2);
         }
      }

      return (java.lang.String[])var0.toArray(new String[var0.size()]);
   }
}
