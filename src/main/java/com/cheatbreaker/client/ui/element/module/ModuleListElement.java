package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleManager;
import com.cheatbreaker.client.module.staff.StaffModule;
import com.cheatbreaker.client.module.type.AutoTextModule;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.element.type.ColorPickerElement;
import com.cheatbreaker.client.ui.element.type.custom.GlobalSettingsElement;
import com.cheatbreaker.client.ui.element.type.custom.KeybindElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import com.cheatbreaker.client.ui.element.type.BlockSelectionElement;
import org.davidmoten.text.utils.WordWrap;
import com.cheatbreaker.client.ui.element.type.PotionSelectionElement;
import com.cheatbreaker.client.ui.element.type.SettingSectionElement;
import com.cheatbreaker.client.ui.element.type.CrosshairPreviewElement;
import com.cheatbreaker.client.ui.element.type.CyclingToggleElement;
import com.cheatbreaker.client.ui.element.type.CyclingChoiceElement;
import com.cheatbreaker.client.ui.element.type.IconNumericSliderElement;
import com.cheatbreaker.client.ui.element.module.ModuleListElement$EnumSwitch;
import com.cheatbreaker.client.ui.util.GuiThemeColors;

public class ModuleListElement extends AbstractScrollableElement {
   public ModulesGuiButtonElement recoveredField1357;
   public boolean recoveredField1358 = false;
   public boolean recoveredField1359;
   public List<ModuleSettingsElement> recoveredField1360;
   public ModulesGuiButtonElement recoveredField1361;
   public List<AbstractModulesGuiElement> recoveredField1362;
   public int recoveredField1363;
   public ModulesGuiButtonElement recoveredField1364 = null;
   public Map<Object, ArrayList<AbstractModulesGuiElement>> recoveredField1365;
   public ModulesGuiButtonElement recoveredField1366;
   public GlobalSettingsElement recoveredField1367;
   public AbstractScrollableElement scrollable;
   public AbstractModule module;
   public ModulesGuiButtonElement recoveredField1368;
   public int recoveredField1369 = 0;

