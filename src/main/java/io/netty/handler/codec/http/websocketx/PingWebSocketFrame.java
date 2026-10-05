package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.item.ItemSaddle;
import net.minecraft.world.gen.NoiseGeneratorImproved;

public class PingWebSocketFrame extends WebSocketFrame {

   public PingWebSocketFrame copy() {
      return new PingWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().copy());
   }

   public PingWebSocketFrame(boolean var1, int var2, ByteBuf var3) {
      super(var1, var2, var3);
   }

   public PingWebSocketFrame(ByteBuf var1) {
      super(var1);
   }

   public PingWebSocketFrame retain() {
      super.retain();
      return this;
   }

   public PingWebSocketFrame retain(int var1) {
      super.retain(var1);
      return this;
   }

   public PingWebSocketFrame() {
      super(true, 0, Unpooled.buffer(0));
   }

   public PingWebSocketFrame duplicate() {
      return new PingWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().duplicate());
   }
}
