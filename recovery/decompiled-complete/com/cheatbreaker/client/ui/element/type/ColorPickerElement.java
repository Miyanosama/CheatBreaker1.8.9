package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass5100;

public class ColorPickerElement extends AbstractModulesGuiElement {
   public int field_0008;
   public float field_0011;
   public float pickerHeight;
   public boolean field_0013;
   public float pickerY;
   public boolean field_0003;
   public float field_0005;
   public ColorPickerColorElement colorPickerColorElement;
   public float field_0015;
   public boolean field_0002 = false;
   public List<ColorPickerColorElement> colors;
   public int field_0004;
   public float pickerX;
   public int field_0014;
   public int field_0010;
   public float field_0012;
   public float pickerWidth;
   public boolean field_0017;

   public void method_07937() {
      if (CheatBreaker.getInstance().getGlobalSettings().field_0020.size() >= 16) {
         CheatBreaker.getInstance().getGlobalSettings().field_0020.remove(0);
      }

      CheatBreaker.getInstance().getGlobalSettings().field_0020.add(new ColorPickerColorElement(this.scale, (Integer)this.setting.getValue(), 1.0F));
      Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
   }

   public void method_07935(List<ColorPickerColorElement> var1, int var2, int var3, int var4, int var5) {
      int var6 = 0;
      int var7 = 0;
      byte var8 = 8;

      for (ColorPickerColorElement var10 : var1) {
         if (var1 != this.colors) {
            if (var6 == var8) {
               var7++;
               var6 = 0;
            }

            int var12 = var2 + var7 * 12;
            int var11 = var3 + var6 * 12;
            var10.yOffset = this.yOffset;
            var10.setDimensions(var12, var11, 10, 10);
         }

         if (var10.isMouseInside(var4, var5)) {
            if (var1 == this.colors) {
               this.setting.setValue(new Color(var10.color).getRGB());
            } else {
               this.setting.setValue(new Color(var10.color, true).getRGB());
            }

            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            float[] var15 = Color.RGBtoHSB(var10.color >> 16 & 0xFF, var10.color >> 8 & 0xFF, var10.color & 0xFF, null);
            this.field_0012 = var15[0];
            int var14 = (int)(var15[1] * this.pickerWidth);
            int var13 = (int)(this.pickerHeight - var15[2] * this.pickerHeight);
            this.setting.colorArray = new int[]{var14, var13};
         }

         var6++;
      }
   }

   public void method_07938(int var1, int var2) {
      Gui.drawRect(
         this.pickerX + this.pickerWidth + 4.0F,
         this.pickerY - 1.0F,
         this.pickerX + this.pickerWidth + 14.0F,
         this.pickerY + 1.0F + this.pickerHeight,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0037 : UnidentifiedClass5100.field_0013
      );

      for (int var3 = 0; var3 < this.pickerHeight; var3++) {
         if (this.field_0017 && var2 >= (this.yOffset + this.pickerY + var3) * this.scale && var2 <= (this.yOffset + this.pickerY + var3 + 1.0F) * this.scale) {
            int var4 = (Integer)this.setting.getValue();
            float[] var5 = Color.RGBtoHSB(var4 >> 16 & 0xFF, var4 >> 8 & 0xFF, var4 & 0xFF, null);
            this.setting.setValue(Color.HSBtoRGB(this.field_0012, var5[1], var5[2]));
            this.field_0012 = var3 / this.pickerHeight;
         }

         int var6 = Color.HSBtoRGB(var3 / this.pickerHeight, 1.0F, 1.0F);
         Gui.drawRect(this.pickerX + this.pickerWidth + 5.0F, this.pickerY + var3, this.pickerX + this.pickerWidth + 13.0F, this.pickerY + var3 + 1.0F, var6);
      }

      float var7 = -1.0F + this.pickerHeight * this.field_0012;
      Gui.drawRect(this.pickerX + this.pickerWidth + 4.0F, this.pickerY + var7, this.pickerX + this.pickerWidth + 14.0F, this.pickerY + var7 + 3.0F, -822083584);
      Gui.drawRect(
         this.pickerX + this.pickerWidth + 4.0F, this.pickerY + var7 + 1.0F, this.pickerX + this.pickerWidth + 14.0F, this.pickerY + var7 + 2.0F, -805306369
      );
   }

