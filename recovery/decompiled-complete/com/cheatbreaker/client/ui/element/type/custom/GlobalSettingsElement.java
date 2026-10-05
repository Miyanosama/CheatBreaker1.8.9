package com.cheatbreaker.client.ui.element.type.custom;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.xml.DOMConfigurator;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass5100;

public class GlobalSettingsElement extends AbstractModulesGuiElement {
   public int field_0002 = 0;
   public ByteArrayDecoder field_0003;
   public AbstractScrollableElement field_0001;
   public DOMConfigurator field_0004;
   public ResourceLocation field_0005 = new ResourceLocation("client/icons/right.png");
   public int field_0000;

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }

   public GlobalSettingsElement(AbstractScrollableElement var1, int var2, float var3) {
      super(var3);
      this.field_0001 = var1;
      this.field_0000 = var2;
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var4 = this.isMouseInside(var1, var2);
      byte var5 = 75;
      Gui.a(
         this.x,
         this.y + this.height - 1,
         this.x + this.width,
         this.y + this.height,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0035 : UnidentifiedClass5100.field_0026
      );
      float var6 = CBModulesGui.getSmoothFloat(790.0F);
      if (var4) {
         if (this.field_0002 < var5) {
            this.field_0002 = (int)(this.field_0002 + var6);
            if (this.field_0002 > var5) {
               this.field_0002 = var5;
            }
         }
      } else if (this.field_0002 > 0) {
         this.field_0002 = this.field_0002 - var6 < 0.0F ? 0 : (int)(this.field_0002 - var6);
      }

      if (this.field_0002 > 0) {
         float var7 = (float)this.field_0002 / var5 * 100.0F;
         Gui.a(this.x, (int)(this.y + (this.height - this.height * var7 / 100.0F)), this.x + this.width, this.y + this.height, this.field_0000);
      }

      float var8 = GlobalSettings.field_0099.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var8, var8, var8, 0.35F);
      RenderUtil.drawIcon(this.field_0005, 2.5F, this.x + 6, this.y + 6.0F);
      CheatBreaker.getInstance()
         .field_0039
         .drawString(
            "CheatBreaker Settings".toUpperCase(),
            this.x + 14.0F,
            this.y + 3.0F,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0005 : UnidentifiedClass5100.field_0024
         );
   }
}
