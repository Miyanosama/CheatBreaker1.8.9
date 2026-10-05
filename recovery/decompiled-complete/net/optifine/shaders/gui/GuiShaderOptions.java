package net.optifine.shaders.gui;

import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.optifine.Lang;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionScreen;

public class GuiShaderOptions extends GuiScreenOF {
   public ContinuationWebSocketFrame field_0005;
   public boolean changed;
   public TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderShaderOptions());
   public GameSettings settings;
   public static String field_0001;
   public String screenName = null;
   public GuiScreen prevScreen;
   public String title;
   public static String field_0003;
   public static String field_0010;
   public String screenText = null;

   public GuiShaderOptions(GuiScreen var1, GameSettings var2, String var3) {
      this(var1, var2);
      this.screenName = var3;
      if (var3 != null) {
         this.screenText = Shaders.translate("screen." + var3, var3);
      }
   }

   @Override
   public void a_() {
      super.a_();
      if (this.changed) {
         Shaders.saveShaderPackOptions();
         this.changed = false;
         Shaders.uninit();
      }
   }

   @Override
   public void actionPerformedRightClick(GuiButton var1) {
      if (var1 instanceof GuiButtonShaderOption) {
         GuiButtonShaderOption var2 = (GuiButtonShaderOption)var1;
         ShaderOption var3 = var2.getShaderOption();
         if (isShiftKeyDown()) {
            var3.resetValue();
         } else if (var2.isSwitchable()) {
            var3.prevValue();
         }

         this.updateAllButtons();
         this.changed = true;
      }
   }

   public void updateAllButtons() {
      for (GuiButton var2 : this.n) {
         if (var2 instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption var3 = (GuiButtonShaderOption)var2;
            ShaderOption var4 = var3.getShaderOption();
            if (var4 instanceof ShaderOptionProfile) {
               ShaderOptionProfile var5 = (ShaderOptionProfile)var4;
               var5.updateProfile();
            }

            var3.j = getButtonText(var4, var3.getButtonWidth());
            var3.valueChanged();
         }
      }
   }

   public GuiShaderOptions(GuiScreen var1, GameSettings var2) {
      this.changed = false;
      this.title = "Shader Options";
      this.prevScreen = var1;
      this.settings = var2;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      if (this.screenText != null) {
         this.drawCenteredString(this.q, this.screenText, this.l / 2, 15, 16777215);
      } else {
         this.drawCenteredString(this.q, this.title, this.l / 2, 15, 16777215);
      }

      super.drawScreen(var1, var2, var3);
      this.tooltipManager.drawTooltips(var1, var2, this.n);
   }

   @Override
   public void initGui() {
      this.title = I18n.format("of.options.shaderOptionsTitle");
      byte var1 = 100;
      int var2 = 0;
      byte var3 = 30;
      byte var4 = 20;
      byte var5 = 120;
      byte var6 = 20;
      int var7 = Shaders.getShaderPackColumns(this.screenName, 2);
      ShaderOption[] var8 = Shaders.getShaderPackOptions(this.screenName);
      if (var8 != null) {
         int var9 = MathHelper.ceiling_double_int(var8.length / 9.0);
         if (var7 < var9) {
            var7 = var9;
         }

         for (int var10 = 0; var10 < var8.length; var10++) {
            ShaderOption var11 = var8[var10];
            if (var11 != null && var11.isVisible()) {
               int var12 = var10 % var7;
               int var13 = var10 / var7;
               int var14 = Math.min(this.l / var7, 200);
               var2 = (this.l - var14 * var7) / 2;
               int var15 = var12 * var14 + 5 + var2;
               int var16 = var3 + var13 * var4;
               int var17 = var14 - 10;
               String var18 = getButtonText(var11, var17);
               Object var19;
               if (Shaders.isShaderPackOptionSlider(var11.getName())) {
                  var19 = new GuiSliderShaderOption(var1 + var10, var15, var16, var17, var6, var11, var18);
               } else {
                  var19 = new GuiButtonShaderOption(var1 + var10, var15, var16, var17, var6, var11, var18);
               }

               ((GuiButtonShaderOption)var19).l = var11.isEnabled();
               this.n.add((GuiButton)var19);
            }
         }
      }

      this.n.add(new GuiButton(201, this.l / 2 - var5 - 20, this.m / 6 + 168 + 11, var5, var6, I18n.format("controls.reset")));
      this.n.add(new GuiButton(200, this.l / 2 + 20, this.m / 6 + 168 + 11, var5, var6, I18n.format("gui.done")));
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k < 200 && var1 instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption var2 = (GuiButtonShaderOption)var1;
            ShaderOption var3 = var2.getShaderOption();
            if (var3 instanceof ShaderOptionScreen) {
               String var8 = var3.getName();
               GuiShaderOptions var5 = new GuiShaderOptions(this, this.settings, var8);
               this.j.displayGuiScreen(var5);
               return;
            }

            if (isShiftKeyDown()) {
               var3.resetValue();
            } else if (var2.isSwitchable()) {
               var3.nextValue();
            }

            this.updateAllButtons();
            this.changed = true;
         }

         if (var1.k == 201) {
            ShaderOption[] var6 = Shaders.method_02355(Shaders.getShaderPackOptions());

            for (int var7 = 0; var7 < var6.length; var7++) {
               ShaderOption var4 = var6[var7];
               var4.resetValue();
               this.changed = true;
            }

            this.updateAllButtons();
         }

         if (var1.k == 200) {
            if (this.changed) {
               Shaders.saveShaderPackOptions();
               this.changed = false;
               Shaders.uninit();
            }

            this.j.displayGuiScreen(this.prevScreen);
         }
      }
   }

   public static String getButtonText(ShaderOption var0, int var1) {
      String var2 = var0.getNameText();
      if (var0 instanceof ShaderOptionScreen) {
         ShaderOptionScreen var6 = (ShaderOptionScreen)var0;
         return var2 + "...";
      } else {
         FontRenderer var3 = Config.getMinecraft().fontRendererObj;
         int var4 = var3.getStringWidth(": " + Lang.getOff()) + 5;

         while (var3.getStringWidth(var2) + var4 >= var1 && var2.length() > 0) {
            var2 = var2.substring(0, var2.length() - 1);
         }

         String var7 = var0.isChanged() ? var0.getValueColor(var0.getValue()) : "";
         String var5 = var0.getValueText(var0.getValue());
         return var2 + ": " + var7 + var5;
      }
   }
}
