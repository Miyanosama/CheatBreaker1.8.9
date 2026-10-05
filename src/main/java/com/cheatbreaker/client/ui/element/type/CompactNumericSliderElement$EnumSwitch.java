package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.config.Setting;

// $VF: synthetic class
public class CompactNumericSliderElement$EnumSwitch {
   public static int[] recoveredField1485 = new int[Setting.Type.values().length];

   static {
      try {
         recoveredField1485[Setting.Type.INTEGER.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField1485[Setting.Type.FLOAT.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField1485[Setting.Type.DOUBLE.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
