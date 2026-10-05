package net.minecraft.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Map.Entry;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayer$1;
import net.minecraft.client.particle.EntitySnowShovelFX$Factory;
import net.minecraft.inventory.ContainerRepair$1;
import org.slf4j.helpers.MarkerIgnoringBase;
import recovered.unidentified.UnidentifiedClass1509;

public class IChatComponent$Serializer implements JsonDeserializer<IChatComponent>, JsonSerializer<IChatComponent> {
   public EntitySnowShovelFX$Factory field_0003;
   public TeleportToPlayer$1 field_0005;
   public static Gson GSON;
   public UnidentifiedClass1509 field_0004;
   public MarkerIgnoringBase field_0000;
   public ContainerRepair$1 field_0001;

   public void serializeChatStyle(ChatStyle var1, JsonObject var2, JsonSerializationContext var3) {
      JsonElement var4 = var3.serialize(var1);
      if (var4.isJsonObject()) {
         JsonObject var5 = (JsonObject)var4;

         for (Entry var7 : var5.entrySet()) {
            var2.add((String)var7.getKey(), (JsonElement)var7.getValue());
         }
      }
   }

   static {
      GsonBuilder var0 = new GsonBuilder();
      var0.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent$Serializer());
      var0.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle$Serializer());
      var0.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
      GSON = var0.create();
   }

   public static String componentToJson(IChatComponent var0) {
      return GSON.toJson(var0);
   }

   public JsonElement serialize(IChatComponent var1, Type var2, JsonSerializationContext var3) {
      if (var1 instanceof ChatComponentText && var1.getChatStyle().isEmpty() && var1.getSiblings().isEmpty()) {
         return new JsonPrimitive(((ChatComponentText)var1).method_07470());
      } else {
         JsonObject var4 = new JsonObject();
         if (!var1.getChatStyle().isEmpty()) {
            this.serializeChatStyle(var1.getChatStyle(), var4, var3);
         }

         if (!var1.getSiblings().isEmpty()) {
            JsonArray var5 = new JsonArray();

            for (IChatComponent var7 : var1.getSiblings()) {
               var5.add(this.serialize(var7, var7.getClass(), var3));
            }

            var4.add("extra", var5);
         }

         if (var1 instanceof ChatComponentText) {
            var4.addProperty("text", ((ChatComponentText)var1).method_07470());
         } else if (var1 instanceof ChatComponentTranslation) {
            ChatComponentTranslation var11 = (ChatComponentTranslation)var1;
            var4.addProperty("translate", var11.getKey());
            if (var11.getFormatArgs() != null && var11.getFormatArgs().length > 0) {
               JsonArray var14 = new JsonArray();

               for (Object var10 : var11.getFormatArgs()) {
                  if (var10 instanceof IChatComponent) {
                     var14.add(this.serialize((IChatComponent)var10, var10.getClass(), var3));
                  } else {
                     var14.add(new JsonPrimitive(String.valueOf(var10)));
                  }
               }

               var4.add("with", var14);
            }
         } else if (var1 instanceof ChatComponentScore) {
            ChatComponentScore var12 = (ChatComponentScore)var1;
            JsonObject var15 = new JsonObject();
            var15.addProperty("name", var12.getName());
            var15.addProperty("objective", var12.getObjective());
            var15.addProperty("value", var12.getUnformattedTextForChat());
            var4.add("score", var15);
         } else {
            if (!(var1 instanceof ChatComponentSelector)) {
               throw new IllegalArgumentException("Don't know how to serialize " + var1 + " as a Component");
            }

            ChatComponentSelector var13 = (ChatComponentSelector)var1;
            var4.addProperty("selector", var13.method_29641());
         }

         return var4;
      }
   }

   public static IChatComponent jsonToComponent(String var0) {
      return (IChatComponent)GSON.fromJson(var0, IChatComponent.class);
   }

   public IChatComponent deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if (var1.isJsonPrimitive()) {
         return new ChatComponentText(var1.getAsString());
      } else if (!var1.isJsonObject()) {
         if (var1.isJsonArray()) {
            JsonArray var11 = var1.getAsJsonArray();
            IChatComponent var12 = null;

            for (JsonElement var17 : var11) {
               IChatComponent var18 = this.deserialize(var17, var17.getClass(), var3);
               if (var12 == null) {
                  var12 = var18;
               } else {
                  var12.appendSibling(var18);
               }
            }

            return var12;
         } else {
            throw new JsonParseException("Don't know how to turn " + var1.toString() + " into a Component");
         }
      } else {
         JsonObject var4 = var1.getAsJsonObject();
         Object var5;
         if (var4.has("text")) {
            var5 = new ChatComponentText(var4.get("text").getAsString());
         } else if (var4.has("translate")) {
            String var6 = var4.get("translate").getAsString();
            if (var4.has("with")) {
               JsonArray var7 = var4.getAsJsonArray("with");
               Object[] var8 = new Object[var7.size()];

               for (int var9 = 0; var9 < var8.length; var9++) {
                  var8[var9] = this.deserialize(var7.get(var9), var2, var3);
                  if (var8[var9] instanceof ChatComponentText) {
                     ChatComponentText var10 = (ChatComponentText)var8[var9];
                     if (var10.getChatStyle().isEmpty() && var10.getSiblings().isEmpty()) {
                        var8[var9] = var10.method_07470();
                     }
                  }
               }

               var5 = new ChatComponentTranslation(var6, var8);
            } else {
               var5 = new ChatComponentTranslation(var6);
            }
         } else if (var4.has("score")) {
            JsonObject var13 = var4.getAsJsonObject("score");
            if (!var13.has("name") || !var13.has("objective")) {
               throw new JsonParseException("A score component needs a least a name and an objective");
            }

            var5 = new ChatComponentScore(JsonUtils.getString(var13, "name"), JsonUtils.getString(var13, "objective"));
            if (var13.has("value")) {
               ((ChatComponentScore)var5).setValue(JsonUtils.getString(var13, "value"));
            }
         } else {
            if (!var4.has("selector")) {
               throw new JsonParseException("Don't know how to turn " + var1.toString() + " into a Component");
            }

            var5 = new ChatComponentSelector(JsonUtils.getString(var4, "selector"));
         }

         if (var4.has("extra")) {
            JsonArray var14 = var4.getAsJsonArray("extra");
            if (var14.size() <= 0) {
               throw new JsonParseException("Unexpected empty array of components");
            }

            for (int var16 = 0; var16 < var14.size(); var16++) {
               ((IChatComponent)var5).appendSibling(this.deserialize(var14.get(var16), var2, var3));
            }
         }

         ((IChatComponent)var5).setChatStyle((ChatStyle)var3.deserialize(var1, ChatStyle.class));
         return (IChatComponent)var5;
      }
   }
}
