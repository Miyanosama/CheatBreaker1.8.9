package net.minecraft.util;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.handler.codec.socks.SocksInitRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.enchantment.EnchantmentUntouching;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpUtil {
   public static ListeningExecutorService field_180193_a = MoreExecutors.listeningDecorator(
      Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Downloader %d").build())
   );
   public SocksInitRequest field_0005;
   public Entity field_0002;
   public EnchantmentUntouching field_0004;
   public static AtomicInteger downloadThreadsStarted = new AtomicInteger(0);
   public static Logger logger = LogManager.getLogger();

   public static String buildPostString(Map<String, Object> var0) {
      StringBuilder var1 = new StringBuilder();

      for (Entry var3 : var0.entrySet()) {
         if (var1.length() > 0) {
            var1.append('&');
         }

         try {
            var1.append(URLEncoder.encode((String)var3.getKey(), "UTF-8"));
         } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
         }

         if (var3.getValue() != null) {
            var1.append('=');

            try {
               var1.append(URLEncoder.encode(var3.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException var5) {
               var5.printStackTrace();
            }
         }
      }

      return var1.toString();
   }

   public static String postMap(URL var0, Map<String, Object> var1, boolean var2) {
      return post(var0, buildPostString(var1), var2);
   }

   public static ListenableFuture<Object> downloadResourcePack(File var0, String var1, Map<String, String> var2, int var3, IProgressUpdate var4, Proxy var5) {
      return field_180193_a.submit(new HttpUtil$1(var4, var1, var5, var2, var0, var3));
   }

   public static int getSuitableLanPort() {
      ServerSocket var0 = null;
      int var1 = -1;

      try {
         var0 = new ServerSocket(0);
         var1 = var0.getLocalPort();
      } finally {
         try {
            if (var0 != null) {
               var0.close();
            }
         } catch (IOException var8) {
         }
      }

      return var1;
   }

   public static String get(URL var0) {
      HttpURLConnection var1 = (HttpURLConnection)var0.openConnection();
      var1.setRequestMethod("GET");
      BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
      StringBuilder var3 = new StringBuilder();

      String var4;
      while ((var4 = var2.readLine()) != null) {
         var3.append(var4);
         var3.append('\r');
      }

      var2.close();
      return var3.toString();
   }

   public static String post(URL var0, String var1, boolean var2) {
      try {
         Proxy var3 = MinecraftServer.getServer() == null ? null : MinecraftServer.getServer().getServerProxy();
         if (var3 == null) {
            var3 = Proxy.NO_PROXY;
         }

         HttpURLConnection var4 = (HttpURLConnection)var0.openConnection(var3);
         var4.setRequestMethod("POST");
         var4.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         var4.setRequestProperty("Content-Length", "" + var1.getBytes().length);
         var4.setRequestProperty("Content-Language", "en-US");
         var4.setUseCaches(false);
         var4.setDoInput(true);
         var4.setDoOutput(true);
         DataOutputStream var5 = new DataOutputStream(var4.getOutputStream());
         var5.writeBytes(var1);
         var5.flush();
         var5.close();
         BufferedReader var6 = new BufferedReader(new InputStreamReader(var4.getInputStream()));
         StringBuffer var7 = new StringBuffer();

         String var8;
         while ((var8 = var6.readLine()) != null) {
            var7.append(var8);
            var7.append('\r');
         }

         var6.close();
         return var7.toString();
      } catch (Exception var9) {
         if (!var2) {
            logger.error("Could not post to " + var0, var9);
         }

         return "";
      }
   }
}
