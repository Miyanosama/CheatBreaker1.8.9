package io.netty.util;

import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$WrappedHttpRequest;
import io.netty.util.internal.InternalThreadLocalMap;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Map;
import net.minecraft.item.ItemSoup;
import net.optifine.shaders.config.Property;

public class CharsetUtil {
   public HttpPostRequestEncoder$WrappedHttpRequest __junk6166030793622070749;
   public static Charset US_ASCII = Charset.forName("US-ASCII");
   public static Charset UTF_8 = Charset.forName("UTF-8");
   public static Charset UTF_16 = Charset.forName("UTF-16");
   public static Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
   public ItemSoup __junk8907791743493304809;
   public Property __junk546933486690581854;
   public static Charset UTF_16BE = Charset.forName("UTF-16BE");
   public static Charset UTF_16LE = Charset.forName("UTF-16LE");

   public static CharsetEncoder getEncoder(Charset var0) {
      if (var0 == null) {
         throw new NullPointerException("charset");
      } else {
         Map var1 = InternalThreadLocalMap.get().charsetEncoderCache();
         CharsetEncoder var2 = (CharsetEncoder)var1.get(var0);
         if (var2 != null) {
            var2.reset();
            var2.onMalformedInput(CodingErrorAction.REPLACE);
            var2.onUnmappableCharacter(CodingErrorAction.REPLACE);
            return var2;
         } else {
            var2 = var0.newEncoder();
            var2.onMalformedInput(CodingErrorAction.REPLACE);
            var2.onUnmappableCharacter(CodingErrorAction.REPLACE);
            var1.put(var0, var2);
            return var2;
         }
      }
   }

   public static CharsetDecoder getDecoder(Charset var0) {
      if (var0 == null) {
         throw new NullPointerException("charset");
      } else {
         Map var1 = InternalThreadLocalMap.get().charsetDecoderCache();
         CharsetDecoder var2 = (CharsetDecoder)var1.get(var0);
         if (var2 != null) {
            var2.reset();
            var2.onMalformedInput(CodingErrorAction.REPLACE);
            var2.onUnmappableCharacter(CodingErrorAction.REPLACE);
            return var2;
         } else {
            var2 = var0.newDecoder();
            var2.onMalformedInput(CodingErrorAction.REPLACE);
            var2.onUnmappableCharacter(CodingErrorAction.REPLACE);
            var1.put(var0, var2);
            return var2;
         }
      }
   }
}
