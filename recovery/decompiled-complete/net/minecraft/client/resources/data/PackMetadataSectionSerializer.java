package net.minecraft.client.resources.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.netty.channel.DefaultMessageSizeEstimator$1;
import java.lang.reflect.Type;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;
import org.slf4j.MDC;

public class PackMetadataSectionSerializer extends BaseMetadataSectionSerializer<PackMetadataSection> implements JsonSerializer<PackMetadataSection> {
   public MDC field_0001;
   public EntityFootStepFX field_0002;
   public DefaultMessageSizeEstimator$1 field_0000;

   @Override
   public String getSectionName() {
      return "pack";
   }

   public JsonElement serialize(PackMetadataSection var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("pack_format", var1.getPackFormat());
      var4.add("description", var3.serialize(var1.getPackDescription()));
      return var4;
   }

   public PackMetadataSection deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      IChatComponent var5 = (IChatComponent)var3.deserialize(var4.get("description"), IChatComponent.class);
      if (var5 == null) {
         throw new JsonParseException("Invalid/missing description!");
      } else {
         int var6 = JsonUtils.getInt(var4, "pack_format");
         return new PackMetadataSection(var5, var6);
      }
   }
}
