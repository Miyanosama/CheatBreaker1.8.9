package com.cheatbreaker.client.ui.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import io.netty.handler.codec.compression.JdkZlibEncoder$1;
import net.minecraft.client.Minecraft;
import net.minecraft.server.management.PreYggdrasilConverter;
import org.apache.log4j.jmx.MethodUnion;
import recovered.unidentified.UnidentifiedClass1171;

public abstract class AbstractModulesGuiElement {
   public int height;
   public Setting setting;
   public float scale;
   public UnidentifiedClass1171 field_0007;
   public int y;
   public MethodUnion field_0002;
   public int x;
   public PreYggdrasilConverter field_0006;
   public int width;
   public int yOffset = 0;
   public JdkZlibEncoder$1 field_0000;

   public void method_23215() {
   }

   public abstract void handleDrawElement(int var1, int var2, float var3);

   public boolean method_13022(Setting var1) {
      if (var1 == null) {
         return false;
      } else {
         if (var1.method_08903() != null) {
            if (CheatBreaker.getInstance().getGlobalSettings().field_0100.getValue().equals("Simple")
               && !var1.method_08903().equals(SettingsDetailLevel.field_0000)) {
               return true;
            }

            if (CheatBreaker.getInstance().getGlobalSettings().field_0100.getValue().equals("Medium")
               && !var1.method_08903().equals(SettingsDetailLevel.field_0000)
               && !var1.method_08903().equals(SettingsDetailLevel.field_0003)) {
               return true;
            }
         }

         return var1.method_08921() == null ? false : !Boolean.valueOf(var1.method_08921().getAsBoolean());
      }
   }

   public void onScroll(int var1) {
   }

   public void setDimensions(int var1, int var2, int var3, int var4) {
      this.x = var1;
      this.y = var2;
      this.width = var3;
      this.height = var4;
   }

   public void method_23220(Setting var1, int var2, int var3) {
   }

   public void method_23221() {
   }

   public abstract void handleMouseClick(int var1, int var2, int var3);

   public void method_23216(char var1, int var2) {
   }

   public boolean isMouseInside(int var1, int var2) {
      return var1 > this.x * this.scale
         && var1 < (this.x + this.width) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + this.height + this.yOffset) * this.scale
         && Minecraft.getMinecraft().currentScreen instanceof CBModulesGui;
   }

   public AbstractModulesGuiElement(float var1) {
      this.scale = var1;
   }

   public int getHeight() {
      return this.height;
   }

   public boolean method_23217(float var1, float var2, int var3, boolean var4) {
      return false;
   }
}
