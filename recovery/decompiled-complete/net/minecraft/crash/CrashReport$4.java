package net.minecraft.crash;

import java.util.concurrent.Callable;
import org.apache.log4j.pattern.RelativeTimePatternConverter;

public class CrashReport$4 implements Callable<String> {
   public RelativeTimePatternConverter field_0000;

   public CrashReport$4(CrashReport var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      return System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
   }
}
