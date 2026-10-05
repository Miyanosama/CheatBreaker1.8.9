package com.cheatbreaker.client.ui.mainmenu.element;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class ScrollableElement extends AbstractElement {
   public float recoveredField809;
   public boolean dragClick;
   public float scrollAmount;
   public boolean hovered;
   public double internalScrollAmount;
   public float translateY;
   public AbstractElement parent;
   public boolean alwaysTrueApparently;
   public float recoveredField810;

   public void drawScrollable(float var1, float var2, boolean var3) {
      if (var3 && (this.parent == null || this.parent.a_(var1, var2)) && this.internalScrollAmount != 0.0) {
         this.translateY = (float)(this.translateY + this.internalScrollAmount / 8.0);
         this.internalScrollAmount = 0.0;
      }

      if (this.alwaysTrueApparently) {
         if (this.translateY < -this.scrollAmount + this.height) {
            this.translateY = -this.scrollAmount + this.height;
            this.internalScrollAmount = 0.0;
         }

         if (this.translateY > 0.0F) {
            this.translateY = 0.0F;
            this.internalScrollAmount = 0.0;
         }
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, this.translateY, 0.0F);
   }

   public AbstractElement getParent() {
      return this.parent;
   }

   public void method_12071(float var1, float var2, boolean var3) {
      this.alwaysTrueApparently = true;
      GL11.glPopMatrix();
      boolean var4 = this.method_12068();
      if (this.hovered && (!Mouse.isButtonDown(0) || !this.a_(var1, var2) || !var3)) {
         this.hovered = false;
      }

      if (this.dragClick && !Mouse.isButtonDown(0)) {
         this.dragClick = false;
      }

      float var5 = this.height;
      float var6 = this.scrollAmount;
      float var7 = var5 / var6 * 100.0F;
      float var8 = var5 / 100.0F * var7;
      float var9 = this.translateY / 100.0F * var7;
      if (Mouse.isButtonDown(0) && this.dragClick) {
         float var10 = var2 - this.y;
         float var11 = var10 / this.height;
         this.translateY = -(this.scrollAmount - this.height / 2.0F) + this.scrollAmount * var11 + var8 / 2.0F;
      }

      if (var4) {
         float var13 = this.height;
         boolean var14 = var1 >= this.x && var1 <= this.x + this.width && var2 > this.y - var9 && var2 < this.y + var8 - var9;
         boolean var12 = var1 >= this.x && var1 <= this.x + this.width && var2 > this.y && var2 < this.y + var5 - 3.0F;
         if (Mouse.isButtonDown(0) && !this.hovered && var12) {
         }

         if (this.hovered) {
            if (this.translateY != this.recoveredField809
               && this.recoveredField809 != var8 / 2.0F
               && this.recoveredField809 != var8 / 2.0F + -this.scrollAmount + var13) {
               if (var2 > this.y + this.height - var8 - var8 / 4.0F + var9) {
                  this.translateY = var6 / 7.0F;
               } else if (var2 < this.y + this.height - var8 / 4.0F + var9) {
                  this.translateY += var6 / 7.0F;
               }

               this.recoveredField809 = this.translateY;
            } else if (var2 > this.y + this.height - var8 - var8 / 4.0F + var9 || var2 < this.y + this.height - var8 / 4.0F - var9) {
               this.recoveredField809 = 1.0F;
            }
         }

         if (this.translateY < -this.scrollAmount + var13) {
            this.translateY = -this.scrollAmount + var13;
            this.internalScrollAmount = 0.0;
         }

         if (this.translateY > 0.0F) {
            this.translateY = 0.0F;
            this.internalScrollAmount = 0.0;
         }

         Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13158601);
         Gui.drawRect(this.x, this.y + this.height + var9, this.x + this.width, this.y + this.height - var8 + var9, !var14 && !this.hovered ? -4180940 : -52429);
      }

      if (!var4 && this.translateY != 0.0F) {
         this.translateY = 0.0F;
      }
   }

   public boolean method_12068() {
      return this.scrollAmount > this.height;
   }

   public boolean isDragClick() {
      return this.dragClick;
   }

   @Override
   public void handleElementMouse() {
      int var1 = Mouse.getEventDWheel();
      if (var1 != 0 && this.scrollAmount >= this.height) {
         this.internalScrollAmount += var1 / 1.75F;
      }
   }

   public boolean isHovered() {
      return this.hovered;
   }

   public void handleScrollableMouseClicked(float var1, float var2, boolean var3) {
      if (var3 && (this.parent == null || this.parent.a_(var1, var2)) && this.internalScrollAmount != 0.0) {
         this.translateY = (float)(this.translateY - this.internalScrollAmount / 8.0);
         this.internalScrollAmount = 0.0;
      }

      if (this.alwaysTrueApparently) {
         if (this.translateY < -this.scrollAmount + this.height) {
            this.translateY = -this.scrollAmount + this.height;
            this.internalScrollAmount = 0.0;
         }

         if (this.translateY > 0.0F) {
            this.translateY = 0.0F;
            this.internalScrollAmount = 0.0;
         }
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, -this.translateY, 0.0F);
   }

   public float method_12074() {
      return this.translateY;
   }

   public void setScrollAmount(float var1) {
      this.scrollAmount = var1;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      this.alwaysTrueApparently = true;
      GL11.glPopMatrix();
      boolean var4 = this.method_12068();
      if (this.hovered && (!Mouse.isButtonDown(0) || !this.a_(var1, var2) || !var3)) {
         this.hovered = false;
      }

      if (this.dragClick && !Mouse.isButtonDown(0)) {
         this.dragClick = false;
      }

      float var5 = this.height;
      float var6 = this.scrollAmount;
      float var7 = var5 / var6 * 100.0F;
      float var8 = var5 / 100.0F * var7;
      float var9 = this.translateY / 100.0F * var7;
      if (Mouse.isButtonDown(0) && this.dragClick) {
         float var10 = var2 - this.y;
         float var11 = var10 / this.height;
         this.translateY = -(this.scrollAmount * var11) + var8 / 2.0F;
      }

      if (var4) {
         float var13 = this.height;
         boolean var14 = var1 >= this.x && var1 <= this.x + this.width && var2 > this.y - var9 && var2 < this.y + var8 - var9;
         boolean var12 = var1 >= this.x && var1 <= this.x + this.width && var2 > this.y && var2 < this.y + var5 - 3.0F;
         if (Mouse.isButtonDown(0) && !this.hovered && var12) {
         }

         if (this.hovered) {
            if (this.translateY != this.recoveredField809
               && this.recoveredField809 != var8 / 2.0F
               && this.recoveredField809 != var8 / 2.0F + -this.scrollAmount + var13) {
               if (var2 > this.y + var8 - var8 / 4.0F - var9) {
                  this.translateY -= var6 / 7.0F;
               } else if (var2 < this.y + var8 / 4.0F - var9) {
                  this.translateY += var6 / 7.0F;
               }

               this.recoveredField809 = this.translateY;
            } else if (var2 > this.y + var8 - var8 / 4.0F - var9 || var2 < this.y + var8 / 4.0F - var9) {
               this.recoveredField809 = 1.0F;
            }
         }

         if (this.translateY < -this.scrollAmount + var13) {
            this.translateY = -this.scrollAmount + var13;
            this.internalScrollAmount = 0.0;
         }

         if (this.translateY > 0.0F) {
            this.translateY = 0.0F;
            this.internalScrollAmount = 0.0;
         }

         Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13158601);
         Gui.drawRect(this.x, this.y - var9, this.x + this.width, this.y + var8 - var9, !var14 && !this.hovered ? -4180940 : -52429);
      }

      if (!var4 && this.translateY != 0.0F) {
         this.translateY = 0.0F;
      }
   }

   @Override
   public boolean a_(float var1, float var2) {
      return var1 >= this.x && var1 <= this.x + this.width && var2 > this.y && var2 < this.y + this.height;
   }

   public ScrollableElement(AbstractElement var1) {
      this.parent = var1;
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (this.a_(var1, var2) && var4) {
         this.recoveredField810 = var2 - this.y;
         this.dragClick = true;
      }

      return false;
   }
}
