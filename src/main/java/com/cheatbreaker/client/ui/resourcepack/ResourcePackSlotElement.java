package com.cheatbreaker.client.ui.resourcepack;

import com.cheatbreaker.client.util.render.LegacyGlStateManager;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class ResourcePackSlotElement {
   public int recoveredField1780;
   public int recoveredField1781;
   public int recoveredField1782;
   public double[] recoveredField1783;
   public float recoveredField1784;
   public float recoveredField1785;
   public boolean recoveredField1786;
   public int recoveredField1787;
   public int recoveredField1788;
   public long recoveredField1789;
   public int recoveredField1790;
   public Minecraft recoveredField1791;
   public int recoveredField1792;
   public float recoveredField1793;
   public int recoveredField1794;
   public int recoveredField1795;
   public String recoveredField1796;
   public int recoveredField1797;

   public void method_00750() {
      int var1;
      if (this.recoveredField1786 && (var1 = Mouse.getEventDWheel()) != 0) {
         var1 = var1 > 0 ? -1 : 1;
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField487.getValue()) {
            this.recoveredField1794 = var1;
            this.recoveredField1788 = 0;
         } else {
            this.recoveredField1793 = this.recoveredField1793 + var1 * this.recoveredField1782 / 2;
         }
      }
   }

   public ResourcePackSlotElement(Minecraft var1, int var2, int var3, int var4, int var5, int var6, String var7) {
      this.recoveredField1791 = var1;
      this.recoveredField1790 = var4;
      this.recoveredField1795 = var5;
      this.recoveredField1780 = var2;
      this.recoveredField1787 = var3;
      this.recoveredField1792 = var2 + var4;
      this.recoveredField1797 = var3 + var5;
      this.recoveredField1782 = var6;
      this.recoveredField1796 = var7;
      this.recoveredField1781 = -1;
      this.recoveredField1788 = -1;
      this.recoveredField1784 = -2.0F;
      this.recoveredField1783 = new double[]{
         5.088448,
         4.809692672,
         3.4292885120000003,
         3.268147903999999,
         2.697228288,
         2.019487744,
         1.8882322560000002,
         1.6936698879999996,
         1.4352491520000008,
         1.2045501440000006,
         0.7097322879999997,
         0.5842770560000003,
         0.5043583360000001,
         0.37950342400000014,
         0.282300416,
         0.21170873600000029,
         0.09733600000000031,
         0.08991539199999998,
         0.06209913599999961,
         0.030371328000000197,
         0.01452678400000007,
         0.006229504000000219,
         0.0017279999999999518
      };
   }

   public abstract int method_00746();

   public abstract void method_00749(int var1, boolean var2);

   public abstract void method_00751();

   public abstract void method_00748(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7);

   public void method_00744() {
      int var1 = this.method_00745() - (this.recoveredField1797 - this.recoveredField1787 - 4);
      if (var1 < 0) {
         var1 /= 2;
      }

      if (this.recoveredField1793 > var1) {
         this.recoveredField1793 = var1;
      }

      if (this.recoveredField1793 < 0.0F) {
         this.recoveredField1793 = 0.0F;
      }
   }

   public void method_00747(int var1, int var2) {
      this.method_00751();
      this.recoveredField1786 = this.recoveredField1780 <= var1
         && var1 <= this.recoveredField1780 + this.recoveredField1790
         && this.recoveredField1787 <= var2
         && var2 <= this.recoveredField1797;
      int var4 = this.method_00746();
      byte var5 = 6;
      int var6 = this.recoveredField1780 + this.recoveredField1790;
      int var7 = var6 - 6;
      int var8 = var7 - 1;
      int var9 = this.recoveredField1797 - this.recoveredField1787;
      byte var10 = 4;
      if (Mouse.isButtonDown(0)) {
         if (this.recoveredField1784 == -1.0F) {
            if (this.recoveredField1786) {
               int var11 = var2 - this.recoveredField1787 + (int)this.recoveredField1793 - 4;
               int var12 = var11 / this.recoveredField1782;
               if (0 <= var12 && var12 < var4 && var1 <= var8 && 0 <= var11) {
                  this.method_00749(var12, var12 == this.recoveredField1781 && System.currentTimeMillis() - this.recoveredField1789 < 500L);
                  this.recoveredField1781 = this.recoveredField1781 == var12 ? -1 : var12;
                  this.recoveredField1789 = System.currentTimeMillis();
               }

               if (var7 <= var1 && var1 <= var6) {
                  this.recoveredField1785 = -1.0F;
                  int var13 = this.method_00745() - var9 - 4;
                  if (var13 < 1) {
                     var13 = 1;
                  }

                  int var3;
                  if ((var3 = (int)((float)(var9 * var9) / this.method_00745())) < 32) {
                     var3 = 32;
                  }

                  if (var3 > var9 - 8) {
                     var3 = var9 - 8;
                  }

                  this.recoveredField1785 /= (float)(var9 - var3) / var13;
               } else {
                  this.recoveredField1785 = 1.0F;
               }

               this.recoveredField1784 = var2;
            } else {
               this.recoveredField1784 = -2.0F;
            }
         } else if (this.recoveredField1784 >= 0.0F) {
            this.recoveredField1793 = this.recoveredField1793 - (var2 - this.recoveredField1784) * this.recoveredField1785;
            this.recoveredField1784 = var2;
         }
      } else {
         if (this.recoveredField1788 != -1) {
            this.recoveredField1793 = (float)(this.recoveredField1793 + this.recoveredField1783[this.recoveredField1788] * this.recoveredField1794 * 2.0);
            this.recoveredField1788++;
            if (this.recoveredField1788 >= this.recoveredField1783.length) {
               this.recoveredField1788 = -1;
            }
         }

         this.recoveredField1784 = -1.0F;
      }

      this.method_00744();
      int var18 = this.recoveredField1787 + 2 - (int)this.recoveredField1793;

      for (int var19 = 0; var19 < var4; var19++) {
         int var21 = var18 + var19 * this.recoveredField1782;
         int var14 = this.recoveredField1782 - 4;
         int var15 = var21 + var14 + 2;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         LegacyGlStateManager.method_28200(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(3042);
         GL11.glPushMatrix();
         GL11.glEnable(3089);
         ScaledResolution var16 = new ScaledResolution(this.recoveredField1791);
         RenderUtil.method_22062(this.recoveredField1780, this.recoveredField1787 + 1, this.recoveredField1792, this.recoveredField1797, var16);
         this.method_00748(var19, var8, var21, var14, var1, var2, this.recoveredField1780 <= var1 && var1 < var8 && var21 - 2 <= var2 && var2 < var15);
         GL11.glDisable(3089);
         GL11.glPopMatrix();
      }

      int var20 = this.method_00745() - var9 - 4;
      if (var20 > 0) {
         int var22 = var9 * var9 / this.method_00745();
         if (var22 < 32) {
            var22 = 32;
         }

         if (var22 > var9 - 8) {
            var22 = var9 - 8;
         }

         int var17;
         if ((var17 = (int)this.recoveredField1793 * (var9 - var22) / var20 + this.recoveredField1787) < this.recoveredField1787) {
            var17 = this.recoveredField1787;
         }

         Gui.a(var7, var17, var6, var17 + var22, -4144960);
      }

      LegacyGlStateManager.method_28201(7424);
      GL11.glEnable(3553);
      GL11.glEnable(3008);
      Gui.recoveredField2942 = 1.0F;
      this.recoveredField1791.ingameGUI.method_00889(this.recoveredField1780, this.recoveredField1787, var6, this.recoveredField1787 + 5, -16777216, 0);
      this.recoveredField1791.ingameGUI.method_00889(this.recoveredField1780, this.recoveredField1797 - 5, var6, this.recoveredField1797, 0, -16777216);
      Gui.recoveredField2942 = -90.0F;
   }

   public int method_00745() {
      return this.method_00746() * this.recoveredField1782;
   }
}
