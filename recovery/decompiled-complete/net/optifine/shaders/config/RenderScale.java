package net.optifine.shaders.config;

import junit.swingui.TestRunner$11;
import net.minecraft.client.shader.ShaderLoader$ShaderType;
import net.optifine.entity.model.ModelAdapterEnderChest;
import net.optifine.gui.TooltipProviderOptions;
import recovered.unidentified.UnidentifiedClass0275;

public class RenderScale {
   public TestRunner$11 field_0003;
   public float scale = 1.0F;
   public ShaderLoader$ShaderType field_0002;
   public float offsetX = 0.0F;
   public float offsetY = 0.0F;
   public ModelAdapterEnderChest field_0001;
   public TooltipProviderOptions field_0007;
   public UnidentifiedClass0275 field_0004;

   @Override
   public String toString() {
      return "" + this.scale + ", " + this.offsetX + ", " + this.offsetY;
   }

   public float getOffsetX() {
      return this.offsetX;
   }

   public float getOffsetY() {
      return this.offsetY;
   }

   public RenderScale(float var1, float var2, float var3) {
      this.scale = var1;
      this.offsetX = var2;
      this.offsetY = var3;
   }

   public float getScale() {
      return this.scale;
   }
}
