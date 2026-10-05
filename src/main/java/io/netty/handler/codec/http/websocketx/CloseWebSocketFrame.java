package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import net.minecraft.client.main.GameConfiguration$FolderInformation;
import net.minecraft.client.player.inventory.LocalBlockIntercommunication;

public class CloseWebSocketFrame extends WebSocketFrame {

   public String reasonText() {
      ByteBuf var1 = this.content();
      if (var1 != null && var1.capacity() > 2) {
         var1.readerIndex(2);
         String var2 = var1.toString(CharsetUtil.UTF_8);
         var1.readerIndex(0);
         return var2;
      } else {
         return "";
      }
   }

   public int statusCode() {
      ByteBuf var1 = this.content();
      if (var1 != null && var1.capacity() != 0) {
         var1.readerIndex(0);
         short var2 = var1.readShort();
         var1.readerIndex(0);
         return var2;
      } else {
         return -1;
      }
   }

   public CloseWebSocketFrame(int var1, String var2) {
      this(true, 0, var1, var2);
   }

   public static ByteBuf newBinaryData(int var0, String var1) {
      byte[] var2 = EmptyArrays.EMPTY_BYTES;
      if (var1 != null) {
         var2 = var1.getBytes(CharsetUtil.UTF_8);
      }

      ByteBuf var3 = Unpooled.buffer(2 + var2.length);
      var3.writeShort(var0);
      if (var2.length > 0) {
         var3.writeBytes(var2);
      }

      var3.readerIndex(0);
      return var3;
   }

   public CloseWebSocketFrame retain() {
      super.retain();
      return this;
   }

   public CloseWebSocketFrame(boolean var1, int var2, ByteBuf var3) {
      super(var1, var2, var3);
   }

   public CloseWebSocketFrame duplicate() {
      return new CloseWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().duplicate());
   }

   public CloseWebSocketFrame copy() {
      return new CloseWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().copy());
   }

   public CloseWebSocketFrame(boolean var1, int var2) {
      this(var1, var2, Unpooled.buffer(0));
   }

   public CloseWebSocketFrame(boolean var1, int var2, int var3, String var4) {
      super(var1, var2, newBinaryData(var3, var4));
   }

   public CloseWebSocketFrame() {
      super(Unpooled.buffer(0));
   }

   public CloseWebSocketFrame retain(int var1) {
      super.retain(var1);
      return this;
   }
}
