package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;

public class GuiErrorScreen extends GuiScreen {
   public String field_146312_f;
   public String field_146313_a;

   public GuiErrorScreen(String var1, String var2) {
      this.field_146313_a = var1;
      this.field_146312_f = var2;
   }

   @Override
   public void initGui() {
      super.initGui();
      this.n.add(new GuiButton(0, this.l / 2 - 100, 140, I18n.format("gui.cancel")));
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawGradientRect(0, 0, this.l, this.m, -12574688, -11530224);
      this.drawCenteredString(this.q, this.field_146313_a, this.l / 2, 90, 16777215);
      this.drawCenteredString(this.q, this.field_146312_f, this.l / 2, 110, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      this.j.displayGuiScreen((GuiScreen)null);
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
   }
}
