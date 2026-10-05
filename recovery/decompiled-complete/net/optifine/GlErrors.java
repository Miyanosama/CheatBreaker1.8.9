package net.optifine;

import net.minecraft.src.Config;
import net.minecraft.world.storage.WorldInfo$1;
import net.optifine.shaders.Shaders;

public class GlErrors {
   public static boolean frameStarted = false;
   public static int countErrors = 0;
   public static boolean oneErrorEnabled = false;
   public static long field_0006;
   public Shaders field_0000;
   public static long field_0001 = -1L & -1L;
   public static int countErrorsSuppressed = 0;
   public static int field_0005;
   public static boolean suppressed = false;
   public WorldInfo$1 field_0009;

   public static void frameStart() {
      frameStarted = true;
      if (field_0001 < (6092424660971062288L & 8358L)) {
         field_0001 = System.currentTimeMillis();
      }

      if (System.currentTimeMillis() > field_0001 + (-873749629214737476L & 68536L)) {
         if (countErrorsSuppressed > 0) {
            Config.error("Suppressed " + countErrors + " OpenGL errors");
         }

         suppressed = countErrors > 10;
         field_0001 = System.currentTimeMillis();
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
