package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.overlay.element.ConsoleElement;
import com.cheatbreaker.client.ui.util.HudUtil;
import io.netty.handler.ssl.OpenSslEngine;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DirectionHudModule extends AbstractModule {
   public ResourceLocation field_0005 = new ResourceLocation("textures/gui/compass.png");
   public Setting field_0007;
   public OpenSslEngine field_0002;
   public ConsoleElement field_0003;
   public Setting field_0011;
   public Setting field_0008;
   public Setting field_0012;
   public Setting field_0010;
   public Setting field_0000;
   public Setting field_0004;
   public Setting field_0006;
   public Setting field_0009;
   public Setting field_0001;

   public void renderReal(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.field_0043 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         GlStateManager.enableBlend();
         this.scaleAndTranslate(var1.getResolution());
         this.method_28812(66.0F, 12.0F);
         if (!this.minecraft.ingameGUI.getChatGUI().getChatOpen() || (Boolean)this.field_0000.getValue()) {
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
      if ((Integer)this.field_0001.getValue() != 4095) {
         int var4 = this.field_0001.method_08901();
         int var5 = this.field_0006.method_08901();
         int var6 = this.field_0011.method_08901();
         this.minecraft.getTextureManager().bindTexture(this.field_0005);
         if ((Boolean)this.field_0007.getValue()) {
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

         if ((Boolean)this.field_0004.getValue()) {
            GL11.glColor4f((var6 >> 16 & 0xFF) / 255.0F, (var6 >> 8 & 0xFF) / 255.0F, (var6 & 0xFF) / 255.0F, (var6 >> 24 & 0xFF) / 255.0F);
            if (var1 < 128) {
               HudUtil.drawTexturedModalRect(var3, var2, var1, 72, 66, 12, -100.0F);
            } else {
               HudUtil.drawTexturedModalRect(var3, var2, var1 - 128, 84, 66, 12, -100.0F);
            }
         }
      } else {
         this.minecraft.getTextureManager().bindTexture(this.field_0005);
         if (var1 < 128) {
            HudUtil.drawTexturedModalRect(var3, var2, var1, 0, 66, 12, -100.0F);
         } else {
            HudUtil.drawTexturedModalRect(var3, var2, var1 - 128, 12, 66, 12, -100.0F);
         }
      }

      this.minecraft.fontRendererObj.drawString("|", var3 + 32, var2 + 1, this.field_0008.method_08901());
      this.minecraft.fontRendererObj.drawString("|§r", var3 + 32, var2 + 5, this.field_0008.method_08901());
      if ((Boolean)this.field_0009.getValue()) {
         float var7 = (Float)this.field_0012.getValue();
         Gui.method_00886(-var7, -var7, 66.0F + var7, 12.0F + var7, var7, this.field_0010.method_08901());
      }
   }

   public DirectionHudModule() {
      super("Direction HUD");
      this.setDefaultAnchor(CBGuiAnchor.MIDDLE_TOP);
      this.setState(false);
      this.field_0000 = new Setting(this, "Show While Typing", "Show the mod when opening chat.").setValue(true).method_08914(SettingsDetailLevel.field_0001);
      this.field_0004 = new Setting(this, "Highlight North", "Make North highlight a different color.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0007 = new Setting(this, "Show Background", "Draw a background.").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0009 = new Setting(this, "Show Border", "Draw a border around the background.").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0012 = new Setting(this, "Border Thickness", "Change the thickness of the border.")
         .setValue(1.0F)
         .setMinMax(0.25F, 3.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0009.getValue());
      this.field_0008 = new Setting(this, "Marker Color", "Change the marker color.")
         .setValue(-43691)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0001 = new Setting(this, "Direction Color", "Change the direction color.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0011 = new Setting(this, "Highlight Color", "Change the highlight color.")
         .setValue(-43691)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0004.getValue());
      this.field_0006 = new Setting(this, "Background Color", "Change the background color.")
         .setValue(-14606047)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0007.getValue());
      this.field_0010 = new Setting(this, "Border Color", "Sets the color for the border.")
         .setValue(-1627389952)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0009.getValue());
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/dirhud.png"), 65, 12);
      this.method_28821("Displays your cardinal direction.");
      this.method_28829("bspkrs", "jadedcat");
      this.method_28820(GuiDrawEvent.class, this::renderReal);
   }
}
