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
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0144;

public class BossBarModule extends AbstractModule {
   public Setting field_0005;
   public Setting field_0007;
   public Setting field_0002;
   public Setting field_0003;
   public Setting field_0011;
   public Setting field_0008;
   public Setting field_0012;
   public Setting field_0010;
   public Setting field_0000;
   public Setting field_0004;
   public Setting field_0006;
   public EntityEndermite field_0009;
   public Setting field_0001;

   public void method_26218(String var1, float var2, int var3, int var4) {
      GlStateManager.enableBlend();
      int var5 = this.field_0011.method_08901();
      if (this.field_0008.getValue().equals("Texture Pack")) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.minecraft.getTextureManager().bindTexture(Gui.icons);
      } else {
         GL11.glColor4f((var5 >> 16 & 0xFF) / 255.0F, (var5 >> 8 & 0xFF) / 255.0F, (var5 & 0xFF) / 255.0F, (var5 >> 24 & 0xFF) / 255.0F);
         this.minecraft
            .getTextureManager()
            .bindTexture(new ResourceLocation("client/icons/bossbar/" + this.field_0008.getValue().toString().toLowerCase() + ".png"));
      }

      FontRenderer var6 = this.minecraft.fontRendererObj;
      float var7 = this.field_0008.getValue().equals("Custom") ? this.field_0001.method_08905() : 182.0F;
      float var8 = this.field_0003.getValue() && !(var6.getStringWidth(var1) < var7)
         ? var3 + (var6.getStringWidth(var1) - var7) * this.field_0012.method_08905() / 100.0F
         : var3;
      float var10 = var2 * var7;
      if ((Boolean)this.field_0006.getValue()) {
         if (this.field_0008.getValue().equals("Custom")) {
            int var11 = new Color(
                  (this.field_0002.method_08901() >> 16 & 0xFF) / 4,
                  (this.field_0002.method_08901() >> 8 & 0xFF) / 4,
                  (this.field_0002.method_08901() & 0xFF) / 4,
                  this.field_0002.method_08901() >> 24 & 0xFF
               )
               .getRGB();
            int var12 = new Color(
                  (this.field_0010.method_08901() >> 16 & 0xFF) / 4,
                  (this.field_0010.method_08901() >> 8 & 0xFF) / 4,
                  (this.field_0010.method_08901() & 0xFF) / 4,
                  this.field_0010.method_08901() >> 24 & 0xFF
               )
               .getRGB();
            RenderUtil.method_22057(var8, var4, var8 + var7, var4 + this.field_0005.method_08905(), this.field_0004.method_08901(), var11, var12);
            if (var10 > 0.0F) {
               RenderUtil.method_22057(
                  var8, var4, var8 + var10, var4 + this.field_0005.method_08905(), 0, this.field_0002.method_08901(), this.field_0010.method_08901()
               );
            }

            RenderUtil.method_22057(var8, var4, var8 + var7, var4 + this.field_0005.method_08905(), this.field_0004.method_08901(), 0, 0);
         } else {
            this.minecraft.ingameGUI.drawTexturedModalRect(var8, var4, 0, 74, (int)var7, 5);
            this.minecraft.ingameGUI.drawTexturedModalRect(var8, var4, 0, 74, (int)var7, 5);
            if (var10 > 0.0F) {
               this.minecraft.ingameGUI.drawTexturedModalRect(var8, var4, 0, 79, (int)var10, 5);
            }
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if ((Boolean)this.field_0003.getValue()) {
         var6.drawString(
            var1, var3 + this.field_0041 / 2.0F - var6.getStringWidth(var1) / 2, var4 - 10, this.field_0000.method_08901(), (Boolean)this.field_0007.getValue()
         );
      }

      float var14 = this.field_0006.getValue() ? (this.field_0008.getValue().equals("Custom") ? this.field_0005.method_08905() : 5.0F) : 0.0F;
      float var15 = this.field_0003.getValue() ? 9.0F : 0.0F;
      float var13 = this.field_0003.getValue() && this.field_0006.getValue() ? 1.0F : 0.0F;
      this.method_28812(
         this.field_0006.getValue() ? (this.field_0003.getValue() ? Math.max((float)var6.getStringWidth(var1), var7) : var7) : var6.getStringWidth(var1),
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
            this.method_26218(BossStatus.bossName, BossStatus.healthScale, 0, this.field_0003.getValue() ? 10 : 0);
         }

         GL11.glPopMatrix();
      }
   }

   public BossBarModule() {
      super("Boss Bar");
      this.setDefaultAnchor(CBGuiAnchor.MIDDLE_TOP);
      this.field_0020 = false;
      this.field_0003 = new Setting(this, "Render Text").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0006 = new Setting(this, "Render Health").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0007 = new Setting(this, "Text Shadow")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0003.getValue());
      this.field_0008 = new Setting(this, "Boss Bar Texture")
         .setValue("Texture Pack")
         .acceptedValues("Texture Pack", "Default", "Blurred", "Custom")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0006.getValue());
      this.field_0012 = new Setting(this, "Text Position")
         .setValue(50.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0006.getValue() && (Boolean)this.field_0003.getValue());
      this.field_0001 = new Setting(this, "Boss Bar Width")
         .setValue(182.0F)
         .setMinMax(100.0F, 200.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0006.getValue() && this.field_0008.getValue().equals("Custom"));
      this.field_0005 = new Setting(this, "Boss Bar Height")
         .setValue(5.0F)
         .setMinMax(3.0F, 24.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0006.getValue() && this.field_0008.getValue().equals("Custom"));
      this.field_0000 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0003.getValue());
      this.field_0011 = new Setting(this, "Boss Bar Color")
         .setValue(-65337)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(
            () -> (Boolean)this.field_0006.getValue() && !this.field_0008.getValue().equals("Texture Pack") && !this.field_0008.getValue().equals("Custom")
         );
      this.field_0002 = new Setting(this, "Top Boss Bar Color")
         .setValue(-1310536)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0006.getValue() && this.field_0008.getValue().equals("Custom"));
      this.field_0010 = new Setting(this, "Bottom Boss Bar Color")
         .setValue(-7602067)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0006.getValue() && this.field_0008.getValue().equals("Custom"));
      this.field_0004 = new Setting(this, "Outline Boss Bar Color")
         .setValue(-11927494)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0006.getValue() && this.field_0008.getValue().equals("Custom"));
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/bossbar.png"), 106, 5);
      this.method_28821("Displays the current boss and health.");
      this.method_28820(GuiDrawEvent.class, this::method_26219);
      this.method_28820(UnidentifiedClass0144.class, this::method_26217);
      this.setDefaultState(true);
   }

   public void method_26217(UnidentifiedClass0144 var1) {
      if (this.method_28866() && (!this.field_0043 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.method_01054());
         if (BossStatus.bossName == null || BossStatus.statusBarTime <= 0) {
            this.method_26218("Wither", 1.0F, 0, this.field_0003.getValue() ? 10 : 0);
         }

         GL11.glPopMatrix();
      }
   }
}
