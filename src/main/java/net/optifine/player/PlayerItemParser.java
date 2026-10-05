package net.optifine.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.CustomEntityModelParser;
import net.optifine.util.Json;

public class PlayerItemParser {
   public static final String recoveredField364 = "uvEast";
   public static final String recoveredField365 = "invertAxis";
   public static final String recoveredField366 = "submodel";
   public static final String recoveredField367 = "PlayerItem";
   public static final String recoveredField368 = "uvSouth";
   public static final String recoveredField369 = "uvDown";
   public static final String recoveredField370 = "scale";
   public static final String recoveredField371 = "coordinates";
   public static final String recoveredField372 = "usePlayerTexture";
   public static final String recoveredField373 = "type";
   public static final String recoveredField374 = "uvRight";
   public static final String recoveredField375 = "type";
   public static final String recoveredField376 = "ModelBox";
   public static final String recoveredField377 = "submodels";
   public static final String recoveredField378 = "uvNorth";
   public static final String recoveredField379 = "mirrorTexture";
   public static final String recoveredField380 = "textureOffset";
   public static final String recoveredField381 = "uvBack";
   public static final String recoveredField382 = "models";
   public static final String recoveredField383 = "texture";
   public static final String recoveredField384 = "sprites";
   public static final String recoveredField385 = "baseId";
   public static final String recoveredField386 = "id";
   public static final String recoveredField387 = "textureSize";
   public static final String recoveredField388 = "translate";
   public static final String recoveredField389 = "uvLeft";
   public static final String recoveredField390 = "textureSize";
   public static final String recoveredField391 = "uvWest";
   public static final String recoveredField392 = "attachTo";
   public static final String recoveredField393 = "boxes";
   public static final String recoveredField394 = "rotate";
   public static final String recoveredField395 = "uvUp";
   public static final String recoveredField396 = "uvFront";
   public static final String recoveredField397 = "sizeAdd";
   public static JsonParser jsonParser = new JsonParser();

   public static PlayerItemModel parseItemModel(JsonObject var0) {
      String var1 = Json.getString(var0, "type");
      if (!Config.equals(var1, "PlayerItem")) {
         throw new JsonParseException("Unknown model type: " + var1);
      } else {
         int[] var2 = Json.parseIntArray(var0.get("textureSize"), 2);
         checkNull(var2, "Missing texture size");
         Dimension var3 = new Dimension(var2[0], var2[1]);
         boolean var4 = Json.getBoolean(var0, "usePlayerTexture", false);
         JsonArray var5 = (JsonArray)var0.get("models");
         checkNull(var5, "Missing elements");
         HashMap var6 = new HashMap();
         ArrayList var7 = new ArrayList();
         new ArrayList();

         for (int var8 = 0; var8 < var5.size(); var8++) {
            JsonObject var9 = (JsonObject)var5.get(var8);
            String var10 = Json.getString(var9, "baseId");
            if (var10 != null) {
               JsonObject var11 = (JsonObject)var6.get(var10);
               if (var11 == null) {
                  Config.warn("BaseID not found: " + var10);
                  continue;
               }

               for (Entry var13 : var11.entrySet()) {
                  if (!var9.has((String)var13.getKey())) {
                     var9.add((String)var13.getKey(), (JsonElement)var13.getValue());
                  }
               }
            }

            String var15 = Json.getString(var9, "id");
            if (var15 != null) {
               if (!var6.containsKey(var15)) {
                  var6.put(var15, var9);
               } else {
                  Config.warn("Duplicate model ID: " + var15);
               }
            }

            PlayerItemRenderer var16 = parseItemRenderer(var9, var3);
            if (var16 != null) {
               var7.add(var16);
            }
         }

         PlayerItemRenderer[] var14 = (net.optifine.player.PlayerItemRenderer[])var7.toArray(new PlayerItemRenderer[var7.size()]);
         return new PlayerItemModel(var3, var4, var14);
      }
   }

   public static ResourceLocation makeResourceLocation(String var0) {
      int var1 = var0.indexOf(58);
      if (var1 < 0) {
         return new ResourceLocation(var0);
      } else {
         String var2 = var0.substring(0, var1);
         String var3 = var0.substring(var1 + 1);
         return new ResourceLocation(var2, var3);
      }
   }

