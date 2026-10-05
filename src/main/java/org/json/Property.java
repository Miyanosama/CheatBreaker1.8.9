package org.json;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Map.Entry;
import org.json.JSONObject;

public class Property {
   public static Properties method_28478(JSONObject var0) {
      Properties var1 = new Properties();
      if (var0 != null) {
         for (Entry var3 : var0.entrySet()) {
            Object var4 = var3.getValue();
            if (!JSONObject.recoveredField2781.equals(var4)) {
               var1.put(var3.getKey(), var4.toString());
            }
         }
      }

      return var1;
   }

   public static JSONObject method_28479(Properties var0) {
      JSONObject var1 = new JSONObject(var0 == null ? 0 : var0.size());
      if (var0 != null && !var0.isEmpty()) {
         Enumeration var2 = var0.propertyNames();

         while (var2.hasMoreElements()) {
            String var3 = (String)var2.nextElement();
            var1.put(var3, var0.getProperty(var3));
         }
      }

      return var1;
   }
}
