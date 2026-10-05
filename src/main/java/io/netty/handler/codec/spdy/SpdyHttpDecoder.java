package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.renderer.block.model.BreakingFour;
import org.apache.log4j.spi.DefaultRepositorySelector;

public class SpdyHttpDecoder extends MessageToMessageDecoder<SpdyFrame> {
   public int spdyVersion;
   public Map<Integer, FullHttpMessage> messageMap;
   public int maxContentLength;
   public boolean validateHeaders;

   public static FullHttpRequest createHttpRequest(int var0, SpdyHeadersFrame var1) throws java.lang.Exception {
      SpdyHeaders var2 = var1.headers();
      HttpMethod var3 = SpdyHeaders.getMethod(var0, var1);
      String var4 = SpdyHeaders.getUrl(var0, var1);
      HttpVersion var5 = SpdyHeaders.getVersion(var0, var1);
      SpdyHeaders.removeMethod(var0, var1);
      SpdyHeaders.removeUrl(var0, var1);
      SpdyHeaders.removeVersion(var0, var1);
      DefaultFullHttpRequest var6 = new DefaultFullHttpRequest(var5, var3, var4);
      SpdyHeaders.removeScheme(var0, var1);
      String var7 = var2.get(":host");
      var2.remove(":host");
      var6.headers().set("Host", var7);

      for (Entry var9 : var1.headers()) {
         var6.headers().add((String)var9.getKey(), var9.getValue());
      }

      HttpHeaders.setKeepAlive(var6, true);
      var6.headers().remove("Transfer-Encoding");
      return var6;
   }

   public SpdyHttpDecoder(SpdyVersion var1, int var2, Map<Integer, FullHttpMessage> var3, boolean var4) {
      if (var1 == null) {
         throw new NullPointerException("version");
      } else if (var2 <= 0) {
         throw new IllegalArgumentException("maxContentLength must be a positive integer: " + var2);
      } else {
         this.spdyVersion = var1.getVersion();
         this.maxContentLength = var2;
         this.messageMap = var3;
         this.validateHeaders = var4;
      }
   }

   public static FullHttpResponse createHttpResponse(ChannelHandlerContext var0, int var1, SpdyHeadersFrame var2, boolean var3) throws java.lang.Exception {
      HttpResponseStatus var4 = SpdyHeaders.getStatus(var1, var2);
      HttpVersion var5 = SpdyHeaders.getVersion(var1, var2);
      SpdyHeaders.removeStatus(var1, var2);
      SpdyHeaders.removeVersion(var1, var2);
      DefaultFullHttpResponse var6 = new DefaultFullHttpResponse(var5, var4, var0.alloc().buffer(), var3);

      for (Entry var8 : var2.headers()) {
         var6.headers().add((String)var8.getKey(), var8.getValue());
      }

      HttpHeaders.setKeepAlive(var6, true);
      var6.headers().remove("Transfer-Encoding");
      var6.headers().remove("Trailer");
      return var6;
   }

   public FullHttpMessage putMessage(int var1, FullHttpMessage var2) {
      return this.messageMap.put(var1, var2);
   }

   public SpdyHttpDecoder(SpdyVersion var1, int var2) {
      this(var1, var2, new HashMap<>(), true);
   }

