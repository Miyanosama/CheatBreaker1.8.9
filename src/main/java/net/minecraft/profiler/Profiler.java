package net.minecraft.profiler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.src.Config;
import net.optifine.Lagometer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Profiler {
   public static final String recoveredField2389 = "display";
   public static final String recoveredField2390 = "render";
   public List<Long> timestampList;
   public List<String> sectionList = Lists.newArrayList();
   public static final String recoveredField2391 = "tick";
   public static final String recoveredField2392 = "preRenderErrors";
   public static final String recoveredField2393 = "scheduledExecutables";
   public static Logger logger = LogManager.getLogger();
   public static int HASH_SCHEDULED_EXECUTABLES = "scheduledExecutables".hashCode();
   public String profilingSection;
   public boolean profilerLocalEnabled;
   public static int HASH_TICK = "tick".hashCode();
   public static int HASH_PRE_RENDER_ERRORS = "preRenderErrors".hashCode();
   public boolean profilerGlobalEnabled;
   public Map<String, Long> profilingMap;
   public static int HASH_RENDER = "render".hashCode();
   public boolean profilingEnabled;
   public static int HASH_DISPLAY = "display".hashCode();

   public String getNameOfLastSection() {
      return this.sectionList.size() == 0 ? "[UNKNOWN]" : this.sectionList.get(this.sectionList.size() - 1);
   }

   public void startSection(Class<?> var1) {
      if (this.profilingEnabled) {
         this.startSection(var1.getSimpleName());
      }
   }

   public void clearProfiling() {
      this.profilingMap.clear();
      this.profilingSection = "";
      this.sectionList.clear();
      this.profilerLocalEnabled = this.profilerGlobalEnabled;
   }

   public void endStartSection(String var1) {
      if (this.profilerLocalEnabled) {
         this.endSection();
         this.startSection(var1);
      }
   }

   public void startSection(String var1) {
      if (Lagometer.isActive()) {
         int var2 = var1.hashCode();
         if (var2 == HASH_SCHEDULED_EXECUTABLES && var1.equals("scheduledExecutables")) {
            Lagometer.recoveredField958.method_30327();
         } else if (var2 == HASH_TICK && var1.equals("tick") && Config.isMinecraftThread()) {
            Lagometer.recoveredField958.method_30328();
            Lagometer.recoveredField947.method_30327();
         } else if (var2 == HASH_PRE_RENDER_ERRORS && var1.equals("preRenderErrors")) {
            Lagometer.recoveredField947.method_30328();
         }
      }

      if (Config.isFastRender()) {
         int var3 = var1.hashCode();
         if (var3 == HASH_RENDER && var1.equals("render")) {
            GlStateManager.clearEnabled = false;
         } else if (var3 == HASH_DISPLAY && var1.equals("display")) {
            GlStateManager.clearEnabled = true;
         }
      }

      if (this.profilerLocalEnabled && this.profilingEnabled) {
         if (this.profilingSection.length() > 0) {
            this.profilingSection = this.profilingSection + ".";
         }

         this.profilingSection = this.profilingSection + var1;
         this.sectionList.add(this.profilingSection);
         this.timestampList.add(System.nanoTime());
      }
   }

   public List<Profiler.Result> getProfilingData(String var1) {
      if (!this.profilingEnabled) {
         return null;
      } else {
         long var2 = this.profilingMap.containsKey("root") ? this.profilingMap.get("root") : 0L;
         long var4 = this.profilingMap.containsKey(var1) ? this.profilingMap.get(var1) : -1L;
         ArrayList var6 = Lists.newArrayList();
         if (var1.length() > 0) {
            var1 = var1 + ".";
         }

         long var7 = 0L;

         for (String var10 : this.profilingMap.keySet()) {
            if (var10.length() > var1.length() && var10.startsWith(var1) && var10.indexOf(".", var1.length() + 1) < 0) {
               var7 += this.profilingMap.get(var10);
            }
         }

         float var19 = (float)var7;
         if (var7 < var4) {
            var7 = var4;
         }

         if (var2 < var7) {
            var2 = var7;
         }

         for (String var11 : this.profilingMap.keySet()) {
            if (var11.length() > var1.length() && var11.startsWith(var1) && var11.indexOf(".", var1.length() + 1) < 0) {
               long var12 = this.profilingMap.get(var11);
               double var14 = var12 * 100.0 / var7;
               double var16 = var12 * 100.0 / var2;
               String var18 = var11.substring(var1.length());
               var6.add(new Profiler.Result(var18, var14, var16));
            }
         }

         for (String var22 : this.profilingMap.keySet()) {
            this.profilingMap.put(var22, this.profilingMap.get(var22) * 950L / 1000L);
         }

         if ((float)var7 > var19) {
            var6.add(new Profiler.Result("unspecified", ((float)var7 - var19) * 100.0 / var7, ((float)var7 - var19) * 100.0 / var2));
         }

         Collections.sort(var6);
         var6.add(0, new Profiler.Result(var1, 100.0, var7 * 100.0 / var2));
         return var6;
      }
   }

   public void endSection() {
      if (this.profilerLocalEnabled && this.profilingEnabled) {
         long var1 = System.nanoTime();
         long var3 = this.timestampList.remove(this.timestampList.size() - 1);
         this.sectionList.remove(this.sectionList.size() - 1);
         long var5 = var1 - var3;
         if (this.profilingMap.containsKey(this.profilingSection)) {
            this.profilingMap.put(this.profilingSection, this.profilingMap.get(this.profilingSection) + var5);
         } else {
            this.profilingMap.put(this.profilingSection, var5);
         }

         if (var5 > 100000000L) {
            logger.warn("Something's taking too long! '" + this.profilingSection + "' took aprox " + var5 / 1000000.0 + " ms");
         }

         this.profilingSection = !this.sectionList.isEmpty() ? this.sectionList.get(this.sectionList.size() - 1) : "";
      }
   }

   public Profiler() {
      this.timestampList = Lists.newArrayList();
      this.profilingSection = "";
      this.profilingMap = Maps.newHashMap();
      this.profilerGlobalEnabled = true;
      this.profilerLocalEnabled = this.profilerGlobalEnabled;
   }

   public static final class Result implements Comparable<Profiler.Result> {
      public String field_76331_c;
      public double field_76332_a;
      public double field_76330_b;

      public int compareTo(Profiler.Result var1) {
         return var1.field_76332_a < this.field_76332_a ? -1 : (var1.field_76332_a > this.field_76332_a ? 1 : var1.field_76331_c.compareTo(this.field_76331_c));
      }

      public Result(String var1, double var2, double var4) {
         this.field_76331_c = var1;
         this.field_76332_a = var2;
         this.field_76330_b = var4;
      }

      public int getColor() {
         return (this.field_76331_c.hashCode() & 11184810) + 4473924;
      }
   }
}
