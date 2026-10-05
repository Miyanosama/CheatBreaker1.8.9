package net.minecraft.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.client.renderer.GlStateManager$TexGen;
import net.minecraft.util.JsonUtils;
import recovered.unidentified.UnidentifiedClass1135;

public class ServerStatusResponse$MinecraftProtocolVersionIdentifier$Serializer
   implements JsonDeserializer<ServerStatusResponse$MinecraftProtocolVersionIdentifier>,
   JsonSerializer<ServerStatusResponse$MinecraftProtocolVersionIdentifier> {
   public GlStateManager$TexGen field_0000;
   public UnidentifiedClass1135 field_0001;

   public ServerStatusResponse$MinecraftProtocolVersionIdentifier deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = JsonUtils.getJsonObject(var1, "version");
      return new ServerStatusResponse$MinecraftProtocolVersionIdentifier(JsonUtils.getString(var4, "name"), JsonUtils.getInt(var4, "protocol"));
   }

   public JsonElement serialize(ServerStatusResponse$MinecraftProtocolVersionIdentifier var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("name", var1.getName());
      var4.addProperty("protocol", var1.getProtocol());
      return var4;
   }
}
