package net.minecraft.server.management;

import com.google.gson.JsonObject;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$RoomCrossing;
import org.apache.log4j.LogSF;
import recovered.unidentified.UnidentifiedClass1605;

public class UserListEntry<T> {
   public T value;
   public LogSF field_0003;
   public StructureStrongholdPieces$RoomCrossing field_0000;
   public UnidentifiedClass1605 field_0001;

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
