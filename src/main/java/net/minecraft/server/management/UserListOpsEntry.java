package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class UserListOpsEntry extends UserListEntry<GameProfile> {
   public int permissionLevel;
   public boolean bypassesPlayerLimit;

   public int getPermissionLevel() {
      return this.permissionLevel;
   }

   public UserListOpsEntry(JsonObject var1) {
      super(constructProfile(var1), var1);
      this.permissionLevel = var1.has("level") ? var1.get("level").getAsInt() : 0;
      this.bypassesPlayerLimit = var1.has("bypassesPlayerLimit") && var1.get("bypassesPlayerLimit").getAsBoolean();
   }

   @Override
   public void onSerialization(JsonObject var1) {
      if (this.getValue() != null) {
         var1.addProperty("uuid", this.getValue().getId() == null ? "" : this.getValue().getId().toString());
         var1.addProperty("name", this.getValue().getName());
         super.onSerialization(var1);
         var1.addProperty("level", this.permissionLevel);
         var1.addProperty("bypassesPlayerLimit", this.bypassesPlayerLimit);
      }
   }

   public boolean bypassesPlayerLimit() {
      return this.bypassesPlayerLimit;
   }

   public UserListOpsEntry(GameProfile var1, int var2, boolean var3) {
      super(var1);
      this.permissionLevel = var2;
      this.bypassesPlayerLimit = var3;
   }

   public static GameProfile constructProfile(JsonObject var0) {
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
}
