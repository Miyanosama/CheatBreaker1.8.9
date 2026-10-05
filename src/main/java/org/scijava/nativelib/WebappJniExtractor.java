package org.scijava.nativelib;

import java.io.File;
import java.io.IOException;
import org.scijava.nativelib.BaseJniExtractor;

public class WebappJniExtractor extends BaseJniExtractor {
   public File recoveredField3197;
   public File recoveredField3198 = new File(System.getProperty("java.library.tmpdir", "tmplib"));

   @Override
   public File method_12332() {
      return this.recoveredField3198;
   }

   @Override
   public File method_12334() {
      return this.recoveredField3197;
   }

   @Override
   public void finalize() throws java.io.IOException {
      File[] var1 = this.recoveredField3197.listFiles();

      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2].delete();
      }

      this.recoveredField3197.delete();
   }

   public WebappJniExtractor(String var1) throws java.io.IOException {
      this.recoveredField3198.mkdirs();
      if (!this.recoveredField3198.isDirectory()) {
         throw new IOException("Unable to create native library working directory " + this.recoveredField3198);
      } else {
         long var2 = System.currentTimeMillis();
         int var5 = 0;

         while (true) {
            File var4 = new File(this.recoveredField3198, var1 + "." + var2 + "." + var5);
            if (var4.mkdir()) {
               this.recoveredField3197 = var4;
               this.recoveredField3197.deleteOnExit();
               return;
            }

            if (!var4.exists()) {
               throw new IOException("Unable to create native library working directory " + var4);
            }

            var5++;
         }
      }
   }
}
