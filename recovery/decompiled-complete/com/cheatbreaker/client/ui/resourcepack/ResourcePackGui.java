package com.cheatbreaker.client.ui.resourcepack;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.fading.ExponentialFade;
import com.cheatbreaker.client.ui.overlay.friend.FriendRequestListElement;
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
import net.minecraft.client.renderer.entity.RenderRabbit;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import org.apache.log4j.helpers.FileWatchdog;
import recovered.unidentified.UnidentifiedClass0517;
import recovered.unidentified.UnidentifiedClass3581;
import recovered.unidentified.UnidentifiedClass4240;
import recovered.unidentified.UnidentifiedClass4327;
import recovered.unidentified.UnidentifiedClass4443;
import recovered.unidentified.UnidentifiedClass4855;

public class ResourcePackGui extends GuiScreen {
   public static Comparator<ResourcePackRepository$Entry> field_0012 = (var0, var1) -> StringUtils.stripControlCodes(var0.getResourcePackName())
      .trim()
      .compareToIgnoreCase(StringUtils.stripControlCodes(var1.getResourcePackName()).trim());
   public RenderRabbit field_0021;
   public List<ResourcePackRepository$Entry> field_0011;
   public FriendRequestListElement field_0018;
   public List<ResourcePackRepository$Entry> field_0006;
   public GuiButton field_0007;
   public UnidentifiedClass4240 field_0022;
   public UnidentifiedClass4443 field_0016;
   public List<UnidentifiedClass4443> field_0008;
   public UnidentifiedClass4855 field_0023;
   public ResourcePackRepository field_0005;
   public static Comparator<UnidentifiedClass4443> field_0013 = (var0, var1) -> StringUtils.stripControlCodes(var0.method_26764())
      .trim()
      .compareToIgnoreCase(StringUtils.stripControlCodes(var1.method_26764()).trim());
   public GuiScreen field_0015;
   public GuiButton field_0010;
   public List<ResourcePackRepository$Entry> field_0017;
   public ExponentialFade field_0020;
   public FileWatchdog field_0003;
   public List<ResourcePackRepository$Entry> field_0009;
   public UnidentifiedClass0517 field_0014;
   public GuiButton field_0019;
   public GuiTextField field_0002;
   public GuiButton field_0001;
   public boolean field_0004;
   public GuiButton field_0000;

   public boolean method_01950(ResourcePackRepository$Entry var1) {
      if (!this.field_0011.contains(var1)) {
         this.field_0011.add(0, var1);
         return true;
      } else {
         return false;
      }
   }

   public void method_01953(List<ResourcePackRepository$Entry> var1) {
      String var2 = (String)CheatBreaker.getInstance().getGlobalSettings().field_0108.getValue();
      if (var2.equals("A-Z")) {
         var1.sort(field_0012);
      } else if (var2.equals("Z-A")) {
         var1.sort(field_0012.reversed());
      }
   }

   public void method_01957(ResourcePackRepository$Entry var1) {
      if (!this.field_0006.contains(var1) && this.field_0016 == null) {
         this.field_0006.add(var1);
         this.method_01953(this.field_0006);
      }
   }

   @Override
   public void updateScreen() {
      if (this.field_0023 != null && this.field_0023.method_28978()) {
         UnidentifiedClass3581.method_22037(this::method_01946);
         this.method_01954(null);
      }

      this.field_0002.updateCursorCounter();
   }

   public void method_01948(ResourcePackRepository$Entry var1) {
      boolean var2 = false;

      for (ResourcePackRepository$Entry var4 : this.field_0017) {
         if (var4.getResourcePackName().equals(var1.getResourcePackName())) {
            var2 = true;
            break;
         }
      }

      if (!var2) {
         this.field_0017.add(var1);
         this.method_01953(this.field_0017);
      }
   }

