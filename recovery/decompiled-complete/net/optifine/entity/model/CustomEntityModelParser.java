package net.optifine.entity.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import io.netty.channel.ThreadPerChannelEventLoop$1;
import io.netty.handler.codec.spdy.SpdyCodecUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.ConnectedParser;
import net.optifine.entity.model.anim.ModelUpdater;
import net.optifine.entity.model.anim.ModelVariableUpdater;
import net.optifine.player.PlayerItemParser;
import net.optifine.util.Json;
import recovered.unidentified.UnidentifiedClass3584;
import recovered.unidentified.UnidentifiedClass4213;

public class CustomEntityModelParser {
   public static String field_0015;
   public static String field_0028;
   public UnidentifiedClass3584 field_0014;
   public ThreadPerChannelEventLoop$1 field_0025;
   public static String field_0006;
   public static String field_0008;
   public UnidentifiedClass4213 field_0029;
   public static String field_0021;
   public static String field_0009;
   public static String field_0031;
   public static String field_0005;
   public static String field_0016;
   public static String field_0019;
   public static String field_0012;
   public static String field_0023;
   public static String field_0027;
   public static String field_0003;
   public SpdyCodecUtil field_0011;
   public static String field_0017;
   public static String field_0026;
   public static String field_0002;
   public static String field_0001;
   public static String field_0004;
   public static String field_0000;
   public static String field_0024;
   public static String field_0018;
   public static String field_0022;
   public static String field_0013;
   public static String field_0030;
   public static String field_0020;
   public static String field_0010;
   public static String field_0007;

   public static ResourceLocation getResourceLocation(String var0, String var1, String var2) {
      if (!var1.endsWith(var2)) {
         var1 = var1 + var2;
      }

      if (!var1.contains("/")) {
         var1 = var0 + "/" + var1;
      } else if (var1.startsWith("./")) {
         var1 = var0 + "/" + var1.substring(2);
      } else if (var1.startsWith("~/")) {
         var1 = "optifine/" + var1.substring(2);
      }

      return new ResourceLocation(var1);
   }

   public static void copyJsonElements(JsonObject var0, JsonObject var1) {
      for (Entry var3 : var0.entrySet()) {
         if (!((String)var3.getKey()).equals("id") && !var1.has((String)var3.getKey())) {
            var1.add((String)var3.getKey(), (JsonElement)var3.getValue());
         }
      }
   }

   public static CustomModelRenderer parseCustomModelRenderer(JsonObject var0, int[] var1, String var2) {
      String var3 = Json.getString(var0, "part");
      checkNull(var3, "Model part not specified, missing \"replace\" or \"attachTo\".");
      boolean var4 = Json.getBoolean(var0, "attach", false);
      CustomEntityModel var5 = new CustomEntityModel();
      if (var1 != null) {
         var5.t = var1[0];
         var5.u = var1[1];
      }

      ModelUpdater var6 = null;
      JsonArray var7 = (JsonArray)var0.get("animations");
      if (var7 != null) {
         ArrayList var8 = new ArrayList();

         for (int var9 = 0; var9 < var7.size(); var9++) {
            JsonObject var10 = (JsonObject)var7.get(var9);

            for (Entry var12 : var10.entrySet()) {
               String var13 = (String)var12.getKey();
               String var14 = ((JsonElement)var12.getValue()).getAsString();
               ModelVariableUpdater var15 = new ModelVariableUpdater(var13, var14);
               var8.add(var15);
            }
         }

         if (var8.size() > 0) {
            ModelVariableUpdater[] var17 = var8.toArray(new ModelVariableUpdater[var8.size()]);
            var6 = new ModelUpdater(var17);
         }
      }

      ModelRenderer var16 = PlayerItemParser.parseModelRenderer(var0, var5, var1, var2);
      return new CustomModelRenderer(var3, var4, var16, var6);
   }

   public static void method_12407(JsonObject var0, Map var1) {
      String var2 = Json.getString(var0, "id");
      if (var2 != null) {
         if (var2.length() < 1) {
            Config.warn("Empty model ID: " + var2);
         } else if (var1.containsKey(var2)) {
            Config.warn("Duplicate model ID: " + var2);
         } else {
            var1.put(var2, var0);
         }
      }
   }

   public static void checkNull(Object var0, String var1) {
      if (var0 == null) {
         throw new JsonParseException(var1);
      }
   }

   public static void method_12413(JsonObject var0, Map var1) {
      String var2 = Json.getString(var0, "baseId");
      if (var2 != null) {
         JsonObject var3 = (JsonObject)var1.get(var2);
         if (var3 == null) {
            Config.warn("BaseID not found: " + var2);
         } else {
            copyJsonElements(var3, var0);
         }
      }
   }

   public static JsonObject loadJson(ResourceLocation var0) {
      InputStream var1 = Config.getResourceStream(var0);
      if (var1 == null) {
         return null;
      } else {
         String var2 = Config.readInputStream(var1, "ASCII");
         var1.close();
         JsonParser var3 = new JsonParser();
         return (JsonObject)var3.parse(var2);
      }
   }

   public static void processExternalModel(JsonObject var0, Map var1, String var2) {
      String var3 = Json.getString(var0, "model");
      if (var3 != null) {
         ResourceLocation var4 = getResourceLocation(var2, var3, ".jpm");

         try {
            JsonObject var5 = loadJson(var4);
            if (var5 == null) {
               Config.warn("Model not found: " + var4);
               return;
            }

            copyJsonElements(var5, var0);
         } catch (IOException var6) {
            Config.error("" + var6.getClass().getName() + ": " + var6.getMessage());
         } catch (JsonParseException var7) {
            Config.error("" + var7.getClass().getName() + ": " + var7.getMessage());
         } catch (Exception var8) {
            var8.printStackTrace();
         }
      }
   }

   public static CustomEntityRenderer parseEntityRender(JsonObject var0, String var1) {
      ConnectedParser var2 = new ConnectedParser("CustomEntityModels");
      String var3 = var2.parseName(var1);
      String var4 = var2.parseBasePath(var1);
      String var5 = Json.getString(var0, "texture");
      int[] var6 = Json.parseIntArray(var0.get("textureSize"), 2);
      float var7 = Json.getFloat(var0, "shadowSize", -1.0F);
      JsonArray var8 = (JsonArray)var0.get("models");
      checkNull(var8, "Missing models");
      HashMap var9 = new HashMap();
      ArrayList var10 = new ArrayList();

      for (int var11 = 0; var11 < var8.size(); var11++) {
         JsonObject var12 = (JsonObject)var8.get(var11);
         method_12413(var12, var9);
         processExternalModel(var12, var9, var4);
         method_12407(var12, var9);
         CustomModelRenderer var13 = parseCustomModelRenderer(var12, var6, var4);
         if (var13 != null) {
            var10.add(var13);
         }
      }

      CustomModelRenderer[] var14 = var10.toArray(new CustomModelRenderer[var10.size()]);
      ResourceLocation var15 = null;
      if (var5 != null) {
         var15 = getResourceLocation(var4, var5, ".png");
      }

      return new CustomEntityRenderer(var3, var4, var15, var14, var7);
   }
}
