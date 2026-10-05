package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.netty.handler.codec.base64.Base64;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$1;
import net.minecraft.world.pathfinder.SwimNodeProcessor;
import org.apache.commons.lang3.StringUtils;

public class ModelBlock$Deserializer implements JsonDeserializer<ModelBlock> {
   public StructureNetherBridgePieces$1 field_0001;
   public Base64 field_0002;
   public SwimNodeProcessor field_0000;

   public ModelBlock deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      List var5 = this.getModelElements(var3, var4);
      String var6 = this.getParent(var4);
      boolean var7 = StringUtils.isEmpty(var6);
      boolean var8 = var5.isEmpty();
      if (var8 && var7) {
         throw new JsonParseException("BlockModel requires either elements or parent, found neither");
      } else if (!var7 && !var8) {
         throw new JsonParseException("BlockModel requires either elements or parent, found both");
      } else {
         Map var9 = this.getTextures(var4);
         boolean var10 = this.getAmbientOcclusionEnabled(var4);
         ItemCameraTransforms var11 = ItemCameraTransforms.DEFAULT;
         if (var4.has("display")) {
            JsonObject var12 = JsonUtils.getJsonObject(var4, "display");
            var11 = (ItemCameraTransforms)var3.deserialize(var12, ItemCameraTransforms.class);
         }

         return var8 ? new ModelBlock(new ResourceLocation(var6), var9, var10, true, var11) : new ModelBlock(var5, var9, var10, true, var11);
      }
   }

   public boolean getAmbientOcclusionEnabled(JsonObject var1) {
      return JsonUtils.getBoolean(var1, "ambientocclusion", true);
   }

   public List<BlockPart> getModelElements(JsonDeserializationContext var1, JsonObject var2) {
      ArrayList var3 = Lists.newArrayList();
      if (var2.has("elements")) {
         for (JsonElement var5 : JsonUtils.getJsonArray(var2, "elements")) {
            var3.add((BlockPart)var1.deserialize(var5, BlockPart.class));
         }
      }

      return var3;
   }

   public Map<String, String> getTextures(JsonObject var1) {
      HashMap var2 = Maps.newHashMap();
      if (var1.has("textures")) {
         JsonObject var3 = var1.getAsJsonObject("textures");

         for (Entry var5 : var3.entrySet()) {
            var2.put(var5.getKey(), ((JsonElement)var5.getValue()).getAsString());
         }
      }

      return var2;
   }

   public String getParent(JsonObject var1) {
      return JsonUtils.getString(var1, "parent", "");
   }
}
