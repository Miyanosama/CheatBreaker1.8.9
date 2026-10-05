package net.minecraft.server.management;

import com.google.gson.JsonObject;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachTransformedEntryTask;
import java.io.File;
import java.net.SocketAddress;
import net.minecraft.client.model.ModelZombieVillager;

public class BanList extends UserList<String, IPBanEntry> {
   public ModelZombieVillager field_0000;
   public ConcurrentHashMapV8$ForEachTransformedEntryTask field_0001;

   public BanList(File var1) {
      super(var1);
   }

   @Override
   public UserListEntry<String> createEntry(JsonObject var1) {
      return new IPBanEntry(var1);
   }

   public IPBanEntry getBanEntry(SocketAddress var1) {
      String var2 = this.addressToString(var1);
      return this.getEntry(var2);
   }

   public String addressToString(SocketAddress var1) {
      String var2 = var1.toString();
      if (var2.contains("/")) {
         var2 = var2.substring(var2.indexOf(47) + 1);
      }

      if (var2.contains(":")) {
         var2 = var2.substring(0, var2.indexOf(58));
      }

      return var2;
   }

   public boolean isBanned(SocketAddress var1) {
      String var2 = this.addressToString(var1);
      return this.hasEntry(var2);
   }
}