   public static PlayerItemRenderer parseItemRenderer(JsonObject var0, Dimension var1) {
      String var2 = Json.getString(var0, "type");
      if (!Config.equals(var2, "ModelBox")) {
         Config.warn("Unknown model type: " + var2);
         return null;
      } else {
         String var3 = Json.getString(var0, "attachTo");
         int var4 = parseAttachModel(var3);
         ModelPlayerItem var5 = new ModelPlayerItem();
         var5.t = var1.width;
         var5.u = var1.height;
         ModelRenderer var6 = parseModelRenderer(var0, var5, (int[])null, (String)null);
         return new PlayerItemRenderer(var4, var6);
      }
   }

   public static void checkNull(Object var0, String var1) {
      if (var0 == null) {
         throw new JsonParseException(var1);
      }
   }

   public static ModelRenderer parseModelRenderer(JsonObject var0, ModelBase var1, int[] var2, String var3) {
      ModelRenderer var4 = new ModelRenderer(var1);
      String var5 = Json.getString(var0, "id");
      var4.setId(var5);
      float var6 = Json.getFloat(var0, "scale", 1.0F);
      var4.scaleX = var6;
      var4.scaleY = var6;
      var4.scaleZ = var6;
      String var7 = Json.getString(var0, "texture");
      if (var7 != null) {
         var4.setTextureLocation(CustomEntityModelParser.getResourceLocation(var3, var7, ".png"));
      }

      int[] var8 = Json.parseIntArray(var0.get("textureSize"), 2);
      if (var8 == null) {
         var8 = var2;
      }

      if (var8 != null) {
         var4.setTextureSize(var8[0], var8[1]);
      }

      String var9 = Json.getString(var0, "invertAxis", "").toLowerCase();
      boolean var10 = var9.contains("x");
      boolean var11 = var9.contains("y");
      boolean var12 = var9.contains("z");
      float[] var13 = Json.parseFloatArray(var0.get("translate"), 3, new float[3]);
      if (var10) {
         var13[0] = -var13[0];
      }

      if (var11) {
         var13[1] = -var13[1];
      }

      if (var12) {
         var13[2] = -var13[2];
      }

      float[] var14 = Json.parseFloatArray(var0.get("rotate"), 3, new float[3]);

      for (int var15 = 0; var15 < var14.length; var15++) {
         var14[var15] = var14[var15] / 180.0F * MathHelper.PI;
      }

      if (var10) {
         var14[0] = -var14[0];
      }

      if (var11) {
         var14[1] = -var14[1];
      }

      if (var12) {
         var14[2] = -var14[2];
      }

      var4.setRotationPoint(var13[0], var13[1], var13[2]);
      var4.rotateAngleX = var14[0];
      var4.rotateAngleY = var14[1];
      var4.rotateAngleZ = var14[2];
      String var26 = Json.getString(var0, "mirrorTexture", "").toLowerCase();
      boolean var16 = var26.contains("u");
      boolean var17 = var26.contains("v");
      if (var16) {
         var4.mirror = true;
      }

      if (var17) {
         var4.mirrorV = true;
      }

      JsonArray var18 = var0.getAsJsonArray("boxes");
      if (var18 != null) {
         for (int var19 = 0; var19 < var18.size(); var19++) {
            JsonObject var20 = var18.get(var19).getAsJsonObject();
            int[] var21 = Json.parseIntArray(var20.get("textureOffset"), 2);
            int[][] var22 = parseFaceUvs(var20);
            if (var21 == null && var22 == null) {
               throw new JsonParseException("Texture offset not specified");
            }

            float[] var23 = Json.parseFloatArray(var20.get("coordinates"), 6);
            if (var23 == null) {
               throw new JsonParseException("Coordinates not specified");
            }

            if (var10) {
               var23[0] = -var23[0] - var23[3];
            }

            if (var11) {
               var23[1] = -var23[1] - var23[4];
            }

            if (var12) {
               var23[2] = -var23[2] - var23[5];
            }

            float var24 = Json.getFloat(var20, "sizeAdd", 0.0F);
            if (var22 != null) {
               var4.addBox(var22, var23[0], var23[1], var23[2], var23[3], var23[4], var23[5], var24);
            } else {
               var4.setTextureOffset(var21[0], var21[1]);
               var4.addBox(var23[0], var23[1], var23[2], (int)var23[3], (int)var23[4], (int)var23[5], var24);
            }
         }
      }

      JsonArray var27 = var0.getAsJsonArray("sprites");
      if (var27 != null) {
         for (int var28 = 0; var28 < var27.size(); var28++) {
            JsonObject var30 = var27.get(var28).getAsJsonObject();
            int[] var33 = Json.parseIntArray(var30.get("textureOffset"), 2);
            if (var33 == null) {
               throw new JsonParseException("Texture offset not specified");
            }

            float[] var35 = Json.parseFloatArray(var30.get("coordinates"), 6);
            if (var35 == null) {
               throw new JsonParseException("Coordinates not specified");
            }

            if (var10) {
               var35[0] = -var35[0] - var35[3];
            }

            if (var11) {
               var35[1] = -var35[1] - var35[4];
            }

            if (var12) {
               var35[2] = -var35[2] - var35[5];
            }

            float var37 = Json.getFloat(var30, "sizeAdd", 0.0F);
            var4.setTextureOffset(var33[0], var33[1]);
            var4.addSprite(var35[0], var35[1], var35[2], (int)var35[3], (int)var35[4], (int)var35[5], var37);
         }
      }

      JsonObject var29 = (JsonObject)var0.get("submodel");
      if (var29 != null) {
         ModelRenderer var31 = parseModelRenderer(var29, var1, var8, var3);
         var4.addChild(var31);
      }

      JsonArray var32 = (JsonArray)var0.get("submodels");
      if (var32 != null) {
         for (int var34 = 0; var34 < var32.size(); var34++) {
            JsonObject var36 = (JsonObject)var32.get(var34);
            ModelRenderer var38 = parseModelRenderer(var36, var1, var8, var3);
            if (var38.getId() != null) {
               ModelRenderer var25 = var4.getChild(var38.getId());
               if (var25 != null) {
                  Config.warn("Duplicate model ID: " + var38.getId());
               }
            }

            var4.addChild(var38);
         }
      }

      return var4;
   }

