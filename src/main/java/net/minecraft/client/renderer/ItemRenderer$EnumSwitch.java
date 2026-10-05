package net.minecraft.client.renderer;

import net.minecraft.item.EnumAction;

// $VF: synthetic class
public class ItemRenderer$EnumSwitch {
   public static int[] recoveredField1779 = new int[EnumAction.values().length];

   static {
      try {
         recoveredField1779[EnumAction.NONE.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         recoveredField1779[EnumAction.EAT.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField1779[EnumAction.DRINK.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField1779[EnumAction.BLOCK.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField1779[EnumAction.BOW.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
