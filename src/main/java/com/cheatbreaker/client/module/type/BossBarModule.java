package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.awt.Color;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.event.type.HudPreviewDrawEvent;

public class BossBarModule extends AbstractModule {
   public Setting recoveredField1978;
   public Setting recoveredField1979;
   public Setting recoveredField1980;
   public Setting recoveredField1981;
   public Setting recoveredField1982;
   public Setting recoveredField1983;
   public Setting recoveredField1984;
   public Setting recoveredField1985;
   public Setting recoveredField1986;
   public Setting recoveredField1987;
   public Setting recoveredField1988;
   public Setting recoveredField1989;

   public void method_26218(String var1, float var2, int var3, int var4) {
      GlStateManager.enableBlend();
      int var5 = this.recoveredField1982.method_08901();
      if ((Boolean)this.recoveredField1983.getValue().equals("Texture Pack")) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.minecraft.getTextureManager().bindTexture(Gui.icons);
      } else {
         GL11.glColor4f((var5 >> 16 & 0xFF) / 255.0F, (var5 >> 8 & 0xFF) / 255.0F, (var5 & 0xFF) / 255.0F, (var5 >> 24 & 0xFF) / 255.0F);
         this.minecraft
            .getTextureManager()
            .bindTexture(new ResourceLocation("client/icons/bossbar/" + this.recoveredField1983.getValue().toString().toLowerCase() + ".png"));
      }

      FontRenderer var6 = this.minecraft.fontRendererObj;
      float var7 = this.recoveredField1983.getValue().equals("Custom") ? this.recoveredField1989.method_08905() : 182.0F;
      float var8 = (Boolean)this.recoveredField1981.getValue() && !(var6.getStringWidth(var1) < var7)
         ? var3 + (var6.getStringWidth(var1) - var7) * this.recoveredField1984.method_08905() / 100.0F
         : var3;
      float var10 = var2 * var7;
      if ((Boolean)this.recoveredField1988.getValue()) {
         if ((Boolean)this.recoveredField1983.getValue().equals("Custom")) {
            int var11 = new Color(
                  (this.recoveredField1980.method_08901() >> 16 & 0xFF) / 4,
                  (this.recoveredField1980.method_08901() >> 8 & 0xFF) / 4,
                  (this.recoveredField1980.method_08901() & 0xFF) / 4,
                  this.recoveredField1980.method_08901() >> 24 & 0xFF
               )
               .getRGB();
            int var12 = new Color(
                  (this.recoveredField1985.method_08901() >> 16 & 0xFF) / 4,
                  (this.recoveredField1985.method_08901() >> 8 & 0xFF) / 4,
                  (this.recoveredField1985.method_08901() & 0xFF) / 4,
                  this.recoveredField1985.method_08901() >> 24 & 0xFF
               )
               .getRGB();
            RenderUtil.method_22057(
               var8, var4, var8 + var7, var4 + this.recoveredField1978.method_08905(), this.recoveredField1987.method_08901(), var11, var12
            );
            if (var10 > 0.0F) {
               RenderUtil.method_22057(
                  var8,
                  var4,
                  var8 + var10,
                  var4 + this.recoveredField1978.method_08905(),
                  0,
                  this.recoveredField1980.method_08901(),
                  this.recoveredField1985.method_08901()
               );
            }

            RenderUtil.method_22057(var8, var4, var8 + var7, var4 + this.recoveredField1978.method_08905(), this.recoveredField1987.method_08901(), 0, 0);
         } else {
            this.minecraft.ingameGUI.drawTexturedModalRect(var8, var4, 0, 74, (int)var7, 5);
            this.minecraft.ingameGUI.drawTexturedModalRect(var8, var4, 0, 74, (int)var7, 5);
            if (var10 > 0.0F) {
               this.minecraft.ingameGUI.drawTexturedModalRect(var8, var4, 0, 79, (int)var10, 5);
            }
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if ((Boolean)this.recoveredField1981.getValue()) {
         var6.drawString(
            var1,
            var3 + this.recoveredField3889 / 2.0F - var6.getStringWidth(var1) / 2,
            var4 - 10,
            this.recoveredField1986.method_08901(),
            (Boolean)this.recoveredField1979.getValue()
         );
      }

      float var14 = (Boolean)this.recoveredField1988.getValue()
         ? (this.recoveredField1983.getValue().equals("Custom") ? this.recoveredField1978.method_08905() : 5.0F)
         : 0.0F;
      float var15 = (Boolean)this.recoveredField1981.getValue() ? 9.0F : 0.0F;
      float var13 = (Boolean)this.recoveredField1981.getValue() && (Boolean)this.recoveredField1988.getValue() ? 1.0F : 0.0F;
      this.method_28812(
         (Boolean)this.recoveredField1988.getValue()
            ? ((Boolean)this.recoveredField1981.getValue() ? Math.max((float)var6.getStringWidth(var1), var7) : var7)
            : var6.getStringWidth(var1),
         var14 + var15 + var13
      );
      GlStateManager.disableBlend();
   }

   public void method_26219(GuiDrawEvent var1) {
      if (this.method_28866()) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.getResolution());
         if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
            BossStatus.statusBarTime--;
            this.method_26218(BossStatus.bossName, BossStatus.healthScale, 0, (Boolean)this.recoveredField1981.getValue() ? 10 : 0);
         }

