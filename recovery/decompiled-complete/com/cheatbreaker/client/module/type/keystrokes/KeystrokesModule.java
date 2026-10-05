package com.cheatbreaker.client.module.type.keystrokes;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.mainmenu.NewsMenu;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.varia.DenyAllFilter;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0315;
import recovered.unidentified.UnidentifiedClass0877;

public class KeystrokesModule extends AbstractModule {
   public Setting field_0028;
   public Setting field_0030;
   public Setting field_0006;
   public Setting field_0010;
   public Setting field_0041;
   public Setting field_0032;
   public Setting field_0051;
   public Setting field_0040;
   public UnidentifiedClass0315 field_0001;
   public UnidentifiedClass0315 field_0021;
   public DenyAllFilter field_0029;
   public Setting field_0033;
   public Setting field_0002;
   public Setting field_0025;
   public Setting field_0020;
   public UnidentifiedClass0315 field_0048;
   public Setting field_0018;
   public Setting field_0031;
   public Setting field_0000;
   public Setting field_0022;
   public List<Long> field_0042;
   public Setting field_0034;
   public Setting field_0050;
   public Setting field_0007;
   public Setting field_0037;
   public Setting field_0045;
   public Setting field_0013;
   public Setting field_0038;
   public Setting field_0049;
   public Setting field_0036;
   public Setting field_0014;
   public Setting field_0044;
   public Setting field_0047;
   public UnidentifiedClass0315 field_0046;
   public Setting field_0027;
   public Setting field_0023;
   public Setting field_0015;
   public Setting field_0039;
   public List<Long> field_0003 = new ArrayList<>();
   public NewsMenu field_0008;
   public Setting field_0043;
   public UnidentifiedClass0315 field_0024;
   public Setting field_0012;
   public Setting field_0035;
   public UnidentifiedClass0315 field_0026;
   public UnidentifiedClass0315 field_0016;
   public Setting field_0004;
   public Setting field_0017;
   public Setting field_0019;
   public UnidentifiedClass0315 field_0009;
   public Setting field_0005;
   public Setting field_0011;

