package com.cheatbreaker.client.ui.overlay;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.AbstractFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import javazoom.jl.decoder.huffcodetab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.model.ModelBakery;
import net.optifine.expr.FunctionFloat$1;

public class Alert {
   public String field_0005;
   public float field_0008;
   public long field_0004;
   public boolean field_0007;
   public AbstractFade fade = new FloatFade(36184403L & 828109257765311263L);
   public float field_0002;
   public huffcodetab field_0009;
   public ModelBakery field_0006;
   public String[] field_0003;
   public IMetadataSerializer field_0010;
   public FunctionFloat$1 field_0000;

   public float method_19902() {
      return this.field_0008;
   }

   public void setX(float var1) {
   }

   public static int method_19909() {
      return 55;
   }

   public boolean method_19900() {
      return System.currentTimeMillis() - this.field_0004 > (76189100L & 571485612L);
   }

   public static void method_19904(String var0) {
      OverlayGui.getInstance().setSection(var0);
   }

   public void method_19910(float var1) {
      this.field_0008 = this.field_0002;
      this.field_0002 = var1;
      this.fade.method_20200();
   }

   public Alert(String var1, String[] var2, float var3) {
      this.field_0005 = var1;
      this.field_0003 = var2;
      this.field_0002 = var3;
      this.field_0008 = var3;
      this.field_0004 = System.currentTimeMillis();
   }

   public static int method_19907() {
      return 140;
   }

   public void drawAlert() {
      float var1 = new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() - 140;
      float var2 = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight()
         + this.field_0008
         - (this.field_0008 - this.field_0002) * this.fade.method_21227();
      if (this.field_0007) {
         Minecraft.getMinecraft().ingameGUI.method_00889(var1, var2, var1 + 140.0F, var2 + 55.0F, -819057106, -822083584);

         for (int var3 = 0; var3 < this.field_0003.length && var3 <= 3; var3++) {
            String var10001 = this.field_0003[var3];
            CheatBreaker.getInstance().playRegular14px.drawString(var10001, var1 + 4.0F, var2 + 4.0F + var3 * 10, -1);
         }
      } else {
         Minecraft.getMinecraft().ingameGUI.method_00889(var1, var2, var1 + 140.0F, var2 + 55.0F, -819057106, -822083584);
         CheatBreaker.getInstance().playRegular14px.drawStringWithShadow(this.field_0005, var1 + 4.0F, var2 + 4.0F, -1);
         Gui.drawRect(var1 + 4.0F, var2 + 14.5F, var1 + 140.0F - 5.0F, var2 + 15.0F, 777936478);

         for (int var4 = 0; var4 < this.field_0003.length && var4 <= 2; var4++) {
            String var5 = this.field_0003[var4];
            CheatBreaker.getInstance().playRegular14px.drawString(var5, var1 + 4.0F, var2 + 17.0F + var4 * 10, -1);
         }
      }

      if (!(Minecraft.getMinecraft().currentScreen instanceof OverlayGui)) {
         CheatBreaker.getInstance().playRegular14px.drawString("Press Shift + Tab", var1 + 4.0F, var2 + method_19909() - 12.0F, 1879048191);
      }
   }

   public float method_19899() {
      return this.field_0002;
   }

   public boolean method_19911() {
      return !this.fade.method_21217() || this.fade.method_21210();
   }

   public static void displayMessage(String var0, String var1) {
      OverlayGui.getInstance().queueAlert(var0, var1);
   }

   public void method_19901(float var1) {
      this.field_0002 = var1;
   }

   public void method_19906(boolean var1) {
      this.field_0007 = var1;
   }

   public void method_19908(float var1) {
      this.field_0008 = var1;
   }
}
