package net.minecraft.entity.player;

public enum EntityPlayer$EnumChatVisibility {
   FULL(0, "options.chat.visibility.full"),
   SYSTEM(1, "options.chat.visibility.system"),
   HIDDEN(2, "options.chat.visibility.hidden");

   public static EntityPlayer$EnumChatVisibility[] ID_LOOKUP = new EntityPlayer$EnumChatVisibility[values().length];
   // $VF: synthetic field
   public static EntityPlayer$EnumChatVisibility[] $VALUES = new EntityPlayer$EnumChatVisibility[]{FULL, SYSTEM, EntityPlayer$EnumChatVisibility.HIDDEN};
   public int chatVisibility;
   public String resourceKey;

   public int getChatVisibility() {
      return this.chatVisibility;
   }

   public String getResourceKey() {
      return this.resourceKey;
   }

   static {
      for (EntityPlayer$EnumChatVisibility var3 : values()) {
         ID_LOOKUP[var3.chatVisibility] = var3;
      }
   }

   public static EntityPlayer$EnumChatVisibility getEnumChatVisibility(int var0) {
      return ID_LOOKUP[var0 % ID_LOOKUP.length];
   }

   public EntityPlayer$EnumChatVisibility(int var3, String var4) {
      this.chatVisibility = var3;
      this.resourceKey = var4;
   }
}
