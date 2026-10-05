package net.optifine.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;

public class ResUtils {
   public static Properties readProperties(String var0, String var1) {
      ResourceLocation var2 = new ResourceLocation(var0);

      try {
         InputStream var3 = Config.getResourceStream(var2);
         if (var3 == null) {
            return null;
         } else {
            PropertiesOrdered var4 = new PropertiesOrdered();
            var4.load(var3);
            var3.close();
            Config.dbg("" + var1 + ": Loading " + var0);
            return var4;
         }
      } catch (FileNotFoundException var5) {
         return null;
      } catch (IOException var6) {
         Config.warn("" + var1 + ": Error reading " + var0);
         return null;
      }
   }

   public static String[] collectFilesFolder(File var0, String var1, String[] var2, String[] var3) {
      ArrayList var4 = new ArrayList();
      String var5 = "assets/minecraft/";
      File[] var6 = var0.listFiles();
      if (var6 == null) {
         return new String[0];
      } else {
         for (int var7 = 0; var7 < var6.length; var7++) {
            File var8 = var6[var7];
            if (var8.isFile()) {
               String var13 = var1 + var8.getName();
               if (var13.startsWith(var5)) {
                  var13 = var13.substring(var5.length());
                  if (StrUtils.startsWith(var13, var2) && StrUtils.endsWith(var13, var3)) {
                     var4.add(var13);
                  }
               }
            } else if (var8.isDirectory()) {
               String var9 = var1 + var8.getName() + "/";
               String[] var10 = collectFilesFolder(var8, var9, var2, var3);

               for (int var11 = 0; var11 < var10.length; var11++) {
                  String var12 = var10[var11];
                  var4.add(var12);
               }
            }
         }

         return (java.lang.String[])var4.toArray(new String[var4.size()]);
      }
   }

   public static String[] collectFiles(IResourcePack var0, String var1, String var2, String[] var3) {
      return collectFiles(var0, new String[]{var1}, new String[]{var2}, var3);
   }

   public static String[] collectFilesZIP(File var0, String[] var1, String[] var2) {
      ArrayList var3 = new ArrayList();
      String var4 = "assets/minecraft/";

      try {
         ZipFile var5 = new ZipFile(var0);
         Enumeration var6 = var5.entries();

         while (var6.hasMoreElements()) {
            ZipEntry var7 = (ZipEntry)var6.nextElement();
            String var8 = var7.getName();
            if (var8.startsWith(var4)) {
               var8 = var8.substring(var4.length());
               if (StrUtils.startsWith(var8, var1) && StrUtils.endsWith(var8, var2)) {
                  var3.add(var8);
               }
            }
         }

         var5.close();
         return (java.lang.String[])var3.toArray(new String[var3.size()]);
      } catch (IOException var9) {
         var9.printStackTrace();
         return new String[0];
      }
   }

   public static String[] collectFilesFixed(IResourcePack var0, String[] var1) {
      if (var1 == null) {
         return new String[0];
      } else {
         ArrayList var2 = new ArrayList();

         for (int var3 = 0; var3 < var1.length; var3++) {
            String var4 = var1[var3];
            ResourceLocation var5 = new ResourceLocation(var4);
            if (var0.resourceExists(var5)) {
               var2.add(var4);
            }
         }

         return (java.lang.String[])var2.toArray(new String[var2.size()]);
      }
   }

   public static String[] collectFiles(String[] var0, String[] var1) {
      LinkedHashSet var2 = new LinkedHashSet();
      IResourcePack[] var3 = Config.getResourcePacks();

      for (int var4 = 0; var4 < var3.length; var4++) {
         IResourcePack var5 = var3[var4];
         String[] var6 = collectFiles(var5, var0, var1, (String[])null);
         var2.addAll(Arrays.asList(var6));
      }

      return (java.lang.String[])var2.toArray(new String[var2.size()]);
   }

   public static String[] collectFiles(String var0, String var1) {
      return collectFiles(new String[]{var0}, new String[]{var1});
   }

   public static boolean isLowercase(String var0) {
      return var0.equals(var0.toLowerCase(Locale.ROOT));
   }

   public static Properties readProperties(InputStream var0, String var1) {
      if (var0 == null) {
         return null;
      } else {
         try {
            PropertiesOrdered var2 = new PropertiesOrdered();
            var2.load(var0);
            var0.close();
            return var2;
         } catch (FileNotFoundException var3) {
            return null;
         } catch (IOException var4) {
            return null;
         }
      }
   }

   public static String[] collectFiles(IResourcePack var0, String[] var1, String[] var2) {
      return collectFiles(var0, var1, var2, (String[])null);
   }

   public static String[] collectFiles(IResourcePack var0, String[] var1, String[] var2, String[] var3) {
      if (var0 instanceof DefaultResourcePack) {
         return collectFilesFixed(var0, var3);
      } else if (!(var0 instanceof AbstractResourcePack)) {
         Config.warn("Unknown resource pack type: " + var0);
         return new String[0];
      } else {
         AbstractResourcePack var4 = (AbstractResourcePack)var0;
         File var5 = var4.a;
         if (var5 == null) {
            return new String[0];
         } else if (var5.isDirectory()) {
            return collectFilesFolder(var5, "", var1, var2);
         } else if (var5.isFile()) {
            return collectFilesZIP(var5, var1, var2);
         } else {
            Config.warn("Unknown resource pack file: " + var5);
            return new String[0];
         }
      }
   }
}
