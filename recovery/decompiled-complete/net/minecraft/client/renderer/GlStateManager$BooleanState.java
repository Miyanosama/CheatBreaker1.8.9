package net.minecraft.client.renderer;

import io.netty.handler.timeout.ReadTimeoutException;
import net.optifine.shaders.ClippingHelperShadow;
import org.lwjgl.opengl.GL11;

public class GlStateManager$BooleanState {
   public ClippingHelperShadow field_0001;
   public ReadTimeoutException field_0003;
   public int capability;
   public boolean currentState = false;

   public void setState(boolean var1) {
      if (var1 != this.currentState) {
         this.currentState = var1;
         if (var1) {
            GL11.glEnable(this.capability);
         } else {
            GL11.glDisable(this.capability);
         }
      }
   }

   public GlStateManager$BooleanState(int var1) {
      this.capability = var1;
   }

   public void setDisabled() {
      this.setState(false);
   }

   public void setEnabled() {
      this.setState(true);
   }
}
