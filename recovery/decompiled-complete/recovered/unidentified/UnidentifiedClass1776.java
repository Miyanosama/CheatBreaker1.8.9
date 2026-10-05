package recovered.unidentified;

import java.io.File;
import java.io.IOException;
import net.optifine.ClearWater;
import org.scijava.nativelib.BaseJniExtractor;

public class UnidentifiedClass1776 extends BaseJniExtractor {
   public ClearWater field_0001;
   public File field_0000;
   public File field_0002 = new File(System.getProperty("java.library.tmpdir", "tmplib"));

   @Override
   public File method_12332() {
      return this.field_0002;
   }

   @Override
   public File method_12334() {
      return this.field_0000;
   }

   @Override
   public void finalize() {
      File[] var1 = this.field_0000.listFiles();

      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2].delete();
      }

      this.field_0000.delete();
   }

   public UnidentifiedClass1776(String var1) {
      this.field_0002.mkdirs();
      if (!this.field_0002.isDirectory()) {
         throw new IOException("Unable to create native library working directory " + this.field_0002);
      } else {
         long var2 = System.currentTimeMillis();
         int var5 = 0;

         while (true) {
            File var4 = new File(this.field_0002, var1 + "." + var2 + "." + var5);
            if (var4.mkdir()) {
               this.field_0000 = var4;
               this.field_0000.deleteOnExit();
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