   public ModuleListElement(List var1, float var2, int var3, int var4, int var5, int var6) {
      super(var2, var3, var4, var5, var6);
      this.recoveredField1359 = var1 == CheatBreaker.getInstance().getModuleManager().recoveredField1725;
      this.recoveredField1363 = -12418828;
      this.recoveredField1367 = new GlobalSettingsElement(this, this.recoveredField1363, var2);
      this.recoveredField1360 = new ArrayList<>();

      for (Object var8 : var1) {
         if (!((AbstractModule)var8).method_28787() || ((AbstractModule)var8).isStaffEnabledModule()) {
            if (!((AbstractModule)var8).getName().equalsIgnoreCase("Zans Minimap")) {
               this.recoveredField1360.add(new ModuleSettingsElement(this, this.recoveredField1363, (AbstractModule)var8, var2));
            }

            if (((AbstractModule)var8).getGuiAnchor() != null) {
               int var10006 = this.x + 2;
               int var10007 = this.y + 4;
               this.recoveredField1364 = new ModulesGuiButtonElement(
                  CheatBreaker.getInstance().recoveredField1589, null, "Reset Position", var10006, var10007, 14, 14, this.recoveredField1363, var2
               );
            }
         }
      }

      this.recoveredField1366 = new ModulesGuiButtonElement(null, "arrow-64.png", this.x + 2, this.y + 4, 28, 28, this.recoveredField1363, var2, false);
      int var20 = this.x + 2;
      int var23 = this.y + 4;
      this.recoveredField1368 = new ModulesGuiButtonElement(
         CheatBreaker.getInstance().recoveredField1589, null, "Reset Settings", var20, var23, 14, 14, this.recoveredField1363, var2
      );
      this.module = null;
      this.recoveredField1365 = new HashMap<>();

      for (Object var16 : var1) {
         if ((!((AbstractModule)var16).method_28787() || ((AbstractModule)var16).isStaffEnabledModule())
            && var16 != CheatBreaker.getInstance().getModuleManager().minmap) {
            ArrayList var9 = new ArrayList();

            for (Setting var11 : ((AbstractModule)var16).getSettingsList()) {
               switch (ModuleListElement$EnumSwitch.recoveredField991[var11.getType().ordinal()]) {
                  case 1:
                     if (var16 == CheatBreaker.getInstance().getModuleManager().potionStatus
                        && var11 == CheatBreaker.getInstance().getModuleManager().potionStatus.recoveredField2953) {
                        // Keep the existing global storage keys while grouping the inventory controls in Potion Status.
                        GlobalSettings settings = CheatBreaker.getInstance().getGlobalSettings();
                        var9.add(new CyclingToggleElement(var11, var2, "Show Potion Info (Mod On)"));
                        var9.add(new CyclingToggleElement(settings.recoveredField541, var2, "Show Potion Info (Mod Off)"));
                        var9.add(new CyclingToggleElement(settings.recoveredField515, var2));
                     } else {
                        var9.add(new CyclingToggleElement(var11, var2));
                     }
                     break;
                  case 2:
                  case 3:
                  case 4:
                     if (((AbstractModule)var16).method_28787() && var11 == ((StaffModule)var16).getKeybindSetting()
                        || ((AbstractModule)var16).method_28787() && var11 == ((AbstractModule)var16).recoveredField3895
                        || ((AbstractModule)var16).guiAnchor == null
                           && var16 != CheatBreaker.getInstance().getModuleManager().recoveredField1721
                           && var11 == ((AbstractModule)var16).recoveredField3895) {
                        break;
                     }

                     if (var11.getType().equals(Setting.Type.INTEGER) && var11.method_08911().toLowerCase().contains("color")) {
                        var9.add(new ColorPickerElement(var11, var2));
                     } else {
                        if (var11.getType().equals(Setting.Type.INTEGER) && var11.method_08911().endsWith("Keybind")) {
                           if ((
                                 ((AbstractModule)var16).guiAnchor != null
                                    || var16 == CheatBreaker.getInstance().getModuleManager().recoveredField1721
                                    || var11 != ((AbstractModule)var16).recoveredField3898
                              )
                              && (
                                 !((AbstractModule)var16).recoveredField3912
                                    || var16 == CheatBreaker.getInstance().getModuleManager().recoveredField1721
                                    || var11 != ((AbstractModule)var16).recoveredField3903
                              )) {
                              KeybindElement var12 = new KeybindElement(var11, var2);
                              var9.add(var12);
                              CheatBreaker.getInstance().getModuleManager().recoveredField1720.put(var11, var12);
                           }
                           break;
                        }

                        var9.add(new IconNumericSliderElement(var11, var2));
                     }
                     break;
                  case 5:
                     var9.add(new PotionSelectionElement(var11, var2));
                     break;
                  case 6:
                     if (((AbstractModule)var16).guiAnchor != null
                        || var16 == CheatBreaker.getInstance().getModuleManager().recoveredField1721
                        || var11 != ((AbstractModule)var16).recoveredField3908) {
                        var9.add(new CyclingChoiceElement(var11, var2));
                     }
                     break;
                  case 7:
                     if (var11.method_08911().endsWith("String") || var11.method_08911().contains("Background)")) {
                        var9.add(new com.cheatbreaker.client.ui.element.KeybindElement(var11, var2));
                     } else if (var11.method_08911().startsWith("Hot key") && var11.method_08867()) {
                        var9.add(new com.cheatbreaker.client.ui.element.KeybindElement(var11, var2));
                     } else if (var11.method_08911().equalsIgnoreCase("label")) {
                        var9.add(new SettingSectionElement(var11, var2));
                        if (CheatBreaker.getInstance().getModuleManager().recoveredField1721.recoveredField2710.getValue().equals(var11.getValue())) {
                           var9.add(new CrosshairPreviewElement(var2));
                        }
                     }
               }
            }

            if (((AbstractModule)var16).method_28787()) {
               var9.add(new KeybindElement(((StaffModule)var16).getKeybindSetting(), var2));
               if (var16 == CheatBreaker.getInstance().getModuleManager().xray) {
                  var9.add(new BlockSelectionElement(CheatBreaker.getInstance().getModuleManager().xray.method_01553(), "Blocks", var2));
               }
            }

            this.recoveredField1365.put(var16, var9);
         }
      }

      this.recoveredField1362 = new ArrayList<>();

      for (Setting var17 : CheatBreaker.getInstance().getGlobalSettings().recoveredField571) {
         GlobalSettings settings = CheatBreaker.getInstance().getGlobalSettings();
         if (var17 == settings.recoveredField541 || var17 == settings.recoveredField515) {
            continue;
         }
         switch (ModuleListElement$EnumSwitch.recoveredField991[var17.getType().ordinal()]) {
            case 1:
               this.recoveredField1362.add(new CyclingToggleElement(var17, var2));
               break;
            case 2:
            case 3:
            case 4:
               if (var17.getType().equals(Setting.Type.INTEGER) && var17.method_08911().toLowerCase().contains("color")) {
                  this.recoveredField1362.add(new ColorPickerElement(var17, var2));
               } else if (var17 == settings.friendListKeybind) {
                  this.recoveredField1362.add(new KeybindElement(var17, var2));
               } else if (var17 != CheatBreaker.getInstance().getGlobalSettings().getCrosshairSettingsLabel()) {
                  this.recoveredField1362.add(new IconNumericSliderElement(var17, var2));
               }
            case 5:
            default:
               break;
            case 6:
               this.recoveredField1362.add(new CyclingChoiceElement(var17, var2));
               break;
            case 7:
               if (var17.method_08911().equalsIgnoreCase("label")) {
                  this.recoveredField1362.add(new SettingSectionElement(var17, var2));
               }
         }
      }

      int var15 = 25;

      for (AbstractModulesGuiElement var19 : this.recoveredField1362) {
         var15 += var19.getHeight();
      }

      var20 = this.x + var5 - 120;
      this.recoveredField1357 = new ModulesGuiButtonElement(
         CheatBreaker.getInstance().recoveredField1595, null, "Apply to all text", var20, this.y + var15 + 4, 110, 28, -12418828, var2
      );
      var20 = this.x + var5 - 120;
      this.recoveredField1361 = new ModulesGuiButtonElement(
         CheatBreaker.getInstance().recoveredField1595, null, "Add Another", var20, this.y + var15 + 4, 110, 28, -13916106, var2, false, true
      );
   }

