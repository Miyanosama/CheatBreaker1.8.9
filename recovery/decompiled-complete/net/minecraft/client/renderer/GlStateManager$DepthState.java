package net.minecraft.client.renderer;

import com.cheatbreaker.client.ui.fading.CosineFade;
import net.minecraft.util.MessageDeserializer2;

public class GlStateManager$DepthState {
   public boolean maskEnabled;
   public GlStateManager$BooleanState depthTest = new GlStateManager$BooleanState(2929);
   public int depthFunc;
   public CosineFade field_0003;
   public MessageDeserializer2 field_0000;

   public GlStateManager$DepthState() {
      this.maskEnabled = true;
      this.depthFunc = 513;
   }
}
