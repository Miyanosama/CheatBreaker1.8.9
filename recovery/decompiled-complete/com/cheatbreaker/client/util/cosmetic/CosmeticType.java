package com.cheatbreaker.client.util.cosmetic;

import net.optifine.entity.model.ModelAdapterBat;

public enum CosmeticType {
   field_0003("dragon_wings"),
   field_0006("cape"),
   field_0002("emote"),
   field_0005("subicon"),
   field_0001("icon");

   // $VF: synthetic field
   public static CosmeticType[] field_0000 = new CosmeticType[]{field_0003, field_0006, field_0002, CosmeticType.field_0001, field_0005};
   public ModelAdapterBat field_0007;
   public String field_0004;

   public static CosmeticType method_00486(String var0) {
      for (CosmeticType var4 : values()) {
         if (var4.method_00485().equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   public String method_00485() {
      return this.field_0004;
   }

   public static CosmeticType method_00487(String var0) {
      return Enum.valueOf(CosmeticType.class, var0);
   }

   public CosmeticType(String var3) {
      this.field_0004 = var3;
   }
}
