package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class BuildInformationMenu extends MainMenuBase {
   public GradientTextButton recoveredField987 = new GradientTextButton("BACK");

   @Override
   public void drawMenu(float var1, float var2) {
      super.drawMenu(var1, var2);
      Gui.drawRect(
         this.getScaledWidth() / 2.0F - 80.0F,
         this.getScaledHeight() / 2.0F - 40.0F,
         this.getScaledWidth() / 2.0F + 80.0F,
         this.getScaledHeight() / 2.0F + 50.0F,
         788529152
      );
      this.recoveredField987.drawElement(var1, var2, true);
      CheatBreaker.getInstance().recoveredField1548.drawCenteredString(
         "CheatBreaker 1.8.9", this.getScaledWidth() / 2.0F, this.getScaledHeight() / 2.0F, -1118482
      );
   }

   @Override
   public void initGui() {
      super.initGui();
      this.recoveredField987.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() / 2.0F + 55.0F, 60.0F, 12.0F);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      if (this.recoveredField987.a_(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new MainMenu());
      }
   }
}
