package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.spdy.SpdySession$StreamState;
import io.netty.util.CharsetUtil;
import net.optifine.http.FileUploadThread;
import org.java_websocket.enums.Role;

public class TextWebSocketFrame extends WebSocketFrame {
   public FileUploadThread __junk8004044248502628614;
   public SpdySession$StreamState __junk8931776057267559918;
   public Role __junk3358911125294233230;

   public static ByteBuf fromText(String var0) {
      return var0 != null && !var0.isEmpty() ? Unpooled.copiedBuffer(var0, CharsetUtil.UTF_8) : Unpooled.EMPTY_BUFFER;
   }

   public TextWebSocketFrame(boolean var1, int var2, ByteBuf var3) {
      super(var1, var2, var3);
   }

   public TextWebSocketFrame duplicate() {
      return new TextWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().duplicate());
   }

   public TextWebSocketFrame() {
      super(Unpooled.buffer(0));
   }

   public TextWebSocketFrame(String var1) {
      super(fromText(var1));
   }

   public TextWebSocketFrame copy() {
      return new TextWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().copy());
   }

   public TextWebSocketFrame retain(int var1) {
      super.retain(var1);
      return this;
   }

   public TextWebSocketFrame(ByteBuf var1) {
      super(var1);
   }

   public TextWebSocketFrame(boolean var1, int var2, String var3) {
      super(var1, var2, fromText(var3));
   }

   public TextWebSocketFrame retain() {
      super.retain();
      return this;
   }

   public String text() {
      return this.content().toString(CharsetUtil.UTF_8);
   }
}
