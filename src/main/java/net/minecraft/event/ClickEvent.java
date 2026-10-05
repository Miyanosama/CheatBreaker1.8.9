package net.minecraft.event;

import com.google.common.collect.Maps;
import java.util.Map;

public class ClickEvent {
   public ClickEvent.Action action;
   public String value;

   @Override
   public int hashCode() {
      int var1 = this.action.hashCode();
      return 31 * var1 + (this.value != null ? this.value.hashCode() : 0);
   }

   public String getValue() {
      return this.value;
   }

   public ClickEvent.Action getAction() {
      return this.action;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ClickEvent var2 = (ClickEvent)var1;
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

   public ClickEvent(ClickEvent.Action var1, String var2) {
      this.action = var1;
      this.value = var2;
   }

   @Override
   public String toString() {
      return "ClickEvent{action=" + this.action + ", value='" + this.value + '\'' + '}';
   }

   public static enum Action {
      OPEN_URL("open_url", true),
      OPEN_FILE("open_file", false),
      RUN_COMMAND("run_command", true),
      TWITCH_USER_INFO("twitch_user_info", false),
      SUGGEST_COMMAND("suggest_command", true),
      CHANGE_PAGE("change_page", true),
      UPLOAD_SCREENSHOT("upload_screenshot", false),
      COPY_SCREENSHOT("copy_screenshot", false);
      public String canonicalName;
      public boolean allowedInChat;
      // $VF: synthetic field
      public static ClickEvent.Action[] recoveredField103 = new ClickEvent.Action[]{
         OPEN_URL,
         OPEN_FILE,
         RUN_COMMAND,
         TWITCH_USER_INFO,
         ClickEvent.Action.SUGGEST_COMMAND,
         ClickEvent.Action.CHANGE_PAGE,
         UPLOAD_SCREENSHOT,
         ClickEvent.Action.COPY_SCREENSHOT
      };
      public static Map<String, ClickEvent.Action> nameMapping = Maps.newHashMap();

      Action(String var3, boolean var4) {
         this.canonicalName = var3;
         this.allowedInChat = var4;
      }

      public String getCanonicalName() {
         return this.canonicalName;
      }

      public static ClickEvent.Action getValueByCanonicalName(String var0) {
         return nameMapping.get(var0);
      }

      public boolean shouldAllowInChat() {
         return this.allowedInChat;
      }

      static {
         for (ClickEvent.Action var3 : values()) {
            nameMapping.put(var3.getCanonicalName(), var3);
         }
      }
   }
}
