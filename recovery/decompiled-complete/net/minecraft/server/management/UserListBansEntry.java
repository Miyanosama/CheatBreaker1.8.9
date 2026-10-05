package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.UUID;
import net.minecraft.client.gui.GuiPageButtonList;

public class UserListBansEntry extends BanEntry<GameProfile> {
   public GuiPageButtonList field_0000;

   public UserListBansEntry(GameProfile var1, Date var2, String var3, Date var4, String var5) {
      super(var1, var4, var3, var4, var5);
   }

   public static GameProfile toGameProfile(JsonObject var0) {
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

   @Override
   public void onSerialization(JsonObject var1) {
      if (this.getValue() != null) {
         var1.addProperty("uuid", this.getValue().getId() == null ? "" : this.getValue().getId().toString());
         var1.addProperty("name", this.getValue().getName());
         super.onSerialization(var1);
      }
   }

   public UserListBansEntry(JsonObject var1) {
      super(toGameProfile(var1), var1);
   }

   public UserListBansEntry(GameProfile var1) {
      this(var1, (Date)null, (String)null, (Date)null, (String)null);
   }
}
