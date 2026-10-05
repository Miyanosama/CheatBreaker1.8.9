package net.minecraft.server.management;

import com.google.gson.JsonObject;

public class UserListEntry<T> {
   public T value;

   public T getValue() {
      return this.value;
   }

   public UserListEntry(T var1) {
      this.value = (T)var1;
   }

   public boolean hasBanExpired() {
      return false;
   }

   public void onSerialization(JsonObject var1) {
   }

   public UserListEntry(T var1, JsonObject var2) {
      this.value = (T)var1;
   }
}
