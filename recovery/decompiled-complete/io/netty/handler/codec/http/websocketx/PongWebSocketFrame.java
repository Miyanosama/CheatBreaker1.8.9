package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiScreenCustomizePresets;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.network.NetworkManager$3;
import net.minecraft.world.gen.structure.MapGenNetherBridge;

public class PongWebSocketFrame extends WebSocketFrame {
   public EntityLargeExplodeFX __junk723796231133861632;
   public MapGenNetherBridge __junk7709417292304092549;
   public GuiScreenCustomizePresets __junk1172855909436954417;
   public NetworkManager$3 __junk2591983885494744056;

   public PongWebSocketFrame duplicate() {
      return new PongWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().duplicate());
   }

   public PongWebSocketFrame copy() {
      return new PongWebSocketFrame(this.isFinalFragment(), this.rsv(), this.content().copy());
   }

   public PongWebSocketFrame() {
      super(Unpooled.buffer(0));
   }

   public PongWebSocketFrame(boolean var1, int var2, ByteBuf var3) {
      super(var1, var2, var3);
   }

   public PongWebSocketFrame retain() {
      super.retain();
      return this;
   }

   public PongWebSocketFrame retain(int var1) {
      super.retain(var1);
      return this;
   }

   public PongWebSocketFrame(ByteBuf var1) {
      super(var1);
   }
}
