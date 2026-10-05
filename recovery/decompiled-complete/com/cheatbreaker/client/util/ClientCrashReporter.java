package com.cheatbreaker.client.util;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$SeekAheadOptimize;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX$Factory;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.optifine.http.HttpUtils;
import org.apache.log4j.helpers.CountingQuietWriter;

public class ClientCrashReporter {
   public HttpPostBodyUtil$SeekAheadOptimize field_0001;
   public EntityCrit2FX$Factory field_0002;
   public CountingQuietWriter field_0000;

   public static void method_09063(StackTraceElement[] var0) {
      try {
         GameSettings var1 = Config.getGameSettings();
         String var2 = CheatBreaker.getInstance().getGlobalSettings().field_0120;
         String var3 = method_09064(var0);
         byte[] var4 = var3.getBytes(StandardCharsets.US_ASCII);
         HashMap var5 = new HashMap();
         System.out.println("[Crash Handler] Sending Post");
         String var6 = HttpUtils.post(var2, var5, var4);
         System.out.println("[Crash Handler] Sent Post");
         if (var6 != null) {
            StringSelection var7 = new StringSelection(var6);
            Clipboard var8 = Toolkit.getDefaultToolkit().getSystemClipboard();
            var8.setContents(var7, null);
            new Thread(
                  () -> JOptionPane.showMessageDialog(
                     null,
                     "Your client has crashed. \n\nPlease use the following code (also copied to your clipboard) when submitting a bug report: " + var6,
                     "Something went wrong (Closing in 5 seconds)",
                     2
                  )
               )
               .start();
         }

         Thread.sleep(205674352L & -8269804335657312270L);
      } catch (Exception var9) {
         Config.dbg(var9.getClass().getName() + ": " + var9.getMessage());
      }
   }

   public static void method_09060(CrashReportCategory var0) {
      String var1 = "[";

      for (Object var3 : CheatBreaker.getInstance().getGlobalSettings().field_0013) {
         if (!((Setting)var3).method_08911().equals("label")) {
            var1 = var1 + ((Setting)var3).method_08911() + "=" + ((Setting)var3).getValue() + ", ";
         }
      }

      var0.addCrashSection("Global Settings", var1 + "]");

      for (Object var8 : CheatBreaker.getInstance().getModuleManager().field_0008) {
         String var4 = "[";

         for (Setting var6 : ((AbstractModule)var8).getSettingsList()) {
            if (!var6.method_08911().equals("label")) {
               var4 = var4
                  + var6.method_08911()
                  + "="
                  + var6.getValue()
                  + (((AbstractModule)var8).getSettingsList().indexOf(var6) == ((AbstractModule)var8).getSettingsList().size() - 1 ? "" : ", ");
            }
         }

         var0.addCrashSection(((AbstractModule)var8).getName() + " options", var4 + "]");
      }

      var0.addCrashSection("OpenGlVersion", "" + Config.openGlVersion);
      var0.addCrashSection("OpenGlRenderer", "" + Config.openGlRenderer);
      var0.addCrashSection("OpenGlVendor", "" + Config.openGlVendor);
      var0.addCrashSection("CpuCount", "" + Config.getAvailableProcessors());
   }

   public static String method_09062(CrashReport var0) {
      return "Unknown";
   }

   public static String method_09064(StackTraceElement[] var0) {
      StringBuilder var2 = new StringBuilder();
      var2.append("CheatBreaker client Version: ")
         .append(CheatBreaker.getInstance().method_19749())
         .append("/")
         .append(CheatBreaker.getInstance().method_19798())
         .append("\n\n");
      var2.append("\n");
      var2.append("\n\n");
      var2.append("= [ StackTrace ] =\n");

      for (StackTraceElement var6 : var0) {
         var2.append("\n").append(var6);
      }

      var2.append("\n\n");
      GameSettings var10 = Minecraft.getMinecraft().gameSettings;
      if (Minecraft.getMinecraft().getSession() != null) {
         var2.append("= [ User Info ] =\n");
         var2.append("Username: ").append(Minecraft.getMinecraft().getSession().getUsername()).append("\n");
         var2.append("UUID: ").append(Minecraft.getMinecraft().getSession().getPlayerID()).append("\n\n");
      }

      var2.append("= [ Options.txt ] =\n");

      try {
         BufferedReader var1 = new BufferedReader(new FileReader(var10.optionsFile));

         String var11;
         while ((var11 = var1.readLine()) != null) {
            if (!var11.startsWith("stream") && !var11.startsWith("key_") && !var11.startsWith("soundCategory")) {
               var2.append(var11).append("\n");
            }
         }
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      var2.append("\n= [ OptionsOF.txt ] =\n");

      try {
         BufferedReader var9 = new BufferedReader(new FileReader(var10.optionsFileOF));

         String var12;
         while ((var12 = var9.readLine()) != null) {
            if (!var12.startsWith("ofAnimated")) {
               var2.append(var12 + "\n");
            }
         }
      } catch (IOException var7) {
         var7.printStackTrace();
      }

      return var2.toString();
   }
}
