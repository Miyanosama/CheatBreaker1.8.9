package com.cheatbreaker.client.util.cosmetic;

public enum CosmeticType {
      WINGS("dragon_wings"),
      CAPE("cape"),
      EMOTE("emote"),
      ICON("icon"),
      SUBICON("subicon");

   public static CosmeticType[] recoveredField3211 = new CosmeticType[]{
      WINGS, CAPE, EMOTE, CosmeticType.ICON, SUBICON
   };
   public String recoveredField3213;

   public static CosmeticType method_00486(String var0) {
      for (CosmeticType var4 : values()) {
         if (var4.method_00485().equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   public String method_00485() {
      return this.recoveredField3213;
   }

   public static CosmeticType method_00487(String var0) {
      return Enum.valueOf(CosmeticType.class, var0);
   }

   CosmeticType(String var3) {
      this.recoveredField3213 = var3;
   }
}
