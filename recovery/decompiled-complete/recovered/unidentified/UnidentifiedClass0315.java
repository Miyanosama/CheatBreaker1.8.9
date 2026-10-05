package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.particle.Barrier;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.log4j.HTMLLayout;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass0315 {
   public Color field_0006;
   public float field_0013;
   public Color field_0005;
   public long field_0011;
   public Color field_0001;
   public Barrier field_0002;
   public float field_0014;
   public Color field_0009;
   public int field_0003;
   public Color field_0015;
   public String field_0000;
   public Color field_0007;
   public Color field_0008;
   public HTMLLayout field_0004;
   public boolean field_0010;
   public Color field_0012;

   public UnidentifiedClass0315(String var1, int var2, float var3, float var4) {
      this.field_0000 = var1;
      this.field_0003 = var2;
      this.field_0014 = var3;
      this.field_0013 = var4;
   }

   public int method_02422() {
      return this.field_0003;
   }

   public String method_02425() {
      return this.field_0000;
   }

   public float method_02421() {
      return this.field_0013;
   }

   public float method_02424() {
      return this.field_0014;
   }

   public void method_02423(float var1, float var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
      Minecraft var10 = Minecraft.getMinecraft();
      int var11 = this.field_0003 != -99 && this.field_0003 != -100 ? -1 : (this.field_0003 == -99 ? 1 : 0);
      boolean var12 = (var10.currentScreen == null || var10.currentScreen instanceof GuiContainer || var10.currentScreen instanceof CBModulesGui)
         && (var11 != -1 ? Mouse.isButtonDown(var11) : Keyboard.isKeyDown(this.field_0003));
      if (var12 && !this.field_0010) {
         this.field_0010 = true;
         this.field_0011 = System.currentTimeMillis();
         this.field_0001 = new Color(var5, true);
         this.field_0015 = new Color(var6, true);
         this.field_0006 = new Color(var3, true);
         this.field_0005 = new Color(var4, true);
         this.field_0007 = new Color(CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0022.method_08901(), true);
         this.field_0009 = new Color(CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0038.method_08901(), true);
         this.field_0008 = new Color(var7, true);
         this.field_0012 = new Color(var8, true);
      } else if (this.field_0010 && !var12) {
         this.field_0010 = false;
         this.field_0011 = System.currentTimeMillis();
         this.field_0001 = new Color(var6, true);
         this.field_0015 = new Color(var5, true);
         this.field_0006 = new Color(var4, true);
         this.field_0005 = new Color(var3, true);
         this.field_0007 = new Color(CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0038.method_08901(), true);
         this.field_0009 = new Color(CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0022.method_08901(), true);
         this.field_0008 = new Color(var8, true);
         this.field_0012 = new Color(var7, true);
      }

      float var13 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0015.getValue();
      int var14;
      if ((float)(System.currentTimeMillis() - this.field_0011) < var13) {
         float var15 = (float)(System.currentTimeMillis() - this.field_0011) / var13;
         var14 = new Color(
               (int)Math.abs(var15 * this.field_0015.getRed() + (1.0F - var15) * this.field_0001.getRed()),
               (int)Math.abs(var15 * this.field_0015.getGreen() + (1.0F - var15) * this.field_0001.getGreen()),
               (int)Math.abs(var15 * this.field_0015.getBlue() + (1.0F - var15) * this.field_0001.getBlue()),
               (int)Math.abs(var15 * this.field_0015.getAlpha() + (1.0F - var15) * this.field_0001.getAlpha())
            )
            .getRGB();
      } else {
         var14 = var12 ? var6 : var5;
      }

      float var30 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0005.getValue();
      int var16;
      int var17;
      int var18;
      if ((float)(System.currentTimeMillis() - this.field_0011) < var30) {
         float var19 = (float)(System.currentTimeMillis() - this.field_0011);
         float var20 = var19 / var30;
         int var21 = (int)Math.abs(var20 * this.field_0005.getRed() + (1.0F - var20) * this.field_0006.getRed());
         int var22 = (int)Math.abs(var20 * this.field_0005.getGreen() + (1.0F - var20) * this.field_0006.getGreen());
         int var23 = (int)Math.abs(var20 * this.field_0005.getBlue() + (1.0F - var20) * this.field_0006.getBlue());
         int var24 = (int)Math.abs(var20 * this.field_0005.getAlpha() + (1.0F - var20) * this.field_0006.getAlpha());
         int var25 = (int)Math.abs(var20 * this.field_0009.getRed() + (1.0F - var20) * this.field_0007.getRed());
         int var26 = (int)Math.abs(var20 * this.field_0009.getGreen() + (1.0F - var20) * this.field_0007.getGreen());
         int var27 = (int)Math.abs(var20 * this.field_0009.getBlue() + (1.0F - var20) * this.field_0007.getBlue());
         int var28 = (int)Math.abs(var20 * this.field_0009.getAlpha() + (1.0F - var20) * this.field_0007.getAlpha());
         var16 = new Color(var21, var22, var23, var24).getRGB();
         var17 = new Color(var25, var26, var27, var28).getRGB();
         var18 = new Color(var21 / 4, var22 / 4, var23 / 4, var24).getRGB();
      } else {
         var16 = var12 ? var4 : var3;
         var17 = var12
            ? CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0038.method_08901()
            : CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0022.method_08901();
         int var31 = var4 >> 16 & 0xFF;
         int var33 = var4 >> 8 & 0xFF;
         int var35 = var4 & 0xFF;
         int var40 = var3 >> 16 & 0xFF;
         int var43 = var3 >> 8 & 0xFF;
         int var45 = var3 & 0xFF;
         var18 = var12 ? new Color(var31 / 4, var33 / 4, var35 / 4).getRGB() : new Color(var40 / 4, var43 / 4, var45 / 4).getRGB();
      }

      float var32 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0027.getValue();
      int var34;
      if ((float)(System.currentTimeMillis() - this.field_0011) < var32) {
         float var36 = (float)(System.currentTimeMillis() - this.field_0011) / var32;
         var34 = new Color(
               (int)Math.abs(var36 * this.field_0012.getRed() + (1.0F - var36) * this.field_0008.getRed()),
               (int)Math.abs(var36 * this.field_0012.getGreen() + (1.0F - var36) * this.field_0008.getGreen()),
               (int)Math.abs(var36 * this.field_0012.getBlue() + (1.0F - var36) * this.field_0008.getBlue()),
               (int)Math.abs(var36 * this.field_0012.getAlpha() + (1.0F - var36) * this.field_0008.getAlpha())
            )
            .getRGB();
      } else {
         var34 = var12 ? var8 : var7;
      }

      if ((Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0011.getValue()) {
         Gui.drawRect(var1, var2, var1 + this.field_0014, var2 + this.field_0013, var14);
      }

      if ((Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0023.getValue()) {
         float var37 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0000.getValue();
         Gui.method_00886(var1 - var37, var2 - var37, var1 + this.field_0014 + var37, var2 + this.field_0013 + var37, var37, var34);
      }

      if (this.field_0003 == var10.gameSettings.keyBindJump.getKeyCode()
         && (Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0037.getValue()) {
         float var39 = CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0019.getValue()
            ? this.field_0013 / 2.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0045.getValue() / 2.0F
            : 3.0F;
         if (var9) {
            Gui.drawRect(
               var1
                  + this.field_0014 / 2.0F
                  - this.field_0014 / (12.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0050.getValue())
                  + 1.0F,
               var2 + var39 + 1.0F,
               var1
                  + this.field_0014 / 2.0F
                  + this.field_0014 / (12.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0050.getValue())
                  + 1.0F,
               var2 + var39 + 1.0F + (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0045.getValue(),
               var18
            );
         }

         Gui.drawRect(
            var1 + this.field_0014 / 2.0F - this.field_0014 / (12.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0050.getValue()),
            var2 + var39,
            var1 + this.field_0014 / 2.0F + this.field_0014 / (12.0F - (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0050.getValue()),
            var2 + var39 + (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0045.getValue(),
            var16
         );
      } else {
         GlStateManager.enableBlend();
         float var38 = var1 + this.field_0014 / 2.0F - var10.fontRendererObj.getStringWidth(this.field_0000) / 2 + 0.26F;
         float var41 = var2 + this.field_0013 / 2.0F - var10.fontRendererObj.FONT_HEIGHT / 2 + 0.65F;
         if ((Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0018.getValue()) {
            var38 = var1 + this.field_0014 / 2.0F - var10.fontRendererObj.getStringWidth(this.field_0000) / 2;
            var41 = var2 + this.field_0013 / 2.0F - var10.fontRendererObj.FONT_HEIGHT / 2 + 1.0F;
         }

         float var44 = (Float)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0043.getValue();
         boolean var46 = this.field_0003 == var10.gameSettings.field_0073.getKeyCode();
         boolean var47 = this.field_0003 == var10.gameSettings.field_0071.getKeyCode();
         String var48 = var46
            ? (String)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0041.getValue()
            : (var47 ? (String)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0002.getValue() : "");
         if (var46 || var47) {
            if (var44 > 14.0F && var48.equals("With Clicks")) {
               GL11.glPushMatrix();
               float var50 = 0.6666F;
               GL11.glScalef(var50, var50, 0.0F);
               var41 = var2 + this.field_0013 / 2.0F - var10.fontRendererObj.FONT_HEIGHT / 2 + 6.0F;
               String var52 = CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0003.size() + " CPS";
               if (this.field_0000.equals("RMB")) {
                  var52 = CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0042.size() + " CPS";
               }

               float var53 = var1 + this.field_0014 / 2.0F - var10.fontRendererObj.getStringWidth(var52) / 2.0F * var50;
               var10.fontRendererObj.drawString(var52, var53 / var50, var41 / var50, var17, var9);
               GL11.glScalef(1.0F, 1.0F, 0.0F);
               GL11.glPopMatrix();
               var41 = var2 + this.field_0013 / 2.0F - var10.fontRendererObj.FONT_HEIGHT / 2 - 3.0F;
            } else if (var48.equals("Replace Clicks")) {
               GL11.glPushMatrix();
               float var49 = 1.0F;
               GL11.glScalef(var49, var49, 0.0F);
               String var51 = this.field_0000;
               String var29 = var44 < 15.0F ? "" : " CPS";
               if (var46 && CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0003.size() > 0) {
                  var51 = CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0003.size() + var29;
                  if (var44 > 15.0F) {
                     var49 = 0.6666F;
                     GL11.glScalef(var49, var49, 0.0F);
                  }
               }

               if (var47 && CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0042.size() > 0) {
                  var51 = CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0042.size() + var29;
                  if (var44 > 15.0F) {
                     var49 = 0.6666F;
                     GL11.glScalef(var49, var49, 0.0F);
                  }
               }

               var38 = var1 + this.field_0014 / 2.0F - var10.fontRendererObj.getStringWidth(var51) / 2.0F * var49 + 0.26F;
               var41 = var2
                  + this.field_0013 / 2.0F
                  - var10.fontRendererObj.FONT_HEIGHT / 2
                  + (var44 > 15.0F && !var51.equals(this.field_0000) ? 1.65F : 0.65F);
               if ((Boolean)CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0018.getValue()) {
                  var38 = var1 + this.field_0014 / 2.0F - var10.fontRendererObj.getStringWidth(var51) / 2.0F * var49;
                  var41 = var2
                     + this.field_0013 / 2.0F
                     - var10.fontRendererObj.FONT_HEIGHT / 2
                     + (var44 > 15.0F && !var51.equals(this.field_0000) ? 2.0F : 1.0F);
               }

               var10.fontRendererObj.drawString(var51, var38 / var49, var41 / var49, var16, var9);
               GL11.glScalef(1.0F, 1.0F, 0.0F);
               GL11.glPopMatrix();
            }
         }

         if (!var48.equals("Replace Clicks")) {
            var10.fontRendererObj.drawString(this.field_0000, var38, var41, var16, var9);
         }

         GlStateManager.disableBlend();
      }
   }
}
