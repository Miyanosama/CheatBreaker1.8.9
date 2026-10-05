package net.optifine.shaders.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.EnumShaderOption;

public class GuiButtonEnumShaderOption extends GuiButton {
   public EnumShaderOption enumShaderOption = null;

   public EnumShaderOption getEnumShaderOption() {
      return this.enumShaderOption;
   }

   public void updateButtonText() {
      this.j = getButtonText(this.enumShaderOption);
   }

   public GuiButtonEnumShaderOption(EnumShaderOption var1, int var2, int var3, int var4, int var5) {
      super(var1.ordinal(), var2, var3, var4, var5, getButtonText(var1));
      this.enumShaderOption = var1;
   }

   public static String getButtonText(EnumShaderOption var0) {
      String var1 = I18n.format(var0.getResourceKey()) + ": ";
      switch (GuiButtonEnumShaderOption$1.$SwitchMap$net$optifine$shaders$config$EnumShaderOption[var0.ordinal()]) {
         case 1:
            return var1 + GuiShaders.toStringAa(Shaders.configAntialiasingLevel);
         case 2:
            return var1 + GuiShaders.toStringOnOff(Shaders.configNormalMap);
         case 3:
            return var1 + GuiShaders.toStringOnOff(Shaders.configSpecularMap);
         case 4:
            return var1 + GuiShaders.toStringQuality(Shaders.configRenderResMul);
         case 5:
            return var1 + GuiShaders.toStringQuality(Shaders.configShadowResMul);
         case 6:
            return var1 + GuiShaders.toStringHandDepth(Shaders.configHandDepthMul);
         case 7:
            return var1 + GuiShaders.toStringOnOff(Shaders.configCloudShadow);
         case 8:
            return var1 + Shaders.configOldHandLight.getUserValue();
         case 9:
            return var1 + Shaders.configOldLighting.getUserValue();
         case 10:
            return var1 + GuiShaders.toStringOnOff(Shaders.configShadowClipFrustrum);
         case 11:
            return var1 + GuiShaders.toStringOnOff(Shaders.configTweakBlockDamage);
         default:
            return var1 + Shaders.getEnumShaderOption(var0);
      }
   }
}
