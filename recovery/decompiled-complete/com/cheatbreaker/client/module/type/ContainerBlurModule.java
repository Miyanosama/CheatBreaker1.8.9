package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.google.common.base.Throwables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager$BooleanState;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;

public class ContainerBlurModule extends AbstractModule {
   public WorldGenBigMushroom field_0000;
   public GlStateManager$BooleanState field_0001;

   public ContainerBlurModule() {
      super("Container Blur");
      this.setDefaultState(false);
      this.setPreviewLabel("Container Blur", 1.1F);
      this.method_28821("Adds a blur to containers.");
      this.method_28820(TickEvent.class, this::method_26846);
   }

   public void method_26847() {
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

   public void method_26846(TickEvent var1) {
      if (this.minecraft.currentScreen == null && !this.minecraft.entityRenderer.isShaderActive()) {
         this.method_26847();
      }
   }
}
