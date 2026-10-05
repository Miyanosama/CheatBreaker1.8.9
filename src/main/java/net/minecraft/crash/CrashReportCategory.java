package net.minecraft.crash;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public class CrashReportCategory {
   public String name;
   public StackTraceElement[] stackTrace;
   public List<CrashReportCategory.Entry> children = Lists.newArrayList();
   public CrashReport crashReport;

   public void trimStackTraceEntriesFromBottom(int var1) {
      StackTraceElement[] var2 = new StackTraceElement[this.stackTrace.length - var1];
      System.arraycopy(this.stackTrace, 0, var2, 0, var2.length);
      this.stackTrace = var2;
   }

   public int getPrunedStackTrace(int var1) {
      StackTraceElement[] var2 = Thread.currentThread().getStackTrace();
      if (var2.length <= 0) {
         return 0;
      } else {
         this.stackTrace = new StackTraceElement[var2.length - 3 - var1];
         System.arraycopy(var2, 3 + var1, this.stackTrace, 0, this.stackTrace.length);
         return this.stackTrace.length;
      }
   }

   public void addCrashSectionThrowable(String var1, Throwable var2) {
      this.addCrashSection(var1, var2);
   }

   public void appendToStringBuilder(StringBuilder var1) {
      var1.append("-- ").append(this.name).append(" --\n");
      var1.append("Details:");

      for (CrashReportCategory.Entry var3 : this.children) {
         var1.append("\n\t");
         var1.append(var3.getKey());
         var1.append(": ");
         var1.append(var3.getValue());
      }

      if (this.stackTrace != null && this.stackTrace.length > 0) {
         var1.append("\nStacktrace:");

         for (StackTraceElement var5 : this.stackTrace) {
            var1.append("\n\tat ");
            var1.append(var5.toString());
         }
      }
   }

   public void addCrashSectionCallable(String var1, Callable<String> var2) {
      try {
         this.addCrashSection(var1, var2.call());
      } catch (Throwable var4) {
         this.addCrashSectionThrowable(var1, var4);
      }
   }

   public void addCrashSection(String var1, Object var2) {
      this.children.add(new CrashReportCategory.Entry(var1, var2));
   }

   public StackTraceElement[] getStackTrace() {
      return this.stackTrace;
   }

   public static void addBlockInfo(CrashReportCategory var0, final BlockPos var1, final IBlockState var2) {
      var0.addCrashSectionCallable("Block", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return var2.toString();
         }
      });
      var0.addCrashSectionCallable("Block location", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return CrashReportCategory.getCoordinateInfo(var1);
         }
      });
   }

   public static void addBlockInfo(CrashReportCategory var0, final BlockPos var1, final Block var2, final int var3) {
      final int var4 = Block.getIdFromBlock(var2);
      var0.addCrashSectionCallable("Block type", new Callable<String>() {
         public String call() throws java.lang.Exception {
            try {
               return String.format("ID #%d (%s // %s)", var4, var2.getUnlocalizedName(), var2.getClass().getCanonicalName());
            } catch (Throwable var2x) {
               return "ID #" + var4;
            }
         }
      });
      var0.addCrashSectionCallable("Block data value", new Callable<String>() {
         public String call() throws java.lang.Exception {
            if (var3 < 0) {
               return "Unknown? (Got " + var3 + ")";
            } else {
               String var1 = String.format("%4s", Integer.toBinaryString(var3)).replace(" ", "0");
               return String.format("%1$d / 0x%1$X / 0b%2$s", var3, var1);
            }
         }
      });
      var0.addCrashSectionCallable("Block location", new Callable<String>() {
         public String call() throws java.lang.Exception {
            return CrashReportCategory.getCoordinateInfo(var1);
         }
      });
   }

   public static String getCoordinateInfo(double var0, double var2, double var4) {
      return String.format("%.2f,%.2f,%.2f - %s", var0, var2, var4, getCoordinateInfo(new BlockPos(var0, var2, var4)));
   }

   public static String getCoordinateInfo(BlockPos var0) {
      int var1 = var0.getX();
      int var2 = var0.getY();
      int var3 = var0.getZ();
      StringBuilder var4 = new StringBuilder();

      try {
         var4.append(String.format("World: (%d,%d,%d)", var1, var2, var3));
      } catch (Throwable var17) {
         var4.append("(Error finding world loc)");
      }

      var4.append(", ");

      try {
         int var5 = var1 >> 4;
         int var6 = var3 >> 4;
         int var7 = var1 & 15;
         int var8 = var2 >> 4;
         int var9 = var3 & 15;
         int var10 = var5 << 4;
         int var11 = var6 << 4;
         int var12 = (var5 + 1 << 4) - 1;
         int var13 = (var6 + 1 << 4) - 1;
         var4.append(
            String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", var7, var8, var9, var5, var6, var10, var11, var12, var13)
         );
      } catch (Throwable var16) {
         var4.append("(Error finding chunk loc)");
      }

      var4.append(", ");

      try {
         int var18 = var1 >> 9;
         int var19 = var3 >> 9;
         int var20 = var18 << 5;
         int var21 = var19 << 5;
         int var22 = (var18 + 1 << 5) - 1;
         int var23 = (var19 + 1 << 5) - 1;
         int var24 = var18 << 9;
         int var25 = var19 << 9;
         int var26 = (var18 + 1 << 9) - 1;
         int var14 = (var19 + 1 << 9) - 1;
         var4.append(
            String.format(
               "Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)",
               var18,
               var19,
               var20,
               var21,
               var22,
               var23,
               var24,
               var25,
               var26,
               var14
            )
         );
      } catch (Throwable var15) {
         var4.append("(Error finding world loc)");
      }

      return var4.toString();
   }

   public boolean firstTwoElementsOfStackTraceMatch(StackTraceElement var1, StackTraceElement var2) {
      if (this.stackTrace.length != 0 && var1 != null) {
         StackTraceElement var3 = this.stackTrace[0];
         if (var3.isNativeMethod() == var1.isNativeMethod()
            && var3.getClassName().equals(var1.getClassName())
            && var3.getFileName().equals(var1.getFileName())
            && var3.getMethodName().equals(var1.getMethodName())) {
            if (var2 != null != this.stackTrace.length > 1) {
               return false;
            } else if (var2 != null && !this.stackTrace[1].equals(var2)) {
               return false;
            } else {
               this.stackTrace[0] = var1;
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public CrashReportCategory(CrashReport var1, String var2) {
      this.stackTrace = new StackTraceElement[0];
      this.crashReport = var1;
      this.name = var2;
   }

   public static class Entry {
      public String value;
      public String key;

      public String getKey() {
         return this.key;
      }

      public String getValue() {
         return this.value;
      }

      public Entry(String var1, Object var2) {
         this.key = var1;
         if (var2 == null) {
            this.value = "~~NULL~~";
         } else if (var2 instanceof Throwable) {
            Throwable var3 = (Throwable)var2;
            this.value = "~~ERROR~~ " + var3.getClass().getSimpleName() + ": " + var3.getMessage();
         } else {
            this.value = var2.toString();
         }
      }
   }
}
