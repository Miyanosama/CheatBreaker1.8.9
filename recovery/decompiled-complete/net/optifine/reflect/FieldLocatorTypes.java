package net.optifine.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import net.optifine.Log;
import recovered.unidentified.UnidentifiedClass4343;

public class FieldLocatorTypes implements IFieldLocator {
   public Field field = null;
   public UnidentifiedClass4343 field_0001;

   @Override
   public Field getField() {
      return this.field;
   }

   public FieldLocatorTypes(Class var1, Class[] var2, Class var3, Class[] var4, String var5) {
      Field[] var6 = var1.getDeclaredFields();
      ArrayList var7 = new ArrayList();

      for (int var8 = 0; var8 < var6.length; var8++) {
         Field var9 = var6[var8];
         var7.add(var9.getType());
      }

      ArrayList var12 = new ArrayList();
      var12.addAll(Arrays.asList(var2));
      var12.add(var3);
      var12.addAll(Arrays.asList(var4));
      int var13 = Collections.indexOfSubList(var7, var12);
      if (var13 < 0) {
         Log.log("(Reflector) Field not found: " + var5);
      } else {
         int var10 = Collections.indexOfSubList(var7.subList(var13 + 1, var7.size()), var12);
         if (var10 >= 0) {
            Log.log("(Reflector) More than one match found for field: " + var5);
         } else {
            int var11 = var13 + var2.length;
            this.field = var6[var11];
         }
      }
   }
}
