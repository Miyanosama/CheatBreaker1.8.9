package io.netty.channel;

import net.minecraft.client.renderer.GlStateManager$AlphaState;
import net.minecraft.client.util.JsonException$Entry;
import org.java_websocket.framing.ControlFrame;

public class AbstractChannelHandlerContext$1 implements Runnable {
   public ControlFrame __junk8675944402612976665;
   public JsonException$Entry __junk6088723370448104318;
   public GlStateManager$AlphaState __junk1141584405544568292;

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$000(this.this$0);
   }

   public AbstractChannelHandlerContext$1(AbstractChannelHandlerContext var1) {
      this.this$0 = var1;
      super();
   }
}
