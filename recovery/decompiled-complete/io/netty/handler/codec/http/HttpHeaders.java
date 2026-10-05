package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.world.SpawnerAnimals;

public abstract class HttpHeaders implements Iterable<Entry<String, String>> {
   public static CharSequence CONTENT_LENGTH_ENTITY = newEntity("Content-Length");
   public static CharSequence SEC_WEBSOCKET_LOCATION_ENTITY = newEntity("Sec-WebSocket-Location");
   public static byte[] HEADER_SEPERATOR = new byte[]{58, 32};
   public static byte[] CRLF = new byte[]{13, 10};
   public static CharSequence EXPECT_ENTITY = newEntity("Expect");
   public static CharSequence CONNECTION_ENTITY = newEntity("Connection");
   public static CharSequence CONTINUE_ENTITY = newEntity("100-continue");
   public static CharSequence TRANSFER_ENCODING_ENTITY = newEntity("Transfer-Encoding");
   public static CharSequence CLOSE_ENTITY = newEntity("close");
   public static CharSequence SEC_WEBSOCKET_KEY1_ENTITY = newEntity("Sec-WebSocket-Key1");
   public SpawnerAnimals __junk6217557991223768362;
   public static HttpHeaders EMPTY_HEADERS = new HttpHeaders$1();
   public static CharSequence HOST_ENTITY = newEntity("Host");
   public static CharSequence SEC_WEBSOCKET_ORIGIN_ENTITY = newEntity("Sec-WebSocket-Origin");
   public static CharSequence DATE_ENTITY = newEntity("Date");
   public static CharSequence KEEP_ALIVE_ENTITY = newEntity("keep-alive");
   public static CharSequence SEC_WEBSOCKET_KEY2_ENTITY = newEntity("Sec-WebSocket-Key2");
   public static CharSequence CHUNKED_ENTITY = newEntity("chunked");

   public abstract HttpHeaders add(String var1, Object var2);

   public static void setDateHeader(HttpMessage var0, String var1, Date var2) {
      setDateHeader(var0, (CharSequence)var1, var2);
   }

   public static boolean isKeepAlive(HttpMessage var0) {
      String var1 = var0.headers().get(CONNECTION_ENTITY);
      if (var1 != null && equalsIgnoreCase(CLOSE_ENTITY, var1)) {
         return false;
      } else {
         return var0.getProtocolVersion().isKeepAliveDefault() ? !equalsIgnoreCase(CLOSE_ENTITY, var1) : equalsIgnoreCase(KEEP_ALIVE_ENTITY, var1);
      }
   }

   public static void setHeader(HttpMessage var0, String var1, Iterable<?> var2) {
      var0.headers().set(var1, var2);
   }

   public static void setDate(HttpMessage var0, Date var1) {
      if (var1 != null) {
         var0.headers().set(DATE_ENTITY, HttpHeaderDateFormat.get().format(var1));
      } else {
         var0.headers().set(DATE_ENTITY, null);
      }
   }

   public abstract boolean isEmpty();

   public static CharSequence newEntity(String var0) {
      if (var0 == null) {
         throw new NullPointerException("name");
      } else {
         return new HttpHeaderEntity(var0);
      }
   }

   public static boolean isContentLengthSet(HttpMessage var0) {
      return var0.headers().contains(CONTENT_LENGTH_ENTITY);
   }

   public static String getHost(HttpMessage var0) {
      return var0.headers().get(HOST_ENTITY);
   }

   public abstract HttpHeaders set(String var1, Object var2);

   public static CharSequence newNameEntity(String var0) {
      if (var0 == null) {
         throw new NullPointerException("name");
      } else {
         return new HttpHeaderEntity(var0, HEADER_SEPERATOR);
      }
   }

   public boolean contains(CharSequence var1, CharSequence var2, boolean var3) {
      return this.contains(var1.toString(), var2.toString(), var3);
   }

