package net.minecraft.util;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import io.netty.handler.codec.spdy.SpdyHeaders$HttpNames;
import java.util.Set;
import recovered.unidentified.UnidentifiedClass3379;

public class JsonSerializableSet extends ForwardingSet<String> implements IJsonSerializable {
   public SpdyHeaders$HttpNames field_0001;
   public UnidentifiedClass3379 field_0002;
   public Set<String> underlyingSet = Sets.newHashSet();

   public Set<String> delegate() {
      return this.underlyingSet;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.isJsonArray()) {
         for (JsonElement var3 : var1.getAsJsonArray()) {
            this.add(var3.getAsString());
         }
      }
   }

   @Override
   public JsonElement getSerializableElement() {
      JsonArray var1 = new JsonArray();

      for (String var3 : this) {
         var1.add(new JsonPrimitive(var3));
      }

      return var1;
   }
}
