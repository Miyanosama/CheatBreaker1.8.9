package com.cheatbreaker.client.util.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.apache.logging.log4j.LogManager;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class ServerMappingLoader {
   public static volatile JsonArray recoveredField140 = new JsonArray();
   private static volatile boolean loaded;

   public static boolean isLoaded() {
      return loaded;
   }

   public static String[] method_12435(String var0) {
      try {
         for (JsonElement var2 : recoveredField140) {
            String var3 = var2.getAsJsonObject().get("id").toString().replaceAll("\"", "");
            if (Objects.equals(var0, var3)) {
               JsonArray var4 = (JsonArray)var2.getAsJsonObject().get("addresses");
               ArrayList var5 = new ArrayList();

               for (JsonElement var7 : var4) {
                  var5.add(var7.toString().replaceAll("\"", ""));
               }

               String[] var9 = new String[var5.size()];
               return (java.lang.String[])var5.toArray(var9);
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return null;
   }

   public static String method_12436(String var0, String var1) {
      try {
         for (JsonElement var3 : recoveredField140) {
            String var4 = var3.getAsJsonObject().get(var1).toString().replaceAll("\"", "");

            for (JsonElement var7 : (JsonArray)var3.getAsJsonObject().get("addresses")) {
               if (var0.endsWith(var7.toString().replaceAll("\"", ""))) {
                  return var4;
               }
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return null;
   }

   static {
      Thread loader = new Thread(() -> {
         try {
            URLConnection connection = new URL("https://servermappings.lunarclientcdn.com/servers.json").openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
               recoveredField140 = new JsonParser().parse(reader).getAsJsonArray();
            }
         } catch (Exception error) {
            LogManager.getLogger().warn("Could not load optional server mappings", error);
         } finally {
            loaded = true;
         }
      }, "Server Mapping Loader");
      loader.setDaemon(true);
      loader.start();
   }
}
