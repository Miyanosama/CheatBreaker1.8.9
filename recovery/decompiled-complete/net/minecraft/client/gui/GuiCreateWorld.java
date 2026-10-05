package net.minecraft.client.gui;

import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.util.internal.chmv8.ForkJoinTask$AdaptedCallable;
import java.util.Random;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

public class GuiCreateWorld extends GuiScreen {
   public boolean field_0013;
   public boolean generateStructuresEnabled;
   public GuiButton btnMapFeatures;
   public ForkJoinTask$AdaptedCallable field_0023;
   public GuiButton btnBonusItems;
   public String worldSeed;
   public FixedLengthFrameDecoder field_0027;
   public GuiButton btnGameMode;
   public NBTTagIntArray field_0008;
   public int selectedIndex;
   public GuiTextField worldSeedField;
   public static String[] disallowedFilenames = new String[]{
      "CON",
      "COM",
      "PRN",
      "AUX",
      "CLOCK$",
      "NUL",
      "COM1",
      "COM2",
      "COM3",
      "COM4",
      "COM5",
      "COM6",
      "COM7",
      "COM8",
      "COM9",
      "LPT1",
      "LPT2",
      "LPT3",
      "LPT4",
      "LPT5",
      "LPT6",
      "LPT7",
      "LPT8",
      "LPT9"
   };
   public String gameModeDesc2;
   public boolean field_0010;
   public GuiButton btnMapType;
   public boolean field_0025;
   public boolean inMoreWorldOptionsDisplay;
   public GuiButton btnMoreOptions;
   public String worldName;
   public GuiScreen parentScreen;
   public String gameModeDesc1;
   public String savedGameMode;
   public String gameMode = "survival";
   public String saveDirName;
   public GuiButton btnCustomizeType;
   public GuiTextField worldNameField;
   public boolean allowCheats;
   public String chunkProviderSettingsJson;
   public GuiButton btnAllowCommands;
   public boolean field_0018;

   public GuiCreateWorld(GuiScreen var1) {
      this.generateStructuresEnabled = true;
      this.chunkProviderSettingsJson = "";
      this.parentScreen = var1;
      this.worldSeed = "";
      this.worldName = I18n.format("selectWorld.newWorld");
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.n.clear();
      this.n.add(new GuiButton(0, this.l / 2 - 155, this.m - 28, 150, 20, I18n.format("selectWorld.create")));
      this.n.add(new GuiButton(1, this.l / 2 + 5, this.m - 28, 150, 20, I18n.format("gui.cancel")));
      this.n.add(this.btnGameMode = new GuiButton(2, this.l / 2 - 75, 115, 150, 20, I18n.format("selectWorld.gameMode")));
      this.n.add(this.btnMoreOptions = new GuiButton(3, this.l / 2 - 75, 187, 150, 20, I18n.format("selectWorld.moreWorldOptions")));
      this.n.add(this.btnMapFeatures = new GuiButton(4, this.l / 2 - 155, 100, 150, 20, I18n.format("selectWorld.mapFeatures")));
      this.btnMapFeatures.m = false;
      this.n.add(this.btnBonusItems = new GuiButton(7, this.l / 2 + 5, 151, 150, 20, I18n.format("selectWorld.bonusItems")));
      this.btnBonusItems.m = false;
      this.n.add(this.btnMapType = new GuiButton(5, this.l / 2 + 5, 100, 150, 20, I18n.format("selectWorld.mapType")));
      this.btnMapType.m = false;
      this.n.add(this.btnAllowCommands = new GuiButton(6, this.l / 2 - 155, 151, 150, 20, I18n.format("selectWorld.allowCommands")));
      this.btnAllowCommands.m = false;
      this.n.add(this.btnCustomizeType = new GuiButton(8, this.l / 2 + 5, 120, 150, 20, I18n.format("selectWorld.customizeType")));
      this.btnCustomizeType.m = false;
      this.worldNameField = new GuiTextField(9, this.q, this.l / 2 - 100, 60, 200, 20);
      this.worldNameField.setFocused(true);
      this.worldNameField.setText(this.worldName);
      this.worldSeedField = new GuiTextField(10, this.q, this.l / 2 - 100, 60, 200, 20);
      this.worldSeedField.setText(this.worldSeed);
      this.showMoreWorldOptions(this.inMoreWorldOptionsDisplay);
      this.calcSaveDirName();
      this.updateDisplayState();
   }

