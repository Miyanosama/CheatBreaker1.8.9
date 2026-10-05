package com.cheatbreaker.client.module.type.keystrokes;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.module.type.keystrokes.KeystrokeKey;
import com.cheatbreaker.client.event.type.MouseClickEvent;

public class KeystrokesModule extends AbstractModule {
   public Setting recoveredField2303;
   public Setting recoveredField2304;
   public Setting recoveredField2305;
   public Setting recoveredField2306;
   public Setting recoveredField2307;
   public Setting recoveredField2308;
   public Setting recoveredField2309;
   public Setting recoveredField2310;
   public KeystrokeKey recoveredField2311;
   public KeystrokeKey recoveredField2312;
   public Setting recoveredField2313;
   public Setting recoveredField2314;
   public Setting recoveredField2315;
   public Setting recoveredField2316;
   public KeystrokeKey recoveredField2317;
   public Setting recoveredField2318;
   public Setting recoveredField2319;
   public Setting recoveredField2320;
   public Setting recoveredField2321;
   public List<Long> recoveredField2322;
   public Setting recoveredField2323;
   public Setting recoveredField2324;
   public Setting recoveredField2325;
   public Setting recoveredField2326;
   public Setting recoveredField2327;
   public Setting recoveredField2328;
   public Setting recoveredField2329;
   public Setting recoveredField2330;
   public Setting recoveredField2331;
   public Setting recoveredField2332;
   public Setting recoveredField2333;
   public Setting recoveredField2334;
   public KeystrokeKey recoveredField2335;
   public Setting recoveredField2336;
   public Setting recoveredField2337;
   public Setting recoveredField2338;
   public Setting recoveredField2339;
   public List<Long> recoveredField2340 = new ArrayList<>();
   public Setting recoveredField2341;
   public KeystrokeKey recoveredField2342;
   public Setting recoveredField2343;
   public Setting recoveredField2344;
   public KeystrokeKey recoveredField2345;
   public KeystrokeKey recoveredField2346;
   public Setting recoveredField2347;
   public Setting recoveredField2348;
   public Setting recoveredField2349;
   public KeystrokeKey recoveredField2350;
   public Setting recoveredField2351;
   public Setting recoveredField2352;

