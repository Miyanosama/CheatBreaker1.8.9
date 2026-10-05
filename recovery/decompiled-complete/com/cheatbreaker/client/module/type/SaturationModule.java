package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceMappingsToDoubleTask;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo$1;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.stream.ChatController$ChatState;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$3;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0002;
import recovered.unidentified.UnidentifiedClass0212;
import recovered.unidentified.UnidentifiedClass1369;

public class SaturationModule extends NumberHudModule {
   public Setting field_0013;
   public NetworkPlayerInfo$1 field_0004;
   public ChatController$ChatState field_0016;
   public int field_0018;
   public static ResourceLocation field_0017 = new ResourceLocation("client/appleskin/icons.png");
   public byte field_0011;
   public Setting field_0008;
   public Setting field_0005;
   public Setting field_0014;
   public float field_0000 = 0.0F;
   public Setting field_0002;
   public Setting field_0015;
   public Setting field_0009;
   public Setting field_0003;
   public Setting field_0012;
   public Setting field_0010;
   public ConcurrentHashMapV8$MapReduceMappingsToDoubleTask field_0006;
   public StructureStrongholdPieces$3 field_0001;
   public Setting field_0007;

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
            byte var13 = 16;
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
      boolean var1 = this.minecraft.playerController.isNotCreative() || !(Boolean)this.field_0013.getValue();
      if (this.method_09815(this.field_0003, (int)this.minecraft.thePlayer.getFoodStats().getSaturationLevel(), this.field_0004.method_08912())) {
         return null;
      } else {
         return var1 ? this.method_24950() : null;
      }
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.field_0013 = new Setting(this, "Hide in Creative Mode", "Hide the mod in Creative mode.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0012 = new Setting(this, "Color Based on Amount", "Make the text color be based off the amount of saturation levels you have.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003);
      new Setting(this, "label").setValue("AppleSkin Options").method_08914(SettingsDetailLevel.field_0003);
      this.field_0008 = new Setting(this, "Show Saturation Overlay", "Shows the AppleSkin Saturation Overlay").setValue(false);
      this.field_0014 = new Setting(this, "Show Food Values Overlay", "Shows the AppleSkin Food Values Overlay").setValue(false);
   }

   @Override
   public void renderHud(GuiDrawEvent var1) {
      super.renderHud(var1);
      if (CheatBreaker.getInstance().getModuleManager().saturationModule.field_0014.method_08908()
         || CheatBreaker.getInstance().getModuleManager().saturationModule.field_0008.method_08908()) {
         if (this.minecraft.thePlayer.m == null && this.minecraft.playerController.shouldDrawHUD()) {
            Minecraft var2 = Minecraft.getMinecraft();
            EntityPlayerSP var3 = var2.thePlayer;
            ItemStack var4 = var3.getHeldItem();
            FoodStats var5 = var3.getFoodStats();
            ScaledResolution var6 = var1.getResolution();
            int var7 = var6.getScaledWidth() / 2 + 91;
            int var8 = var6.getScaledHeight() - this.field_0018;
            if (CheatBreaker.getInstance().getModuleManager().saturationModule.field_0008.method_08908()) {
               method_24947(0.0F, var5.getSaturationLevel(), var2, var7, var8, 1.0F);
            }

            if (CheatBreaker.getInstance().getModuleManager().saturationModule.field_0014.method_08908()
               && var4 != null
               && UnidentifiedClass0212.method_01544(var4)) {
               UnidentifiedClass0002 var9 = UnidentifiedClass0212.method_01545(var4, var3);
               method_24948(var9.field_0003, var5.getFoodLevel(), var2, var7, var8, this.field_0000);
               if (CheatBreaker.getInstance().getModuleManager().saturationModule.field_0008.method_08908()) {
                  int var10 = var5.getFoodLevel() + var9.field_0003;
                  float var11 = var5.getSaturationLevel() + var9.method_00007();
                  method_24947(
                     var11 > var10 ? var10 - var5.getSaturationLevel() : var9.method_00007(), var5.getSaturationLevel(), var2, var7, var8, this.field_0000
                  );
               }
            } else {
               this.field_0000 = 0.0F;
               this.field_0011 = 1;
            }
         }
      }
   }

   public void method_24949(TickEvent var1) {
      this.field_0000 = this.field_0000 + this.field_0011 * 0.125F;
      if (this.field_0000 >= 1.5F) {
         this.field_0000 = 1.0F;
         this.field_0011 = -1;
      } else if (this.field_0000 <= -0.5F) {
         this.field_0000 = 0.0F;
         this.field_0011 = 1;
      }
   }

   public String method_24950() {
      double var1 = this.minecraft.thePlayer.getFoodStats().getSaturationLevel();
      int var3 = (Integer)this.field_0009.getValue();
      String var4 = String.join("", Collections.nCopies(var3, "#"));
      return !this.field_0002.getValue() && var3 != 0
         ? new DecimalFormat("#." + var4, new DecimalFormatSymbols(Locale.ENGLISH)).format(var1)
         : String.format("%." + var3 + "f", var1);
   }

   public static void method_24947(float var0, float var1, Minecraft var2, int var3, int var4, float var5) {
      if (!(var1 + var0 < 0.0F)) {
         int var6 = var0 != 0.0F ? Math.max(0, (int)var1 / 2) : 0;
         int var7 = (int)Math.ceil(Math.min(20.0F, var1 + var0) / 2.0F);
         int var8 = var7 - var6;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         var2.getTextureManager().bindTexture(field_0017);
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
      this.field_0010 = new Setting(this, "Highest Amount Color", "The text color that will show when the saturation level is over 15.")
         .setValue(-2097185)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(this.field_0012::method_08908);
      this.field_0007 = new Setting(this, "High Amount Color", "The text color that will show when the saturation level is between 11-15.")
         .setValue(-4522079)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(this.field_0012::method_08908);
      this.field_0015 = new Setting(this, "Medium Amount Color", "The text color that will show when the saturation level is between 6-10.")
         .setValue(-103)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(this.field_0012::method_08908);
      this.field_0003 = new Setting(this, "Low Amount Color", "The text color that will show when the saturation level is between 1-5.")
         .setValue(-48574)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(this.field_0012::method_08908);
      this.field_0005 = new Setting(this, "Empty Amount Color", "The text color that will show when the saturation level is empty.")
         .setValue(-5627615)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(this.field_0012::method_08908);
      this.field_0012.method_08894(() -> !this.field_0012.method_08908());
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
      this.field_0011 = 1;
      this.field_0018 = 39;
      this.method_28821("Displays your current food saturation level.");
      this.method_28829("Eum3 (Number HUD)", "squeek502 (AppleSkin)");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/saturation.png"), 36, 36);
      this.setDefaultAnchor(CBGuiAnchor.MIDDLE_BOTTOM_RIGHT);
      this.setDefaultTranslations(-15.0F, -35.0F);
      this.field_0020 = false;
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_24949);
   }

   @Override
   public String method_00166() {
      return "Saturation";
   }

   @Override
   public int getTextOffsetY() {
      double var1 = this.minecraft.thePlayer.getFoodStats().getSaturationLevel();
      if ((Boolean)this.field_0012.getValue()) {
         if (var1 <= 0.0) {
            return this.field_0005.method_08901();
         } else if (var1 <= 5.0) {
            return this.field_0003.method_08901();
         } else if (var1 <= 10.0) {
            return this.field_0015.method_08901();
         } else {
            return var1 <= 15.0 ? this.field_0007.method_08901() : this.field_0010.method_08901();
         }
      } else {
         return this.field_0012.method_08901();
      }
   }

   @Override
   public void method_01862() {
      this.field_0002 = new Setting(this, "Show trailing zeros", "Show all trailing zeros.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Integer)this.field_0009.getValue() != 0);
      this.field_0009 = new Setting(this, "Decimals", "Change how many decimals the mod should display.")
         .setValue(0)
         .setMinMax(0, 17)
         .method_08914(SettingsDetailLevel.field_0003);
   }

   @Override
   public UnidentifiedClass1369 method_08395() {
      return new UnidentifiedClass1369(10.0F, 18.0F, 24.0F, 18.0F, 30.0F, 80.0F);
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
