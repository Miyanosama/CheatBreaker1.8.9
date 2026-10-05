package net.minecraft.event;

import com.google.common.collect.Maps;
import com.jagrosh.discordipc.IPCClient;
import java.util.Map;
import javazoom.jl.decoder.Decoder$Params;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.item.EntityMinecartEmpty;

public enum HoverEvent$Action {
   SHOW_ITEM("show_item", true),
   SHOW_TEXT("show_text", true),
   SHOW_ENTITY("show_entity", true),
   SHOW_ACHIEVEMENT("show_achievement", true);

   public IPCClient field_0005;
   public BlockBed field_0008;
   public Decoder$Params field_0001;
   public static Map<String, HoverEvent$Action> nameMapping = Maps.newHashMap();
   public String canonicalName;
   public EntityMinecartEmpty field_0007;
   public boolean allowedInChat;

   public HoverEvent$Action(String var3, boolean var4) {
      this.canonicalName = var3;
      this.allowedInChat = var4;
   }

   public boolean shouldAllowInChat() {
      return this.allowedInChat;
   }

   public static HoverEvent$Action getValueByCanonicalName(String var0) {
      return nameMapping.get(var0);
   }

   public String getCanonicalName() {
      return this.canonicalName;
   }

   static {
      for (HoverEvent$Action var3 : values()) {
         nameMapping.put(var3.getCanonicalName(), var3);
      }
   }
}
