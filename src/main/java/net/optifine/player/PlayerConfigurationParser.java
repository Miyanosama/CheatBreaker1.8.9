package net.optifine.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.http.HttpPipeline;
import net.optifine.http.HttpUtils;
import net.optifine.util.Json;

public class PlayerConfigurationParser {
   public static final String recoveredField941 = "type";
   public static final String recoveredField942 = "active";
   public String player = null;
   public static final String recoveredField943 = "items";

   public PlayerConfiguration parsePlayerConfiguration(JsonElement var1) {
      if (var1 == null) {
         throw new JsonParseException("JSON object is null, player: " + this.player);
      } else {
         JsonObject var2 = (JsonObject)var1;
         PlayerConfiguration var3 = new PlayerConfiguration();
         JsonArray var4 = (JsonArray)var2.get("items");
         if (var4 != null) {
            for (int var5 = 0; var5 < var4.size(); var5++) {
               JsonObject var6 = (JsonObject)var4.get(var5);
               boolean var7 = Json.getBoolean(var6, "active", true);
               if (var7) {
                  String var8 = Json.getString(var6, "type");
                  if (var8 == null) {
                     Config.warn("Item type is null, player: " + this.player);
                  } else {
                     String var9 = Json.getString(var6, "model");
                     if (var9 == null) {
                        var9 = "items/" + var8 + "/model.cfg";
                     }

                     PlayerItemModel var10 = this.downloadModel(var9);
                     if (var10 != null) {
                        if (!var10.isUsePlayerTexture()) {
                           String var11 = Json.getString(var6, "texture");
                           if (var11 == null) {
                              var11 = "items/" + var8 + "/users/" + this.player + ".png";
                           }

                           BufferedImage var12 = this.downloadTextureImage(var11);
                           if (var12 == null) {
                              continue;
                           }

                           var10.setTextureImage(var12);
                           ResourceLocation var13 = new ResourceLocation("optifine.net", var11);
                           var10.setTextureLocation(var13);
                        }

                        var3.addPlayerItemModel(var10);
                     }
                  }
               }
            }
         }

         return var3;
      }
   }

   public PlayerConfigurationParser(String var1) {
      this.player = var1;
   }

   public PlayerItemModel downloadModel(String var1) {
      String var2 = HttpUtils.getPlayerItemsUrl() + "/" + var1;

      try {
         byte[] var3 = HttpPipeline.get(var2, Minecraft.getMinecraft().getProxy());
         String var4 = new String(var3, "ASCII");
         JsonParser var5 = new JsonParser();
         JsonObject var6 = (JsonObject)var5.parse(var4);
         return PlayerItemParser.parseItemModel(var6);
      } catch (Exception var8) {
         Config.warn("Error loading item model " + var1 + ": " + var8.getClass().getName() + ": " + var8.getMessage());
         return null;
      }
   }

   public BufferedImage downloadTextureImage(String var1) {
      String var2 = HttpUtils.getPlayerItemsUrl() + "/" + var1;

      try {
         byte[] var3 = HttpPipeline.get(var2, Minecraft.getMinecraft().getProxy());
         return ImageIO.read(new ByteArrayInputStream(var3));
      } catch (IOException var5) {
         Config.warn("Error loading item texture " + var1 + ": " + var5.getClass().getName() + ": " + var5.getMessage());
         return null;
      }
   }
}
