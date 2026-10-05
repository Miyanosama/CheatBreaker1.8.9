package net.optifine;

import net.minecraft.src.Config;

public class GlErrors {
   public static final long recoveredField3946 = 3000L;
   public static final int recoveredField3948 = 10;
   public static boolean frameStarted = false;
   public static long recoveredField3947 = -1L;
   public static int countErrors = 0;
   public static int countErrorsSuppressed = 0;
   public static boolean suppressed = false;
   public static boolean oneErrorEnabled = false;

   public static void frameStart() {
      frameStarted = true;
      if (recoveredField3947 < 0L) {
         recoveredField3947 = System.currentTimeMillis();
      }

      if (System.currentTimeMillis() > recoveredField3947 + 3000L) {
         if (countErrorsSuppressed > 0) {
            Config.error("Suppressed " + countErrors + " OpenGL errors");
         }

         suppressed = countErrors > 10;
         recoveredField3947 = System.currentTimeMillis();
         countErrors = 0;
         countErrorsSuppressed = 0;
         oneErrorEnabled = true;
      }
   }

   public static boolean isEnabled(int var0) {
      if (!frameStarted) {
         return true;
      } else {
         countErrors++;
         if (oneErrorEnabled) {
            oneErrorEnabled = false;
            return true;
         } else {
            if (suppressed) {
               countErrorsSuppressed++;
            }

            return !suppressed;
         }
      }
   }
}
