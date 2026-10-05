package com.cheatbreaker.client.ui.resourcepack;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.google.common.collect.Lists;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolderEntry;
import com.cheatbreaker.client.util.AsyncExecutor;
import com.cheatbreaker.client.ui.resourcepack.SelectedResourcePackEntry;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolderScanner;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolder;
import com.cheatbreaker.client.ui.resourcepack.RecursiveDirectoryWatcher;

public class ResourcePackGui extends GuiScreen {
   public static Comparator<ResourcePackRepository.Entry> recoveredField855 = (var0, var1) -> StringUtils.stripControlCodes(var0.getResourcePackName())
      .trim()
      .compareToIgnoreCase(StringUtils.stripControlCodes(var1.getResourcePackName()).trim());
   public List<ResourcePackRepository.Entry> recoveredField856;
   public List<ResourcePackRepository.Entry> recoveredField857;
   public GuiButton recoveredField858;
   public SelectedResourcePackEntry recoveredField859;
   public ResourcePackFolder recoveredField860;
   public List<ResourcePackFolder> recoveredField861;
   public RecursiveDirectoryWatcher recoveredField862;
   public ResourcePackRepository recoveredField863;
   public static Comparator<ResourcePackFolder> recoveredField864 = (var0, var1) -> StringUtils.stripControlCodes(var0.method_26764())
      .trim()
      .compareToIgnoreCase(StringUtils.stripControlCodes(var1.method_26764()).trim());
   public GuiScreen recoveredField865;
   public GuiButton recoveredField866;
   public List<ResourcePackRepository.Entry> recoveredField867;
   public List<ResourcePackRepository.Entry> recoveredField868;
   public ResourcePackFolderEntry recoveredField869;
   public GuiButton recoveredField870;
   public GuiTextField recoveredField871;
   public GuiButton recoveredField872;
   public boolean recoveredField873;
   public GuiButton recoveredField874;

   public boolean method_01950(ResourcePackRepository.Entry var1) {
      if (!this.recoveredField856.contains(var1)) {
         this.recoveredField856.add(0, var1);
         return true;
      } else {
         return false;
      }
   }

   public void method_01953(List<ResourcePackRepository.Entry> var1) {
      String var2 = (String)CheatBreaker.getInstance().getGlobalSettings().recoveredField495.getValue();
      if (var2.equals("A-Z")) {
         var1.sort(recoveredField855);
      } else if (var2.equals("Z-A")) {
         var1.sort(recoveredField855.reversed());
      }
   }

   public void method_01957(ResourcePackRepository.Entry var1) {
      if (!this.recoveredField857.contains(var1) && this.recoveredField860 == null) {
         this.recoveredField857.add(var1);
         this.method_01953(this.recoveredField857);
      }
   }

   @Override
   public void updateScreen() {
      if (this.recoveredField862 != null && this.recoveredField862.method_28978()) {
         AsyncExecutor.method_22037(this::method_01946);
         this.method_01954(null);
      }

      this.recoveredField871.updateCursorCounter();
   }

   public void method_01948(ResourcePackRepository.Entry var1) {
      boolean var2 = false;

      for (ResourcePackRepository.Entry var4 : this.recoveredField867) {
         if (var4.getResourcePackName().equals(var1.getResourcePackName())) {
            var2 = true;
            break;
         }
      }

      if (!var2) {
         this.recoveredField867.add(var1);
         this.method_01953(this.recoveredField867);
      }
   }

