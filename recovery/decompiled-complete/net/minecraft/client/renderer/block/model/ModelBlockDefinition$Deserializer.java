package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.handler.codec.spdy.SpdySessionHandler$4;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import javazoom.jl.converter.WaveFile$WaveFormat_Chunk;
import net.minecraft.util.JsonUtils;

public class ModelBlockDefinition$Deserializer implements JsonDeserializer<ModelBlockDefinition> {
   public SpdySessionHandler$4 field_0000;
   public WaveFile$WaveFormat_Chunk field_0001;

   public List<ModelBlockDefinition$Variants> parseVariantsList(JsonDeserializationContext var1, JsonObject var2) {
      JsonObject var3 = JsonUtils.getJsonObject(var2, "variants");
      ArrayList var4 = Lists.newArrayList();

      for (Entry var6 : var3.entrySet()) {
         var4.add(this.parseVariants(var1, var6));
      }

      return var4;
   }

   public ModelBlockDefinition$Variants parseVariants(JsonDeserializationContext var1, Entry<String, JsonElement> var2) {
      String var3 = (String)var2.getKey();
      ArrayList var4 = Lists.newArrayList();
      JsonElement var5 = (JsonElement)var2.getValue();
      if (var5.isJsonArray()) {
         for (JsonElement var7 : var5.getAsJsonArray()) {
            var4.add((ModelBlockDefinition$Variant)var1.deserialize(var7, ModelBlockDefinition$Variant.class));
         }
      } else {
         var4.add((ModelBlockDefinition$Variant)var1.deserialize(var5, ModelBlockDefinition$Variant.class));
      }

      return new ModelBlockDefinition$Variants(var3, var4);
   }

   public ModelBlockDefinition deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      List var5 = this.parseVariantsList(var3, var4);
      return new ModelBlockDefinition(var5);
   }
}
