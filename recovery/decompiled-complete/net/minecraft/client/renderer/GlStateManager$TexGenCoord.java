package net.minecraft.client.renderer;

import io.netty.handler.codec.spdy.SpdyFrameDecoder$State;
import javax.vecmath.Tuple4b;
import net.optifine.entity.model.ModelAdapterBoat;

public class GlStateManager$TexGenCoord {
   public int param = -1;
   public ModelAdapterBoat field_0005;
   public SpdyFrameDecoder$State field_0002;
   public Tuple4b field_0004;
   public GlStateManager$BooleanState textureGen;
   public int coord;

   public GlStateManager$TexGenCoord(int var1, int var2) {
      this.coord = var1;
      this.textureGen = new GlStateManager$BooleanState(var2);
   }
}
