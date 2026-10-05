package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.google.common.base.Throwables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;

public class ColorSaturationModule extends AbstractModule {
   public void method_23585(TickEvent var1) {
      if (this.minecraft.currentScreen == null && !this.minecraft.entityRenderer.isShaderActive()) {
         this.method_23586();
      }
   }

   public void method_23586() {
      this.minecraft.entityRenderer.method_29166();
      ShaderGroup var1 = Minecraft.getMinecraft().entityRenderer.getShaderGroup();

      try {
         if (this.minecraft.entityRenderer.isShaderActive() && this.minecraft.thePlayer != null) {
            for (Shader var3 : var1.method_20563()) {
               ;
            }
         }
      } catch (IllegalArgumentException var4) {
         Throwables.propagate(var4);
      }
   }

   public ColorSaturationModule() {
      super("Color Saturation");
      this.setDefaultState(false);
      this.setPreviewLabel("Color Saturation", 1.1F);
      this.method_28821("Allows you to adjust the hue, saturation, brightness, and contrast of your screen.");
      this.method_28820(TickEvent.class, this::method_23585);
   }
}
