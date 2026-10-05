package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javazoom.jl.decoder.JavaLayerUtils;
import javazoom.jl.player.Player;

public class DashUtil {
   public static boolean field_0002;
   public static String field_0004;
   public static DashPlayer dashPlayer = new DashPlayer();
   public GuiDrawEvent field_0003;
   public static Player player;

   public static void end() {
      if (player != null) {
         player.close();
         player = null;
      }

      field_0002 = false;
   }

   public static boolean isPlayerNotNull() {
      return player != null;
   }

   public static String dashHelpers(String var0) {
      try {
         URLConnection var1 = new URL(var0).openConnection();
         BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
         return var2.readLine();
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static void end(String var0) {
      if (!field_0002) {
         field_0002 = true;
         if (player != null) {
            player.close();
            player = null;
         } else {
            new Thread(() -> {
               try {
                  URL var1 = new URL(var0);
                  InputStream var2 = var1.openStream();
                  dashPlayer = new DashPlayer();
                  player = new Player(var2, dashPlayer);
                  player.play();
               } catch (Exception var3) {
                  var3.printStackTrace();
               }
            }).start();
         }
      }
   }

   public static DashPlayer getDashPlayer() {
      return dashPlayer;
   }

   public static List<Station> dashHelpers() {
      JavaLayerUtils.setHook(new DashHook());
      ArrayList var0 = new ArrayList();

      try {
         JsonObject var1 = new JsonParser().parse(dashHelpers("https://dash-api.com/api/v3/allData.php")).getAsJsonObject();
         if (var1.has("stations")) {
            for (JsonElement var4 : var1.getAsJsonArray("stations")) {
               JsonObject var5 = var4.getAsJsonObject();
               String var6 = var5.get("name").getAsString();
               String var7 = var5.get("genre").getAsString();
               String var8 = var5.get("square_logo_url").getAsString();
               String var9 = var5.get("current_song_url").getAsString();
               String var10 = var5.get("stream_url").getAsString();
               Station var11 = new Station(var6, var8, var7, var9, var10);
               var0.add(var11);
            }
         }
      } catch (Exception var12) {
         var12.printStackTrace();
      }

      return var0;
   }
}
