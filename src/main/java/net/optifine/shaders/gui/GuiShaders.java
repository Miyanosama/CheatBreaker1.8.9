package net.optifine.shaders.gui;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.optifine.Lang;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderEnumShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersTex;
import net.optifine.shaders.config.EnumShaderOption;
import org.lwjgl.Sys;

public class GuiShaders extends GuiScreenOF {
   public boolean saved;
   public GuiScreen parentGui;
   public static final int recoveredField93 = 2;
   public static final int recoveredField94 = 1;
   public static final int recoveredField95 = 4;
   public static final int recoveredField96 = 0;
   public int updateTimer;
   public static final int recoveredField97 = 3;
   public static float[] QUALITY_MULTIPLIERS = new float[]{
      0.5F, 0.6F, 0.6666667F, 0.75F, 0.8333333F, 0.9F, 1.0F, 1.1666666F, 1.3333334F, 1.5F, 1.6666666F, 1.8F, 2.0F
   };
   public static String[] QUALITY_MULTIPLIER_NAMES = new String[]{
      "0.5x", "0.6x", "0.66x", "0.75x", "0.83x", "0.9x", "1x", "1.16x", "1.33x", "1.5x", "1.66x", "1.8x", "2x"
   };
   public static float QUALITY_MULTIPLIER_DEFAULT = 1.0F;
   public GuiSlotShaders shaderList;
   public static float[] HAND_DEPTH_VALUES = new float[]{0.0625F, 0.125F, 0.25F};
   public static String[] HAND_DEPTH_NAMES = new String[]{"0.5x", "1x", "2x"};
   public String screenTitle = "Shaders";
   public TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderEnumShaderOptions());
   public static float HAND_DEPTH_DEFAULT = 0.125F;

   public static int getValueIndex(float var0, float[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         float var3 = var1[var2];
         if (var3 >= var0) {
            return var2;
         }
      }

      return var1.length - 1;
   }

   public void updateButtons() {
      boolean var1 = Config.isShaders();

      for (GuiButton var3 : this.n) {
         if (var3.k != 201 && var3.k != 202 && var3.k != 210 && var3.k != EnumShaderOption.ANTIALIASING.ordinal()) {
            var3.l = var1;
         }
      }
   }

   public boolean hasShiftDown() {
      return isShiftKeyDown();
   }

   public float getNextValue(float var1, float[] var2, float var3, boolean var4, boolean var5) {
      if (var5) {
         return var3;
      } else {
         int var6 = getValueIndex(var1, var2);
         if (var4) {
            if (++var6 >= var2.length) {
               var6 = 0;
            }
         } else if (--var6 < 0) {
            var6 = var2.length - 1;
         }

         return var2[var6];
      }
   }

   public static String toStringQuality(float var0) {
      return toStringValue(var0, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_NAMES);
   }

   public Minecraft getMc() {
      return this.j;
   }

   public static String toStringAa(int var0) {
      return var0 == 2 ? "FXAA 2x" : (var0 == 4 ? "FXAA 4x" : Lang.getOff());
   }

   public void drawCenteredString(String var1, int var2, int var3, int var4) {
      this.drawCenteredString(this.q, var1, var2, var3, var4);
   }

   @Override
   public void a_() {
      super.a_();
      if (!this.saved) {
         Shaders.storeConfig();
      }
   }

   public void actionPerformed(GuiButton var1, boolean var2) throws java.io.IOException {
      if (var1.l) {
         if (!(var1 instanceof GuiButtonEnumShaderOption)) {
            if (!var2) {
               switch (var1.k) {
                  case 201:
                     switch (getOSType()) {
                        case 1:
                           String var3 = String.format("cmd.exe /C start \"Open file\" \"%s\"", Shaders.shaderPacksDir.getAbsolutePath());

                           try {
                              Runtime.getRuntime().exec(var3);
                              return;
                           } catch (IOException var10) {
                              var10.printStackTrace();
                              break;
                           }
                        case 2:
                           try {
                              Runtime.getRuntime().exec(new String[]{"/usr/bin/open", Shaders.shaderPacksDir.getAbsolutePath()});
                              return;
                           } catch (IOException var9) {
                              var9.printStackTrace();
                           }
                     }

                     boolean var11 = false;

                     try {
                        Class var13 = Class.forName("java.awt.Desktop");
                        Object var14 = var13.getMethod("getDesktop").invoke(null);
                        var13.getMethod("browse", URI.class).invoke(var14, new File(this.j.mcDataDir, "shaderpacks").toURI());
                     } catch (Throwable var8) {
                        var8.printStackTrace();
                        var11 = true;
                     }

                     if (var11) {
                        Config.dbg("Opening via system class!");
                        Sys.openURL("file://" + Shaders.shaderPacksDir.getAbsolutePath());
                     }
                     break;
                  case 202:
                     Shaders.storeConfig();
                     this.saved = true;
                     this.j.displayGuiScreen(this.parentGui);
                     break;
                  case 203:
                     GuiShaderOptions var4 = new GuiShaderOptions(this, Config.getGameSettings());
                     Config.getMinecraft().displayGuiScreen(var4);
                     break;
                  case 210:
                     try {
                        Class var5 = Class.forName("java.awt.Desktop");
                        Object var6 = var5.getMethod("getDesktop").invoke(null);
                        var5.getMethod("browse", URI.class).invoke(var6, new URI("http://optifine.net/shaderPacks"));
                     } catch (Throwable var7) {
                        var7.printStackTrace();
                     }
                  case 204:
                  case 205:
                  case 206:
                  case 207:
                  case 208:
                  case 209:
                  default:
                     this.shaderList.actionPerformed(var1);
               }
            }
         } else {
            GuiButtonEnumShaderOption var12 = (GuiButtonEnumShaderOption)var1;
            switch (var12.getEnumShaderOption()) {
               case ANTIALIASING:
                  Shaders.nextAntialiasingLevel(!var2);
                  if (this.hasShiftDown()) {
                     Shaders.configAntialiasingLevel = 0;
                  }

                  Shaders.uninit();
                  break;
               case NORMAL_MAP:
                  Shaders.configNormalMap = !Shaders.configNormalMap;
                  if (this.hasShiftDown()) {
                     Shaders.configNormalMap = true;
                  }

                  Shaders.uninit();
                  this.j.scheduleResourcesRefresh();
                  break;
               case SPECULAR_MAP:
                  Shaders.configSpecularMap = !Shaders.configSpecularMap;
                  if (this.hasShiftDown()) {
                     Shaders.configSpecularMap = true;
                  }

                  Shaders.uninit();
                  this.j.scheduleResourcesRefresh();
                  break;
               case RENDER_RES_MUL:
                  Shaders.configRenderResMul = this.getNextValue(
                     Shaders.configRenderResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !var2, this.hasShiftDown()
                  );
                  Shaders.uninit();
                  Shaders.scheduleResize();
                  break;
               case SHADOW_RES_MUL:
                  Shaders.configShadowResMul = this.getNextValue(
                     Shaders.configShadowResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !var2, this.hasShiftDown()
                  );
                  Shaders.uninit();
                  Shaders.scheduleResizeShadow();
                  break;
               case HAND_DEPTH_MUL:
                  Shaders.configHandDepthMul = this.getNextValue(Shaders.configHandDepthMul, HAND_DEPTH_VALUES, HAND_DEPTH_DEFAULT, !var2, this.hasShiftDown());
                  Shaders.uninit();
                  break;
               case OLD_HAND_LIGHT:
                  Shaders.configOldHandLight.nextValue(!var2);
                  if (this.hasShiftDown()) {
                     Shaders.configOldHandLight.resetValue();
                  }

                  Shaders.uninit();
                  break;
               case OLD_LIGHTING:
                  Shaders.configOldLighting.nextValue(!var2);
                  if (this.hasShiftDown()) {
                     Shaders.configOldLighting.resetValue();
                  }

                  Shaders.updateBlockLightLevel();
                  Shaders.uninit();
                  this.j.scheduleResourcesRefresh();
                  break;
               case TWEAK_BLOCK_DAMAGE:
                  Shaders.configTweakBlockDamage = !Shaders.configTweakBlockDamage;
                  break;
               case CLOUD_SHADOW:
                  Shaders.configCloudShadow = !Shaders.configCloudShadow;
                  break;
               case TEX_MIN_FIL_B:
                  Shaders.configTexMinFilB = (Shaders.configTexMinFilB + 1) % 3;
                  Shaders.configTexMinFilN = Shaders.configTexMinFilS = Shaders.configTexMinFilB;
                  var1.j = "Tex Min: " + Shaders.recoveredField2081[Shaders.configTexMinFilB];
                  ShadersTex.updateTextureMinMagFilter();
                  break;
               case TEX_MAG_FIL_N:
                  Shaders.configTexMagFilN = (Shaders.configTexMagFilN + 1) % 2;
                  var1.j = "Tex_n Mag: " + Shaders.recoveredField2163[Shaders.configTexMagFilN];
                  ShadersTex.updateTextureMinMagFilter();
                  break;
               case TEX_MAG_FIL_S:
                  Shaders.configTexMagFilS = (Shaders.configTexMagFilS + 1) % 2;
                  var1.j = "Tex_s Mag: " + Shaders.recoveredField2163[Shaders.configTexMagFilS];
                  ShadersTex.updateTextureMinMagFilter();
                  break;
               case SHADOW_CLIP_FRUSTRUM:
                  Shaders.configShadowClipFrustrum = !Shaders.configShadowClipFrustrum;
                  var1.j = "ShadowClipFrustrum: " + toStringOnOff(Shaders.configShadowClipFrustrum);
                  ShadersTex.updateTextureMinMagFilter();
            }

            var12.updateButtonText();
         }
      }
   }

   @Override
   public void actionPerformedRightClick(GuiButton var1) throws java.io.IOException {
      this.actionPerformed(var1, true);
   }

   public static String toStringOnOff(boolean var0) {
      String var1 = Lang.getOn();
      String var2 = Lang.getOff();
      return var0 ? var1 : var2;
   }

   @Override
   public void initGui() {
      this.screenTitle = I18n.format("of.options.shadersTitle");
      if (Shaders.shadersConfig == null) {
         Shaders.loadConfig();
      }

      byte var1 = 120;
      byte var2 = 20;
      int var3 = this.l - var1 - 10;
      byte var4 = 30;
      byte var5 = 20;
      int var6 = this.l - var1 - 20;
      this.shaderList = new GuiSlotShaders(this, var6, this.m, var4, this.m - 50, 16);
      this.shaderList.registerScrollButtons(7, 8);
      this.n.add(new GuiButtonEnumShaderOption(EnumShaderOption.ANTIALIASING, var3, 0 * var5 + var4, var1, var2));
      this.n.add(new GuiButtonEnumShaderOption(EnumShaderOption.NORMAL_MAP, var3, 1 * var5 + var4, var1, var2));
      this.n.add(new GuiButtonEnumShaderOption(EnumShaderOption.SPECULAR_MAP, var3, 2 * var5 + var4, var1, var2));
      this.n.add(new GuiButtonEnumShaderOption(EnumShaderOption.RENDER_RES_MUL, var3, 3 * var5 + var4, var1, var2));
      this.n.add(new GuiButtonEnumShaderOption(EnumShaderOption.SHADOW_RES_MUL, var3, 4 * var5 + var4, var1, var2));
      this.n.add(new GuiButtonEnumShaderOption(EnumShaderOption.HAND_DEPTH_MUL, var3, 5 * var5 + var4, var1, var2));
      this.n.add(new GuiButtonEnumShaderOption(EnumShaderOption.OLD_HAND_LIGHT, var3, 6 * var5 + var4, var1, var2));
      this.n.add(new GuiButtonEnumShaderOption(EnumShaderOption.OLD_LIGHTING, var3, 7 * var5 + var4, var1, var2));
      int var7 = Math.min(150, var6 / 2 - 10);
      int var8 = var6 / 4 - var7 / 2;
      int var9 = this.m - 25;
      this.n.add(new GuiButton(201, var8, var9, var7 - 22 + 1, var2, Lang.get("of.options.shaders.shadersFolder")));
      this.n.add(new GuiButtonDownloadShaders(210, var8 + var7 - 22 - 1, var9));
      this.n.add(new GuiButton(202, var6 / 4 * 3 - var7 / 2, this.m - 25, var7, var2, I18n.format("gui.done")));
      this.n.add(new GuiButton(203, var3, this.m - 25, var1, var2, Lang.get("of.options.shaders.shaderOptions")));
      this.updateButtons();
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      this.actionPerformed(var1, false);
   }

   public static String toStringValue(float var0, float[] var1, String[] var2) {
      int var3 = getValueIndex(var0, var1);
      return var2[var3];
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.updateTimer--;
   }

   public static int getOSType() {
      String var0 = System.getProperty("os.name").toLowerCase();
      return var0.contains("win")
         ? 1
         : (
            var0.contains("mac")
               ? 2
               : (var0.contains("solaris") ? 3 : (var0.contains("sunos") ? 3 : (var0.contains("linux") ? 4 : (var0.contains("unix") ? 4 : 0))))
         );
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.shaderList.a(var1, var2, var3);
      if (this.updateTimer <= 0) {
         this.shaderList.updateList();
         this.updateTimer += 20;
      }

      this.drawCenteredString(this.q, this.screenTitle + " ", this.l / 2, 15, 16777215);
      String var4 = "OpenGL: " + Shaders.glVersionString + ", " + Shaders.glVendorString + ", " + Shaders.glRendererString;
      int var5 = this.q.getStringWidth(var4);
      if (var5 < this.l - 5) {
         this.drawCenteredString(this.q, var4, this.l / 2, this.m - 40, 8421504);
      } else {
         this.drawString(this.q, var4, 5, this.m - 40, 8421504);
      }

      super.drawScreen(var1, var2, var3);
      this.tooltipManager.drawTooltips(var1, var2, this.n);
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.shaderList.handleMouseInput();
   }

   public static String toStringHandDepth(float var0) {
      return toStringValue(var0, HAND_DEPTH_VALUES, HAND_DEPTH_NAMES);
   }

   public GuiShaders(GuiScreen var1, GameSettings var2) {
      this.updateTimer = -1;
      this.saved = false;
      this.parentGui = var1;
   }
}
