package net.optifine.shaders;

import com.google.common.base.Joiner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.src.Config;
import net.optifine.util.StrUtils;

public class ShaderPackZip implements IShaderPack {
   public File packFile;
   public String baseFolder;
   public GuiScreenServerList field_0001;
   public ZipFile packZipFile;
   public ModelSquid field_0000;

   @Override
   public InputStream getResourceAsStream(String var1) {
      try {
         if (this.packZipFile == null) {
            this.packZipFile = new ZipFile(this.packFile);
            this.baseFolder = this.detectBaseFolder(this.packZipFile);
         }

         String var2 = StrUtils.removePrefix(var1, "/");
         if (var2.contains("..")) {
            var2 = this.resolveRelative(var2);
         }

         ZipEntry var3 = this.packZipFile.getEntry(this.baseFolder + var2);
         return var3 == null ? null : this.packZipFile.getInputStream(var3);
      } catch (Exception var4) {
         return null;
      }
   }

   @Override
   public boolean hasDirectory(String var1) {
      try {
         if (this.packZipFile == null) {
            this.packZipFile = new ZipFile(this.packFile);
            this.baseFolder = this.detectBaseFolder(this.packZipFile);
         }

         String var2 = StrUtils.removePrefix(var1, "/");
         ZipEntry var3 = this.packZipFile.getEntry(this.baseFolder + var2);
         return var3 != null;
      } catch (IOException var4) {
         return false;
      }
   }

   public String detectBaseFolder(ZipFile var1) {
      ZipEntry var2 = var1.getEntry("shaders/");
      if (var2 != null && var2.isDirectory()) {
         return "";
      } else {
         Pattern var3 = Pattern.compile("([^/]+/)shaders/");
         Enumeration var4 = var1.entries();

         while (var4.hasMoreElements()) {
            ZipEntry var5 = (ZipEntry)var4.nextElement();
            String var6 = var5.getName();
            Matcher var7 = var3.matcher(var6);
            if (var7.matches()) {
               String var8 = var7.group(1);
               if (var8 != null) {
                  if (var8.equals("shaders/")) {
                     return "";
                  }

                  return var8;
               }
            }
         }

         return "";
      }
   }

   public ShaderPackZip(String var1, File var2) {
      this.packFile = var2;
      this.packZipFile = null;
      this.baseFolder = "";
   }

   @Override
   public void close() {
      if (this.packZipFile != null) {
         try {
            this.packZipFile.close();
         } catch (Exception var2) {
         }

         this.packZipFile = null;
      }
   }

   public String resolveRelative(String var1) {
      ArrayDeque var2 = new ArrayDeque();
      String[] var3 = Config.tokenize(var1, "/");

      for (int var4 = 0; var4 < var3.length; var4++) {
         String var5 = var3[var4];
         if (var5.equals("..")) {
            if (var2.isEmpty()) {
               return "";
            }

            var2.removeLast();
         } else {
            var2.add(var5);
         }
      }

      return Joiner.on('/').join(var2);
   }

   @Override
   public String getName() {
      return this.packFile.getName();
   }
}
