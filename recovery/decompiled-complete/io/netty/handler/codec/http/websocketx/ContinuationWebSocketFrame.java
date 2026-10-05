package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import net.minecraft.client.gui.achievement.GuiStats$StatsBlock;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.resources.SkinManager$3$1;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.network.play.server.S0BPacketAnimation;

public class ContinuationWebSocketFrame extends WebSocketFrame {
   public EntityAISit __junk2572917329916768778;
   public RenderZombie __junk2641637907243586242;
   public SkinManager$3$1 __junk6192679616378671571;
   public GuiStats$StatsBlock __junk7394880082732333494;
   public S0BPacketAnimation __junk1685726707266466782;

   public ContinuationWebSocketFrame retain() {
      super.retain();
      return this;
   }

   public ContinuationWebSocketFrame() {
      this(Unpooled.buffer(0));
   }

   public ContinuationWebSocketFrame retain(int var1) {
      super.retain(var1);
      return this;
   }

   public ContinuationWebSocketFrame(boolean var1, int var2, ByteBuf var3) {
      super(var1, var2, var3);
   }

   public ContinuationWebSocketFrame(boolean var1, int var2, String var3) {
      this(var1, var2, fromText(var3));
   }

   public String text() {
      return this.content().toString(CharsetUtil.UTF_8);
   }

   public static ByteBuf fromText(String var0) {
      return var0 != null && !var0.isEmpty() ? Unpooled.copiedBuffer(var0, CharsetUtil.UTF_8) : Unpooled.EMPTY_BUFFER;
   }

   public ContinuationWebSocketFrame duplicate() {
      return new ContinuationWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().duplicate());
   }

   public ContinuationWebSocketFrame copy() {
      return new ContinuationWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().copy());
   }

   public ContinuationWebSocketFrame(ByteBuf var1) {
      super(var1);
   }
}
