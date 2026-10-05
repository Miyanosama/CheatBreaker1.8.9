package net.optifine.shaders.uniform;

import java.util.HashMap;
import java.util.Map;
import net.optifine.util.CounterInt;
import net.optifine.util.SmoothFloat;
import recovered.unidentified.UnidentifiedClass3953;

public class Smoother {
   public UnidentifiedClass3953 field_0001;
   public static CounterInt counterIds = new CounterInt(1);
   public static Map<Integer, SmoothFloat> mapSmoothValues = new HashMap<>();

   public static int getNextId() {
      synchronized (counterIds) {
         return counterIds.nextValue();
      }
   }

   public static void resetValues() {
      synchronized (mapSmoothValues) {
         mapSmoothValues.clear();
      }
   }

   public static float getSmoothValue(int var0, float var1, float var2, float var3) {
      synchronized (mapSmoothValues) {
         Integer var5 = var0;
         SmoothFloat var6 = mapSmoothValues.get(var5);
         if (var6 == null) {
            var6 = new SmoothFloat(var1, var2, var3);
            mapSmoothValues.put(var5, var6);
         }

         return var6.getSmoothValue(var1, var2, var3);
      }
   }
}
