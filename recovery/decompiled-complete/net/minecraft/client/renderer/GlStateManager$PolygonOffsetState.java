package net.minecraft.client.renderer;

import io.netty.handler.codec.http.websocketx.WebSocket13FrameEncoder;
import javax.vecmath.Matrix3f;

public class GlStateManager$PolygonOffsetState {
   public GlStateManager$BooleanState polygonOffsetLine;
   public float units;
   public GlStateManager$BooleanState polygonOffsetFill = new GlStateManager$BooleanState(32823);
   public float factor;
   public Matrix3f field_0000;
   public WebSocket13FrameEncoder field_0001;

   public GlStateManager$PolygonOffsetState() {
      this.polygonOffsetLine = new GlStateManager$BooleanState(10754);
      this.factor = 0.0F;
      this.units = 0.0F;
   }
}
