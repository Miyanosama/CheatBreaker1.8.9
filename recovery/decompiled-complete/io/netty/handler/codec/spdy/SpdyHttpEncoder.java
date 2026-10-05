package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBufProcessor$8;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import org.java_websocket.server.SSLParametersWebSocketServerFactory;

public class SpdyHttpEncoder extends MessageToMessageEncoder<HttpObject> {
   public WorldGenTaiga1 __junk8920164089872534655;
   public SSLParametersWebSocketServerFactory __junk4711670501356729443;
   public int spdyVersion;
   public C16PacketClientStatus$EnumState __junk2021779454916518631;
   public int currentStreamId;
   public ByteBufProcessor$8 __junk3205829830252879980;

   public SpdyHttpEncoder(SpdyVersion var1) {
      if (var1 == null) {
         throw new NullPointerException("version");
      } else {
         this.spdyVersion = var1.getVersion();
      }
   }

   public SpdySynStreamFrame createSynStreamFrame(HttpMessage var1) {
      int var2 = SpdyHttpHeaders.getStreamId(var1);
      int var3 = SpdyHttpHeaders.getAssociatedToStreamId(var1);
      byte var4 = SpdyHttpHeaders.getPriority(var1);
      String var5 = SpdyHttpHeaders.getUrl(var1);
      String var6 = SpdyHttpHeaders.getScheme(var1);
      SpdyHttpHeaders.removeStreamId(var1);
      SpdyHttpHeaders.removeAssociatedToStreamId(var1);
      SpdyHttpHeaders.removePriority(var1);
      SpdyHttpHeaders.removeUrl(var1);
      SpdyHttpHeaders.removeScheme(var1);
      var1.headers().remove("Connection");
      var1.headers().remove("Keep-Alive");
      var1.headers().remove("Proxy-Connection");
      var1.headers().remove("Transfer-Encoding");
      DefaultSpdySynStreamFrame var7 = new DefaultSpdySynStreamFrame(var2, var3, var4);
      if (var1 instanceof FullHttpRequest) {
         HttpRequest var8 = (HttpRequest)var1;
         SpdyHeaders.setMethod(this.spdyVersion, var7, var8.getMethod());
         SpdyHeaders.setUrl(this.spdyVersion, var7, var8.getUri());
         SpdyHeaders.setVersion(this.spdyVersion, var7, var1.getProtocolVersion());
      }

      if (var1 instanceof HttpResponse) {
         HttpResponse var10 = (HttpResponse)var1;
         SpdyHeaders.setStatus(this.spdyVersion, var7, var10.getStatus());
         SpdyHeaders.setUrl(this.spdyVersion, var7, var5);
         SpdyHeaders.setVersion(this.spdyVersion, var7, var1.getProtocolVersion());
         var7.setUnidirectional(true);
      }

      if (this.spdyVersion >= 3) {
         String var11 = HttpHeaders.getHost(var1);
         var1.headers().remove("Host");
         SpdyHeaders.setHost(var7, var11);
      }

      if (var6 == null) {
         var6 = "https";
      }

      SpdyHeaders.setScheme(this.spdyVersion, var7, var6);

      for (Entry var9 : var1.headers()) {
         var7.headers().add((String)var9.getKey(), var9.getValue());
      }

      this.currentStreamId = var7.streamId();
      var7.setLast(isLast(var1));
      return var7;
   }

   public void encode(ChannelHandlerContext var1, HttpObject var2, List<Object> var3) {
      boolean var4 = false;
      boolean var5 = false;
      if (var2 instanceof HttpRequest) {
         HttpRequest var6 = (HttpRequest)var2;
         SpdySynStreamFrame var7 = this.createSynStreamFrame(var6);
         var3.add(var7);
         var5 = var7.isLast();
         var4 = true;
      }

      if (var2 instanceof HttpResponse) {
         HttpResponse var13 = (HttpResponse)var2;
         if (var13.headers().contains("X-SPDY-Associated-To-Stream-ID")) {
            SpdySynStreamFrame var15 = this.createSynStreamFrame(var13);
            var5 = var15.isLast();
            var3.add(var15);
         } else {
            SpdySynReplyFrame var16 = this.createSynReplyFrame(var13);
            var5 = var16.isLast();
            var3.add(var16);
         }

         var4 = true;
      }

      if (var2 instanceof HttpContent && !var5) {
         HttpContent var14 = (HttpContent)var2;
         var14.content().retain();
         DefaultSpdyDataFrame var17 = new DefaultSpdyDataFrame(this.currentStreamId, var14.content());
         var17.setLast(var14 instanceof LastHttpContent);
         if (!(var14 instanceof LastHttpContent)) {
            var3.add(var17);
         } else {
            LastHttpContent var8 = (LastHttpContent)var14;
            HttpHeaders var9 = var8.trailingHeaders();
            if (var9.isEmpty()) {
               var3.add(var17);
            } else {
               DefaultSpdyHeadersFrame var10 = new DefaultSpdyHeadersFrame(this.currentStreamId);

               for (Entry var12 : var9) {
                  var10.headers().add((String)var12.getKey(), var12.getValue());
               }

               var3.add(var10);
               var3.add(var17);
            }
         }

         var4 = true;
      }

      if (!var4) {
         throw new UnsupportedMessageTypeException(var2);
      }
   }

   public SpdySynReplyFrame createSynReplyFrame(HttpResponse var1) {
      int var2 = SpdyHttpHeaders.getStreamId(var1);
      SpdyHttpHeaders.removeStreamId(var1);
      var1.headers().remove("Connection");
      var1.headers().remove("Keep-Alive");
      var1.headers().remove("Proxy-Connection");
      var1.headers().remove("Transfer-Encoding");
      DefaultSpdySynReplyFrame var3 = new DefaultSpdySynReplyFrame(var2);
      SpdyHeaders.setStatus(this.spdyVersion, var3, var1.getStatus());
      SpdyHeaders.setVersion(this.spdyVersion, var3, var1.getProtocolVersion());

      for (Entry var5 : var1.headers()) {
         var3.headers().add((String)var5.getKey(), var5.getValue());
      }

      this.currentStreamId = var2;
      var3.setLast(isLast(var1));
      return var3;
   }

   public static boolean isLast(HttpMessage var0) {
      if (var0 instanceof FullHttpMessage) {
         FullHttpMessage var1 = (FullHttpMessage)var0;
         if (var1.trailingHeaders().isEmpty() && !var1.content().isReadable()) {
            return true;
         }
      }

      return false;
   }
}
