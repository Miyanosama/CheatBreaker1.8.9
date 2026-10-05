package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CrosshairPreviewElement extends AbstractModulesGuiElement {
   public CrosshairPreviewElement(float var1) {
      super(var1);
      this.height = 50;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      Gui.a(this.x + (this.width / 2 - 15) - 41, this.y + 4, this.x + (this.width / 2 - 15) + 41, this.y + 51, -16777216);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22064(
         new ResourceLocation(
            "client/defaults/crosshair_"
               + CheatBreaker.getInstance().getModuleManager().recoveredField1721.recoveredField2712.method_08874().toLowerCase()
               + ".png"
         ),
         this.x + (this.width / 2 - 15) - 40,
         this.y + 5,
         80.0F,
         45.0F
      );
      Gui.recoveredField2942 = 0.0F;
      float var10001 = this.x + this.width / 2 - 15;
      CheatBreaker.getInstance().getModuleManager().recoveredField1721.method_28369(var10001, this.y + this.height / 2 + 3, false);
   }
}
