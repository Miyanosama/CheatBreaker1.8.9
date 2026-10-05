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
      switch (var0) {
         case ANTIALIASING:
            return var1 + GuiShaders.toStringAa(Shaders.configAntialiasingLevel);
         case NORMAL_MAP:
            return var1 + GuiShaders.toStringOnOff(Shaders.configNormalMap);
         case SPECULAR_MAP:
            return var1 + GuiShaders.toStringOnOff(Shaders.configSpecularMap);
         case RENDER_RES_MUL:
            return var1 + GuiShaders.toStringQuality(Shaders.configRenderResMul);
         case SHADOW_RES_MUL:
            return var1 + GuiShaders.toStringQuality(Shaders.configShadowResMul);
         case HAND_DEPTH_MUL:
            return var1 + GuiShaders.toStringHandDepth(Shaders.configHandDepthMul);
         case CLOUD_SHADOW:
            return var1 + GuiShaders.toStringOnOff(Shaders.configCloudShadow);
         case OLD_HAND_LIGHT:
            return var1 + Shaders.configOldHandLight.getUserValue();
         case OLD_LIGHTING:
            return var1 + Shaders.configOldLighting.getUserValue();
         case SHADOW_CLIP_FRUSTRUM:
            return var1 + GuiShaders.toStringOnOff(Shaders.configShadowClipFrustrum);
         case TWEAK_BLOCK_DAMAGE:
            return var1 + GuiShaders.toStringOnOff(Shaders.configTweakBlockDamage);
         default:
            return var1 + Shaders.getEnumShaderOption(var0);
      }
   }
}
