package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;

public class GuiMemoryErrorScreen extends GuiScreen {
   @Override
   public void initGui() {
      this.n.clear();
      this.n.add(new GuiOptionButton(0, this.l / 2 - 155, this.m / 4 + 120 + 12, I18n.format("gui.toTitle")));
      this.n.add(new GuiOptionButton(1, this.l / 2 - 155 + 160, this.m / 4 + 120 + 12, I18n.format("menu.quit")));
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.k == 0) {
         this.j.displayGuiScreen(new GuiMainMenu());
      } else if (var1.k == 1) {
         this.j.shutdown();
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, "Out of memory!", this.l / 2, this.m / 4 - 60 + 20, 16777215);
      this.drawString(this.q, "Minecraft has run out of memory.", this.l / 2 - 140, this.m / 4 - 60 + 60 + 0, 10526880);
      this.drawString(this.q, "This could be caused by a bug in the game or by the", this.l / 2 - 140, this.m / 4 - 60 + 60 + 18, 10526880);
      this.drawString(this.q, "Java Virtual Machine not being allocated enough", this.l / 2 - 140, this.m / 4 - 60 + 60 + 27, 10526880);
      this.drawString(this.q, "memory.", this.l / 2 - 140, this.m / 4 - 60 + 60 + 36, 10526880);
      this.drawString(this.q, "To prevent level corruption, the current game has quit.", this.l / 2 - 140, this.m / 4 - 60 + 60 + 54, 10526880);
      this.drawString(this.q, "We've tried to free up enough memory to let you go back to", this.l / 2 - 140, this.m / 4 - 60 + 60 + 63, 10526880);
      this.drawString(this.q, "the main menu and back to playing, but this may not have worked.", this.l / 2 - 140, this.m / 4 - 60 + 60 + 72, 10526880);
      this.drawString(this.q, "Please restart the game if you see this message again.", this.l / 2 - 140, this.m / 4 - 60 + 60 + 81, 10526880);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
   }
}
