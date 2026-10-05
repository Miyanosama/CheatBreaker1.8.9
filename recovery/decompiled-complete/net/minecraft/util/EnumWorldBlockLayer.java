package net.minecraft.util;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import net.minecraft.init.Bootstrap$13;

public enum EnumWorldBlockLayer {
   CUTOUT_MIPPED("Mipped Cutout"),
   CUTOUT("Cutout"),
   SOLID("Solid"),
   TRANSLUCENT("Translucent");

   // $VF: synthetic field
   public static EnumWorldBlockLayer[] $VALUES = new EnumWorldBlockLayer[]{
      EnumWorldBlockLayer.SOLID, EnumWorldBlockLayer.CUTOUT_MIPPED, EnumWorldBlockLayer.CUTOUT, EnumWorldBlockLayer.TRANSLUCENT
   };
   public String layerName;
   public AbstractElement field_0005;
   public Bootstrap$13 field_0004;

   public EnumWorldBlockLayer(String var3) {
      this.layerName = var3;
   }

   @Override
   public String toString() {
      return this.layerName;
   }
}
