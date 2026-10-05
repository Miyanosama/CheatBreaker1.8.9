package net.minecraft.server.management;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public class PlayerProfileCache$Serializer implements JsonDeserializer<PlayerProfileCache$ProfileEntry>, JsonSerializer<PlayerProfileCache$ProfileEntry> {
   public JsonElement serialize(PlayerProfileCache$ProfileEntry var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("name", var1.getGameProfile().getName());
      UUID var5 = var1.getGameProfile().getId();
      var4.addProperty("uuid", var5 == null ? "" : var5.toString());
      var4.addProperty("expiresOn", PlayerProfileCache.dateFormat.format(var1.getExpirationDate()));
      return var4;
   }

   public PlayerProfileCache$Serializer(PlayerProfileCache var1) {
      this.field_152677_a = var1;
      super();
   }

   public PlayerProfileCache$ProfileEntry deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if (var1.isJsonObject()) {
         JsonObject var4 = var1.getAsJsonObject();
         JsonElement var5 = var4.get("name");
         JsonElement var6 = var4.get("uuid");
         JsonElement var7 = var4.get("expiresOn");
         if (var5 != null && var6 != null) {
            String var8 = var6.getAsString();
            String var9 = var5.getAsString();
            Date var10 = null;
            if (var7 != null) {
               try {
                  var10 = PlayerProfileCache.dateFormat.parse(var7.getAsString());
               } catch (ParseException var14) {
                  var10 = null;
               }
            }

            if (var9 != null && var8 != null) {
               UUID var11;
               try {
                  var11 = UUID.fromString(var8);
               } catch (Throwable var13) {
                  return null;
               }

               PlayerProfileCache var10002 = this.field_152677_a;
               this.field_152677_a.getClass();
               return new PlayerProfileCache$ProfileEntry(var10002, new GameProfile(var11, var9), var10, null);
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }
}
