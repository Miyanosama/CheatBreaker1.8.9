package net.minecraft.client.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiSelectWorld extends GuiScreen implements GuiYesNoCallback {
   public DateFormat field_146633_h = new SimpleDateFormat();
   public boolean field_146634_i;
   public String field_146636_v;
   public GuiButton recreateButton;
   public int selectedIndex;
   public String[] field_146635_w;
   public GuiSelectWorld.List availableWorlds;
   public GuiButton selectButton;
   public static Logger logger = LogManager.getLogger();
   public String field_146637_u;
   public GuiButton deleteButton;
   public GuiScreen parentScreen;
   public GuiButton renameButton;
   public boolean confirmingDelete;
   public java.util.List<SaveFormatComparator> field_146639_s;
   public String screenTitle = "Select world";

   @Override
   public void confirmClicked(boolean var1, int var2) {
      if (this.confirmingDelete) {
         this.confirmingDelete = false;
         if (var1) {
            ISaveFormat var3 = this.j.getSaveLoader();
            var3.flushCache();
            var3.deleteWorldDirectory(this.func_146621_a(var2));

            try {
               this.loadLevelList();
            } catch (AnvilConverterException var5) {
               logger.error("Couldn't load level list", var5);
            }
         }

         this.j.displayGuiScreen(this);
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k == 2) {
            String var2 = this.func_146614_d(this.selectedIndex);
            if (var2 != null) {
               this.confirmingDelete = true;
               GuiYesNo var3 = makeDeleteWorldYesNo(this, var2, this.selectedIndex);
               this.j.displayGuiScreen(var3);
            }
         } else if (var1.k == 1) {
            this.func_146615_e(this.selectedIndex);
         } else if (var1.k == 3) {
            this.j.displayGuiScreen(new GuiCreateWorld(this));
         } else if (var1.k == 6) {
            this.j.displayGuiScreen(new GuiRenameWorld(this, this.func_146621_a(this.selectedIndex)));
         } else if (var1.k == 0) {
            this.j.displayGuiScreen(this.parentScreen);
         } else if (var1.k == 7) {
            GuiCreateWorld var5 = new GuiCreateWorld(this);
            ISaveHandler var6 = this.j.getSaveLoader().getSaveLoader(this.func_146621_a(this.selectedIndex), false);
            WorldInfo var4 = var6.loadWorldInfo();
            var6.flush();
            var5.recreateFromExistingWorld(var4);
            this.j.displayGuiScreen(var5);
         } else {
            this.availableWorlds.actionPerformed(var1);
         }
      }
   }

   public void loadLevelList() throws net.minecraft.client.AnvilConverterException {
      ISaveFormat var1 = this.j.getSaveLoader();
      this.field_146639_s = var1.getSaveList();
      Collections.sort(this.field_146639_s);
      this.selectedIndex = -1;
   }

   public static GuiYesNo makeDeleteWorldYesNo(GuiYesNoCallback var0, String var1, int var2) {
      String var3 = I18n.format("selectWorld.deleteQuestion");
      String var4 = "'" + var1 + "' " + I18n.format("selectWorld.deleteWarning");
      String var5 = I18n.format("selectWorld.deleteButton");
      String var6 = I18n.format("gui.cancel");
      return new GuiYesNo(var0, var3, var4, var5, var6, var2);
   }

   public String func_146614_d(int var1) {
      String var2 = this.field_146639_s.get(var1).getDisplayName();
      if (StringUtils.isEmpty(var2)) {
         var2 = I18n.format("selectWorld.world") + " " + (var1 + 1);
      }

      return var2;
   }

   @Override
   public void initGui() {
      this.screenTitle = I18n.format("selectWorld.title");

      try {
         this.loadLevelList();
      } catch (AnvilConverterException var2) {
         logger.error("Couldn't load level list", var2);
         this.j.displayGuiScreen(new GuiErrorScreen("Unable to load worlds", var2.getMessage()));
         return;
      }

      this.field_146637_u = I18n.format("selectWorld.world");
      this.field_146636_v = I18n.format("selectWorld.conversion");
      this.field_146635_w[WorldSettings.GameType.SURVIVAL.getID()] = I18n.format("gameMode.survival");
      this.field_146635_w[WorldSettings.GameType.CREATIVE.getID()] = I18n.format("gameMode.creative");
      this.field_146635_w[WorldSettings.GameType.ADVENTURE.getID()] = I18n.format("gameMode.adventure");
      this.field_146635_w[WorldSettings.GameType.SPECTATOR.getID()] = I18n.format("gameMode.spectator");
      this.availableWorlds = new GuiSelectWorld.List(this.j);
      this.availableWorlds.registerScrollButtons(4, 5);
      this.addWorldSelectionButtons();
   }

   public String func_146621_a(int var1) {
      return this.field_146639_s.get(var1).getFileName();
   }

   public void func_146615_e(int var1) {
      this.j.displayGuiScreen((GuiScreen)null);
      if (!this.field_146634_i) {
         this.field_146634_i = true;
         String var2 = this.func_146621_a(var1);
         if (var2 == null) {
            var2 = "World" + var1;
         }

         String var3 = this.func_146614_d(var1);
         if (var3 == null) {
            var3 = "World" + var1;
         }

         if (this.j.getSaveLoader().canLoadWorld(var2)) {
            this.j.launchIntegratedServer(var2, var3, (WorldSettings)null);
         }
      }
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.availableWorlds.handleMouseInput();
   }

   public void addWorldSelectionButtons() {
      this.n.add(this.selectButton = new GuiButton(1, this.l / 2 - 154, this.m - 52, 150, 20, I18n.format("selectWorld.select")));
      this.n.add(new GuiButton(3, this.l / 2 + 4, this.m - 52, 150, 20, I18n.format("selectWorld.create")));
      this.n.add(this.renameButton = new GuiButton(6, this.l / 2 - 154, this.m - 28, 72, 20, I18n.format("selectWorld.rename")));
      this.n.add(this.deleteButton = new GuiButton(2, this.l / 2 - 76, this.m - 28, 72, 20, I18n.format("selectWorld.delete")));
      this.n.add(this.recreateButton = new GuiButton(7, this.l / 2 + 4, this.m - 28, 72, 20, I18n.format("selectWorld.recreate")));
      this.n.add(new GuiButton(0, this.l / 2 + 82, this.m - 28, 72, 20, I18n.format("gui.cancel")));
      this.selectButton.l = false;
      this.deleteButton.l = false;
      this.renameButton.l = false;
      this.recreateButton.l = false;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.availableWorlds.a(var1, var2, var3);
      this.drawCenteredString(this.q, this.screenTitle, this.l / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   public GuiSelectWorld(GuiScreen var1) {
      this.field_146635_w = new String[4];
      this.parentScreen = var1;
   }

   public class List extends GuiSlot {
      @Override
      public int getSize() {
         return GuiSelectWorld.this.field_146639_s.size();
      }

      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         SaveFormatComparator var7 = GuiSelectWorld.this.field_146639_s.get(var1);
         String var8 = var7.getDisplayName();
         if (StringUtils.isEmpty(var8)) {
            var8 = GuiSelectWorld.this.field_146637_u + " " + (var1 + 1);
         }

         String var9 = var7.getFileName();
         var9 = var9 + " (" + GuiSelectWorld.this.field_146633_h.format(new Date(var7.getLastTimePlayed()));
         var9 = var9 + ")";
         String var10 = "";
         if (var7.requiresConversion()) {
            var10 = GuiSelectWorld.this.field_146636_v + " " + var10;
         } else {
            var10 = GuiSelectWorld.this.field_146635_w[var7.getEnumGameType().getID()];
            if (var7.isHardcoreModeEnabled()) {
               var10 = EnumChatFormatting.DARK_RED + I18n.format("gameMode.hardcore") + EnumChatFormatting.RESET;
            }

            if (var7.getCheatsEnabled()) {
               var10 = var10 + ", " + I18n.format("selectWorld.cheats");
            }
         }

         GuiSelectWorld.this.drawString(GuiSelectWorld.this.q, var8, var2 + 2, var3 + 1, 16777215);
         GuiSelectWorld.this.drawString(GuiSelectWorld.this.q, var9, var2 + 2, var3 + 12, 8421504);
         GuiSelectWorld.this.drawString(GuiSelectWorld.this.q, var10, var2 + 2, var3 + 12 + 10, 8421504);
      }

      @Override
      public int getContentHeight() {
         return GuiSelectWorld.this.field_146639_s.size() * 36;
      }

      @Override
      public boolean isSelected(int var1) {
         return var1 == GuiSelectWorld.this.selectedIndex;
      }

      @Override
      public void elementClicked(int var1, boolean var2, int var3, int var4) {
         GuiSelectWorld.this.selectedIndex = var1;
         boolean var5 = GuiSelectWorld.this.selectedIndex >= 0 && GuiSelectWorld.this.selectedIndex < this.getSize();
         GuiSelectWorld.this.selectButton.l = var5;
         GuiSelectWorld.this.deleteButton.l = var5;
         GuiSelectWorld.this.renameButton.l = var5;
         GuiSelectWorld.this.recreateButton.l = var5;
         if (var2 && var5) {
            GuiSelectWorld.this.func_146615_e(var1);
         }
      }

      @Override
      public void drawBackground() {
         GuiSelectWorld.this.drawDefaultBackground();
      }

      public List(Minecraft var2) {
         super(var2, GuiSelectWorld.this.l, GuiSelectWorld.this.m, 32, GuiSelectWorld.this.m - 64, 36);
      }
   }
}
