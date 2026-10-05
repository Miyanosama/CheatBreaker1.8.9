package com.cheatbreaker.client.ui.module;

public enum CBGuiAnchor {
      LEFT_TOP("LEFT_TOP"),
      LEFT_MIDDLE("LEFT_MIDDLE"),
      LEFT_BOTTOM("LEFT_BOTTOM"),
      MIDDLE_TOP("MIDDLE_TOP"),
      MIDDLE_MIDDLE("MIDDLE_MIDDLE"),
      MIDDLE_BOTTOM_LEFT("MIDDLE_BOTTOM_LEFT"),
      MIDDLE_BOTTOM_RIGHT("MIDDLE_BOTTOM_RIGHT"),
      RIGHT_TOP("RIGHT_TOP"),
      RIGHT_MIDDLE("RIGHT_MIDDLE"),
      RIGHT_BOTTOM("RIGHT_BOTTOM");
   public String label;
   public static CBGuiAnchor[] recoveredField1616 = new CBGuiAnchor[]{
      LEFT_TOP,
      CBGuiAnchor.LEFT_MIDDLE,
      LEFT_BOTTOM,
      MIDDLE_TOP,
      MIDDLE_MIDDLE,
      MIDDLE_BOTTOM_LEFT,
      CBGuiAnchor.MIDDLE_BOTTOM_RIGHT,
      RIGHT_TOP,
      CBGuiAnchor.RIGHT_MIDDLE,
      RIGHT_BOTTOM
   };

   CBGuiAnchor(String var3) {
      this.label = var3;
   }

   public String getLabel() {
      return this.label;
   }
}
