package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenCapeOF;

public class GuiCustomizeSkin extends GuiScreen {
   public String title;
   public GuiScreen parentScreen;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.title, this.l / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   public String func_175358_a(EnumPlayerModelParts var1) {
      String var2;
      if (this.j.gameSettings.getModelParts().contains(var1)) {
         var2 = I18n.format("options.on");
      } else {
         var2 = I18n.format("options.off");
      }

      return var1.func_179326_d().getFormattedText() + ": " + var2;
   }

   public GuiCustomizeSkin(GuiScreen var1) {
      this.parentScreen = var1;
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k == 210) {
            this.j.displayGuiScreen(new GuiScreenCapeOF(this));
         }

         if (var1.k == 200) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(this.parentScreen);
         } else if (var1 instanceof GuiCustomizeSkin.ButtonPart) {
            EnumPlayerModelParts var2 = ((GuiCustomizeSkin.ButtonPart)var1).playerModelParts;
            this.j.gameSettings.switchModelPartEnabled(var2);
            var1.j = this.func_175358_a(var2);
         }
      }
   }

   @Override
   public void initGui() {
      int var1 = 0;
      this.title = I18n.format("options.skinCustomisation.title");

      for (EnumPlayerModelParts var5 : EnumPlayerModelParts.values()) {
         this.n.add(new GuiCustomizeSkin.ButtonPart(var5.getPartId(), this.l / 2 - 155 + var1 % 2 * 160, this.m / 6 + 24 * (var1 >> 1), 150, 20, var5));
         var1++;
      }

      if (var1 % 2 == 1) {
         var1++;
      }

      this.n.add(new GuiButtonOF(210, this.l / 2 - 100, this.m / 6 + 24 * (var1 >> 1), I18n.format("of.options.skinCustomisation.ofCape")));
      var1 += 2;
      this.n.add(new GuiButton(200, this.l / 2 - 100, this.m / 6 + 24 * (var1 >> 1), I18n.format("gui.done")));
   }

   public class ButtonPart extends GuiButton {
      public EnumPlayerModelParts playerModelParts;

      public ButtonPart(int var2, int var3, int var4, int var5, int var6, EnumPlayerModelParts var7) {
         super(var2, var3, var4, var5, var6, GuiCustomizeSkin.this.func_175358_a(var7));
         this.playerModelParts = var7;
      }
   }
}