   public KeystrokesModule() {
      super("Key Strokes");
      this.recoveredField2322 = new ArrayList<>();
      this.setDefaultAnchor(CBGuiAnchor.LEFT_TOP);
      this.setDefaultTranslations(0.0F, 5.0F);
      this.setDefaultState(false);
      this.recoveredField2333 = new Setting(this, "label").setValue("Key Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2334 = new Setting(this, "Show movement keys").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2308 = new Setting(this, "Show clicks").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2303 = new Setting(this, "Show spacebar").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2310 = new Setting(this, "Show sneak key").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2332 = new Setting(this, "label")
         .setValue("Click Options")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2308.getValue());
      this.recoveredField2307 = new Setting(this, "Left CPS")
         .setValue("OFF")
         .acceptedValues("OFF", "Replace Clicks", "With Clicks", "Separate")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2308.getValue());
      this.recoveredField2314 = new Setting(this, "Right CPS")
         .setValue("OFF")
         .acceptedValues("OFF", "Replace Clicks", "With Clicks", "Separate")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2308.getValue());
      this.recoveredField2330 = new Setting(this, "CPS Background Height")
         .setValue(13.0F)
         .setMinMax(10.0F, 24.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(
            () -> (Boolean)this.recoveredField2308.getValue() && (Boolean)this.recoveredField2307.getValue().equals("Separate")
               || (Boolean)this.recoveredField2314.getValue().equals("Separate")
         );
      this.recoveredField2321 = new Setting(this, "CPS Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(
            () -> (Boolean)this.recoveredField2308.getValue() && (Boolean)this.recoveredField2307.getValue().equals("With Clicks")
               || (Boolean)this.recoveredField2314.getValue().equals("With Clicks")
         );
      this.recoveredField2329 = new Setting(this, "CPS Color (Pressed)")
         .setValue(-16777216)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(
            () -> (Boolean)this.recoveredField2308.getValue() && (Boolean)this.recoveredField2307.getValue().equals("With Clicks")
               || (Boolean)this.recoveredField2314.getValue().equals("With Clicks")
         );
      this.recoveredField2325 = new Setting(this, "label")
         .setValue("Spacebar Options")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2303.getValue());
      this.recoveredField2326 = new Setting(this, "Show Spacebar Line")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2303.getValue());
      this.recoveredField2349 = new Setting(this, "Center Spacebar Line")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2303.getValue() && (Boolean)this.recoveredField2326.getValue());
      this.recoveredField2324 = new Setting(this, "Spacebar Line Width")
         .setValue(4.0F)
         .setMinMax(1.0F, 8.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(() -> (Boolean)this.recoveredField2303.getValue() && (Boolean)this.recoveredField2326.getValue());
      this.recoveredField2327 = new Setting(this, "Spacebar Line Height")
         .setValue(1.0F)
         .setMinMax(0.5F, 2.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(() -> (Boolean)this.recoveredField2303.getValue() && (Boolean)this.recoveredField2326.getValue());
      this.recoveredField2319 = new Setting(this, "Spacebar Height")
         .setValue(50.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(() -> (Boolean)this.recoveredField2303.getValue());
      this.recoveredField2328 = new Setting(this, "label").setValue("Text Options").method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2339 = new Setting(this, "Text Shadow").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2313 = new Setting(this, "Replace names with arrows")
         .setValue(false)
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField2334.getValue());
      this.recoveredField2318 = new Setting(this, "Legacy Position")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2334.getValue() || (Boolean)this.recoveredField2308.getValue());
      this.recoveredField2306 = new Setting(this, "label").setValue("Background Options").method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2352 = new Setting(this, "Show Background").setValue(true).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2337 = new Setting(this, "Show Border")
         .setValue(false)
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2347 = new Setting(this, "Split Box Size")
         .setValue(false)
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2341 = new Setting(this, "Box size")
         .setValue(18.0F)
         .setMinMax(10.0F, 32.0F)
         .method_08892("px")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2316 = new Setting(this, "Box size height")
         .setValue(18.0F)
         .setMinMax(10.0F, 32.0F)
         .method_08892("px")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2347.getValue());
      this.recoveredField2344 = new Setting(this, "Gap")
         .setValue(1.0F)
         .setMinMax(0.0F, 4.0F)
         .method_08892("px")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2320 = new Setting(this, "Border Thickness")
         .setValue(1.0F)
         .setMinMax(0.0F, 3.0F)
         .method_08892("px")
         .onChange(var1 -> this.initialize())
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2337.getValue());
      this.recoveredField2305 = new Setting(this, "label").setValue("Fade Options").method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2351 = new Setting(this, "Text Fade Time")
         .setValue(0.0F)
         .setMinMax(0.0F, 200.0F)
         .method_08892("ms")
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2338 = new Setting(this, "Background Fade Time")
         .setValue(75.0F)
         .setMinMax(0.0F, 200.0F)
         .method_08892("ms")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2352.getValue());
      this.recoveredField2336 = new Setting(this, "Border Fade Time")
         .setValue(100.0F)
         .setMinMax(0.0F, 200.0F)
         .method_08892("ms")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField2337.getValue());
      this.recoveredField2323 = new Setting(this, "label").setValue("Color Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2309 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2331 = new Setting(this, "Text Color (Pressed)")
         .setValue(-16777216)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2304 = new Setting(this, "Background Color")
         .setValue(1862270976)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField2352.getValue());
      this.recoveredField2348 = new Setting(this, "Background Color (Pressed)")
         .setValue(1879048191)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField2352.getValue());
      this.recoveredField2315 = new Setting(this, "Border Color")
         .setValue(-1627389952)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField2337.getValue());
      this.recoveredField2343 = new Setting(this, "Border Color (Pressed)")
         .setValue(-1610612737)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField2337.getValue());
      this.initialize();
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/wasd.png"), 55, 37);
      this.method_28821("Displays when your movement keys, mouse or spacebar is pressed.");
      this.method_28829("Fyu");
      this.method_28820(GuiDrawEvent.class, this::onDraw);
      this.method_28820(MouseClickEvent.class, this::method_24303);
   }

   public void method_24303(MouseClickEvent var1) {
      if (var1.method_05882() == 0) {
         this.recoveredField2340.add(System.currentTimeMillis());
      }

      if (var1.method_05882() == 1) {
         this.recoveredField2322.add(System.currentTimeMillis());
      }
   }

   public void initialize() {
      int var1 = this.minecraft.gameSettings.keyBindForward.getKeyCode();
      int var2 = this.minecraft.gameSettings.keyBindLeft.getKeyCode();
      int var3 = this.minecraft.gameSettings.keyBindBack.getKeyCode();
      int var4 = this.minecraft.gameSettings.keyBindRight.getKeyCode();
      float var5 = (Float)this.recoveredField2341.getValue();
      float var6 = (Boolean)this.recoveredField2347.getValue() ? (Float)this.recoveredField2316.getValue() : (Float)this.recoveredField2341.getValue();
      float var7 = (Float)this.recoveredField2344.getValue();
      String var8 = Keyboard.getKeyName(var1);
      String var9 = Keyboard.getKeyName(var2);
      String var10 = Keyboard.getKeyName(var3);
      String var11 = Keyboard.getKeyName(var4);
      float var12 = this.minecraft.fontRendererObj.getStringWidth(var8) * (Float)this.recoveredField3895.getValue();
      float var13 = this.minecraft.fontRendererObj.getStringWidth(var9) * (Float)this.recoveredField3895.getValue();
      float var14 = this.minecraft.fontRendererObj.getStringWidth(var10) * (Float)this.recoveredField3895.getValue();
      float var15 = this.minecraft.fontRendererObj.getStringWidth(var11) * (Float)this.recoveredField3895.getValue();
      int var16 = this.minecraft.gameSettings.keyBindJump.getKeyCode();
      int var17 = this.minecraft.gameSettings.recoveredField2699.getKeyCode();
      int var18 = this.minecraft.gameSettings.recoveredField2680.getKeyCode();
      int var19 = this.minecraft.gameSettings.keyBindSneak.getKeyCode();
      boolean var20 = (Boolean)this.recoveredField2313.getValue();
      float var21 = (Float)this.recoveredField2320.getValue();
      if (!(Boolean)this.recoveredField2337.getValue()) {
         var21 = 0.0F;
      }

      this.recoveredField2345 = new KeystrokeKey(var20 ? "▲" : (var12 > var5 ? var8.substring(0, 1) : var8), var1, var5, var6);
      this.recoveredField2312 = new KeystrokeKey(var20 ? "◀" : (var13 > var5 ? var9.substring(0, 1) : var9), var2, var5, var6);
      this.recoveredField2350 = new KeystrokeKey(var20 ? "▼" : (var14 > var5 ? var10.substring(0, 1) : var10), var3, var5, var6);
      this.recoveredField2346 = new KeystrokeKey(var20 ? "▶" : (var15 > var5 ? var11.substring(0, 1) : var11), var4, var5, var6);
      float var22 = (
            this.recoveredField2312.method_02424() + this.recoveredField2350.method_02424() + this.recoveredField2346.method_02424() + var7 + var21 * 2.0F
         )
         / 2.0F;
      this.recoveredField2311 = new KeystrokeKey(var5 < 14.0F ? "L" : "LMB", var17, var22, var6);
      this.recoveredField2335 = new KeystrokeKey(var5 < 14.0F ? "R" : "RMB", var18, var22, var6);
      this.recoveredField2342 = new KeystrokeKey(
         Keyboard.getKeyName(var16),
         var16,
         this.recoveredField2312.method_02424() + this.recoveredField2350.method_02424() + this.recoveredField2346.method_02424() + 2.0F * var7 + var21 * 4.0F,
         var6 * (Float)this.recoveredField2319.getValue() / 100.0F
      );
      this.recoveredField2317 = new KeystrokeKey(
         "SNEAK",
         var19,
         this.recoveredField2312.method_02424() + this.recoveredField2350.method_02424() + this.recoveredField2346.method_02424() + 2.0F * var7 + var21 * 4.0F,
         var6 * 0.72F
      );
   }

   public void onDraw(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.recoveredField3905 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.getResolution());
         this.recoveredField2340.removeIf(var0 -> var0 < System.currentTimeMillis() - 1000L);
         this.recoveredField2322.removeIf(var0 -> var0 < System.currentTimeMillis() - 1000L);
         float var2 = 0.0F;
         float var3 = 0.0F;
         float var4 = (Float)this.recoveredField2344.getValue();
         float var5 = (Float)this.recoveredField2320.getValue();
         float var6 = (Float)this.recoveredField2330.getValue();
         if (!(Boolean)this.recoveredField2337.getValue()) {
            var5 = 0.0F;
         }

         if ((Boolean)this.recoveredField2334.getValue()) {
            this.recoveredField2345
               .method_02423(
                  this.recoveredField2312.method_02424() + var4 + var5 * 2.0F,
                  0.0F,
                  this.recoveredField2309.method_08901(),
                  this.recoveredField2331.method_08901(),
                  this.recoveredField2304.method_08901(),
                  this.recoveredField2348.method_08901(),
                  this.recoveredField2315.method_08901(),
                  this.recoveredField2343.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            this.recoveredField2312
               .method_02423(
                  0.0F,
                  this.recoveredField2345.method_02421() + var4 + var5 * 2.0F,
                  this.recoveredField2309.method_08901(),
                  this.recoveredField2331.method_08901(),
                  this.recoveredField2304.method_08901(),
                  this.recoveredField2348.method_08901(),
                  this.recoveredField2315.method_08901(),
                  this.recoveredField2343.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            this.recoveredField2350
               .method_02423(
                  this.recoveredField2312.method_02424() + var4 + var5 * 2.0F,
                  this.recoveredField2345.method_02421() + var4 + var5 * 2.0F,
                  this.recoveredField2309.method_08901(),
                  this.recoveredField2331.method_08901(),
                  this.recoveredField2304.method_08901(),
                  this.recoveredField2348.method_08901(),
                  this.recoveredField2315.method_08901(),
                  this.recoveredField2343.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            this.recoveredField2346
               .method_02423(
                  this.recoveredField2312.method_02424() + this.recoveredField2350.method_02424() + 2.0F * var4 + var5 * 4.0F,
                  this.recoveredField2345.method_02421() + var4 + var5 * 2.0F,
                  this.recoveredField2309.method_08901(),
                  this.recoveredField2331.method_08901(),
                  this.recoveredField2304.method_08901(),
                  this.recoveredField2348.method_08901(),
                  this.recoveredField2315.method_08901(),
                  this.recoveredField2343.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            var2 = this.recoveredField2312.method_02424()
               + this.recoveredField2350.method_02424()
               + this.recoveredField2346.method_02424()
               + 2.0F * var4
               + var5 * 2.0F;
            var3 += this.recoveredField2345.method_02421() + 2.0F * var4 + this.recoveredField2350.method_02421();
         }

         if ((Boolean)this.recoveredField2308.getValue()) {
            this.recoveredField2311
               .method_02423(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.recoveredField2309.method_08901(),
                  this.recoveredField2331.method_08901(),
                  this.recoveredField2304.method_08901(),
                  this.recoveredField2348.method_08901(),
                  this.recoveredField2315.method_08901(),
                  this.recoveredField2343.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            this.recoveredField2335
               .method_02423(
                  this.recoveredField2311.method_02424() + var4 + var5 * 2.0F,
                  var3 + var5 * 4.0F,
                  this.recoveredField2309.method_08901(),
                  this.recoveredField2331.method_08901(),
                  this.recoveredField2304.method_08901(),
                  this.recoveredField2348.method_08901(),
                  this.recoveredField2315.method_08901(),
                  this.recoveredField2343.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            var2 = this.recoveredField2311.method_02424() + this.recoveredField2335.method_02424() + var4 + var5 * 2.0F;
            var3 += this.recoveredField2335.method_02421() + var4 + var5 * 2.0F;
         }

         if ((Boolean)this.recoveredField2303.getValue()) {
            this.recoveredField2342
               .method_02423(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.recoveredField2309.method_08901(),
                  this.recoveredField2331.method_08901(),
                  this.recoveredField2304.method_08901(),
                  this.recoveredField2348.method_08901(),
                  this.recoveredField2315.method_08901(),
                  this.recoveredField2343.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            var2 = this.recoveredField2342.method_02424();
            var3 += this.recoveredField2342.method_02421() + var4 + var5 * 2.0F;
         }

         if ((Boolean)this.recoveredField2310.getValue()) {
            this.recoveredField2317
               .method_02423(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.recoveredField2309.method_08901(),
                  this.recoveredField2331.method_08901(),
                  this.recoveredField2304.method_08901(),
                  this.recoveredField2348.method_08901(),
                  this.recoveredField2315.method_08901(),
                  this.recoveredField2343.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            var2 = this.recoveredField2311.method_02424() + this.recoveredField2335.method_02424() + var4 + var5 * 2.0F;
            var3 += this.recoveredField2317.method_02421() + var4 + var5 * 2.0F;
         }

         if ((Boolean)this.recoveredField2307.getValue().equals("Separate")) {
            String var7 = this.recoveredField2340.size() + " CPS";
            if ((Boolean)this.recoveredField2352.getValue()) {
               Gui.drawRect(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.recoveredField2312.method_02424()
                     + this.recoveredField2350.method_02424()
                     + this.recoveredField2346.method_02424()
                     + 2.0F * var4
                     + var5 * 4.0F,
                  var3 + var5 * 4.0F + var6,
                  this.recoveredField2304.method_08901()
               );
            }

            if ((Boolean)this.recoveredField2337.getValue()) {
               Gui.method_00886(
                  -var5,
                  var3 + var5 * 3.0F,
                  this.recoveredField2312.method_02424()
                     + this.recoveredField2350.method_02424()
                     + this.recoveredField2346.method_02424()
                     + 2.0F * var4
                     + var5 * 5.0F,
                  var3 + var5 * 5.0F + var6,
                  var5,
                  this.recoveredField2315.method_08901()
               );
            }

            GlStateManager.enableBlend();
            this.minecraft
               .fontRendererObj
               .drawString(
                  var7,
                  this.recoveredField3889 / 2.0F - this.minecraft.fontRendererObj.getStringWidth(var7) / 2 - 0.1F,
                  var3 + var5 * 4.0F + (var6 / 2.0F - 3.49F),
                  this.recoveredField2321.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            var2 = this.recoveredField2311.method_02424() + this.recoveredField2335.method_02424() + var4 + var5 * 2.0F;
            var3 += var6 + var4 + var5 * 2.0F;
         }

         if ((Boolean)this.recoveredField2314.getValue().equals("Separate")) {
            String var8 = this.recoveredField2322.size() + " RCPS";
            if ((Boolean)this.recoveredField2352.getValue()) {
               Gui.drawRect(
                  0.0F,
                  var3 + var5 * 4.0F,
                  this.recoveredField2312.method_02424()
                     + this.recoveredField2350.method_02424()
                     + this.recoveredField2346.method_02424()
                     + 2.0F * var4
                     + var5 * 4.0F,
                  var3 + var5 * 4.0F + var6,
                  this.recoveredField2304.method_08901()
               );
            }

            if ((Boolean)this.recoveredField2337.getValue()) {
               Gui.method_00886(
                  -var5,
                  var3 + var5 * 3.0F,
                  this.recoveredField2312.method_02424()
                     + this.recoveredField2350.method_02424()
                     + this.recoveredField2346.method_02424()
                     + 2.0F * var4
                     + var5 * 5.0F,
                  var3 + var5 * 5.0F + var6,
                  var5,
                  this.recoveredField2315.method_08901()
               );
            }

            GlStateManager.enableBlend();
            this.minecraft
               .fontRendererObj
               .drawString(
                  var8,
                  this.recoveredField3889 / 2.0F - this.minecraft.fontRendererObj.getStringWidth(var8) / 2 - 0.1F,
                  var3 + var5 * 4.0F + (var6 / 2.0F - 3.49F),
                  this.recoveredField2321.method_08901(),
                  (Boolean)this.recoveredField2339.getValue()
               );
            var2 = this.recoveredField2311.method_02424() + this.recoveredField2335.method_02424() + var4 + var5 * 2.0F;
            var3 += var6 + var4 + var5 * 2.0F;
         }

         GlStateManager.disableBlend();
         this.method_28812(var2, var3 - var4 + var5 * 2.0F);
         GL11.glPopMatrix();
      }
   }
}