   public static int[][] parseFaceUvs(JsonObject var0) {
      int[][] var1 = new int[][]{
         Json.parseIntArray(var0.get("uvDown"), 4),
         Json.parseIntArray(var0.get("uvUp"), 4),
         Json.parseIntArray(var0.get("uvNorth"), 4),
         Json.parseIntArray(var0.get("uvSouth"), 4),
         Json.parseIntArray(var0.get("uvWest"), 4),
         Json.parseIntArray(var0.get("uvEast"), 4)
      };
      if (var1[2] == null) {
         var1[2] = Json.parseIntArray(var0.get("uvFront"), 4);
      }

      if (var1[3] == null) {
         var1[3] = Json.parseIntArray(var0.get("uvBack"), 4);
      }

      if (var1[4] == null) {
         var1[4] = Json.parseIntArray(var0.get("uvLeft"), 4);
      }

      if (var1[5] == null) {
         var1[5] = Json.parseIntArray(var0.get("uvRight"), 4);
      }

      boolean var2 = false;

      for (int var3 = 0; var3 < var1.length; var3++) {
         if (var1[var3] != null) {
            var2 = true;
         }
      }

      return !var2 ? (int[][])null : var1;
   }

   public static int parseAttachModel(String var0) {
      if (var0 == null) {
         return 0;
      } else if (var0.equals("body")) {
         return 0;
      } else if (var0.equals("head")) {
         return 1;
      } else if (var0.equals("leftArm")) {
         return 2;
      } else if (var0.equals("rightArm")) {
         return 3;
      } else if (var0.equals("leftLeg")) {
         return 4;
      } else if (var0.equals("rightLeg")) {
         return 5;
      } else if (var0.equals("cape")) {
         return 6;
      } else {
         Config.warn("Unknown attachModel: " + var0);
         return 0;
      }
   }
}
