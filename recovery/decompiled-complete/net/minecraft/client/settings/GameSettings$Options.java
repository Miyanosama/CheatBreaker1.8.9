package net.minecraft.client.settings;

import net.minecraft.entity.passive.EntityWolf$1;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;

public enum GameSettings$Options {
   SMOOTH_BIOMES("of.options.SMOOTH_BIOMES", false, false),
   USE_VBO("options.vbo", false, true),
   RAIN_SPLASH("of.options.RAIN_SPLASH", false, false),
   STREAM_VOLUME_SYSTEM("options.stream.systemVolume", true, false),
   VIGNETTE("of.options.VIGNETTE", false, false),
   INVERT_MOUSE("options.invertMouse", false, true),
   CUSTOM_ENTITY_MODELS("of.options.CUSTOM_ENTITY_MODELS", false, false),
   PORTAL_PARTICLES("of.options.PORTAL_PARTICLES", false, false),
   ADVANCED_TOOLTIPS("of.options.ADVANCED_TOOLTIPS", false, false),
   EMISSIVE_TEXTURES("of.options.EMISSIVE_TEXTURES", false, false),
   BLOCK_ALTERNATIVES("options.blockAlternatives", false, true),
   ANIMATED_FIRE("of.options.ANIMATED_FIRE", false, false),
   SHOW_CAPES("of.options.SHOW_CAPES", false, false),
   AO_LEVEL("of.options.AO_LEVEL", true, false),
   SMOOTH_FPS("of.options.SMOOTH_FPS", false, false),
   RENDER_REGIONS("of.options.RENDER_REGIONS", false, false),
   STREAM_MIC_TOGGLE_BEHAVIOR("options.stream.micToggleBehavior", false, false),
   FAST_MATH("of.options.FAST_MATH", false, false),
   RENDER_DISTANCE("options.renderDistance", true, false, 2.0F, 16.0F, 1.0F),
   TRANSLUCENT_BLOCKS("of.options.TRANSLUCENT_BLOCKS", false, false),
   TIME("of.options.TIME", false, false),
   BETTER_SNOW("of.options.BETTER_SNOW", false, false),
   LAGOMETER("of.options.LAGOMETER", false, false),
   LAZY_CHUNK_LOADING("of.options.LAZY_CHUNK_LOADING", false, false),
   STREAM_CHAT_ENABLED("options.stream.chat.enabled", false, false),
   FULLSCREEN_MODE("of.options.FULLSCREEN_MODE", true, false, 0.0F, Config.getDisplayModes().length, 1.0F),
   STREAM_COMPRESSION("options.stream.compression", false, false),
   ENABLE_VSYNC("options.vsync", false, true),
   AA_LEVEL("of.options.AA_LEVEL", true, false, 0.0F, 16.0F, 1.0F),
   ANIMATED_FLAME("of.options.ANIMATED_FLAME", false, false),
   CHUNK_UPDATES_DYNAMIC("of.options.CHUNK_UPDATES_DYNAMIC", false, false),
   NATURAL_TEXTURES("of.options.NATURAL_TEXTURES", false, false),
   SUN_MOON("of.options.SUN_MOON", false, false),
   FIREWORK_PARTICLES("of.options.FIREWORK_PARTICLES", false, false),
   GAMMA("options.gamma", true, false),
   ALTERNATE_BLOCKS("of.options.ALTERNATE_BLOCKS", false, false),
   GUI_SCALE("options.guiScale", false, false),
   CONNECTED_TEXTURES("of.options.CONNECTED_TEXTURES", false, false),
   DROPPED_ITEMS("of.options.DROPPED_ITEMS", false, false),
   FAST_RENDER("of.options.FAST_RENDER", false, false),
   HELD_ITEM_TOOLTIPS("of.options.HELD_ITEM_TOOLTIPS", false, false),
   FOG_START("of.options.FOG_START", false, false),
   CLEAR_WATER("of.options.CLEAR_WATER", false, false),
   ANIMATED_TEXTURES("of.options.ANIMATED_TEXTURES", false, false),
   FORCE_UNICODE_FONT("options.forceUnicodeFont", false, true),
   CHAT_LINKS_PROMPT("options.chat.links.prompt", false, true),
   VIEW_BOBBING("options.viewBobbing", false, true),
   CLOUDS("of.options.CLOUDS", false, false),
   CUSTOM_ITEMS("of.options.CUSTOM_ITEMS", false, false),
   STREAM_CHAT_USER_FILTER("options.stream.chat.userFilter", false, false),
   SCREENSHOT_SIZE("of.options.SCREENSHOT_SIZE", false, false),
   SHOW_FPS("of.options.SHOW_FPS", false, false),
   AF_LEVEL("of.options.AF_LEVEL", true, false, 1.0F, 16.0F, 1.0F),
   SMART_ANIMATIONS("of.options.SMART_ANIMATIONS", false, false),
   DRIPPING_WATER_LAVA("of.options.DRIPPING_WATER_LAVA", false, false),
   WEATHER("of.options.WEATHER", false, false),
   DYNAMIC_FOV("of.options.DYNAMIC_FOV", false, false),
   CUSTOM_GUIS("of.options.CUSTOM_GUIS", false, false),
   USE_FULLSCREEN("options.fullscreen", false, true),
   CHAT_LINKS("options.chat.links", false, true),
   SENSITIVITY("options.sensitivity", true, false),
   CHAT_HEIGHT_UNFOCUSED("options.chat.height.unfocused", true, false),
   CHAT_VISIBILITY("options.chat.visibility", false, false),
   AMBIENT_OCCLUSION("options.ao", false, false),
   CUSTOM_COLORS("of.options.CUSTOM_COLORS", false, false),
   STREAM_BYTES_PER_PIXEL("options.stream.bytesPerPixel", true, false),
   VOID_PARTICLES("of.options.VOID_PARTICLES", false, false),
   RANDOM_ENTITIES("of.options.RANDOM_ENTITIES", false, false),
   FRAMERATE_LIMIT("options.framerateLimit", true, false, 0.0F, 260.0F, 5.0F),
   AUTOSAVE_TICKS("of.options.AUTOSAVE_TICKS", false, false),
   TREES("of.options.TREES", false, false),
   CUSTOM_FONTS("of.options.CUSTOM_FONTS", false, false),
   MIPMAP_LEVELS("options.mipmapLevels", true, false, 0.0F, 4.0F, 1.0F),
   SKY("of.options.SKY", false, false),
   CHAT_COLOR("options.chat.color", false, true),
   PROFILER("of.options.PROFILER", false, false),
   ANIMATED_LAVA("of.options.ANIMATED_LAVA", false, false),
   SHOW_GL_ERRORS("of.options.SHOW_GL_ERRORS", false, false),
   ANIMATED_EXPLOSION("of.options.ANIMATED_EXPLOSION", false, false),
   ANAGLYPH("options.anaglyph", false, true),
   MIPMAP_TYPE("of.options.MIPMAP_TYPE", true, false, 0.0F, 3.0F, 1.0F),
   STREAM_VOLUME_MIC("options.stream.micVolumne", true, false),
   SWAMP_COLORS("of.options.SWAMP_COLORS", false, false),
   ANIMATED_PORTAL("of.options.ANIMATED_PORTAL", false, false),
   STARS("of.options.STARS", false, false),
   STREAM_FPS("options.stream.fps", true, false),
   BETTER_GRASS("of.options.BETTER_GRASS", false, false),
   CHAT_WIDTH("options.chat.width", true, false),
   CHUNK_UPDATES("of.options.CHUNK_UPDATES", false, false),
   CUSTOM_SKY("of.options.CUSTOM_SKY", false, false),
   CHAT_HEIGHT_FOCUSED("options.chat.height.focused", true, false),
   SMOOTH_WORLD("of.options.SMOOTH_WORLD", false, false),
   RAIN("of.options.RAIN", false, false),
   FBO_ENABLE("options.fboEnable", false, true),
   WATER_PARTICLES("of.options.WATER_PARTICLES", false, false),
   ENTITY_SHADOWS("options.entityShadows", false, true),
   FOV("options.fov", true, false, 30.0F, 110.0F, 1.0F),
   PARTICLES("options.particles", false, false),
   CHAT_OPACITY("options.chat.opacity", true, false),
   GRAPHICS("options.graphics", false, false),
   ANIMATED_REDSTONE("of.options.ANIMATED_REDSTONE", false, false),
   STREAM_KBPS("options.stream.kbps", true, false),
   REALMS_NOTIFICATIONS("options.realmsNotifications", false, true),
   SNOOPER_ENABLED("options.snooper", false, true),
   TOUCHSCREEN("options.touchscreen", false, true),
   CHAT_SCALE("options.chat.scale", true, false),
   POTION_PARTICLES("of.options.POTION_PARTICLES", false, false),
   ANIMATED_TERRAIN("of.options.ANIMATED_TERRAIN", false, false),
   DYNAMIC_LIGHTS("of.options.DYNAMIC_LIGHTS", false, false),
   SATURATION("options.saturation", true, false),
   CLOUD_HEIGHT("of.options.CLOUD_HEIGHT", true, false),
   RENDER_CLOUDS("options.renderClouds", false, false),
   FOG_FANCY("of.options.FOG_FANCY", false, false),
   REDUCED_DEBUG_INFO("options.reducedDebugInfo", false, true),
   ANIMATED_SMOKE("of.options.ANIMATED_SMOKE", false, false),
   ANIMATED_WATER("of.options.ANIMATED_WATER", false, false),
   STREAM_SEND_METADATA("options.stream.sendMetadata", false, true);
   public EntityWolf$1 field_0026;
   public boolean enumBoolean;
   // $VF: synthetic field
   public static GameSettings$Options[] $VALUES = new GameSettings$Options[]{
      INVERT_MOUSE,
      GameSettings$Options.SENSITIVITY,
      GameSettings$Options.FOV,
      GameSettings$Options.GAMMA,
      GameSettings$Options.SATURATION,
      GameSettings$Options.RENDER_DISTANCE,
      GameSettings$Options.VIEW_BOBBING,
      GameSettings$Options.ANAGLYPH,
      GameSettings$Options.FRAMERATE_LIMIT,
      GameSettings$Options.FBO_ENABLE,
      GameSettings$Options.RENDER_CLOUDS,
      GameSettings$Options.GRAPHICS,
      GameSettings$Options.AMBIENT_OCCLUSION,
      GameSettings$Options.GUI_SCALE,
      GameSettings$Options.PARTICLES,
      GameSettings$Options.CHAT_VISIBILITY,
      GameSettings$Options.CHAT_COLOR,
      GameSettings$Options.CHAT_LINKS,
      GameSettings$Options.CHAT_OPACITY,
      GameSettings$Options.CHAT_LINKS_PROMPT,
      GameSettings$Options.SNOOPER_ENABLED,
      GameSettings$Options.USE_FULLSCREEN,
      GameSettings$Options.ENABLE_VSYNC,
      USE_VBO,
      GameSettings$Options.TOUCHSCREEN,
      GameSettings$Options.CHAT_SCALE,
      GameSettings$Options.CHAT_WIDTH,
      GameSettings$Options.CHAT_HEIGHT_FOCUSED,
      GameSettings$Options.CHAT_HEIGHT_UNFOCUSED,
      GameSettings$Options.MIPMAP_LEVELS,
      GameSettings$Options.FORCE_UNICODE_FONT,
      GameSettings$Options.STREAM_BYTES_PER_PIXEL,
      GameSettings$Options.STREAM_VOLUME_MIC,
      STREAM_VOLUME_SYSTEM,
      GameSettings$Options.STREAM_KBPS,
      GameSettings$Options.STREAM_FPS,
      GameSettings$Options.STREAM_COMPRESSION,
      GameSettings$Options.STREAM_SEND_METADATA,
      GameSettings$Options.STREAM_CHAT_ENABLED,
      GameSettings$Options.STREAM_CHAT_USER_FILTER,
      GameSettings$Options.STREAM_MIC_TOGGLE_BEHAVIOR,
      GameSettings$Options.BLOCK_ALTERNATIVES,
      GameSettings$Options.REDUCED_DEBUG_INFO,
      GameSettings$Options.ENTITY_SHADOWS,
      GameSettings$Options.REALMS_NOTIFICATIONS,
      GameSettings$Options.FOG_FANCY,
      GameSettings$Options.FOG_START,
      GameSettings$Options.MIPMAP_TYPE,
      GameSettings$Options.SMOOTH_FPS,
      GameSettings$Options.CLOUDS,
      GameSettings$Options.CLOUD_HEIGHT,
      GameSettings$Options.TREES,
      GameSettings$Options.RAIN,
      GameSettings$Options.ANIMATED_WATER,
      GameSettings$Options.ANIMATED_LAVA,
      GameSettings$Options.ANIMATED_FIRE,
      GameSettings$Options.ANIMATED_PORTAL,
      GameSettings$Options.AO_LEVEL,
      GameSettings$Options.LAGOMETER,
      GameSettings$Options.SHOW_FPS,
      GameSettings$Options.AUTOSAVE_TICKS,
      GameSettings$Options.BETTER_GRASS,
      GameSettings$Options.ANIMATED_REDSTONE,
      GameSettings$Options.ANIMATED_EXPLOSION,
      GameSettings$Options.ANIMATED_FLAME,
      GameSettings$Options.ANIMATED_SMOKE,
      GameSettings$Options.WEATHER,
      GameSettings$Options.SKY,
      GameSettings$Options.STARS,
      GameSettings$Options.SUN_MOON,
      VIGNETTE,
      GameSettings$Options.CHUNK_UPDATES,
      GameSettings$Options.CHUNK_UPDATES_DYNAMIC,
      GameSettings$Options.TIME,
      GameSettings$Options.CLEAR_WATER,
      GameSettings$Options.SMOOTH_WORLD,
      GameSettings$Options.VOID_PARTICLES,
      GameSettings$Options.WATER_PARTICLES,
      RAIN_SPLASH,
      PORTAL_PARTICLES,
      GameSettings$Options.POTION_PARTICLES,
      GameSettings$Options.FIREWORK_PARTICLES,
      GameSettings$Options.PROFILER,
      GameSettings$Options.DRIPPING_WATER_LAVA,
      GameSettings$Options.BETTER_SNOW,
      GameSettings$Options.FULLSCREEN_MODE,
      GameSettings$Options.ANIMATED_TERRAIN,
      GameSettings$Options.SWAMP_COLORS,
      GameSettings$Options.RANDOM_ENTITIES,
      SMOOTH_BIOMES,
      GameSettings$Options.CUSTOM_FONTS,
      GameSettings$Options.CUSTOM_COLORS,
      GameSettings$Options.SHOW_CAPES,
      GameSettings$Options.CONNECTED_TEXTURES,
      GameSettings$Options.CUSTOM_ITEMS,
      GameSettings$Options.AA_LEVEL,
      GameSettings$Options.AF_LEVEL,
      GameSettings$Options.ANIMATED_TEXTURES,
      GameSettings$Options.NATURAL_TEXTURES,
      GameSettings$Options.EMISSIVE_TEXTURES,
      GameSettings$Options.HELD_ITEM_TOOLTIPS,
      GameSettings$Options.DROPPED_ITEMS,
      GameSettings$Options.LAZY_CHUNK_LOADING,
      GameSettings$Options.CUSTOM_SKY,
      GameSettings$Options.FAST_MATH,
      GameSettings$Options.FAST_RENDER,
      GameSettings$Options.TRANSLUCENT_BLOCKS,
      GameSettings$Options.DYNAMIC_FOV,
      GameSettings$Options.DYNAMIC_LIGHTS,
      GameSettings$Options.ALTERNATE_BLOCKS,
      CUSTOM_ENTITY_MODELS,
      ADVANCED_TOOLTIPS,
      GameSettings$Options.SCREENSHOT_SIZE,
      GameSettings$Options.CUSTOM_GUIS,
      GameSettings$Options.RENDER_REGIONS,
      GameSettings$Options.SHOW_GL_ERRORS,
      GameSettings$Options.SMART_ANIMATIONS
   };
   public float valueMax;
   public float valueMin;
   public float valueStep;
   public String enumString;
   public boolean enumFloat;

