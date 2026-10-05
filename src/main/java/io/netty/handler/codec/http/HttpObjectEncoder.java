package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.FileRegion;
import io.netty.channel.epoll.EpollSocketChannelConfig;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;
import java.util.List;
import net.minecraft.entity.monster.EntitySlime;

public abstract class HttpObjectEncoder<H extends HttpMessage> extends MessageToMessageEncoder<Object> {
   public static final int ST_CONTENT_NON_CHUNK = 1;
   public static final int ST_INIT = 0;
   public int state = 0;
   public static final int ST_CONTENT_CHUNK = 2;
   public static byte[] CRLF = new byte[]{13, 10};
   public static byte[] ZERO_CRLF = new byte[]{48, 13, 10};
   public static byte[] ZERO_CRLF_CRLF = new byte[]{48, 13, 10, 13, 10};
   public static ByteBuf CRLF_BUF = Unpooled.unreleasableBuffer(Unpooled.directBuffer(HttpObjectEncoder.CRLF.length).writeBytes(HttpObjectEncoder.CRLF));
   public static ByteBuf ZERO_CRLF_CRLF_BUF = Unpooled.unreleasableBuffer(
      Unpooled.directBuffer(HttpObjectEncoder.ZERO_CRLF_CRLF.length).writeBytes(HttpObjectEncoder.ZERO_CRLF_CRLF)
   );

   @Override
   public void encode(ChannelHandlerContext var1, Object var2, List<Object> var3) throws java.lang.Exception {
      ByteBuf var4 = null;
      if (var2 instanceof HttpMessage) {
         if (this.state != 0) {
            throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(var2));
         }

         HttpMessage var5 = (HttpMessage)var2;
         var4 = var1.alloc().buffer();
         this.encodeInitialLine(var4, (H)var5);
         HttpHeaders.encode(var5.headers(), var4);
         var4.writeBytes(CRLF);
         this.state = HttpHeaders.isTransferEncodingChunked(var5) ? 2 : 1;
      }

      if (!(var2 instanceof HttpContent) && !(var2 instanceof ByteBuf) && !(var2 instanceof FileRegion)) {
         if (var4 != null) {
            var3.add(var4);
         }
      } else {
         if (this.state == 0) {
            throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(var2));
         }

         long var7 = contentLength(var2);
         if (this.state == 1) {
            if (var7 > 0L) {
               if (var4 != null && var4.writableBytes() >= var7 && var2 instanceof HttpContent) {
                  var4.writeBytes(((HttpContent)var2).content());
                  var3.add(var4);
               } else {
                  if (var4 != null) {
                     var3.add(var4);
                  }

                  var3.add(encodeAndRetain(var2));
               }
            } else if (var4 != null) {
               var3.add(var4);
            } else {
               var3.add(Unpooled.EMPTY_BUFFER);
            }

            if (var2 instanceof LastHttpContent) {
               this.state = 0;
            }
         } else {
            if (this.state != 2) {
               throw new Error();
            }

            if (var4 != null) {
               var3.add(var4);
            }

            this.encodeChunkedContent(var1, var2, var7, var3);
         }
      }
   }

   public abstract void encodeInitialLine(ByteBuf var1, H var2) throws java.lang.Exception ;

   public void encodeChunkedContent(ChannelHandlerContext var1, Object var2, long var3, List<Object> var5) {
      if (var3 > 0L) {
         byte[] var6 = Long.toHexString(var3).getBytes(CharsetUtil.US_ASCII);
         ByteBuf var7 = var1.alloc().buffer(var6.length + 2);
         var7.writeBytes(var6);
         var7.writeBytes(CRLF);
         var5.add(var7);
         var5.add(encodeAndRetain(var2));
         var5.add(CRLF_BUF.duplicate());
      }

      if (var2 instanceof LastHttpContent) {
         HttpHeaders var8 = ((LastHttpContent)var2).trailingHeaders();
         if (var8.isEmpty()) {
            var5.add(ZERO_CRLF_CRLF_BUF.duplicate());
         } else {
            ByteBuf var9 = var1.alloc().buffer();
            var9.writeBytes(ZERO_CRLF);
            HttpHeaders.encode(var8, var9);
            var9.writeBytes(CRLF);
            var5.add(var9);
         }

         this.state = 0;
      } else if (var3 == 0L) {
         var5.add(Unpooled.EMPTY_BUFFER);
      }
   }

   public static long contentLength(Object var0) {
      if (var0 instanceof HttpContent) {
         return ((HttpContent)var0).content().readableBytes();
      } else if (var0 instanceof ByteBuf) {
         return ((ByteBuf)var0).readableBytes();
      } else if (var0 instanceof FileRegion) {
         return ((FileRegion)var0).count();
      } else {
         throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(var0));
      }
   }

   @Override
   public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
      return var1 instanceof HttpObject || var1 instanceof ByteBuf || var1 instanceof FileRegion;
   }

   public static Object encodeAndRetain(Object var0) {
      if (var0 instanceof ByteBuf) {
         return ((ByteBuf)var0).retain();
      } else if (var0 instanceof HttpContent) {
         return ((HttpContent)var0).content().retain();
      } else if (var0 instanceof FileRegion) {
         return ((FileRegion)var0).retain();
      } else {
         throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(var0));
      }
   }

   public static void encodeAscii(String var0, ByteBuf var1) {
      HttpHeaders.encodeAscii0(var0, var1);
   }
}
