package net.minecraft.client.gui;

import com.cheatbreaker.client.BuildBranch;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.biome.BiomeColorHelper$2;

public class GuiYesNo extends GuiScreen {
   public String cancelButtonText;
   public int parentButtonClickedId;
   public BuildBranch field_0004;
   public String messageLine1;
   public GuiYesNoCallback parentScreen;
   public int ticksUntilEnable;
   public BiomeColorHelper$2 field_0009;
   public String confirmButtonText;
   public String messageLine2;
   public List<String> field_175298_s = Lists.newArrayList();
   public CosmeticType field_0008;

   @Override
   public void actionPerformed(GuiButton var1) {
      this.parentScreen.confirmClicked(var1.k == 0, this.parentButtonClickedId);
   }

   @Override
   public void initGui() {
      this.n.add(new GuiOptionButton(0, this.l / 2 - 155, this.m / 6 + 96, this.confirmButtonText));
      this.n.add(new GuiOptionButton(1, this.l / 2 - 155 + 160, this.m / 6 + 96, this.cancelButtonText));
      this.field_175298_s.clear();
      this.field_175298_s.addAll(this.q.listFormattedStringToWidth(this.messageLine2, this.l - 50));
   }

   public GuiYesNo(GuiYesNoCallback var1, String var2, String var3, String var4, String var5, int var6) {
      this.parentScreen = var1;
      this.messageLine1 = var2;
      this.messageLine2 = var3;
      this.confirmButtonText = var4;
      this.cancelButtonText = var5;
      this.parentButtonClickedId = var6;
   }

   public GuiYesNo(GuiYesNoCallback var1, String var2, String var3, int var4) {
      this.parentScreen = var1;
      this.messageLine1 = var2;
      this.messageLine2 = var3;
      this.parentButtonClickedId = var4;
      this.confirmButtonText = I18n.format("gui.yes");
      this.cancelButtonText = I18n.format("gui.no");
   }

   public void setButtonDelay(int var1) {
      this.ticksUntilEnable = var1;

      for (GuiButton var3 : this.n) {
         var3.l = false;
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.messageLine1, this.l / 2, 70, 16777215);
      int var4 = 90;

      for (String var6 : this.field_175298_s) {
         this.drawCenteredString(this.q, var6, this.l / 2, var4, 16777215);
         var4 += this.q.FONT_HEIGHT;
      }

      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (--this.ticksUntilEnable == 0) {
         for (GuiButton var2 : this.n) {
            var2.l = true;
         }
      }
   }
}