   public void setValueMax(float var1) {
      this.valueMax = var1;
   }

   public float getValueMax() {
      return this.valueMax;
   }

   public float snapToStepClamp(float var1) {
      var1 = this.snapToStep(var1);
      return MathHelper.clamp_float(var1, this.valueMin, this.valueMax);
   }

   public float denormalizeValue(float var1) {
      return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(var1, 0.0F, 1.0F));
   }

   public GameSettings$Options(String var3, boolean var4, boolean var5) {
      this(var3, var4, var5, 0.0F, 1.0F, 0.0F);
   }

   public boolean getEnumFloat() {
      return this.enumFloat;
   }

   public GameSettings$Options(String var3, boolean var4, boolean var5, float var6, float var7, float var8) {
      this.enumString = var3;
      this.enumFloat = var4;
      this.enumBoolean = var5;
      this.valueMin = var6;
      this.valueMax = var7;
      this.valueStep = var8;
   }

   public float normalizeValue(float var1) {
      return MathHelper.clamp_float((this.snapToStepClamp(var1) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
   }

   public float snapToStep(float var1) {
      if (this.valueStep > 0.0F) {
         var1 = this.valueStep * Math.round(var1 / this.valueStep);
      }

      return var1;
   }

   public boolean getEnumBoolean() {
      return this.enumBoolean;
   }

   public int returnEnumOrdinal() {
      return this.ordinal();
   }

   public static GameSettings$Options getEnumOptions(int var0) {
      for (GameSettings$Options var4 : values()) {
         if (var4.returnEnumOrdinal() == var0) {
            return var4;
         }
      }

      return null;
   }

   public String getEnumString() {
      return this.enumString;
   }
}
