package org.json;

import com.cheatbreaker.client.ui.util.RenderUtil;
import java.util.Map.Entry;
import net.minecraft.client.model.ModelPlayer;

public class CookieList {
   public RenderUtil field_0000;
   public ModelPlayer field_0001;

   public static String toString(JSONObject var0) {
      boolean var1 = false;
      StringBuilder var2 = new StringBuilder();

      for (Entry var4 : var0.method_07199()) {
         String var5 = (String)var4.getKey();
         Object var6 = var4.getValue();
         if (!JSONObject.field_0003.equals(var6)) {
            if (var1) {
               var2.append(';');
            }

            var2.append(Cookie.escape(var5));
            var2.append("=");
            var2.append(Cookie.escape(var6.toString()));
            var1 = true;
         }
      }

      return var2.toString();
   }

   public static JSONObject toJSONObject(String var0) {
      JSONObject var1 = new JSONObject();
      JSONTokener var2 = new JSONTokener(var0);

      while (var2.more()) {
         String var3 = Cookie.unescape(var2.nextTo('='));
         var2.next('=');
         var1.put(var3, Cookie.unescape(var2.nextTo(';')));
         var2.next();
      }

      return var1;
   }
}