   public void method_13024(String var1, int var2) {
      for (String var6 : WordWrap.method_05899(var1).method_29697(85).method_29703(false).method_29693().split("\n")) {
         float var10002 = this.x + 38;
         float var10003 = (float)(this.y + 19) + this.recoveredField1369 * 9;
         CheatBreaker.getInstance().recoveredField1589.drawString(var6, var10002, var10003, var2);
         this.recoveredField1369++;
      }
   }

   @Override
   public boolean method_13022(Setting var1) {
      if (var1.method_08903() != null) {
         if (CheatBreaker.getInstance().getGlobalSettings().recoveredField584.getValue().equals("Simple")
            && !var1.method_08903().equals(SettingsDetailLevel.SIMPLE)) {
            return true;
         }

         if (CheatBreaker.getInstance().getGlobalSettings().recoveredField584.getValue().equals("Medium")
            && !var1.method_08903().equals(SettingsDetailLevel.SIMPLE)
            && !var1.method_08903().equals(SettingsDetailLevel.MEDIUM)) {
            return true;
         }
      }

      return var1.method_08921() == null ? false : !Boolean.valueOf(var1.method_08921().getAsBoolean());
   }

   @Override
   public void method_03200(AbstractModule var1) {
      Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
      this.recoveredField3012 = 0;
      this.recoveredField3010 = 0.0;
      this.yOffset = 0;
      this.module = var1;
      this.scrollable = null;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      if (this.module == null && !this.recoveredField1358) {
         if (!this.recoveredField1367.isMouseInside(var1, var2) || this.recoveredField1359) {
            for (ModuleSettingsElement var25 : this.recoveredField1360) {
               if (var25.isMouseInside(var1, var2) && this.method_03198(var25.module)) {
                  var25.handleMouseClick(var1, var2, var3);
               }
            }
         } else {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.recoveredField1358 = true;
            this.recoveredField3012 = 0;
            this.recoveredField3010 = 0.0;
            this.yOffset = 0;
         }
      } else if (!this.recoveredField1366.isMouseInside(var1, var2)) {
         if (this.recoveredField1368.isMouseInside(var1, var2)) {
            ModuleManager var4 = CheatBreaker.getInstance().getModuleManager();
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            if (this.module == CheatBreaker.getInstance().getModuleManager().recoveredField1701) {
               this.module.getSettingsList().removeIf(var0 -> var0.method_08911().toLowerCase().startsWith("hot key"));
               ((AutoTextModule)this.module).recoveredField1864 = 1;
               CheatBreaker.getInstance().method_19818().method_04443(var4.recoveredField1695);
               return;
            }

            for (int var5 = 0; var5 < this.module.getSettingsList().size(); var5++) {
               try {
                  Setting var6 = this.module.getSettingsList().get(var5);
                  if (this.module.getSettingsList().get(var5).method_08867() || this.module.getSettingsList().get(var5).method_08879()) {
                     var6.method_08885(false);
                     var6.method_08887(0);
                  }

                  var6.setValue(this.module.method_28782().get(var5), false);
               } catch (Exception var16) {
                  var16.printStackTrace();
               }
            }

            CheatBreaker.getInstance().method_19818().method_04443(var4.recoveredField1695);
         }

         if (this.recoveredField1364.isMouseInside(var1, var2)) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.module.setTranslations(this.module.defaultXTranslation, this.module.defaultYTranslation);
            this.module.method_28819(this.module.method_28830());
         }

         if (this.recoveredField1361.isMouseInside(var1, var2) && this.module == CheatBreaker.getInstance().getModuleManager().recoveredField1701) {
            CheatBreaker.getInstance().getModuleManager().recoveredField1701.method_26046();
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            CheatBreaker.getInstance().method_19818().method_04443(CheatBreaker.getInstance().getModuleManager().recoveredField1695);
         }

         if (this.module != null && this.recoveredField1365.containsKey(this.module)) {
            for (AbstractModulesGuiElement var24 : this.recoveredField1365.get(this.module)) {
               if (var24.isMouseInside(var1, var2) && !var24.method_13022(var24.setting)) {
                  var24.handleMouseClick(var1, var2, var3);
               }
            }
         } else if (this.recoveredField1358) {
            if (this.recoveredField1357.isMouseInside(var1, var2)) {
               for (AbstractModule var22 : CheatBreaker.getInstance().getModuleManager().recoveredField1706) {
                  for (Setting var7 : var22.getSettingsList()) {
                     if (var7.getType() == Setting.Type.INTEGER
                        && var7.method_08911().toLowerCase().contains("color")
                        && !var7.method_08911().toLowerCase().contains("background")) {
                        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
                        var7.setValue(CheatBreaker.getInstance().getGlobalSettings().recoveredField536.method_08901());
                     }
                  }
               }
            } else {
               for (AbstractModulesGuiElement var23 : this.recoveredField1362) {
                  if (var23.isMouseInside(var1, var2) && !var23.method_13022(var23.setting)) {
                     var23.handleMouseClick(var1, var2, var3);
                  }
               }
            }
         }
      } else {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.module = null;
         this.recoveredField1358 = false;
         if (this.scrollable != null) {
            CBModulesGui.instance.currentScrollableElement = this.scrollable;
         }
      }

