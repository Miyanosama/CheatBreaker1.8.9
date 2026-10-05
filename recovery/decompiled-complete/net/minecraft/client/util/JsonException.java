package net.minecraft.client.util;

import com.google.common.collect.Lists;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.stream.BroadcastController$2;
import net.minecraft.network.play.server.S14PacketEntity$S15PacketEntityRelMove;
import org.apache.log4j.chainsaw.ControlPanel$2;

public class JsonException extends IOException {
   public BroadcastController$2 field_0003;
   public ObjectEncoderOutputStream field_0005;
   public List<JsonException$Entry> field_151383_a = Lists.newArrayList();
   public String exceptionMessage;
   public S14PacketEntity$S15PacketEntityRelMove field_0000;
   public ControlPanel$2 field_0001;

   public JsonException(String var1, Throwable var2) {
      super(var2);
      this.field_151383_a.add(new JsonException$Entry(null));
      this.exceptionMessage = var1;
   }

   public JsonException(String var1) {
      this.field_151383_a.add(new JsonException$Entry(null));
      this.exceptionMessage = var1;
   }

   public void func_151380_a(String var1) {
      JsonException$Entry.access$100(this.field_151383_a.get(0), var1);
   }

   @Override
   public String getMessage() {
      return "Invalid " + this.field_151383_a.get(this.field_151383_a.size() - 1).toString() + ": " + this.exceptionMessage;
   }

   public static JsonException func_151379_a(Exception var0) {
      if (var0 instanceof JsonException) {
         return (JsonException)var0;
      } else {
         String var1 = var0.getMessage();
         if (var0 instanceof FileNotFoundException) {
            var1 = "File not found";
         }

         return new JsonException(var1, var0);
      }
   }

   public void func_151381_b(String var1) {
      JsonException$Entry.access$202(this.field_151383_a.get(0), var1);
      this.field_151383_a.add(0, new JsonException$Entry(null));
   }
}
