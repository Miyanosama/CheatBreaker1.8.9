package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.util.food.FoodValues;
import com.cheatbreaker.client.util.food.FoodHelper;
import com.cheatbreaker.client.ui.module.HudSizeDefaults;

public class SaturationModule extends NumberHudModule {
   public Setting recoveredField295;
   public int recoveredField296;
   public static ResourceLocation recoveredField297 = new ResourceLocation("client/appleskin/icons.png");
   public byte recoveredField298;
   public Setting recoveredField299;
   public Setting recoveredField300;
   public Setting recoveredField301;
   public float recoveredField302 = 0.0F;
   public Setting recoveredField303;
   public Setting recoveredField304;
   public Setting recoveredField305;
   public Setting recoveredField306;
   public Setting recoveredField307;
   public Setting recoveredField308;
   public Setting recoveredField309;

   public static void method_24948(int var0, int var1, Minecraft var2, int var3, int var4, float var5) {
      if (var0 != 0) {
         int var6 = var1 / 2;
         int var7 = (int)Math.ceil(Math.min(20, var1 + var0) / 2.0F);
         int var8 = var7 - var6;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         var2.getTextureManager().bindTexture(Gui.icons);
         method_24952(var5);

         for (int var9 = var6; var9 < var6 + var8; var9++) {
            int var10 = var9 * 2 + 1;
            int var11 = var3 - var9 * 8 - 9;
            int var13 = 16;
            byte var14 = 13;
            if (var2.thePlayer.isPotionActive(Potion.hunger)) {
               var13 += 36;
            }

            var2.ingameGUI.drawTexturedModalRect(var11, var4, 16 + var14 * 9, 27, 9, 9);
            if (var10 < var1 + var0) {
               var2.ingameGUI.drawTexturedModalRect(var11, var4, var13 + 36, 27, 9, 9);
            } else if (var10 == var1 + var0) {
               var2.ingameGUI.drawTexturedModalRect(var11, var4, var13 + 45, 27, 9, 9);
            }
         }

         method_24945(var5);
      }
   }

   @Override
   public boolean method_08396() {
      return false;
   }

   @Override
   public String method_00167() {
      boolean var1 = this.minecraft.playerController.isNotCreative() || !(Boolean)this.recoveredField295.getValue();
      if (this.method_09815(this.recoveredField2752, (int)this.minecraft.thePlayer.getFoodStats().getSaturationLevel(), this.recoveredField2753.method_08912())) {
         return null;
      } else {
         return var1 ? this.method_24950() : null;
      }
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.recoveredField295 = new Setting(this, "Hide in Creative Mode", "Hide the mod in Creative mode.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField307 = new Setting(this, "Color Based on Amount", "Make the text color be based off the amount of saturation levels you have.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM);
      new Setting(this, "label").setValue("AppleSkin Options").method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField299 = new Setting(this, "Show Saturation Overlay", "Shows the AppleSkin Saturation Overlay").setValue(false);
      this.recoveredField301 = new Setting(this, "Show Food Values Overlay", "Shows the AppleSkin Food Values Overlay").setValue(false);
   }

   @Override
   public void renderHud(GuiDrawEvent var1) {
      super.renderHud(var1);
      if (CheatBreaker.getInstance().getModuleManager().saturationModule.recoveredField301.method_08908()
         || CheatBreaker.getInstance().getModuleManager().saturationModule.recoveredField299.method_08908()) {
         if (this.minecraft.thePlayer.m == null && this.minecraft.playerController.shouldDrawHUD()) {
            Minecraft var2 = Minecraft.getMinecraft();
            EntityPlayerSP var3 = var2.thePlayer;
            ItemStack var4 = var3.getHeldItem();
            FoodStats var5 = var3.getFoodStats();
            ScaledResolution var6 = var1.getResolution();
            int var7 = var6.getScaledWidth() / 2 + 91;
            int var8 = var6.getScaledHeight() - this.recoveredField296;
            if (CheatBreaker.getInstance().getModuleManager().saturationModule.recoveredField299.method_08908()) {
               method_24947(0.0F, var5.getSaturationLevel(), var2, var7, var8, 1.0F);
            }

            if (CheatBreaker.getInstance().getModuleManager().saturationModule.recoveredField301.method_08908()
               && var4 != null
               && FoodHelper.method_01544(var4)) {
               FoodValues var9 = FoodHelper.method_01545(var4, var3);
               method_24948(var9.recoveredField48, var5.getFoodLevel(), var2, var7, var8, this.recoveredField302);
               if (CheatBreaker.getInstance().getModuleManager().saturationModule.recoveredField299.method_08908()) {
                  int var10 = var5.getFoodLevel() + var9.recoveredField48;
                  float var11 = var5.getSaturationLevel() + var9.method_00007();
                  method_24947(
                     var11 > var10 ? var10 - var5.getSaturationLevel() : var9.method_00007(),
                     var5.getSaturationLevel(),
                     var2,
                     var7,
                     var8,
                     this.recoveredField302
                  );
               }
            } else {
               this.recoveredField302 = 0.0F;
               this.recoveredField298 = 1;
            }
         }
      }
   }

   public void method_24949(TickEvent var1) {
      this.recoveredField302 = this.recoveredField302 + this.recoveredField298 * 0.125F;
      if (this.recoveredField302 >= 1.5F) {
         this.recoveredField302 = 1.0F;
         this.recoveredField298 = -1;
      } else if (this.recoveredField302 <= -0.5F) {
         this.recoveredField302 = 0.0F;
         this.recoveredField298 = 1;
      }
   }