   public void method_07933() {
      boolean var1 = true;

      for (byte var2 = 2; var2 < this.pickerHeight - 4.0F; var2 += 4) {
         if (!var1) {
            Gui.drawRect(this.pickerX + this.pickerWidth + 19.0F, this.pickerY + var2, this.pickerX + this.pickerWidth + 23.0F, this.pickerY + var2 + 4.0F, -1);
            Gui.drawRect(
               this.pickerX + this.pickerWidth + 23.0F, this.pickerY + var2 + 4.0F, this.pickerX + this.pickerWidth + 27.0F, this.pickerY + var2 + 8.0F, -1
            );
            Gui.drawRect(
               this.pickerX + this.pickerWidth + 23.0F, this.pickerY + var2, this.pickerX + this.pickerWidth + 27.0F, this.pickerY + var2 + 4.0F, -7303024
            );
            Gui.drawRect(
               this.pickerX + this.pickerWidth + 19.0F,
               this.pickerY + var2 + 4.0F,
               this.pickerX + this.pickerWidth + 23.0F,
               this.pickerY + var2 + 8.0F,
               -7303024
            );
         }

         var1 = !var1;
      }
   }

   public void method_07934(int var1, int var2) {
      Gui.drawRect(
         this.pickerX + this.pickerWidth + 18.0F,
         this.pickerY - 1.0F,
         this.pickerX + this.pickerWidth + 28.0F,
         this.pickerY + 1.0F + this.pickerHeight,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0037 : UnidentifiedClass5100.field_0013
      );
      this.method_07933();

      for (int var3 = 0; var3 < this.pickerHeight; var3++) {
         int var4 = (Integer)this.setting.getValue();
         int var5 = new Color(var4 >> 16 & 0xFF, var4 >> 8 & 0xFF, var4 & 0xFF, Math.round(255.0F - var3 / this.pickerHeight * 255.0F)).getRGB();
         if (this.field_0013 && var2 >= (this.yOffset + this.pickerY + var3) * this.scale && var2 <= (this.yOffset + this.pickerY + var3 + 1.0F) * this.scale) {
            this.field_0015 = var3 / this.pickerHeight;
            this.setting.setValue(var5);
         }

         Gui.drawRect(this.pickerX + this.pickerWidth + 19.0F, this.pickerY + var3, this.pickerX + this.pickerWidth + 27.0F, this.pickerY + var3 + 1.0F, var5);
      }

      float var6 = -1.0F + this.pickerHeight * this.field_0015;
      Gui.drawRect(
         this.pickerX + this.pickerWidth + 18.0F, this.pickerY + var6, this.pickerX + this.pickerWidth + 28.0F, this.pickerY + var6 + 3.0F, -822083584
      );
      Gui.drawRect(
         this.pickerX + this.pickerWidth + 18.0F, this.pickerY + var6 + 1.0F, this.pickerX + this.pickerWidth + 28.0F, this.pickerY + var6 + 2.0F, -805306369
      );
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > (this.x + this.width - 40) * this.scale
         && var1 < (this.x + this.width - 12) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 18 + this.yOffset) * this.scale;
      boolean var5 = var1 > this.x * this.scale
         && var1 < (this.x + this.width - 40) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 18 + this.yOffset) * this.scale;
      if (var5) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         float[] var6 = Color.RGBtoHSB(
            (Integer)this.setting.getValue() >> 16 & 0xFF, (Integer)this.setting.getValue() >> 8 & 0xFF, (Integer)this.setting.getValue() & 0xFF, null
         );
         this.field_0012 = var6[0];
         int var7 = (int)(var6[1] * this.pickerWidth);
         int var8 = (int)(this.pickerHeight - var6[2] * this.pickerHeight);
         this.setting.colorArray = new int[]{var7, var8};
         this.field_0002 = !this.field_0002;
      } else if (var4) {
         if (CheatBreaker.getInstance().getGlobalSettings().isFavouriteColor((Integer)this.setting.getValue())) {
            CheatBreaker.getInstance().getGlobalSettings().removeFavouriteColor((Integer)this.setting.getValue());
         } else {
            if (CheatBreaker.getInstance().getGlobalSettings().field_0043.size() >= 16) {
               CheatBreaker.getInstance().getGlobalSettings().field_0043.remove(0);
            }

            CheatBreaker.getInstance().getGlobalSettings().field_0043.add(new ColorPickerColorElement(this.scale, (Integer)this.setting.getValue(), 1.0F));
         }

         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
      } else if (this.field_0002) {
         this.method_07935(this.colors, 0, 0, var1, var2);
         this.method_07935(CheatBreaker.getInstance().getGlobalSettings().field_0020, this.field_0014, this.field_0004, var1, var2);
         this.method_07935(CheatBreaker.getInstance().getGlobalSettings().field_0043, this.field_0010, this.field_0008, var1, var2);
         boolean var9 = var1 > (this.pickerX - 51.0F) * this.scale
            && var2 > (this.pickerY + 1.0F + this.yOffset) * this.scale
            && var1 < (this.pickerX - 43.0F) * this.scale
            && var2 < (this.pickerY + 9.0F + this.yOffset) * this.scale;
         boolean var10 = var1 > (this.pickerX - 51.0F) * this.scale
            && var2 > (this.pickerY + 10.0F + this.yOffset) * this.scale
            && var1 < (this.pickerX - 43.0F) * this.scale
            && var2 < (this.pickerY + 19.0F + this.yOffset) * this.scale;
         if (var1 > this.pickerX * this.scale
            && var1 < (this.pickerX + this.pickerWidth) * this.scale
            && var2 > (this.pickerY + this.yOffset) * this.scale
            && var2 < (this.pickerY + this.pickerHeight + this.yOffset) * this.scale) {
            this.field_0003 = true;
         }

         if (var1 > (this.pickerX + this.pickerWidth + 4.0F) * this.scale
            && var1 < (this.pickerX + this.pickerWidth + 14.0F) * this.scale
            && var2 > (this.pickerY - 1.0F + this.yOffset) * this.scale
            && var2 < (this.pickerY + 1.0F + this.pickerHeight + this.yOffset) * this.scale) {
            this.field_0017 = true;
         }

         if (var1 > (this.pickerX + this.pickerWidth + 18.0F) * this.scale
            && var1 < (this.pickerX + this.pickerWidth + 28.0F) * this.scale
            && var2 > (this.pickerY - 1.0F + this.yOffset) * this.scale
            && var2 < (this.pickerY + 1.0F + this.pickerHeight + this.yOffset) * this.scale) {
            this.field_0013 = true;
         }

         if (var9) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.setting.field_0013 = !this.setting.field_0013;
         }

         if (var10) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.setting.field_0026 = !this.setting.field_0026;
         }
      }

      if (this.setting == CheatBreaker.getInstance().getModuleManager().field_0047.field_0015) {
         Minecraft.getMinecraft().renderGlobal.loadRenderers();
      }
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      this.height = this.field_0002 ? 130 : 18;
      this.pickerX = this.x + 56;
      this.field_0005 = this.x + 176;
      this.pickerY = this.y + 25;
      this.field_0011 = this.y + 119;
      this.pickerWidth = this.field_0005 - this.pickerX;
      this.pickerHeight = this.field_0011 - this.pickerY;
      CheatBreaker.getInstance()
         .field_0068
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + 4,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0000 : UnidentifiedClass5100.field_0010
         );
      this.colorPickerColorElement.color = this.setting.method_08901();
      this.colorPickerColorElement.setDimensions(this.x + 160, this.y + 3, 14, 14);
      this.colorPickerColorElement.yOffset = this.yOffset;
      this.colorPickerColorElement.handleDrawElement(var1, var2, var3);
      Gui.a(
         this.x + 186,
         this.y + 16,
         this.x + this.width - 16,
         this.y + 17,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0014 : UnidentifiedClass5100.field_0009
      );
      float var10002 = this.x + 188;
      CheatBreaker.getInstance()
         .field_0039
         .drawString("#", var10002, this.y + 4, GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0030 : UnidentifiedClass5100.field_0029);
      CheatBreaker.getInstance()
         .field_0039
         .drawString(
            Integer.toHexString(this.setting.method_08901()),
            this.x + 194,
            this.y + 4,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0030 : UnidentifiedClass5100.field_0029
         );
      boolean var4 = var1 > (this.x + this.width - 40) * this.scale
         && var1 < (this.x + this.width - 12) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 18 + this.yOffset) * this.scale;
      String var5 = var4 ? "(Favorite)" : "(+)";
      if (CheatBreaker.getInstance().getGlobalSettings().isFavouriteColor((Integer)this.setting.getValue())) {
         var5 = var4 ? "(Un-favorite)" : "(-)";
      }

      CheatBreaker.getInstance()
         .field_0039
         .drawString(
            var5,
            this.x + this.width - 16 - CheatBreaker.getInstance().field_0039.getStringWidth(var5),
            this.y + 4,
            var4
               ? (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0037 : UnidentifiedClass5100.field_0013)
               : (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0030 : UnidentifiedClass5100.field_0029)
         );
      this.method_23220(this.setting, var1, var2);
      if (this.field_0002) {
         if (this.field_0003 && !Mouse.isButtonDown(0)) {
            this.field_0003 = false;
            this.method_07937();
         }

         if (this.field_0017 && !Mouse.isButtonDown(0)) {
            this.field_0017 = false;
            this.method_07937();
         }

         if (this.field_0013 && !Mouse.isButtonDown(0)) {
            this.field_0013 = false;
            this.method_07937();
         }

         Gui.a(
            this.x + 55,
            this.y + 24,
            this.x + 177,
            this.y + 120,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0037 : UnidentifiedClass5100.field_0013
         );
         Tessellator var6 = Tessellator.getInstance();
         GL11.glDisable(3553);
         WorldRenderer var7 = var6.getWorldRenderer();
         var7.begin(7, DefaultVertexFormats.POSITION);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         var7.pos(this.pickerX, this.field_0011, 0.0);
         var7.pos(this.field_0005, this.field_0011, 0.0);
         var7.pos(this.field_0005, this.pickerY, 0.0);
         var7.pos(this.pickerX, this.pickerY, 0.0);
         var6.draw();
         int[] var8 = null;

         for (int var9 = 0; var9 < this.pickerWidth; var9++) {
            for (int var10 = 0; var10 < this.pickerHeight; var10++) {
               float var11 = var9 / this.pickerWidth;
               float var12 = 1.0F - var10 / this.pickerHeight;
               int var13 = (int)this.field_0015 << 24 | Color.HSBtoRGB(this.field_0012, var11, var12);
               boolean var14 = var1 >= (this.pickerX + var9) * this.scale && var1 <= (this.pickerX + var9 + 1.0F) * this.scale;
               boolean var15 = var2 <= (this.pickerY + var10 + 1.0F + this.yOffset) * this.scale && var2 > (this.pickerY + var10 + this.yOffset) * this.scale;
               boolean var16 = var14 && var15;
               boolean var17 = var9 == 0 && var1 < this.pickerX * this.scale && var15;
               boolean var18 = var10 == 0 && var2 < (this.pickerY + this.yOffset) * this.scale && var14;
               boolean var19 = var9 == this.pickerWidth - 1.0F && var1 > (this.pickerX + this.pickerWidth) * this.scale && var15;
               boolean var20 = var10 == this.pickerHeight - 1.0F && var2 > (this.pickerY + this.pickerHeight + this.yOffset) * this.scale && var14;
               if (this.field_0003 && (var16 || var17 || var18 || var19 || var20)) {
                  this.setting.setValue(var13);
                  this.setting.colorArray = new int[]{var9, var10};
               }

               if (this.setting.colorArray != null) {
                  var8 = this.setting.colorArray;
               } else if (var13 == (Integer)this.setting.getValue()) {
                  var8 = new int[]{var9, var10};
               }

               var7.begin(7, DefaultVertexFormats.POSITION);
               GL11.glColor4f((var13 >> 16 & 0xFF) / 255.0F, (var13 >> 8 & 0xFF) / 255.0F, (var13 & 0xFF) / 255.0F, 1.0F);
               var7.pos(this.pickerX + var9, this.pickerY + var10 + 1.0F, 0.0).endVertex();
               var7.pos(this.pickerX + var9 + 1.0F, this.pickerY + var10 + 1.0F, 0.0).endVertex();
               var7.pos(this.pickerX + var9 + 1.0F, this.pickerY + var10, 0.0).endVertex();
               var7.pos(this.pickerX + var9, this.pickerY + var10, 0.0).endVertex();
               var6.draw();
            }
         }

         if (var8 != null) {
            GL11.glPushMatrix();
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.75F);
            RenderUtil.method_22052(this.pickerX + var8[0] + 1.115F, this.pickerY + var8[1] + 1.115F, 4.0);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtil.method_22052(this.pickerX + var8[0] + 1.115F, this.pickerY + var8[1] + 1.115F, 2.7);
            GL11.glPopMatrix();
         }

         Gui.drawRect(this.pickerX - 51.0F, this.pickerY + 1.0F, this.pickerX - 43.0F, this.pickerY + 9.0F, -16777216);
         Gui.drawRect(this.pickerX - 50.0F, this.pickerY + 2.0F, this.pickerX - 44.0F, this.pickerY + 8.0F, this.setting.field_0013 ? -13369549 : -1);
         var10002 = this.pickerX - 40.0F;
         CheatBreaker.getInstance()
            .field_0036
            .drawString(
               "CHROMA", var10002, this.pickerY, GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0030 : UnidentifiedClass5100.field_0029
            );
         if (this.setting.field_0013) {
            Gui.drawRect(this.pickerX - 51.0F, this.pickerY + 10.0F, this.pickerX - 43.0F, this.pickerY + 18.0F, -16777216);
            Gui.drawRect(this.pickerX - 50.0F, this.pickerY + 11.0F, this.pickerX - 44.0F, this.pickerY + 17.0F, this.setting.field_0026 ? -13369549 : -1);
            var10002 = this.pickerX - 40.0F;
            CheatBreaker.getInstance()
               .field_0036
               .drawString(
                  "FAST",
                  var10002,
                  this.pickerY + 10.0F,
                  GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0030 : UnidentifiedClass5100.field_0029
               );
         }

         this.method_07938(var1, var2);
         this.method_07934(var1, var2);
         this.field_0014 = (int)(this.pickerX + this.pickerWidth + 64.0F);
         this.field_0004 = (int)this.pickerY;
         this.drawColorList(CheatBreaker.getInstance().getGlobalSettings().field_0020, this.field_0014, this.field_0004, var1, var2, (int)var3);
         this.field_0010 = (int)(this.pickerX + this.pickerWidth + 94.0F);
         this.field_0008 = (int)this.pickerY;
         this.drawColorList(CheatBreaker.getInstance().getGlobalSettings().field_0043, this.field_0010, this.field_0008, var1, var2, (int)var3);
         this.drawColorList(this.colors, (int)(this.pickerX + this.pickerWidth + 34.0F), (int)this.pickerY, var1, var2, (int)var3);
      }
   }

   public ColorPickerElement(Setting var1, float var2) {
      super(var2);
      this.field_0003 = false;
      this.field_0017 = false;
      this.field_0013 = false;
      this.field_0012 = 1.0F;
      this.field_0015 = 1.0F;
      this.setting = var1;
      this.colorPickerColorElement = new ColorPickerColorElement(var2, (Integer)var1.getValue(), 1.0F);
      this.colors = new ArrayList<>();

      for (int var3 = 0; var3 < 16; var3++) {
         int var4 = Minecraft.getMinecraft().fontRendererObj.colorCode[var3];
         this.colors.add(new ColorPickerColorElement(var2, var4, 1.0F));
      }
   }

   public void drawColorList(List<ColorPickerColorElement> var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = 0;
      int var8 = 0;
      byte var9 = 8;

      for (ColorPickerColorElement var11 : var1) {
         var11.scale = this.scale;
         if (var7 == var9) {
            var8++;
            var7 = 0;
         }

         if (var1 == this.colors) {
            int var12 = var9 * 2 / 8 * 12;
            int var13 = var2 + var12 - var8 * 12 - 12;
            int var14 = var3 + var7 * var12 - var7 * 12;
            var11.yOffset = this.yOffset;
            var11.setDimensions(var13, var14, 10, 10);
            String var15 = "0123456789abcdefklmnor";
            int var16 = var7 + var8 * var9;
            String var17 = var15.substring(var16, var16 + 1);
            if (var11.isMouseInside(var4, var5)) {
               Gui.a(var13 + 12, var14 - 1, var13 + 26, var14 + 11, -1087492562);
               CheatBreaker.getInstance().field_0068.drawStringWithShadow("&" + var17, var13 + 14, var14, -1);
            }
         } else {
            int var18 = var2 + var8 * 12;
            int var19 = var3 + var7 * 12;
            var11.yOffset = this.yOffset;
            var11.setDimensions(var18, var19, 10, 10);
         }

         var11.handleDrawElement(var4, var5, var6);
         var7++;
      }
   }
}
