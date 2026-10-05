package net.minecraft.server.management;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.netty.buffer.ReadOnlyByteBufferBuf;
import io.netty.channel.EventLoopException;
import io.netty.channel.embedded.EmbeddedEventLoop;
import java.lang.reflect.Type;
import net.optifine.shaders.config.ShaderPackParser$1;
import org.apache.log4j.LogSF;

public class UserList$Serializer implements JsonDeserializer<UserListEntry<K>>, JsonSerializer<UserListEntry<K>> {
   public EventLoopException field_0005;
   public LogSF field_0002;
   public ShaderPackParser$1 field_0004;
   public ReadOnlyByteBufferBuf field_0000;
   public EmbeddedEventLoop field_0001;

   public UserList$Serializer(UserList var1) {
      this.field_152752_a = var1;
      super();
   }

   public UserListEntry<K> deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if (var1.isJsonObject()) {
         JsonObject var4 = var1.getAsJsonObject();
         return this.field_152752_a.createEntry(var4);
      } else {
         return null;
      }
   }

   public JsonElement serialize(UserListEntry<K> var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var1.onSerialization(var4);
      return var4;
   }
}
