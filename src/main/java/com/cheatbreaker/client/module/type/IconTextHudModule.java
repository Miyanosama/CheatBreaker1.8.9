package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.module.HudSizeDefaults;

public abstract class IconTextHudModule extends TextHudModule {
   public ResourceLocation recoveredField2001 = null;
   public Setting recoveredField2002;

   @Override
   public void method_04331(String var1) {
      boolean var2 = this.recoveredField2002.method_08908();
      String var3 = this.method_00166();
      if (var3 == null || var3.isEmpty()) {
         var3 = "";
      }

      String var4;
      if (this.method_21170() != null && this.method_00167() != null) {
         var4 = this.method_21170();
      } else if (!var3.equals("")) {
         if (!var2 && !(Boolean)this.recoveredField2245.getValue()) {
            var4 = this.recoveredField2243.getValue().toString().replaceAll("%LABEL%", var3).replaceAll("%VALUE%", var1);
         } else {
            var4 = this.recoveredField2233.getValue().toString().replaceAll("%LABEL%", var3).replaceAll("%VALUE%", var1);
         }
      } else {
         var4 = var1;
      }

      this.recoveredField2001 = this.method_01868();
      float var5 = this.minecraft.fontRendererObj.getStringWidth(var4);
      float var6 = (Float)this.recoveredField2247.getValue();
      float var7 = var2 ? var6 : 0.0F;
      float var8 = !(Boolean)this.recoveredField2241.getValue() ? var5 + (Float)this.recoveredField2242.getValue() + var7 : (Float)this.recoveredField2231.getValue();
      if ((Boolean)this.recoveredField2238.getValue().equals("Extend Width") && (Boolean)this.recoveredField2241.getValue()) {
         var8 = Math.max((Float)this.recoveredField2231.getValue(), var5 + (Float)this.recoveredField2242.getValue() + var7);
      }

      if (!(Boolean)this.recoveredField2245.getValue() && !var2 && !(Boolean)this.recoveredField2239.getValue()) {
         GL11.glEnable(3042);
         this.method_28812(
            this.minecraft.fontRendererObj.drawString(var4, 0.0F, 0.0F, this.getTextOffsetY(), (Boolean)this.recoveredField2246.getValue()),
            this.minecraft.fontRendererObj.FONT_HEIGHT
         );
      } else {
         this.method_28812(var8, var6);
         if ((Boolean)this.recoveredField2245.getValue()) {
            Gui.drawRect(0.0F, 0.0F, var8, var6, this.recoveredField2244.method_08901());
            if ((Boolean)this.recoveredField2236.getValue()) {
               float var9 = (Float)this.recoveredField2235.getValue();
               Gui.method_00886(-var9, -var9, var8 + var9, var6 + var9, var9, this.recoveredField2237.method_08901());
            }
         }

         if (var2) {
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            RenderUtil.method_22063(this.recoveredField2001, var6 / 2.0F, 0.0F, 0.0F);
         }

         GL11.glEnable(3042);
         float var10 = 1.0F;
         if (this.minecraft.fontRendererObj.getStringWidth(var4) > var8 - this.recoveredField2242.method_08905() - var7
            && (Boolean)this.recoveredField2238.getValue().equals("Scale Text")) {
            var10 = (var8 - this.recoveredField2242.method_08905() - var7) / this.minecraft.fontRendererObj.getStringWidth(var4);
            GL11.glScalef(var10, var10, 1.0F);
            var10 = this.minecraft.fontRendererObj.getStringWidth(var4) / (var8 - this.recoveredField2242.method_08905() - var7);
         }

         this.minecraft
            .fontRendererObj
            .drawString(
               var4,
               this.recoveredField3889 / 2.0F * var10 - this.minecraft.fontRendererObj.getStringWidth(var4) / 2 + var7 * var10 / 2.0F + 0.6F,
               var6 / 2.0F * var10 - 3.49F,
               this.getTextOffsetY(),
               (Boolean)this.recoveredField2245.getValue() ? (Boolean)this.recoveredField2240.getValue() : (Boolean)this.recoveredField2246.getValue()
            );
         if (this.minecraft.fontRendererObj.getStringWidth(var4) > var8) {
            GL11.glScalef(var10, var10, 1.0F);
         }
      }

      GL11.glDisable(3042);
   }

   @Override
   public void method_00165() {
      this.recoveredField2002 = new Setting(this, "Show icon", "Show an icon corresponding to the mod.").setValue(true);
   }

   public IconTextHudModule(String var1, String var2) {
      super(var1, var2);
   }

   @Override
   public HudSizeDefaults method_08395() {
      return new HudSizeDefaults(10.0F, 16.0F, 64.0F, 40.0F, 56.0F, 80.0F);
   }

   @Override
   public boolean method_08396() {
      return false;
   }

   public ResourceLocation method_01868() {
      return this.recoveredField2001;
   }
}
