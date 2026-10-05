package com.cheatbreaker.client.ui.module;

import net.minecraft.block.BlockGlass;

public enum CBGuiAnchor {
   MIDDLE_BOTTOM_LEFT("MIDDLE_BOTTOM_LEFT"),
   RIGHT_BOTTOM("RIGHT_BOTTOM"),
   MIDDLE_MIDDLE("MIDDLE_MIDDLE"),
   LEFT_TOP("LEFT_TOP"),
   MIDDLE_TOP("MIDDLE_TOP"),
   LEFT_BOTTOM("LEFT_BOTTOM"),
   RIGHT_TOP("RIGHT_TOP"),
   RIGHT_MIDDLE("RIGHT_MIDDLE"),
   LEFT_MIDDLE("LEFT_MIDDLE"),
   MIDDLE_BOTTOM_RIGHT("MIDDLE_BOTTOM_RIGHT");
   public String label;
   // $VF: synthetic field
   public static CBGuiAnchor[] field_0003 = new CBGuiAnchor[]{
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
   public BlockGlass field_0006;

   public CBGuiAnchor(String var3) {
      this.label = var3;
   }

   public String getLabel() {
      return this.label;
   }
}
