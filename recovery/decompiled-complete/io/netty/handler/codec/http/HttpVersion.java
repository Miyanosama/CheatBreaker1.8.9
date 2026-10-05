package io.netty.handler.codec.http;

import com.cheatbreaker.client.util.title.Title;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f$Deserializer;
import net.minecraft.tileentity.TileEntityFlowerPot;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$26;

public class HttpVersion implements Comparable<HttpVersion> {
   public Title __junk4952660592229821817;
   public static Pattern VERSION_PATTERN = Pattern.compile("(\\S+)/(\\d+)\\.(\\d+)");
   public static HttpVersion HTTP_1_1 = new HttpVersion("HTTP", 1, 1, true, true);
   public byte[] bytes;
   public int minorVersion;
   public static HttpVersion HTTP_1_0 = new HttpVersion("HTTP", 1, 0, false, true);
   public boolean keepAliveDefault;
   public TileEntityFlowerPot __junk6767416870046045567;
   public String text;
   public int majorVersion;
   public LogBrokerMonitor$26 __junk8407337170530536681;
   public String protocolName;
   public static String HTTP_1_0_STRING;
   public static String HTTP_1_1_STRING;
   public ItemTransformVec3f$Deserializer __junk8124095775319328616;

   @Override
   public String toString() {
      return this.text();
   }

   public HttpVersion(String var1, int var2, int var3, boolean var4, boolean var5) {
      if (var1 == null) {
         throw new NullPointerException("protocolName");
      } else {
         var1 = var1.trim().toUpperCase();
         if (var1.isEmpty()) {
            throw new IllegalArgumentException("empty protocolName");
         } else {
            for (int var6 = 0; var6 < var1.length(); var6++) {
               if (Character.isISOControl(var1.charAt(var6)) || Character.isWhitespace(var1.charAt(var6))) {
                  throw new IllegalArgumentException("invalid character in protocolName");
               }
            }

            if (var2 < 0) {
               throw new IllegalArgumentException("negative majorVersion");
            } else if (var3 < 0) {
               throw new IllegalArgumentException("negative minorVersion");
            } else {
               this.protocolName = var1;
               this.majorVersion = var2;
               this.minorVersion = var3;
               this.text = var1 + '/' + var2 + '.' + var3;
               this.keepAliveDefault = var4;
               if (var5) {
                  this.bytes = this.text.getBytes(CharsetUtil.US_ASCII);
               } else {
                  this.bytes = null;
               }
            }
         }
      }
   }

   public boolean isKeepAliveDefault() {
      return this.keepAliveDefault;
   }

   public String text() {
      return this.text;
   }

   public int minorVersion() {
      return this.minorVersion;
   }

   public int majorVersion() {
      return this.majorVersion;
   }

   public HttpVersion(String var1, int var2, int var3, boolean var4) {
      this(var1, var2, var3, var4, false);
   }

   public String protocolName() {
      return this.protocolName;
   }

   public void encode(ByteBuf var1) {
      if (this.bytes == null) {
         HttpHeaders.encodeAscii0(this.text, var1);
      } else {
         var1.writeBytes(this.bytes);
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof HttpVersion)) {
         return false;
      } else {
         HttpVersion var2 = (HttpVersion)var1;
         return this.minorVersion() == var2.minorVersion() && this.majorVersion() == var2.majorVersion() && this.protocolName().equals(var2.protocolName());
      }
   }

   public static HttpVersion version0(String var0) {
      if ("HTTP/1.1".equals(var0)) {
         return HTTP_1_1;
      } else {
         return "HTTP/1.0".equals(var0) ? HTTP_1_0 : null;
      }
   }

   @Override
   public int hashCode() {
      return (this.protocolName().hashCode() * 31 + this.majorVersion()) * 31 + this.minorVersion();
   }

   public static HttpVersion valueOf(String var0) {
      if (var0 == null) {
         throw new NullPointerException("text");
      } else {
         var0 = var0.trim();
         if (var0.isEmpty()) {
            throw new IllegalArgumentException("text is empty");
         } else {
            HttpVersion var1 = version0(var0);
            if (var1 == null) {
               var0 = var0.toUpperCase();
               var1 = version0(var0);
               if (var1 == null) {
                  var1 = new HttpVersion(var0, true);
               }
            }

            return var1;
         }
      }
   }

   public int compareTo(HttpVersion var1) {
      int var2 = this.protocolName().compareTo(var1.protocolName());
      if (var2 != 0) {
         return var2;
      } else {
         var2 = this.majorVersion() - var1.majorVersion();
         return var2 != 0 ? var2 : this.minorVersion() - var1.minorVersion();
      }
   }

   public HttpVersion(String var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("text");
      } else {
         var1 = var1.trim().toUpperCase();
         if (var1.isEmpty()) {
            throw new IllegalArgumentException("empty text");
         } else {
            Matcher var3 = VERSION_PATTERN.matcher(var1);
            if (!var3.matches()) {
               throw new IllegalArgumentException("invalid version format: " + var1);
            } else {
               this.protocolName = var3.group(1);
               this.majorVersion = Integer.parseInt(var3.group(2));
               this.minorVersion = Integer.parseInt(var3.group(3));
               this.text = this.protocolName + '/' + this.majorVersion + '.' + this.minorVersion;
               this.keepAliveDefault = var2;
               this.bytes = null;
            }
         }
      }
   }
}
