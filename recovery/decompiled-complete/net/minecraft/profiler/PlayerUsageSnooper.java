package net.minecraft.profiler;

import com.google.common.collect.Maps;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;
import java.util.Map.Entry;
import junit.awtui.TestRunner$7;
import recovered.unidentified.UnidentifiedClass0877;

public class PlayerUsageSnooper {
   public String uniqueID;
   public int selfCounter;
   public long minecraftStartTimeMilis;
   public boolean isRunning;
   public IPlayerUsage playerStatsCollector;
   public StringDecoder field_0002;
   public TestRunner$7 field_0012;
   public ReadTimeoutException field_0009;
   public Map<String, Object> clientStats;
   public Object syncLock;
   public Map<String, Object> snooperStats = Maps.newHashMap();
   public UnidentifiedClass0877 field_0007;
   public Timer threadTrigger;
   public URL serverUrl;

   public long getMinecraftStartTimeMillis() {
      return this.minecraftStartTimeMilis;
   }

   public void addJvmArgsToSnooper() {
      RuntimeMXBean var1 = ManagementFactory.getRuntimeMXBean();
      List var2 = var1.getInputArguments();
      int var3 = 0;

      for (String var5 : var2) {
         if (var5.startsWith("-X")) {
            this.addClientStat("jvm_arg[" + var3++ + "]", var5);
         }
      }

      this.addClientStat("jvm_args", var3);
   }

   public Map<String, String> getCurrentStats() {
      LinkedHashMap var1 = Maps.newLinkedHashMap();
      synchronized (this.syncLock) {
         this.addMemoryStatsToSnooper();

         for (Entry var4 : this.snooperStats.entrySet()) {
            var1.put(var4.getKey(), var4.getValue().toString());
         }

         for (Entry var8 : this.clientStats.entrySet()) {
            var1.put(var8.getKey(), var8.getValue().toString());
         }

         return var1;
      }
   }

   public String getUniqueID() {
      return this.uniqueID;
   }

   public void startSnooper() {
      if (!this.isRunning) {
         this.isRunning = true;
         this.addOSData();
         this.threadTrigger.schedule(new PlayerUsageSnooper$1(this), 9514752L & 7783422482815389860L, 157137852L & -5077113626191593566L);
      }
   }

   public void addMemoryStatsToSnooper() {
      this.addStatToSnooper("memory_total", Runtime.getRuntime().totalMemory());
      this.addStatToSnooper("memory_max", Runtime.getRuntime().maxMemory());
      this.addStatToSnooper("memory_free", Runtime.getRuntime().freeMemory());
      this.addStatToSnooper("cpu_cores", Runtime.getRuntime().availableProcessors());
      this.playerStatsCollector.addServerStatsToSnooper(this);
   }

   public boolean isSnooperRunning() {
      return this.isRunning;
   }

   public void addOSData() {
      this.addJvmArgsToSnooper();
      this.addClientStat("snooper_token", this.uniqueID);
      this.addStatToSnooper("snooper_token", this.uniqueID);
      this.addStatToSnooper("os_name", System.getProperty("os.name"));
      this.addStatToSnooper("os_version", System.getProperty("os.version"));
      this.addStatToSnooper("os_architecture", System.getProperty("os.arch"));
      this.addStatToSnooper("java_version", System.getProperty("java.version"));
      this.addClientStat("version", "1.8.9");
      this.playerStatsCollector.addServerTypeToSnooper(this);
   }

   public void stopSnooper() {
      this.threadTrigger.cancel();
   }

   public void addClientStat(String var1, Object var2) {
      synchronized (this.syncLock) {
         this.clientStats.put(var1, var2);
      }
   }

   public void addStatToSnooper(String var1, Object var2) {
      synchronized (this.syncLock) {
         this.snooperStats.put(var1, var2);
      }
   }

   public PlayerUsageSnooper(String var1, IPlayerUsage var2, long var3) {
      this.clientStats = Maps.newHashMap();
      this.uniqueID = UUID.randomUUID().toString();
      this.threadTrigger = new Timer("Snooper Timer", true);
      this.syncLock = new Object();

      try {
         this.serverUrl = new URL("http://snoop.minecraft.net/" + var1 + "?version=" + 2);
      } catch (MalformedURLException var6) {
         throw new IllegalArgumentException();
      }

      this.playerStatsCollector = var2;
      this.minecraftStartTimeMilis = var3;
   }
}
