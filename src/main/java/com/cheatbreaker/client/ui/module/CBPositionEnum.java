package com.cheatbreaker.client.ui.module;

public enum CBPositionEnum {
      BOTTOM("BOTTOM"),
      TOP("TOP"),
      CENTER("CENTER"),
      LEFT("LEFT"),
      RIGHT("RIGHT");
   public static CBPositionEnum[] recoveredField2540 = new CBPositionEnum[]{
      CBPositionEnum.BOTTOM, CBPositionEnum.TOP, CBPositionEnum.CENTER, CBPositionEnum.LEFT, CBPositionEnum.RIGHT
   };
   public String identifier;

   CBPositionEnum(String var3) {
      this.identifier = var3;
   }

   public String getIdentifier() {
      return this.identifier;
   }
}
