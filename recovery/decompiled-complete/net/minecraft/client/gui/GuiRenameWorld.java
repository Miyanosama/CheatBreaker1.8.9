package net.minecraft.client.gui;

import io.netty.channel.udt.nio.NioUdtProvider$1;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.input.Keyboard;

public class GuiRenameWorld extends GuiScreen {
   public NioUdtProvider$1 field_0001;
   public String saveName;
   public GuiTextField field_146583_f;
   public GuiScreen parentScreen;

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.n.clear();
      this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 4 + 96 + 12, I18n.format("selectWorld.renameButton")));
      this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 4 + 120 + 12, I18n.format("gui.cancel")));
      ISaveFormat var1 = this.j.getSaveLoader();
      WorldInfo var2 = var1.getWorldInfo(this.saveName);
      String var3 = var2.getWorldName();
      this.field_146583_f = new GuiTextField(2, this.q, this.l / 2 - 100, 60, 200, 20);
      this.field_146583_f.setFocused(true);
      this.field_146583_f.setText(var3);
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, I18n.format("selectWorld.renameTitle"), this.l / 2, 20, 16777215);
      this.drawString(this.q, I18n.format("selectWorld.enterName"), this.l / 2 - 100, 47, 10526880);
      this.field_146583_f.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void keyTyped(char var1, int var2) {
      this.field_146583_f.textboxKeyTyped(var1, var2);
      this.n.get(0).l = this.field_146583_f.getText().trim().length() > 0;
      if (var2 == 28 || var2 == 156) {
         this.actionPerformed(this.n.get(0));
      }
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   public GuiRenameWorld(GuiScreen var1, String var2) {
      this.parentScreen = var1;
      this.saveName = var2;
   }

   @Override
   public void updateScreen() {
      this.field_146583_f.updateCursorCounter();
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 1) {
            this.j.displayGuiScreen(this.parentScreen);
         } else if (var1.k == 0) {
            ISaveFormat var2 = this.j.getSaveLoader();
            var2.renameWorld(this.saveName, this.field_146583_f.getText().trim());
            this.j.displayGuiScreen(this.parentScreen);
         }
      }
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_146583_f.mouseClicked(var1, var2, var3);
   }
}
