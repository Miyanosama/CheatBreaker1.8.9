package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.HudUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DirectionHudModule extends AbstractModule {
   public ResourceLocation recoveredField264 = new ResourceLocation("textures/gui/compass.png");
   public Setting recoveredField265;
   public Setting recoveredField266;
   public Setting recoveredField267;
   public Setting recoveredField268;
   public Setting recoveredField269;
   public Setting recoveredField270;
   public Setting recoveredField271;
   public Setting recoveredField272;
   public Setting recoveredField273;
   public Setting recoveredField274;

   public void renderReal(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.recoveredField3905 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         GlStateManager.enableBlend();
         this.scaleAndTranslate(var1.getResolution());
         this.method_28812(66.0F, 12.0F);
         if (!this.minecraft.ingameGUI.getChatGUI().getChatOpen() || (Boolean)this.recoveredField270.getValue()) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.method_05368();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         GlStateManager.disableBlend();
         GL11.glPopMatrix();
      }
   }

   public void method_05368() {
      int var1 = MathHelper.floor_double(this.minecraft.thePlayer.y * 256.0F / 360.0F + 0.5) & 0xFF;
      byte var2 = 0;
      byte var3 = 0;
      if ((Integer)this.recoveredField274.getValue() != 4095) {
         int var4 = this.recoveredField274.method_08901();
         int var5 = this.recoveredField272.method_08901();
         int var6 = this.recoveredField266.method_08901();
         this.minecraft.getTextureManager().bindTexture(this.recoveredField264);
         if ((Boolean)this.recoveredField265.getValue()) {
            GL11.glColor4f((var5 >> 16 & 0xFF) / 255.0F, (var5 >> 8 & 0xFF) / 255.0F, (var5 & 0xFF) / 255.0F, (var5 >> 24 & 0xFF) / 255.0F);
            if (var1 < 128) {
               HudUtil.drawTexturedModalRect(var3, var2, var1, 0, 66, 12, -100.0F);
            } else {
               HudUtil.drawTexturedModalRect(var3, var2, var1 - 128, 12, 66, 12, -100.0F);
            }
         }

         GL11.glColor4f((var4 >> 16 & 0xFF) / 255.0F, (var4 >> 8 & 0xFF) / 255.0F, (var4 & 0xFF) / 255.0F, (var4 >> 24 & 0xFF) / 255.0F);
         if (var1 < 128) {
            HudUtil.drawTexturedModalRect(var3, var2, var1, 24, 66, 12, -100.0F);
         } else {
            HudUtil.drawTexturedModalRect(var3, var2, var1 - 128, 36, 66, 12, -100.0F);
         }

         if ((Boolean)this.recoveredField271.getValue()) {
            GL11.glColor4f((var6 >> 16 & 0xFF) / 255.0F, (var6 >> 8 & 0xFF) / 255.0F, (var6 & 0xFF) / 255.0F, (var6 >> 24 & 0xFF) / 255.0F);
            if (var1 < 128) {
               HudUtil.drawTexturedModalRect(var3, var2, var1, 72, 66, 12, -100.0F);
            } else {
               HudUtil.drawTexturedModalRect(var3, var2, var1 - 128, 84, 66, 12, -100.0F);
            }
         }
      } else {
         this.minecraft.getTextureManager().bindTexture(this.recoveredField264);
         if (var1 < 128) {
            HudUtil.drawTexturedModalRect(var3, var2, var1, 0, 66, 12, -100.0F);
         } else {
            HudUtil.drawTexturedModalRect(var3, var2, var1 - 128, 12, 66, 12, -100.0F);
         }
      }

      this.minecraft.fontRendererObj.drawString("|", var3 + 32, var2 + 1, this.recoveredField267.method_08901());
      this.minecraft.fontRendererObj.drawString("|§r", var3 + 32, var2 + 5, this.recoveredField267.method_08901());
      if ((Boolean)this.recoveredField273.getValue()) {
         float var7 = (Float)this.recoveredField268.getValue();
         Gui.method_00886(-var7, -var7, 66.0F + var7, 12.0F + var7, var7, this.recoveredField269.method_08901());
      }
   }

   public DirectionHudModule() {
      super("Direction HUD");
      this.setDefaultAnchor(CBGuiAnchor.MIDDLE_TOP);
      this.setState(false);
      this.recoveredField270 = new Setting(this, "Show While Typing", "Show the mod when opening chat.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField271 = new Setting(this, "Highlight North", "Make North highlight a different color.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField265 = new Setting(this, "Show Background", "Draw a background.").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField273 = new Setting(this, "Show Border", "Draw a border around the background.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField268 = new Setting(this, "Border Thickness", "Change the thickness of the border.")
         .setValue(1.0F)
         .setMinMax(0.25F, 3.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField273.getValue());
      this.recoveredField267 = new Setting(this, "Marker Color", "Change the marker color.")
         .setValue(-43691)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField274 = new Setting(this, "Direction Color", "Change the direction color.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField266 = new Setting(this, "Highlight Color", "Change the highlight color.")
         .setValue(-43691)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField271.getValue());
      this.recoveredField272 = new Setting(this, "Background Color", "Change the background color.")
         .setValue(-14606047)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField265.getValue());
      this.recoveredField269 = new Setting(this, "Border Color", "Sets the color for the border.")
         .setValue(-1627389952)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField273.getValue());
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/dirhud.png"), 65, 12);
      this.method_28821("Displays your cardinal direction.");
      this.method_28829("bspkrs", "jadedcat");
      this.method_28820(GuiDrawEvent.class, this::renderReal);
   }
}
