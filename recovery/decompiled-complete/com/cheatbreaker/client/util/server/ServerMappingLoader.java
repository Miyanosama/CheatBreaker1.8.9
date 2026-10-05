package com.cheatbreaker.client.util.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker00;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class ServerMappingLoader {
   public static JsonArray field_0000;
   public WebSocketClientHandshaker00 field_0001;

   public static String[] method_12435(String var0) {
      try {
         for (JsonElement var2 : field_0000) {
            String var3 = var2.getAsJsonObject().get("id").toString().replaceAll("\"", "");
            if (Objects.equals(var0, var3)) {
               JsonArray var4 = (JsonArray)var2.getAsJsonObject().get("addresses");
               ArrayList var5 = new ArrayList();

               for (JsonElement var7 : var4) {
                  var5.add(var7.toString().replaceAll("\"", ""));
               }

               String[] var9 = new String[var5.size()];
               return var5.toArray(var9);
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return null;
   }

   public static String method_12436(String var0, String var1) {
      try {
         for (JsonElement var3 : field_0000) {
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
      try {
         field_0000 = new JsonParser()
            .parse(
               new BufferedReader(new InputStreamReader(new URL("https://servermappings.lunarclientcdn.com/servers.json").openStream(), StandardCharsets.UTF_8))
            )
            .getAsJsonArray();
      } catch (Exception var1) {
         var1.printStackTrace();
      }
   }
}
