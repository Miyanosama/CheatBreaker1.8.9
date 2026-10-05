package io.netty.handler.codec.http.websocketx;

import io.netty.channel.AbstractChannelHandlerContext$WriteAndFlushTask$1;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpHeaders;
import java.net.URI;
import java.util.List;
import net.minecraft.client.model.ModelBat;
import org.apache.log4j.pattern.PatternConverter;

public class WebSocketClientProtocolHandler extends WebSocketProtocolHandler {
   public WebSocketClientHandshaker handshaker;
   public PatternConverter __junk5698966230246644429;
   public AbstractChannelHandlerContext$WriteAndFlushTask$1 __junk4603299511285320798;
   public boolean handleCloseFrames;
   public ModelBat __junk3770653858259121608;

   public WebSocketClientProtocolHandler(URI var1, WebSocketVersion var2, String var3, boolean var4, HttpHeaders var5, int var6, boolean var7) {
      this(WebSocketClientHandshakerFactory.newHandshaker(var1, var2, var3, var4, var5, var6), var7);
   }

   public WebSocketClientProtocolHandler(WebSocketClientHandshaker var1) {
      this(var1, true);
   }

   public WebSocketClientProtocolHandler(URI var1, WebSocketVersion var2, String var3, boolean var4, HttpHeaders var5, int var6) {
      this(var1, var2, var3, var4, var5, var6, true);
   }

   @Override
   public void decode(ChannelHandlerContext var1, WebSocketFrame var2, List<Object> var3) {
      if (this.handleCloseFrames && var2 instanceof CloseWebSocketFrame) {
         var1.close();
      } else {
         super.decode(var1, var2, var3);
      }
   }

   public WebSocketClientProtocolHandler(WebSocketClientHandshaker var1, boolean var2) {
      this.handshaker = var1;
      this.handleCloseFrames = var2;
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      ChannelPipeline var2 = var1.pipeline();
      if (var2.get(WebSocketClientProtocolHandshakeHandler.class) == null) {
         var1.pipeline()
            .addBefore(var1.name(), WebSocketClientProtocolHandshakeHandler.class.getName(), new WebSocketClientProtocolHandshakeHandler(this.handshaker));
      }
   }
}