   public static boolean equalsIgnoreCase(CharSequence var0, CharSequence var1) {
      if (var0 == var1) {
         return true;
      } else if (var0 != null && var1 != null) {
         int var2 = var0.length();
         if (var2 != var1.length()) {
            return false;
         } else {
            for (int var3 = var2 - 1; var3 >= 0; var3--) {
               char var4 = var0.charAt(var3);
               char var5 = var1.charAt(var3);
               if (var4 != var5) {
                  if (var4 >= 'A' && var4 <= 'Z') {
                     var4 = (char)(var4 + ' ');
                  }

                  if (var5 >= 'A' && var5 <= 'Z') {
                     var5 = (char)(var5 + ' ');
                  }

                  if (var4 != var5) {
                     return false;
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public List<String> getAll(CharSequence var1) {
      return this.getAll(var1.toString());
   }

   public static void validateHeaderName(CharSequence var0) {
      if (var0 == null) {
         throw new NullPointerException("Header names cannot be null");
      } else {
         for (int var1 = 0; var1 < var0.length(); var1++) {
            char var2 = var0.charAt(var1);
            if (var2 > 127) {
               throw new IllegalArgumentException("Header name cannot contain non-ASCII characters: " + var0);
            }

            switch (var2) {
               case '\t':
               case '\n':
               case '\u000b':
               case '\f':
               case '\r':
               case ' ':
               case ',':
               case ':':
               case ';':
               case '=':
                  throw new IllegalArgumentException("Header name cannot contain the following prohibited characters: =,;: \\t\\r\\n\\v\\f: " + var0);
            }
         }
      }
   }

   public static void setKeepAlive(HttpMessage var0, boolean var1) {
      HttpHeaders var2 = var0.headers();
      if (var0.getProtocolVersion().isKeepAliveDefault()) {
         if (var1) {
            var2.remove(CONNECTION_ENTITY);
         } else {
            var2.set(CONNECTION_ENTITY, CLOSE_ENTITY);
         }
      } else if (var1) {
         var2.set(CONNECTION_ENTITY, KEEP_ALIVE_ENTITY);
      } else {
         var2.remove(CONNECTION_ENTITY);
      }
   }

   public static String getHeader(HttpMessage var0, CharSequence var1) {
      return var0.headers().get(var1);
   }

   public static void set100ContinueExpected(HttpMessage var0, boolean var1) {
      if (var1) {
         var0.headers().set(EXPECT_ENTITY, CONTINUE_ENTITY);
      } else {
         var0.headers().remove(EXPECT_ENTITY);
      }
   }

   public static long getContentLength(HttpMessage var0, long var1) {
      String var3 = var0.headers().get(CONTENT_LENGTH_ENTITY);
      if (var3 != null) {
         try {
            return Long.parseLong(var3);
         } catch (NumberFormatException var6) {
            return var1;
         }
      } else {
         long var4 = getWebSocketContentLength(var0);
         return var4 >= (1346388067L & 167837852L) ? var4 : var1;
      }
   }

   public static String getHost(HttpMessage var0, String var1) {
      return getHeader(var0, HOST_ENTITY, var1);
   }

   public static void setHost(HttpMessage var0, String var1) {
      var0.headers().set(HOST_ENTITY, var1);
   }

   public static int getIntHeader(HttpMessage var0, CharSequence var1, int var2) {
      String var3 = getHeader(var0, var1);
      if (var3 == null) {
         return var2;
      } else {
         try {
            return Integer.parseInt(var3);
         } catch (NumberFormatException var5) {
            return var2;
         }
      }
   }

   public boolean contains(CharSequence var1) {
      return this.contains(var1.toString());
   }

   public static void encode(HttpHeaders var0, ByteBuf var1) {
      if (var0 instanceof DefaultHttpHeaders) {
         ((DefaultHttpHeaders)var0).encode(var1);
      } else {
         for (Entry var3 : var0) {
            encode((CharSequence)var3.getKey(), (CharSequence)var3.getValue(), var1);
         }
      }
   }

   public static boolean isTransferEncodingChunked(HttpMessage var0) {
      return var0.headers().contains(TRANSFER_ENCODING_ENTITY, CHUNKED_ENTITY, true);
   }

   public static void setIntHeader(HttpMessage var0, String var1, int var2) {
      var0.headers().set(var1, var2);
   }

   public static void addIntHeader(HttpMessage var0, String var1, int var2) {
      var0.headers().add(var1, var2);
   }

   public HttpHeaders set(CharSequence var1, Iterable<?> var2) {
      return this.set(var1.toString(), var2);
   }

   public static void clearHeaders(HttpMessage var0) {
      var0.headers().clear();
   }

   public static void setDateHeader(HttpMessage var0, CharSequence var1, Date var2) {
      if (var2 != null) {
         var0.headers().set(var1, HttpHeaderDateFormat.get().format(var2));
      } else {
         var0.headers().set(var1, null);
      }
   }

   public abstract HttpHeaders add(String var1, Iterable<?> var2);

   public static void validateHeaderValue(CharSequence var0) {
      if (var0 == null) {
         throw new NullPointerException("Header values cannot be null");
      } else {
         byte var1 = 0;

         for (int var2 = 0; var2 < var0.length(); var2++) {
            char var3 = var0.charAt(var2);
            switch (var3) {
               case '\u000b':
                  throw new IllegalArgumentException("Header value contains a prohibited character '\\v': " + var0);
               case '\f':
                  throw new IllegalArgumentException("Header value contains a prohibited character '\\f': " + var0);
            }

            switch (var1) {
               case 0:
                  switch (var3) {
                     case '\n':
                        var1 = 2;
                        continue;
                     case '\r':
                        var1 = 1;
                     default:
                        continue;
                  }
               case 1:
                  switch (var3) {
                     case '\n':
                        var1 = 2;
                        continue;
                     default:
                        throw new IllegalArgumentException("Only '\\n' is allowed after '\\r': " + var0);
                  }
               case 2:
                  switch (var3) {
                     case '\t':
                     case ' ':
                        var1 = 0;
                        break;
                     default:
                        throw new IllegalArgumentException("Only ' ' and '\\t' are allowed after '\\n': " + var0);
                  }
            }
         }

         if (var1 != 0) {
            throw new IllegalArgumentException("Header value must not end with '\\r' or '\\n':" + var0);
         }
      }
   }

   public static void setIntHeader(HttpMessage var0, CharSequence var1, int var2) {
      var0.headers().set(var1, var2);
   }

   public String get(CharSequence var1) {
      return this.get(var1.toString());
   }

   public static void setHeader(HttpMessage var0, CharSequence var1, Iterable<?> var2) {
      var0.headers().set(var1, var2);
   }

   public static void setHeader(HttpMessage var0, CharSequence var1, Object var2) {
      var0.headers().set(var1, var2);
   }

   public static void set100ContinueExpected(HttpMessage var0) {
      set100ContinueExpected(var0, true);
   }

   public abstract HttpHeaders remove(String var1);

   public boolean contains(String var1, String var2, boolean var3) {
      List var4 = this.getAll(var1);
      if (var4.isEmpty()) {
         return false;
      } else {
         for (String var6 : var4) {
            if (var3) {
               if (equalsIgnoreCase(var6, var2)) {
                  return true;
               }
            } else if (var6.equals(var2)) {
               return true;
            }
         }

         return false;
      }
   }

   public abstract HttpHeaders clear();

   public static int getIntHeader(HttpMessage var0, String var1) {
      return getIntHeader(var0, (CharSequence)var1);
   }

   public HttpHeaders add(HttpHeaders var1) {
      if (var1 == null) {
         throw new NullPointerException("headers");
      } else {
         for (Entry var3 : var1) {
            this.add((String)var3.getKey(), var3.getValue());
         }

         return this;
      }
   }

   public static void setIntHeader(HttpMessage var0, String var1, Iterable<Integer> var2) {
      var0.headers().set(var1, var2);
   }

   public abstract String get(String var1);

   public static void addIntHeader(HttpMessage var0, CharSequence var1, int var2) {
      var0.headers().add(var1, var2);
   }

   public static void setHeader(HttpMessage var0, String var1, Object var2) {
      var0.headers().set(var1, var2);
   }

   public static void setDateHeader(HttpMessage var0, CharSequence var1, Iterable<Date> var2) {
      var0.headers().set(var1, var2);
   }

   public static void encodeAscii0(CharSequence var0, ByteBuf var1) {
      int var2 = var0.length();

      for (int var3 = 0; var3 < var2; var3++) {
         var1.writeByte((byte)var0.charAt(var3));
      }
   }

   public static Date getDate(HttpMessage var0, Date var1) {
      return getDateHeader(var0, DATE_ENTITY, var1);
   }

   public static void addHeader(HttpMessage var0, CharSequence var1, Object var2) {
      var0.headers().add(var1, var2);
   }

   public static String getHeader(HttpMessage var0, String var1, String var2) {
      return getHeader(var0, (CharSequence)var1, var2);
   }

   public static Date getDate(HttpMessage var0) {
      return getDateHeader(var0, DATE_ENTITY);
   }

   public HttpHeaders set(CharSequence var1, Object var2) {
      return this.set(var1.toString(), var2);
   }

   public abstract Set<String> names();

   public abstract List<Entry<String, String>> entries();

   public static String getHeader(HttpMessage var0, String var1) {
      return var0.headers().get(var1);
   }

   public static void addHeader(HttpMessage var0, String var1, Object var2) {
      var0.headers().add(var1, var2);
   }

   public static Date getDateHeader(HttpMessage var0, String var1, Date var2) {
      return getDateHeader(var0, (CharSequence)var1, var2);
   }

   public static void removeTransferEncodingChunked(HttpMessage var0) {
      List var1 = var0.headers().getAll(TRANSFER_ENCODING_ENTITY);
      if (!var1.isEmpty()) {
         Iterator var2 = var1.iterator();

         while (var2.hasNext()) {
            String var3 = (String)var2.next();
            if (equalsIgnoreCase(var3, CHUNKED_ENTITY)) {
               var2.remove();
            }
         }

         if (var1.isEmpty()) {
            var0.headers().remove(TRANSFER_ENCODING_ENTITY);
         } else {
            var0.headers().set(TRANSFER_ENCODING_ENTITY, var1);
         }
      }
   }

   public abstract boolean contains(String var1);

   public static void setIntHeader(HttpMessage var0, CharSequence var1, Iterable<Integer> var2) {
      var0.headers().set(var1, var2);
   }

   public static int getIntHeader(HttpMessage var0, String var1, int var2) {
      return getIntHeader(var0, (CharSequence)var1, var2);
   }

   public static void removeHeader(HttpMessage var0, CharSequence var1) {
      var0.headers().remove(var1);
   }

   public static CharSequence newValueEntity(String var0) {
      if (var0 == null) {
         throw new NullPointerException("name");
      } else {
         return new HttpHeaderEntity(var0, CRLF);
      }
   }

   public static int getWebSocketContentLength(HttpMessage var0) {
      HttpHeaders var1 = var0.headers();
      if (var0 instanceof HttpRequest) {
         HttpRequest var2 = (HttpRequest)var0;
         if (HttpMethod.GET.equals(var2.getMethod()) && var1.contains(SEC_WEBSOCKET_KEY1_ENTITY) && var1.contains(SEC_WEBSOCKET_KEY2_ENTITY)) {
            return 8;
         }
      } else if (var0 instanceof HttpResponse) {
         HttpResponse var3 = (HttpResponse)var0;
         if (var3.getStatus().code() == 101 && var1.contains(SEC_WEBSOCKET_ORIGIN_ENTITY) && var1.contains(SEC_WEBSOCKET_LOCATION_ENTITY)) {
            return 16;
         }
      }

      return -1;
   }

   public HttpHeaders remove(CharSequence var1) {
      return this.remove(var1.toString());
   }

   public static Date getDateHeader(HttpMessage var0, String var1) {
      return getDateHeader(var0, (CharSequence)var1);
   }

   public static void setDateHeader(HttpMessage var0, String var1, Iterable<Date> var2) {
      var0.headers().set(var1, var2);
   }

   public HttpHeaders set(HttpHeaders var1) {
      if (var1 == null) {
         throw new NullPointerException("headers");
      } else {
         this.clear();

         for (Entry var3 : var1) {
            this.add((String)var3.getKey(), var3.getValue());
         }

         return this;
      }
   }

   public static Date getDateHeader(HttpMessage var0, CharSequence var1) {
      String var2 = getHeader(var0, var1);
      if (var2 == null) {
         throw new ParseException("header not found: " + var1, 0);
      } else {
         return HttpHeaderDateFormat.get().parse(var2);
      }
   }

   public abstract List<String> getAll(String var1);

   public static void addDateHeader(HttpMessage var0, CharSequence var1, Date var2) {
      var0.headers().add(var1, var2);
   }

   public static int getIntHeader(HttpMessage var0, CharSequence var1) {
      String var2 = getHeader(var0, var1);
      if (var2 == null) {
         throw new NumberFormatException("header not found: " + var1);
      } else {
         return Integer.parseInt(var2);
      }
   }

   public static void addDateHeader(HttpMessage var0, String var1, Date var2) {
      var0.headers().add(var1, var2);
   }

   public static void setContentLength(HttpMessage var0, long var1) {
      var0.headers().set(CONTENT_LENGTH_ENTITY, var1);
   }

   public static void removeHeader(HttpMessage var0, String var1) {
      var0.headers().remove(var1);
   }

   public static boolean encodeAscii(CharSequence var0, ByteBuf var1) {
      if (var0 instanceof HttpHeaderEntity) {
         return ((HttpHeaderEntity)var0).encode(var1);
      } else {
         encodeAscii0(var0, var1);
         return false;
      }
   }

   public static Date getDateHeader(HttpMessage var0, CharSequence var1, Date var2) {
      String var3 = getHeader(var0, var1);
      if (var3 == null) {
         return var2;
      } else {
         try {
            return HttpHeaderDateFormat.get().parse(var3);
         } catch (ParseException var5) {
            return var2;
         }
      }
   }

   public HttpHeaders add(CharSequence var1, Object var2) {
      return this.add(var1.toString(), var2);
   }

   public static void setHost(HttpMessage var0, CharSequence var1) {
      var0.headers().set(HOST_ENTITY, var1);
   }

   public static void encode(CharSequence var0, CharSequence var1, ByteBuf var2) {
      if (!encodeAscii(var0, var2)) {
         var2.writeBytes(HEADER_SEPERATOR);
      }

      if (!encodeAscii(var1, var2)) {
         var2.writeBytes(CRLF);
      }
   }

   public static long getContentLength(HttpMessage var0) {
      String var1 = getHeader(var0, CONTENT_LENGTH_ENTITY);
      if (var1 != null) {
         return Long.parseLong(var1);
      } else {
         long var2 = getWebSocketContentLength(var0);
         if (var2 >= (19465728L & -4401746714674718643L)) {
            return var2;
         } else {
            throw new NumberFormatException("header not found: Content-Length");
         }
      }
   }

   public abstract HttpHeaders set(String var1, Iterable<?> var2);

   public HttpHeaders add(CharSequence var1, Iterable<?> var2) {
      return this.add(var1.toString(), var2);
   }

   public static int hash(CharSequence var0) {
      if (var0 instanceof HttpHeaderEntity) {
         return ((HttpHeaderEntity)var0).hash();
      } else {
         int var1 = 0;

         for (int var2 = var0.length() - 1; var2 >= 0; var2--) {
            char var3 = var0.charAt(var2);
            if (var3 >= 'A' && var3 <= 'Z') {
               var3 = (char)(var3 + ' ');
            }

            var1 = 31 * var1 + var3;
         }

         if (var1 > 0) {
            return var1;
         } else {
            return var1 == Integer.MIN_VALUE ? Integer.MAX_VALUE : -var1;
         }
      }
   }

   public static void setTransferEncodingChunked(HttpMessage var0) {
      addHeader(var0, TRANSFER_ENCODING_ENTITY, CHUNKED_ENTITY);
      removeHeader(var0, CONTENT_LENGTH_ENTITY);
   }

   public static String getHeader(HttpMessage var0, CharSequence var1, String var2) {
      String var3 = var0.headers().get(var1);
      return var3 == null ? var2 : var3;
   }

   public static boolean is100ContinueExpected(HttpMessage var0) {
      if (!(var0 instanceof HttpRequest)) {
         return false;
      } else if (var0.getProtocolVersion().compareTo(HttpVersion.HTTP_1_1) < 0) {
         return false;
      } else {
         String var1 = var0.headers().get(EXPECT_ENTITY);
         if (var1 == null) {
            return false;
         } else {
            return equalsIgnoreCase(CONTINUE_ENTITY, var1) ? true : var0.headers().contains(EXPECT_ENTITY, CONTINUE_ENTITY, true);
         }
      }
   }
}
