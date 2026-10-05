package net.minecraft.network;

import com.cheatbreaker.client.module.type.ClockModule;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.netty.handler.codec.spdy.SpdySessionHandler$1;
import java.lang.reflect.Type;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;

public class ServerStatusResponse$Serializer implements JsonDeserializer<ServerStatusResponse>, JsonSerializer<ServerStatusResponse> {
   public GuiAchievement field_0001;
   public ClockModule field_0003;
   public SpdySessionHandler$1 field_0000;
   public RandomPositionGenerator field_0002;

   public JsonElement serialize(ServerStatusResponse var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      if (var1.getServerDescription() != null) {
         var4.add("description", var3.serialize(var1.getServerDescription()));
      }

      if (var1.getPlayerCountData() != null) {
         var4.add("players", var3.serialize(var1.getPlayerCountData()));
      }

      if (var1.getProtocolVersionInfo() != null) {
         var4.add("version", var3.serialize(var1.getProtocolVersionInfo()));
      }

      if (var1.getFavicon() != null) {
         var4.addProperty("favicon", var1.getFavicon());
      }

      return var4;
   }

   public ServerStatusResponse deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = JsonUtils.getJsonObject(var1, "status");
      ServerStatusResponse var5 = new ServerStatusResponse();
      if (var4.has("description")) {
         var5.setServerDescription((IChatComponent)var3.deserialize(var4.get("description"), IChatComponent.class));
      }

      if (var4.has("players")) {
         var5.setPlayerCountData((ServerStatusResponse$PlayerCountData)var3.deserialize(var4.get("players"), ServerStatusResponse$PlayerCountData.class));
      }

      if (var4.has("version")) {
         var5.setProtocolVersionInfo(
            (ServerStatusResponse$MinecraftProtocolVersionIdentifier)var3.deserialize(
               var4.get("version"), ServerStatusResponse$MinecraftProtocolVersionIdentifier.class
            )
         );
      }

      if (var4.has("favicon")) {
         var5.setFavicon(JsonUtils.getString(var4, "favicon"));
      }

      return var5;
   }
}
