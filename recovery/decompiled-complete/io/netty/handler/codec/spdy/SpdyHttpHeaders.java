package io.netty.handler.codec.spdy;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.util.HashedWheelTimer$1;
import net.minecraft.client.network.NetworkPlayerInfo$2;

public class SpdyHttpHeaders {
   public NetworkPlayerInfo$2 __junk6430279531419832848;
   public HashedWheelTimer$1 __junk3955837724337542461;

   public static int getStreamId(HttpMessage var0) {
      return HttpHeaders.getIntHeader(var0, "X-SPDY-Stream-ID");
   }

   public static void setAssociatedToStreamId(HttpMessage var0, int var1) {
      HttpHeaders.setIntHeader(var0, "X-SPDY-Associated-To-Stream-ID", var1);
   }

   public static void setPriority(HttpMessage var0, byte var1) {
      HttpHeaders.setIntHeader(var0, "X-SPDY-Priority", var1);
   }

   public static byte getPriority(HttpMessage var0) {
      return (byte)HttpHeaders.getIntHeader(var0, "X-SPDY-Priority", 0);
   }

   public static String getScheme(HttpMessage var0) {
      return var0.headers().get("X-SPDY-Scheme");
   }

   public static void removePriority(HttpMessage var0) {
      var0.headers().remove("X-SPDY-Priority");
   }

   public static void setScheme(HttpMessage var0, String var1) {
      var0.headers().set("X-SPDY-Scheme", var1);
   }

   public static void removeAssociatedToStreamId(HttpMessage var0) {
      var0.headers().remove("X-SPDY-Associated-To-Stream-ID");
   }

   public static void removeScheme(HttpMessage var0) {
      var0.headers().remove("X-SPDY-Scheme");
   }

   public static void removeUrl(HttpMessage var0) {
      var0.headers().remove("X-SPDY-URL");
   }

   public static void setStreamId(HttpMessage var0, int var1) {
      HttpHeaders.setIntHeader(var0, "X-SPDY-Stream-ID", var1);
   }

   public static void removeStreamId(HttpMessage var0) {
      var0.headers().remove("X-SPDY-Stream-ID");
   }

   public static String getUrl(HttpMessage var0) {
      return var0.headers().get("X-SPDY-URL");
   }

   public static int getAssociatedToStreamId(HttpMessage var0) {
      return HttpHeaders.getIntHeader(var0, "X-SPDY-Associated-To-Stream-ID", 0);
   }

   public static void setUrl(HttpMessage var0, String var1) {
      var0.headers().set("X-SPDY-URL", var1);
   }
}
