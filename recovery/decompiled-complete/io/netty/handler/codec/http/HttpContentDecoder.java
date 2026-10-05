package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker00;
import io.netty.util.ReferenceCountUtil;
import java.util.List;

public abstract class HttpContentDecoder extends MessageToMessageDecoder<HttpObject> {
   public HttpMessage message;
   public boolean decodeStarted;
   public boolean continueResponse;
   public EmbeddedChannel decoder;
   public WebSocketClientHandshaker00 __junk5093604078793423692;

   public void finishDecode(List<Object> var1) {
      if (this.decoder.finish()) {
         this.fetchDecoderOutput(var1);
      }

      this.decodeStarted = false;
      this.decoder = null;
   }

   public void cleanup() {
      if (this.decoder != null) {
         if (this.decoder.finish()) {
            while (true) {
               ByteBuf var1 = (ByteBuf)this.decoder.readOutbound();
               if (var1 == null) {
                  break;
               }

               var1.release();
            }
         }

         this.decoder = null;
      }
   }

   public abstract EmbeddedChannel newContentDecoder(String var1);

   public void decode(ChannelHandlerContext var1, HttpObject var2, List<Object> var3) {
      if (var2 instanceof HttpResponse && ((HttpResponse)var2).getStatus().code() == 100) {
         if (!(var2 instanceof LastHttpContent)) {
            this.continueResponse = true;
         }

         var3.add(ReferenceCountUtil.retain(var2));
      } else if (this.continueResponse) {
         if (var2 instanceof LastHttpContent) {
            this.continueResponse = false;
         }

         var3.add(ReferenceCountUtil.retain(var2));
      } else {
         if (var2 instanceof HttpMessage) {
            if (!$assertionsDisabled && this.message != null) {
               throw new AssertionError();
            }

            this.message = (HttpMessage)var2;
            this.decodeStarted = false;
            this.cleanup();
         }

         if (var2 instanceof HttpContent) {
            HttpContent var4 = (HttpContent)var2;
            if (!this.decodeStarted) {
               this.decodeStarted = true;
               HttpMessage var5 = this.message;
               HttpHeaders var6 = var5.headers();
               this.message = null;
               String var7 = var6.get("Content-Encoding");
               if (var7 != null) {
                  var7 = var7.trim();
               } else {
                  var7 = "identity";
               }

               if ((this.decoder = this.newContentDecoder(var7)) != null) {
                  String var8 = this.getTargetContentEncoding(var7);
                  if ("identity".equals(var8)) {
                     var6.remove("Content-Encoding");
                  } else {
                     var6.set("Content-Encoding", var8);
                  }

                  var3.add(var5);
                  this.decodeContent(var4, var3);
                  if (var6.contains("Content-Length")) {
                     int var9 = 0;
                     int var10 = var3.size();

                     for (int var11 = 0; var11 < var10; var11++) {
                        Object var12 = var3.get(var11);
                        if (var12 instanceof HttpContent) {
                           var9 += ((HttpContent)var12).content().readableBytes();
                        }
                     }

                     var6.set("Content-Length", Integer.toString(var9));
                  }

                  return;
               }

               if (var4 instanceof LastHttpContent) {
                  this.decodeStarted = false;
               }

               var3.add(var5);
               var3.add(var4.retain());
               return;
            }

            if (this.decoder != null) {
               this.decodeContent(var4, var3);
            } else {
               if (var4 instanceof LastHttpContent) {
                  this.decodeStarted = false;
               }

               var3.add(var4.retain());
            }
         }
      }
   }

   public String getTargetContentEncoding(String var1) {
      return "identity";
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) {
      this.cleanup();
      super.handlerRemoved(var1);
   }

   public void fetchDecoderOutput(List<Object> var1) {
      while (true) {
         ByteBuf var2 = (ByteBuf)this.decoder.readInbound();
         if (var2 == null) {
            return;
         }

         if (!var2.isReadable()) {
            var2.release();
         } else {
            var1.add(new DefaultHttpContent(var2));
         }
      }
   }

   public void decode(ByteBuf var1, List<Object> var2) {
      this.decoder.writeInbound(var1.retain());
      this.fetchDecoderOutput(var2);
   }

   public void decodeContent(HttpContent var1, List<Object> var2) {
      ByteBuf var3 = var1.content();
      this.decode(var3, var2);
      if (var1 instanceof LastHttpContent) {
         this.finishDecode(var2);
         LastHttpContent var4 = (LastHttpContent)var1;
         HttpHeaders var5 = var4.trailingHeaders();
         if (var5.isEmpty()) {
            var2.add(LastHttpContent.EMPTY_LAST_CONTENT);
         } else {
            var2.add(new ComposedLastHttpContent(var5));
         }
      }
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      this.cleanup();
      super.channelInactive(var1);
   }
}
