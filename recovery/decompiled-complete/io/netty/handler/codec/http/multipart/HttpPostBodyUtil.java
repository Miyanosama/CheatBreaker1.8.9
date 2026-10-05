package io.netty.handler.codec.http.multipart;

import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;
import net.minecraft.client.renderer.vertex.VertexFormat$1;

public class HttpPostBodyUtil {
   public static int chunkSize;
   public static String CONTENT_DISPOSITION;
   public static String FILE;
   public static String NAME;
   public VertexFormat$1 __junk3946357719135515872;
   public static String DEFAULT_TEXT_CONTENT_TYPE;
   public static Charset ISO_8859_1 = CharsetUtil.ISO_8859_1;
   public static String DEFAULT_BINARY_CONTENT_TYPE;
   public static String ATTACHMENT;
   public static String FORM_DATA;
   public static String FILENAME;
   public static String MULTIPART_MIXED;
   public static Charset US_ASCII = CharsetUtil.US_ASCII;

   public static int findNonWhitespace(String var0, int var1) {
      int var2 = var1;

      while (var2 < var0.length() && Character.isWhitespace(var0.charAt(var2))) {
         var2++;
      }

      return var2;
   }

   public static int findWhitespace(String var0, int var1) {
      int var2 = var1;

      while (var2 < var0.length() && !Character.isWhitespace(var0.charAt(var2))) {
         var2++;
      }

      return var2;
   }

   public static int findEndOfString(String var0) {
      int var1 = var0.length();

      while (var1 > 0 && Character.isWhitespace(var0.charAt(var1 - 1))) {
         var1--;
      }

      return var1;
   }
}
