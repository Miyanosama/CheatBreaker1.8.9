package com.cheatbreaker.client.util.render;

import net.minecraft.item.EnumAction;

// $VF: synthetic class
public class LegacyItemAnimations$EnumSwitch {
   public static int[] recoveredField1256 = new int[EnumAction.values().length];

   static {
      try {
         recoveredField1256[EnumAction.EAT.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         recoveredField1256[EnumAction.DRINK.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField1256[EnumAction.BLOCK.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField1256[EnumAction.BOW.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField1256[EnumAction.NONE.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
