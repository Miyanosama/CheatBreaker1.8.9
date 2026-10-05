package com.cheatbreaker.client.module.type.keystrokes;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class KeystrokeKey {
   public Color recoveredField2262;
   public float recoveredField2263;
   public Color recoveredField2264;
   public long recoveredField2265;
   public Color recoveredField2266;
   public float recoveredField2267;
   public Color recoveredField2268;
   public int recoveredField2269;
   public Color recoveredField2270;
   public String recoveredField2271;
   public Color recoveredField2272;
   public Color recoveredField2273;
   public boolean recoveredField2274;
   public Color recoveredField2275;

   public KeystrokeKey(String var1, int var2, float var3, float var4) {
      this.recoveredField2271 = var1;
      this.recoveredField2269 = var2;
      this.recoveredField2267 = var3;
      this.recoveredField2263 = var4;
   }

   public int method_02422() {
      return this.recoveredField2269;
   }

   public String method_02425() {
      return this.recoveredField2271;
   }

   public float method_02421() {
      return this.recoveredField2263;
   }

   public float method_02424() {
      return this.recoveredField2267;
   }

   public void method_02423(float var1, float var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
      Minecraft var10 = Minecraft.getMinecraft();
      int var11 = this.recoveredField2269 != -99 && this.recoveredField2269 != -100 ? -1 : (this.recoveredField2269 == -99 ? 1 : 0);
      boolean var12 = (var10.currentScreen == null || var10.currentScreen instanceof GuiContainer || var10.currentScreen instanceof CBModulesGui)
         && (var11 != -1 ? Mouse.isButtonDown(var11) : Keyboard.isKeyDown(this.recoveredField2269));
      if (var12 && !this.recoveredField2274) {
         this.recoveredField2274 = true;
         this.recoveredField2265 = System.currentTimeMillis();
         this.recoveredField2266 = new Color(var5, true);
         this.recoveredField2270 = new Color(var6, true);
         this.recoveredField2262 = new Color(var3, true);
         this.recoveredField2264 = new Color(var4, true);
         this.recoveredField2272 = new Color(CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2321.method_08901(), true);
         this.recoveredField2268 = new Color(CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2329.method_08901(), true);
         this.recoveredField2273 = new Color(var7, true);
         this.recoveredField2275 = new Color(var8, true);
      } else if (this.recoveredField2274 && !var12) {
         this.recoveredField2274 = false;
         this.recoveredField2265 = System.currentTimeMillis();
         this.recoveredField2266 = new Color(var6, true);
         this.recoveredField2270 = new Color(var5, true);
         this.recoveredField2262 = new Color(var4, true);
         this.recoveredField2264 = new Color(var3, true);
         this.recoveredField2272 = new Color(CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2329.method_08901(), true);
         this.recoveredField2268 = new Color(CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2321.method_08901(), true);
         this.recoveredField2273 = new Color(var8, true);
         this.recoveredField2275 = new Color(var7, true);
      }

      float var13 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2338.getValue();
      int var14;
      if ((float)(System.currentTimeMillis() - this.recoveredField2265) < var13) {
         float var15 = (float)(System.currentTimeMillis() - this.recoveredField2265) / var13;
         var14 = new Color(
               (int)Math.abs(var15 * this.recoveredField2270.getRed() + (1.0F - var15) * this.recoveredField2266.getRed()),
               (int)Math.abs(var15 * this.recoveredField2270.getGreen() + (1.0F - var15) * this.recoveredField2266.getGreen()),
               (int)Math.abs(var15 * this.recoveredField2270.getBlue() + (1.0F - var15) * this.recoveredField2266.getBlue()),
               (int)Math.abs(var15 * this.recoveredField2270.getAlpha() + (1.0F - var15) * this.recoveredField2266.getAlpha())
            )
            .getRGB();
      } else {
         var14 = var12 ? var6 : var5;
      }

      float var30 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2351.getValue();
      int var16;
      int var17;
      int var18;
      if ((float)(System.currentTimeMillis() - this.recoveredField2265) < var30) {
         float var19 = (float)(System.currentTimeMillis() - this.recoveredField2265);
         float var20 = var19 / var30;
         int var21 = (int)Math.abs(var20 * this.recoveredField2264.getRed() + (1.0F - var20) * this.recoveredField2262.getRed());
         int var22 = (int)Math.abs(var20 * this.recoveredField2264.getGreen() + (1.0F - var20) * this.recoveredField2262.getGreen());
         int var23 = (int)Math.abs(var20 * this.recoveredField2264.getBlue() + (1.0F - var20) * this.recoveredField2262.getBlue());
         int var24 = (int)Math.abs(var20 * this.recoveredField2264.getAlpha() + (1.0F - var20) * this.recoveredField2262.getAlpha());
         int var25 = (int)Math.abs(var20 * this.recoveredField2268.getRed() + (1.0F - var20) * this.recoveredField2272.getRed());
         int var26 = (int)Math.abs(var20 * this.recoveredField2268.getGreen() + (1.0F - var20) * this.recoveredField2272.getGreen());
         int var27 = (int)Math.abs(var20 * this.recoveredField2268.getBlue() + (1.0F - var20) * this.recoveredField2272.getBlue());
         int var28 = (int)Math.abs(var20 * this.recoveredField2268.getAlpha() + (1.0F - var20) * this.recoveredField2272.getAlpha());
         var16 = new Color(var21, var22, var23, var24).getRGB();
         var17 = new Color(var25, var26, var27, var28).getRGB();
         var18 = new Color(var21 / 4, var22 / 4, var23 / 4, var24).getRGB();
      } else {
         var16 = var12 ? var4 : var3;
         var17 = var12
            ? CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2329.method_08901()
            : CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2321.method_08901();
         int var31 = var4 >> 16 & 0xFF;
         int var33 = var4 >> 8 & 0xFF;
         int var35 = var4 & 0xFF;
         int var40 = var3 >> 16 & 0xFF;
         int var43 = var3 >> 8 & 0xFF;
         int var45 = var3 & 0xFF;
         var18 = var12 ? new Color(var31 / 4, var33 / 4, var35 / 4).getRGB() : new Color(var40 / 4, var43 / 4, var45 / 4).getRGB();
      }

      float var32 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2336.getValue();
      int var34;
      if ((float)(System.currentTimeMillis() - this.recoveredField2265) < var32) {
         float var36 = (float)(System.currentTimeMillis() - this.recoveredField2265) / var32;
         var34 = new Color(
               (int)Math.abs(var36 * this.recoveredField2275.getRed() + (1.0F - var36) * this.recoveredField2273.getRed()),
               (int)Math.abs(var36 * this.recoveredField2275.getGreen() + (1.0F - var36) * this.recoveredField2273.getGreen()),
               (int)Math.abs(var36 * this.recoveredField2275.getBlue() + (1.0F - var36) * this.recoveredField2273.getBlue()),
               (int)Math.abs(var36 * this.recoveredField2275.getAlpha() + (1.0F - var36) * this.recoveredField2273.getAlpha())
            )
            .getRGB();
      } else {
         var34 = var12 ? var8 : var7;
      }

      if ((Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2352.getValue()) {
         Gui.drawRect(var1, var2, var1 + this.recoveredField2267, var2 + this.recoveredField2263, var14);
      }

      if ((Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2337.getValue()) {
         float var37 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2320.getValue();
         Gui.method_00886(var1 - var37, var2 - var37, var1 + this.recoveredField2267 + var37, var2 + this.recoveredField2263 + var37, var37, var34);
      }

      if (this.recoveredField2269 == var10.gameSettings.keyBindJump.getKeyCode()
         && (Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2326.getValue()) {
         float var39 = (Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2349.getValue()
            ? this.recoveredField2263 / 2.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2327.getValue() / 2.0F
            : 3.0F;
         if (var9) {
            Gui.drawRect(
               var1
                  + this.recoveredField2267 / 2.0F
                  - this.recoveredField2267 / (12.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2324.getValue())
                  + 1.0F,
               var2 + var39 + 1.0F,
               var1
                  + this.recoveredField2267 / 2.0F
                  + this.recoveredField2267 / (12.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2324.getValue())
                  + 1.0F,
               var2 + var39 + 1.0F + (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2327.getValue(),
               var18
            );
         }

         Gui.drawRect(
            var1
               + this.recoveredField2267 / 2.0F
               - this.recoveredField2267 / (12.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2324.getValue()),
            var2 + var39,
            var1
               + this.recoveredField2267 / 2.0F
               + this.recoveredField2267 / (12.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2324.getValue()),
            var2 + var39 + (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2327.getValue(),
            var16
         );
      } else {
         GlStateManager.enableBlend();
         float var38 = var1 + this.recoveredField2267 / 2.0F - var10.fontRendererObj.getStringWidth(this.recoveredField2271) / 2 + 0.26F;
         float var41 = var2 + this.recoveredField2263 / 2.0F - var10.fontRendererObj.FONT_HEIGHT / 2 + 0.65F;
         if ((Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2318.getValue()) {
            var38 = var1 + this.recoveredField2267 / 2.0F - var10.fontRendererObj.getStringWidth(this.recoveredField2271) / 2;
            var41 = var2 + this.recoveredField2263 / 2.0F - var10.fontRendererObj.FONT_HEIGHT / 2 + 1.0F;
         }

         float var44 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2341.getValue();
         boolean var46 = this.recoveredField2269 == var10.gameSettings.recoveredField2699.getKeyCode();
         boolean var47 = this.recoveredField2269 == var10.gameSettings.recoveredField2680.getKeyCode();
         String var48 = var46
            ? (String)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2307.getValue()
            : (var47 ? (String)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2314.getValue() : "");
         if (var46 || var47) {
            if (var44 > 14.0F && var48.equals("With Clicks")) {
               GL11.glPushMatrix();
               float var50 = 0.6666F;
               GL11.glScalef(var50, var50, 0.0F);
               var41 = var2 + this.recoveredField2263 / 2.0F - var10.fontRendererObj.FONT_HEIGHT / 2 + 6.0F;
               String var52 = CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2340.size() + " CPS";
               if (this.recoveredField2271.equals("RMB")) {
                  var52 = CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2322.size() + " CPS";
               }

               float var53 = var1 + this.recoveredField2267 / 2.0F - var10.fontRendererObj.getStringWidth(var52) / 2.0F * var50;
               var10.fontRendererObj.drawString(var52, var53 / var50, var41 / var50, var17, var9);
               GL11.glScalef(1.0F, 1.0F, 0.0F);
               GL11.glPopMatrix();
               var41 = var2 + this.recoveredField2263 / 2.0F - var10.fontRendererObj.FONT_HEIGHT / 2 - 3.0F;
            } else if (var48.equals("Replace Clicks")) {
               GL11.glPushMatrix();
               float var49 = 1.0F;
               GL11.glScalef(var49, var49, 0.0F);
               String var51 = this.recoveredField2271;
               String var29 = var44 < 15.0F ? "" : " CPS";
               if (var46 && CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2340.size() > 0) {
                  var51 = CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2340.size() + var29;
                  if (var44 > 15.0F) {
                     var49 = 0.6666F;
                     GL11.glScalef(var49, var49, 0.0F);
                  }
               }

               if (var47 && CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2322.size() > 0) {
                  var51 = CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2322.size() + var29;
                  if (var44 > 15.0F) {
                     var49 = 0.6666F;
                     GL11.glScalef(var49, var49, 0.0F);
                  }
               }

               var38 = var1 + this.recoveredField2267 / 2.0F - var10.fontRendererObj.getStringWidth(var51) / 2.0F * var49 + 0.26F;
               var41 = var2
                  + this.recoveredField2263 / 2.0F
                  - var10.fontRendererObj.FONT_HEIGHT / 2
                  + (var44 > 15.0F && !var51.equals(this.recoveredField2271) ? 1.65F : 0.65F);
               if ((Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.recoveredField2318.getValue()) {
                  var38 = var1 + this.recoveredField2267 / 2.0F - var10.fontRendererObj.getStringWidth(var51) / 2.0F * var49;
                  var41 = var2
                     + this.recoveredField2263 / 2.0F
                     - var10.fontRendererObj.FONT_HEIGHT / 2
                     + (var44 > 15.0F && !var51.equals(this.recoveredField2271) ? 2.0F : 1.0F);
               }

               var10.fontRendererObj.drawString(var51, var38 / var49, var41 / var49, var16, var9);
               GL11.glScalef(1.0F, 1.0F, 0.0F);
               GL11.glPopMatrix();
            }
         }

         if (!var48.equals("Replace Clicks")) {
            var10.fontRendererObj.drawString(this.recoveredField2271, var38, var41, var16, var9);
         }

         GlStateManager.disableBlend();
      }
   }
}
