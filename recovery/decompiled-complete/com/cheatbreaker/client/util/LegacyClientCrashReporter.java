package com.cheatbreaker.client.util;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import io.netty.channel.DefaultChannelPipeline;
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
import net.minecraft.client.gui.spectator.BaseSpectatorGroup;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.optifine.http.HttpUtils;

public class LegacyClientCrashReporter {
   public BaseSpectatorGroup field_0001;
   public SimpleBakedModel field_0002;
   public DefaultChannelPipeline field_0000;

   public static void method_01815(CrashReportCategory var0) {
      StringBuilder var1 = new StringBuilder("[");

      for (Object var3 : CheatBreaker.getInstance().getGlobalSettings().field_0013) {
         if (!((Setting)var3).method_08911().equals("label")) {
            var1.append(((Setting)var3).method_08911()).append("=").append(((Setting)var3).getValue()).append(", ");
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

   public static String method_01818(StackTraceElement[] var0) {
      StringBuilder var2 = new StringBuilder();
      var2.append("CheatBreaker Client Version: ")
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

   public static String method_01817(CrashReport var0) {
      return "Unknown";
   }

   public static void method_01819(StackTraceElement[] var0) {
      try {
         String var1 = CheatBreaker.getInstance().getGlobalSettings().field_0120;
         String var2 = method_01818(var0);
         byte[] var3 = var2.getBytes(StandardCharsets.US_ASCII);
         String var4 = HttpUtils.post(var1, new HashMap(), var3);
         System.out.println("[Crash Handler] Sent Post");
         if (var4 != null) {
            StringSelection var5 = new StringSelection(var4);
            Clipboard var6 = Toolkit.getDefaultToolkit().getSystemClipboard();
            var6.setContents(var5, null);
            new Thread(
                  () -> JOptionPane.showMessageDialog(
                     null,
                     "Your client has crashed. \n\nPlease use the following code (also copied to your clipboard) when submitting a bug report: " + var4,
                     "Something went wrong (Closing in 5 seconds)",
                     2
                  )
               )
               .start();
         }

         Thread.sleep(1346508656L & -3812137772821768324L);
      } catch (Exception var7) {
         Config.dbg(var7.getClass().getName() + ": " + var7.getMessage());
      }
   }
}
