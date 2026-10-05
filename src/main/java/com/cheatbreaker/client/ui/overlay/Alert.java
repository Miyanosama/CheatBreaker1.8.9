package com.cheatbreaker.client.ui.overlay;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.AbstractFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class Alert {
   public String recoveredField1990;
   public float recoveredField1991;
   public long recoveredField1992;
   public boolean recoveredField1993;
   public AbstractFade fade = new FloatFade(275L);
   public float recoveredField1994;
   public String[] recoveredField1995;

   public float method_19902() {
      return this.recoveredField1991;
   }

   public void setX(float var1) {
   }

   public static int method_19909() {
      return 55;
   }

   public boolean method_19900() {
      return System.currentTimeMillis() - this.recoveredField1992 > 3500L;
   }

   public static void method_19904(String var0) {
      OverlayGui.getInstance().setSection(var0);
   }

   public void method_19910(float var1) {
      this.recoveredField1991 = this.recoveredField1994;
      this.recoveredField1994 = var1;
      this.fade.method_20200();
   }

   public Alert(String var1, String[] var2, float var3) {
      this.recoveredField1990 = var1;
      this.recoveredField1995 = var2;
      this.recoveredField1994 = var3;
      this.recoveredField1991 = var3;
      this.recoveredField1992 = System.currentTimeMillis();
   }

   public static int method_19907() {
      return 140;
   }

   public void drawAlert() {
      float var1 = new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() - 140;
      float var2 = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight()
         + this.recoveredField1991
         - (this.recoveredField1991 - this.recoveredField1994) * this.fade.method_21227();
      if (this.recoveredField1993) {
         Minecraft.getMinecraft().ingameGUI.method_00889(var1, var2, var1 + 140.0F, var2 + 55.0F, -819057106, -822083584);

         for (int var3 = 0; var3 < this.recoveredField1995.length && var3 <= 3; var3++) {
            String var10001 = this.recoveredField1995[var3];
            CheatBreaker.getInstance().playRegular14px.drawString(var10001, var1 + 4.0F, var2 + 4.0F + var3 * 10, -1);
         }
      } else {
         Minecraft.getMinecraft().ingameGUI.method_00889(var1, var2, var1 + 140.0F, var2 + 55.0F, -819057106, -822083584);
         CheatBreaker.getInstance().playRegular14px.drawStringWithShadow(this.recoveredField1990, var1 + 4.0F, var2 + 4.0F, -1);
         Gui.drawRect(var1 + 4.0F, var2 + 14.5F, var1 + 140.0F - 5.0F, var2 + 15.0F, 777936478);

         for (int var4 = 0; var4 < this.recoveredField1995.length && var4 <= 2; var4++) {
            String var5 = this.recoveredField1995[var4];
            CheatBreaker.getInstance().playRegular14px.drawString(var5, var1 + 4.0F, var2 + 17.0F + var4 * 10, -1);
         }
      }

      if (!(Minecraft.getMinecraft().currentScreen instanceof OverlayGui)) {
         CheatBreaker.getInstance().playRegular14px.drawString("Press Shift + Tab", var1 + 4.0F, var2 + method_19909() - 12.0F, 1879048191);
      }
   }

   public float method_19899() {
      return this.recoveredField1994;
   }

   public boolean method_19911() {
      return !this.fade.method_21217() || this.fade.method_21210();
   }

   public static void displayMessage(String var0, String var1) {
      OverlayGui.getInstance().queueAlert(var0, var1);
   }

   public void method_19901(float var1) {
      this.recoveredField1994 = var1;
   }

   public void method_19906(boolean var1) {
      this.recoveredField1993 = var1;
   }

   public void method_19908(float var1) {
      this.recoveredField1991 = var1;
   }
}
