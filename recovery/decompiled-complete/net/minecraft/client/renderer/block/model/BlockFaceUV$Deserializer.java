package net.minecraft.client.renderer.block.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.netty.handler.codec.http.HttpClientCodec$1;
import io.netty.handler.codec.rtsp.RtspHeaders$Names;
import java.lang.reflect.Type;
import net.minecraft.item.crafting.RecipesTools;
import net.minecraft.util.JsonUtils;

public class BlockFaceUV$Deserializer implements JsonDeserializer<BlockFaceUV> {
   public RecipesTools field_0001;
   public RtspHeaders$Names field_0002;
   public HttpClientCodec$1 field_0000;

   public float[] parseUV(JsonObject var1) {
      if (!var1.has("uv")) {
         return null;
      } else {
         JsonArray var2 = JsonUtils.getJsonArray(var1, "uv");
         if (var2.size() != 4) {
            throw new JsonParseException("Expected 4 uv values, found: " + var2.size());
         } else {
            float[] var3 = new float[4];

            for (int var4 = 0; var4 < var3.length; var4++) {
               var3[var4] = JsonUtils.getFloat(var2.get(var4), "uv[" + var4 + "]");
            }

            return var3;
         }
      }
   }

   public int parseRotation(JsonObject var1) {
      int var2 = JsonUtils.getInt(var1, "rotation", 0);
      if (var2 >= 0 && var2 % 90 == 0 && var2 / 90 <= 3) {
         return var2;
      } else {
         throw new JsonParseException("Invalid rotation " + var2 + " found, only 0/90/180/270 allowed");
      }
   }

   public BlockFaceUV deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      float[] var5 = this.parseUV(var4);
      int var6 = this.parseRotation(var4);
      return new BlockFaceUV(var5, var6);
   }
}
