package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class DisconnectConfirmationGui extends AbstractGui {
   public GradientTextButton recoveredField1021;
   public GradientTextButton recoveredField1022;
   public boolean recoveredField1023;
   public ColorFade recoveredField1024;
   public GradientTextButton recoveredField1025;
   public GuiScreen recoveredField1026;

   @Override
   public void initGui() {
      this.method_11296();
      this.recoveredField1023 = true;
      float var1 = this.getScaledWidth() / 2.0F;
      float var2 = this.getScaledHeight() / 2.0F - 50.0F;
      if (!this.j.isSingleplayer() && CheatBreaker.getInstance().getGlobalSettings().recoveredField512.method_08908()) {
         this.recoveredField1025.setElementSize(var1 - 75.0F, var2 + 50.0F, 74.0F, 12.0F);
         this.recoveredField1021.setElementSize(var1 + 1.0F, var2 + 50.0F, 74.0F, 12.0F);
      } else {
         this.recoveredField1025.setElementSize(var1 - 75.0F, var2 + 50.0F, 150.0F, 12.0F);
      }

      this.recoveredField1022.setElementSize(var1 - 75.0F, var2 + 64.0F, 150.0F, 12.0F);
      this.recoveredField1022.method_25133();
   }

   public DisconnectConfirmationGui(GuiScreen var1) {
      this.recoveredField1026 = var1;
      this.recoveredField1024 = new ColorFade(2000L, -1, -52429);
      this.recoveredField1022 = new GradientTextButton("Back to Game Menu");
      this.recoveredField1025 = new GradientTextButton(I18n.format("menu.disconnect"));
      this.recoveredField1021 = new GradientTextButton("Reconnect");
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      if (this.recoveredField1022.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(this.recoveredField1026);
      } else if (this.recoveredField1025.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.theWorld.method_05035();
         this.j.loadWorld(null);
         this.j.displayGuiScreen(new LegacyMainMenu());
      } else if (this.recoveredField1021.a_(var1, var2)) {
         if (this.j.currentServerData != null && this.j.theWorld != null) {
            this.j.theWorld.method_05035();
            this.j.loadWorld((WorldClient)null);
         }

         if (this.j.currentServerData != null) {
            this.j.displayGuiScreen(new GuiConnecting(this, this.j, this.j.currentServerData));
         }
      }
   }

   @Override
   public void a_() {
      this.j.entityRenderer.stopUseShader();
   }

   @Override
   public void drawMenu(float var1, float var2) {
      if (this.recoveredField1023 && this.recoveredField1024.method_21210()) {
         this.recoveredField1023 = false;
      } else if (!this.recoveredField1023 && this.recoveredField1024.method_21210()) {
         this.recoveredField1023 = true;
      }

      this.method_11295(this.getScaledWidth(), this.getScaledHeight());
      float var3 = this.getScaledWidth() / 2.0F;
      float var4 = this.getScaledHeight() / 2.0F - 50.0F;
      CheatBreaker.getInstance()
         .recoveredField1595
         .drawCenteredString("WARNING!", var3, var4, this.recoveredField1024.method_25066(this.recoveredField1023).getRGB());
      CheatBreaker.getInstance().recoveredField1548.drawCenteredString("Are you sure you want to disconnect?", var3, var4 + 15.0F, -1);
      this.recoveredField1022.drawElement(var1, var2, true);
      this.recoveredField1025.drawElement(var1, var2, true);
      if (!this.j.isSingleplayer() && CheatBreaker.getInstance().getGlobalSettings().recoveredField512.method_08908()) {
         this.recoveredField1021.drawElement(var1, var2, true);
      }
   }

   @Override
   public void onMouseReleased(float var1, float var2, int var3) {
   }
}