   public void method_01954(ResourcePackFolder var1) {
      this.recoveredField860 = var1;
      int var2 = (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField590.getValue() ? 26 : 4;
      if (var1 == null) {
         if (this.recoveredField871 != null) {
            this.recoveredField871.setVisible((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField590.getValue());
         }

         this.recoveredField857 = new ArrayList<>(this.recoveredField863.getRepositoryEntriesAll());
         this.recoveredField857.removeAll(this.recoveredField856);
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField542.getValue()) {
            this.recoveredField869 = new ResourcePackFolderEntry(
               this, 10, 32, (this.l - 30) / 2, this.m - 80 - var2, 36, this.recoveredField857, this.recoveredField861, true
            );
         } else {
            this.recoveredField869 = new ResourcePackFolderEntry(
               this, this.l / 2 - 204, 32, 200, this.m - 80 - var2, 36, this.recoveredField857, this.recoveredField861, true
            );
         }
      } else {
         this.recoveredField857 = var1.method_26763();
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField542.getValue()) {
            this.recoveredField869 = new ResourcePackFolderEntry(
               this, 10, 32, (this.l - 30) / 2, this.m - 80 - var2, 36, this.recoveredField857, var1.method_26765(), true
            );
         } else {
            this.recoveredField869 = new ResourcePackFolderEntry(
               this, this.l / 2 - 204, 32, 200, this.m - 80 - var2, 36, this.recoveredField857, var1.method_26765(), true
            );
         }
      }

      this.method_01953(this.recoveredField857);
   }

