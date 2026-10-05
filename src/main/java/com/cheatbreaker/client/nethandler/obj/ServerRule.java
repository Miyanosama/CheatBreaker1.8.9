package com.cheatbreaker.client.nethandler.obj;

public enum ServerRule {
      VOICE_ENABLED("voiceEnabled", Boolean.class),
      MINIMAP_STATUS("minimapStatus", String.class),
      SERVER_HANDLES_WAYPOINTS("serverHandlesWaypoints", Boolean.class),
      COMPETITIVE_GAMEMODE("competitiveGame", Boolean.class),
      LEGACY_ENCHANTING("legacyEnchanting", Boolean.class),
      LEGACY_COMBAT("legacyCombat", Boolean.class);
   public static ServerRule[] recoveredField2370 = new ServerRule[]{
      ServerRule.VOICE_ENABLED,
      ServerRule.MINIMAP_STATUS,
      SERVER_HANDLES_WAYPOINTS,
      ServerRule.COMPETITIVE_GAMEMODE,
      LEGACY_ENCHANTING,
      ServerRule.LEGACY_COMBAT
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

   ServerRule(String var3, Class<?> var4) {
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
