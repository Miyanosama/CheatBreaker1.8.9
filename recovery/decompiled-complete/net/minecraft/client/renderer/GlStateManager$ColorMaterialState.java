package net.minecraft.client.renderer;

import io.netty.handler.codec.http.HttpHeaders$1;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$SeekAheadOptimize;
import io.netty.util.internal.chmv8.ForkJoinPool$1;
import net.optifine.shaders.gui.GuiSlotShaders;

public class GlStateManager$ColorMaterialState {
   public int mode;
   public GuiSlotShaders field_0005;
   public HttpPostBodyUtil$SeekAheadOptimize field_0002;
   public GlStateManager$BooleanState colorMaterial = new GlStateManager$BooleanState(2903);
   public int face = 1032;
   public HttpHeaders$1 field_0001;
   public ForkJoinPool$1 field_0006;

   public GlStateManager$ColorMaterialState() {
      this.mode = 5634;
   }
}
