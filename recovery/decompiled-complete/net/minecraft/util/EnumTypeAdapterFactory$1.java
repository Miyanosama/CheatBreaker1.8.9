package net.minecraft.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesTask;
import java.util.Map;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.world.chunk.ChunkPrimer;

public class EnumTypeAdapterFactory$1 extends TypeAdapter<T> {
   public ModelEnderCrystal field_0002;
   public ConcurrentHashMapV8$MapReduceEntriesTask field_0001;
   public ChunkPrimer field_0003;

   public T read(JsonReader var1) {
      if (var1.peek() == JsonToken.NULL) {
         var1.nextNull();
         return null;
      } else {
         return (T)this.field_151231_a.get(var1.nextString());
      }
   }

   public EnumTypeAdapterFactory$1(EnumTypeAdapterFactory var1, Map var2) {
      this.field_151230_b = var1;
      this.field_151231_a = var2;
      super();
   }

   public void write(JsonWriter var1, T var2) {
      if (var2 == null) {
         var1.nullValue();
      } else {
         var1.value(EnumTypeAdapterFactory.access$000(this.field_151230_b, var2));
      }
   }
}
