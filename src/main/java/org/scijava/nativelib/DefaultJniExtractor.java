package org.scijava.nativelib;

import java.io.File;
import java.io.IOException;
import org.scijava.nativelib.BaseJniExtractor;

public class DefaultJniExtractor extends BaseJniExtractor {
   public File recoveredField2436;

   public void method_27584(String var1) throws java.io.IOException {
      this.recoveredField2436 = new File(System.getProperty("java.library.tmpdir", var1));
      this.recoveredField2436.mkdirs();
      if (!this.recoveredField2436.isDirectory()) {
         throw new IOException("Unable to create native library working directory " + this.recoveredField2436);
      }
   }

   public DefaultJniExtractor(Class var1, String var2) throws java.io.IOException {
      super(var1);
      this.method_27584(var2);
   }

   public DefaultJniExtractor() throws java.io.IOException {
      super(null);
      this.method_27584("tmplib");
   }

   @Override
   public File method_12332() {
      return this.recoveredField2436;
   }

   @Override
   public File method_12334() {
      return this.recoveredField2436;
   }
}
