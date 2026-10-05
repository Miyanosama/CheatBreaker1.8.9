package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.spdy.SpdySessionHandler$ClosingChannelFutureListener;
import io.netty.util.CharsetUtil;
import net.minecraft.util.AxisAlignedBB;
import org.apache.log4j.LogSF;

public class HttpRequestEncoder extends HttpObjectEncoder<HttpRequest> {
   public static byte[] CRLF = new byte[]{13, 10};
   public static char QUESTION_MARK;
   public LogSF __junk11562579428509951;
   public static char SLASH;
   public AxisAlignedBB __junk2282932746450275702;
   public SpdySessionHandler$ClosingChannelFutureListener __junk1334505515163607357;

   public void encodeInitialLine(ByteBuf var1, HttpRequest var2) {
      var2.getMethod().encode(var1);
      var1.writeByte(32);
      String var3 = var2.getUri();
      if (var3.length() == 0) {
         var3 = var3 + '/';
      } else {
         int var4 = var3.indexOf("://");
         if (var4 != -1 && var3.charAt(0) != '/') {
            int var5 = var4 + 3;
            int var6 = var3.indexOf(63, var5);
            if (var6 == -1) {
               if (var3.lastIndexOf(47) <= var5) {
                  var3 = var3 + '/';
               }
            } else if (var3.lastIndexOf(47, var6) <= var5) {
               int var7 = var3.length();
               StringBuilder var8 = new StringBuilder(var7 + 1);
               var8.append(var3, 0, var6);
               var8.append('/');
               var8.append(var3, var6, var7);
               var3 = var8.toString();
            }
         }
      }

      var1.writeBytes(var3.getBytes(CharsetUtil.UTF_8));
      var1.writeByte(32);
      var2.getProtocolVersion().encode(var1);
      var1.writeBytes(CRLF);
   }

   @Override
   public boolean acceptOutboundMessage(Object var1) {
      return super.acceptOutboundMessage(var1) && !(var1 instanceof HttpResponse);
   }
}
