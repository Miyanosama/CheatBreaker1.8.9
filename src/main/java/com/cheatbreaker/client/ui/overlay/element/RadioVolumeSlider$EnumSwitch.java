package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.config.Setting;

// $VF: synthetic class
public class RadioVolumeSlider$EnumSwitch {
   public static int[] recoveredField3944 = new int[Setting.Type.values().length];

   static {
      try {
         recoveredField3944[Setting.Type.INTEGER.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField3944[Setting.Type.FLOAT.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField3944[Setting.Type.DOUBLE.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
