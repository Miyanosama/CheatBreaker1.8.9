package com.cheatbreaker.client.nethandler.obj;

import net.minecraft.client.gui.GuiLanguage;

public enum ServerRule {
   field_0007("legacyEnchanting", Boolean.class),
   field_0003("serverHandlesWaypoints", Boolean.class),
   field_0000("legacyCombat", Boolean.class),
   field_0005("minimapStatus", String.class),
   field_0002("voiceEnabled", Boolean.class),
   field_0009("competitiveGame", Boolean.class);
   public GuiLanguage field_0004;
   // $VF: synthetic field
   public static ServerRule[] field_0006 = new ServerRule[]{
      ServerRule.field_0002, ServerRule.field_0005, field_0003, ServerRule.field_0009, field_0007, ServerRule.field_0000
   };
   public String ruleName;
   public Class<?> value;

   public static ServerRule getRuleName(String var0) {
      ServerRule var1 = null;

      for (ServerRule var5 : values()) {
         if (var5.getRuleName().equals(var0)) {
            var1 = var5;
         }
      }

      return var1;
   }

   public ServerRule(String var3, Class<?> var4) {
      this.ruleName = var3;
      this.value = var4;
   }

   public Class<?> getValue() {
      return this.value;
   }

   public String getRuleName() {
      return this.ruleName;
   }
}
