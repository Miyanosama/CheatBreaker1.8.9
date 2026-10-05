package com.cheatbreaker.client.util;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.optifine.http.FileUploadThread;
import net.optifine.http.IFileUploadListener;

public class ClientDiagnosticReport {
   public static void method_20169() {
      Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Uploaded debug info."));
      if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField594.getValue()) {
         Minecraft.getMinecraft().entityRenderer.stopUseShader();
      }

      Minecraft.getMinecraft().displayGuiScreen(null);
   }

   public static String method_20170(String var0) {
      Runtime var1 = Runtime.getRuntime();
      long var2 = var1.maxMemory();
      long var4 = var1.totalMemory();
      long var6 = var1.freeMemory();
      long var8 = var2 / 1024L / 1024L;
      long var10 = var4 / 1024L / 1024L;
      long var12 = var6 / 1024L / 1024L;
      StringBuilder var14 = new StringBuilder();
      var14.append("OptiFine Version: ").append(Config.getVersion()).append("\n\n");
      var14.append("CheatBreaker client Version: ")
         .append(CheatBreaker.getInstance().method_19749())
         .append("/")
         .append(CheatBreaker.getInstance().method_19798())
         .append("\n\n");
      var14.append("= [ Description ] =\n");
      var14.append(var0).append("\n\n");
      var14.append("= [ User Info ] =\n");
      if (Minecraft.getMinecraft().getSession() != null) {
         try {
            var14.append("User: ").append(Minecraft.getMinecraft().getSession().getUsername()).append("\n");
            var14.append("UUID: ").append(Minecraft.getMinecraft().getSession().getPlayerID()).append("\n\n");
         } catch (Exception var21) {
            var21.printStackTrace();
         }
      }

      var14.append("= [ System Info ] =\n");
      var14.append("OpenGlVersion: ").append(Config.openGlVersion).append("\n");
      var14.append("OpenGlRenderer: ").append(Config.openGlRenderer).append("\n");
      var14.append("OpenGlVendor: ").append(Config.openGlVendor).append("\n");
      var14.append("CpuCount: ").append(Config.getAvailableProcessors()).append("\n");
      var14.append("OS: ")
         .append(System.getProperty("os.name"))
         .append(" (")
         .append(System.getProperty("os.arch"))
         .append(") version ")
         .append(System.getProperty("os.version"))
         .append("\n");
      var14.append("Java: ").append(System.getProperty("java.version")).append(", ").append(System.getProperty("java.vendor")).append("\n");
      var14.append("Java VM: ")
         .append(System.getProperty("java.vm.name"))
         .append(" (")
         .append(System.getProperty("java.vm.info"))
         .append("), ")
         .append(System.getProperty("java.vm.vendor"))
         .append("\n");
      var14.append("Memory: ")
         .append(var6)
         .append(" bytes (")
         .append(var12)
         .append(" MB) / ")
         .append(var4)
         .append(" bytes (")
         .append(var10)
         .append(" MB) up to ")
         .append(var2)
         .append(" bytes (")
         .append(var8)
         .append(" MB)")
         .append("\n");
      var14.append("JVM Flags: ").append(method_20172()).append("\n\n");
      var14.append("= [ Module Settings ] =").append("\n");

      for (AbstractModule var16 : CheatBreaker.getInstance().getModuleManager().recoveredField1706) {
         StringBuilder var17 = new StringBuilder();

         for (Setting var19 : var16.getSettingsList()) {
            if (!var19.method_08911().equals("label")) {
               var17.append(var19.method_08911())
                  .append("=")
                  .append(var19.getValue())
                  .append(var16.getSettingsList().indexOf(var19) == var16.getSettingsList().size() - 1 ? "" : ", ");
            }
         }

         var14.append(var16.getName()).append(": [").append((CharSequence)var17).append("]\n");
      }

      var14.append("\n= [ Global Settings ] =").append("\n");
      StringBuilder var24 = new StringBuilder();
      List var25 = CheatBreaker.getInstance().getGlobalSettings().recoveredField571;

      for (Setting var28 : (Iterable<Setting>)(Iterable<?>)(var25)) {
         if (!var28.method_08911().equals("label")) {
            var24.append(var28.method_08911()).append("=").append(var28.getValue()).append(var25.indexOf(var28) == var25.size() - 1 ? "" : ", ");
         }
      }

      var14.append("Global: [").append((CharSequence)var24).append("]\n\n");
      GameSettings var27 = Minecraft.getMinecraft().gameSettings;
      var14.append("= [ Options.txt ] =\n");

      try {
         BufferedReader var29 = new BufferedReader(new FileReader(var27.optionsFile));

         String var31;
         while ((var31 = var29.readLine()) != null) {
            if (!var31.startsWith("stream") && !var31.startsWith("key_") && !var31.startsWith("soundCategory")) {
               var14.append(var31).append("\n");
            }
         }
      } catch (IOException var23) {
         var23.printStackTrace();
      }

      var14.append("\n= [ OptionsOF.txt ] =\n");

      try {
         BufferedReader var30 = new BufferedReader(new FileReader(var27.optionsFileOF));

         String var32;
         while ((var32 = var30.readLine()) != null) {
            if (!var32.startsWith("ofAnimated")) {
               var14.append(var32).append("\n");
            }
         }
      } catch (IOException var22) {
         var22.printStackTrace();
      }

      return var14.toString();
   }

   public static String method_20172() {
      RuntimeMXBean var0 = ManagementFactory.getRuntimeMXBean();
      List var1 = var0.getInputArguments();
      int var2 = 0;
      StringBuilder var3 = new StringBuilder();

      for (String var5 : (Iterable<String>)(Iterable<?>)(var1)) {
         if (var5.startsWith("-X")) {
            if (var2++ > 0) {
               var3.append(" ");
            }

            var3.append(var5);
         }
      }

      return String.format("%d total; %s", var2, var3);
   }

   public static void method_20173(String var0) {
      try {
         String var1 = CheatBreaker.getInstance().getGlobalSettings().recoveredField597;
         String var2 = method_20170(var0);
         byte[] var3 = var2.getBytes(StandardCharsets.US_ASCII);
         IFileUploadListener var4 = (var0x, var1x, var2x) -> method_20169();
         HashMap var5 = new HashMap();
         var5.put("OF-Version", Config.getVersion());
         FileUploadThread var6 = new FileUploadThread(var1, var5, var3, var4);
         var6.setPriority(10);
         var6.start();
         Thread.sleep(1000L);
      } catch (InterruptedException var7) {
         var7.printStackTrace();
      }
   }
}
