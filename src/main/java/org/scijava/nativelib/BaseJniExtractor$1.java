package org.scijava.nativelib;

import java.io.File;
import java.io.FilenameFilter;
import org.scijava.nativelib.BaseJniExtractor;

public class BaseJniExtractor$1 implements FilenameFilter {
   public String recoveredField78;
   public String recoveredField79;
   public BaseJniExtractor recoveredField80;

   public BaseJniExtractor$1(BaseJniExtractor var1, String var2, String var3) {
      this.recoveredField80 = var1;
      this.recoveredField79 = var2;
      this.recoveredField78 = var3;
   }

   @Override
   public boolean accept(File var1, String var2) {
      return var2.startsWith(this.recoveredField79) && var2.endsWith(this.recoveredField78);
   }
}
