package net.minecraft.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;

public class ChatStyle {
   public HoverEvent chatHoverEvent;
   public Boolean underlined;
   public EnumChatFormatting color;
   public ChatStyle parentStyle;
   public String insertion;
   public ClickEvent chatClickEvent;
   public Boolean obfuscated;
   public Boolean strikethrough;
   public Boolean bold;
   public Boolean italic;
   public static ChatStyle rootStyle = new ChatStyle() {
      @Override
      public String getFormattingCode() {
         return "";
      }

      @Override
      public String getInsertion() {
         return null;
      }

      @Override
      public ChatStyle setChatHoverEvent(HoverEvent var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ChatStyle createShallowCopy() {
         return this;
      }

      @Override
      public boolean getStrikethrough() {
         return false;
      }

      @Override
      public EnumChatFormatting getColor() {
         return null;
      }

      @Override
      public ChatStyle setParentStyle(ChatStyle var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ChatStyle setUnderlined(Boolean var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ChatStyle setChatClickEvent(ClickEvent var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public HoverEvent getChatHoverEvent() {
         return null;
      }

      @Override
      public boolean getUnderlined() {
         return false;
      }

      @Override
      public String toString() {
         return "Style.ROOT";
      }

      @Override
      public boolean getObfuscated() {
         return false;
      }

      @Override
      public ChatStyle setStrikethrough(Boolean var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean getItalic() {
         return false;
      }

      @Override
      public ChatStyle setItalic(Boolean var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ChatStyle setBold(Boolean var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ChatStyle createDeepCopy() {
         return this;
      }

      @Override
      public boolean getBold() {
         return false;
      }

      @Override
      public ClickEvent getChatClickEvent() {
         return null;
      }

      @Override
      public ChatStyle setColor(EnumChatFormatting var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ChatStyle setObfuscated(Boolean var1) {
         throw new UnsupportedOperationException();
      }
   };

   @Override
   public int hashCode() {
      int var1 = this.color.hashCode();
      var1 = 31 * var1 + this.bold.hashCode();
      var1 = 31 * var1 + this.italic.hashCode();
      var1 = 31 * var1 + this.underlined.hashCode();
      var1 = 31 * var1 + this.strikethrough.hashCode();
      var1 = 31 * var1 + this.obfuscated.hashCode();
      var1 = 31 * var1 + this.chatClickEvent.hashCode();
      var1 = 31 * var1 + this.chatHoverEvent.hashCode();
      return 31 * var1 + this.insertion.hashCode();
   }

   public ChatStyle createShallowCopy() {
      ChatStyle var1 = new ChatStyle();
      var1.bold = this.bold;
      var1.italic = this.italic;
      var1.strikethrough = this.strikethrough;
      var1.underlined = this.underlined;
      var1.obfuscated = this.obfuscated;
      var1.color = this.color;
      var1.chatClickEvent = this.chatClickEvent;
      var1.chatHoverEvent = this.chatHoverEvent;
      var1.parentStyle = this.parentStyle;
      var1.insertion = this.insertion;
      return var1;
   }

   @Override
   public String toString() {
      return "Style{hasParent="
         + (this.parentStyle != null)
         + ", color="
         + this.color
         + ", bold="
         + this.bold
         + ", italic="
         + this.italic
         + ", underlined="
         + this.underlined
         + ", obfuscated="
         + this.obfuscated
         + ", clickEvent="
         + this.getChatClickEvent()
         + ", hoverEvent="
         + this.getChatHoverEvent()
         + ", insertion="
         + this.getInsertion()
         + '}';
   }

   public ChatStyle setChatClickEvent(ClickEvent var1) {
      this.chatClickEvent = var1;
      return this;
   }

   public ChatStyle setInsertion(String var1) {
      this.insertion = var1;
      return this;
   }

   public HoverEvent getChatHoverEvent() {
      return this.chatHoverEvent == null ? this.getParent().getChatHoverEvent() : this.chatHoverEvent;
   }

   public ChatStyle setChatHoverEvent(HoverEvent var1) {
      this.chatHoverEvent = var1;
      return this;
   }

   public boolean isEmpty() {
      return this.bold == null
         && this.italic == null
         && this.strikethrough == null
         && this.underlined == null
         && this.obfuscated == null
         && this.color == null
         && this.chatClickEvent == null
         && this.chatHoverEvent == null;
   }

   public ChatStyle getParent() {
      return this.parentStyle == null ? rootStyle : this.parentStyle;
   }

   public EnumChatFormatting getColor() {
      return this.color == null ? this.getParent().getColor() : this.color;
   }

   public ChatStyle setBold(Boolean var1) {
      this.bold = var1;
      return this;
   }

   public boolean getStrikethrough() {
      return this.strikethrough == null ? this.getParent().getStrikethrough() : this.strikethrough;
   }

   public String getFormattingCode() {
      if (this.isEmpty()) {
         return this.parentStyle != null ? this.parentStyle.getFormattingCode() : "";
      } else {
         StringBuilder var1 = new StringBuilder();
         if (this.getColor() != null) {
            var1.append(this.getColor());
         }

         if (this.getBold()) {
            var1.append(EnumChatFormatting.BOLD);
         }

         if (this.getItalic()) {
            var1.append(EnumChatFormatting.ITALIC);
         }

         if (this.getUnderlined()) {
            var1.append(EnumChatFormatting.UNDERLINE);
         }

         if (this.getObfuscated()) {
            var1.append(EnumChatFormatting.OBFUSCATED);
         }

         if (this.getStrikethrough()) {
            var1.append(EnumChatFormatting.STRIKETHROUGH);
         }

         return var1.toString();
      }
   }

   public boolean getObfuscated() {
      return this.obfuscated == null ? this.getParent().getObfuscated() : this.obfuscated;
   }

   public boolean getUnderlined() {
      return this.underlined == null ? this.getParent().getUnderlined() : this.underlined;
   }

   public ChatStyle setStrikethrough(Boolean var1) {
      this.strikethrough = var1;
      return this;
   }

   public ChatStyle setObfuscated(Boolean var1) {
      this.obfuscated = var1;
      return this;
   }

   public ChatStyle createDeepCopy() {
      ChatStyle var1 = new ChatStyle();
      var1.setBold(this.getBold());
      var1.setItalic(this.getItalic());
      var1.setStrikethrough(this.getStrikethrough());
      var1.setUnderlined(this.getUnderlined());
      var1.setObfuscated(this.getObfuscated());
      var1.setColor(this.getColor());
      var1.setChatClickEvent(this.getChatClickEvent());
      var1.setChatHoverEvent(this.getChatHoverEvent());
      var1.setInsertion(this.getInsertion());
      return var1;
   }

   public ChatStyle setColor(EnumChatFormatting var1) {
      this.color = var1;
      return this;
   }

   public boolean getBold() {
      return this.bold == null ? this.getParent().getBold() : this.bold;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ChatStyle)) {
         return false;
      } else {
         ChatStyle var3 = (ChatStyle)var1;
         return this.getBold() == var3.getBold()
            && this.getColor() == var3.getColor()
            && this.getItalic() == var3.getItalic()
            && this.getObfuscated() == var3.getObfuscated()
            && this.getStrikethrough() == var3.getStrikethrough()
            && this.getUnderlined() == var3.getUnderlined()
            && (this.getChatClickEvent() != null ? this.getChatClickEvent().equals(var3.getChatClickEvent()) : var3.getChatClickEvent() == null)
            && (this.getChatHoverEvent() != null ? this.getChatHoverEvent().equals(var3.getChatHoverEvent()) : var3.getChatHoverEvent() == null)
            && (this.getInsertion() != null ? this.getInsertion().equals(var3.getInsertion()) : var3.getInsertion() == null);
      }
   }

   public ChatStyle setItalic(Boolean var1) {
      this.italic = var1;
      return this;
   }

   public boolean getItalic() {
      return this.italic == null ? this.getParent().getItalic() : this.italic;
   }

   public ChatStyle setParentStyle(ChatStyle var1) {
      this.parentStyle = var1;
      return this;
   }

   public String getInsertion() {
      return this.insertion == null ? this.getParent().getInsertion() : this.insertion;
   }

   public ClickEvent getChatClickEvent() {
      return this.chatClickEvent == null ? this.getParent().getChatClickEvent() : this.chatClickEvent;
   }

   public ChatStyle setUnderlined(Boolean var1) {
      this.underlined = var1;
      return this;
   }

   public static class Serializer implements JsonDeserializer<ChatStyle>, JsonSerializer<ChatStyle> {
      public ChatStyle deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws com.google.gson.JsonParseException {
         if (var1.isJsonObject()) {
            ChatStyle var4 = new ChatStyle();
            JsonObject var5 = var1.getAsJsonObject();
            if (var5 == null) {
               return null;
            } else {
               if (var5.has("bold")) {
                  var4.bold = var5.get("bold").getAsBoolean();
               }

               if (var5.has("italic")) {
                  var4.italic = var5.get("italic").getAsBoolean();
               }

               if (var5.has("underlined")) {
                  var4.underlined = var5.get("underlined").getAsBoolean();
               }

               if (var5.has("strikethrough")) {
                  var4.strikethrough = var5.get("strikethrough").getAsBoolean();
               }

               if (var5.has("obfuscated")) {
                  var4.obfuscated = var5.get("obfuscated").getAsBoolean();
               }

               if (var5.has("color")) {
                  var4.color = var3.deserialize(var5.get("color"), EnumChatFormatting.class);
               }

               if (var5.has("insertion")) {
                  var4.insertion = var5.get("insertion").getAsString();
               }

               if (var5.has("clickEvent")) {
                  JsonObject var6 = var5.getAsJsonObject("clickEvent");
                  if (var6 != null) {
                     JsonPrimitive var7 = var6.getAsJsonPrimitive("action");
                     ClickEvent.Action var8 = var7 == null ? null : ClickEvent.Action.getValueByCanonicalName(var7.getAsString());
                     JsonPrimitive var9 = var6.getAsJsonPrimitive("value");
                     String var10 = var9 == null ? null : var9.getAsString();
                     if (var8 != null && var10 != null && var8.shouldAllowInChat()) {
                        var4.chatClickEvent = new ClickEvent(var8, var10);
                     }
                  }
               }

               if (var5.has("hoverEvent")) {
                  JsonObject var11 = var5.getAsJsonObject("hoverEvent");
                  if (var11 != null) {
                     JsonPrimitive var12 = var11.getAsJsonPrimitive("action");
                     HoverEvent.Action var13 = var12 == null ? null : HoverEvent.Action.getValueByCanonicalName(var12.getAsString());
                     IChatComponent var14 = var3.deserialize(var11.get("value"), IChatComponent.class);
                     if (var13 != null && var14 != null && var13.shouldAllowInChat()) {
                        var4.chatHoverEvent = new HoverEvent(var13, var14);
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
            if (var1.bold != null) {
               var4.addProperty("bold", var1.bold);
            }

            if (var1.italic != null) {
               var4.addProperty("italic", var1.italic);
            }

            if (var1.underlined != null) {
               var4.addProperty("underlined", var1.underlined);
            }

            if (var1.strikethrough != null) {
               var4.addProperty("strikethrough", var1.strikethrough);
            }

            if (var1.obfuscated != null) {
               var4.addProperty("obfuscated", var1.obfuscated);
            }

            if (var1.color != null) {
               var4.add("color", var3.serialize(var1.color));
            }

            if (var1.insertion != null) {
               var4.add("insertion", var3.serialize(var1.insertion));
            }

            if (var1.chatClickEvent != null) {
               JsonObject var5 = new JsonObject();
               var5.addProperty("action", var1.chatClickEvent.getAction().getCanonicalName());
               var5.addProperty("value", var1.chatClickEvent.getValue());
               var4.add("clickEvent", var5);
            }

            if (var1.chatHoverEvent != null) {
               JsonObject var6 = new JsonObject();
               var6.addProperty("action", var1.chatHoverEvent.getAction().getCanonicalName());
               var6.add("value", var3.serialize(var1.chatHoverEvent.getValue()));
               var4.add("hoverEvent", var6);
            }

            return var4;
         }
      }
   }
}
