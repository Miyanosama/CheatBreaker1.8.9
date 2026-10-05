package net.optifine.util;

import net.minecraft.block.BlockPrismarine$EnumType;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.network.EnumConnectionState$2;

public class MemoryMonitor {
   public static long startTimeMs = System.currentTimeMillis();
   public static long lastMemory = MemoryMonitor.startMemory;
   public BlockPrismarine$EnumType field_0004;
   public static long lastTimeMs = startTimeMs;
   public static boolean gcEvent = false;
   public AbstractTexture field_0002;
   public EnumConnectionState$2 field_0009;
   public static int memBytesSec = 0;
   public static long MB = 1075713672L & 592486436L;
   public SkinManager field_0010;
   public static long startMemory = getMemoryUsed();

   public static long getAllocationRateMb() {
      return memBytesSec / MB;
   }

   public static long getStartMemoryMb() {
      return startMemory / MB;
   }

   public static long getMemoryUsed() {
      Runtime var0 = Runtime.getRuntime();
      return var0.totalMemory() - var0.freeMemory();
   }

   public static void update() {
      long var0 = System.currentTimeMillis();
      long var2 = getMemoryUsed();
      gcEvent = var2 < lastMemory;
      if (gcEvent) {
         long var4 = lastTimeMs - startTimeMs;
         long var6 = lastMemory - startMemory;
         double var8 = var4 / 1000.0;
         int var10 = (int)(var6 / var8);
         if (var10 > 0) {
            memBytesSec = var10;
         }

         startTimeMs = var0;
         startMemory = var2;
      }

      lastTimeMs = var0;
      lastMemory = var2;
   }

   public static long getStartTimeMs() {
      return startTimeMs;
   }

   public static boolean isGcEvent() {
      return gcEvent;
   }
}