   public void updateDisplayState() {
      this.btnGameMode.j = I18n.format("selectWorld.gameMode") + ": " + I18n.format("selectWorld.gameMode." + this.gameMode);
      this.gameModeDesc1 = I18n.format("selectWorld.gameMode." + this.gameMode + ".line1");
      this.gameModeDesc2 = I18n.format("selectWorld.gameMode." + this.gameMode + ".line2");
      this.btnMapFeatures.j = I18n.format("selectWorld.mapFeatures") + " ";
      if (this.generateStructuresEnabled) {
         this.btnMapFeatures.j = this.btnMapFeatures.j + I18n.format("options.on");
      } else {
         this.btnMapFeatures.j = this.btnMapFeatures.j + I18n.format("options.off");
      }

      this.btnBonusItems.j = I18n.format("selectWorld.bonusItems") + " ";
      if (this.field_0025 && !this.field_0013) {
         this.btnBonusItems.j = this.btnBonusItems.j + I18n.format("options.on");
      } else {
         this.btnBonusItems.j = this.btnBonusItems.j + I18n.format("options.off");
      }

      this.btnMapType.j = I18n.format("selectWorld.mapType") + " " + I18n.format(WorldType.worldTypes[this.selectedIndex].getTranslateName());
      this.btnAllowCommands.j = I18n.format("selectWorld.allowCommands") + " ";
      if (this.allowCheats && !this.field_0013) {
         this.btnAllowCommands.j = this.btnAllowCommands.j + I18n.format("options.on");
      } else {
         this.btnAllowCommands.j = this.btnAllowCommands.j + I18n.format("options.off");
      }
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   public static String getUncollidingSaveDirName(ISaveFormat var0, String var1) {
      var1 = var1.replaceAll("[\\./\"]", "_");

      for (String var5 : disallowedFilenames) {
         if (var1.equalsIgnoreCase(var5)) {
            var1 = "_" + var1 + "_";
         }
      }

      while (var0.getWorldInfo(var1) != null) {
         var1 = var1 + "-";
      }

      return var1;
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      if (this.inMoreWorldOptionsDisplay) {
         this.worldSeedField.mouseClicked(var1, var2, var3);
      } else {
         this.worldNameField.mouseClicked(var1, var2, var3);
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, I18n.format("selectWorld.create"), this.l / 2, 20, -1);
      if (this.inMoreWorldOptionsDisplay) {
         this.drawString(this.q, I18n.format("selectWorld.enterSeed"), this.l / 2 - 100, 47, -6250336);
         this.drawString(this.q, I18n.format("selectWorld.seedInfo"), this.l / 2 - 100, 85, -6250336);
         if (this.btnMapFeatures.m) {
            this.drawString(this.q, I18n.format("selectWorld.mapFeatures.info"), this.l / 2 - 150, 122, -6250336);
         }

         if (this.btnAllowCommands.m) {
            this.drawString(this.q, I18n.format("selectWorld.allowCommands.info"), this.l / 2 - 150, 172, -6250336);
         }

         this.worldSeedField.drawTextBox();
         if (WorldType.worldTypes[this.selectedIndex].showWorldInfoNotice()) {
            this.q
               .drawSplitString(
                  I18n.format(WorldType.worldTypes[this.selectedIndex].getTranslatedInfo()),
                  this.btnMapType.h + 2,
                  this.btnMapType.i + 22,
                  this.btnMapType.getButtonWidth(),
                  10526880
               );
         }
      } else {
         this.drawString(this.q, I18n.format("selectWorld.enterName"), this.l / 2 - 100, 47, -6250336);
         this.drawString(this.q, I18n.format("selectWorld.resultFolder") + " " + this.saveDirName, this.l / 2 - 100, 85, -6250336);
         this.worldNameField.drawTextBox();
         this.drawString(this.q, this.gameModeDesc1, this.l / 2 - 100, 137, -6250336);
         this.drawString(this.q, this.gameModeDesc2, this.l / 2 - 100, 149, -6250336);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void showMoreWorldOptions(boolean var1) {
      this.inMoreWorldOptionsDisplay = var1;
      if (WorldType.worldTypes[this.selectedIndex] == WorldType.DEBUG_WORLD) {
         this.btnGameMode.m = !this.inMoreWorldOptionsDisplay;
         this.btnGameMode.l = false;
         if (this.savedGameMode == null) {
            this.savedGameMode = this.gameMode;
         }

         this.gameMode = "spectator";
         this.btnMapFeatures.m = false;
         this.btnBonusItems.m = false;
         this.btnMapType.m = this.inMoreWorldOptionsDisplay;
         this.btnAllowCommands.m = false;
         this.btnCustomizeType.m = false;
      } else {
         this.btnGameMode.m = !this.inMoreWorldOptionsDisplay;
         this.btnGameMode.l = true;
         if (this.savedGameMode != null) {
            this.gameMode = this.savedGameMode;
            this.savedGameMode = null;
         }

         this.btnMapFeatures.m = this.inMoreWorldOptionsDisplay && WorldType.worldTypes[this.selectedIndex] != WorldType.CUSTOMIZED;
         this.btnBonusItems.m = this.inMoreWorldOptionsDisplay;
         this.btnMapType.m = this.inMoreWorldOptionsDisplay;
         this.btnAllowCommands.m = this.inMoreWorldOptionsDisplay;
         this.btnCustomizeType.m = this.inMoreWorldOptionsDisplay
            && (WorldType.worldTypes[this.selectedIndex] == WorldType.FLAT || WorldType.worldTypes[this.selectedIndex] == WorldType.CUSTOMIZED);
      }

      this.updateDisplayState();
      if (this.inMoreWorldOptionsDisplay) {
         this.btnMoreOptions.j = I18n.format("gui.done");
      } else {
         this.btnMoreOptions.j = I18n.format("selectWorld.moreWorldOptions");
      }
   }

   @Override
   public void updateScreen() {
      this.worldNameField.updateCursorCounter();
      this.worldSeedField.updateCursorCounter();
   }

   public boolean canSelectCurWorldType() {
      WorldType var1 = WorldType.worldTypes[this.selectedIndex];
      return var1 != null && var1.getCanBeCreated() ? (var1 == WorldType.DEBUG_WORLD ? isShiftKeyDown() : true) : false;
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 1) {
            this.j.displayGuiScreen(this.parentScreen);
         } else if (var1.k == 0) {
            this.j.displayGuiScreen((GuiScreen)null);
            if (this.field_0010) {
               return;
            }

            this.field_0010 = true;
            long var2 = new Random().nextLong();
            String var4 = this.worldSeedField.getText();
            if (!StringUtils.isEmpty(var4)) {
               try {
                  long var5 = Long.parseLong(var4);
                  if (var5 != (6683608936033419635L & -6683608936637521912L)) {
                     var2 = var5;
                  }
               } catch (NumberFormatException var7) {
                  var2 = var4.hashCode();
               }
            }

            WorldSettings$GameType var8 = WorldSettings$GameType.getByName(this.gameMode);
            WorldSettings var6 = new WorldSettings(var2, var8, this.generateStructuresEnabled, this.field_0013, WorldType.worldTypes[this.selectedIndex]);
            var6.setWorldName(this.chunkProviderSettingsJson);
            if (this.field_0025 && !this.field_0013) {
               var6.enableBonusChest();
            }

            if (this.allowCheats && !this.field_0013) {
               var6.method_26032();
            }

            this.j.launchIntegratedServer(this.saveDirName, this.worldNameField.getText().trim(), var6);
         } else if (var1.k == 3) {
            this.toggleMoreWorldOptions();
         } else if (var1.k == 2) {
            if (this.gameMode.equals("survival")) {
               if (!this.field_0018) {
                  this.allowCheats = false;
               }

               this.field_0013 = false;
               this.gameMode = "hardcore";
               this.field_0013 = true;
               this.btnAllowCommands.l = false;
               this.btnBonusItems.l = false;
               this.updateDisplayState();
            } else if (this.gameMode.equals("hardcore")) {
               if (!this.field_0018) {
                  this.allowCheats = true;
               }

               this.field_0013 = false;
               this.gameMode = "creative";
               this.updateDisplayState();
               this.field_0013 = false;
               this.btnAllowCommands.l = true;
               this.btnBonusItems.l = true;
            } else {
               if (!this.field_0018) {
                  this.allowCheats = false;
               }

               this.gameMode = "survival";
               this.updateDisplayState();
               this.btnAllowCommands.l = true;
               this.btnBonusItems.l = true;
               this.field_0013 = false;
            }

            this.updateDisplayState();
         } else if (var1.k == 4) {
            this.generateStructuresEnabled = !this.generateStructuresEnabled;
            this.updateDisplayState();
         } else if (var1.k == 7) {
            this.field_0025 = !this.field_0025;
            this.updateDisplayState();
         } else if (var1.k == 5) {
            this.selectedIndex++;
            if (this.selectedIndex >= WorldType.worldTypes.length) {
               this.selectedIndex = 0;
            }

            while (!this.canSelectCurWorldType()) {
               this.selectedIndex++;
               if (this.selectedIndex >= WorldType.worldTypes.length) {
                  this.selectedIndex = 0;
               }
            }

            this.chunkProviderSettingsJson = "";
            this.updateDisplayState();
            this.showMoreWorldOptions(this.inMoreWorldOptionsDisplay);
         } else if (var1.k == 6) {
            this.field_0018 = true;
            this.allowCheats = !this.allowCheats;
            this.updateDisplayState();
         } else if (var1.k == 8) {
            if (WorldType.worldTypes[this.selectedIndex] == WorldType.FLAT) {
               this.j.displayGuiScreen(new GuiCreateFlatWorld(this, this.chunkProviderSettingsJson));
            } else {
               this.j.displayGuiScreen(new GuiCustomizeWorldScreen(this, this.chunkProviderSettingsJson));
            }
         }
      }
   }

   public void calcSaveDirName() {
      this.saveDirName = this.worldNameField.getText().trim();

      for (char var4 : ChatAllowedCharacters.allowedCharactersArray) {
         this.saveDirName = this.saveDirName.replace(var4, '_');
      }

      if (StringUtils.isEmpty(this.saveDirName)) {
         this.saveDirName = "World";
      }

      this.saveDirName = getUncollidingSaveDirName(this.j.getSaveLoader(), this.saveDirName);
   }

   @Override
   public void keyTyped(char var1, int var2) {
      if (this.worldNameField.isFocused() && !this.inMoreWorldOptionsDisplay) {
         this.worldNameField.textboxKeyTyped(var1, var2);
         this.worldName = this.worldNameField.getText();
      } else if (this.worldSeedField.isFocused() && this.inMoreWorldOptionsDisplay) {
         this.worldSeedField.textboxKeyTyped(var1, var2);
         this.worldSeed = this.worldSeedField.getText();
      }

      if (var2 == 28 || var2 == 156) {
         this.actionPerformed(this.n.get(0));
      }

      this.n.get(0).l = this.worldNameField.getText().length() > 0;
      this.calcSaveDirName();
   }

   public void recreateFromExistingWorld(WorldInfo var1) {
      this.worldName = I18n.format("selectWorld.newWorld.copyOf", var1.getWorldName());
      this.worldSeed = var1.getSeed() + "";
      this.selectedIndex = var1.getTerrainType().getWorldTypeID();
      this.chunkProviderSettingsJson = var1.getGeneratorOptions();
      this.generateStructuresEnabled = var1.isMapFeaturesEnabled();
      this.allowCheats = var1.areCommandsAllowed();
      if (var1.isHardcoreModeEnabled()) {
         this.gameMode = "hardcore";
      } else if (var1.getGameType().isSurvivalOrAdventure()) {
         this.gameMode = "survival";
      } else if (var1.getGameType().isCreative()) {
         this.gameMode = "creative";
      }
   }

   public void toggleMoreWorldOptions() {
      this.showMoreWorldOptions(!this.inMoreWorldOptionsDisplay);
   }
}
