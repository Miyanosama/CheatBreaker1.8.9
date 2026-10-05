package net.optifine.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Config;

public class HttpUtils {
   public static final String recoveredField2504 = "http://optifine.net";
   public static final String recoveredField2505 = "http://s.optifine.net";
   public static String playerItemsUrl = null;

   public static String post(String var0, Map var1, byte[] var2) throws java.io.IOException {
      HttpURLConnection var3 = null;

      String var4;
      try {
         URL var5 = new URL(var0);
         var3 = (HttpURLConnection)var5.openConnection(Minecraft.getMinecraft().getProxy());
         var3.setRequestMethod("POST");
         if (var1 != null) {
            for (Object var7 : var1.keySet()) {
               String var8 = (String)var7;
               String var9 = "" + var1.get(var8);
               var3.setRequestProperty(var8, var9);
            }
         }

         var3.setRequestProperty("Content-Type", "text/plain");
         var3.setRequestProperty("Content-Length", "" + var2.length);
         var3.setRequestProperty("Content-Language", "en-US");
         var3.setUseCaches(false);
         var3.setDoInput(true);
         var3.setDoOutput(true);
         OutputStream var15 = var3.getOutputStream();
         var15.write(var2);
         var15.flush();
         var15.close();
         InputStream var16 = var3.getInputStream();
         InputStreamReader var17 = new InputStreamReader(var16, "ASCII");
         BufferedReader var18 = new BufferedReader(var17);
         StringBuffer var10 = new StringBuffer();

         String var11;
         while ((var11 = var18.readLine()) != null) {
            var10.append(var11);
            var10.append('\r');
         }

         var18.close();
         var4 = var10.toString();
      } finally {
         if (var3 != null) {
            var3.disconnect();
         }
      }

      return var4;
   }

   public static synchronized String getPlayerItemsUrl() {
      if (playerItemsUrl == null) {
         try {
            boolean var0 = Config.parseBoolean(System.getProperty("player.models.local"), false);
            if (var0) {
               File var1 = Minecraft.getMinecraft().mcDataDir;
               File var2 = new File(var1, "playermodels");
               playerItemsUrl = var2.toURI().toURL().toExternalForm();
            }
         } catch (Exception var3) {
            Config.warn("" + var3.getClass().getName() + ": " + var3.getMessage());
         }

         if (playerItemsUrl == null) {
            playerItemsUrl = "http://s.optifine.net";
         }
      }

      return playerItemsUrl;
   }

   public static byte[] get(String var0) throws java.io.IOException {
      HttpURLConnection var1 = null;

      byte[] var2;
      try {
         URL var3 = new URL(var0);
         var1 = (HttpURLConnection)var3.openConnection(Minecraft.getMinecraft().getProxy());
         var1.setDoInput(true);
         var1.setDoOutput(false);
         var1.connect();
         if (var1.getResponseCode() / 100 != 2) {
            if (var1.getErrorStream() != null) {
               Config.readAll(var1.getErrorStream());
            }

            throw new IOException("HTTP response: " + var1.getResponseCode());
         }

         InputStream var4 = var1.getInputStream();
         byte[] var5 = new byte[var1.getContentLength()];
         int var6 = 0;

         do {
            int var7 = var4.read(var5, var6, var5.length - var6);
            if (var7 < 0) {
               throw new IOException("Input stream closed: " + var0);
            }

            var6 += var7;
         } while (var6 < var5.length);

         var2 = var5;
      } finally {
         if (var1 != null) {
            var1.disconnect();
         }
      }

      return var2;
   }
}
