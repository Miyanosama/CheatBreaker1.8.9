package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.ui.element.type.NumericSliderElement$EnumSwitch;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class NumericSliderElement extends AbstractModulesGuiElement {
   static float valueForMouseX(float mouseX, float elementX, float scale, double width,
                              float minimum, float maximum, boolean snapIntegers) {
      double start = (elementX + 181.25F) * scale;
      double value = minimum + (mouseX - start) * (maximum - minimum) / (width * scale);
      if (snapIntegers && maximum > minimum) {
         double integer = Math.rint(value);
         double integerX = start + width * scale * (integer - minimum) / (maximum - minimum);
         if (integer >= minimum && integer <= maximum && Math.abs(mouseX - integerX) <= 3.0F * scale) {
            value = integer;
         }
      }
      return Math.max(minimum, Math.min(maximum, Math.round(value * 100.0) / 100.0F));
   }

   public float recoveredField1501 = -1.0F;
   public boolean recoveredField1502 = false;
   public float recoveredField1503;
   public Setting recoveredField1504;

   public NumericSliderElement(Setting var1, float var2) {
      super(var2);
      this.recoveredField1504 = var1;
      this.height = 14;
      this.recoveredField1501 = Float.parseFloat("" + var1.getValue());
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      short var6 = 148;
      CheatBreaker.getInstance().recoveredField1589.drawString(this.recoveredField1504.method_08911().toUpperCase(), this.x + 10, this.y + 2, -1895825408);
      if (this.recoveredField1502 && !Mouse.isButtonDown(0)) {
         this.recoveredField1502 = false;
      }

      String var7 = this.recoveredField1504.getValue().toString();
      CheatBreaker.getInstance()
         .recoveredField1589
         .drawString(var7, (float)(this.x + 169) - CheatBreaker.getInstance().recoveredField1589.getStringWidth(var7), this.y + 2, -1895825408);
      boolean var8 = var1 > (this.x + 172) * this.scale
         && var1 < (this.x + 172 + var6 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + 10 + this.yOffset) * this.scale;
      RenderUtil.method_22054(this.x + 174, this.y + 6, this.x + 170 + var6 - 4, this.y + 8, 1.0, var8 ? -1895825408 : 1862270976);
      double var9 = var6 - 18;
      float var11 = Float.parseFloat("" + this.recoveredField1504.method_08904());
      float var12 = Float.parseFloat("" + this.recoveredField1504.method_08878());
      if (this.recoveredField1502) {
         this.recoveredField1503 = valueForMouseX(var1, this.x, this.scale, var9, var11, var12,
            this.recoveredField1504.method_08911().contains("Scale"));
         if (this.recoveredField1504.getType().equals(Setting.Type.INTEGER) || Keyboard.isKeyDown(42)) {
            this.recoveredField1503 = Math.round(this.recoveredField1503);
         }

         if (this.recoveredField1503 < var11) {
            this.recoveredField1503 = var11;
         } else if (this.recoveredField1503 > var12) {
            this.recoveredField1503 = var12;
         }

         switch (NumericSliderElement$EnumSwitch.recoveredField332[this.recoveredField1504.getType().ordinal()]) {
            case 1:
               this.recoveredField1504.setValue(Integer.parseInt((int)this.recoveredField1503 + ""));
               break;
            case 2:
               this.recoveredField1504.setValue(this.recoveredField1503);
               break;
            case 3:
               this.recoveredField1504.setValue(Double.parseDouble(this.recoveredField1503 + ""));
         }

         CheatBreaker.getInstance().getModuleManager().keyStrokes.initialize();
         Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
      }

      float var5;
      float var16;
      var5 = (var5 = Float.parseFloat(this.recoveredField1504.getValue() + "")) < this.recoveredField1501
         ? this.recoveredField1501 - var5
         : (var16 = var5 - this.recoveredField1501);
      float var13 = ((var12 - var11) / 20.0F + var5 * 8.0F) / (Minecraft.debugFPS + 1);
      if (var13 < 1.0E-4) {
         var13 = 1.0E-4F;
      }

      float var4;
      if (this.recoveredField1501 < (var4 = Float.parseFloat(this.recoveredField1504.getValue() + ""))) {
         this.recoveredField1501 = Math.min(this.recoveredField1501 + var13, var4);
      } else if (this.recoveredField1501 > var4) {
         this.recoveredField1501 = Math.max(this.recoveredField1501 - var13, var4);
      }

      double var14 = 100.0F * ((this.recoveredField1501 - var11) / (var12 - var11));
      RenderUtil.method_22054(this.x + 174, this.y + 6, this.x + 180 + var9 * var14 / 100.0, this.y + 8, 4.0, -12418828);
      GL11.glColor4f(0.25F, 0.45F, 1.0F, 1.0F);
      RenderUtil.method_22052(this.x + 181.25F + var9 * var14 / 100.0, this.y + 7.25F, 4.5);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22052(this.x + 181.25F + var9 * var14 / 100.0, this.y + 7.25F, 2.7F);
      this.method_23220(this.recoveredField1504, var1, var2);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + 170 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + 10 + this.yOffset) * this.scale;
      if (var3 == 0 && var4) {
         this.recoveredField1502 = true;
      }
   }
}
