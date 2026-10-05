package net.optifine;

import io.netty.channel.socket.nio.NioDatagramChannelConfig;
import io.netty.util.Signal;
import java.util.HashMap;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.optifine.http.FileUploadThread;
import net.optifine.shaders.Shaders;

public class CrashReporter {
   public SoundEventAccessorComposite field_0001;
   public NioDatagramChannelConfig field_0002;
   public Signal field_0000;

   public static String makeSummary(CrashReport var0) {
      Throwable var1 = var0.getCrashCause();
      if (var1 == null) {
         return "Unknown";
      } else {
         StackTraceElement[] var2 = var1.getStackTrace();
         String var3 = "unknown";
         if (var2.length > 0) {
            var3 = var2[0].toString().trim();
         }

         return var1.getClass().getName() + ": " + var1.getMessage() + " (" + var0.getDescription() + ") [" + var3 + "]";
      }
   }

   public static void extendCrashReport(CrashReportCategory var0) {
      var0.addCrashSection("OptiFine Version", Config.getVersion());
      var0.addCrashSection("OptiFine Build", Config.getBuild());
      if (Config.getGameSettings() != null) {
         var0.addCrashSection("Render Distance Chunks", "" + Config.getChunkViewDistance());
         var0.addCrashSection("Mipmaps", "" + Config.getMipmapLevels());
         var0.addCrashSection("Anisotropic Filtering", "" + Config.getAnisotropicFilterLevel());
         var0.addCrashSection("Antialiasing", "" + Config.getAntialiasingLevel());
         var0.addCrashSection("Multitexture", "" + Config.isMultiTexture());
      }

      var0.addCrashSection("Shaders", "" + Shaders.getShaderPackName());
      var0.addCrashSection("OpenGlVersion", "" + Config.openGlVersion);
      var0.addCrashSection("OpenGlRenderer", "" + Config.openGlRenderer);
      var0.addCrashSection("OpenGlVendor", "" + Config.openGlVendor);
      var0.addCrashSection("CpuCount", "" + Config.getAvailableProcessors());
   }

   public static void onCrashReport(CrashReport var0, CrashReportCategory var1) {
      try {
         Throwable var2 = var0.getCrashCause();
         if (var2 == null) {
            return;
         }

         if (var2.getClass().getName().contains(".fml.client.SplashProgress")) {
            return;
         }

         if (var2.getClass() == Throwable.class) {
            return;
         }

         extendCrashReport(var1);
         GameSettings var3 = Config.getGameSettings();
         if (var3 == null) {
            return;
         }

         if (!var3.snooperEnabled) {
            return;
         }

         String var4 = "http://optifine.net/crashReport";
         String var5 = makeReport(var0);
         byte[] var6 = var5.getBytes("ASCII");
         CrashReporter$1 var7 = new CrashReporter$1();
         HashMap var8 = new HashMap();
         var8.put("OF-Version", Config.getVersion());
         var8.put("OF-Summary", makeSummary(var0));
         FileUploadThread var9 = new FileUploadThread(var4, var8, var6, var7);
         var9.setPriority(10);
         var9.start();
         Thread.sleep(1753402344L & -2290190082507013144L);
      } catch (Exception var10) {
         Config.dbg(var10.getClass().getName() + ": " + var10.getMessage());
      }
   }

   public static String makeReport(CrashReport var0) {
      StringBuffer var1 = new StringBuffer();
      var1.append("OptiFineVersion: " + Config.getVersion() + "\n");
      var1.append("Summary: " + makeSummary(var0) + "\n");
      var1.append("\n");
      var1.append(var0.getCompleteReport());
      var1.append("\n");
      return var1.toString();
   }
}
