package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.google.common.base.Throwables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenClay;

public class MotionBlurModule extends AbstractModule {
   public WorldGenClay field_0002;
   public Setting field_0003;
   public Setting field_0000;
   public Setting field_0001;
   public TileEntityEnderChest field_0004;

   public MotionBlurModule() {
      super("Motion Blur");
      this.setDefaultState(false);
      this.field_0000 = new Setting(this, "Old Blur", "Revert to the previously used motion blur shader.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0003 = new Setting(this, "Amount", "Change the amount of blur intensity.")
         .setValue(10.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0001 = new Setting(this, "Color", "Change the color of the motion blur.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0000.getValue());
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/swordblur.png"), 40, 40);
      this.method_28821("Adds a shader to blur your surroundings while moving.");
      this.method_28829("Fyu (Original)", "Moonsworth LLC (Newer shader)");
      this.method_28820(TickEvent.class, this::method_02063);
   }

   public void method_02063(TickEvent var1) {
      if (this.minecraft.currentScreen == null && !this.minecraft.entityRenderer.isShaderActive()) {
         this.method_02065();
      }
   }

   public void method_02065() {
      this.minecraft.entityRenderer.method_29166();
      ShaderGroup var1 = Minecraft.getMinecraft().entityRenderer.getShaderGroup();

      try {
         if (this.minecraft.entityRenderer.isShaderActive() && this.minecraft.thePlayer != null) {
            for (Shader var3 : var1.method_20563()) {
               ShaderUniform var4 = var3.getShaderManager().method_29622("Phosphor");
               if (var4 != null) {
                  float var5 = (Float)this.field_0003.getValue() / 100.0F * 0.9F;
                  if (var5 >= 1.0F) {
                     var5 = 0.99F;
                  }

                  float var6 = 1.0F - var5;
                  if ((Boolean)this.field_0000.getValue()) {
                     var6 = 0.7F + (Float)this.field_0003.getValue() / 1000.0F * 3.0F - 0.01F;
                  }

                  int var7 = this.field_0000.getValue() ? this.field_0001.method_08901() : -1;
                  float var8 = (var7 >> 16 & 0xFF) / 255.0F;
                  float var9 = (var7 >> 8 & 0xFF) / 255.0F;
                  float var10 = (var7 & 0xFF) / 255.0F;
                  var4.set(var6 * var8, var6 * var9, var6 * var10);
               }
            }
         }
      } catch (IllegalArgumentException var11) {
         Throwables.propagate(var11);
      }
   }
}
