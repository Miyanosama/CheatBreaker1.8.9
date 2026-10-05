package net.minecraft.crash;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.websocket.client.WSPacketClientCrashReport;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import net.minecraft.block.BlockSilverfish$EnumType$5;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.particle.EntitySplashFX$Factory;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.minecraft.util.ReportedException;
import net.optifine.CrashReporter;
import net.optifine.http.HttpUtils;
import net.optifine.reflect.Reflector;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrashReport {
   public String description;
   public GuiDownloadTerrain field_0009;
   public BlockSilverfish$EnumType$5 field_0004;
   public CrashReportCategory theReportCategory = new CrashReportCategory(this, "System Details");
   public List<CrashReportCategory> crashReportSections = Lists.newArrayList();
   public boolean reported;
   public Throwable cause;
   public static Logger logger = LogManager.getLogger();
   public EntitySplashFX$Factory field_0003;
   public StackTraceElement[] stacktrace;
   public boolean firstCategoryInCrashReport = true;
   public File crashReportFile;

   public CrashReportCategory makeCategory(String var1) {
      return this.makeCategoryDepth(var1, 1);
   }

   public Throwable getCrashCause() {
      return this.cause;
   }

   public void getSectionsInStringBuilder(StringBuilder var1) {
      if ((this.stacktrace == null || this.stacktrace.length <= 0) && this.crashReportSections.size() > 0) {
         this.stacktrace = (StackTraceElement[])ArrayUtils.subarray(this.crashReportSections.get(0).getStackTrace(), 0, 1);
      }

      if (this.stacktrace != null && this.stacktrace.length > 0) {
         var1.append("-- Head --\n");
         var1.append("Stacktrace:\n");

         for (StackTraceElement var5 : this.stacktrace) {
            var1.append("\t").append("at ").append(var5.toString());
            var1.append("\n");
         }

         var1.append("\n");
      }

      for (CrashReportCategory var7 : this.crashReportSections) {
         var7.appendToStringBuilder(var1);
         var1.append("\n\n");
      }

      this.theReportCategory.appendToStringBuilder(var1);
   }

   public boolean saveToFile(File var1) {
      if (this.crashReportFile != null) {
         return false;
      } else {
         if (var1.getParentFile() != null) {
            var1.getParentFile().mkdirs();
         }

         try {
            FileWriter var2 = new FileWriter(var1);
            var2.write(this.getCompleteReport());
            var2.close();
            this.crashReportFile = var1;
            return true;
         } catch (Throwable var3) {
            logger.error("Could not save crash report to " + var1, var3);
            return false;
         }
      }
   }

   public CrashReportCategory getCategory() {
      return this.theReportCategory;
   }

   public static void method_26097(String var0, String var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("OptiFine Version: ").append(Config.getVersion()).append("\n\n");
      var2.append("CheatBreaker client Version: ")
         .append(CheatBreaker.getInstance().method_19749() + "/" + CheatBreaker.getInstance().method_19798())
         .append("\n\n");
      var2.append("= [ User Info ] =\n");
      if (Minecraft.getMinecraft().getSession() != null) {
         try {
            var2.append("User: " + Minecraft.getMinecraft().getSession().getUsername() + "\n");
            var2.append("UUID: " + Minecraft.getMinecraft().getSession().getPlayerID() + "\n\n");
         } catch (Exception var25) {
            var25.printStackTrace();
         }
      }

      var2.append("= [ Crash Info ] =\n");
      var2.append("Crash ID: " + var0);
      var2.append("\nStackTrace: " + var1 + "\n\n");
      Runtime var5 = Runtime.getRuntime();
      long var6 = var5.maxMemory();
      long var8 = var5.totalMemory();
      long var10 = var5.freeMemory();
      long var12 = var6 / (419505170L & -8417646091779306496L) / (6695106015861408769L & -6695106017680173636L);
      long var14 = var8 / (-5124207788015139742L & 1149383957L) / (72090669L & -6161585103978757120L);
      long var16 = var10 / (336201474L & 1624319029L) / (1562286467219457200L & -1562286468359860990L);
      var2.append("= [ System Info ] =\n");
      var2.append("OpenGlVersion: " + Config.openGlVersion + "\n");
      var2.append("OpenGlRenderer: " + Config.openGlRenderer + "\n");
      var2.append("OpenGlVendor: " + Config.openGlVendor + "\n");
      var2.append("CpuCount: " + Config.getAvailableProcessors() + "\n");
      var2.append("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version") + "\n");
      var2.append("Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor") + "\n");
      var2.append(
         "Java VM: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor") + "\n"
      );
      var2.append("Memory: " + var10 + " bytes (" + var16 + " MB) / " + var8 + " bytes (" + var14 + " MB) up to " + var6 + " bytes (" + var12 + " MB)\n");
      var2.append("JVM Flags: " + method_26091() + "\n\n");
      var2.append("= [ Module Settings ] =").append("\n");

      for (AbstractModule var19 : CheatBreaker.getInstance().getModuleManager().field_0008) {
         StringBuilder var20 = new StringBuilder();

         for (Setting var22 : var19.getSettingsList()) {
            if (!var22.method_08911().equals("label")) {
               var20.append(var22.method_08911())
                  .append("=")
                  .append(var22.getValue())
                  .append(var19.getSettingsList().indexOf(var22) == var19.getSettingsList().size() - 1 ? "" : ", ");
            }
         }

         var2.append(var19.getName() + ": [" + var20.toString() + "]\n");
      }

      var2.append("\n= [ Global Settings ] =").append("\n");
      String var30 = "";
      List var31 = CheatBreaker.getInstance().getGlobalSettings().field_0013;

      for (Setting var34 : var31) {
         if (!var34.method_08911().equals("label")) {
            var30 = var30 + var34.method_08911() + "=" + var34.getValue() + (var31.indexOf(var34) == var31.size() - 1 ? "" : ", ");
         }
      }

      var2.append("Global: [" + var30 + "]\n\n");
      GameSettings var33 = Minecraft.getMinecraft().gameSettings;
      var2.append("= [ Options.txt ] =\n");

      try {
         BufferedReader var3 = new BufferedReader(new FileReader(var33.optionsFile));

         String var4;
         while ((var4 = var3.readLine()) != null) {
            if (!var4.startsWith("stream") && !var4.startsWith("key_") && !var4.startsWith("soundCategory")) {
               var2.append(var4 + "\n");
            }
         }
      } catch (IOException var27) {
         var27.printStackTrace();
      }

      var2.append("\n= [ OptionsOF.txt ] =\n");

      try {
         BufferedReader var28 = new BufferedReader(new FileReader(var33.optionsFileOF));

         String var29;
         while ((var29 = var28.readLine()) != null) {
            if (!var29.startsWith("ofAnimated")) {
               var2.append(var29 + "\n");
            }
         }
      } catch (IOException var26) {
         var26.printStackTrace();
      }

      byte[] var35 = null;

      try {
         var35 = var2.toString().getBytes("ASCII");
      } catch (Exception var24) {
      }

      ImmutableMap var36 = ImmutableMap.builder()
         .put("branch", CheatBreaker.getInstance().method_19798())
         .put("buildType", CheatBreaker.getInstance().method_19784())
         .put("crashID", var0)
         .put("version", "1.8.9")
         .put("gitCommit", CheatBreaker.getInstance().method_19754())
         .put("osInfo", System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"))
         .put(
            "memoryInfo", "Memory: " + var10 + " bytes (" + var16 + " MB) / " + var8 + " bytes (" + var14 + " MB) up to " + var6 + " bytes (" + var12 + " MB)"
         )
         .put("username", Minecraft.getMinecraft().getSession().getUsername())
         .put("uuid", Minecraft.getMinecraft().getSession().getPlayerID())
         .build();
      HttpUtils.post(CheatBreaker.getInstance().getGlobalSettings().field_0120, var36, var35);
      if (CheatBreaker.getInstance().getAssetsWebSocket().isOpen()) {
         WSPacketClientCrashReport var23 = new WSPacketClientCrashReport(
            var0,
            CheatBreaker.getInstance().method_19749(),
            System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"),
            "Memory: " + var10 + " bytes (" + var16 + " MB) / " + var8 + " bytes (" + var14 + " MB) up to " + var6 + " bytes (" + var12 + " MB)",
            var2.toString()
         );
         CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(var23);
      }
   }

   public static String getWittyComment() {
      String[] var0 = new String[]{
         "Who set us up the TNT?",
         "Everything's going to plan. No, really, that was supposed to happen.",
         "Uh... Did I do that?",
         "Oops.",
         "Why did you do that?",
         "I feel sad now :(",
         "My bad.",
         "I'm sorry, Dave.",
         "I let you down. Sorry :(",
         "On the bright side, I bought you a teddy bear!",
         "Daisy, daisy...",
         "Oh - I know what I did wrong!",
         "Hey, that tickles! Hehehe!",
         "I blame Dinnerbone.",
         "You should try our sister game, Minceraft!",
         "Don't be sad. I'll do better next time, I promise!",
         "Don't be sad, have a hug! <3",
         "I just don't know what went wrong :(",
         "Shall we play a game?",
         "Quite honestly, I wouldn't worry myself about that.",
         "I bet Cylons wouldn't have this problem.",
         "Sorry :(",
         "Surprise! Haha. Well, this is awkward.",
         "Would you like a cupcake?",
         "Hi. I'm Minecraft, and I'm a crashaholic.",
         "Ooh. Shiny.",
         "This doesn't make any sense!",
         "Why is it breaking :(",
         "Don't do that.",
         "Ouch. That hurt :(",
         "You're mean.",
         "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]",
         "There are four lights!",
         "But it works on my machine."
      };

      try {
         return var0[(int)(System.nanoTime() % var0.length)];
      } catch (Throwable var2) {
         return "Witty comment unavailable :(";
      }
   }

   public CrashReport(String var1, Throwable var2) {
      this.stacktrace = new StackTraceElement[0];
      this.reported = false;
      this.description = var1;
      this.cause = var2;
      this.populateEnvironment();
   }

   public String getDescription() {
      return this.description;
   }

   public void populateEnvironment() {
      this.theReportCategory.addCrashSectionCallable("Minecraft Version", new CrashReport$1(this));
      this.theReportCategory.addCrashSectionCallable("Operating System", new CrashReport$2(this));
      this.theReportCategory.addCrashSectionCallable("Java Version", new CrashReport$3(this));
      this.theReportCategory.addCrashSectionCallable("Java VM Version", new CrashReport$4(this));
      this.theReportCategory.addCrashSectionCallable("Memory", new CrashReport$5(this));
      this.theReportCategory.addCrashSectionCallable("JVM Flags", new CrashReport$6(this));
      this.theReportCategory.addCrashSectionCallable("IntCache", new CrashReport$7(this));
      if (Reflector.FMLCommonHandler_enhanceCrashReport.exists()) {
         Object var1 = Reflector.call(Reflector.FMLCommonHandler_instance);
         Reflector.callString(var1, Reflector.FMLCommonHandler_enhanceCrashReport, this, this.theReportCategory);
      }
   }

   public CrashReportCategory makeCategoryDepth(String var1, int var2) {
      CrashReportCategory var3 = new CrashReportCategory(this, var1);
      if (this.firstCategoryInCrashReport) {
         int var4 = var3.getPrunedStackTrace(var2);
         StackTraceElement[] var5 = this.cause.getStackTrace();
         StackTraceElement var6 = null;
         StackTraceElement var7 = null;
         int var8 = var5.length - var4;
         if (var8 < 0) {
            System.out.println("Negative index in crash report handler (" + var5.length + "/" + var4 + ")");
         }

         if (var5 != null && 0 <= var8 && var8 < var5.length) {
            var6 = var5[var8];
            if (var5.length + 1 - var4 < var5.length) {
               var7 = var5[var5.length + 1 - var4];
            }
         }

         this.firstCategoryInCrashReport = var3.firstTwoElementsOfStackTraceMatch(var6, var7);
         if (var4 > 0 && !this.crashReportSections.isEmpty()) {
            CrashReportCategory var9 = this.crashReportSections.get(this.crashReportSections.size() - 1);
            var9.trimStackTraceEntriesFromBottom(var4);
         } else if (var5 != null && var5.length >= var4 && 0 <= var8 && var8 < var5.length) {
            this.stacktrace = new StackTraceElement[var8];
            System.arraycopy(var5, 0, this.stacktrace, 0, this.stacktrace.length);
         } else {
            this.firstCategoryInCrashReport = false;
         }
      }

      this.crashReportSections.add(var3);
      return var3;
   }

   public static CrashReport makeCrashReport(Throwable var0, String var1) {
      try {
         StringBuilder var3 = new StringBuilder();
         var3.append(var0.getClass().getSimpleName()).append(": ").append(var0.getLocalizedMessage());

         for (StackTraceElement var7 : var0.getStackTrace()) {
            var3.append("\n").append("\t").append("at ").append(var7);
         }

         UUID var9 = UUID.randomUUID();
         StringSelection var10 = new StringSelection("CB-" + var9);
         Clipboard var11 = Toolkit.getDefaultToolkit().getSystemClipboard();
         var11.setContents(var10, null);
         method_26097(var9.toString(), var3.toString());
         if (var0.getClass().getSimpleName().equals("OutOfMemoryError")) {
            new Thread(
                  () -> JOptionPane.showMessageDialog(
                     null,
                     "Your client has ran out of memory.\nYou can increase memory allocation in the launcher. \n\nPlease use the following code (also copied to your clipboard) when submitting a bug report: \n\nCB-"
                        + var9,
                     "Out of Memory",
                     2
                  )
               )
               .start();
         } else {
            new Thread(
                  () -> JOptionPane.showMessageDialog(
                     null,
                     "Your client has crashed. \n\nPlease use the following code (also copied to your clipboard) when submitting a bug report: \n\nCB-" + var9,
                     "Something went wrong",
                     2
                  )
               )
               .start();
         }

         Thread.sleep(-7878597501966086240L & 7878597500349734908L);
      } catch (Exception var8) {
         System.out.println("Something went wrong");
         var8.printStackTrace();
      }

      CrashReport var2;
      if (var0 instanceof ReportedException) {
         var2 = ((ReportedException)var0).getCrashReport();
      } else {
         var2 = new CrashReport(var1, var0);
      }

      return var2;
   }

   public File getFile() {
      return this.crashReportFile;
   }

   public String getCauseStackTraceOrString() {
      StringWriter var1 = null;
      PrintWriter var2 = null;
      Object var3 = this.cause;
      if (var3.getMessage() == null) {
         if (var3 instanceof NullPointerException) {
            var3 = new NullPointerException(this.description);
         } else if (var3 instanceof StackOverflowError) {
            var3 = new StackOverflowError(this.description);
         } else if (var3 instanceof OutOfMemoryError) {
            var3 = new OutOfMemoryError(this.description);
         }

         var3.setStackTrace(this.cause.getStackTrace());
      }

      String var4 = var3.toString();

      try {
         var1 = new StringWriter();
         var2 = new PrintWriter(var1);
         var3.printStackTrace(var2);
         var4 = var1.toString();
      } finally {
         IOUtils.closeQuietly(var1);
         IOUtils.closeQuietly(var2);
      }

      return var4;
   }

   public String getCompleteReport() {
      if (!this.reported) {
         this.reported = true;
         CrashReporter.onCrashReport(this, this.theReportCategory);
      }

      StringBuilder var1 = new StringBuilder();
      var1.append("---- Minecraft Crash Report ----\n");
      Reflector.call(Reflector.BlamingTransformer_onCrash, var1);
      Reflector.call(Reflector.CoreModManager_onCrash, var1);
      var1.append("// ");
      var1.append(getWittyComment());
      var1.append("\n\n");
      var1.append("Time: ");
      var1.append(new SimpleDateFormat().format(new Date()));
      var1.append("\n");
      var1.append("Description: ");
      var1.append(this.description);
      var1.append("\n\n");
      var1.append(this.getCauseStackTraceOrString());
      var1.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");

      for (int var2 = 0; var2 < 87; var2++) {
         var1.append("-");
      }

      var1.append("\n\n");
      this.getSectionsInStringBuilder(var1);
      return var1.toString();
   }

   public static String method_26091() {
      RuntimeMXBean var0 = ManagementFactory.getRuntimeMXBean();
      List var1 = var0.getInputArguments();
      int var2 = 0;
      StringBuilder var3 = new StringBuilder();

      for (String var5 : var1) {
         if (var5.startsWith("-X")) {
            if (var2++ > 0) {
               var3.append(" ");
            }

            var3.append(var5);
         }
      }

      return String.format("%d total; %s", var2, var3.toString());
   }
}
