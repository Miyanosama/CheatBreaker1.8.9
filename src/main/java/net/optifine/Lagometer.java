package net.optifine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.profiler.Profiler;
import net.minecraft.src.Config;
import net.optifine.util.MemoryMonitor;
import org.lwjgl.opengl.GL11;
import net.optifine.Lagometer$TimerNano;

public class Lagometer {
   public static boolean active = false;
   public static Lagometer$TimerNano recoveredField947 = new Lagometer$TimerNano();
   public static Lagometer$TimerNano recoveredField958 = new Lagometer$TimerNano();
   public static Lagometer$TimerNano recoveredField945 = new Lagometer$TimerNano();
   public static Minecraft mc;
   public static Lagometer$TimerNano recoveredField952 = new Lagometer$TimerNano();
   public static Profiler profiler;
   public static Lagometer$TimerNano recoveredField953 = new Lagometer$TimerNano();
   public static Lagometer$TimerNano recoveredField944 = new Lagometer$TimerNano();
   public static GameSettings gameSettings;
   public static Lagometer$TimerNano recoveredField946 = new Lagometer$TimerNano();
   public static long[] recoveredField948 = new long[512];
   public static long[] recoveredField959 = new long[512];
   public static long[] recoveredField951 = new long[512];
   public static long[] recoveredField954 = new long[512];
   public static long[] recoveredField956 = new long[512];
   public static long[] recoveredField960 = new long[512];
   public static long[] recoveredField949 = new long[512];
   public static long[] recoveredField950 = new long[512];
   public static boolean[] gcs = new boolean[512];
   public static int numRecordedFrameTimes = 0;
   public static long recoveredField957 = -1L;
   public static long recoveredField955 = 0L;

   public static void showLagometer(ScaledResolution var0) {
      if (gameSettings != null && (gameSettings.ofLagometer || gameSettings.showLagometer)) {
         long var1 = System.nanoTime();
         GlStateManager.clear(256);
         GlStateManager.matrixMode(5889);
         GlStateManager.pushMatrix();
         GlStateManager.enableColorMaterial();
         GlStateManager.loadIdentity();
         GlStateManager.ortho(0.0, mc.displayWidth, mc.displayHeight, 0.0, 1000.0, 3000.0);
         GlStateManager.matrixMode(5888);
         GlStateManager.pushMatrix();
         GlStateManager.loadIdentity();
         GlStateManager.translate(0.0F, 0.0F, -2000.0F);
         GL11.glLineWidth(1.0F);
         GlStateManager.disableTexture2D();
         Tessellator var3 = Tessellator.getInstance();
         WorldRenderer var4 = var3.getWorldRenderer();
         var4.begin(1, DefaultVertexFormats.POSITION_COLOR);

         for (int var5 = 0; var5 < recoveredField948.length; var5++) {
            int var6 = (var5 - numRecordedFrameTimes & recoveredField948.length - 1) * 100 / recoveredField948.length;
            var6 += 155;
            float var7 = mc.displayHeight;
            long var8 = 0L;
            if (gcs[var5]) {
               renderTime(var5, recoveredField948[var5], var6, var6 / 2, 0, var7, var4);
            } else {
               renderTime(var5, recoveredField948[var5], var6, var6, var6, var7, var4);
               var7 -= (float)renderTime(var5, recoveredField950[var5], var6 / 2, var6 / 2, var6 / 2, var7, var4);
               var7 -= (float)renderTime(var5, recoveredField949[var5], 0, var6, 0, var7, var4);
               var7 -= (float)renderTime(var5, recoveredField960[var5], var6, var6, 0, var7, var4);
               var7 -= (float)renderTime(var5, recoveredField956[var5], var6, 0, 0, var7, var4);
               var7 -= (float)renderTime(var5, recoveredField954[var5], var6, 0, var6, var7, var4);
               var7 -= (float)renderTime(var5, recoveredField951[var5], 0, 0, var6, var7, var4);
               float var10 = var7 - (float)renderTime(var5, recoveredField959[var5], 0, var6, var6, var7, var4);
            }
         }

         renderTimeDivider(0, recoveredField948.length, 33333333L, 196, 196, 196, mc.displayHeight, var4);
         renderTimeDivider(0, recoveredField948.length, 16666666L, 196, 196, 196, mc.displayHeight, var4);
         var3.draw();
         GlStateManager.enableTexture2D();
         int var15 = mc.displayHeight - 80;
         int var17 = mc.displayHeight - 160;
         mc.fontRendererObj.drawString("30", 2, var17 + 1, -8947849);
         mc.fontRendererObj.drawString("30", 1, var17, -3881788);
         mc.fontRendererObj.drawString("60", 2, var15 + 1, -8947849);
         mc.fontRendererObj.drawString("60", 1, var15, -3881788);
         GlStateManager.matrixMode(5889);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.enableTexture2D();
         float var24 = 1.0F - (float)((System.currentTimeMillis() - MemoryMonitor.getStartTimeMs()) / 1000.0);
         var24 = Config.limit(var24, 0.0F, 1.0F);
         int var26 = (int)(170.0F + var24 * 85.0F);
         int var9 = (int)(100.0F + var24 * 55.0F);
         int var27 = (int)(10.0F + var24 * 10.0F);
         int var11 = var26 << 16 | var9 << 8 | var27;
         int var12 = 512 / var0.getScaleFactor() + 2;
         int var13 = mc.displayHeight / var0.getScaleFactor() - 8;
         GuiIngame var14 = mc.ingameGUI;
         GuiIngame.a(var12 - 1, var13 - 1, var12 + 50, var13 + 10, -1605349296);
         mc.fontRendererObj.drawString(" " + MemoryMonitor.getAllocationRateMb() + " MB/s", var12, var13, var11);
         recoveredField955 = System.nanoTime() - var1;
      }
   }

