package net.optifine.shaders.config;

import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.entity.monster.EntityGuardian$1;
import net.minecraft.network.play.server.S14PacketEntity$S15PacketEntityRelMove;
import net.optifine.reflect.ReflectorFields;

public enum EnumShaderOption {
   NORMAL_MAP("of.options.shaders.NORMAL_MAP", "normalMapEnabled", "true"),
   RENDER_RES_MUL("of.options.shaders.RENDER_RES_MUL", "renderResMul", "1.0"),
   TEX_MAG_FIL_B("of.options.shaders.TEX_MAG_FIL_B", "TexMagFilB", "0"),
   TWEAK_BLOCK_DAMAGE("of.options.shaders.TWEAK_BLOCK_DAMAGE", "tweakBlockDamage", "false"),
   ANTIALIASING("of.options.shaders.ANTIALIASING", "antialiasingLevel", "0"),
   SHADOW_CLIP_FRUSTRUM("of.options.shaders.SHADOW_CLIP_FRUSTRUM", "shadowClipFrustrum", "true"),
   SPECULAR_MAP("of.options.shaders.SPECULAR_MAP", "specularMapEnabled", "true"),
   TEX_MAG_FIL_N("of.options.shaders.TEX_MAG_FIL_N", "TexMagFilN", "0"),
   CLOUD_SHADOW("of.options.shaders.CLOUD_SHADOW", "cloudShadow", "true"),
   TEX_MAG_FIL_S("of.options.shaders.TEX_MAG_FIL_S", "TexMagFilS", "0"),
   TEX_MIN_FIL_S("of.options.shaders.TEX_MIN_FIL_S", "TexMinFilS", "0"),
   TEX_MIN_FIL_B("of.options.shaders.TEX_MIN_FIL_B", "TexMinFilB", "0"),
   HAND_DEPTH_MUL("of.options.shaders.HAND_DEPTH_MUL", "handDepthMul", "0.125"),
   TEX_MIN_FIL_N("of.options.shaders.TEX_MIN_FIL_N", "TexMinFilN", "0"),
   SHADOW_RES_MUL("of.options.shaders.SHADOW_RES_MUL", "shadowResMul", "1.0"),
   OLD_HAND_LIGHT("of.options.shaders.OLD_HAND_LIGHT", "oldHandLight", "default"),
   OLD_LIGHTING("of.options.shaders.OLD_LIGHTING", "oldLighting", "default"),
   SHADER_PACK("of.options.shaders.SHADER_PACK", "shaderPack", "");
   public String resourceKey = null;
   public String valueDefault;
   public S14PacketEntity$S15PacketEntityRelMove field_0020;
   // $VF: synthetic field
   public static EnumShaderOption[] $VALUES = new EnumShaderOption[]{
      EnumShaderOption.ANTIALIASING,
      NORMAL_MAP,
      EnumShaderOption.SPECULAR_MAP,
      RENDER_RES_MUL,
      EnumShaderOption.SHADOW_RES_MUL,
      EnumShaderOption.HAND_DEPTH_MUL,
      EnumShaderOption.CLOUD_SHADOW,
      EnumShaderOption.OLD_HAND_LIGHT,
      EnumShaderOption.OLD_LIGHTING,
      EnumShaderOption.SHADER_PACK,
      EnumShaderOption.TWEAK_BLOCK_DAMAGE,
      EnumShaderOption.SHADOW_CLIP_FRUSTRUM,
      EnumShaderOption.TEX_MIN_FIL_B,
      EnumShaderOption.TEX_MIN_FIL_N,
      EnumShaderOption.TEX_MIN_FIL_S,
      TEX_MAG_FIL_B,
      EnumShaderOption.TEX_MAG_FIL_N,
      EnumShaderOption.TEX_MAG_FIL_S
   };
   public String propertyKey = null;
   public AnimationMetadataSectionSerializer field_0014;
   public ReflectorFields field_0001;
   public EntityGuardian$1 field_0019;

   public EnumShaderOption(String var3, String var4, String var5) {
      this.valueDefault = null;
      this.resourceKey = var3;
      this.propertyKey = var4;
      this.valueDefault = var5;
   }

   public String getValueDefault() {
      return this.valueDefault;
   }

   public String getResourceKey() {
      return this.resourceKey;
   }

   public String getPropertyKey() {
      return this.propertyKey;
   }
}
