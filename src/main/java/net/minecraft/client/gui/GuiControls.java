package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class GuiControls extends GuiScreen {
   public GameSettings options;
   public GuiButton buttonReset;
   public static GameSettings.Options[] optionsArr = new GameSettings.Options[]{
      GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN
   };
   public GuiKeyBindingList keyBindingList;
   public GuiScreen parentScreen;
   public KeyBinding buttonId;
   public long time;
   public String screenTitle = "Controls";

   @Override
   public void initGui() {
      this.keyBindingList = new GuiKeyBindingList(this, this.j);
      this.n.add(new GuiButton(200, this.l / 2 - 155, this.m - 29, 150, 20, I18n.format("gui.done")));
      this.n.add(this.buttonReset = new GuiButton(201, this.l / 2 - 155 + 160, this.m - 29, 150, 20, I18n.format("controls.resetAll")));
      this.screenTitle = I18n.format("controls.title");
      int var1 = 0;

      for (GameSettings.Options var5 : optionsArr) {
         if (var5.getEnumFloat()) {
            this.n.add(new GuiOptionSlider(var5.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, 18 + 24 * (var1 >> 1), var5));
         } else {
            this.n
               .add(
                  new GuiOptionButton(
                     var5.returnEnumOrdinal(), this.l / 2 - 155 + var1 % 2 * 160, 18 + 24 * (var1 >> 1), var5, this.options.getKeyBinding(var5)
                  )
               );
         }

         var1++;
      }
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.keyBindingList.handleMouseInput();
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      if (this.buttonId != null) {
         this.options.setOptionKeyBinding(this.buttonId, -100 + var3);
         this.buttonId = null;
         KeyBinding.resetKeyBindingArrayAndHash();
      } else if (var3 != 0 || !this.keyBindingList.b(var1, var2, var3)) {
         super.mouseClicked(var1, var2, var3);
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.keyBindingList.a(var1, var2, var3);
      this.drawCenteredString(this.q, this.screenTitle, this.l / 2, 8, 16777215);
      boolean var4 = true;

      for (KeyBinding var8 : this.options.keyBindings) {
         if (var8.getKeyCode() != var8.getKeyCodeDefault()) {
            var4 = false;
            break;
         }
      }

      this.buttonReset.l = !var4;
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      if (this.buttonId != null) {
         if (var2 == 1) {
            this.options.setOptionKeyBinding(this.buttonId, 0);
         } else if (var2 != 0) {
            this.options.setOptionKeyBinding(this.buttonId, var2);
         } else if (var1 > 0) {
            this.options.setOptionKeyBinding(this.buttonId, var1 + 256);
         }

         this.buttonId = null;
         this.time = Minecraft.getSystemTime();
         KeyBinding.resetKeyBindingArrayAndHash();
      } else {
         super.keyTyped(var1, var2);
      }
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
      if (var3 != 0 || !this.keyBindingList.c(var1, var2, var3)) {
         super.mouseReleased(var1, var2, var3);
      }
   }

   public GuiControls(GuiScreen var1, GameSettings var2) {
      this.buttonId = null;
      this.parentScreen = var1;
      this.options = var2;
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.k == 200) {
         this.j.displayGuiScreen(this.parentScreen);
      } else if (var1.k == 201) {
         for (KeyBinding var5 : this.j.gameSettings.keyBindings) {
            var5.setKeyCode(var5.getKeyCodeDefault());
         }

         KeyBinding.resetKeyBindingArrayAndHash();
      } else if (var1.k < 100 && var1 instanceof GuiOptionButton) {
         this.options.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
         var1.j = this.options.getKeyBinding(GameSettings.Options.getEnumOptions(var1.k));
      }
   }
}
