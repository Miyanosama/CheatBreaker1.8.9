package net.optifine.shaders.uniform;

import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.shaders.Shaders;

public enum ShaderParameterFloat {
      BIOME("biome"),
      TEMPERATURE("temperature"),
      RAINFALL("rainfall"),
      HELD_ITEM_ID(Shaders.uniform_heldItemId),
      HELD_BLOCK_LIGHT_VALUE(Shaders.uniform_heldBlockLightValue),
      HELD_ITEM_ID2(Shaders.uniform_heldItemId2),
      HELD_BLOCK_LIGHT_VALUE2(Shaders.uniform_heldBlockLightValue2),
      WORLD_TIME(Shaders.uniform_worldTime),
      WORLD_DAY(Shaders.uniform_worldDay),
      MOON_PHASE(Shaders.uniform_moonPhase),
      FRAME_COUNTER(Shaders.uniform_frameCounter),
      FRAME_TIME(Shaders.uniform_frameTime),
      FRAME_TIME_COUNTER(Shaders.uniform_frameTimeCounter),
      SUN_ANGLE(Shaders.uniform_sunAngle),
      SHADOW_ANGLE(Shaders.uniform_shadowAngle),
      RAIN_STRENGTH(Shaders.uniform_rainStrength),
      ASPECT_RATIO(Shaders.uniform_aspectRatio),
      VIEW_WIDTH(Shaders.uniform_viewWidth),
      VIEW_HEIGHT(Shaders.uniform_viewHeight),
      NEAR(Shaders.uniform_near),
      FAR(Shaders.uniform_far),
      WETNESS(Shaders.uniform_wetness),
      EYE_ALTITUDE(Shaders.uniform_eyeAltitude),
      EYE_BRIGHTNESS(Shaders.uniform_eyeBrightness, new String[]{"x", "y"}),
      TERRAIN_TEXTURE_SIZE(Shaders.uniform_terrainTextureSize, new String[]{"x", "y"}),
      TERRRAIN_ICON_SIZE(Shaders.uniform_terrainIconSize),
      IS_EYE_IN_WATER(Shaders.uniform_isEyeInWater),
      NIGHT_VISION(Shaders.uniform_nightVision),
      BLINDNESS(Shaders.uniform_blindness),
      SCREEN_BRIGHTNESS(Shaders.uniform_screenBrightness),
      HIDE_GUI(Shaders.uniform_hideGUI),
      CENTER_DEPT_SMOOTH(Shaders.uniform_centerDepthSmooth),
      ATLAS_SIZE(Shaders.uniform_atlasSize, new String[]{"x", "y"}),
      CAMERA_POSITION(Shaders.uniform_cameraPosition, new String[]{"x", "y", "z"}),
      PREVIOUS_CAMERA_POSITION(Shaders.uniform_previousCameraPosition, new String[]{"x", "y", "z"}),
      SUN_POSITION(Shaders.uniform_sunPosition, new String[]{"x", "y", "z"}),
      MOON_POSITION(Shaders.uniform_moonPosition, new String[]{"x", "y", "z"}),
      SHADOW_LIGHT_POSITION(Shaders.uniform_shadowLightPosition, new String[]{"x", "y", "z"}),
      UP_POSITION(Shaders.uniform_upPosition, new String[]{"x", "y", "z"}),
      SKY_COLOR(Shaders.uniform_skyColor, new String[]{"r", "g", "b"}),
      GBUFFER_PROJECTION(Shaders.uniform_gbufferProjection, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      GBUFFER_PROJECTION_INVERSE(Shaders.uniform_gbufferProjectionInverse, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      GBUFFER_PREVIOUS_PROJECTION(Shaders.uniform_gbufferPreviousProjection, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      GBUFFER_MODEL_VIEW(Shaders.uniform_gbufferModelView, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      GBUFFER_MODEL_VIEW_INVERSE(Shaders.uniform_gbufferModelViewInverse, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      GBUFFER_PREVIOUS_MODEL_VIEW(Shaders.uniform_gbufferPreviousModelView, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      SHADOW_PROJECTION(Shaders.uniform_shadowProjection, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      SHADOW_PROJECTION_INVERSE(Shaders.uniform_shadowProjectionInverse, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      SHADOW_MODEL_VIEW(Shaders.uniform_shadowModelView, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
      SHADOW_MODEL_VIEW_INVERSE(Shaders.uniform_shadowModelViewInverse, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"});
   public String[] indexNames1;
   public ShaderUniformBase uniform;
   public String name;
   public String[] indexNames2;
   public static ShaderParameterFloat[] $VALUES = new ShaderParameterFloat[]{
      ShaderParameterFloat.BIOME,
      ShaderParameterFloat.TEMPERATURE,
      RAINFALL,
      HELD_ITEM_ID,
      HELD_BLOCK_LIGHT_VALUE,
      ShaderParameterFloat.HELD_ITEM_ID2,
      HELD_BLOCK_LIGHT_VALUE2,
      WORLD_TIME,
      WORLD_DAY,
      ShaderParameterFloat.MOON_PHASE,
      ShaderParameterFloat.FRAME_COUNTER,
      FRAME_TIME,
      FRAME_TIME_COUNTER,
      SUN_ANGLE,
      ShaderParameterFloat.SHADOW_ANGLE,
      RAIN_STRENGTH,
      ASPECT_RATIO,
      VIEW_WIDTH,
      VIEW_HEIGHT,
      NEAR,
      FAR,
      WETNESS,
      ShaderParameterFloat.EYE_ALTITUDE,
      EYE_BRIGHTNESS,
      ShaderParameterFloat.TERRAIN_TEXTURE_SIZE,
      TERRRAIN_ICON_SIZE,
      IS_EYE_IN_WATER,
      NIGHT_VISION,
      BLINDNESS,
      SCREEN_BRIGHTNESS,
      ShaderParameterFloat.HIDE_GUI,
      CENTER_DEPT_SMOOTH,
      ATLAS_SIZE,
      ShaderParameterFloat.CAMERA_POSITION,
      PREVIOUS_CAMERA_POSITION,
      SUN_POSITION,
      ShaderParameterFloat.MOON_POSITION,
      SHADOW_LIGHT_POSITION,
      UP_POSITION,
      SKY_COLOR,
      GBUFFER_PROJECTION,
      GBUFFER_PROJECTION_INVERSE,
      GBUFFER_PREVIOUS_PROJECTION,
      ShaderParameterFloat.GBUFFER_MODEL_VIEW,
      GBUFFER_MODEL_VIEW_INVERSE,
      GBUFFER_PREVIOUS_MODEL_VIEW,
      SHADOW_PROJECTION,
      SHADOW_PROJECTION_INVERSE,
      SHADOW_MODEL_VIEW,
      SHADOW_MODEL_VIEW_INVERSE
   };

   public ShaderUniformBase getUniform() {
      return this.uniform;
   }

   ShaderParameterFloat(ShaderUniformBase var3, String[] var4, String[] var5) {
      this.name = var3.getName();
      this.uniform = var3;
      this.indexNames1 = var4;
      this.indexNames2 = var5;
      if (!instanceOf(var3, ShaderUniformM4.class)) {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + var3.getClass().getName());
      }
   }

   public static boolean instanceOf(Object var0, Class... var1) {
      if (var0 == null) {
         return false;
      } else {
         Class var2 = var0.getClass();

         for (int var3 = 0; var3 < var1.length; var3++) {
            Class var4 = var1[var3];
            if (var4.isAssignableFrom(var2)) {
               return true;
            }
         }

         return false;
      }
   }

   ShaderParameterFloat(ShaderUniformBase var3) {
      this.name = var3.getName();
      this.uniform = var3;
      if (!instanceOf(var3, ShaderUniform1f.class, ShaderUniform1i.class)) {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + var3.getClass().getName());
      }
   }

   ShaderParameterFloat(String var3) {
      this.name = var3;
   }

   public String[] getIndexNames1() {
      return this.indexNames1;
   }

   public String getName() {
      return this.name;
   }

   ShaderParameterFloat(ShaderUniformBase var3, String[] var4) {
      this.name = var3.getName();
      this.uniform = var3;
      this.indexNames1 = var4;
      if (!instanceOf(var3, ShaderUniform2i.class, ShaderUniform2f.class, ShaderUniform3f.class, ShaderUniform4f.class)) {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + var3.getClass().getName());
      }
   }

   public float eval(int var1, int var2) {
      if (this.indexNames1 == null || var1 >= 0 && var1 <= this.indexNames1.length) {
         if (this.indexNames2 == null || var2 >= 0 && var2 <= this.indexNames2.length) {
            switch (this) {
               case BIOME:
                  BlockPos var3 = Shaders.getCameraPosition();
                  BiomeGenBase var4 = Shaders.getCurrentWorld().getBiomeGenForCoords(var3);
                  return var4.az;
               case TEMPERATURE:
                  BlockPos var5 = Shaders.getCameraPosition();
                  BiomeGenBase var6 = Shaders.getCurrentWorld().getBiomeGenForCoords(var5);
                  return var6 != null ? var6.getFloatTemperature(var5) : 0.0F;
               case RAINFALL:
                  BlockPos var7 = Shaders.getCameraPosition();
                  BiomeGenBase var8 = Shaders.getCurrentWorld().getBiomeGenForCoords(var7);
                  return var8 != null ? var8.getFloatRainfall() : 0.0F;
               default:
                  if (this.uniform instanceof ShaderUniform1f) {
                     return ((ShaderUniform1f)this.uniform).getValue();
                  } else if (this.uniform instanceof ShaderUniform1i) {
                     return ((ShaderUniform1i)this.uniform).getValue();
                  } else if (this.uniform instanceof ShaderUniform2i) {
                     return ((ShaderUniform2i)this.uniform).getValue()[var1];
                  } else if (this.uniform instanceof ShaderUniform2f) {
                     return ((ShaderUniform2f)this.uniform).getValue()[var1];
                  } else if (this.uniform instanceof ShaderUniform3f) {
                     return ((ShaderUniform3f)this.uniform).getValue()[var1];
                  } else if (this.uniform instanceof ShaderUniform4f) {
                     return ((ShaderUniform4f)this.uniform).getValue()[var1];
                  } else if (this.uniform instanceof ShaderUniformM4) {
                     return ((ShaderUniformM4)this.uniform).getValue(var1, var2);
                  } else {
                     throw new IllegalArgumentException("Unknown uniform type: " + this);
                  }
            }
         } else {
            Config.warn("Invalid index2, parameter: " + this + ", index: " + var2);
            return 0.0F;
         }
      } else {
         Config.warn("Invalid index1, parameter: " + this + ", index: " + var1);
         return 0.0F;
      }
   }

   public String[] getIndexNames2() {
      return this.indexNames2;
   }
}
