package com.cheatbreaker.client.util.server;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.type.AutoTextModule;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import com.cheatbreaker.client.util.HttpJsonReader;

public class ServerRestrictionManager {
   public String recoveredField2440 = "https://cheatbreaker2.com/restrictions";
   public JsonArray recoveredField2441;

   public ServerRestrictionManager() {
      this.method_24954();
      this.method_24955();
   }

   public void method_24954() {
      try {
         this.recoveredField2441 = new JsonParser()
            .parse(
               new BufferedReader(
                  new InputStreamReader(HttpJsonReader.method_10985(new URL("https://cheatbreaker2.com/restrictions"), false), StandardCharsets.UTF_8)
               )
            )
            .getAsJsonArray();
      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   public void method_24955() {
      try {
         for (JsonElement var2 : this.recoveredField2441) {
            ArrayList var3 = new ArrayList();
            String var4 = var2.getAsJsonObject().get("id").toString().replaceAll("\"", "");
            String var5 = var2.getAsJsonObject().get("restrictedMessage").toString().replaceAll("\"", "");
            AbstractModule var6 = CheatBreaker.getInstance().getModuleManager().method_21669(var4);
            JsonArray var7 = (JsonArray)var2.getAsJsonObject().get("restrictedOptions");
            JsonArray var8 = (JsonArray)var2.getAsJsonObject().get("restrictedAddresses");
            JsonArray var9 = (JsonArray)var2.getAsJsonObject().get("restrictedServerMappingIDs");
            if (var6 != null) {
               if (var8 != null) {
                  for (JsonElement var11 : var8) {
                     String var12 = var11.toString().replaceAll("\"", "");
                     var3.add(var12);
                  }
               }

               if (var7 != null) {
                  for (JsonElement var17 : var7) {
                     String var20 = var17.toString().replaceAll("\"", "");
                  }
               }

               if (var9 != null) {
                  for (JsonElement var18 : var9) {
                     String var21 = var18.toString().replaceAll("\"", "");
                     var3.add(var21);
                  }
               }

               int var16 = var3.size();
               String[] var19 = (java.lang.String[])var3.toArray(new String[var16]);
               if (var6 == CheatBreaker.getInstance().getModuleManager().recoveredField1701) {
                  var5 = "The allowed commands are: "
                     + Arrays.toString((Object[])((AutoTextModule)var6).recoveredField1866).replaceAll("\\[", "").replaceAll("]", "");
               }

               var6.method_28823(var5, var19);
            }
         }
      } catch (NullPointerException var13) {
         var13.printStackTrace();
      }
   }
}
