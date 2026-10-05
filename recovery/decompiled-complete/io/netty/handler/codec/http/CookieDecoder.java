package io.netty.handler.codec.http;

import io.netty.util.internal.AppendableCharSequence;
import io.netty.util.internal.StringUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import net.optifine.shaders.gui.GuiShaders;

public class CookieDecoder {
   public GuiShaders __junk7275363356197047339;
   public AppendableCharSequence __junk8803668208520160573;
   public static char COMMA;

   public static void extractKeyValuePairs(String var0, List<String> var1, List<String> var2) {
      int var3 = var0.length();
      int var4 = 0;

      label77:
      while (var4 != var3) {
         switch (var0.charAt(var4)) {
            case '\t':
            case '\n':
            case '\u000b':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ';':
               var4++;
               continue;
         }

         while (var4 != var3) {
            if (var0.charAt(var4) != '$') {
               String var5;
               String var6;
               if (var4 == var3) {
                  var5 = null;
                  var6 = null;
               } else {
                  int var7 = var4;

                  label68:
                  while (true) {
                     switch (var0.charAt(var4)) {
                        case ';':
                           var5 = var0.substring(var7, var4);
                           var6 = null;
                           break label68;
                        case '=':
                           var5 = var0.substring(var7, var4);
                           if (++var4 == var3) {
                              var6 = "";
                           } else {
                              char var9 = var0.charAt(var4);
                              if (var9 == '"' || var9 == '\'') {
                                 StringBuilder var15 = new StringBuilder(var0.length() - var4);
                                 char var11 = var9;
                                 boolean var12 = false;
                                 var4++;

                                 while (var4 != var3) {
                                    if (var12) {
                                       var12 = false;
                                       var9 = var0.charAt(var4++);
                                       switch (var9) {
                                          case '"':
                                          case '\'':
                                          case '\\':
                                             var15.setCharAt(var15.length() - 1, var9);
                                             break;
                                          default:
                                             var15.append(var9);
                                       }
                                    } else {
                                       var9 = var0.charAt(var4++);
                                       if (var9 == var11) {
                                          var6 = var15.toString();
                                          break label68;
                                       }

                                       var15.append(var9);
                                       if (var9 == '\\') {
                                          var12 = true;
                                       }
                                    }
                                 }

                                 var6 = var15.toString();
                                 break label68;
                              }

                              int var10 = var0.indexOf(59, var4);
                              if (var10 > 0) {
                                 var6 = var0.substring(var4, var10);
                                 var4 = var10;
                              } else {
                                 var6 = var0.substring(var4);
                                 var4 = var3;
                              }
                           }
                           break label68;
                        default:
                           if (++var4 == var3) {
                              var5 = var0.substring(var7);
                              var6 = null;
                              break label68;
                           }
                     }
                  }
               }

               var1.add(var5);
               var2.add(var6);
               continue label77;
            }

            var4++;
         }
         break;
      }
   }

   public static Set<Cookie> decode(String var0) {
      ArrayList var1 = new ArrayList(8);
      ArrayList var2 = new ArrayList(8);
      extractKeyValuePairs(var0, var1, var2);
      if (var1.isEmpty()) {
         return Collections.emptySet();
      } else {
         int var4 = 0;
         int var3;
         if (((String)var1.get(0)).equalsIgnoreCase("Version")) {
            try {
               var4 = Integer.parseInt((String)var2.get(0));
            } catch (NumberFormatException var28) {
            }

            var3 = 1;
         } else {
            var3 = 0;
         }

         if (var1.size() <= var3) {
            return Collections.emptySet();
         } else {
            TreeSet var5;
            for (var5 = new TreeSet(); var3 < var1.size(); var3++) {
               String var6 = (String)var1.get(var3);
               String var7 = (String)var2.get(var3);
               if (var7 == null) {
                  var7 = "";
               }

               DefaultCookie var8 = new DefaultCookie(var6, var7);
               boolean var9 = false;
               boolean var10 = false;
               boolean var11 = false;
               String var12 = null;
               String var13 = null;
               String var14 = null;
               String var15 = null;
               long var16 = -9223372035915241464L & -9223372035746888379L;
               ArrayList var18 = new ArrayList(2);

               for (int var19 = var3 + 1; var19 < var1.size(); var3++) {
                  var6 = (String)var1.get(var19);
                  var7 = (String)var2.get(var19);
                  if ("Discard".equalsIgnoreCase(var6)) {
                     var9 = true;
                  } else if ("Secure".equalsIgnoreCase(var6)) {
                     var10 = true;
                  } else if ("HTTPOnly".equalsIgnoreCase(var6)) {
                     var11 = true;
                  } else if ("Comment".equalsIgnoreCase(var6)) {
                     var12 = var7;
                  } else if ("CommentURL".equalsIgnoreCase(var6)) {
                     var13 = var7;
                  } else if ("Domain".equalsIgnoreCase(var6)) {
                     var14 = var7;
                  } else if ("Path".equalsIgnoreCase(var6)) {
                     var15 = var7;
                  } else if ("Expires".equalsIgnoreCase(var6)) {
                     try {
                        long var31 = HttpHeaderDateFormat.get().parse(var7).getTime() - System.currentTimeMillis();
                        var16 = var31 / (269776874L & 1021L) + (var31 % (-5979004608893156374L & 1141527532L) != (1737163777L & -2339964546413487420L) ? 1 : 0);
                     } catch (ParseException var27) {
                     }
                  } else if ("Max-Age".equalsIgnoreCase(var6)) {
                     var16 = Integer.parseInt(var7);
                  } else if ("Version".equalsIgnoreCase(var6)) {
                     var4 = Integer.parseInt(var7);
                  } else {
                     if (!"Port".equalsIgnoreCase(var6)) {
                        break;
                     }

                     String[] var20 = StringUtil.split(var7, ',');

                     for (String var24 : var20) {
                        try {
                           var18.add(Integer.valueOf(var24));
                        } catch (NumberFormatException var26) {
                        }
                     }
                  }

                  var19++;
               }

               var8.setVersion(var4);
               var8.setMaxAge(var16);
               var8.setPath(var15);
               var8.setDomain(var14);
               var8.setSecure(var10);
               var8.setHttpOnly(var11);
               if (var4 > 0) {
                  var8.setComment(var12);
               }

               if (var4 > 1) {
                  var8.setCommentUrl(var13);
                  var8.setPorts(var18);
                  var8.setDiscard(var9);
               }

               var5.add(var8);
            }

            return var5;
         }
      }
   }
}
