package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class LegacyFPSModule extends AbstractModule {
   public Setting recoveredField2576;
   public Setting recoveredField2577;
   public Setting recoveredField2578;

   public LegacyFPSModule() {
      super("FPS");
      this.setDefaultAnchor(CBGuiAnchor.RIGHT_TOP);
      this.setDefaultTranslations(0.0F, 0.0F);
      this.setState(false);
      this.recoveredField2578 = new Setting(this, "Show Background").setValue(true);
      this.recoveredField2577 = new Setting(this, "Text Color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.recoveredField2576 = new Setting(this, "Background Color").setValue(1862270976).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.setPreviewLabel("[144 FPS]", 1.4F);
      this.method_28820(GuiDrawEvent.class, this::method_09503);
   }

   public void method_09503(GuiDrawEvent var1) {
      if (this.method_28866()) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.getResolution());
         if ((Boolean)this.recoveredField2578.getValue()) {
            this.method_28812(56.0F, 18.0F);
            Gui.drawRect(0.0F, 0.0F, 56.0F, 13.0F, this.recoveredField2576.method_08901());
            String var2 = Minecraft.debugFPS + " FPS";
            this.minecraft
               .fontRendererObj
               .drawString(
                  var2,
                  (int)(this.recoveredField3889 / 2.0F - this.minecraft.fontRendererObj.getStringWidth(var2) / 2),
                  3,
                  this.recoveredField2577.method_08901()
               );
         } else {
            String var3 = "[" + Minecraft.debugFPS + " FPS]";
            this.method_28812(
               this.minecraft
                  .fontRendererObj
                  .drawString(
                     var3,
                     this.recoveredField3889 / 2.0F - this.minecraft.fontRendererObj.getStringWidth(var3) / 2,
                     0.0F,
                     this.recoveredField2577.method_08901(),
                     true
                  ),
               18.0F
            );
         }

         GL11.glPopMatrix();
      }
   }
}
