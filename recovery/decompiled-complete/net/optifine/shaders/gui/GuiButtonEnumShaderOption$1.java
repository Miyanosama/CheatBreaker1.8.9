package net.optifine.shaders.gui;

import io.netty.util.concurrent.MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms$1;
import net.optifine.shaders.config.EnumShaderOption;
import org.java_websocket.framing.DataFrame;
import recovered.unidentified.UnidentifiedClass1316;

// $VF: synthetic class
public class GuiButtonEnumShaderOption$1 {
   public UnidentifiedClass1316 field_0002;
   public DataFrame field_0004;
   public ItemCameraTransforms$1 field_0001;
   public MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser field_0003;

   static {
      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.ANTIALIASING.ordinal()] = 1;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.NORMAL_MAP.ordinal()] = 2;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.SPECULAR_MAP.ordinal()] = 3;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.RENDER_RES_MUL.ordinal()] = 4;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.SHADOW_RES_MUL.ordinal()] = 5;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.HAND_DEPTH_MUL.ordinal()] = 6;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.CLOUD_SHADOW.ordinal()] = 7;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.OLD_HAND_LIGHT.ordinal()] = 8;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.OLD_LIGHTING.ordinal()] = 9;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.SHADOW_CLIP_FRUSTRUM.ordinal()] = 10;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$optifine$shaders$config$EnumShaderOption[EnumShaderOption.TWEAK_BLOCK_DAMAGE.ordinal()] = 11;
      } catch (NoSuchFieldError var1) {
      }
   }
}
