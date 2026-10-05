package net.minecraft.event;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.gui.inventory.GuiBeacon$CancelButton;
import net.minecraft.entity.item.EntityFallingBlock;

public enum ClickEvent$Action {
   field_0011("open_file", false),
   field_0005("run_command", true),
   OPEN_URL("open_url", true),
   field_0012("twitch_user_info", false),
   field_0003("upload_screenshot", false),
   field_0007("change_page", true),
   SUGGEST_COMMAND("suggest_command", true),
   field_0004("copy_screenshot", false);
   public EntityFallingBlock field_0006;
   public String canonicalName;
   public boolean allowedInChat;
   public static Map<String, ClickEvent$Action> nameMapping = Maps.newHashMap();
   // $VF: synthetic field
   public static ClickEvent$Action[] field_0013 = new ClickEvent$Action[]{
      OPEN_URL, field_0011, field_0005, field_0012, ClickEvent$Action.SUGGEST_COMMAND, ClickEvent$Action.field_0007, field_0003, ClickEvent$Action.field_0004
   };
   public GuiBeacon$CancelButton field_0000;

   public ClickEvent$Action(String var3, boolean var4) {
      this.canonicalName = var3;
      this.allowedInChat = var4;
   }

   public String getCanonicalName() {
      return this.canonicalName;
   }

   public static ClickEvent$Action getValueByCanonicalName(String var0) {
      return nameMapping.get(var0);
   }

   public boolean shouldAllowInChat() {
      return this.allowedInChat;
   }

   static {
      for (ClickEvent$Action var3 : values()) {
         nameMapping.put(var3.getCanonicalName(), var3);
      }
   }
}