   public void decode(ChannelHandlerContext var1, SpdyFrame var2, List<Object> var3) throws java.lang.Exception {
      if (var2 instanceof SpdySynStreamFrame) {
         SpdySynStreamFrame var4 = (SpdySynStreamFrame)var2;
         int var5 = var4.streamId();
         if (SpdyCodecUtil.isServerId(var5)) {
            int var6 = var4.associatedStreamId();
            if (var6 == 0) {
               DefaultSpdyRstStreamFrame var27 = new DefaultSpdyRstStreamFrame(var5, SpdyStreamStatus.INVALID_STREAM);
               var1.writeAndFlush(var27);
               return;
            }

            String var7 = SpdyHeaders.getUrl(this.spdyVersion, var4);
            if (var7 == null) {
               DefaultSpdyRstStreamFrame var33 = new DefaultSpdyRstStreamFrame(var5, SpdyStreamStatus.PROTOCOL_ERROR);
               var1.writeAndFlush(var33);
               return;
            }

            if (var4.isTruncated()) {
               DefaultSpdyRstStreamFrame var32 = new DefaultSpdyRstStreamFrame(var5, SpdyStreamStatus.INTERNAL_ERROR);
               var1.writeAndFlush(var32);
               return;
            }

            try {
               FullHttpResponse var8 = createHttpResponse(var1, this.spdyVersion, var4, this.validateHeaders);
               SpdyHttpHeaders.setStreamId(var8, var5);
               SpdyHttpHeaders.setAssociatedToStreamId(var8, var6);
               SpdyHttpHeaders.setPriority(var8, var4.priority());
               SpdyHttpHeaders.setUrl(var8, var7);
               if (var4.isLast()) {
                  HttpHeaders.setContentLength(var8, 0L);
                  var3.add(var8);
               } else {
                  this.putMessage(var5, var8);
               }
            } catch (Exception var12) {
               DefaultSpdyRstStreamFrame var9 = new DefaultSpdyRstStreamFrame(var5, SpdyStreamStatus.PROTOCOL_ERROR);
               var1.writeAndFlush(var9);
            }
         } else {
            if (var4.isTruncated()) {
               DefaultSpdySynReplyFrame var22 = new DefaultSpdySynReplyFrame(var5);
               var22.setLast(true);
               SpdyHeaders.setStatus(this.spdyVersion, var22, HttpResponseStatus.REQUEST_HEADER_FIELDS_TOO_LARGE);
               SpdyHeaders.setVersion(this.spdyVersion, var22, HttpVersion.HTTP_1_0);
               var1.writeAndFlush(var22);
               return;
            }

            try {
               FullHttpRequest var21 = createHttpRequest(this.spdyVersion, var4);
               SpdyHttpHeaders.setStreamId(var21, var5);
               if (var4.isLast()) {
                  var3.add(var21);
               } else {
                  this.putMessage(var5, var21);
               }
            } catch (Exception var11) {
               DefaultSpdySynReplyFrame var28 = new DefaultSpdySynReplyFrame(var5);
               var28.setLast(true);
               SpdyHeaders.setStatus(this.spdyVersion, var28, HttpResponseStatus.BAD_REQUEST);
               SpdyHeaders.setVersion(this.spdyVersion, var28, HttpVersion.HTTP_1_0);
               var1.writeAndFlush(var28);
            }
         }
      } else if (var2 instanceof SpdySynReplyFrame) {
         SpdySynReplyFrame var13 = (SpdySynReplyFrame)var2;
         int var17 = var13.streamId();
         if (var13.isTruncated()) {
            DefaultSpdyRstStreamFrame var24 = new DefaultSpdyRstStreamFrame(var17, SpdyStreamStatus.INTERNAL_ERROR);
            var1.writeAndFlush(var24);
            return;
         }

         try {
            FullHttpResponse var23 = createHttpResponse(var1, this.spdyVersion, var13, this.validateHeaders);
            SpdyHttpHeaders.setStreamId(var23, var17);
            if (var13.isLast()) {
               HttpHeaders.setContentLength(var23, 0L);
               var3.add(var23);
            } else {
               this.putMessage(var17, var23);
            }
         } catch (Exception var10) {
            DefaultSpdyRstStreamFrame var29 = new DefaultSpdyRstStreamFrame(var17, SpdyStreamStatus.PROTOCOL_ERROR);
            var1.writeAndFlush(var29);
         }
      } else if (var2 instanceof SpdyHeadersFrame) {
         SpdyHeadersFrame var14 = (SpdyHeadersFrame)var2;
         int var18 = var14.streamId();
         FullHttpMessage var25 = this.getMessage(var18);
         if (var25 == null) {
            return;
         }

         if (!var14.isTruncated()) {
            for (Entry var34 : var14.headers()) {
               var25.headers().add((String)var34.getKey(), var34.getValue());
            }
         }

         if (var14.isLast()) {
            HttpHeaders.setContentLength(var25, var25.content().readableBytes());
            this.removeMessage(var18);
            var3.add(var25);
         }
      } else if (var2 instanceof SpdyDataFrame) {
         SpdyDataFrame var15 = (SpdyDataFrame)var2;
         int var19 = var15.streamId();
         FullHttpMessage var26 = this.getMessage(var19);
         if (var26 == null) {
            return;
         }

         ByteBuf var31 = var26.content();
         if (var31.readableBytes() > this.maxContentLength - var15.content().readableBytes()) {
            this.removeMessage(var19);
            throw new TooLongFrameException("HTTP content length exceeded " + this.maxContentLength + " bytes.");
         }

         ByteBuf var35 = var15.content();
         int var36 = var35.readableBytes();
         var31.writeBytes(var35, var35.readerIndex(), var36);
         if (var15.isLast()) {
            HttpHeaders.setContentLength(var26, var31.readableBytes());
            this.removeMessage(var19);
            var3.add(var26);
         }
      } else if (var2 instanceof SpdyRstStreamFrame) {
         SpdyRstStreamFrame var16 = (SpdyRstStreamFrame)var2;
         int var20 = var16.streamId();
         this.removeMessage(var20);
      }
   }

   public FullHttpMessage getMessage(int var1) {
      return this.messageMap.get(var1);
   }

   public SpdyHttpDecoder(SpdyVersion var1, int var2, boolean var3) {
      this(var1, var2, new HashMap<>(), var3);
   }

   public FullHttpMessage removeMessage(int var1) {
      return this.messageMap.remove(var1);
   }

   public SpdyHttpDecoder(SpdyVersion var1, int var2, Map<Integer, FullHttpMessage> var3) {
      this(var1, var2, var3, true);
   }
}
