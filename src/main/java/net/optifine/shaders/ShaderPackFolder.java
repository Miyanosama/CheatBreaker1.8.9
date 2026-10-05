package net.optifine.shaders;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.optifine.shaders.IShaderPack;
import net.optifine.util.StrUtils;

public class ShaderPackFolder implements IShaderPack {
   public File recoveredField1926;

   @Override
   public InputStream getResourceAsStream(String var1) {
      try {
         String var2 = StrUtils.removePrefixSuffix(var1, "/", "/");
         File var3 = new File(this.recoveredField1926, var2);
         return !var3.exists() ? null : new BufferedInputStream(new FileInputStream(var3));
      } catch (Exception var4) {
         return null;
      }
   }

   @Override
   public String getName() {
      return this.recoveredField1926.getName();
   }

   @Override
   public boolean hasDirectory(String var1) {
      File var2 = new File(this.recoveredField1926, var1.substring(1));
      return !var2.exists() ? false : var2.isDirectory();
   }

   @Override
   public void close() {
   }

   public ShaderPackFolder(String var1, File var2) {
      this.recoveredField1926 = var2;
   }
}