   public String method_24950() {
      double var1 = this.minecraft.thePlayer.getFoodStats().getSaturationLevel();
      int var3 = (Integer)this.recoveredField305.getValue();
      String var4 = String.join("", Collections.nCopies(var3, "#"));
      return !(Boolean)this.recoveredField303.getValue() && var3 != 0
         ? new DecimalFormat("#." + var4, new DecimalFormatSymbols(Locale.ENGLISH)).format(var1)
         : String.format("%." + var3 + "f", var1);
   }

   public static void method_24947(float var0, float var1, Minecraft var2, int var3, int var4, float var5) {
      if (!(var1 + var0 < 0.0F)) {
         int var6 = var0 != 0.0F ? Math.max(0, (int)var1 / 2) : 0;
         int var7 = (int)Math.ceil(Math.min(20.0F, var1 + var0) / 2.0F);
         int var8 = var7 - var6;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         var2.getTextureManager().bindTexture(recoveredField297);
         method_24952(var5);

         for (int var9 = var6; var9 < var6 + var8; var9++) {
            int var10 = var3 - var9 * 8 - 9;
            float var12 = (var1 + var0) / 2.0F - var9;
            if (var12 >= 1.0F) {
               var2.ingameGUI.drawTexturedModalRect(var10, var4, 27, 0, 9, 9);
            } else if (var12 > 0.5) {
               var2.ingameGUI.drawTexturedModalRect(var10, var4, 18, 0, 9, 9);
            } else if (var12 > 0.25) {
               var2.ingameGUI.drawTexturedModalRect(var10, var4, 9, 0, 9, 9);
            } else if (var12 > 0.0F) {
               var2.ingameGUI.drawTexturedModalRect(var10, var4, 0, 0, 9, 9);
            }
         }

         method_24945(var5);
         var2.getTextureManager().bindTexture(Gui.icons);
      }
   }

   @Override
   public void method_04335() {
      this.recoveredField308 = new Setting(this, "Highest Amount Color", "The text color that will show when the saturation level is over 15.")
         .setValue(-2097185)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(this.recoveredField307::method_08908);
      this.recoveredField309 = new Setting(this, "High Amount Color", "The text color that will show when the saturation level is between 11-15.")
         .setValue(-4522079)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(this.recoveredField307::method_08908);
      this.recoveredField304 = new Setting(this, "Medium Amount Color", "The text color that will show when the saturation level is between 6-10.")
         .setValue(-103)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(this.recoveredField307::method_08908);
      this.recoveredField306 = new Setting(this, "Low Amount Color", "The text color that will show when the saturation level is between 1-5.")
         .setValue(-48574)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(this.recoveredField307::method_08908);
      this.recoveredField300 = new Setting(this, "Empty Amount Color", "The text color that will show when the saturation level is empty.")
         .setValue(-5627615)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(this.recoveredField307::method_08908);
      this.recoveredField2232.method_08894(() -> !this.recoveredField307.method_08908());
   }

   public static void method_24952(float var0) {
      GlStateManager.enableBlend();
      if (var0 != 1.0F) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, var0);
         GlStateManager.blendFunc(770, 771);
      }
   }

   public SaturationModule() {
      super("Saturation", "10", 1.5F, false, true);
      this.recoveredField298 = 1;
      this.recoveredField296 = 39;
      this.method_28821("Displays your current food saturation level.");
      this.method_28829("Eum3 (Number HUD)", "squeek502 (AppleSkin)");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/saturation.png"), 36, 36);
      this.setDefaultAnchor(CBGuiAnchor.MIDDLE_BOTTOM_RIGHT);
      this.setDefaultTranslations(-15.0F, -35.0F);
      this.recoveredField3912 = false;
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_24949);
   }

   @Override
   public String method_00166() {
      return "Saturation";
   }

   @Override
   public int getTextOffsetY() {
      double var1 = this.minecraft.thePlayer.getFoodStats().getSaturationLevel();
      if ((Boolean)this.recoveredField307.getValue()) {
         if (var1 <= 0.0) {
            return this.recoveredField300.method_08901();
         } else if (var1 <= 5.0) {
            return this.recoveredField306.method_08901();
         } else if (var1 <= 10.0) {
            return this.recoveredField304.method_08901();
         } else {
            return var1 <= 15.0 ? this.recoveredField309.method_08901() : this.recoveredField308.method_08901();
         }
      } else {
           return this.recoveredField2232.method_08901();
      }
   }

   @Override
   public void method_01862() {
      this.recoveredField303 = new Setting(this, "Show trailing zeros", "Show all trailing zeros.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Integer)this.recoveredField305.getValue() != 0);
      this.recoveredField305 = new Setting(this, "Decimals", "Change how many decimals the mod should display.")
         .setValue(0)
         .setMinMax(0, 17)
         .method_08914(SettingsDetailLevel.MEDIUM);
   }

   @Override
   public HudSizeDefaults method_08395() {
      return new HudSizeDefaults(10.0F, 18.0F, 24.0F, 18.0F, 30.0F, 80.0F);
   }

   @Override
   public String method_21178() {
      return "%VALUE%";
   }

   @Override
   public String method_00164() {
      return this.method_24950();
   }

   public static void method_24945(float var0) {
      GlStateManager.disableBlend();
      if (var0 != 1.0F) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }
}