   public void method_01954(UnidentifiedClass4443 var1) {
      this.field_0016 = var1;
      int var2 = CheatBreaker.getInstance().getGlobalSettings().field_0054.getValue() ? 26 : 4;
      if (var1 == null) {
         if (this.field_0002 != null) {
            this.field_0002.setVisible((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0054.getValue());
         }

         this.field_0006 = new ArrayList<>(this.field_0005.getRepositoryEntriesAll());
         this.field_0006.removeAll(this.field_0011);
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0106.getValue()) {
            this.field_0014 = new UnidentifiedClass0517(this, 10, 32, (this.l - 30) / 2, this.m - 80 - var2, 36, this.field_0006, this.field_0008, true);
         } else {
            this.field_0014 = new UnidentifiedClass0517(this, this.l / 2 - 204, 32, 200, this.m - 80 - var2, 36, this.field_0006, this.field_0008, true);
         }
      } else {
         this.field_0006 = var1.method_26763();
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0106.getValue()) {
            this.field_0014 = new UnidentifiedClass0517(this, 10, 32, (this.l - 30) / 2, this.m - 80 - var2, 36, this.field_0006, var1.method_26765(), true);
         } else {
            this.field_0014 = new UnidentifiedClass0517(this, this.l / 2 - 204, 32, 200, this.m - 80 - var2, 36, this.field_0006, var1.method_26765(), true);
         }
      }

