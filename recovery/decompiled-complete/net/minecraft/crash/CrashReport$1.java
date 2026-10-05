package net.minecraft.crash;

import io.netty.util.Recycler$2;
import java.util.concurrent.Callable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.stats.StatisticsFile;

public class CrashReport$1 implements Callable<String> {
   public Recycler$2 field_0001;
   public StatisticsFile field_0000;
   public CreativeTabs field_0002;

   public CrashReport$1(CrashReport var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      return "1.8.9";
   }
}
