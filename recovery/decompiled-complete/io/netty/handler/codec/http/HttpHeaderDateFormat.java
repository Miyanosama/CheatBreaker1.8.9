package io.netty.handler.codec.http;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceKeysToLongTask;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import net.minecraft.server.management.ServerConfigurationManager$1;
import org.java_websocket.util.ByteBufferUtils;

public class HttpHeaderDateFormat extends SimpleDateFormat {
   public ByteBufferUtils __junk856120257498779463;
   public static FastThreadLocal<HttpHeaderDateFormat> dateFormatThreadLocal = new HttpHeaderDateFormat$1();
   public ConcurrentHashMapV8$MapReduceKeysToLongTask __junk3699974041003030183;
   public ServerConfigurationManager$1 __junk8217318130506225152;
   public static long serialVersionUID;
   public SimpleDateFormat format1 = new HttpHeaderDateFormat$HttpHeaderDateFormatObsolete1();
   public SimpleDateFormat format2 = new HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2();

   public static HttpHeaderDateFormat get() {
      return dateFormatThreadLocal.get();
   }

   public HttpHeaderDateFormat() {
      super("E, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
      this.setTimeZone(TimeZone.getTimeZone("GMT"));
   }

   @Override
   public Date parse(String var1, ParsePosition var2) {
      Date var3 = super.parse(var1, var2);
      if (var3 == null) {
         var3 = this.format1.parse(var1, var2);
      }

      if (var3 == null) {
         var3 = this.format2.parse(var1, var2);
      }

      return var3;
   }
}