         GL11.glPopMatrix();
      }
   }

   public BossBarModule() {
      super("Boss Bar");
      this.setDefaultAnchor(CBGuiAnchor.MIDDLE_TOP);
      this.recoveredField3912 = false;
      this.recoveredField1981 = new Setting(this, "Render Text").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1988 = new Setting(this, "Render Health").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1979 = new Setting(this, "Text Shadow")
         .setValue(true)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField1981.getValue());
      this.recoveredField1983 = new Setting(this, "Boss Bar Texture")
         .setValue("Texture Pack")
         .acceptedValues("Texture Pack", "Default", "Blurred", "Custom")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1988.getValue());
      this.recoveredField1984 = new Setting(this, "Text Position")
         .setValue(50.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1988.getValue() && (Boolean)this.recoveredField1981.getValue());
      this.recoveredField1989 = new Setting(this, "Boss Bar Width")
         .setValue(182.0F)
         .setMinMax(100.0F, 200.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1988.getValue() && (Boolean)this.recoveredField1983.getValue().equals("Custom"));
      this.recoveredField1978 = new Setting(this, "Boss Bar Height")
         .setValue(5.0F)
         .setMinMax(3.0F, 24.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1988.getValue() && (Boolean)this.recoveredField1983.getValue().equals("Custom"));
      this.recoveredField1986 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1981.getValue());
      this.recoveredField1982 = new Setting(this, "Boss Bar Color")
         .setValue(-65337)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(
            () -> (Boolean)this.recoveredField1988.getValue()
               && !this.recoveredField1983.getValue().equals("Texture Pack")
               && !this.recoveredField1983.getValue().equals("Custom")
         );
      this.recoveredField1980 = new Setting(this, "Top Boss Bar Color")
         .setValue(-1310536)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1988.getValue() && (Boolean)this.recoveredField1983.getValue().equals("Custom"));
      this.recoveredField1985 = new Setting(this, "Bottom Boss Bar Color")
         .setValue(-7602067)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1988.getValue() && (Boolean)this.recoveredField1983.getValue().equals("Custom"));
      this.recoveredField1987 = new Setting(this, "Outline Boss Bar Color")
         .setValue(-11927494)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1988.getValue() && (Boolean)this.recoveredField1983.getValue().equals("Custom"));
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/bossbar.png"), 106, 5);
      this.method_28821("Displays the current boss and health.");
      this.method_28820(GuiDrawEvent.class, this::method_26219);
      this.method_28820(HudPreviewDrawEvent.class, this::method_26217);
      this.setDefaultState(true);
   }

   public void method_26217(HudPreviewDrawEvent var1) {
      if (this.method_28866() && (!this.recoveredField3905 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.method_01054());
         if (BossStatus.bossName == null || BossStatus.statusBarTime <= 0) {
            this.method_26218("Wither", 1.0F, 0, (Boolean)this.recoveredField1981.getValue() ? 10 : 0);
         }

         GL11.glPopMatrix();
      }
   }
}
