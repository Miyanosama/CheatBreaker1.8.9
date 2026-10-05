package org.json;

import java.util.Locale;
import java.util.Map.Entry;

public class HTTP {
   public static final String recoveredField66 = "\r\n";

   public static String toString(JSONObject var0) throws org.json.JSONException {
      StringBuilder var1 = new StringBuilder();
      if (var0.has("Status-Code") && var0.has("Reason-Phrase")) {
         var1.append(var0.getString("HTTP-Version"));
         var1.append(' ');
         var1.append(var0.getString("Status-Code"));
         var1.append(' ');
         var1.append(var0.getString("Reason-Phrase"));
      } else {
         if (!var0.has("Method") || !var0.has("Request-URI")) {
            throw new JSONException("Not enough material for an HTTP header.");
         }

         var1.append(var0.getString("Method"));
         var1.append(' ');
         var1.append('"');
         var1.append(var0.getString("Request-URI"));
         var1.append('"');
         var1.append(' ');
         var1.append(var0.getString("HTTP-Version"));
      }

      var1.append("\r\n");

      for (Entry var3 : var0.entrySet()) {
         String var4 = (String)var3.getKey();
         if (!"HTTP-Version".equals(var4)
            && !"Status-Code".equals(var4)
            && !"Reason-Phrase".equals(var4)
            && !"Method".equals(var4)
            && !"Request-URI".equals(var4)
            && !JSONObject.recoveredField2781.equals(var3.getValue())) {
            var1.append(var4);
            var1.append(": ");
            var1.append(var0.optString(var4));
            var1.append("\r\n");
         }
      }

      var1.append("\r\n");
      return var1.toString();
   }

   public static JSONObject toJSONObject(String var0) throws org.json.JSONException {
      JSONObject var1 = new JSONObject();
      HTTPTokener var2 = new HTTPTokener(var0);
      String var3 = var2.nextToken();
      if (var3.toUpperCase(Locale.ROOT).startsWith("HTTP")) {
         var1.put("HTTP-Version", var3);
         var1.put("Status-Code", var2.nextToken());
         var1.put("Reason-Phrase", var2.nextTo('\u0000'));
         var2.next();
      } else {
         var1.put("Method", var3);
         var1.put("Request-URI", var2.nextToken());
         var1.put("HTTP-Version", var2.nextToken());
      }

      while (var2.more()) {
         String var4 = var2.nextTo(':');
         var2.next(':');
         var1.put(var4, var2.nextTo('\u0000'));
         var2.next();
      }

      return var1;
   }
}
