package net.minecraft.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.netty.channel.FixedRecvByteBufAllocator$HandleImpl;
import io.netty.handler.codec.spdy.DefaultSpdyHeaders$1;
import java.lang.reflect.Type;
import net.minecraft.client.resources.ResourcePackListEntry$1;
import net.minecraft.entity.monster.IMob$2;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent$Action;
import recovered.unidentified.UnidentifiedClass4667;

public class ChatStyle$Serializer implements JsonDeserializer<ChatStyle>, JsonSerializer<ChatStyle> {
   public ResourcePackListEntry$1 field_0002;
   public IMob$2 field_0004;
   public UnidentifiedClass4667 field_0001;
   public DefaultSpdyHeaders$1 field_0003;
   public FixedRecvByteBufAllocator$HandleImpl field_0000;

   public ChatStyle deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if (var1.isJsonObject()) {
         ChatStyle var4 = new ChatStyle();
         JsonObject var5 = var1.getAsJsonObject();
         if (var5 == null) {
            return null;
         } else {
            if (var5.has("bold")) {
               ChatStyle.access$002(var4, var5.get("bold").getAsBoolean());
            }

            if (var5.has("italic")) {
               ChatStyle.access$102(var4, var5.get("italic").getAsBoolean());
            }

            if (var5.has("underlined")) {
               ChatStyle.access$202(var4, var5.get("underlined").getAsBoolean());
            }

            if (var5.has("strikethrough")) {
               ChatStyle.access$302(var4, var5.get("strikethrough").getAsBoolean());
            }

            if (var5.has("obfuscated")) {
               ChatStyle.access$402(var4, var5.get("obfuscated").getAsBoolean());
            }

            if (var5.has("color")) {
               ChatStyle.access$502(var4, (EnumChatFormatting)var3.deserialize(var5.get("color"), EnumChatFormatting.class));
            }

            if (var5.has("insertion")) {
               ChatStyle.access$602(var4, var5.get("insertion").getAsString());
            }

            if (var5.has("clickEvent")) {
               JsonObject var6 = var5.getAsJsonObject("clickEvent");
               if (var6 != null) {
                  JsonPrimitive var7 = var6.getAsJsonPrimitive("action");
                  ClickEvent$Action var8 = var7 == null ? null : ClickEvent$Action.getValueByCanonicalName(var7.getAsString());
                  JsonPrimitive var9 = var6.getAsJsonPrimitive("value");
                  String var10 = var9 == null ? null : var9.getAsString();
                  if (var8 != null && var10 != null && var8.shouldAllowInChat()) {
                     ChatStyle.access$702(var4, new ClickEvent(var8, var10));
                  }
               }
            }

            if (var5.has("hoverEvent")) {
               JsonObject var11 = var5.getAsJsonObject("hoverEvent");
               if (var11 != null) {
                  JsonPrimitive var12 = var11.getAsJsonPrimitive("action");
                  HoverEvent$Action var13 = var12 == null ? null : HoverEvent$Action.getValueByCanonicalName(var12.getAsString());
                  IChatComponent var14 = (IChatComponent)var3.deserialize(var11.get("value"), IChatComponent.class);
                  if (var13 != null && var14 != null && var13.shouldAllowInChat()) {
                     ChatStyle.access$802(var4, new HoverEvent(var13, var14));
                  }
               }
            }

            return var4;
         }
      } else {
         return null;
      }
   }

   public JsonElement serialize(ChatStyle var1, Type var2, JsonSerializationContext var3) {
      if (var1.isEmpty()) {
         return null;
      } else {
         JsonObject var4 = new JsonObject();
         if (ChatStyle.access$000(var1) != null) {
            var4.addProperty("bold", ChatStyle.access$000(var1));
         }

         if (ChatStyle.access$100(var1) != null) {
            var4.addProperty("italic", ChatStyle.access$100(var1));
         }

         if (ChatStyle.access$200(var1) != null) {
            var4.addProperty("underlined", ChatStyle.access$200(var1));
         }

         if (ChatStyle.access$300(var1) != null) {
            var4.addProperty("strikethrough", ChatStyle.access$300(var1));
         }

         if (ChatStyle.access$400(var1) != null) {
            var4.addProperty("obfuscated", ChatStyle.access$400(var1));
         }

         if (ChatStyle.access$500(var1) != null) {
            var4.add("color", var3.serialize(ChatStyle.access$500(var1)));
         }

         if (ChatStyle.access$600(var1) != null) {
            var4.add("insertion", var3.serialize(ChatStyle.access$600(var1)));
         }

         if (ChatStyle.access$700(var1) != null) {
            JsonObject var5 = new JsonObject();
            var5.addProperty("action", ChatStyle.access$700(var1).getAction().getCanonicalName());
            var5.addProperty("value", ChatStyle.access$700(var1).getValue());
            var4.add("clickEvent", var5);
         }

         if (ChatStyle.access$800(var1) != null) {
            JsonObject var6 = new JsonObject();
            var6.addProperty("action", ChatStyle.access$800(var1).getAction().getCanonicalName());
            var6.add("value", var3.serialize(ChatStyle.access$800(var1).getValue()));
            var4.add("hoverEvent", var6);
         }

         return var4;
      }
   }
}
