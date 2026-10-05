package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.AbstractByteBufAllocator$1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.spdy.SpdyStreamStatus;
import net.minecraft.client.gui.GuiPageButtonList$GuiSlideEntry;
import net.minecraft.client.renderer.texture.TextureMap$2;
import net.minecraft.client.resources.Language;
import net.optifine.util.MathUtilsTest;

public class BinaryWebSocketFrame extends WebSocketFrame {
   public Language __junk8596750222449771745;
   public AbstractByteBufAllocator$1 __junk2245483056086619764;
   public MathUtilsTest __junk7336812112391483008;
   public GuiPageButtonList$GuiSlideEntry __junk7539477210116497630;
   public TextureMap$2 __junk2763510887584120362;
   public SpdyStreamStatus __junk5902931347361809003;

   public BinaryWebSocketFrame(boolean var1, int var2, ByteBuf var3) {
      super(var1, var2, var3);
   }

   public BinaryWebSocketFrame(ByteBuf var1) {
      super(var1);
   }

   public BinaryWebSocketFrame duplicate() {
      return new BinaryWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().duplicate());
   }

   public BinaryWebSocketFrame copy() {
      return new BinaryWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().copy());
   }

   public BinaryWebSocketFrame() {
      super(Unpooled.buffer(0));
   }

   public BinaryWebSocketFrame retain() {
      super.retain();
      return this;
   }

   public BinaryWebSocketFrame retain(int var1) {
      super.retain(var1);
      return this;
   }
}
