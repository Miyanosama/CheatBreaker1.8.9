package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class CompetitiveLeaveWarningGui extends AbstractGui {
   public ColorFade recoveredField310;
   public boolean recoveredField311;
   public GradientTextButton recoveredField312;
   public GradientTextButton recoveredField313;
   public GuiScreen recoveredField314;

   @Override
   public void onMouseReleased(float var1, float var2, int var3) {
   }

   public CompetitiveLeaveWarningGui(GuiScreen var1) {
      this.recoveredField314 = var1;
      this.recoveredField310 = new ColorFade(2000L, -1, -52429);
      this.recoveredField313 = new GradientTextButton("Cancel");
      this.recoveredField312 = new GradientTextButton("I understand, continue");
   }

   @Override
   public void drawMenu(float var1, float var2) {
      if (this.recoveredField311 && this.recoveredField310.method_21210()) {
         this.recoveredField311 = false;
      } else if (!this.recoveredField311 && this.recoveredField310.method_21210()) {
         this.recoveredField311 = true;
      }

      this.method_11295(this.getScaledWidth(), this.getScaledHeight());
      float var3 = this.getScaledWidth() / 2.0F;
      float var4 = this.getScaledHeight() / 2.0F - 50.0F;
      CheatBreaker.getInstance()
         .recoveredField1595
         .drawCenteredString("WARNING!", var3, var4, this.recoveredField310.method_25066(this.recoveredField311).getRGB());
      CheatBreaker.getInstance().recoveredField1548.drawCenteredString("Leaving competitive games may result in penalties.", var3, var4 + 15.0F, -1);
      CheatBreaker.getInstance()
         .recoveredField1548
         .drawCenteredString("You may be suspended from competitive games if you continue leaving games!", var3, var4 + 25.0F, -1);
      this.recoveredField313.drawElement(var1, var2, true);
      this.recoveredField312.drawElement(var1, var2, true);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      if (this.recoveredField313.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(this.recoveredField314);
      } else if (this.recoveredField312.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.theWorld.method_05035();
         this.j.loadWorld(null);
         this.j.displayGuiScreen(new MainMenu());
      }
   }

   @Override
   public void a_() {
      this.j.entityRenderer.stopUseShader();
   }

   @Override
   public void initGui() {
      this.method_11296();
      this.recoveredField311 = true;
      float var1 = this.getScaledWidth() / 2.0F;
      float var2 = this.getScaledHeight() / 2.0F - 50.0F;
      this.recoveredField312.setElementSize(var1 - 75.0F, var2 + 50.0F, 150.0F, 12.0F);
      this.recoveredField313.setElementSize(var1 - 75.0F, var2 + 64.0F, 150.0F, 12.0F);
      this.recoveredField313.method_25133();
   }
}
