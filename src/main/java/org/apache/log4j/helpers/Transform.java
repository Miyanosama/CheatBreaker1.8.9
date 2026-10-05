package org.apache.log4j.helpers;

public class Transform {
   public static final String recoveredField2278 = "<![CDATA[";
   public static final String recoveredField2279 = "]]&gt;";
   public static final String recoveredField2280 = "]]>";
   public static final String recoveredField2281 = "]]>]]&gt;<![CDATA[";
   public static int CDATA_END_LEN = "]]>".length();

   public static String escapeTags(String var0) {
      if (var0 != null && var0.length() != 0 && (var0.indexOf(34) != -1 || var0.indexOf(38) != -1 || var0.indexOf(60) != -1 || var0.indexOf(62) != -1)) {
         StringBuffer var1 = new StringBuffer(var0.length() + 6);
         byte var2 = 32;
         int var3 = var0.length();

         for (int var4 = 0; var4 < var3; var4++) {
            char var5 = var0.charAt(var4);
            if (var5 > '>') {
               var1.append(var5);
            } else if (var5 == '<') {
               var1.append("&lt;");
            } else if (var5 == '>') {
               var1.append("&gt;");
            } else if (var5 == '&') {
               var1.append("&amp;");
            } else if (var5 == '"') {
               var1.append("&quot;");
            } else {
               var1.append(var5);
            }
         }

         return var1.toString();
      } else {
         return var0;
      }
   }

   public static void appendEscapingCDATA(StringBuffer var0, String var1) {
      if (var1 != null) {
         int var2 = var1.indexOf("]]>");
         if (var2 < 0) {
            var0.append(var1);
         } else {
            int var3;
            for (var3 = 0; var2 > -1; var2 = var1.indexOf("]]>", var3)) {
               var0.append(var1.substring(var3, var2));
               var0.append("]]>]]&gt;<![CDATA[");
               var3 = var2 + CDATA_END_LEN;
               if (var3 >= var1.length()) {
                  return;
               }
            }

            var0.append(var1.substring(var3));
         }
      }
   }
}