      double var21 = this.height - 10;
      double var27 = this.recoveredField3009;
      double var8 = var21 / var27 * 100.0;
      double var10 = var21 / 100.0 * var8;
      double var12 = this.recoveredField3012 / 100.0 * var8;
      boolean var14 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11 - var12) * this.scale
         && var2 < (this.y + 8 + var10 - var12) * this.scale;
      boolean var15 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11) * this.scale
         && var2 < (this.y + 6 + var21 - 3.0) * this.scale;
      if (var3 == 0 && var15 || var14) {
         this.recoveredField3014 = true;
      }
   }

   public void method_13023(String var1) {
      this.method_13024(
         var1, GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1667 : GuiThemeColors.recoveredField1670
      );
   }

   @Override
   public boolean method_03198(AbstractModule var1) {
      return !var1.getSettingsList().isEmpty() || var1.getName().contains("Zans");
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      RenderUtil.method_22054(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.height + 2,
         8.0,
         GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1664 : GuiThemeColors.recoveredField1647
      );
      this.preDraw(var1, var2);
      CheatBreaker.getInstance().getModuleManager().method_21666(this.recoveredField3012);
      if (this.module == null && !this.recoveredField1358) {
         int var9 = CheatBreaker.getInstance().getModuleManager().recoveredField1706.size() - 39;
         int var11 = 65;

         for (int var13 = 0; var13 < var9; var13 += 5) {
            if (var9 > 10) {
               var11 = (int)(var11 + var9 / 1.8);
            } else {
               var11 += 10;
            }
         }

         this.recoveredField3009 = (int)(CheatBreaker.getInstance().getModuleManager().recoveredField1706.size() * 0.5) + var11;
         if (!this.recoveredField1359) {
            this.recoveredField1367.setDimensions(this.x + 4, this.y + 4, this.width - 12, 18);
            this.recoveredField1367.yOffset = this.recoveredField3012;
            this.recoveredField1367.handleDrawElement(var1, var2, var3);
            this.recoveredField3009 = this.recoveredField3009 + this.recoveredField1367.getHeight();
         }

         for (int var14 = 0; var14 < this.recoveredField1360.size(); var14++) {
            ModuleSettingsElement var7 = this.recoveredField1360.get(var14);
            var7.setDimensions(this.x + 4, this.y + (this.recoveredField1359 ? 4 : 24) + var14 * 20, this.width - 12, 18);
            var7.yOffset = this.recoveredField3012;
            var7.handleDrawElement(var1, var2, var3);
            this.recoveredField3009 = this.recoveredField3009 + var7.getHeight();
         }
      } else if (this.recoveredField1358 && !this.recoveredField1359) {
         Gui.a(
            this.x + 32,
            this.y + 4,
            this.x + 33,
            this.y + Math.max(this.height, this.recoveredField3009) - 4,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1652 : GuiThemeColors.recoveredField1674
         );
         this.recoveredField3009 = 25;
         this.recoveredField1366.setDimensions(this.x + 2, this.y + 2, 28, 28);
         this.recoveredField1366.yOffset = this.recoveredField3012;
         this.recoveredField1366.handleDrawElement(var1, var2, var3);
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawString(
               "CheatBreaker Settings".toUpperCase(),
               this.x + 38,
               this.y + 6,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1669 : GuiThemeColors.recoveredField1659
            );
         Gui.a(
            this.x + 38,
            this.y + 17,
            this.x + this.width - 6,
            this.y + 18,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1652 : GuiThemeColors.recoveredField1674
         );
         int var8 = 0;

         for (AbstractModulesGuiElement var12 : this.recoveredField1362) {
            if (var12.setting == null || !var12.method_13022(var12.setting)) {
               var12.setDimensions(this.x + 38, this.y + 22 + var8, this.width - 40, var12.getHeight());
               var12.yOffset = this.recoveredField3012;
               var12.handleDrawElement(var1, var2, var3);
               var8 += 2 + var12.getHeight();
               this.recoveredField3009 = this.recoveredField3009 + 2 + var12.getHeight();
            }
         }

         this.recoveredField1357.yOffset = this.recoveredField3012;
         this.recoveredField1357.setDimensions(this.x + this.width - 118, this.y + this.recoveredField3009, 100, 20);
         this.recoveredField1357.handleDrawElement(var1, var2, var3);
         this.recoveredField3009 += 24;
      } else {
         Gui.a(
            this.x + 32,
            this.y + 4,
            this.x + 33,
            this.y + Math.max(this.height, this.recoveredField3009) - 4,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1652 : GuiThemeColors.recoveredField1674
         );
         this.recoveredField3009 = 37;
         this.recoveredField1366.setDimensions(this.x + 2, this.y + 2, 28, 28);
         this.recoveredField1366.yOffset = this.recoveredField3012;
         this.recoveredField1366.handleDrawElement(var1, var2, var3);
         this.recoveredField1368.setDimensions(this.x + this.width - 80, this.y + 7, 70, 10);
         this.recoveredField1368.yOffset = this.recoveredField3012;
         this.recoveredField1368.handleDrawElement(var1, var2, var3);
         if (this.module.getGuiAnchor() != null) {
            this.recoveredField1364.setDimensions(this.x + this.width - 150, this.y + 7, 70, 10);
            this.recoveredField1364.yOffset = this.recoveredField3012;
            this.recoveredField1364.handleDrawElement(var1, var2, var3);
         }

         CheatBreaker.getInstance().getModuleManager().method_21674(this.module.getName());
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawString(
               (this.module.getName() + " Settings").toUpperCase(),
               this.x + 38,
               this.y + 6,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1669 : GuiThemeColors.recoveredField1659
            );
         Gui.a(
            this.x + 38,
            this.y + 17,
            this.x + this.width - 12,
            this.y + 18,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1652 : GuiThemeColors.recoveredField1674
         );
         this.recoveredField1369 = 0;
         if (this.module.method_28809() != null) {
            this.method_13023(this.module.method_28809());
         }

         if (this.module.method_28808() != null) {
            this.method_13023("Original creator" + (this.module.method_28808().size() != 1 ? "s: " : ": ") + String.join(", ", this.module.method_28808()));
         }

         if (this.module.method_28839() != null) {
            this.method_13023("Also known as: " + String.join(", ", this.module.method_28839()));
         }

         if (this.module.method_28796() && this.module.method_28784() != null) {
            this.method_13024(
               this.module.getName() + " is currently restricted:\n" + this.module.method_28784(),
               GlobalSettings.recoveredField529.method_08908() ? -43691 : -5636096
            );
         }

         if (this.recoveredField1369 != 0) {
            Gui.a(
               this.x + 38,
               this.y + 21 + this.recoveredField1369 * 9,
               this.x + this.width - 12,
               this.y + 22 + this.recoveredField1369 * 9,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1652 : GuiThemeColors.recoveredField1674
            );
         }

         if (this.module == CheatBreaker.getInstance().getModuleManager().minmap) {
            this.postDraw(var1, var2);
            return;
         }

         if (this.module.getSettingsList().isEmpty()) {
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawString(
                  (this.module.getName().toUpperCase() + " DOES NOT HAVE ANY OPTIONS.").toUpperCase(),
                  this.x + 38,
                  this.y + 26 + this.recoveredField1369 * 9,
                  GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
               );
         }

         int var4 = 0;

         for (AbstractModulesGuiElement var6 : this.recoveredField1365.get(this.module)) {
            if (var6.setting == null || !var6.method_13022(var6.setting)) {
               var6.setDimensions(this.x + 38, this.y + 26 + this.recoveredField1369 * 9 + var4, this.width - 40, var6.getHeight());
               var6.yOffset = this.recoveredField3012;
               var6.handleDrawElement(var1, var2, var3);
               var4 += 2 + var6.getHeight();
               this.recoveredField3009 = this.recoveredField3009 + 2 + var6.getHeight();
            }
         }

         this.recoveredField3009 = this.recoveredField3009 + (9 * this.recoveredField1369 - 9);
      }

      if (this.module == CheatBreaker.getInstance().getModuleManager().recoveredField1701) {
         this.recoveredField1361.yOffset = this.recoveredField3012;
         this.recoveredField1361.setDimensions(this.x + this.width - 118, this.y + this.recoveredField3009 + 5, 100, 18);
         this.recoveredField1361.handleDrawElement(var1, var2, var3);
         this.recoveredField3009 += 24;
      }

      this.postDraw(var1, var2);
   }
}
