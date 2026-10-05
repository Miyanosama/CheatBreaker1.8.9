package net.minecraft.event;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.IChatComponent;

public class HoverEvent {
   public IChatComponent value;
   public HoverEvent.Action action;

   @Override
   public int hashCode() {
      int var1 = this.action.hashCode();
      return 31 * var1 + (this.value != null ? this.value.hashCode() : 0);
   }

   public HoverEvent.Action getAction() {
      return this.action;
   }

   @Override
   public String toString() {
      return "HoverEvent{action=" + this.action + ", value='" + this.value + '\'' + '}';
   }

   public HoverEvent(HoverEvent.Action var1, IChatComponent var2) {
      this.action = var1;
      this.value = var2;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         HoverEvent var2 = (HoverEvent)var1;
         if (this.action != var2.action) {
            return false;
         } else {
            if (this.value != null) {
               if (!this.value.equals(var2.value)) {
                  return false;
               }
            } else if (var2.value != null) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public IChatComponent getValue() {
      return this.value;
   }

   public static enum Action {
      SHOW_TEXT("show_text", true),
      SHOW_ACHIEVEMENT("show_achievement", true),
      SHOW_ITEM("show_item", true),
      SHOW_ENTITY("show_entity", true);

      public static Map<String, HoverEvent.Action> nameMapping = Maps.newHashMap();
      public String canonicalName;
      public boolean allowedInChat;

      Action(String var3, boolean var4) {
         this.canonicalName = var3;
         this.allowedInChat = var4;
      }

      public boolean shouldAllowInChat() {
         return this.allowedInChat;
      }

      public static HoverEvent.Action getValueByCanonicalName(String var0) {
         return nameMapping.get(var0);
      }

      public String getCanonicalName() {
         return this.canonicalName;
      }

      static {
         for (HoverEvent.Action var3 : values()) {
            nameMapping.put(var3.getCanonicalName(), var3);
         }
      }
   }
}
