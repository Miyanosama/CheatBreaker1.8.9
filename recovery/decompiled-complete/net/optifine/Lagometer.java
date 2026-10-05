package net.optifine;

import io.netty.handler.codec.string.StringDecoder;
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
import recovered.unidentified.UnidentifiedClass1769;
import recovered.unidentified.UnidentifiedClass5108;

public class Lagometer {
   public static UnidentifiedClass5108 field_0012 = new UnidentifiedClass5108();
   public static int numRecordedFrameTimes = 0;
   public StringDecoder field_0011;
   public VersionCheckThread field_0020;
   public static UnidentifiedClass5108 field_0006 = new UnidentifiedClass5108();
   public static UnidentifiedClass5108 field_0007 = new UnidentifiedClass5108();
   public static Minecraft mc;
   public static UnidentifiedClass5108 field_0017 = new UnidentifiedClass5108();
   public static Profiler profiler;
   public UnidentifiedClass1769 field_0025;
   public static long[] field_0005 = new long[512];
   public static long[] field_0013 = new long[512];
   public static GameSettings gameSettings;
   public static long[] field_0010 = new long[512];
   public static long[] field_0018 = new long[512];
   public static UnidentifiedClass5108 field_0022 = new UnidentifiedClass5108();
   public static UnidentifiedClass5108 field_0003 = new UnidentifiedClass5108();
   public static boolean active = false;
   public static boolean[] gcs = new boolean[512];
   public static long[] field_0021 = new long[512];
   public static long field_0002 = 5724272535051452416L & 279060486L;
   public static long[] field_0001 = new long[512];
   public static long field_0004 = -1L & -1L;
   public static UnidentifiedClass5108 field_0000 = new UnidentifiedClass5108();
   public static long[] field_0019 = new long[512];
   public static long[] field_0015 = new long[512];

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

         for (int var5 = 0; var5 < field_0005.length; var5++) {
            int var6 = (var5 - numRecordedFrameTimes & field_0005.length - 1) * 100 / field_0005.length;
            var6 += 155;
            float var7 = mc.displayHeight;
            long var8 = 6550217673525952560L & -6550217674054318965L;
            if (gcs[var5]) {
               renderTime(var5, field_0005[var5], var6, var6 / 2, 0, var7, var4);
            } else {
               renderTime(var5, field_0005[var5], var6, var6, var6, var7, var4);
               var7 -= (float)renderTime(var5, field_0010[var5], var6 / 2, var6 / 2, var6 / 2, var7, var4);
               var7 -= (float)renderTime(var5, field_0013[var5], 0, var6, 0, var7, var4);
               var7 -= (float)renderTime(var5, field_0015[var5], var6, var6, 0, var7, var4);
               var7 -= (float)renderTime(var5, field_0001[var5], var6, 0, 0, var7, var4);
               var7 -= (float)renderTime(var5, field_0021[var5], var6, 0, var6, var7, var4);
               var7 -= (float)renderTime(var5, field_0018[var5], 0, 0, var6, var7, var4);
               float var10 = var7 - (float)renderTime(var5, field_0019[var5], 0, var6, var6, var7, var4);
            }
         }

         renderTimeDivider(0, field_0005.length, 33419351L & -8462201102794972459L, 196, 196, 196, mc.displayHeight, var4);
         renderTimeDivider(0, field_0005.length, 285105198L & 117395898L, 196, 196, 196, mc.displayHeight, var4);
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
         field_0002 = System.nanoTime() - var1;
      }
   }

   public static long renderTime(int var0, long var1, int var3, int var4, int var5, float var6, WorldRenderer var7) {
      long var8 = var1 / (142851392L & -3490801875573076544L);
      if (var8 < (704663563L & -1619059546173888829L)) {
         return 549453832L & 1109394388L;
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
      long var9 = var2 / (554151874L & 1077091676L);
      if (var9 < (-5014556024917122493L & 1074449671L)) {
         return 477169200L & 42745856L;
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

      if (gameSettings.field_0191 && (gameSettings.ofLagometer || gameSettings.showLagometer)) {
         active = true;
         long var0 = System.nanoTime();
         if (field_0004 == (-1L & -1L)) {
            field_0004 = var0;
         } else {
            int var2 = numRecordedFrameTimes & field_0005.length - 1;
            numRecordedFrameTimes++;
            boolean var3 = MemoryMonitor.isGcEvent();
            field_0005[var2] = var0 - field_0004 - field_0002;
            field_0019[var2] = field_0017.field_0001;
            field_0018[var2] = field_0000.field_0001;
            field_0021[var2] = field_0006.field_0001;
            field_0001[var2] = field_0022.field_0001;
            field_0015[var2] = field_0003.field_0001;
            field_0013[var2] = field_0012.field_0001;
            field_0010[var2] = field_0007.field_0001;
            gcs[var2] = var3;
            UnidentifiedClass5108.method_30329(field_0017);
            UnidentifiedClass5108.method_30329(field_0000);
            UnidentifiedClass5108.method_30329(field_0003);
            UnidentifiedClass5108.method_30329(field_0022);
            UnidentifiedClass5108.method_30329(field_0006);
            UnidentifiedClass5108.method_30329(field_0012);
            UnidentifiedClass5108.method_30329(field_0007);
            field_0004 = System.nanoTime();
         }
      } else {
         active = false;
         field_0004 = -1L & -1L;
      }
   }
}
