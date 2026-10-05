package net.optifine.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.src.Config;

public class HttpPipeline {
   public static final String recoveredField3659 = "Accept";
   public static final String recoveredField3660 = "Keep-Alive";
   public static final String recoveredField3661 = "Transfer-Encoding";
   public static final String recoveredField3662 = "Host";
   public static final String recoveredField3663 = "keep-alive";
   public static final String recoveredField3664 = "Connection";
   public static final String recoveredField3665 = "chunked";
   public static final String recoveredField3666 = "Location";
   public static final String recoveredField3667 = "User-Agent";
   public static Map mapConnections = new HashMap();

   public static HttpRequest makeRequest(String var0, Proxy var1) throws java.io.IOException {
      URL var2 = new URL(var0);
      if (!var2.getProtocol().equals("http")) {
         throw new IOException("Only protocol http is supported: " + var2);
      } else {
         String var3 = var2.getFile();
         String var4 = var2.getHost();
         int var5 = var2.getPort();
         if (var5 <= 0) {
            var5 = 80;
         }

         String var6 = "GET";
         String var7 = "HTTP/1.1";
         LinkedHashMap var8 = new LinkedHashMap();
         var8.put("User-Agent", "Java/" + System.getProperty("java.version"));
         var8.put("Host", var4);
         var8.put("Accept", "text/html, image/gif, image/png");
         var8.put("Connection", "keep-alive");
         byte[] var9 = new byte[0];
         return new HttpRequest(var4, var5, var1, var6, var3, var7, var8, var9);
      }
   }

   public static synchronized HttpPipelineConnection getConnection(String var0, int var1, Proxy var2) {
      String var3 = makeConnectionKey(var0, var1, var2);
      HttpPipelineConnection var4 = (HttpPipelineConnection)mapConnections.get(var3);
      if (var4 == null) {
         var4 = new HttpPipelineConnection(var0, var1, var2);
         mapConnections.put(var3, var4);
      }

      return var4;
   }

   public static String makeConnectionKey(String var0, int var1, Proxy var2) {
      return var0 + ":" + var1 + "-" + var2;
   }

   public static boolean hasActiveRequests() {
      for (Object var1 : mapConnections.values()) {
         HttpPipelineConnection var2 = (HttpPipelineConnection)var1;
         if (var2.method_05641()) {
            return true;
         }
      }

      return false;
   }

   public static void addRequest(String var0, HttpListener var1) throws java.io.IOException {
      addRequest(var0, var1, Proxy.NO_PROXY);
   }

   public static HttpResponse executeRequest(HttpRequest var0) throws java.io.IOException {
      final HashMap var1 = new HashMap();
      String var2 = "Response";
      String var3 = "Exception";
      HttpListener var4 = new HttpListener() {
         @Override
         public void finished(HttpRequest var1x, HttpResponse var2x) {
            synchronized (var1) {
               var1.put("Response", var2x);
               var1.notifyAll();
            }
         }

         @Override
         public void failed(HttpRequest var1x, Exception var2x) {
            synchronized (var1) {
               var1.put("Exception", var2x);
               var1.notifyAll();
            }
         }
      };
      synchronized (var1) {
         HttpPipelineRequest var6 = new HttpPipelineRequest(var0, var4);
         addRequest(var6);

         try {
            var1.wait();
         } catch (InterruptedException var10) {
            throw new InterruptedIOException("Interrupted");
         }

         Exception var7 = (Exception)var1.get("Exception");
         if (var7 != null) {
            if (var7 instanceof IOException) {
               throw (IOException)var7;
            } else if (var7 instanceof RuntimeException) {
               throw (RuntimeException)var7;
            } else {
               throw new RuntimeException(var7.getMessage(), var7);
            }
         } else {
            HttpResponse var8 = (HttpResponse)var1.get("Response");
            if (var8 == null) {
               throw new IOException("Response is null");
            } else {
               return var8;
            }
         }
      }
   }

   public static void addRequest(HttpPipelineRequest var0) {
      HttpRequest var1 = var0.getHttpRequest();

      for (HttpPipelineConnection var2 = getConnection(var1.getHost(), var1.getPort(), var1.getProxy());
         !var2.addRequest(var0);
         var2 = getConnection(var1.getHost(), var1.getPort(), var1.getProxy())
      ) {
         removeConnection(var1.getHost(), var1.getPort(), var1.getProxy(), var2);
      }
   }

   public static byte[] get(String var0, Proxy var1) throws java.io.IOException {
      if (var0.startsWith("file:")) {
         URL var5 = new URL(var0);
         InputStream var6 = var5.openStream();
         return Config.readAll(var6);
      } else {
         HttpRequest var2 = makeRequest(var0, var1);
         HttpResponse var3 = executeRequest(var2);
         if (var3.getStatus() / 100 != 2) {
            throw new IOException("HTTP response: " + var3.getStatus());
         } else {
            return var3.getBody();
         }
      }
   }

   public static byte[] get(String var0) throws java.io.IOException {
      return get(var0, Proxy.NO_PROXY);
   }

   public static synchronized void removeConnection(String var0, int var1, Proxy var2, HttpPipelineConnection var3) {
      String var4 = makeConnectionKey(var0, var1, var2);
      HttpPipelineConnection var5 = (HttpPipelineConnection)mapConnections.get(var4);
      if (var5 == var3) {
         mapConnections.remove(var4);
      }
   }

   public static void addRequest(String var0, HttpListener var1, Proxy var2) throws java.io.IOException {
      HttpRequest var3 = makeRequest(var0, var2);
      HttpPipelineRequest var4 = new HttpPipelineRequest(var3, var1);
      addRequest(var4);
   }
}
