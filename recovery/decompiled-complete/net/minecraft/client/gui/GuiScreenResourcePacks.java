package net.minecraft.client.gui;

import com.cheatbreaker.client.module.staff.StaffModule;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.util.Util;
import net.minecraft.util.Util$EnumOS;
import org.apache.log4j.chainsaw.EventDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

public class GuiScreenResourcePacks extends GuiScreen {
   public List<ResourcePackListEntry> availableResourcePacks;
   public GuiScreen parentScreen;
   public static Logger logger = LogManager.getLogger();
   public StaffModule field_0006;
   public GuiResourcePackAvailable availableResourcePacksList;
   public List<ResourcePackListEntry> selectedResourcePacks;
   public boolean changed = false;
   public EventDetails field_0005;
   public GuiResourcePackSelected selectedResourcePacksList;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.c(0);
      this.availableResourcePacksList.a(var1, var2, var3);
      this.selectedResourcePacksList.a(var1, var2, var3);
      this.drawCenteredString(this.q, I18n.format("resourcePack.title"), this.l / 2, 16, 16777215);
      this.drawCenteredString(this.q, I18n.format("resourcePack.folderInfo"), this.l / 2 - 77, this.m - 26, 8421504);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
      super.mouseReleased(var1, var2, var3);
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 2) {
            File var2 = this.j.getResourcePackRepository().getDirResourcepacks();
            String var3 = var2.getAbsolutePath();
            if (Util.getOSType() == Util$EnumOS.OSX) {
               try {
                  logger.info(var3);
                  Runtime.getRuntime().exec(new String[]{"/usr/bin/open", var3});
                  return;
               } catch (IOException var9) {
                  logger.error("Couldn't open file", var9);
               }
            } else if (Util.getOSType() == Util$EnumOS.WINDOWS) {
               String var4 = String.format("cmd.exe /C start \"Open file\" \"%s\"", var3);

               try {
                  Runtime.getRuntime().exec(var4);
                  return;
               } catch (IOException var8) {
                  logger.error("Couldn't open file", var8);
               }
            }

            boolean var13 = false;

            try {
               Class var5 = Class.forName("java.awt.Desktop");
               Object var6 = var5.getMethod("getDesktop").invoke(null);
               var5.getMethod("browse", URI.class).invoke(var6, var2.toURI());
            } catch (Throwable var7) {
               logger.error("Couldn't open link", var7);
               var13 = true;
            }

            if (var13) {
               logger.info("Opening via system class!");
               Sys.openURL("file://" + var3);
            }
         } else if (var1.k == 1) {
            if (this.changed) {
               ArrayList var10 = Lists.newArrayList();

               for (ResourcePackListEntry var14 : this.selectedResourcePacks) {
                  if (var14 instanceof ResourcePackListEntryFound) {
                     var10.add(((ResourcePackListEntryFound)var14).func_148318_i());
                  }
               }

               Collections.reverse(var10);
               this.j.getResourcePackRepository().setRepositories(var10);
               this.j.gameSettings.resourcePacks.clear();
               this.j.gameSettings.incompatibleResourcePacks.clear();

               for (ResourcePackRepository$Entry var15 : var10) {
                  this.j.gameSettings.resourcePacks.add(var15.getResourcePackName());
                  if (var15.func_183027_f() != 1) {
                     this.j.gameSettings.incompatibleResourcePacks.add(var15.getResourcePackName());
                  }
               }

               this.j.gameSettings.saveOptions();
               this.j.refreshResources();
            }

            this.j.displayGuiScreen(this.parentScreen);
         }
      }
   }

   public GuiScreenResourcePacks(GuiScreen var1) {
      this.parentScreen = var1;
   }

   public List<ResourcePackListEntry> getSelectedResourcePacks() {
      return this.selectedResourcePacks;
   }

   public void markChanged() {
      this.changed = true;
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.availableResourcePacksList.b(var1, var2, var3);
      this.selectedResourcePacksList.b(var1, var2, var3);
   }

   public List<ResourcePackListEntry> getListContaining(ResourcePackListEntry var1) {
      return this.hasResourcePackEntry(var1) ? this.selectedResourcePacks : this.availableResourcePacks;
   }

   public boolean hasResourcePackEntry(ResourcePackListEntry var1) {
      return this.selectedResourcePacks.contains(var1);
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.selectedResourcePacksList.handleMouseInput();
      this.availableResourcePacksList.handleMouseInput();
   }

   @Override
   public void initGui() {
      this.n.add(new GuiOptionButton(2, this.l / 2 - 154, this.m - 48, I18n.format("resourcePack.openFolder")));
      this.n.add(new GuiOptionButton(1, this.l / 2 + 4, this.m - 48, I18n.format("gui.done")));
      if (!this.changed) {
         this.availableResourcePacks = Lists.newArrayList();
         this.selectedResourcePacks = Lists.newArrayList();
         ResourcePackRepository var1 = this.j.getResourcePackRepository();
         var1.updateRepositoryEntriesAll();
         ArrayList var2 = Lists.newArrayList(var1.getRepositoryEntriesAll());
         var2.removeAll(var1.getRepositoryEntries());

         for (ResourcePackRepository$Entry var4 : var2) {
            this.availableResourcePacks.add(new ResourcePackListEntryFound(this, var4));
         }

         for (ResourcePackRepository$Entry var6 : Lists.reverse(var1.getRepositoryEntries())) {
            this.selectedResourcePacks.add(new ResourcePackListEntryFound(this, var6));
         }

         this.selectedResourcePacks.add(new ResourcePackListEntryDefault(this));
      }

      this.availableResourcePacksList = new GuiResourcePackAvailable(this.j, 200, this.m, this.availableResourcePacks);
      this.availableResourcePacksList.i(this.l / 2 - 4 - 200);
      this.availableResourcePacksList.registerScrollButtons(7, 8);
      this.selectedResourcePacksList = new GuiResourcePackSelected(this.j, 200, this.m, this.selectedResourcePacks);
      this.selectedResourcePacksList.i(this.l / 2 + 4);
      this.selectedResourcePacksList.registerScrollButtons(7, 8);
   }

   public List<ResourcePackListEntry> getAvailableResourcePacks() {
      return this.availableResourcePacks;
   }
}
