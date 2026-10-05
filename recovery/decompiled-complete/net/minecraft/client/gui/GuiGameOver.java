package net.minecraft.client.gui;

import net.minecraft.block.BlockMushroom;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

public class GuiGameOver extends GuiScreen implements GuiYesNoCallback {
   public boolean field_146346_f = false;
   public BlockMushroom field_0002;
   public int enableButtonsTimer;

   @Override
   public void confirmClicked(boolean var1, int var2) {
      if (var1) {
         this.j.theWorld.method_05035();
         this.j.loadWorld((WorldClient)null);
         this.j.displayGuiScreen(new GuiMainMenu());
      } else {
         this.j.thePlayer.respawnPlayer();
         this.j.displayGuiScreen((GuiScreen)null);
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      switch (var1.k) {
         case 0:
            this.j.thePlayer.respawnPlayer();
            this.j.displayGuiScreen((GuiScreen)null);
            break;
         case 1:
            if (this.j.theWorld.P().isHardcoreModeEnabled()) {
               this.j.displayGuiScreen(new GuiMainMenu());
            } else {
               GuiYesNo var2 = new GuiYesNo(
                  this, I18n.format("deathScreen.quit.confirm"), "", I18n.format("deathScreen.titleScreen"), I18n.format("deathScreen.respawn"), 0
               );
               this.j.displayGuiScreen(var2);
               var2.setButtonDelay(20);
            }
      }
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.enableButtonsTimer++;
      if (this.enableButtonsTimer == 20) {
         for (GuiButton var2 : this.n) {
            var2.l = true;
         }
      }
   }

   @Override
   public void initGui() {
      this.n.clear();
      if (this.j.theWorld.P().isHardcoreModeEnabled()) {
         if (this.j.isIntegratedServerRunning()) {
            this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 4 + 96, I18n.format("deathScreen.deleteWorld")));
         } else {
            this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 4 + 96, I18n.format("deathScreen.leaveServer")));
         }
      } else {
         this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 4 + 72, I18n.format("deathScreen.respawn")));
         this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 4 + 96, I18n.format("deathScreen.titleScreen")));
         if (this.j.getSession() == null) {
            this.n.get(1).l = false;
         }
      }

      for (GuiButton var2 : this.n) {
         var2.l = false;
      }
   }

   @Override
   public boolean b_() {
      return false;
   }

   @Override
   public void keyTyped(char var1, int var2) {
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawGradientRect(0, 0, this.l, this.m, 1615855616, -1602211792);
      GlStateManager.pushMatrix();
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
      boolean var4 = this.j.theWorld.P().isHardcoreModeEnabled();
      String var5 = var4 ? I18n.format("deathScreen.title.hardcore") : I18n.format("deathScreen.title");
      this.drawCenteredString(this.q, var5, this.l / 2 / 2, 30, 16777215);
      GlStateManager.popMatrix();
      if (var4) {
         this.drawCenteredString(this.q, I18n.format("deathScreen.hardcoreInfo"), this.l / 2, 144, 16777215);
      }

      this.drawCenteredString(
         this.q, I18n.format("deathScreen.score") + ": " + EnumChatFormatting.YELLOW + this.j.thePlayer.getScore(), this.l / 2, 100, 16777215
      );
      super.drawScreen(var1, var2, var3);
   }
}
