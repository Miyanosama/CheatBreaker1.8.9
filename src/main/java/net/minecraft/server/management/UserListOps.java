package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class UserListOps extends UserList<GameProfile, UserListOpsEntry> {
   public UserListOps(File var1) {
      super(var1);
   }

   public GameProfile getGameProfileFromName(String var1) {
      for (UserListOpsEntry var3 : this.getValues().values()) {
         if (var1.equalsIgnoreCase(var3.getValue().getName())) {
            return var3.getValue();
         }
      }

      return null;
   }

   public String getObjectKey(GameProfile var1) {
      return var1.getId().toString();
   }

   @Override
   public String[] getKeys() {
      String[] var1 = new String[this.getValues().size()];
      int var2 = 0;

      for (UserListOpsEntry var4 : this.getValues().values()) {
         var1[var2++] = var4.getValue().getName();
      }

      return var1;
   }

   @Override
   public UserListEntry<GameProfile> createEntry(JsonObject var1) {
      return new UserListOpsEntry(var1);
   }

   public boolean bypassesPlayerLimit(GameProfile var1) {
      UserListOpsEntry var2 = this.getEntry(var1);
      return var2 != null ? var2.bypassesPlayerLimit() : false;
   }
}
