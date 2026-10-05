package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import io.netty.handler.codec.rtsp.RtspObjectDecoder;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.WorldRenderer$2;
import net.minecraft.command.CommandFill;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass5100;

public class ModulesGuiButtonElement extends AbstractModulesGuiElement {
   public boolean field_0007;
   public AbstractScrollableElement field_0009;
   public int field_0006;
   public int field_0010;
   public WorldRenderer$2 field_0013;
   public CommandFill field_0003;
   public CBFontRenderer field_0005;
   public boolean field_0008;
   public boolean field_0012;
   public boolean field_0002;
   public boolean field_0001 = true;
   public String displayString;
   public RtspObjectDecoder field_0000;
   public boolean field_0011;

   public void method_07928(boolean var1) {
      this.field_0001 = var1;
   }

   public ModulesGuiButtonElement(
      CBFontRenderer var1, AbstractScrollableElement var2, String var3, int var4, int var5, int var6, int var7, int var8, float var9, boolean var10
   ) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.field_0008 = var10;
   }

   public void method_07931(boolean var1) {
      this.field_0007 = var1;
   }

   public void method_07927(boolean var1) {
      this.field_0011 = var1;
   }

   public ModulesGuiButtonElement(
      CBFontRenderer var1,
      AbstractScrollableElement var2,
      String var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      float var9,
      boolean var10,
      boolean var11
   ) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.field_0008 = var10;
      this.field_0012 = var11;
   }

   public ModulesGuiButtonElement(AbstractScrollableElement var1, String var2, int var3, int var4, int var5, int var6, int var7, float var8) {
      this(CheatBreaker.getInstance().field_0062, var1, var2, var3, var4, var5, var6, var7, var8);
      this.field_0008 = true;
   }

   public int method_07929() {
      return this.width;
   }

   public void method_07930(boolean var1) {
      this.field_0002 = var1;
   }

   public ModulesGuiButtonElement(
      CBFontRenderer var1, AbstractScrollableElement var2, String var3, int var4, int var5, int var6, int var7, int var8, float var9
   ) {
      super(var9);
      this.field_0010 = 0;
      this.displayString = var3;
      this.setDimensions(var4, var5, var6, var7);
      this.field_0006 = var8;
      this.field_0009 = var2;
      this.field_0005 = var1;
      this.field_0008 = true;
   }

   public ModulesGuiButtonElement(AbstractScrollableElement var1, String var2, int var3, int var4, int var5, int var6, int var7, float var8, boolean var9) {
      this(CheatBreaker.getInstance().field_0062, var1, var2, var3, var4, var5, var6, var7, var8);
      this.field_0008 = var9;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var5 = this.isMouseInside(var1, var2);
      byte var6 = 120;
      if (var5 && this.field_0001) {
         if (this.field_0008) {
            Gui.a(
               this.x - 2,
               this.y - 2,
               this.x + this.width + 2,
               this.y + this.height + 2,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0021 : UnidentifiedClass5100.field_0036
            );
         }

         float var11 = CBModulesGui.getSmoothFloat(790.0F);
         this.field_0010 = this.field_0010 + var11 < var6 ? (int)(this.field_0010 + var11) : var6;
      } else if (this.field_0010 > 0) {
         float var4 = CBModulesGui.getSmoothFloat(790.0F);
         this.field_0010 = this.field_0010 - var4 < 0.0F ? 0 : (int)(this.field_0010 - var4);
      }

      if (this.field_0012) {
         Gui.a(
            this.x,
            this.y,
            this.x + this.width,
            this.y + this.height,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0018 : UnidentifiedClass5100.field_0001
         );
      }

      if (this.field_0008) {
         if (this.field_0001) {
            Gui.a(
               this.x,
               this.y,
               this.x + this.width,
               this.y + this.height,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0019 : UnidentifiedClass5100.field_0011
            );
         } else {
            Gui.a(
               this.x,
               this.y,
               this.x + this.width,
               this.y + this.height,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0031 : UnidentifiedClass5100.field_0007
            );
         }
      }

      if (this.field_0010 > 0) {
         float var12 = (float)this.field_0010 / var6 * 100.0F;
         Gui.a(this.x, (int)(this.y + (this.height - this.height * var12 / 100.0F)), this.x + this.width, this.y + this.height, this.field_0006);
      }

      if (this.displayString.contains(".png")) {
         float var7 = this.field_0002 ? 3.5F : 8.0F;
         float var8 = this.field_0007 ? this.x + 2.0F : (this.field_0002 ? (float)(this.x + 1.6) : this.x + 6);
         float var9 = this.field_0007 ? this.y + 2.0F : (this.field_0002 ? (float)(this.y + 1.7) : this.y + 6);
         GL11.glPushMatrix();
         float var10 = GlobalSettings.field_0099.method_08908() ? 1.0F : 0.0F;
         GL11.glColor4f(var10, var10, var10, 0.45F);
         RenderUtil.drawIcon(new ResourceLocation("client/icons/" + this.displayString), var7, var8, var9);
         GL11.glPopMatrix();
      } else {
         float var13 = this.field_0005 == CheatBreaker.getInstance().field_0062 ? 2.0F : 0.5F;
         this.field_0005
            .drawCenteredString(
               this.displayString.toUpperCase(),
               this.x + this.width / 2,
               this.y + this.height / 2 - this.field_0005.getHeight() + var13,
               this.field_0011 ? 11141120 : (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015)
            );
      }
   }
}
