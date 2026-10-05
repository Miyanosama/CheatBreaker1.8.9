package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.PrematureChannelClosureException;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameDecoder;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.UniqueName;
import java.util.List;
import net.minecraft.event.HoverEvent$Action;
import net.minecraft.world.biome.BiomeCache;
import net.optifine.NaturalProperties;
import net.optifine.entity.model.CustomEntityModelParser;

public class HttpClientCodec$Decoder extends HttpResponseDecoder {
   public WebSocket13FrameDecoder __junk6020672540178644032;
   public CustomEntityModelParser __junk1477756634616825644;
   public UniqueName __junk6610491255433132302;
   public BiomeCache __junk2631426650737779405;
   public NaturalProperties __junk9201503882459868214;
   public HttpRequestDecoder __junk7398500178044623675;
   public ReadTimeoutException __junk3577188243228272457;
   public HoverEvent$Action __junk2637436032260077848;

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      super.channelInactive(var1);
      if (HttpClientCodec.access$300(this.this$0)) {
         long var2 = HttpClientCodec.access$400(this.this$0).get();
         if (var2 > (8910956223661144065L & -8910956224929856912L)) {
            var1.fireExceptionCaught(new PrematureChannelClosureException("channel gone inactive with " + var2 + " missing response(s)"));
         }
      }
   }

   public void decrement(Object var1) {
      if (var1 != null) {
         if (var1 instanceof LastHttpContent) {
            HttpClientCodec.access$400(this.this$0).decrementAndGet();
         }
      }
   }

   public HttpClientCodec$Decoder(HttpClientCodec var1, int var2, int var3, int var4, boolean var5) {
      this.this$0 = var1;
      super(var2, var3, var4, var5);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      if (HttpClientCodec.access$100(this.this$0)) {
         int var4 = this.actualReadableBytes();
         if (var4 == 0) {
            return;
         }

         var3.add(var2.readBytes(var4));
      } else {
         int var7 = var3.size();
         super.decode(var1, var2, var3);
         if (HttpClientCodec.access$300(this.this$0)) {
            int var5 = var3.size();

            for (int var6 = var7; var6 < var5; var6++) {
               this.decrement(var3.get(var6));
            }
         }
      }
   }

   @Override
   public boolean isContentAlwaysEmpty(HttpMessage var1) {
      int var2 = ((HttpResponse)var1).getStatus().code();
      if (var2 == 100) {
         return true;
      } else {
         HttpMethod var3 = (HttpMethod)HttpClientCodec.access$200(this.this$0).poll();
         char var4 = var3.name().charAt(0);
         switch (var4) {
            case 'C':
               if (var2 == 200 && HttpMethod.CONNECT.equals(var3)) {
                  HttpClientCodec.access$102(this.this$0, true);
                  HttpClientCodec.access$200(this.this$0).clear();
                  return true;
               }
               break;
            case 'H':
               if (HttpMethod.HEAD.equals(var3)) {
                  return true;
               }
         }

         return super.isContentAlwaysEmpty(var1);
      }
   }
}