   public static long renderTime(int var0, long var1, int var3, int var4, int var5, float var6, WorldRenderer var7) {
      long var8 = var1 / 200000L;
      if (var8 < 3L) {
         return 0L;
      } else {
         var7.pos(var0 + 0.5F, var6 - (float)var8 + 0.5F, 0.0).color(var3, var4, var5, 255).endVertex();
         var7.pos(var0 + 0.5F, var6 + 0.5F, 0.0).color(var3, var4, var5, 255).endVertex();
         return var8;
      }
   }

   public static boolean isActive() {
      return active;
   }

   public static long renderTimeDivider(int var0, int var1, long var2, int var4, int var5, int var6, float var7, WorldRenderer var8) {
      long var9 = var2 / 200000L;
      if (var9 < 3L) {
         return 0L;
      } else {
         var8.pos(var0 + 0.5F, var7 - (float)var9 + 0.5F, 0.0).color(var4, var5, var6, 255).endVertex();
         var8.pos(var1 + 0.5F, var7 - (float)var9 + 0.5F, 0.0).color(var4, var5, var6, 255).endVertex();
         return var9;
      }
   }

   public static void updateLagometer() {
      if (mc == null) {
         mc = Minecraft.getMinecraft();
         gameSettings = mc.gameSettings;
         profiler = mc.mcProfiler;
      }

      if (gameSettings.recoveredField2681 && (gameSettings.ofLagometer || gameSettings.showLagometer)) {
         active = true;
         long var0 = System.nanoTime();
         if (recoveredField957 == -1L) {
            recoveredField957 = var0;
         } else {
            int var2 = numRecordedFrameTimes & recoveredField948.length - 1;
            numRecordedFrameTimes++;
            boolean var3 = MemoryMonitor.isGcEvent();
            recoveredField948[var2] = var0 - recoveredField957 - recoveredField955;
            recoveredField959[var2] = recoveredField947.recoveredField2793;
            recoveredField951[var2] = recoveredField958.recoveredField2793;
            recoveredField954[var2] = recoveredField945.recoveredField2793;
            recoveredField956[var2] = recoveredField952.recoveredField2793;
            recoveredField960[var2] = recoveredField953.recoveredField2793;
            recoveredField949[var2] = recoveredField944.recoveredField2793;
            recoveredField950[var2] = recoveredField946.recoveredField2793;
            gcs[var2] = var3;
            Lagometer$TimerNano.method_30329(recoveredField947);
            Lagometer$TimerNano.method_30329(recoveredField958);
            Lagometer$TimerNano.method_30329(recoveredField953);
            Lagometer$TimerNano.method_30329(recoveredField952);
            Lagometer$TimerNano.method_30329(recoveredField945);
            Lagometer$TimerNano.method_30329(recoveredField944);
            Lagometer$TimerNano.method_30329(recoveredField946);
            recoveredField957 = System.nanoTime();
         }
      } else {
         active = false;
         recoveredField957 = -1L;
      }
   }
}