   public KeystrokesModule() {
      super("Key Strokes");
      this.field_0042 = new ArrayList<>();
      this.setDefaultAnchor(CBGuiAnchor.LEFT_TOP);
      this.setDefaultTranslations(0.0F, 5.0F);
      this.setDefaultState(false);
      this.field_0044 = new Setting(this, "label").setValue("Key Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0047 = new Setting(this, "Show movement keys").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0032 = new Setting(this, "Show clicks").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0028 = new Setting(this, "Show spacebar").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0040 = new Setting(this, "Show sneak key").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0014 = new Setting(this, "label")
         .setValue("Click Options")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0032.getValue());
      this.field_0041 = new Setting(this, "Left CPS")
         .setValue("OFF")
         .acceptedValues("OFF", "Replace Clicks", "With Clicks", "Separate")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0032.getValue());
      this.field_0002 = new Setting(this, "Right CPS")
         .setValue("OFF")
         .acceptedValues("OFF", "Replace Clicks", "With Clicks", "Separate")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0032.getValue());
      this.field_0049 = new Setting(this, "CPS Background Height")
         .setValue(13.0F)
         .setMinMax(10.0F, 24.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(
            () -> (Boolean)this.field_0032.getValue() && this.field_0041.getValue().equals("Separate") || this.field_0002.getValue().equals("Separate")
         );
      this.field_0022 = new Setting(this, "CPS Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(
            () -> (Boolean)this.field_0032.getValue() && this.field_0041.getValue().equals("With Clicks") || this.field_0002.getValue().equals("With Clicks")
         );
      this.field_0038 = new Setting(this, "CPS Color (Pressed)")
         .setValue(-16777216)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(
            () -> (Boolean)this.field_0032.getValue() && this.field_0041.getValue().equals("With Clicks") || this.field_0002.getValue().equals("With Clicks")
         );
      this.field_0007 = new Setting(this, "label")
         .setValue("Spacebar Options")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0028.getValue());
      this.field_0037 = new Setting(this, "Show Spacebar Line")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0028.getValue());
      this.field_0019 = new Setting(this, "Center Spacebar Line")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0028.getValue() && (Boolean)this.field_0037.getValue());
      this.field_0050 = new Setting(this, "Spacebar Line Width")
         .setValue(4.0F)
         .setMinMax(1.0F, 8.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(() -> (Boolean)this.field_0028.getValue() && (Boolean)this.field_0037.getValue());
      this.field_0045 = new Setting(this, "Spacebar Line Height")
         .setValue(1.0F)
         .setMinMax(0.5F, 2.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(() -> (Boolean)this.field_0028.getValue() && (Boolean)this.field_0037.getValue());
      this.field_0031 = new Setting(this, "Spacebar Height")
         .setValue(50.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(() -> (Boolean)this.field_0028.getValue());
      this.field_0013 = new Setting(this, "label").setValue("Text Options").method_08914(SettingsDetailLevel.field_0003);
      this.field_0039 = new Setting(this, "Text Shadow").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0033 = new Setting(this, "Replace names with arrows")
         .setValue(false)
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0047.getValue());
      this.field_0018 = new Setting(this, "Legacy Position")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0047.getValue() || (Boolean)this.field_0032.getValue());
      this.field_0010 = new Setting(this, "label").setValue("Background Options").method_08914(SettingsDetailLevel.field_0003);
      this.field_0011 = new Setting(this, "Show Background").setValue(true).method_08914(SettingsDetailLevel.field_0003);
      this.field_0023 = new Setting(this, "Show Border").setValue(false).onChange(var1 -> this.initialize()).method_08914(SettingsDetailLevel.field_0003);
      this.field_0004 = new Setting(this, "Split Box Size").setValue(false).onChange(var1 -> this.initialize()).method_08914(SettingsDetailLevel.field_0003);
      this.field_0043 = new Setting(this, "Box size")
         .setValue(18.0F)
         .setMinMax(10.0F, 32.0F)
         .method_08892("px")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0020 = new Setting(this, "Box size height")
         .setValue(18.0F)
         .setMinMax(10.0F, 32.0F)
         .method_08892("px")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0004.getValue());
      this.field_0035 = new Setting(this, "Gap")
         .setValue(1.0F)
         .setMinMax(0.0F, 4.0F)
         .method_08892("px")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0000 = new Setting(this, "Border Thickness")
         .setValue(1.0F)
         .setMinMax(0.0F, 3.0F)
         .method_08892("px")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0023.getValue());
      this.field_0006 = new Setting(this, "label").setValue("Fade Options").method_08914(SettingsDetailLevel.field_0003);
      this.field_0005 = new Setting(this, "Text Fade Time")
         .setValue(0.0F)
         .setMinMax(0.0F, 200.0F)
         .method_08892("ms")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0015 = new Setting(this, "Background Fade Time")
         .setValue(75.0F)
         .setMinMax(0.0F, 200.0F)
         .method_08892("ms")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0011.getValue());
      this.field_0027 = new Setting(this, "Border Fade Time")
         .setValue(100.0F)
         .setMinMax(0.0F, 200.0F)
         .method_08892("ms")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0023.getValue());
      this.field_0034 = new Setting(this, "label").setValue("Color Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0051 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0036 = new Setting(this, "Text Color (Pressed)")
         .setValue(-16777216)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0030 = new Setting(this, "Background Color")
         .setValue(1862270976)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0011.getValue());
      this.field_0017 = new Setting(this, "Background Color (Pressed)")
         .setValue(1879048191)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0011.getValue());
      this.field_0025 = new Setting(this, "Border Color")
         .setValue(-1627389952)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0023.getValue());
      this.field_0012 = new Setting(this, "Border Color (Pressed)")
         .setValue(-1610612737)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0023.getValue());
      this.initialize();
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/wasd.png"), 55, 37);
      this.method_28821("Displays when your movement keys, mouse or spacebar is pressed.");
      this.method_28829("Fyu");
      this.method_28820(GuiDrawEvent.class, this::onDraw);
      this.method_28820(UnidentifiedClass0877.class, this::method_24303);
   }

   public void method_24303(UnidentifiedClass0877 var1) {
      if (var1.method_05882() == 0) {
         this.field_0003.add(System.currentTimeMillis());
      }

      if (var1.method_05882() == 1) {
         this.field_0042.add(System.currentTimeMillis());
      }
   }

   public void initialize() {
      int var1 = this.minecraft.gameSettings.keyBindForward.getKeyCode();
      int var2 = this.minecraft.gameSettings.keyBindLeft.getKeyCode();
      int var3 = this.minecraft.gameSettings.keyBindBack.getKeyCode();
      int var4 = this.minecraft.gameSettings.keyBindRight.getKeyCode();
      float var5 = (Float)this.field_0043.getValue();
      float var6 = this.field_0004.getValue() ? (Float)this.field_0020.getValue() : (Float)this.field_0043.getValue();
      float var7 = (Float)this.field_0035.getValue();
      String var8 = Keyboard.getKeyName(var1);
      String var9 = Keyboard.getKeyName(var2);
      String var10 = Keyboard.getKeyName(var3);
      String var11 = Keyboard.getKeyName(var4);
      float var12 = this.minecraft.fontRendererObj.getStringWidth(var8) * (Float)this.field_0044.getValue();
      float var13 = this.minecraft.fontRendererObj.getStringWidth(var9) * (Float)this.field_0044.getValue();
      float var14 = this.minecraft.fontRendererObj.getStringWidth(var10) * (Float)this.field_0044.getValue();
      float var15 = this.minecraft.fontRendererObj.getStringWidth(var11) * (Float)this.field_0044.getValue();
      int var16 = this.minecraft.gameSettings.keyBindJump.getKeyCode();
      int var17 = this.minecraft.gameSettings.field_0073.getKeyCode();
      int var18 = this.minecraft.gameSettings.field_0071.getKeyCode();
      int var19 = this.minecraft.gameSettings.keyBindSneak.getKeyCode();
      boolean var20 = (Boolean)this.field_0033.getValue();
      float var21 = (Float)this.field_0000.getValue();
      if (!(Boolean)this.field_0023.getValue()) {
         var21 = 0.0F;
      }

      this.field_0026 = new UnidentifiedClass0315(var20 ? "▲" : (var12 > var5 ? var8.substring(0, 1) : var8), var1, var5, var6);
      this.field_0021 = new UnidentifiedClass0315(var20 ? "◀" : (var13 > var5 ? var9.substring(0, 1) : var9), var2, var5, var6);
      this.field_0009 = new UnidentifiedClass0315(var20 ? "▼" : (var14 > var5 ? var10.substring(0, 1) : var10), var3, var5, var6);
      this.field_0016 = new UnidentifiedClass0315(var20 ? "▶" : (var15 > var5 ? var11.substring(0, 1) : var11), var4, var5, var6);
      float var22 = (this.field_0021.method_02424() + this.field_0009.method_02424() + this.field_0016.method_02424() + var7 + var21 * 2.0F) / 2.0F;
      this.field_0001 = new UnidentifiedClass0315(var5 < 14.0F ? "L" : "LMB", var17, var22, var6);
      this.field_0046 = new UnidentifiedClass0315(var5 < 14.0F ? "R" : "RMB", var18, var22, var6);
      this.field_0024 = new UnidentifiedClass0315(
         Keyboard.getKeyName(var16),
         var16,
         this.field_0021.method_02424() + this.field_0009.method_02424() + this.field_0016.method_02424() + 2.0F * var7 + var21 * 4.0F,
         var6 * (Float)this.field_0031.getValue() / 100.0F
      );
      this.field_0048 = new UnidentifiedClass0315(
         "SNEAK",
         var19,
         this.field_0021.method_02424() + this.field_0009.method_02424() + this.field_0016.method_02424() + 2.0F * var7 + var21 * 4.0F,
         var6 * 0.72F
      );
   }

   public void onDraw(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.field_0043 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.getResolution());
         this.field_0003.removeIf(var0 -> var0 < System.currentTimeMillis() - (-8733534669588819988L & 1881543658L));
         this.field_0042.removeIf(var0 -> var0 < System.currentTimeMillis() - (20122600L & 1141113837L));
         float var2 = 0.0F;
         float var3 = 0.0F;
         float var4 = (Float)this.field_0035.getValue();
         float var5 = (Float)this.field_0000.getValue();
         float var6 = (Float)this.field_0049.getValue();
         if (!(Boolean)this.field_0023.getValue()) {
            var5 = 0.0F;
         }

         if ((Boolean)this.field_0047.getValue()) {
            this.field_0026
               .method_02423(
                  this.field_0021.method_02424() + var4 + var5 * 2.0F,
                  0.0F,
                  this.field_0051.method_08901(),
                  this.field_0036.method_08901(),
                  this.field_0030.method_08901(),
                  this.field_0017.method_08901(),
                  this.field_0025.method_08901(),
                  this.field_0012.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            this.field_0021
               .method_02423(
                  0.0F,
                  this.field_0026.method_02421() + var4 + var5 * 2.0F,
                  this.field_0051.method_08901(),
                  this.field_0036.method_08901(),
                  this.field_0030.method_08901(),
                  this.field_0017.method_08901(),
                  this.field_0025.method_08901(),
                  this.field_0012.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            this.field_0009
               .method_02423(
                  this.field_0021.method_02424() + var4 + var5 * 2.0F,
                  this.field_0026.method_02421() + var4 + var5 * 2.0F,
                  this.field_0051.method_08901(),
                  this.field_0036.method_08901(),
                  this.field_0030.method_08901(),
                  this.field_0017.method_08901(),
                  this.field_0025.method_08901(),
                  this.field_0012.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            this.field_0016
               .method_02423(
                  this.field_0021.method_02424() + this.field_0009.method_02424() + 2.0F * var4 + var5 * 4.0F,
                  this.field_0026.method_02421() + var4 + var5 * 2.0F,
                  this.field_0051.method_08901(),
                  this.field_0036.method_08901(),
                  this.field_0030.method_08901(),
                  this.field_0017.method_08901(),
                  this.field_0025.method_08901(),
                  this.field_0012.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            var2 = this.field_0021.method_02424() + this.field_0009.method_02424() + this.field_0016.method_02424() + 2.0F * var4 + var5 * 2.0F;
            var3 += this.field_0026.method_02421() + 2.0F * var4 + this.field_0009.method_02421();
         }

         if ((Boolean)this.field_0032.getValue()) {
            this.field_0001
               .method_02423(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.field_0051.method_08901(),
                  this.field_0036.method_08901(),
                  this.field_0030.method_08901(),
                  this.field_0017.method_08901(),
                  this.field_0025.method_08901(),
                  this.field_0012.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            this.field_0046
               .method_02423(
                  this.field_0001.method_02424() + var4 + var5 * 2.0F,
                  var3 + var5 * 4.0F,
                  this.field_0051.method_08901(),
                  this.field_0036.method_08901(),
                  this.field_0030.method_08901(),
                  this.field_0017.method_08901(),
                  this.field_0025.method_08901(),
                  this.field_0012.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            var2 = this.field_0001.method_02424() + this.field_0046.method_02424() + var4 + var5 * 2.0F;
            var3 += this.field_0046.method_02421() + var4 + var5 * 2.0F;
         }

         if ((Boolean)this.field_0028.getValue()) {
            this.field_0024
               .method_02423(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.field_0051.method_08901(),
                  this.field_0036.method_08901(),
                  this.field_0030.method_08901(),
                  this.field_0017.method_08901(),
                  this.field_0025.method_08901(),
                  this.field_0012.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            var2 = this.field_0024.method_02424();
            var3 += this.field_0024.method_02421() + var4 + var5 * 2.0F;
         }

         if ((Boolean)this.field_0040.getValue()) {
            this.field_0048
               .method_02423(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.field_0051.method_08901(),
                  this.field_0036.method_08901(),
                  this.field_0030.method_08901(),
                  this.field_0017.method_08901(),
                  this.field_0025.method_08901(),
                  this.field_0012.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            var2 = this.field_0001.method_02424() + this.field_0046.method_02424() + var4 + var5 * 2.0F;
            var3 += this.field_0048.method_02421() + var4 + var5 * 2.0F;
         }

         if (this.field_0041.getValue().equals("Separate")) {
            String var7 = this.field_0003.size() + " CPS";
            if ((Boolean)this.field_0011.getValue()) {
               Gui.drawRect(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.field_0021.method_02424() + this.field_0009.method_02424() + this.field_0016.method_02424() + 2.0F * var4 + var5 * 4.0F,
                  var3 + var5 * 4.0F + var6,
                  this.field_0030.method_08901()
               );
            }

            if ((Boolean)this.field_0023.getValue()) {
               Gui.method_00886(
                  -var5,
                  var3 + var5 * 3.0F,
                  this.field_0021.method_02424() + this.field_0009.method_02424() + this.field_0016.method_02424() + 2.0F * var4 + var5 * 5.0F,
                  var3 + var5 * 5.0F + var6,
                  var5,
                  this.field_0025.method_08901()
               );
            }

            GlStateManager.enableBlend();
            this.minecraft
               .fontRendererObj
               .drawString(
                  var7,
                  this.field_0041 / 2.0F - this.minecraft.fontRendererObj.getStringWidth(var7) / 2 - 0.1F,
                  var3 + var5 * 4.0F + (var6 / 2.0F - 3.49F),
                  this.field_0022.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            var2 = this.field_0001.method_02424() + this.field_0046.method_02424() + var4 + var5 * 2.0F;
            var3 += var6 + var4 + var5 * 2.0F;
         }

         if (this.field_0002.getValue().equals("Separate")) {
            String var8 = this.field_0042.size() + " RCPS";
            if ((Boolean)this.field_0011.getValue()) {
               Gui.drawRect(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.field_0021.method_02424() + this.field_0009.method_02424() + this.field_0016.method_02424() + 2.0F * var4 + var5 * 4.0F,
                  var3 + var5 * 4.0F + var6,
                  this.field_0030.method_08901()
               );
            }

            if ((Boolean)this.field_0023.getValue()) {
               Gui.method_00886(
                  -var5,
                  var3 + var5 * 3.0F,
                  this.field_0021.method_02424() + this.field_0009.method_02424() + this.field_0016.method_02424() + 2.0F * var4 + var5 * 5.0F,
                  var3 + var5 * 5.0F + var6,
                  var5,
                  this.field_0025.method_08901()
               );
            }

            GlStateManager.enableBlend();
            this.minecraft
               .fontRendererObj
               .drawString(
                  var8,
                  this.field_0041 / 2.0F - this.minecraft.fontRendererObj.getStringWidth(var8) / 2 - 0.1F,
                  var3 + var5 * 4.0F + (var6 / 2.0F - 3.49F),
                  this.field_0022.method_08901(),
                  (Boolean)this.field_0039.getValue()
               );
            var2 = this.field_0001.method_02424() + this.field_0046.method_02424() + var4 + var5 * 2.0F;
            var3 += var6 + var4 + var5 * 2.0F;
         }

         GlStateManager.disableBlend();
         this.method_28812(var2, var3 - var4 + var5 * 2.0F);
         GL11.glPopMatrix();
      }
   }
}