   public List<ResourcePackFolder> method_01947() {
      return Collections.unmodifiableList(this.recoveredField861);
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      if (this.recoveredField873) {
         this.recoveredField873 = false;
      }

      if (this.j.theWorld != null && (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField572.getValue()) {
         this.drawDefaultBackground();
      } else {
         this.c(0);
      }

      this.recoveredField869.method_00747(var1, var2);
      this.recoveredField859.method_00747(var1, var2);
      recoveredField2942 = 0.0F;
      if (this.recoveredField869.recoveredField701.isEmpty()) {
         String var4 = !this.recoveredField871.getText().isEmpty() && this.recoveredField867.isEmpty() ? "No resource packs found." : "";
         if (this.recoveredField873) {
            var4 = "Discovering resource packs...";
         }

         this.drawCenteredString(this.q, EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + var4, this.l / 2 - 100, 60, 16777215);
      }

      if (this.recoveredField859.recoveredField701.isEmpty()) {
         String var5 = "Select resource packs.";
         this.drawCenteredString(this.q, EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + var5, this.l / 2 + 100, 60, 16777215);
      }

      super.drawCenteredString(
         this.j.fontRendererObj,
         this.recoveredField859.recoveredField1796,
         this.recoveredField859.recoveredField1780 + this.recoveredField859.recoveredField1790 / 2,
         this.recoveredField859.recoveredField1787 - 14,
         -1
      );
      super.drawCenteredString(
         this.j.fontRendererObj,
         this.recoveredField869.recoveredField1796,
         this.recoveredField869.recoveredField1780 + this.recoveredField869.recoveredField1790 / 2,
         this.recoveredField869.recoveredField1787 - 14,
         -1
      );
      this.drawCenteredString(this.q, I18n.format("resourcePack.folderInfo"), this.l / 2 - 77, this.m - 26, 8421504);
      super.drawScreen(var1, var2, var3);
      this.recoveredField871.drawTextBox();
      if (this.recoveredField871.getVisible() && !this.recoveredField871.isFocused() && this.recoveredField871.getText().isEmpty()) {
         super.drawString(
            this.q,
            EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + "Search...",
            (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField542.getValue() ? 14 : this.l / 2 - 200,
            this.m - 65,
            16777215
         );
      }
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.recoveredField869.method_00750();
      this.recoveredField859.method_00750();
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.k == this.recoveredField870.k) {
         try {
            Desktop.getDesktop().open(this.recoveredField863.getDirResourcepacks());
         } catch (IOException var5) {
            CheatBreaker.getInstance().method_19789().error("Failed to open file", var5);
         }
      } else if (var1.k == this.recoveredField874.k) {
         if (!this.recoveredField868.equals(this.recoveredField859.method_04862())) {
            ArrayList var2 = new ArrayList<>(Lists.reverse(this.recoveredField859.method_04862()));
            this.recoveredField863.setRepositories(var2);
            this.j.gameSettings.resourcePacks.clear();

            for (ResourcePackRepository.Entry var4 : (Iterable<ResourcePackRepository.Entry>)(Iterable<?>)(var2)) {
               this.j.gameSettings.resourcePacks.add(var4.getResourcePackName());
            }

            this.j.gameSettings.saveOptions();
            this.j.refreshResources();
         }

         this.j.displayGuiScreen(this.recoveredField865);
      } else if (var1.k == this.recoveredField858.k) {
         Setting var6 = CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1523;

         for (int var9 = 0; var9 < var6.getAcceptedValues().length; var9++) {
            if (var6.getAcceptedValues()[var9].toLowerCase().equalsIgnoreCase(var6.method_08874())) {
               if (var9 + 1 >= var6.getAcceptedValues().length) {
                  var6.setValue(var6.getAcceptedValues()[0]);
               } else {
                  var6.setValue(var6.getAcceptedValues()[var9 + 1]);
                  var6.setValue(var6.getAcceptedValues()[var9 + 1]);
               }
               break;
            }
         }

         this.recoveredField858.j = "Clear Glass: " + var6.getValue();
         this.j.renderGlobal.loadRenderers();
      } else if (var1.k == this.recoveredField866.k) {
         boolean var7 = CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1513.method_08908();
         this.recoveredField866.j = "Red String: " + (!var7 ? "ON" : "OFF");
         CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1513.setValue(!var7);
         this.j.renderGlobal.loadRenderers();
      } else if (var1.k == this.recoveredField872.k) {
         boolean var8 = CheatBreaker.getInstance().getGlobalSettings().recoveredField572.method_08908();
         this.recoveredField872.j = "Background: " + (!var8 ? "TRANSPARENT" : "NORMAL");
         CheatBreaker.getInstance().getGlobalSettings().recoveredField572.setValue(!var8);
         this.j.renderGlobal.loadRenderers();
      }
   }

   public void method_01946() {
      this.recoveredField861.clear();

      for (File var4 : Objects.requireNonNull(this.recoveredField863.getDirResourcepacks().listFiles(ResourcePackFolderScanner::method_26228))) {
         this.recoveredField861.add(new ResourcePackFolder(var4, this.recoveredField856));
      }

      this.method_01952((String)CheatBreaker.getInstance().getGlobalSettings().recoveredField495.getValue());
   }

   @Override
   public void initGui() {
      int var1 = (this.l - 30) / 2;
      int var2 = this.m - 80 - 46;
      boolean var3 = (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField542.getValue();
      int var4 = var3 ? this.l - var1 - 12 : this.l / 2 + 4;
      int var5 = var3 ? var1 + 2 : 200;
      if (var3) {
         int var10000 = var1 + 2;
      } else {
         short var9 = 200;
      }

      this.method_01954(this.recoveredField860);
      this.recoveredField859 = new SelectedResourcePackEntry(this, var4, 32, var5, var2 + (var3 && this.l / 2 > 338 ? 20 : 0), 36, this.recoveredField856);
      this.recoveredField870 = new GuiButton(0, this.l / 2 - 154, this.m - 48, 150, 20, I18n.format("resourcePack.openFolder"));
      this.n.add(this.recoveredField870);
      this.recoveredField874 = new GuiButton(2, var4, this.m - 48, 200, 20, I18n.format("gui.done"));
      this.n.add(this.recoveredField874);
      int var7 = this.recoveredField859.recoveredField1780
         + this.recoveredField859.recoveredField1790 / 2
         - this.q.getStringWidth(this.recoveredField859.recoveredField1796) / 2
         - (
            this.recoveredField869.recoveredField1780
               + this.recoveredField869.recoveredField1790 / 2
               + this.q.getStringWidth(this.recoveredField869.recoveredField1796) / 2
         )
         - 50;
      if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField542.getValue()) {
         this.recoveredField871 = new GuiTextField(299, this.q, 10, this.m - 70, (this.l - 30) / 2, 18);
      } else {
         this.recoveredField871 = new GuiTextField(299, this.q, this.l / 2 - 204, this.m - 70, 200, 18);
      }

      this.recoveredField871.setVisible(this.recoveredField860 == null && (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField590.getValue());
      int var8 = !var3 ? -1 : 1;
      this.recoveredField858 = new GuiOptionButton(
         100,
         this.l / 2 + 4,
         this.m - 71,
         114,
         20,
         "Clear Glass: " + CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1523.getValue()
      );
      this.n.add(this.recoveredField858);
      this.recoveredField866 = new GuiOptionButton(
         101,
         this.l / 2 + 118,
         this.m - 71,
         86,
         20,
         "Red String: " + ((Boolean)CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1513.getValue() ? "ON" : "OFF")
      );
      this.n.add(this.recoveredField866);
      this.recoveredField872 = new GuiOptionButton(
         102,
         this.l / 2 + (var3 && this.l / 2 > 338 ? 204 : 4),
         this.m - (var3 && this.l / 2 > 338 ? 71 : 91),
         var3 && this.l / 2 > 338 ? 138 : 200,
         20,
         "Background: " + ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField572.getValue() ? "TRANSPARENT" : "NORMAL")
      );
      this.n.add(this.recoveredField872);
      this.recoveredField871.setFocused(true);
   }

   public void method_01952(String var1) {
      if (var1.equals("A-Z")) {
         this.recoveredField861.sort(recoveredField864);
      } else if (var1.equals("Z-A")) {
         this.recoveredField861.sort(recoveredField864.reversed());
      }
   }

   public void method_01958(String var1) {
      if (var1.isEmpty()) {
         this.method_01954(null);
      } else {
         this.recoveredField867.clear();

         for (ResourcePackRepository.Entry var3 : this.recoveredField857) {
            if (StringUtils.stripControlCodes(var3.getResourcePackName()).toLowerCase().contains(var1)
               || StringUtils.stripControlCodes(var3.getTexturePackDescription()).toLowerCase().contains(var1)) {
               this.method_01948(var3);
            }
         }

         for (ResourcePackFolder var5 : this.recoveredField861) {
            this.method_01955(var5, var1);
         }

         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField542.getValue()) {
            this.recoveredField869 = new ResourcePackFolderEntry(this, 10, 32, (this.l - 30) / 2, this.m - 80 - 26, 36, this.recoveredField867, null, false);
         } else {
            this.recoveredField869 = new ResourcePackFolderEntry(this, this.l / 2 - 204, 32, 200, this.m - 80 - 26, 36, this.recoveredField867, null, false);
         }

         this.method_01953(this.recoveredField867);
      }
   }

