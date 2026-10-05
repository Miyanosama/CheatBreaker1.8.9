package net.minecraft.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;
import net.minecraft.util.JsonUtils;
import net.optifine.http.HttpPipeline;

public class ServerStatusResponse$PlayerCountData$Serializer
   implements JsonDeserializer<ServerStatusResponse$PlayerCountData>,
   JsonSerializer<ServerStatusResponse$PlayerCountData> {
   public HttpPipeline field_0000;

   public ServerStatusResponse$PlayerCountData deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = JsonUtils.getJsonObject(var1, "players");
      ServerStatusResponse$PlayerCountData var5 = new ServerStatusResponse$PlayerCountData(JsonUtils.getInt(var4, "max"), JsonUtils.getInt(var4, "online"));
      if (JsonUtils.isJsonArray(var4, "sample")) {
         JsonArray var6 = JsonUtils.getJsonArray(var4, "sample");
         if (var6.size() > 0) {
            GameProfile[] var7 = new GameProfile[var6.size()];

            for (int var8 = 0; var8 < var7.length; var8++) {
               JsonObject var9 = JsonUtils.getJsonObject(var6.get(var8), "player[" + var8 + "]");
               String var10 = JsonUtils.getString(var9, "id");
               var7[var8] = new GameProfile(UUID.fromString(var10), JsonUtils.getString(var9, "name"));
            }

            var5.setPlayers(var7);
         }
      }

      return var5;
   }

   public JsonElement serialize(ServerStatusResponse$PlayerCountData var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("max", var1.getMaxPlayers());
      var4.addProperty("online", var1.getOnlinePlayerCount());
      if (var1.getPlayers() != null && var1.getPlayers().length > 0) {
         JsonArray var5 = new JsonArray();

         for (int var6 = 0; var6 < var1.getPlayers().length; var6++) {
            JsonObject var7 = new JsonObject();
            UUID var8 = var1.getPlayers()[var6].getId();
            var7.addProperty("id", var8 == null ? "" : var8.toString());
            var7.addProperty("name", var1.getPlayers()[var6].getName());
            var5.add(var7);
         }

         var4.add("sample", var5);
      }

      return var4;
   }
}
