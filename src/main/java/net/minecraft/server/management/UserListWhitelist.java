package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class UserListWhitelist extends UserList<GameProfile, UserListWhitelistEntry> {
   public UserListWhitelist(File var1) {
      super(var1);
   }

   @Override
   public String[] getKeys() {
      String[] var1 = new String[this.getValues().size()];
      int var2 = 0;

      for (UserListWhitelistEntry var4 : this.getValues().values()) {
         var1[var2++] = var4.getValue().getName();
      }

      return var1;
   }

   public String getObjectKey(GameProfile var1) {
      return var1.getId().toString();
   }

   public GameProfile getBannedProfile(String var1) {
      for (UserListWhitelistEntry var3 : this.getValues().values()) {
         if (var1.equalsIgnoreCase(var3.getValue().getName())) {
            return var3.getValue();
         }
      }

      return null;
   }

   @Override
   public UserListEntry<GameProfile> createEntry(JsonObject var1) {
      return new UserListWhitelistEntry(var1);
   }
}