   public ResourcePackGui(GuiScreen var1) {
      this.recoveredField865 = var1;
      this.recoveredField863 = var1.j.getResourcePackRepository();
      this.recoveredField857 = new ArrayList<>();
      this.recoveredField867 = new ArrayList<>();
      this.recoveredField856 = new ArrayList<>(Lists.reverse(this.recoveredField863.getRepositoryEntries()));
      this.recoveredField868 = Collections.unmodifiableList(new ArrayList<>(this.recoveredField856));
      this.recoveredField861 = new ArrayList<>();
      AsyncExecutor.method_22037(() -> {
         this.recoveredField863.updateRepositoryEntriesAll();
         this.method_01946();
         this.recoveredField862 = new RecursiveDirectoryWatcher(this.recoveredField863.getDirResourcepacks().toPath());
         this.recoveredField873 = true;
      });
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      super.mouseClicked(var1, var2, var3);
      if (this.recoveredField871.getVisible()) {
         this.recoveredField871.mouseClicked(var1, var2, var3);
      }
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      super.keyTyped(var1, var2);
      if (this.recoveredField871.textboxKeyTyped(var1, var2)) {
         this.method_01958(this.recoveredField871.getText().toLowerCase());
      }
   }

   public boolean method_01959() {
      return !this.recoveredField871.getText().isEmpty();
   }

   public void method_01955(ResourcePackFolder var1, String var2) {
      for (ResourcePackRepository.Entry var4 : var1.method_26763()) {
         if (StringUtils.stripControlCodes(var4.getResourcePackName().toLowerCase()).contains(var2)
            || StringUtils.stripControlCodes(var4.getTexturePackDescription()).toLowerCase().contains(var2)) {
            this.method_01948(var4);
         }
      }

      for (ResourcePackFolder var6 : var1.method_26765()) {
         this.method_01955(var6, var2);
      }
   }
}
