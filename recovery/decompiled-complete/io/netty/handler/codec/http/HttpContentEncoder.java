package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.ReferenceCountUtil;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import net.minecraft.stats.StatBase$3;
import net.minecraft.util.ChatComponentStyle;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$1;

public abstract class HttpContentEncoder extends MessageToMessageCodec<HttpRequest, HttpObject> {
   public EmbeddedChannel encoder;
   public StatBase$3 __junk2768360627438831687;
   public ChatComponentStyle __junk2341152416334999239;
   public Queue<String> acceptEncodingQueue = new ArrayDeque<>();
   public HttpContentEncoder$State state = HttpContentEncoder$State.AWAIT_HEADERS;
   public CategoryNodeEditor$1 __junk5102432418026475872;
   public String acceptEncoding;

   public void fetchEncoderOutput(List<Object> var1) {
      while (true) {
         ByteBuf var2 = (ByteBuf)this.encoder.readOutbound();
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

   @Override
   public boolean acceptOutboundMessage(Object var1) {
      return var1 instanceof HttpContent || var1 instanceof HttpResponse;
   }

   public void finishEncode(List<Object> var1) {
      if (this.encoder.finish()) {
         this.fetchEncoderOutput(var1);
      }

      this.encoder = null;
   }

   public void decode(ChannelHandlerContext var1, HttpRequest var2, List<Object> var3) {
      String var4 = var2.headers().get("Accept-Encoding");
      if (var4 == null) {
         var4 = "identity";
      }

      this.acceptEncodingQueue.add(var4);
      var3.add(ReferenceCountUtil.retain(var2));
   }

   public void encode(ChannelHandlerContext var1, HttpObject var2, List<Object> var3) {
      boolean var4 = var2 instanceof HttpResponse && var2 instanceof LastHttpContent;
      switch (HttpContentEncoder$1.$SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State[this.state.ordinal()]) {
         case 1:
            ensureHeaders(var2);
            if (!$assertionsDisabled && this.encoder != null) {
               throw new AssertionError();
            }

            HttpResponse var5 = (HttpResponse)var2;
            if (var5.getStatus().code() == 100) {
               if (var4) {
                  var3.add(ReferenceCountUtil.retain(var5));
               } else {
                  var3.add(var5);
                  this.state = HttpContentEncoder$State.PASS_THROUGH;
               }
               break;
            } else {
               this.acceptEncoding = this.acceptEncodingQueue.poll();
               if (this.acceptEncoding == null) {
                  throw new IllegalStateException("cannot send more responses than requests");
               }

               if (var4 && !((ByteBufHolder)var5).content().isReadable()) {
                  var3.add(ReferenceCountUtil.retain(var5));
                  break;
               } else {
                  HttpContentEncoder$Result var6 = this.beginEncode(var5, this.acceptEncoding);
                  if (var6 == null) {
                     if (var4) {
                        var3.add(ReferenceCountUtil.retain(var5));
                     } else {
                        var3.add(var5);
                        this.state = HttpContentEncoder$State.PASS_THROUGH;
                     }
                     break;
                  } else {
                     this.encoder = var6.contentEncoder();
                     var5.headers().set("Content-Encoding", var6.targetContentEncoding());
                     var5.headers().remove("Content-Length");
                     var5.headers().set("Transfer-Encoding", "chunked");
                     if (var4) {
                        DefaultHttpResponse var7 = new DefaultHttpResponse(var5.getProtocolVersion(), var5.getStatus());
                        var7.headers().set(var5.headers());
                        var3.add(var7);
                     } else {
                        var3.add(var5);
                        this.state = HttpContentEncoder$State.AWAIT_CONTENT;
                        if (!(var2 instanceof HttpContent)) {
                           break;
                        }
                     }
                  }
               }
            }
         case 2:
            ensureContent(var2);
            if (this.encodeContent((HttpContent)var2, var3)) {
               this.state = HttpContentEncoder$State.AWAIT_HEADERS;
            }
            break;
         case 3:
            ensureContent(var2);
            var3.add(ReferenceCountUtil.retain(var2));
            if (var2 instanceof LastHttpContent) {
               this.state = HttpContentEncoder$State.AWAIT_HEADERS;
            }
      }
   }

   public void cleanup() {
      if (this.encoder != null) {
         if (this.encoder.finish()) {
            while (true) {
               ByteBuf var1 = (ByteBuf)this.encoder.readOutbound();
               if (var1 == null) {
                  break;
               }

               var1.release();
            }
         }

         this.encoder = null;
      }
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) {
      this.cleanup();
      super.handlerRemoved(var1);
   }

   public static void ensureContent(HttpObject var0) {
      if (!(var0 instanceof HttpContent)) {
         throw new IllegalStateException("unexpected message type: " + var0.getClass().getName() + " (expected: " + HttpContent.class.getSimpleName() + ')');
      }
   }

   public abstract HttpContentEncoder$Result beginEncode(HttpResponse var1, String var2);

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      this.cleanup();
      super.channelInactive(var1);
   }

   public static void ensureHeaders(HttpObject var0) {
      if (!(var0 instanceof HttpResponse)) {
         throw new IllegalStateException("unexpected message type: " + var0.getClass().getName() + " (expected: " + HttpResponse.class.getSimpleName() + ')');
      }
   }

   public void encode(ByteBuf var1, List<Object> var2) {
      this.encoder.writeOutbound(var1.retain());
      this.fetchEncoderOutput(var2);
   }

   public boolean encodeContent(HttpContent var1, List<Object> var2) {
      ByteBuf var3 = var1.content();
      this.encode(var3, var2);
      if (var1 instanceof LastHttpContent) {
         this.finishEncode(var2);
         LastHttpContent var4 = (LastHttpContent)var1;
         HttpHeaders var5 = var4.trailingHeaders();
         if (var5.isEmpty()) {
            var2.add(LastHttpContent.EMPTY_LAST_CONTENT);
         } else {
            var2.add(new ComposedLastHttpContent(var5));
         }

         return true;
      } else {
         return false;
      }
   }
}