      this.method_01953(this.field_0006);
   }

   public List<UnidentifiedClass4443> method_01947() {
      return Collections.unmodifiableList(this.field_0008);
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      if (this.field_0004) {
         this.field_0004 = false;
      }

      if (this.j.theWorld != null && (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0088.getValue()) {
         this.drawDefaultBackground();
      } else {
         this.c(0);
      }

      this.field_0014.method_00747(var1, var2);
      this.field_0022.method_00747(var1, var2);
      field_0003 = 0.0F;
      if (this.field_0014.field_0003.isEmpty()) {
         String var4 = !this.field_0002.getText().isEmpty() && this.field_0017.isEmpty() ? "No resource packs found." : "";
         if (this.field_0004) {
            var4 = "Discovering resource packs...";
         }

         this.drawCenteredString(this.q, EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + var4, this.l / 2 - 100, 60, 16777215);
      }

      if (this.field_0022.field_0003.isEmpty()) {
         String var5 = "Select resource packs.";
         this.drawCenteredString(this.q, EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + var5, this.l / 2 + 100, 60, 16777215);
      }

      super.drawCenteredString(
         this.j.fontRendererObj, this.field_0022.field_0005, this.field_0022.field_0014 + this.field_0022.field_0011 / 2, this.field_0022.field_0004 - 14, -1
      );
      super.drawCenteredString(
         this.j.fontRendererObj, this.field_0014.field_0005, this.field_0014.field_0014 + this.field_0014.field_0011 / 2, this.field_0014.field_0004 - 14, -1
      );
      this.drawCenteredString(this.q, I18n.format("resourcePack.folderInfo"), this.l / 2 - 77, this.m - 26, 8421504);
      super.drawScreen(var1, var2, var3);
      this.field_0002.drawTextBox();
      if (this.field_0002.getVisible() && !this.field_0002.isFocused() && this.field_0002.getText().isEmpty()) {
         super.drawString(
            this.q,
            EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + "Search...",
            CheatBreaker.getInstance().getGlobalSettings().field_0106.getValue() ? 14 : this.l / 2 - 200,
            this.m - 65,
            16777215
         );
      }
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.field_0014.method_00750();
      this.field_0022.method_00750();
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.k == this.field_0019.k) {
         try {
            Desktop.getDesktop().open(this.field_0005.getDirResourcepacks());
         } catch (IOException var5) {
            CheatBreaker.getInstance().method_19789().error("Failed to open file", var5);
         }
      } else if (var1.k == this.field_0000.k) {
         if (!this.field_0009.equals(this.field_0022.method_04862())) {
            ArrayList var2 = new ArrayList(Lists.reverse(this.field_0022.method_04862()));
            this.field_0005.setRepositories(var2);
            this.j.gameSettings.resourcePacks.clear();

            for (ResourcePackRepository$Entry var4 : var2) {
               this.j.gameSettings.resourcePacks.add(var4.getResourcePackName());
            }

            this.j.gameSettings.saveOptions();
            this.j.refreshResources();
         }

         this.j.displayGuiScreen(this.field_0015);
      } else if (var1.k == this.field_0007.k) {
         Setting var6 = CheatBreaker.getInstance().getModuleManager().field_0047.field_0004;

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

         this.field_0007.j = "Clear Glass: " + var6.getValue();
         this.j.renderGlobal.loadRenderers();
      } else if (var1.k == this.field_0010.k) {
         boolean var7 = CheatBreaker.getInstance().getModuleManager().field_0047.field_0012.method_08908();
         this.field_0010.j = "Red String: " + (!var7 ? "ON" : "OFF");
         CheatBreaker.getInstance().getModuleManager().field_0047.field_0012.setValue(!var7);
         this.j.renderGlobal.loadRenderers();
      } else if (var1.k == this.field_0001.k) {
         boolean var8 = CheatBreaker.getInstance().getGlobalSettings().field_0088.method_08908();
         this.field_0001.j = "Background: " + (!var8 ? "TRANSPARENT" : "NORMAL");
         CheatBreaker.getInstance().getGlobalSettings().field_0088.setValue(!var8);
         this.j.renderGlobal.loadRenderers();
      }
   }

   public void method_01946() {
      this.field_0008.clear();

      for (File var4 : Objects.requireNonNull(this.field_0005.getDirResourcepacks().listFiles(UnidentifiedClass4327::method_26228))) {
         this.field_0008.add(new UnidentifiedClass4443(var4, this.field_0011));
      }

      this.method_01952((String)CheatBreaker.getInstance().getGlobalSettings().field_0108.getValue());
   }

   @Override
   public void initGui() {
      int var1 = (this.l - 30) / 2;
      int var2 = this.m - 80 - 46;
      boolean var3 = (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0106.getValue();
      int var4 = var3 ? this.l - var1 - 12 : this.l / 2 + 4;
      int var5 = var3 ? var1 + 2 : 200;
      if (var3) {
         int var10000 = var1 + 2;
      } else {
         short var9 = 200;
      }

      this.method_01954(this.field_0016);
      this.field_0022 = new UnidentifiedClass4240(this, var4, 32, var5, var2 + (var3 && this.l / 2 > 338 ? 20 : 0), 36, this.field_0011);
      this.field_0019 = new GuiButton(0, this.l / 2 - 154, this.m - 48, 150, 20, I18n.format("resourcePack.openFolder"));
      this.n.add(this.field_0019);
      this.field_0000 = new GuiButton(2, var4, this.m - 48, 200, 20, I18n.format("gui.done"));
      this.n.add(this.field_0000);
      int var7 = this.field_0022.field_0014
         + this.field_0022.field_0011 / 2
         - this.q.getStringWidth(this.field_0022.field_0005) / 2
         - (this.field_0014.field_0014 + this.field_0014.field_0011 / 2 + this.q.getStringWidth(this.field_0014.field_0005) / 2)
         - 50;
      if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0106.getValue()) {
         this.field_0002 = new GuiTextField(299, this.q, 10, this.m - 70, (this.l - 30) / 2, 18);
      } else {
         this.field_0002 = new GuiTextField(299, this.q, this.l / 2 - 204, this.m - 70, 200, 18);
      }

      this.field_0002.setVisible(this.field_0016 == null && (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0054.getValue());
      int var8 = !var3 ? -1 : 1;
      this.field_0007 = new GuiOptionButton(
         100, this.l / 2 + 4, this.m - 71, 114, 20, "Clear Glass: " + CheatBreaker.getInstance().getModuleManager().field_0047.field_0004.getValue()
      );
      this.n.add(this.field_0007);
      this.field_0010 = new GuiOptionButton(
         101,
         this.l / 2 + 118,
         this.m - 71,
         86,
         20,
         "Red String: " + (CheatBreaker.getInstance().getModuleManager().field_0047.field_0012.getValue() ? "ON" : "OFF")
      );
      this.n.add(this.field_0010);
      this.field_0001 = new GuiOptionButton(
         102,
         this.l / 2 + (var3 && this.l / 2 > 338 ? 204 : 4),
         this.m - (var3 && this.l / 2 > 338 ? 71 : 91),
         var3 && this.l / 2 > 338 ? 138 : 200,
         20,
         "Background: " + (CheatBreaker.getInstance().getGlobalSettings().field_0088.getValue() ? "TRANSPARENT" : "NORMAL")
      );
      this.n.add(this.field_0001);
      this.field_0002.setFocused(true);
   }

   public void method_01952(String var1) {
      if (var1.equals("A-Z")) {
         this.field_0008.sort(field_0013);
      } else if (var1.equals("Z-A")) {
         this.field_0008.sort(field_0013.reversed());
      }
   }

   public void method_01958(String var1) {
      if (var1.isEmpty()) {
         this.method_01954(null);
      } else {
         this.field_0017.clear();

         for (ResourcePackRepository$Entry var3 : this.field_0006) {
            if (StringUtils.stripControlCodes(var3.getResourcePackName()).toLowerCase().contains(var1)
               || StringUtils.stripControlCodes(var3.getTexturePackDescription()).toLowerCase().contains(var1)) {
               this.method_01948(var3);
            }
         }

         for (UnidentifiedClass4443 var5 : this.field_0008) {
            this.method_01955(var5, var1);
         }

         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0106.getValue()) {
            this.field_0014 = new UnidentifiedClass0517(this, 10, 32, (this.l - 30) / 2, this.m - 80 - 26, 36, this.field_0017, null, false);
         } else {
            this.field_0014 = new UnidentifiedClass0517(this, this.l / 2 - 204, 32, 200, this.m - 80 - 26, 36, this.field_0017, null, false);
         }

         this.method_01953(this.field_0017);
      }
   }

   public ResourcePackGui(GuiScreen var1) {
      this.field_0015 = var1;
      this.field_0005 = var1.j.getResourcePackRepository();
      this.field_0006 = new ArrayList<>();
      this.field_0017 = new ArrayList<>();
      this.field_0011 = new ArrayList<>(Lists.reverse(this.field_0005.getRepositoryEntries()));
      this.field_0009 = Collections.unmodifiableList(new ArrayList<>(this.field_0011));
      this.field_0008 = new ArrayList<>();
      UnidentifiedClass3581.method_22037(() -> {
         this.field_0005.updateRepositoryEntriesAll();
         this.method_01946();
         this.field_0023 = new UnidentifiedClass4855(this.field_0005.getDirResourcepacks().toPath());
         this.field_0004 = true;
      });
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      if (this.field_0002.getVisible()) {
         this.field_0002.mouseClicked(var1, var2, var3);
      }
   }

   @Override
   public void keyTyped(char var1, int var2) {
      super.keyTyped(var1, var2);
      if (this.field_0002.textboxKeyTyped(var1, var2)) {
         this.method_01958(this.field_0002.getText().toLowerCase());
      }
   }

   public boolean method_01959() {
      return !this.field_0002.getText().isEmpty();
   }

   public void method_01955(UnidentifiedClass4443 var1, String var2) {
      for (ResourcePackRepository$Entry var4 : var1.method_26763()) {
         if (StringUtils.stripControlCodes(var4.getResourcePackName().toLowerCase()).contains(var2)
            || StringUtils.stripControlCodes(var4.getTexturePackDescription()).toLowerCase().contains(var2)) {
            this.method_01948(var4);
         }
      }

      for (UnidentifiedClass4443 var6 : var1.method_26765()) {
         this.method_01955(var6, var2);
      }
   }
}
