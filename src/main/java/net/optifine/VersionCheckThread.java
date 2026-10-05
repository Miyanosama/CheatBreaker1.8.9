package net.optifine;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.src.Config;

public class VersionCheckThread extends Thread {
   public VersionCheckThread() {
      super("VersionCheck");
   }

   @Override
   public void run() {
      HttpURLConnection var1 = null;

      try {
         Config.dbg("Checking for new version");
         URL var2 = new URL("http://optifine.net/version/1.8.9/HD_U.txt");
         var1 = (HttpURLConnection)var2.openConnection();
         if (Config.getGameSettings().snooperEnabled) {
            var1.setRequestProperty("OF-MC-Version", "1.8.9");
            var1.setRequestProperty("OF-MC-Brand", "" + ClientBrandRetriever.getClientModName());
            var1.setRequestProperty("OF-Edition", "HD_U");
            var1.setRequestProperty("OF-Release", "M6_pre2");
            var1.setRequestProperty("OF-Java-Version", "" + System.getProperty("java.version"));
            var1.setRequestProperty("OF-CpuCount", "" + Config.getAvailableProcessors());
            var1.setRequestProperty("OF-OpenGL-Version", "" + Config.openGlVersion);
            var1.setRequestProperty("OF-OpenGL-Vendor", "" + Config.openGlVendor);
         }

         var1.setDoInput(true);
         var1.setDoOutput(false);
         var1.connect();

         try {
            InputStream var3 = var1.getInputStream();
            String var4 = Config.readInputStream(var3);
            var3.close();
            String[] var5 = Config.tokenize(var4, "\n\r");
            if (var5.length < 1) {
               return;
            }

            String var6 = var5[0].trim();
            Config.dbg("Version found: " + var6);
            if (Config.compareRelease(var6, "M6_pre2") > 0) {
               Config.setNewRelease(var6);
               return;
            }
         } finally {
            if (var1 != null) {
               var1.disconnect();
            }
         }
      } catch (Exception var11) {
         Config.dbg(var11.getClass().getName() + ": " + var11.getMessage());
      }
   }
}
