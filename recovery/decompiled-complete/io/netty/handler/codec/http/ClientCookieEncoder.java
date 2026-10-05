package io.netty.handler.codec.http;

import io.netty.buffer.ByteBufOutputStream;
import net.minecraft.client.renderer.EntityRenderer;

public class ClientCookieEncoder {
   public ByteBufOutputStream __junk3105644302971930102;
   public EntityRenderer __junk468092549496792214;

   public static String encode(Iterable<Cookie> var0) {
      if (var0 == null) {
         throw new NullPointerException("cookies");
      } else {
         StringBuilder var1 = CookieEncoderUtil.stringBuilder();

         for (Cookie var3 : var0) {
            if (var3 == null) {
               break;
            }

            encode(var1, var3);
         }

         return CookieEncoderUtil.stripTrailingSeparator(var1);
      }
   }

   public static String encode(Cookie... var0) {
      if (var0 == null) {
         throw new NullPointerException("cookies");
      } else {
         StringBuilder var1 = CookieEncoderUtil.stringBuilder();

         for (Cookie var5 : var0) {
            if (var5 == null) {
               break;
            }

            encode(var1, var5);
         }

         return CookieEncoderUtil.stripTrailingSeparator(var1);
      }
   }

   public static void encode(StringBuilder var0, Cookie var1) {
      if (var1.getVersion() >= 1) {
         CookieEncoderUtil.add(var0, "$Version", -6618263022898869231L & 6618263021858853641L);
      }

      CookieEncoderUtil.add(var0, var1.getName(), var1.getValue());
      if (var1.getPath() != null) {
         CookieEncoderUtil.add(var0, "$Path", var1.getPath());
      }

      if (var1.getDomain() != null) {
         CookieEncoderUtil.add(var0, "$Domain", var1.getDomain());
      }

      if (var1.getVersion() >= 1 && !var1.getPorts().isEmpty()) {
         var0.append('$');
         var0.append("Port");
         var0.append('=');
         var0.append('"');

         for (int var3 : var1.getPorts()) {
            var0.append(var3);
            var0.append(',');
         }

         var0.setCharAt(var0.length() - 1, '"');
         var0.append(';');
         var0.append(' ');
      }
   }

   public static String encode(String var0, String var1) {
      return encode(new DefaultCookie(var0, var1));
   }

   public static String encode(Cookie var0) {
      if (var0 == null) {
         throw new NullPointerException("cookie");
      } else {
         StringBuilder var1 = CookieEncoderUtil.stringBuilder();
         encode(var1, var0);
         return CookieEncoderUtil.stripTrailingSeparator(var1);
      }
   }
}
