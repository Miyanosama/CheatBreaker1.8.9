package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import io.netty.handler.codec.http.multipart.MixedFileUpload;
import java.util.UUID;
import net.optifine.RandomEntityRule;

public class UserListWhitelistEntry extends UserListEntry<GameProfile> {
   public MixedFileUpload field_0000;
   public RandomEntityRule field_0001;

   public static GameProfile gameProfileFromJsonObject(JsonObject var0) {
      if (var0.has("uuid") && var0.has("name")) {
         String var1 = var0.get("uuid").getAsString();

         UUID var2;
         try {
            var2 = UUID.fromString(var1);
         } catch (Throwable var4) {
            return null;
         }

         return new GameProfile(var2, var0.get("name").getAsString());
      } else {
         return null;
      }
   }

   public UserListWhitelistEntry(GameProfile var1) {
      super(var1);
   }

   public UserListWhitelistEntry(JsonObject var1) {
      super(gameProfileFromJsonObject(var1), var1);
   }

   @Override
   public void onSerialization(JsonObject var1) {
      if (this.getValue() != null) {
         var1.addProperty("uuid", this.getValue().getId() == null ? "" : this.getValue().getId().toString());
         var1.addProperty("name", this.getValue().getName());
         super.onSerialization(var1);
      }
   }
}
