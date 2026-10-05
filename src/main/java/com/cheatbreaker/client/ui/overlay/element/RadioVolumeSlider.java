package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.ui.overlay.element.RadioVolumeSlider$EnumSwitch;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.fading.AbstractFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;

public class RadioVolumeSlider extends AbstractElement {
   public AbstractFade recoveredField2433;
   public Number recoveredField2434;
   public Setting recoveredField2435;

   public Object method_06163(Object var1) {
      try {
         return var1;
      } catch (ClassCastException var3) {
         return null;
      }
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         if (Mouse.isButtonDown(0) && this.a_(var1, var2)) {
            this.recoveredField2433.method_20200();
            this.recoveredField2434 = (Number)this.recoveredField2435.getValue();
            float var5 = ((Number)this.recoveredField2435.method_08904()).floatValue();
            float var6 = ((Number)this.recoveredField2435.method_08878()).floatValue();
            if (var1 - this.x > this.width / 2.0F) {
               var1 += 2.0F;
            }

            float var7 = var5 + (var1 - this.x) * ((var6 - var5) / this.width);
            switch (RadioVolumeSlider$EnumSwitch.recoveredField3944[this.recoveredField2435.getType().ordinal()]) {
               case 1:
                  this.recoveredField2435.setValue(this.method_06163(Integer.parseInt((int)var7 + "")));
                  break;
               case 2:
                  this.recoveredField2435.setValue(this.method_06163(var7));
                  break;
               case 3:
                  this.recoveredField2435.setValue(this.method_06163(Double.parseDouble(var7 + "")));
            }
         }

         return super.handleElementMouseClicked(var1, var2, var3, var4);
      }
   }

   public RadioVolumeSlider(Setting var1) {
      this.recoveredField2435 = var1;
      this.recoveredField2433 = new MinMaxFade(300L);
      this.recoveredField2434 = (Number)var1.getValue();
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13158601);
      if (!this.recoveredField2433.method_21217()) {
         this.recoveredField2434 = (Number)this.recoveredField2435.getValue();
      }

      float var4 = ((Number)this.recoveredField2435.getValue()).floatValue();
      float var5 = ((Number)this.recoveredField2435.method_08904()).floatValue();
      float var6 = ((Number)this.recoveredField2435.method_08878()).floatValue();
      float var7 = var4 - this.recoveredField2434.floatValue();
      float var8 = 100.0F * ((this.recoveredField2434.floatValue() + var7 * this.recoveredField2433.method_21227() - var5) / (var6 - var5));
      Gui.drawRect(this.x, this.y, this.x + this.width / 100.0F * var8, this.y + this.height, -52429);
   }
}
