package com.cheatbreaker.client.ui.element;

import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.util.RenderUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class AbstractScrollableElement extends AbstractModulesGuiElement {
   public boolean recoveredField3008;
   public int recoveredField3009;
   public double recoveredField3010 = 0.0;
   public int recoveredField3011;
   public int recoveredField3012 = 0;
   public float recoveredField3013;
   public boolean recoveredField3014;
   public boolean recoveredField3015;
   public int recoveredField3016;

   @Override
   public void onScroll(int var1) {
      if (var1 != 0 && this.recoveredField3009 >= this.height) {
         this.recoveredField3010 += var1 / 2;
      }
   }

   public abstract boolean method_03198(AbstractModule var1);

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
   }

   public void postDraw(int var1, int var2) {
      this.recoveredField3015 = true;
      GL11.glPopMatrix();
      boolean var3 = this.recoveredField3009 > this.height;
      if (this.recoveredField3014 && !Mouse.isButtonDown(0)) {
         this.recoveredField3014 = false;
      }

      double var4 = this.height - 10;
      double var6 = this.recoveredField3009;
      double var8 = var4 / var6 * 100.0;
      double var10 = var4 / 100.0 * var8;
      double var12 = this.recoveredField3012 / 100.0 * var8;
      if (var3) {
         int var14 = this.height;
         boolean var15 = var1 > (this.x + this.width - 9) * this.scale
            && var1 < (this.x + this.width - 3) * this.scale
            && var2 > (this.y + 11 - var12) * this.scale
            && var2 < (this.y + 8 + var10 - var12) * this.scale;
         boolean var16 = var1 > (this.x + this.width - 9) * this.scale
            && var1 < (this.x + this.width - 3) * this.scale
            && var2 > (this.y + 11) * this.scale
            && var2 < (this.y + 6 + var4 - 3.0) * this.scale;
         if (this.recoveredField3014) {
            if (this.recoveredField3012 != this.recoveredField3013
               && this.recoveredField3013 != var10 / 2.0
               && this.recoveredField3013 != var10 / 2.0 + -this.recoveredField3009 + var14) {
               if (var2 > (this.y + 11 + var10 - var10 / 4.0 - var12) * this.scale) {
                  this.recoveredField3012 = (int)(this.recoveredField3012 - var6 / 70.0);
               } else if (var2 < (this.y + 11 + var10 / 4.0 - var12) * this.scale) {
                  this.recoveredField3012 = (int)(this.recoveredField3012 + var6 / 70.0);
               }

               this.recoveredField3013 = this.recoveredField3012;
            } else if (var2 > (this.y + 11 + var10 - var10 / 4.0 - var12) * this.scale || var2 < (this.y + 11 + var10 / 4.0 - var12) * this.scale) {
               this.recoveredField3013 = 1.0F;
            }
         }

         if (this.recoveredField3012 < -this.recoveredField3009 + var14) {
            this.recoveredField3012 = -this.recoveredField3009 + var14;
            this.recoveredField3010 = 0.0;
         }

         if (this.recoveredField3012 > 0) {
            this.recoveredField3012 = 0;
            this.recoveredField3010 = 0.0;
         }

         RenderUtil.method_22054(
            this.x + this.width - 6, this.y + 11, this.x + this.width - 4, this.y + 6 + var4 - 3.0, 2.0, var16 && !var15 ? 1862270976 : 1056964608
         );
         RenderUtil.method_22054(
            this.x + this.width - 7,
            this.y + 11 - var12,
            this.x + this.width - 3,
            this.y + 8 + var10 - var12,
            4.0,
            !var15 && !this.recoveredField3014 ? -12418828 : -16629505
         );
      }

      if (!var3 && this.recoveredField3012 != 0) {
         this.recoveredField3012 = 0;
      }
   }

   public abstract void method_03200(AbstractModule var1);

   public void preDraw(int var1, int var2) {
      if (this.isMouseInside(var1, var2)) {
         double var3 = Math.round(this.recoveredField3010 / 25.0);
         this.recoveredField3010 -= var3;
         if (this.recoveredField3010 != 0.0) {
            this.recoveredField3012 = (int)(this.recoveredField3012 + var3);
         }
      } else {
         this.recoveredField3010 = 0.0;
      }

      if (this.recoveredField3015) {
         if (this.recoveredField3012 < -this.recoveredField3009 + this.height) {
            this.recoveredField3012 = -this.recoveredField3009 + this.height;
            this.recoveredField3010 = 0.0;
         }

         if (this.recoveredField3012 > 0) {
            this.recoveredField3012 = 0;
            this.recoveredField3010 = 0.0;
         }
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, this.recoveredField3012, 0.0F);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      double var4 = this.height - 10;
      double var6 = this.recoveredField3009;
      double var8 = var4 / var6 * 100.0;
      double var10 = var4 / 100.0 * var8;
      double var12 = this.recoveredField3012 / 100.0 * var8;
      boolean var14 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11 - var12) * this.scale
         && var2 < (this.y + 8 + var10 - var12) * this.scale;
      boolean var15 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11) * this.scale
         && var2 < (this.y + 6 + var4 - 3.0) * this.scale;
      if (var3 == 0 && var15 || var14) {
         this.recoveredField3014 = true;
      }
   }

   public AbstractScrollableElement(float var1, int var2, int var3, int var4, int var5) {
      super(var1);
      this.recoveredField3009 = 0;
      this.recoveredField3015 = false;
      this.recoveredField3014 = false;
      this.recoveredField3008 = false;
      this.recoveredField3011 = var2;
      this.recoveredField3016 = var3;
      this.x = var2;
      this.y = var3;
      this.width = var4;
      this.height = var5;
   }
}
